/*
 *  Copyright (C) ProjectTEK - DLSU CCS 2016
 *  All right Reserved   * 
 */

package servlet.setdata;

import dao.charts.SchoolEnrollment;
import model.education.EnrollmentDet;
import model.education.Enrollment;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import static java.util.logging.Level.SEVERE;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static java.lang.Integer.parseInt;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class SetEnrollmentDataServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String tempCensusYear = request.getParameter("censusYear");
            String classification = request.getParameter("classification");
            
            JSONArray jarrayGenderGrade = new JSONArray();
            JSONArray jarraySchool = new JSONArray();
            JSONObject ObjectAll = new JSONObject();
          
            int censusYear = parseInt(tempCensusYear);
            SchoolEnrollment charts = new SchoolEnrollment();
            EnrollmentDet total = charts.retrieveTotalMaleFemale(censusYear, classification);
            ArrayList<EnrollmentDet> byGrade = charts.retrieveGradeStatistics(censusYear, classification);
            ArrayList<Enrollment> bySchool = charts.retrieveSchoolStatistics(censusYear, classification);
            
            JSONObject objSchoolTotal = new JSONObject();
            try {
                objSchoolTotal.put("totalName", classification + " Schools");
                objSchoolTotal.put("totalMale", total.getMaleCount());
                objSchoolTotal.put("totalFemale", total.getFemaleCount());
            } catch (JSONException ex) {
                getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
            }
            
            for(int i = 0; i < byGrade.size(); i++){
                JSONObject objArrByGrade = new JSONObject();
                try {
                    objArrByGrade.put("byGradeLevel", byGrade.get(i).getGradeLevel());
                    objArrByGrade.put("byGradeLevelMale", byGrade.get(i).getMaleCount());
                    objArrByGrade.put("byGradeLevelFemale", byGrade.get(i).getFemaleCount());
                    jarrayGenderGrade.put(objArrByGrade);
                } catch (JSONException ex) {
                    getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            for(int i = 0; i < bySchool.size(); i++){
                JSONObject objArrBySchool = new JSONObject();
                JSONArray jarrayGrade = new JSONArray();
                try {
                    objArrBySchool.put("bySchoolName", bySchool.get(i).getSchoolName());
                    objArrBySchool.put("bySchoolDistrict", bySchool.get(i).getDistrict());
                    objArrBySchool.put("bySchoolSchoolType", bySchool.get(i).getSchoolType());
                    objArrBySchool.put("bySchoolClassification", bySchool.get(i).getClassification());
                    objArrBySchool.put("bySchoolTotalMale", bySchool.get(i).getTotalMale());
                    objArrBySchool.put("bySchoolTotalFemale", bySchool.get(i).getTotalFemale());
                    objArrBySchool.put("bySchoolGrandTotal", bySchool.get(i).getGrandTotal());
                    objArrBySchool.put("bySchoolGDI", bySchool.get(i).getGenderDisparityIndex());
                    for(int y = 0; y < bySchool.get(i).getEnrollmentDetArrayList().size();y++){
                        JSONObject objArrByGrade = new JSONObject();
                        objArrByGrade.put("bySchoolGrade", bySchool.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());
                        objArrByGrade.put("bySchoolGradeMale", bySchool.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        objArrByGrade.put("bySchoolGradeFemale", bySchool.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        jarrayGrade.put(objArrByGrade);
                    }
                    objArrBySchool.put("bySchoolGradeLevel", jarrayGrade);
                    
                    jarraySchool.put(objArrBySchool);
                } catch (JSONException ex) {
                    getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }

            
            ObjectAll.put("total", objSchoolTotal);
            ObjectAll.put("grade", jarrayGenderGrade);
            ObjectAll.put("school", jarraySchool);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write("[" + ObjectAll.toString() + "]");
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
        } catch (ParseException ex) {
            getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
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
        } catch (ParseException ex) {
            getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
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
