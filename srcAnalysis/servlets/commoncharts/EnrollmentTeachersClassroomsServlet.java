/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */


package servlets.commoncharts;

import dao.analysis.CensusYearDAO;
import dao.analysis.DistrictDAO;
import dao.analysis.FactEnrollmentDAO;
import dao.analysis.FactSchoolTeachersFacilitiesDAO;
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
import model.analysis.FactSchoolTeachersFacilities;
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
public class EnrollmentTeachersClassroomsServlet extends HttpServlet {

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
            JSONArray jarrayYears = new JSONArray();
            JSONArray jarrayDistrict = new JSONArray();
            JSONArray jarrayEnrollmentForSchoolAgeGoingPopulation = new JSONArray();
            JSONArray jarrayClassroomsTeachers = new JSONArray();
            JSONObject ObjectAll = new JSONObject();
            
            CensusYearDAO chartsYears = new CensusYearDAO();
            DistrictDAO chartsDistrict = new DistrictDAO();
            FactEnrollmentDAO chartsEdu = new FactEnrollmentDAO();
            FactSchoolTeachersFacilitiesDAO chartsClassrooms = new FactSchoolTeachersFacilitiesDAO();
            
            ArrayList<Integer> censusYears = chartsYears.retrieveYears();            
            ArrayList<String> district = chartsDistrict.retrieveDistricts();
            ArrayList<FactEnrollment> enrollment = null;
            ArrayList<FactSchoolTeachersFacilities> classroomsTeachers = null;
            try {
                enrollment = chartsEdu.retrieveEnrollmentForSchoolGoingAge();
                classroomsTeachers = chartsClassrooms.retrieveNumberOfClassroomsTeachers();
            } catch (SQLException ex) {
                Logger.getLogger(SetClassroomRequirementServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for(int i = 0; i < censusYears.size(); i++){
                JSONObject objYears = new JSONObject();
                try {
                    objYears.put("year", censusYears.get(i));
                    jarrayYears.put(objYears);
                } catch (JSONException ex) {
                    getLogger(SetClassroomRequirementServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            for(int i = 0; i < district.size(); i++){
                JSONObject objBgy = new JSONObject();
                try {
                    objBgy.put("district", district.get(i));
                    jarrayDistrict.put(objBgy);
                } catch (JSONException ex) {
                    getLogger(SetClassroomRequirementServlet.class.getName()).log(SEVERE, null, ex);
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
                    getLogger(SetClassroomRequirementServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            for(int i = 0; i < classroomsTeachers.size(); i++){
                JSONObject objClassrooms = new JSONObject();
                try {
                    objClassrooms.put("year", classroomsTeachers.get(i).getCensusYear());
                    objClassrooms.put("district", classroomsTeachers.get(i).getDistrict());
                    objClassrooms.put("classrooms", classroomsTeachers.get(i).getNoOfClassrooms());
                    objClassrooms.put("teachers", classroomsTeachers.get(i).getNoOfTeachers());
                    objClassrooms.put("zone", classroomsTeachers.get(i).getZone());
                    jarrayClassroomsTeachers.put(objClassrooms);
                } catch (JSONException ex) {
                    getLogger(SetClassroomRequirementServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            ObjectAll.put("years", jarrayYears);
            ObjectAll.put("districts", jarrayDistrict);
            ObjectAll.put("enrollment", jarrayEnrollmentForSchoolAgeGoingPopulation);
            ObjectAll.put("classrooms", jarrayClassroomsTeachers);
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
            Logger.getLogger(EnrollmentTeachersClassroomsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EnrollmentTeachersClassroomsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
