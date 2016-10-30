/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package servlets.demo.servlet;

import servlets.servlet.BaseServlet;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */

public class ViewReportsDemo extends BaseServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = request.getParameter("page");
        RequestDispatcher rd = null;

        //NOT SPECIFIC
        if (page.equalsIgnoreCase("byAgeGroup")) {
            request.setAttribute("page", "byAgeGroup");
            rd = request.getRequestDispatcher("/WEB-INF/JSPDemo/ViewReportbyAgeGroup.jsp");
        } else if (page.equalsIgnoreCase("maritalStatus")) {
            request.setAttribute("page", "MaritalStatus");
            rd = request.getRequestDispatcher("/WEB-INF/JSPDemo/ViewReportMaritalStatus.jsp");
            rd.forward(request, response);
        } else if (page.equalsIgnoreCase("highestAttaintment")) {
            request.setAttribute("page", "highestAttaintment");
            rd = request.getRequestDispatcher("/WEB-INF/JSPDemo/ViewReportHighestAttaintment.jsp");
        } 
        //SPECIFIC
        else if (page.equalsIgnoreCase("byAgeGroupS")) {
            String censusYear = request.getParameter("censusYear");
            request.setAttribute("censusYear", censusYear);
            request.setAttribute("page", "byAgeGroupS");
            rd = request.getRequestDispatcher("/WEB-INF/JSPDemo/ViewReportbyAgeGroup.jsp");
        } else if (page.equalsIgnoreCase("maritalStatusS")) {
            String censusYear = request.getParameter("censusYear");
            request.setAttribute("page", "MaritalStatusS");
            request.setAttribute("censusYear", censusYear);
            rd = request.getRequestDispatcher("/WEB-INF/JSPDemo/ViewReportMaritalStatus.jsp");
            rd.forward(request, response);
        } else if (page.equalsIgnoreCase("highestAttaintmentS")) {
            String censusYear = request.getParameter("formID");
            request.setAttribute("formID", censusYear);
            request.setAttribute("page", "highestAttaintmentS");
            rd = request.getRequestDispatcher("/WEB-INF/JSPDemo/ViewReportHighestAttaintment.jsp");
        }

        rd.forward(request, response);

    }
}
