/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */



package servlets.education.servlet;

import dao.RecordDAO;
import model.Record;
import servlets.servlet.BaseServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import static java.util.logging.Level.SEVERE;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static java.lang.Integer.parseInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class ApprovalsEducation extends BaseServlet {

     /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException servlet exception
     * @throws IOException servlet IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user = request.getParameter("sUser");
        String formID = request.getParameter("sformID");
        String decision = request.getParameter("decision");

        RecordDAO recordDAO = new RecordDAO();
        ArrayList<Record> publicEnrollment = new ArrayList<>();
        ArrayList<Record> privateEnrollment = new ArrayList<>();
        ArrayList<Record> directoryPrivate = new ArrayList<>();
        ArrayList<Record> directoryPublic = new ArrayList<>();
        if (decision.equalsIgnoreCase("approve")) {
            boolean x = false;
            try {
                x = recordDAO.approvedRecord(parseInt(formID), parseInt(user));
            } catch (SQLException ex) {
                getLogger(ApprovalsEducation.class.getName()).log(SEVERE, null, ex);
            }
            RequestDispatcher rd = null;

            if (x) {

                try {
                    publicEnrollment = recordDAO.GetForApproval(160000000, 169999999);
                } catch (ParseException ex) {
                    Logger.getLogger(ApprovalsEducation.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    privateEnrollment = recordDAO.GetForApproval(140000000, 149999999);
                } catch (ParseException ex) {
                    Logger.getLogger(ApprovalsEducation.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    directoryPrivate = recordDAO.GetForApproval(120000000, 129999999);
                } catch (ParseException ex) {
                    Logger.getLogger(ApprovalsEducation.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    directoryPublic = recordDAO.GetForApproval(190000000, 199999999);
                } catch (ParseException ex) {
                    Logger.getLogger(ApprovalsEducation.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("subject", "");
                request.setAttribute("page", "approvalAdmin");
                request.setAttribute("message", "success");
                request.setAttribute("publicEnrollment", publicEnrollment);
                request.setAttribute("directoryPrivate", directoryPrivate);
                request.setAttribute("directoryPublic", directoryPublic);
                request.setAttribute("privateEnrollment", privateEnrollment);
                rd = request.getRequestDispatcher("/WEB-INF/JSPApproval/education.jsp");
                rd.forward(request, response);

            } else {
                request.setAttribute("message", "notSuccess");
                rd = request.getRequestDispatcher("/WEB-INF/JSPApproval/demographics.jsp");
                rd.forward(request, response);

            }

        } else if (decision.equalsIgnoreCase("reject")) {
            String comments = request.getParameter("comments");

            boolean x = false;
            try {
                x = recordDAO.rejectRecord(parseInt(formID), parseInt(user),comments);
            } catch (SQLException ex) {
                getLogger(ApprovalsEducation.class.getName()).log(SEVERE, null, ex);
            }
            RequestDispatcher rd = null;

            if (x) {

                try {
                    publicEnrollment = recordDAO.GetForApproval(160000000, 169999999);
                } catch (ParseException ex) {
                    Logger.getLogger(ApprovalsEducation.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    privateEnrollment = recordDAO.GetForApproval(140000000, 149999999);
                } catch (ParseException ex) {
                    Logger.getLogger(ApprovalsEducation.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    directoryPrivate = recordDAO.GetForApproval(120000000, 129999999);
                } catch (ParseException ex) {
                    Logger.getLogger(ApprovalsEducation.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    directoryPublic = recordDAO.GetForApproval(190000000, 199999999);
                } catch (ParseException ex) {
                    Logger.getLogger(ApprovalsEducation.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("subject", "");
                request.setAttribute("page", "approvalAdmin");
                request.setAttribute("message", "successRejected");
                request.setAttribute("publicEnrollment", publicEnrollment);
                request.setAttribute("directoryPrivate", directoryPrivate);
                request.setAttribute("directoryPublic", directoryPublic);
                request.setAttribute("privateEnrollment", privateEnrollment);
                rd = request.getRequestDispatcher("/WEB-INF/JSPApproval/education.jsp");
                rd.forward(request, response);

            } else {

                try {
                    publicEnrollment = recordDAO.GetForApproval(160000000, 169999999);
                } catch (ParseException ex) {
                    Logger.getLogger(ApprovalsEducation.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    privateEnrollment = recordDAO.GetForApproval(140000000, 149999999);
                } catch (ParseException ex) {
                    Logger.getLogger(ApprovalsEducation.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    directoryPrivate = recordDAO.GetForApproval(120000000, 129999999);
                } catch (ParseException ex) {
                    Logger.getLogger(ApprovalsEducation.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    directoryPublic = recordDAO.GetForApproval(190000000, 199999999);
                } catch (ParseException ex) {
                    Logger.getLogger(ApprovalsEducation.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("subject", "");
                request.setAttribute("page", "approvalAdmin");
                request.setAttribute("message", "notSuccessRejection");
                request.setAttribute("publicEnrollment", publicEnrollment);
                request.setAttribute("directoryPrivate", directoryPrivate);
                request.setAttribute("directoryPublic", directoryPublic);
                request.setAttribute("privateEnrollment", privateEnrollment);
                rd = request.getRequestDispatcher("/WEB-INF/JSPApproval/education.jsp");
                rd.forward(request, response);

            }

        }

    }
}
