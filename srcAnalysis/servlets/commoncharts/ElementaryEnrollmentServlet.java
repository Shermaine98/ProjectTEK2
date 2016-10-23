/*
 *  Copyright (C) ProjectTEK - DLSU CCS 2016
 *  All right Reserved   * 
 */

package servlets.commoncharts;

import dao.analysis.CensusYearDAO;
import dao.analysis.ClassificationDAO;
import dao.analysis.DistrictDAO;
import dao.analysis.FactEnrollmentDAO;
import dao.analysis.GenderDAO;
import dao.analysis.GradeLevelDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.analysis.FactEnrollment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import servlet.setdata.SetAnalysisDataServlet;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class ElementaryEnrollmentServlet extends HttpServlet {

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
            throws ServletException, IOException, ParseException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            CensusYearDAO censusYearDAO = new CensusYearDAO();
            DistrictDAO chartsDistrict = new DistrictDAO();
            FactEnrollmentDAO chartEnrollment = new FactEnrollmentDAO();
            GenderDAO chartsGender = new GenderDAO();
            ClassificationDAO chartsClassification = new ClassificationDAO();
            GradeLevelDAO chartsGradeLevel = new GradeLevelDAO();
            
            JSONArray jarrayYears = new JSONArray();
            JSONArray jarrayDistrict = new JSONArray();
            JSONArray jarrayEnrollment = new JSONArray();
            JSONArray jarrayGender = new JSONArray();
            JSONArray jarrayClassification = new JSONArray();
            JSONArray jarrayGradeLevel = new JSONArray();
            JSONArray jarraySchools = new JSONArray();
            JSONObject ObjectAll = new JSONObject();
            
            ArrayList<String> genders = chartsGender.retrieveGenderWithoutBothSexes();
            ArrayList<Integer> censusYears = censusYearDAO.retrieveYears();            
            ArrayList<String> district = chartsDistrict.retrieveDistrictsEnrollment();
            ArrayList<FactEnrollment> enrollment = chartEnrollment.retrieveElementaryEnrollment();
            ArrayList<FactEnrollment> schools = chartEnrollment.retrieveSchools();
            ArrayList<String> classifications = chartsClassification.retrieveClassifications();
            ArrayList<String> gradeLevels = chartsGradeLevel.retrieveElementaryLevels();
            
            for(int i = 0; i < enrollment.size(); i++){
                JSONObject objEnrollment = new JSONObject();
                try {
                    objEnrollment.put("year", enrollment.get(i).getCensusYear());
                    objEnrollment.put("schoolName", enrollment.get(i).getSchoolName());
                    objEnrollment.put("district", enrollment.get(i).getDistrict());
                    objEnrollment.put("type", enrollment.get(i).getType());
                    objEnrollment.put("gradeLevel", enrollment.get(i).getLevel());
                    objEnrollment.put("classification", enrollment.get(i).getClassification());
                    objEnrollment.put("male", enrollment.get(i).getMaleCount());
                    objEnrollment.put("female", enrollment.get(i).getFemaleCount());
                    objEnrollment.put("schoolName", enrollment.get(i).getSchoolName());
                    objEnrollment.put("zone", enrollment.get(i).getZone());
                    jarrayEnrollment.put(objEnrollment);
                } catch (JSONException ex) {
                    getLogger(SetAnalysisDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            for(int i = 0; i < censusYears.size(); i++){
                JSONObject objYears = new JSONObject();
                try {
                    objYears.put("year", censusYears.get(i));
                    jarrayYears.put(objYears);
                } catch (JSONException ex) {
                    getLogger(SetAnalysisDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            for(int i = 0; i < district.size(); i++){
                JSONObject objBgy = new JSONObject();
                try {
                    objBgy.put("district", district.get(i));
                    jarrayDistrict.put(objBgy);
                } catch (JSONException ex) {
                    getLogger(SetAnalysisDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            for(int i = 0; i < classifications.size(); i++){
                JSONObject objClassifications = new JSONObject();
                try {
                    objClassifications.put("classification", classifications.get(i));
                    jarrayClassification.put(objClassifications);
                } catch (JSONException ex) {
                    getLogger(SetAnalysisDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            for(int i = 0; i < genders.size(); i++){
                JSONObject objGender = new JSONObject();
                try {
                    objGender.put("gender", genders.get(i));
                    jarrayGender.put(objGender);
                } catch (JSONException ex) {
                    getLogger(SetAnalysisDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            JSONObject objGender = new JSONObject();
            objGender.put("gender", "Both Sexes");
            jarrayGender.put(objGender);
            
            for(int i = 0; i < gradeLevels.size(); i++){
                JSONObject objGradeLevel = new JSONObject();
                try {
                    objGradeLevel.put("gradeLevel", gradeLevels.get(i));
                    jarrayGradeLevel.put(objGradeLevel);
                } catch (JSONException ex) {
                    getLogger(SetAnalysisDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            for(int i = 0; i < schools.size(); i++){
                JSONObject objSchools = new JSONObject();
                try {
                    objSchools.put("schoolName", schools.get(i).getSchoolName());
                    objSchools.put("district", schools.get(i).getDistrict());
                    objSchools.put("classification", schools.get(i).getClassification());
                    jarraySchools.put(objSchools);
                } catch (JSONException ex) {
                    getLogger(SetAnalysisDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            ObjectAll.put("years", jarrayYears);
            ObjectAll.put("genders", jarrayGender);
            ObjectAll.put("schools", jarraySchools);
            ObjectAll.put("people", jarrayEnrollment);
            ObjectAll.put("districts", jarrayDistrict);
            ObjectAll.put("gradeLevels", jarrayGradeLevel);
            ObjectAll.put("classifications", jarrayClassification);
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
            Logger.getLogger(ElementaryEnrollmentServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ElementaryEnrollmentServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ElementaryEnrollmentServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ElementaryEnrollmentServlet.class.getName()).log(Level.SEVERE, null, ex);
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
