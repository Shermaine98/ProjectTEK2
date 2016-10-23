/*
 *  Copyright (C) ProjectTEK - DLSU CCS 2016
 *  All right Reserved   * 
 */

package servlets.education.servlet;

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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // ServletContext context= getServletContext();
        RequestDispatcher rd = null;
        DirectorySchoolDAO directorySchoolDAO = new DirectorySchoolDAO();

        
        
        
        
        
        
        String schoolName = request.getParameter("schoolName");
        String address = request.getParameter("Address");
        String censusYear = request.getParameter("censusYear");
        String schoolID = request.getParameter("schoolID");

        String KteacherMale = request.getParameter("KteacherMale");
        String KteacherFemale = request.getParameter("KteacherFemale");
        String KteacherTotal = request.getParameter("KteacherTotal");

        String EteacherMale = request.getParameter("EteacherMale");
        String EteacherFemale = request.getParameter("EteacherFemale");
        String EteacherTotal = request.getParameter("EteacherTotal");

        String KinderClassRoom = request.getParameter("KinderClassRoom");
        String ElemClassRoom = request.getParameter("ElemClassRoom");
        String ElemTotal = request.getParameter("TotalClassrooms");

        String KinderSeats = request.getParameter("KinderSeats");
        String ElemSeats = request.getParameter("ElemSeats");
        String TotalSeats = request.getParameter("TotalSeats");

        String lat = request.getParameter("lat");
        String longi = request.getParameter("long");
        String classification = request.getParameter("classification");

        boolean x = false;

        DirectorySchool directorySchool = new DirectorySchool();
        int formid = 0;
        try {
            formid = directorySchoolDAO.getFormID(Integer.parseInt(censusYear), classification);
        } catch (SQLException ex) {
            Logger.getLogger(AddNewSchool.class.getName()).log(Level.SEVERE, null, ex);
        }

        directorySchool.setFormID(formid);
        directorySchool.setSchoolName(censusYear);
        directorySchool.setSchoolID(Integer.parseInt(schoolID));
        directorySchool.setSchoolName(schoolName);
        directorySchool.setAddress(address);
        directorySchool.setLatitude(Double.parseDouble(lat));
        directorySchool.setLongitude(Double.parseDouble(longi));
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
        elemClassrooms.setGradeLevel("Elementary");
        elemTeachers.setFemaleCount(Integer.parseInt(EteacherFemale));
        elemTeachers.setMaleCount(Integer.parseInt(EteacherMale));
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

        directorySchoolDAO.EncodeDirectorySchool(directorySchool);

        if (x) {
            if (x) {

            }
        } else {
            rd = request.getRequestDispatcher("/ErrorHandler");
            request.setAttribute("page", "enrollment");
            request.setAttribute("action", "upload");
            rd.forward(request, response);
        }
    }
}
