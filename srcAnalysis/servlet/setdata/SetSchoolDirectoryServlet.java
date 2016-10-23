/*
 *  Copyright (C) ProjectTEK - DLSU CCS 2016
 *  All right Reserved   * 
 */

package servlet.setdata;

import dao.RecordDAO;
import dao.charts.SchoolDirectory;
import model.Record;
import model.education.DirectorySchool;
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
public class SetSchoolDirectoryServlet extends HttpServlet {

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
            
            JSONArray jarrayTable = new JSONArray();
            JSONObject ObjectAll = new JSONObject();
          
            int censusYear = parseInt(tempCensusYear);
            ArrayList<DirectorySchool> schools = new ArrayList<DirectorySchool>();
            SchoolDirectory schoolDirectory = new SchoolDirectory();
                        
            schools = schoolDirectory.retrieveDirectorySchool(censusYear, classification);

            Record records = new RecordDAO().GetbyFormID(censusYear);
            
            JSONObject objSchoolTotal = new JSONObject();
            try {
                objSchoolTotal.put("totalName", classification + " Schools");
                objSchoolTotal.put("totalClassrooms", schoolDirectory.retrieveTotalClassrooms(censusYear, classification));
                objSchoolTotal.put("totalSeats", schoolDirectory.retrieveTotalSeats(censusYear, classification));
                objSchoolTotal.put("totalTeachers", schoolDirectory.retrieveTotalTeachers(censusYear, classification));
            } catch (JSONException ex) {
                getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
            }
            
            
            for(int i = 0; i < schools.size(); i++){
                JSONObject objArrSchoolDirectory = new JSONObject();
                try {
                    objArrSchoolDirectory.put("schoolDirectoryName", schools.get(i).getSchoolName());
                    objArrSchoolDirectory.put("schoolDirectoryID", schools.get(i).getSchoolID());
                    objArrSchoolDirectory.put("schoolDirectoryElemClassroom", schools.get(i).getElemClassrooms().get(0).getClassroomCount());
                    objArrSchoolDirectory.put("schoolDirectoryKinderClassroom", schools.get(i).getElemClassrooms().get(1).getClassroomCount());
                    objArrSchoolDirectory.put("schoolDirectoryElemTeachers", (schools.get(i).getTeacher().get(0).getFemaleCount() + schools.get(i).getTeacher().get(0).getMaleCount()));
                    objArrSchoolDirectory.put("schoolDirectoryElemTeachersMale", (schools.get(i).getTeacher().get(0).getMaleCount()));
                    objArrSchoolDirectory.put("schoolDirectoryElemTeachersFemale", (schools.get(i).getTeacher().get(0).getFemaleCount()));
                    objArrSchoolDirectory.put("schoolDirectoryKinderTeachers", (schools.get(i).getTeacher().get(1).getFemaleCount() + schools.get(i).getTeacher().get(1).getMaleCount()));
                    objArrSchoolDirectory.put("schoolDirectoryKinderTeachersMale", (schools.get(i).getTeacher().get(1).getMaleCount()));
                    objArrSchoolDirectory.put("schoolDirectoryKinderTeachersFemale", (schools.get(i).getTeacher().get(1).getFemaleCount()));
                    objArrSchoolDirectory.put("schoolDirectoryElemSeats", schools.get(i).getSeats().get(0).getSeatCount());
                    objArrSchoolDirectory.put("schoolDirectoryKinderSeats", schools.get(i).getSeats().get(1).getSeatCount());
                    jarrayTable.put(objArrSchoolDirectory);
                } catch (JSONException ex) {
                    getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }

            ObjectAll.put("total", objSchoolTotal);
            ObjectAll.put("det", jarrayTable);
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
