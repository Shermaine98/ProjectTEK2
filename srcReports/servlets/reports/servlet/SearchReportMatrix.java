/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package servlets.reports.servlet;

import dao.reports.ReportDAO;
import servlets.servlet.BaseServlet;
import com.google.gson.Gson;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */


public class SearchReportMatrix extends BaseServlet {

      /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String year = request.getParameter("query");
        String sector = request.getParameter("sector");

        ReportDAO ReportDAO = new ReportDAO();
        ArrayList<Integer> arrYear = new ArrayList<>();
        try {
            arrYear = ReportDAO.SearchYearMatrix(year, sector);
        } catch (ParseException ex) {
            getLogger(SearchReportMatrix.class.getName()).log(SEVERE, null, ex);
        }

        ArrayList<String> censusYear = new ArrayList<>();

        for (int i = 0; i < arrYear.size(); i++) {
            if (!censusYear.contains(valueOf(arrYear.get(i)))) {
                censusYear.add(valueOf(arrYear.get(i)));
            }
        }
        Gson gson = new Gson();
        String json = gson.toJson(censusYear);
        response.getWriter().write("{\"suggestions\":" + json + "}");
    }

}
