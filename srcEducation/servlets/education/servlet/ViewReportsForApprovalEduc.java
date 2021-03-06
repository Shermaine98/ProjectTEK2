/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package servlets.education.servlet;

import dao.education.DirectorySchoolDAO;
import dao.education.EnrollmentDAO;
import model.education.Enrollment;
import servlets.servlet.BaseServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.education.DirectorySchool;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 *
 */
public class ViewReportsForApprovalEduc extends BaseServlet {

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

        String formID = request.getParameter("formID");
        String page = request.getParameter("page");
        String classification = request.getParameter("classification");
        RequestDispatcher rd = null;
        // VIEW FOR APPROVAL
        if (page.equalsIgnoreCase("enrollment")) {
            EnrollmentDAO DAO = new EnrollmentDAO();
            ArrayList<Enrollment> enrollment = new ArrayList<Enrollment>();
            try {
                enrollment = DAO.ViewEnrollmentFormID(Integer.parseInt(formID), classification.trim());
            } catch (ParseException ex) {
                Logger.getLogger(ViewReportsForApprovalEduc.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("enrollment", enrollment);
            request.setAttribute("classification", classification.trim());
            request.setAttribute("clicked", "forView");
            rd = request.getRequestDispatcher("/WEB-INF/JSPViewTables/Education_Enrollment.jsp");
            rd.forward(request, response);
        } else if (page.equalsIgnoreCase("enrollmentS")) {
            EnrollmentDAO DAO = new EnrollmentDAO();
            ArrayList<Enrollment> enrollment = new ArrayList<Enrollment>();

            if (classification.equalsIgnoreCase("private")) {
                try {
                    enrollment = DAO.ViewEnrollmentFormID(140000000 + Integer.parseInt(formID), classification.trim());
                } catch (ParseException ex) {
                    Logger.getLogger(ViewReportsForApprovalEduc.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    enrollment = DAO.ViewEnrollmentFormID(160000000 + Integer.parseInt(formID), classification.trim());
                } catch (ParseException ex) {
                    Logger.getLogger(ViewReportsForApprovalEduc.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            request.setAttribute("enrollment", enrollment);
            request.setAttribute("classification", classification.trim());
            request.setAttribute("clicked", "forView");
            rd = request.getRequestDispatcher("/WEB-INF/JSPViewTables/Education_Enrollment.jsp");
            rd.forward(request, response);
        } //VIEW FOR ADMIN TO APPROVE
        else if (page.equalsIgnoreCase("enrollmentApproval")) {
            EnrollmentDAO DAO = new EnrollmentDAO();
            ArrayList<Enrollment> enrollment = new ArrayList<Enrollment>();

            if (classification.equalsIgnoreCase("private")) {
                try {
                    enrollment = DAO.ViewEnrollmentFormID(Integer.parseInt(formID), classification.trim());
                } catch (ParseException ex) {
                    Logger.getLogger(ViewReportsForApprovalEduc.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    enrollment = DAO.ViewEnrollmentFormID(Integer.parseInt(formID), classification.trim());
                } catch (ParseException ex) {
                    Logger.getLogger(ViewReportsForApprovalEduc.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            request.setAttribute("enrollment", enrollment);
            request.setAttribute("classification", classification.trim());
            request.setAttribute("clicked", "approvalAdmin");
            out.print("enrollment " + enrollment.size());
            rd = request.getRequestDispatcher("/WEB-INF/JSPViewTables/Education_Enrollment.jsp");
            rd.forward(request, response);
        } //VIEW FOR ADMIN TO APPROVE
        else if (page.equalsIgnoreCase("enrollmentApprovalS")) {
            EnrollmentDAO DAO = new EnrollmentDAO();
            ArrayList<Enrollment> enrollment = new ArrayList<Enrollment>();

            if (classification.equalsIgnoreCase("private")) {
                try {
                    enrollment = DAO.ViewEnrollmentFormID(140000000 + Integer.parseInt(formID), classification.trim());
                } catch (ParseException ex) {
                    Logger.getLogger(ViewReportsForApprovalEduc.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    enrollment = DAO.ViewEnrollmentFormID(160000000 + Integer.parseInt(formID), classification.trim());
                } catch (ParseException ex) {
                    Logger.getLogger(ViewReportsForApprovalEduc.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            request.setAttribute("enrollment", enrollment);
            request.setAttribute("classification", classification.trim());
            request.setAttribute("clicked", "approvalAdmin");
            out.print("enrollment " + enrollment.size());
            rd = request.getRequestDispatcher("/WEB-INF/JSPViewTables/Education_Enrollment.jsp");
            rd.forward(request, response);
        } //  APPROVAL EDUCATION DIRECTORY
        else if (page.equalsIgnoreCase("directoryApproval")) {
            DirectorySchoolDAO DAO = new DirectorySchoolDAO();
            ArrayList<DirectorySchool> directorySchool = new ArrayList<DirectorySchool>();

            if (classification.equalsIgnoreCase("Public")) {
                try {
                    directorySchool = DAO.ViewDirectorySchoolRecentPublic();
                } catch (ParseException | SQLException ex) {
                    Logger.getLogger(ViewReportsForApprovalEduc.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    directorySchool = DAO.ViewDirectorySchoolRecentPrivate();
                } catch (ParseException | SQLException ex) {
                    Logger.getLogger(ViewReportsForApprovalEduc.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            request.setAttribute("directorySchool", directorySchool);
            request.setAttribute("classification", classification.trim());
            request.setAttribute("clicked", "approvalAdmin");
            rd = request.getRequestDispatcher("/WEB-INF/JSPViewTables/SchoolDirectoryApproval.jsp");
            rd.forward(request, response);
        }
    }
}
