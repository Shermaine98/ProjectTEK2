/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package servlets.education.servlet;

import dao.RecordDAO;
import dao.education.DirectorySchoolDAO;
import model.education.DirectorySchool;
import model.education.ElemClassrooms;
import model.education.ElemTeachers;
import model.education.Seats;
import servlets.servlet.BaseServlet;
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
import javax.servlet.http.HttpSession;
import model.accounts.User;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 *
 */
public class AddNewSchool extends BaseServlet {

    /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException servlet exception
     * @throws IOException servlet IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // ServletContext context= getServletContext();
        RequestDispatcher rd = null;
        DirectorySchoolDAO directorySchoolDAO = new DirectorySchoolDAO();

        int year = Calendar.getInstance().get(Calendar.YEAR);

        String schoolName = request.getParameter("schoolName");
        String address = request.getParameter("Address");
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
        String classification = request.getParameter("classification");

        boolean x = false;

        DirectorySchool directorySchool = new DirectorySchool();
        int formid = 0;
        try {
            formid = directorySchoolDAO.getFormID(year, classification);
        } catch (SQLException ex) {
            Logger.getLogger(AddNewSchool.class.getName()).log(Level.SEVERE, null, ex);
        }

        RecordDAO recorddao = new RecordDAO();
        if (classification.equalsIgnoreCase("private")) {
            try {
                x = recorddao.checkExistRecord(120000000, year);
            } catch (SQLException ex) {
                Logger.getLogger(AddNewSchool.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (x == false) {
                HttpSession session = request.getSession();
                User chck = (User) session.getAttribute("user");
                x = recorddao.newRecord(120000000 + year, year, chck.getUserID());
            }
        } else if (classification.equalsIgnoreCase("public")) {

            try {
                x = recorddao.checkExistRecord(190000000, year);
            } catch (SQLException ex) {
                Logger.getLogger(AddNewSchool.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (x == false) {
                HttpSession session = request.getSession();
                User chck = (User) session.getAttribute("user");
                x = recorddao.newRecord(190000000 + year, year, chck.getUserID());
            }
        }

        directorySchool.setFormID(formid);
        directorySchool.setSchoolName(schoolName);
        directorySchool.setSchoolID(Integer.parseInt(schoolID));
        directorySchool.setCensusYear(year);
        directorySchool.setAddress(address);
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

        x = directorySchoolDAO.AddNewEncodeDirectorySchool(directorySchool);

        if (classification.equalsIgnoreCase("private")) {
            if (x) {
                request.setAttribute("page", "add");
                request.setAttribute("saveToDB", "SuccessDB");
                rd = request.getRequestDispatcher("/RetrieveDataEducationServlet?redirect=privateDirectory");
                rd.forward(request, response);

            } else {
                request.setAttribute("page", "add");
                request.setAttribute("saveToDB", "notSuccess");
                rd = request.getRequestDispatcher("/RetrieveDataEducationServlet?redirect=privateDirectory");
                rd.forward(request, response);

            }
        } else if (classification.equalsIgnoreCase("public")) {

            if (x) {
                request.setAttribute("page", "add");
                request.setAttribute("saveToDB", "SuccessDB");
                rd = request.getRequestDispatcher("/RetrieveDataEducationServlet?redirect=publicDirectory");
                rd.forward(request, response);
            } else {
                request.setAttribute("page", "add");
                request.setAttribute("saveToDB", "notSuccess");
                rd = request.getRequestDispatcher("/RetrieveDataEducationServlet?redirect=publicDirectory");
                rd.forward(request, response);

            }
        }
    }
}
