/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package servlets.reports.servlet;

import dao.reports.ReportDAO;
import model.reports.Integrated;
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

public class SetIntegratedAnalysis extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
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
               ArrayList<Integrated>  Integrated = new  ArrayList<>();
             Integrated =  ReportDAO.GetReportIntegratedSubmitted(sector, year);
            JSONArray array = new JSONArray();
                for (int i = 0; i < Integrated.size(); i++) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("text", Integrated.get(i).getText());
                    obj.put("year", Integrated.get(i).getYear());
                    obj.put("createdBy", Integrated.get(i).getCreatedByName());
                    obj.put("sector", Integrated.get(i).getSector());
                    array.put(obj);

                } catch (JSONException ex) {
                    Logger.getLogger(SetIntegratedAnalysis.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SetIntegratedAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SetIntegratedAnalysis.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SetIntegratedAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SetIntegratedAnalysis.class.getName()).log(Level.SEVERE, null, ex);
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