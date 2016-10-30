/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package servlets.reports.servlet;

import dao.reports.ReportDAO;
import model.reports.ReportAnalysis;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */

public class SetReportAnalysis extends HttpServlet {

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException sql exception
     * @throws java.text.ParseException parse exception
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String sector = request.getParameter("sector");
            String year = request.getParameter("year");
              ReportDAO ReportDAO = new ReportDAO();
              ArrayList<ReportAnalysis> reportAnalysis = new ArrayList<ReportAnalysis>();
            reportAnalysis =  ReportDAO.GetReportAnalysisbySectorSubmitted(sector, year);
            JSONArray array = new JSONArray();
            for (int i = 0; i < reportAnalysis.size(); i++) {
                JSONObject obj = new JSONObject();
                try {
                    
                    obj.put("imgSrc", reportAnalysis.get(i).getChartName());
                    obj.put("path", "chartImages/" + reportAnalysis.get(i).getChartName());
                    obj.put("text", reportAnalysis.get(i).getText());
                    obj.put("year", reportAnalysis.get(i).getYear());
                    obj.put("createdBy", reportAnalysis.get(i).getCreatedByName());
                    obj.put("sector", reportAnalysis.get(i).getSector());
                    array.put(obj);

                } catch (JSONException ex) {
                    Logger.getLogger(SetReportAnalysis.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            out.print(array);
            out.flush();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SetReportAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SetReportAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SetReportAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SetReportAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}