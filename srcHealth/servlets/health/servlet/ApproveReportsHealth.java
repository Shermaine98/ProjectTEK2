/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.health.servlet;

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
 * @author Atayan
 * @author Roxas
 * @author Sy
 *
 */
public class ApproveReportsHealth extends BaseServlet {

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

        ArrayList<Record> nutrition = new ArrayList<>();

        if (decision.equalsIgnoreCase("approve")) {
            boolean x = false;
            try {
                x = recordDAO.approvedRecord(parseInt(formID), parseInt(user));
            } catch (SQLException ex) {
                getLogger(ApproveReportsHealth.class.getName()).log(SEVERE, null, ex);
            }
            RequestDispatcher rd = null;

            if (x) {
                try {
                    nutrition = recordDAO.GetForApproval(800000000, 899999999);
                } catch (ParseException ex) {
                    Logger.getLogger(ApproveReportsHealth.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("subject", "");
                request.setAttribute("message", "success");
                request.setAttribute("NutritionalStatus", nutrition);
                rd = request.getRequestDispatcher("/WEB-INF/JSPApproval/health.jsp");
                rd.forward(request, response);

            } else {
                request.setAttribute("subject", "");
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
                getLogger(ApproveReportsHealth.class.getName()).log(SEVERE, null, ex);
            }
            RequestDispatcher rd = null;

            if (x) {
                try {
                    nutrition = recordDAO.GetForApproval(800000000, 899999999);
                } catch (ParseException ex) {
                    Logger.getLogger(ApproveReportsHealth.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("subject", "");
                request.setAttribute("message", "successRejected");
                request.setAttribute("NutritionalStatus", nutrition);
                rd = request.getRequestDispatcher("/WEB-INF/JSPApproval/health.jsp");
                rd.forward(request, response);

            } else {

                try {
                    nutrition = recordDAO.GetForApproval(800000000, 899999999);
                } catch (ParseException ex) {
                    Logger.getLogger(ApproveReportsHealth.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("subject", "");
                request.setAttribute("message", "notSuccessRejection");
                request.setAttribute("NutritionalStatus", nutrition);
                rd = request.getRequestDispatcher("/WEB-INF/JSPApproval/health.jsp");
                rd.forward(request, response);

            }

        }

    }
}
