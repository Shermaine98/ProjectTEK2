/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package servlet.setdata;

import dao.analysis.BarangayDAO;
import dao.analysis.CensusYearDAO;
import dao.analysis.DistrictDAO;
import dao.analysis.FactPeopleDAO;
import dao.analysis.GenderDAO;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import model.analysis.FactPeople;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class SetHHPopAgeGroupSex extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
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
            CensusYearDAO censusYearDAO = new CensusYearDAO();
            DistrictDAO chartsDistrict = new DistrictDAO();
            FactPeopleDAO chartPeople = new FactPeopleDAO();
            BarangayDAO chartsBarangay = new BarangayDAO();
            GenderDAO chartsGender = new GenderDAO();
            
            JSONArray jarrayYears = new JSONArray();
            JSONArray jarrayDistrict = new JSONArray();
            JSONArray jarrayPeople = new JSONArray();
            JSONArray jarrayBarangays = new JSONArray();
            JSONArray jarrayGender = new JSONArray();
            JSONArray jarrayAgeGroup = new JSONArray();
            JSONObject ObjectAll = new JSONObject();

            ArrayList<Integer> barangays = chartsBarangay.retrieveBarangays();
            ArrayList<String> genders = chartsGender.retrieveGenderWithoutBothSexes();
            ArrayList<Integer> censusYears = censusYearDAO.retrieveYears();            
            ArrayList<String> district = chartsDistrict.retrieveDistricts();
            ArrayList<FactPeople> people = chartPeople.retrieveHHPopByAgeGroupSex();
            ArrayList<String> ageGroup = chartPeople.retrieveAgeGroups();
            FactPeople factPeople = new FactPeople();
            
            factPeople.outliersHHPopSexAgeGroup(censusYears, people);
            
            for(int i = 0; i < people.size(); i++){
                JSONObject objPeople = new JSONObject();
                try {
                    objPeople.put("year", people.get(i).getCensusYear());
                    objPeople.put("district", people.get(i).getDistrict());
                    objPeople.put("people", people.get(i).getTotalNoOfPeople());
                    objPeople.put("zone", people.get(i).getZone());
                    objPeople.put("barangay", people.get(i).getBarangay());
                    objPeople.put("gender", people.get(i).getGender());
                    objPeople.put("ageGroup", people.get(i).getAgeBracket());
                    objPeople.put("isOutlier", people.get(i).getIsOutlier());
                    jarrayPeople.put(objPeople);
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
            
            for(int i = 0; i < genders.size(); i++){
                JSONObject objGender = new JSONObject();
                try {
                    objGender.put("gender", genders.get(i));
                    jarrayGender.put(objGender);
                } catch (JSONException ex) {
                    getLogger(SetAnalysisDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            for(int i = 0; i < ageGroup.size(); i++){
                JSONObject objAgeGroup = new JSONObject();
                try {
                    objAgeGroup.put("ageGroup", ageGroup.get(i));
                    jarrayAgeGroup.put(objAgeGroup);
                } catch (JSONException ex) {
                    getLogger(SetAnalysisDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            ObjectAll.put("years", jarrayYears);
            ObjectAll.put("people", jarrayPeople);
            ObjectAll.put("barangays", jarrayBarangays);
            ObjectAll.put("genders", jarrayGender);
            ObjectAll.put("districts", jarrayDistrict);
            ObjectAll.put("ageGroups", jarrayAgeGroup);
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
            Logger.getLogger(SetHHPopAgeGroupSex.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SetHHPopAgeGroupSex.class.getName()).log(Level.SEVERE, null, ex);
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
