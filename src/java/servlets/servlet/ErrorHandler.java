/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package servlets.servlet;



import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gian Carlo Roxas
 * @author shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class ErrorHandler extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("page", "error");
        RequestDispatcher rd = request.getRequestDispatcher("redirect_error.jsp");
        rd.forward(request, response);
    }
}
