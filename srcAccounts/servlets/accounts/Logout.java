/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package servlets.accounts;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gian Carlo Roxas
 * @author shermaine Sy
 * @author Geraldine Atayan
 * 
 */

public class Logout extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        
        try (PrintWriter out = response.getWriter()) {
            request.setAttribute("loggedOut", "success");
            request.getRequestDispatcher("/index.jsp").include(request, response);
            
            HttpSession session = request.getSession();
            session.invalidate();
        }
    }

}