/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package servlets.accounts;

import dao.accounts.Accounts;
import model.accounts.User;
import servlets.servlet.BaseServlet;
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
 * @author Gian Carlo Roxas
 * @author shermaine Sy
 * @author Geraldine Atayan
 * 
 */

public class UpdateAccount extends BaseServlet {

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

        if (redirect.equalsIgnoreCase("updateExternal")) {
            ArrayList<User> users = new ArrayList<>();
//            users = accountsDAO.getApprovedExternalUsers();
//            
//            users = accountsDAO.getApprovedExternalUsers();
                request.setAttribute("page", "ainternal");
                request.setAttribute("users_array", users);
                rd = request.getRequestDispatcher("/WEB-INF/JSPAccounts/approvalsInternal.jsp");
                rd.forward(request, response);
        } else if (redirect.equalsIgnoreCase("updateInternal")) {

            try {
                ArrayList<User> users = new ArrayList<>();
                users = accountsDAO.getUserForApprovalOthers();

                for (int i = 0; i < users.size(); i++) {
//                    accountsDAO.approveUser(users.get(i).getUserID());
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
        }

    }
}
