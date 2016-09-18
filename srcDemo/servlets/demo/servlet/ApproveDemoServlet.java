/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.demo.servlet;

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
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Atayan
 * @author Roxas
 * @author Sy
 *
 */
public class ApproveDemoServlet extends BaseServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user = request.getParameter("sUser");
        String formID = request.getParameter("sformID");
        String decision = request.getParameter("decision");

        RecordDAO recordDAO = new RecordDAO();
        ArrayList<Record> recordsAge = new ArrayList<>();
        ArrayList<Record> recordsMarital = new ArrayList<>();
        ArrayList<Record> recordsHighest = new ArrayList<>();
        if (decision.equalsIgnoreCase("approve")) {
            boolean x = false;
            try {
                x = recordDAO.approvedRecord(parseInt(formID), parseInt(user));
            } catch (SQLException ex) {
                getLogger(ApproveDemoServlet.class.getName()).log(SEVERE, null, ex);
            }
            RequestDispatcher rd = null;

            if (x) {
                //AGE GROUP APPRVOVAL
                try {
                    recordsAge = recordDAO.GetForApproval(200000000, 299999999);
                } catch (ParseException ex) {
                    getLogger(ApproveDemoServlet.class.getName()).log(SEVERE, null, ex);
                }

                //MARITAL GROUP APPRVOVAL
                try {
                    recordsMarital = recordDAO.GetForApproval(300000000, 399999999);
                } catch (ParseException ex) {
                    getLogger(ApproveDemoServlet.class.getName()).log(SEVERE, null, ex);
                }
                //Highest GROUP APPRVOVAL
                try {
                    recordsHighest = recordDAO.GetForApproval(400000000, 499999999);
                } catch (ParseException ex) {
                    getLogger(ApproveDemoServlet.class.getName()).log(SEVERE, null, ex);
                }
                request.setAttribute("message", "success");
                request.setAttribute("age", recordsAge);
                request.setAttribute("marital", recordsMarital);
                request.setAttribute("highest", recordsHighest);
                request.setAttribute("subject", "");
                rd = request.getRequestDispatcher("/WEB-INF/JSPApproval/demographics.jsp");
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
                x = recordDAO.rejectRecord(parseInt(formID), parseInt(user), comments);
            } catch (SQLException ex) {
                getLogger(ApproveDemoServlet.class.getName()).log(SEVERE, null, ex);
            }
            RequestDispatcher rd = null;

            if (x) {
                //AGE GROUP APPRVOVAL
                try {
                    recordsAge = recordDAO.GetForApproval(200000000, 299999999);
                } catch (ParseException ex) {
                    getLogger(ApproveDemoServlet.class.getName()).log(SEVERE, null, ex);
                }

                //MARITAL GROUP APPRVOVAL
                try {
                    recordsMarital = recordDAO.GetForApproval(300000000, 399999999);
                } catch (ParseException ex) {
                    getLogger(ApproveDemoServlet.class.getName()).log(SEVERE, null, ex);
                }
                //Highest GROUP APPRVOVAL
                try {
                    recordsHighest = recordDAO.GetForApproval(400000000, 499999999);
                } catch (ParseException ex) {
                    getLogger(ApproveDemoServlet.class.getName()).log(SEVERE, null, ex);
                }
                request.setAttribute("subject", "");
                request.setAttribute("message", "successRejected");
                request.setAttribute("age", recordsAge);
                request.setAttribute("marital", recordsMarital);
                request.setAttribute("highest", recordsHighest);
                rd = request.getRequestDispatcher("/WEB-INF/JSPApproval/demographics.jsp");
                rd.forward(request, response);

            } else {
                
                 try {
                    recordsAge = recordDAO.GetForApproval(200000000, 299999999);
                } catch (ParseException ex) {
                    getLogger(ApproveDemoServlet.class.getName()).log(SEVERE, null, ex);
                }

                //MARITAL GROUP APPRVOVAL
                try {
                    recordsMarital = recordDAO.GetForApproval(300000000, 399999999);
                } catch (ParseException ex) {
                    getLogger(ApproveDemoServlet.class.getName()).log(SEVERE, null, ex);
                }
                //Highest GROUP APPRVOVAL
                try {
                    recordsHighest = recordDAO.GetForApproval(400000000, 499999999);
                } catch (ParseException ex) {
                    getLogger(ApproveDemoServlet.class.getName()).log(SEVERE, null, ex);
                }
               request.setAttribute("subject", "");
                request.setAttribute("message", "notSuccessRejection");
                request.setAttribute("age", recordsAge);
                request.setAttribute("marital", recordsMarital);
                request.setAttribute("highest", recordsHighest);
                rd = request.getRequestDispatcher("/WEB-INF/JSPApproval/demographics.jsp");
                rd.forward(request, response);

            }

        }

    }
}
