/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package servlets.health.servlet;

import dao.RecordDAO;
import dao.health.DirectoryHospitalDAO;
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
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 *
 */
public class UpdateHealthDirectory extends BaseServlet {

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
        String redirect = request.getParameter("redirect");
        int year = Calendar.getInstance().get(Calendar.YEAR);
        // ServletContext context= getServletContext();
        RequestDispatcher rd = null;
        DirectoryHospitalDAO directoryHospitalDAO = new DirectoryHospitalDAO();

        if (redirect.equalsIgnoreCase("invalid")) {
            String schoolName = request.getParameter("schoolName");
            String censusYear = request.getParameter("censusYear");
            boolean x = false;
            try {
                x = directoryHospitalDAO.setDirectoryInactive(schoolName, Integer.parseInt(censusYear));
            } catch (SQLException ex) {
                Logger.getLogger(UpdateHealthDirectory.class.getName()).log(Level.SEVERE, null, ex);
            }
            String json = new Gson().toJson(x);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(json);
        } else if (redirect.equalsIgnoreCase("submitAll")) {
            boolean checkExist = false;
            try {
                checkExist = directoryHospitalDAO.checkifRecordSubmitted(year-1);
            } catch (SQLException ex) {
                Logger.getLogger(UpdateHealthDirectory.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (checkExist) {
                boolean update= false;
                try {
                     update = directoryHospitalDAO.UpdateValidation(90000000 + year);
                } catch (SQLException ex) {
                    Logger.getLogger(UpdateHealthDirectory.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if(update){
                request.setAttribute("page", "Upload");
                request.setAttribute("saveToDB", "SuccessDB");
                rd = request.getRequestDispatcher("/RetrieveDataHealthServlet?redirect=directoryHosptial");
                rd.forward(request, response);
                }
            } else {

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
            directoryHealth.setNumberOfDoctor(Integer.parseInt(totalDoctors.replaceAll(" ", "").replaceAll(",", "")));
            directoryHealth.setNumberOfNurses(Integer.parseInt(totalNurses.replaceAll(" ", "").replaceAll(",", "")));
            directoryHealth.setNumberOfMidwives(Integer.parseInt(midwives.replaceAll(" ", "").replaceAll(",", "")));
            directoryHealth.setNumberOfBeds(Integer.parseInt(numberofBeds.replaceAll(" ", "").replaceAll(",", "")));
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
                        DirectoryHealth directoryHealth = new DirectoryHealth();
                        directoryHealth.setFormID(90000000+year);
                        directoryHealth.setClassification(classification);
                        directoryHealth.setHospitalName(hospitalName);
                        directoryHealth.setYear(year);
                        directoryHealth.setNumberOfDoctor(Integer.parseInt(totalDoctors.replaceAll(" ", "").replaceAll(",", "")));
                        directoryHealth.setNumberOfNurses(Integer.parseInt(totalNurses.replaceAll(" ", "").replaceAll(",", "")));
                        directoryHealth.setNumberOfMidwives(Integer.parseInt(midwives.replaceAll(" ", "").replaceAll(",", "")));
                        directoryHealth.setNumberOfBeds(Integer.parseInt(numberofBeds.replaceAll(" ", "").replaceAll(",", "")));
                        directoryHealth.setAddresss("");
                        directoryHealth.setTelephone(tel);
                        directoryHealth.setCategory(category);
                        directoryHealth.setAccreditation(Boolean.parseBoolean(accreditation));
                        directoryHealth.setLatitude(0);
                        directoryHealth.setLongitude(0);

                        if (x) {
                            x = directoryHospitalDAO.addNewHospital(directoryHealth);
                            x = directoryHospitalDAO.setDirectoryInactive(hospitalName, Integer.parseInt(oldyear));
                        }
                        
                    } else {
                        x = directoryHospitalDAO.UpdateDirectory(hospitalName, Integer.parseInt(oldyear),
                                Integer.parseInt(totalDoctors), Integer.parseInt(totalNurses),
                                Integer.parseInt(midwives), Integer.parseInt(numberofBeds), accreditation,
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
