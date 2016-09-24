package servlets.accounts;

import dao.accounts.Accounts;
import model.accounts.User;
import servlets.servlet.BaseServlet;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Atayan
 * @author Roxas
 * @author Sy
 *
 */
public class AccountApproval extends BaseServlet {

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
        
        // ServletContext context= getServletContext();
        RequestDispatcher rd = null;
        Accounts accountsDAO = new Accounts();
//(int userID, String division, String position, Date employment) 
        if (redirect.equalsIgnoreCase("approveAllInternal")) {

            try {
                ArrayList<User> users = new ArrayList<>();
                users = accountsDAO.getUserForApprovalMember();

                for (int i = 0; i < users.size(); i++) {
//                    accountsDAO.approveUser(users.get(i).getUserID());
                }

                users = accountsDAO.getUserForApprovalMember();
                request.setAttribute("page", "ainternal");
                request.setAttribute("users_array", users);
                rd = request.getRequestDispatcher("/WEB-INF/JSPAccounts/approvalsInternal.jsp");
                rd.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(AccountApproval.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(AccountApproval.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (redirect.equalsIgnoreCase("approvalAllExternal")) {

            try {
                ArrayList<User> users = new ArrayList<>();
                users = accountsDAO.getUserForApprovalOthers();

                for (int i = 0; i < users.size(); i++) {
                    accountsDAO.approveUserExternal(users.get(i).getUserID());
                }

                users = accountsDAO.getUserForApprovalOthers();
                request.setAttribute("page", "aexternal");
                request.setAttribute("users_array", users);
                rd = request.getRequestDispatcher("/WEB-INF/JSPAccounts/approvalsExternal.jsp");
                rd.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(AccountApproval.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(AccountApproval.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (redirect.equalsIgnoreCase("approve")) {
         //(int userID, String division, String position, Date employment) 

            String userID = request.getParameter("userID");

            String division = request.getParameter("division");
            String position = request.getParameter("position");
            String employmentDate = request.getParameter("employmentDate");
            boolean x = false;
//            try {
////                x = accountsDAO.approveUser(Integer.parseInt(userID),division,position, Date.parse(employmentDate));
//            } catch (SQLException ex) {
//                Logger.getLogger(approvals.class.getName()).log(Level.SEVERE, null, ex);
//            }
            String json = new Gson().toJson(x);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(json);
        } else if (redirect.equalsIgnoreCase("reject")) {
            String userID = request.getParameter("userID");
            boolean x = false;
            try {
                x = accountsDAO.diassapproveUser(Integer.parseInt(userID));
            } catch (SQLException ex) {
                Logger.getLogger(AccountApproval.class.getName()).log(Level.SEVERE, null, ex);
            }
            String json = new Gson().toJson(x);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(json);
        }

    }
}
