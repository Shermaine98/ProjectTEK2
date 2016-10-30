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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import model.analysis.FactEnrollment;
import model.analysis.FactSchoolTeachersFacilities;
import model.analysis.Gaps;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class SetClassroomRequirementServlet extends HttpServlet {

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
            JSONArray jarrayClassrooms = new JSONArray();
            JSONArray jarrayGaps = new JSONArray();
            JSONObject ObjectAll = new JSONObject();
            
            CensusYearDAO chartsYears = new CensusYearDAO();
            DistrictDAO chartsDistrict = new DistrictDAO();
            FactEnrollmentDAO chartsEdu = new FactEnrollmentDAO();
            FactSchoolTeachersFacilitiesDAO chartsClassrooms = new FactSchoolTeachersFacilitiesDAO();
            
            ArrayList<Integer> censusYears = chartsYears.retrieveYears();            
            ArrayList<String> district = chartsDistrict.retrieveDistricts();
            ArrayList<FactEnrollment> enrollment = null;
            ArrayList<FactSchoolTeachersFacilities> classrooms = null;
            try {
                enrollment = chartsEdu.retrieveEnrollmentForSchoolGoingAge();
                classrooms = chartsClassrooms.retrieveNumberOfClassrooms();
            } catch (SQLException ex) {
                Logger.getLogger(SetClassroomRequirementServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("HI");
            ArrayList<Gaps> gaps = null;
            gaps = calculateGaps(classrooms, enrollment);
            
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
            
            for(int i = 0; i < classrooms.size(); i++){
                JSONObject objClassrooms = new JSONObject();
                try {
                    objClassrooms.put("year", classrooms.get(i).getCensusYear());
                    objClassrooms.put("district", classrooms.get(i).getDistrict());
                    objClassrooms.put("classrooms", classrooms.get(i).getNoOfClassrooms());
                    objClassrooms.put("zone", classrooms.get(i).getZone());
                    jarrayClassrooms.put(objClassrooms);
                } catch (JSONException ex) {
                    getLogger(SetClassroomRequirementServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            for(int i = 0; i < gaps.size(); i++){
                JSONObject objGaps = new JSONObject();
                try {
                    objGaps.put("year", gaps.get(i).getCensusYear());
                    objGaps.put("district", gaps.get(i).getDistrict());
                    objGaps.put("gap", gaps.get(i).getGap());
                    objGaps.put("zone", gaps.get(i).getZone());
                    jarrayGaps.put(objGaps);
                } catch (JSONException ex) {
                    getLogger(SetClassroomRequirementServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            ObjectAll.put("years", jarrayYears);
            ObjectAll.put("districts", jarrayDistrict);
            ObjectAll.put("enrollment", jarrayEnrollmentForSchoolAgeGoingPopulation);
            ObjectAll.put("classrooms", jarrayClassrooms);
            ObjectAll.put("gaps", jarrayGaps);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write("[" + ObjectAll.toString() + "]");

        }
    }
    
    public ArrayList<Gaps> calculateGaps(ArrayList<FactSchoolTeachersFacilities> classrooms, ArrayList<FactEnrollment> enrollment){
        ArrayList<Gaps> gaps = new ArrayList<Gaps>();
        System.out.println("s");
        
        for(int i = 0; i < classrooms.size(); i++){
            for(int x = 0; x < enrollment.size(); x++){
                System.out.println(classrooms.get(i).getDistrict() + " " + enrollment.get(x).getDistrict());
                if(classrooms.get(i).getCensusYear() == enrollment.get(x).getCensusYear()
                        && classrooms.get(i).getDistrict().equalsIgnoreCase(enrollment.get(x).getDistrict())){
                    Gaps temp = new Gaps();
                    temp.setCensusYear(classrooms.get(i).getCensusYear());
                    temp.setDistrict(classrooms.get(i).getDistrict());
                    temp.setZone(classrooms.get(i).getZone());
                    System.out.print("CensusYear" + temp.getCensusYear()+" ");
                    System.out.print("District" + temp.getDistrict()+" ");
                    System.out.print("Zone" + temp.getZone()+" ");
                    System.out.print(classrooms.get(i).getNoOfClassrooms() + "x45 " + " - " + (enrollment.get(x).getFemaleCount()+enrollment.get(x).getMaleCount()));
                    int difference = (enrollment.get(x).getCount()-(classrooms.get(i).getNoOfClassrooms()*45));
                    System.out.print("Difference" + difference+"\n");
                    temp.setGap(difference);
                    gaps.add(temp);
                }
            }
        }
        
        return gaps;
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
            Logger.getLogger(SetClassroomRequirementServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SetClassroomRequirementServlet.class.getName()).log(Level.SEVERE, null, ex);
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
