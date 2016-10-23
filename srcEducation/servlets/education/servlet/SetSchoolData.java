/*
 *  Copyright (C) ProjectTEK - DLSU CCS 2016
 *  All rights Reserved   * 
 */

package servlets.education.servlet;

import dao.education.DirectorySchoolDAO;
import model.education.DirectorySchool;
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
public class SetSchoolData extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String schoolName = request.getParameter("schoolName");
            String classification = request.getParameter("classification");
            ArrayList<DirectorySchool> directorySchool = new DirectorySchoolDAO().ViewDirectorySchoolRecentByName(classification, schoolName);
            JSONObject arrayAll = new JSONObject();
              JSONArray arrTeacher = new JSONArray();
            JSONArray arrSchool = new JSONArray();
            JSONArray arrSeats = new JSONArray();
            JSONArray arrClassrooms = new JSONArray();
            for (int i = 0; i < directorySchool.size(); i++) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("schoolName", directorySchool.get(i).getSchoolName());
                    obj.put("classification", directorySchool.get(i).getClassification());
                    
                    for (int y = 0; y < directorySchool.get(i).getTeacher().size(); y++) {
                        JSONObject teacher = new JSONObject();

                        teacher.put("gradeLevel", directorySchool.get(i).getTeacher().get(y).getGradeLevel());
                        teacher.put("femaleCount", directorySchool.get(i).getTeacher().get(y).getFemaleCount());
                        teacher.put("maleCount", directorySchool.get(i).getTeacher().get(y).getMaleCount());
                        arrTeacher.put(teacher);

                    }
                    for (int y = 0; y < directorySchool.get(i).getElemClassrooms().size(); y++) {
                        JSONObject classroom = new JSONObject();
                        classroom.put("ClassgradeLevel", directorySchool.get(i).getElemClassrooms().get(y).getGradeLevel());
                        classroom.put("roomCount", directorySchool.get(i).getElemClassrooms().get(y).getClassroomCount());
                        arrClassrooms.put(classroom);

                    }

                    for (int y = 0; y < directorySchool.get(i).getSeats().size(); y++) {
                        JSONObject seats = new JSONObject();
                        seats.put("SeatsgradeLevel", directorySchool.get(i).getSeats().get(y).getGradeLevel());
                        seats.put("seatCount", directorySchool.get(i).getSeats().get(y).getSeatCount());
                        arrSeats.put(seats);

                    }
                    arrSchool.put(obj);

                } catch (JSONException ex) {
                    Logger.getLogger(SetSchoolData.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            arrayAll.put("school",arrSchool);
            arrayAll.put("teacher", arrTeacher);
            arrayAll.put("seats", arrSeats);
            arrayAll.put("classrooms", arrClassrooms);

            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write("[" + arrayAll.toString() + "]");

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
            Logger.getLogger(SetSchoolData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SetSchoolData.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SetSchoolData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SetSchoolData.class.getName()).log(Level.SEVERE, null, ex);
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
