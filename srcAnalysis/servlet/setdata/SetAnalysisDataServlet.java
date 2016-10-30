/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */


package servlet.setdata;

import dao.analysis.BarangayDAO;
import dao.analysis.CensusYearDAO;
import dao.analysis.DistrictDAO;
import dao.analysis.FactStudentNutritionDAO;
import dao.analysis.FactEnrollmentDAO;
import dao.analysis.FactHospitalDAO;
import dao.analysis.FactPeopleDAO;
import model.analysis.FactPeople;
import model.analysis.FactStudentNutrition;
import model.analysis.FactEnrollment;
import model.analysis.FactHospital;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class SetAnalysisDataServlet extends HttpServlet {

     /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException parse exception
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            JSONArray jarrayNoOfSchoolAgeGoingPopulation = new JSONArray();
            JSONArray jarrayEnrollmentForSchoolAgeGoingPopulation = new JSONArray();
            JSONArray jarrayYears = new JSONArray();
            JSONArray jarrayBarangays = new JSONArray();
            JSONArray jarrayHospitals = new JSONArray();
            JSONArray jarrayPeople = new JSONArray();
            JSONArray jarrayNutrition = new JSONArray();
            JSONArray jarrayDistrict = new JSONArray();
            JSONObject ObjectAll = new JSONObject();
          
            FactPeopleDAO charts = new FactPeopleDAO();
            CensusYearDAO chartsYears = new CensusYearDAO();
            FactEnrollmentDAO chartsEdu = new FactEnrollmentDAO();
            FactHospitalDAO chartsHealth = new FactHospitalDAO();
            FactStudentNutritionDAO chartsNutritional = new FactStudentNutritionDAO();
            BarangayDAO chartsBarangay = new BarangayDAO();
            DistrictDAO chartsDistrict = new DistrictDAO();
            
            ArrayList<Integer> censusYears = chartsYears.retrieveYears();            
            ArrayList<Integer> barangays = chartsBarangay.retrieveBarangays();
            ArrayList<String> district = chartsDistrict.retrieveDistricts();
            ArrayList<FactPeople> schoolAgePopulation = charts.retrieveSchoolGoingAge();
            FactPeople factPeople = new FactPeople();
            factPeople.outliers(censusYears, schoolAgePopulation);
            
            ArrayList<FactPeople> allPeople = charts.retrieveAllPeople();
            factPeople.outliers(censusYears, allPeople);
            
            ArrayList<FactEnrollment> enrollment = null;
            FactEnrollment factEnrollment = new FactEnrollment();
            
            ArrayList<FactStudentNutrition> nutrition = chartsNutritional.retrieveNutritionalStatus();
            ArrayList<FactHospital> hospitals = null;
            
            
            
            try {
                hospitals = chartsHealth.retrieveHospitalsBedsDocsNurses();
                enrollment = chartsEdu.retrieveEnrollmentForSchoolGoingAge();
            } catch (SQLException ex) {
                Logger.getLogger(SetAnalysisDataServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for(int i = 0; i < schoolAgePopulation.size(); i++){
                JSONObject objPeople = new JSONObject();
                try {
                    objPeople.put("year", schoolAgePopulation.get(i).getCensusYear());
                    objPeople.put("barangay", schoolAgePopulation.get(i).getBarangay());
                    objPeople.put("district", schoolAgePopulation.get(i).getDistrict());
                    objPeople.put("people", schoolAgePopulation.get(i).getTotalNoOfPeople());
                    objPeople.put("isOutlier", schoolAgePopulation.get(i).getIsOutlier()); //schoolAgePopulation.get(i).getIsOutlier()
                    objPeople.put("zone", schoolAgePopulation.get(i).getZone());
                    jarrayNoOfSchoolAgeGoingPopulation.put(objPeople);
                } catch (JSONException ex) {
                    getLogger(SetAnalysisDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            for(int i = 0; i < enrollment.size(); i++){
                JSONObject objEnrollment = new JSONObject();
                try {
                    objEnrollment.put("year", enrollment.get(i).getCensusYear());
                    objEnrollment.put("district", enrollment.get(i).getDistrict());
                    objEnrollment.put("enrollment", enrollment.get(i).getCount());
                    objEnrollment.put("zone", enrollment.get(i).getZone());
                    jarrayEnrollmentForSchoolAgeGoingPopulation.put(objEnrollment);
                } catch (JSONException ex) {
                    getLogger(SetAnalysisDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            for(int i = 0; i < nutrition.size(); i++){
                JSONObject objNutrition = new JSONObject();
                try {
                    objNutrition.put("year", nutrition.get(i).getCensusYear());
                    objNutrition.put("district", nutrition.get(i).getDistrict());
                    objNutrition.put("weighed", nutrition.get(i).getPupilsWeighed());
                    objNutrition.put("normal", nutrition.get(i).getTotalNoOfNormal());
                    objNutrition.put("obese", nutrition.get(i).getTotalNoOfObese());
                    objNutrition.put("overweight", nutrition.get(i).getTotalNoOfOverweight());
                    objNutrition.put("severelyWasted", nutrition.get(i).getTotalNoOfSeverelyWasted());
                    objNutrition.put("wasted", nutrition.get(i).getTotalNoOfWasted());
                    objNutrition.put("zone", nutrition.get(i).getZone());
                    jarrayNutrition.put(objNutrition);
                } catch (JSONException ex) {
                    getLogger(SetAnalysisDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            for(int i = 0; i < allPeople.size(); i++){
                JSONObject objPeople = new JSONObject();
                try {
                    objPeople.put("year", allPeople.get(i).getCensusYear());
                    objPeople.put("barangay", allPeople.get(i).getBarangay());
                    objPeople.put("district", allPeople.get(i).getDistrict());
                    objPeople.put("people", allPeople.get(i).getTotalNoOfPeople());
                    objPeople.put("isOutlier", allPeople.get(i).getIsOutlier()); //allPeople.get(i).getIsOutlier()
                    objPeople.put("zone", allPeople.get(i).getZone());
                    jarrayPeople.put(objPeople);
                } catch (JSONException ex) {
                    getLogger(SetAnalysisDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            for(int i = 0; i < hospitals.size(); i++){
                JSONObject objHospitals = new JSONObject();
                try {
                    objHospitals.put("year", hospitals.get(i).getCensusYear());
                    objHospitals.put("district", hospitals.get(i).getDistrict());
                    objHospitals.put("zone", hospitals.get(i).getZone());
                    objHospitals.put("nurses", hospitals.get(i).getTotalNoOfNurses());
                    objHospitals.put("doctors", hospitals.get(i).getTotalNoOfDoctors());
                    objHospitals.put("beds", hospitals.get(i).getTotalNoOfBeds());
                    jarrayHospitals.put(objHospitals);
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
            
            for(int i = 0; i < barangays.size(); i++){
                JSONObject objBgy = new JSONObject();
                try {
                    objBgy.put("barangay", barangays.get(i));
                    jarrayBarangays.put(objBgy);
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
            
            ObjectAll.put("schoolAge", jarrayNoOfSchoolAgeGoingPopulation);
            ObjectAll.put("enrollmentSchoolAge", jarrayEnrollmentForSchoolAgeGoingPopulation);
            ObjectAll.put("years", jarrayYears);
            ObjectAll.put("barangays", jarrayBarangays);
            ObjectAll.put("districts", jarrayDistrict);
            ObjectAll.put("hospitals", jarrayHospitals);
            ObjectAll.put("people", jarrayPeople);
            ObjectAll.put("nutrition", jarrayNutrition);
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
            getLogger(SetAnalysisDataServlet.class.getName()).log(SEVERE, null, ex);
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
            getLogger(SetAnalysisDataServlet.class.getName()).log(SEVERE, null, ex);
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
