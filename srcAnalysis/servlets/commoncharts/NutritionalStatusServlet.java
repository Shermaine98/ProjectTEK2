/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.commoncharts;

import dao.analysis.BarangayDAO;
import dao.analysis.CensusYearDAO;
import dao.analysis.DistrictDAO;
import dao.analysis.FactPeopleDAO;
import dao.analysis.FactStudentNutritionDAO;
import dao.analysis.GenderDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
import model.analysis.FactPeople;
import model.analysis.FactStudentNutrition;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import servlet.setdata.SetAnalysisDataServlet;

/**
 *
 * @author giancarloroxas
 */
public class NutritionalStatusServlet extends HttpServlet {

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
            CensusYearDAO censusYearDAO = new CensusYearDAO();
            DistrictDAO chartsDistrict = new DistrictDAO();
            FactStudentNutritionDAO chartStudentNutrition = new FactStudentNutritionDAO();
            GenderDAO chartsGender = new GenderDAO();
            
            JSONArray jarrayYears = new JSONArray();
            JSONArray jarrayDistrict = new JSONArray();
            JSONArray jarrayStudentNutrition = new JSONArray();
            JSONArray jarrayGradeLevel = new JSONArray();
            JSONArray jarrayGender = new JSONArray();
            JSONObject ObjectAll = new JSONObject();
            
            ArrayList<String> genders = chartsGender.retrieveGenderWithoutBothSexes();
            ArrayList<Integer> censusYears = censusYearDAO.retrieveYears();            
            ArrayList<String> district = chartsDistrict.retrieveDistrictsForNutritionalStatus();
            ArrayList<FactStudentNutrition> studentNutrition = chartStudentNutrition.retrieveNutritionalStatusCommonChart();
            ArrayList<String> gradeLevels = chartStudentNutrition.retrieveGradeLevels();

            for(int i = 0; i < studentNutrition.size(); i++){
                JSONObject objStudentNutrition = new JSONObject();
                try {
                    objStudentNutrition.put("gender", studentNutrition.get(i).getGender());
                    objStudentNutrition.put("gradeLevel", studentNutrition.get(i).getGradeLevel());
                    objStudentNutrition.put("year", studentNutrition.get(i).getCensusYear());
                    objStudentNutrition.put("district", studentNutrition.get(i).getDistrict());
                    objStudentNutrition.put("zone", studentNutrition.get(i).getZone());
                    objStudentNutrition.put("severelyWasted", studentNutrition.get(i).getTotalNoOfSeverelyWasted());
                    objStudentNutrition.put("wasted", studentNutrition.get(i).getTotalNoOfWasted());
                    objStudentNutrition.put("normal", studentNutrition.get(i).getTotalNoOfNormal());
                    objStudentNutrition.put("overweight", studentNutrition.get(i).getTotalNoOfOverweight());
                    objStudentNutrition.put("obese", studentNutrition.get(i).getTotalNoOfObese());
                    jarrayStudentNutrition.put(objStudentNutrition);
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
            
            for(int i = 0; i < gradeLevels.size(); i++){
                JSONObject objGradeLevel = new JSONObject();
                try {
                    objGradeLevel.put("gradeLevel", gradeLevels.get(i));
                    jarrayGradeLevel.put(objGradeLevel);
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
            
            for(int i = 0; i < genders.size(); i++){
                JSONObject objGender = new JSONObject();
                try {
                    objGender.put("gender", genders.get(i));
                    jarrayGender.put(objGender);
                } catch (JSONException ex) {
                    getLogger(SetAnalysisDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            ObjectAll.put("years", jarrayYears);
            ObjectAll.put("people", jarrayStudentNutrition);
            ObjectAll.put("gradeLevels", jarrayGradeLevel);
            ObjectAll.put("genders", jarrayGender);
            ObjectAll.put("districts", jarrayDistrict);
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
            Logger.getLogger(NutritionalStatusServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(NutritionalStatusServlet.class.getName()).log(Level.SEVERE, null, ex);
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
