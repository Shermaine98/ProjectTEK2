/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */



package servlets.education.access;

import dao.RecordDAO;
import dao.education.DirectorySchoolDAO;
import dao.education.EnrollmentDAO;
import model.GlobalRecords;
import model.Record;
import model.education.DirectorySchool;
import servlets.servlet.BaseServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class RetrieveDataEducationServlet extends BaseServlet {
    
    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String redirect = request.getParameter("redirect");
        String saveToDb = (String) request.getAttribute("saveToDB");
        RequestDispatcher rd = null;
        RecordDAO recordDAO = new RecordDAO();

        if (redirect.equalsIgnoreCase("ePrivate")) {

            EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
            ArrayList<GlobalRecords> ErrRecords = new ArrayList<>();
            ArrayList<Record> records = new ArrayList<>();
            try {
                ErrRecords = enrollmentDAO.getValidationFalsePrivateElementary();
                records = recordDAO.GetForApproval(140000000, 149999999);
            } catch (ParseException | SQLException ex) {
                getLogger(RetrieveDataEducationServlet.class.getName()).log(SEVERE, null, ex);
            }
            if (saveToDb != null) {
                request.setAttribute("saveToDB", saveToDb);
                request.setAttribute("page", "ePrivate");
            } else {
                request.setAttribute("saveToDB", "none");
                request.setAttribute("page", "ePrivate");
            }
            request.setAttribute("validatedRecords", records);
            request.setAttribute("IncompleteRecords", ErrRecords);
            rd = request.getRequestDispatcher("/WEB-INF/JSPEducation/ePrivate.jsp");
            rd.forward(request, response);
        } else if (redirect.equalsIgnoreCase("ePublic")) {
            EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
            ArrayList<GlobalRecords> ErrRecords = new ArrayList<>();
            ArrayList<Record> records = new ArrayList<>();
            try {
                ErrRecords = enrollmentDAO.getValidationFalseKinderPublic();
                records = recordDAO.GetForApproval(160000000, 169999999);
            } catch (ParseException | SQLException ex) {
                getLogger(RetrieveDataEducationServlet.class.getName()).log(SEVERE, null, ex);
            }
            //  System.out.print(censusYear.size());
            if (saveToDb != null) {
                request.setAttribute("saveToDB", saveToDb);
                request.setAttribute("page", "ePublic");

            } else {
                request.setAttribute("saveToDB", "none");
                request.setAttribute("page", "ePublic");
            }
            request.setAttribute("validatedRecords", records);
            request.setAttribute("IncompleteRecords", ErrRecords);
            rd = request.getRequestDispatcher("/WEB-INF/JSPEducation/ePublic.jsp");
            rd.forward(request, response);
        } else if (redirect.equalsIgnoreCase("publicDirectory")) {
            DirectorySchoolDAO directorySchoolDAO = new DirectorySchoolDAO();
            ArrayList<DirectorySchool> directorySchool = new ArrayList<DirectorySchool>();
           
            
            if (saveToDb != null) {
                request.setAttribute("saveToDB", saveToDb);
                request.setAttribute("page", "publicDirectory");

            } else {
                request.setAttribute("saveToDB", "none");
                request.setAttribute("page", "publicDirectory");
            }
            
            
            
            try {
                directorySchool = directorySchoolDAO.ViewDirectorySchoolRecentPublic();
            } catch (ParseException ex) {
                Logger.getLogger(RetrieveDataEducationServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(RetrieveDataEducationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            

            request.setAttribute("directory", directorySchool);

            rd = request.getRequestDispatcher("/WEB-INF/JSPEducation/publicDirectory.jsp");

            rd.forward(request, response);

        } else if (redirect.equalsIgnoreCase("privateDirectory")) {
            DirectorySchoolDAO directorySchoolDAO = new DirectorySchoolDAO();
            ArrayList<DirectorySchool> directorySchool = new ArrayList<DirectorySchool>();
            
             if (saveToDb != null) {
                request.setAttribute("saveToDB", saveToDb);
                request.setAttribute("page", "privateDirectory");

            } else {
                request.setAttribute("saveToDB", "none");
                request.setAttribute("page", "privateDirectory");
            }
            
            try {
                directorySchool = directorySchoolDAO.ViewDirectorySchoolRecentPrivate();
            } catch (ParseException ex) {
                Logger.getLogger(RetrieveDataEducationServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(RetrieveDataEducationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.setAttribute("directory", directorySchool);

            rd = request.getRequestDispatcher("/WEB-INF/JSPEducation/privateDirectory.jsp");

            rd.forward(request, response);
        } // head    
        else if (redirect.equalsIgnoreCase("education_approval")) {
            ArrayList<Record> publicEnrollment = new ArrayList<>();
            ArrayList<Record> privateEnrollment = new ArrayList<>();
            ArrayList<Record> directoryPrivate = new ArrayList<>();
            ArrayList<Record> directoryPublic = new ArrayList<>();
            try {
                publicEnrollment = recordDAO.GetForApproval(160000000, 169999999);
            } catch (ParseException ex) {
                Logger.getLogger(RetrieveDataEducationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                privateEnrollment = recordDAO.GetForApproval(140000000, 149999999);
            } catch (ParseException ex) {
                Logger.getLogger(RetrieveDataEducationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                directoryPrivate = recordDAO.GetForApproval(120000000, 129999999);
            } catch (ParseException ex) {
                Logger.getLogger(RetrieveDataEducationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                directoryPublic = recordDAO.GetForApproval(190000000, 199999999);
            } catch (ParseException ex) {
                Logger.getLogger(RetrieveDataEducationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.setAttribute("page", "approvalAdmin");
            request.setAttribute("message", "none");
            request.setAttribute("publicEnrollment", publicEnrollment);
            request.setAttribute("directoryPrivate", directoryPrivate);
            request.setAttribute("directoryPublic", directoryPublic);
            request.setAttribute("privateEnrollment", privateEnrollment);
            request.setAttribute("subject", "");
            rd = request.getRequestDispatcher("/WEB-INF/JSPApproval/education.jsp");
            rd.forward(request, response);
        } else if (redirect.contains("approval_")) {
            if (redirect.equalsIgnoreCase("approval_publicenrollment")) {
                request.setAttribute("subject", "epublic");
            } else if (redirect.equalsIgnoreCase("approval_privateenrollment")) {
                request.setAttribute("subject", "eprivate");
            }  else if (redirect.equalsIgnoreCase("approval_publicdirectory")) {
                request.setAttribute("subject", "publicdirectory");
            } else if (redirect.equalsIgnoreCase("approval_privatedirectory")) {
                request.setAttribute("subject", "privatedirectory");
            }
            ArrayList<Record> publicEnrollment = new ArrayList<>();
            ArrayList<Record> privateEnrollment = new ArrayList<>();
            ArrayList<Record> directoryPrivate = new ArrayList<>();
            ArrayList<Record> directoryPublic = new ArrayList<>();
            try {
                publicEnrollment = recordDAO.GetForApproval(160000000, 169999999);
            } catch (ParseException ex) {
                Logger.getLogger(RetrieveDataEducationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                privateEnrollment = recordDAO.GetForApproval(140000000, 149999999);
            } catch (ParseException ex) {
                Logger.getLogger(RetrieveDataEducationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                directoryPrivate = recordDAO.GetForApproval(120000000, 129999999);
            } catch (ParseException ex) {
                Logger.getLogger(RetrieveDataEducationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                directoryPublic = recordDAO.GetForApproval(190000000, 199999999);
            } catch (ParseException ex) {
                Logger.getLogger(RetrieveDataEducationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.setAttribute("page", "approvalAdmin");
            request.setAttribute("message", "none");
            request.setAttribute("publicEnrollment", publicEnrollment);
            request.setAttribute("directoryPrivate", directoryPrivate);
            request.setAttribute("directoryPublic", directoryPublic);
            request.setAttribute("privateEnrollment", privateEnrollment);
            rd = request.getRequestDispatcher("/WEB-INF/JSPApproval/education.jsp");
            rd.forward(request, response);

        } else {
            request.setAttribute("page", "error");
            rd = request.getRequestDispatcher("redirect_error.jsp");
            rd.forward(request, response);

        }

    }
}
