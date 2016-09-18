/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.health.servlet;

import dao.RecordDAO;
import dao.health.directoryHospitalDAO;
import model.health.DirectoryHealth;
import servlets.servlet.BaseServlet;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Atayan
 * @author Roxas
 * @author Sy
 *
 */
public class UpdateHealthDirectory extends BaseServlet {

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
        String redirect = request.getParameter("redirect");
        int year = Calendar.getInstance().get(Calendar.YEAR);
        // ServletContext context= getServletContext();
        RequestDispatcher rd = null;
        directoryHospitalDAO directoryHospitalDAO = new directoryHospitalDAO();

        if (redirect.equalsIgnoreCase("invalid")) {
            String schoolName = request.getParameter("schoolName");
            boolean x = false;
            try {
                x = directoryHospitalDAO.setDirectoryInactive(schoolName, year);
            } catch (SQLException ex) {
                Logger.getLogger(UpdateHealthDirectory.class.getName()).log(Level.SEVERE, null, ex);
            }
            String json = new Gson().toJson(x);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(json);
        } else if (redirect.equalsIgnoreCase("submitAll")) {
            String uploadedBy = request.getParameter("uploadedBy");
            boolean x = false;

            try {
                x = directoryHospitalDAO.SubmitAllHealth(year, Integer.parseInt(uploadedBy));
            } catch (SQLException ex) {
                Logger.getLogger(UpdateHealthDirectory.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (x) {
                request.setAttribute("page", "Upload");
                request.setAttribute("saveToDB", "SuccessDB");
                rd = request.getRequestDispatcher("/RetrieveDataHealthServlet?redirect=directoryHosptial");
                rd.forward(request, response);

            } else {
                request.setAttribute("page", "Upload");
                request.setAttribute("saveToDB", "notSuccess");
                rd = request.getRequestDispatcher("/RetrieveDataHealthServlet?redirect=directoryHosptial");
                rd.forward(request, response);

            }

        } else if (redirect.equalsIgnoreCase("addNew")) {

            String classification = request.getParameter("classification");
            String hospitalName = request.getParameter("hospitalName");
            String totalDoctors = request.getParameter("doctors");
            String totalNurses = request.getParameter("nurses");
            String midwives = request.getParameter("midwives");
            String numberofBeds = request.getParameter("beds");
            String address = request.getParameter("address");
            String tel = request.getParameter("tel");
            String category = request.getParameter("category");
            String accreditation = request.getParameter("accredited");
            String lat = request.getParameter("lat");
            String longi = request.getParameter("long");
            String uploadedBy = request.getParameter("uploadedBy");
            boolean x = false;
            int formID = 0;
            try {
                formID = directoryHospitalDAO.getFormID(year);
            } catch (SQLException ex) {
                Logger.getLogger(UpdateHealthDirectory.class.getName()).log(Level.SEVERE, null, ex);
            }
            RecordDAO recorddao = new RecordDAO();

            try {
                x = recorddao.checkExistRecord(90000000, year);
            } catch (SQLException ex) {
                Logger.getLogger(UpdateHealthDirectory.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (x == false) {
                x = recorddao.newRecord(90000000 + year, year, Integer.parseInt(uploadedBy));
            }

            DirectoryHealth directoryHealth = new DirectoryHealth();
            directoryHealth.setFormID(formID);
            directoryHealth.setClassification(classification);
            directoryHealth.setHospitalName(hospitalName);
            directoryHealth.setYear(year);
            directoryHealth.setNumberOfDoctor(Integer.parseInt(totalDoctors));
            directoryHealth.setNumberOfNurses(Integer.parseInt(totalNurses));
            directoryHealth.setNumberOfMidwives(Integer.parseInt(midwives));
            directoryHealth.setNumberOfBeds(Integer.parseInt(numberofBeds));
            directoryHealth.setAddresss(address);
            directoryHealth.setTelephone(tel);
            directoryHealth.setCategory(category);
            directoryHealth.setAccreditation(Boolean.parseBoolean(accreditation));
            directoryHealth.setLatitude(Double.parseDouble(lat));
            directoryHealth.setLongitude(Double.parseDouble(longi));

            if (x) {
                x = directoryHospitalDAO.addNewHospital(directoryHealth);
            }
            if (x) {
                request.setAttribute("page", "add");
                request.setAttribute("saveToDB", "SuccessDB");
                rd = request.getRequestDispatcher("/RetrieveDataHealthServlet?redirect=directoryHosptial");
                rd.forward(request, response);

            } else {
                request.setAttribute("page", "add");
                request.setAttribute("saveToDB", "notSuccess");
                rd = request.getRequestDispatcher("/RetrieveDataHealthServlet?redirect=directoryHosptial");
                rd.forward(request, response);

            }

        } else if (redirect.equalsIgnoreCase("updateData")) {

            String hospitalName = request.getParameter("hospitalName");
            String totalDoctors = request.getParameter("doctors");
            String totalNurses = request.getParameter("nurses");
            String midwives = request.getParameter("midwives");
            String numberofBeds = request.getParameter("beds");
            String tel = request.getParameter("tel");
            String category = request.getParameter("category");
            String accreditation = request.getParameter("accredited");
            String classification = request.getParameter("classification");
            String oldyear = request.getParameter("censusYear");

            boolean x = false;
            RecordDAO recorddao = new RecordDAO();

            try {
                x = recorddao.checkExistRecord(90000000, year);
            } catch (SQLException ex) {
                Logger.getLogger(UpdateHealthDirectory.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (x == false) {

                String uploadedBy = request.getParameter("uploadedBy");
                x = recorddao.newRecord(90000000 + year, year, Integer.parseInt(uploadedBy));
            }

            if (x) {

                try {
                    if (year != Integer.parseInt(oldyear)) {
                        x = directoryHospitalDAO.UpdateDirectory(hospitalName, Integer.parseInt(oldyear),
                                Integer.parseInt(totalDoctors), Integer.parseInt(totalNurses),
                                Integer.parseInt("0"), Integer.parseInt("0"), accreditation,
                                category, tel, classification);
                    } else {
                        x = directoryHospitalDAO.UpdateDirectory(hospitalName, Integer.parseInt(oldyear),
                                Integer.parseInt(totalDoctors), Integer.parseInt(totalNurses),
                                Integer.parseInt("0"), Integer.parseInt("0"), accreditation,
                                category, tel, classification);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(UpdateHealthDirectory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (x) {
                request.setAttribute("page", "update");
                request.setAttribute("saveToDB", "SuccessDB");
                rd = request.getRequestDispatcher("/RetrieveDataHealthServlet?redirect=directoryHosptial");
                rd.forward(request, response);

            } else {
                request.setAttribute("page", "update");
                request.setAttribute("saveToDB", "notSuccess");
                rd = request.getRequestDispatcher("/RetrieveDataHealthServlet?redirect=directoryHosptial");
                rd.forward(request, response);

            }

        }

    }
}
