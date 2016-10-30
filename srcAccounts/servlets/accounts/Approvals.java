/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.accounts;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlets.servlet.BaseServlet;
import dao.accounts.Accounts;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import model.accounts.User;

/**
 *
 * @author Atayan
 * @author Roxas
 * @author Sy
 *
 */
public class Approvals extends BaseServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String redirect = request.getParameter("redirect");
        RequestDispatcher rd = null;
        Accounts accountsDAO = new Accounts();

        if (redirect.equalsIgnoreCase("approveAllInternal")) {

            String[] userID = request.getParameterValues("userID");
            String[] division = request.getParameterValues("division");
            String[] position = request.getParameterValues("position");
            String[] employmentDate = request.getParameterValues("employmentDate");

            try {
                ArrayList<User> internalUsers = new ArrayList<>();
                internalUsers = accountsDAO.getUserForApprovalMember();
                ArrayList<User> externalUsers = new ArrayList<>();
                externalUsers = accountsDAO.getUserForApprovalOthers();

                for (int i = 0; i < internalUsers.size(); i++) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date parsed = format.parse(employmentDate[i]);
                    java.sql.Date sql = new java.sql.Date(parsed.getTime());
                    accountsDAO.approveUser(Integer.parseInt(userID[i]), division[i], position[i], sql);
                }

                internalUsers = accountsDAO.getUserForApprovalMember();
                request.setAttribute("type", "internal");
                request.setAttribute("internalUsers", internalUsers);
                request.setAttribute("externalUsers", externalUsers);
                request.setAttribute("notification", "approvedAllInternal");
                rd = request.getRequestDispatcher("/WEB-INF/JSPAccounts/approvals.jsp");
                rd.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Approvals.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Approvals.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (redirect.equalsIgnoreCase("approvalAllExternal")) {

            try {
                ArrayList<User> internalUsers = new ArrayList<>();
                internalUsers = accountsDAO.getUserForApprovalMember();
                ArrayList<User> externalUsers = new ArrayList<>();
                externalUsers = accountsDAO.getUserForApprovalOthers();

                for (int i = 0; i < externalUsers.size(); i++) {
                  accountsDAO.approveUserExternal(externalUsers.get(i).getUserID());
                }

                externalUsers = accountsDAO.getUserForApprovalOthers();
                request.setAttribute("type", "external");
                request.setAttribute("internalUsers", internalUsers);
                request.setAttribute("externalUsers", externalUsers);
                request.setAttribute("notification", "approvedAllExternal");
                rd = request.getRequestDispatcher("/WEB-INF/JSPAccounts/approvals.jsp");
                rd.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Approvals.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Approvals.class.getName()).log(Level.SEVERE, null, ex);
            }
        } //Internal
        else if (redirect.equalsIgnoreCase("approveI")) {
            try {
                String userID = request.getParameter("userID");
                String division = request.getParameter("division");
                String position = request.getParameter("position");
                String employmentDate = request.getParameter("employmentDate");

                boolean x = false;
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date parsed = format.parse(employmentDate);
                java.sql.Date sql = new java.sql.Date(parsed.getTime());

                try {
                  x=  accountsDAO.approveUser(Integer.parseInt(userID), division, position, sql);
                } catch (SQLException ex) {
                    Logger.getLogger(Approvals.class.getName()).log(Level.SEVERE, null, ex);
                }

                String json = new Gson().toJson(x);
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(json);

            } //External
            catch (ParseException ex) {
                Logger.getLogger(Approvals.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (redirect.equalsIgnoreCase("approveE")) {
            //(int userID, String division, String position, Date employment) 
            String userID = request.getParameter("userID");
            boolean x = false;
            try {
             x =  accountsDAO.approveUserExternal(Integer.parseInt(userID));
            } catch (SQLException ex) {
                Logger.getLogger(Approvals.class.getName()).log(Level.SEVERE, null, ex);
            }
            String json = new Gson().toJson(x);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(json);
        } else if (redirect.equalsIgnoreCase("reject")) {
            String userID = request.getParameter("userID");
            boolean x = false;
            try {
                x = accountsDAO.diassapproveUser(Integer.parseInt(userID.trim()));
            } catch (SQLException ex) {
                Logger.getLogger(Approvals.class.getName()).log(Level.SEVERE, null, ex);
            }
            String json = new Gson().toJson(x);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(json);
        }else{
        
        }

    }
}
