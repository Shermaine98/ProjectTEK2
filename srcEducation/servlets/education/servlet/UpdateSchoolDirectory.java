/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package servlets.education.servlet;

import dao.education.DirectorySchoolDAO;
import servlets.servlet.BaseServlet;
import com.google.gson.Gson;
import dao.RecordDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.education.DirectorySchool;
import model.education.ElemClassrooms;
import model.education.ElemTeachers;
import model.education.Seats;
import servlets.health.servlet.UpdateHealthDirectory;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 *
 */
public class UpdateSchoolDirectory extends BaseServlet {

    /**
     *
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String redirect = request.getParameter("redirect");
        String classification = request.getParameter("classification");
        // ServletContext context= getServletContext();
        DirectorySchoolDAO directorySchoolDAO = new DirectorySchoolDAO();

        if (redirect.equalsIgnoreCase("invalid")) {
            try {
                String schoolName = request.getParameter("schoolName");
                String censusYear = request.getParameter("censusYear");

                boolean x = directorySchoolDAO.setDirectoryInactive(schoolName.trim(), classification, Integer.parseInt(censusYear));

                String json = new Gson().toJson(x);
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(json);
            } catch (SQLException ex) {
                Logger.getLogger(UpdateSchoolDirectory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (redirect.equalsIgnoreCase("submitAll")) {
            boolean x;
            int year = Calendar.getInstance().get(Calendar.YEAR);
            String uploadedBy = request.getParameter("uploadedBy");

            if (classification.equalsIgnoreCase("private")) {
                x = directorySchoolDAO.SubtmitAllPrivate(year, Integer.parseInt(uploadedBy));
            } else {
                x = directorySchoolDAO.SubtmitAllPublic(year, Integer.parseInt(uploadedBy));
            }

            if (x) {
                request.setAttribute("page", "Upload");
                request.setAttribute("saveToDB", "SuccessDB");
                if (classification.equalsIgnoreCase("private")) {
                    RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataEducationServlet?redirect=privateDirectory");
                    rd.forward(request, response);
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataEducationServlet?redirect=publicDirectory");
                    rd.forward(request, response);
                }

            } else {
                request.setAttribute("page", "Upload");
                request.setAttribute("saveToDB", "notSuccess");
                if (classification.equalsIgnoreCase("private")) {
                    RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataEducationServlet?redirect=privateDirectory");
                    rd.forward(request, response);
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataEducationServlet?redirect=publicDirectory");
                    rd.forward(request, response);
                }

            }

        } else if (redirect.equalsIgnoreCase("updateData")) {

            int year = Calendar.getInstance().get(Calendar.YEAR);
            String oldYear = request.getParameter("cencusYear");

            String schoolName = request.getParameter("schoolName");
            // String address = request.getParameter("Address");
            //String censusYear = request.getParameter("censusYear");
            String schoolID = request.getParameter("schoolID");

            String KteacherMale = request.getParameter("KteacherMale");
            String KteacherFemale = request.getParameter("KteacherFemale");
            //  String KteacherTotal = request.getParameter("KteacherTotal");

            String EteacherMale = request.getParameter("EteacherMale");
            String EteacherFemale = request.getParameter("EteacherFemale");
            //  String EteacherTotal = request.getParameter("EteacherTotal");

            String KinderClassRoom = request.getParameter("KinderClassRoom");
            String ElemClassRoom = request.getParameter("ElemClassRoom");
            //  String ElemTotal = request.getParameter("TotalClassrooms");

            String KinderSeats = request.getParameter("KinderSeats");
            String ElemSeats = request.getParameter("ElemSeats");
            //  String TotalSeats = request.getParameter("TotalSeats");

            // String lat = request.getParameter("lat");
            // String longi = request.getParameter("long");
            ///  String classification = request.getParameter("classification");
            boolean x = false;
            RecordDAO recorddao = new RecordDAO();
            int formid = 0;
            try {
                formid = directorySchoolDAO.getFormID(year, classification);
            } catch (SQLException ex) {
                Logger.getLogger(AddNewSchool.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (classification.equalsIgnoreCase("private")) {
                try {
                    x = recorddao.checkExistRecord(120000000, year);
                } catch (SQLException ex) {
                    Logger.getLogger(UpdateHealthDirectory.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (x == false) {

                    String uploadedBy = request.getParameter("uploadedBy");
                    x = recorddao.newRecord(120000000 + year, year, Integer.parseInt(uploadedBy));
                }

            } else {
                try {
                    x = recorddao.checkExistRecord(190000000, year);
                } catch (SQLException ex) {
                    Logger.getLogger(UpdateHealthDirectory.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (x == false) {

                    String uploadedBy = request.getParameter("uploadedBy");
                    x = recorddao.newRecord(190000000 + year, year, Integer.parseInt(uploadedBy));
                }

            }
            DirectorySchool directorySchool = new DirectorySchool();

            directorySchool.setFormID(formid);
            directorySchool.setSchoolName(schoolName);
            directorySchool.setSchoolID(Integer.parseInt(schoolID));
            directorySchool.setCensusYear(year);
            directorySchool.setAddress("");
            directorySchool.setLatitude(0);
            directorySchool.setLongitude(0);
            directorySchool.setClassification(classification);

            ArrayList<ElemClassrooms> arrelemClassrooms = new ArrayList<ElemClassrooms>();

            ElemClassrooms elemClassrooms = new ElemClassrooms();
            elemClassrooms.setGradeLevel("Kinder");
            elemClassrooms.setClassroomCount(Integer.parseInt(KinderClassRoom));
            arrelemClassrooms.add(elemClassrooms);

            ElemClassrooms elemClassrooms2 = new ElemClassrooms();
            elemClassrooms2.setGradeLevel("Elementary");
            elemClassrooms2.setClassroomCount(Integer.parseInt(ElemClassRoom));
            arrelemClassrooms.add(elemClassrooms2);

            ArrayList<ElemTeachers> arrTeachers = new ArrayList<ElemTeachers>();
            ElemTeachers elemTeachers = new ElemTeachers();
            elemTeachers.setGradeLevel("Kinder");
            elemTeachers.setFemaleCount(Integer.parseInt(KteacherFemale));
            elemTeachers.setMaleCount(Integer.parseInt(KteacherMale));
            arrTeachers.add(elemTeachers);

            ElemTeachers elemTeachers2 = new ElemTeachers();
            elemTeachers2.setGradeLevel("Elementary");
            elemTeachers2.setFemaleCount(Integer.parseInt(EteacherFemale));
            elemTeachers2.setMaleCount(Integer.parseInt(EteacherMale));
            arrTeachers.add(elemTeachers2);

            ArrayList<Seats> arrseats = new ArrayList<Seats>();
            Seats seats = new Seats();
            seats.setGradeLevel("Kinder");
            seats.setSeatCount(Integer.parseInt(KinderSeats));
            arrseats.add(seats);

            Seats seats2 = new Seats();
            seats2.setGradeLevel("Elementary");
            seats2.setSeatCount(Integer.parseInt(ElemSeats));
            arrseats.add(seats2);

            directorySchool.setElemClassrooms(arrelemClassrooms);
            directorySchool.setSeats(arrseats);
            directorySchool.setTeacher(arrTeachers);

            if (x) {

                try {
                    if (year != Integer.parseInt(oldYear)) {
                        x = directorySchoolDAO.UpdateDirectory(directorySchool);
                    } else {
                        x = directorySchoolDAO.UpdateDirectory(directorySchool);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(UpdateHealthDirectory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            x = directorySchoolDAO.AddNewEncodeDirectorySchool(directorySchool);

        }

    }
}
