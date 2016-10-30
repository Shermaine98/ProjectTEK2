/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package servlets.reports.servlet;

import dao.reports.ReportDAO;
import model.accounts.User;
import model.reports.ReportAnalysis;
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

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class SavedReportAnalysis extends BaseServlet {

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        User chck = (User) session.getAttribute("user");
        int year = Calendar.getInstance().get(Calendar.YEAR);
        RequestDispatcher rd = null;

        String isDraft = request.getParameter("isDraft");

        ReportDAO ReportDAO = new ReportDAO();

        //IV - Health
        //III - Education
        //I - Demo        
        String sector = null;
        if (chck.getPosition().equals("Project Development Officer IV")) {
            sector = "Health";
        } else if (chck.getPosition().equals("Project Development Officer III")) {
            sector = "Education";
        } else if (chck.getPosition().equals("Project Development Officer I")) {
            sector = "Demographics";
        }

        boolean x = false;

        try {
            //DELETE OLD
            x = ReportDAO.deleteRecordReportAnalysis(sector, year);
        } catch (SQLException ex) {
            Logger.getLogger(SavedReportAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (x) {

            ArrayList<ReportAnalysis> arrReportAnalysis = new ArrayList<ReportAnalysis>();
            String minedataAnalysis[] = request.getParameterValues("imageSrcAnalysis");
            String text[] = request.getParameterValues("analysis");
            if (minedataAnalysis != null) {
                for (int i = 0; i < minedataAnalysis.length; i++) {
                    ReportAnalysis reportAnalysis = new ReportAnalysis();
                    reportAnalysis.setYear(year);
                    reportAnalysis.setChartName(minedataAnalysis[i]);
                    reportAnalysis.setText(text[i]);
                    reportAnalysis.setCreatedBy(chck.getUserID());
                    reportAnalysis.setSector(sector);
                    reportAnalysis.setIsDraft(Boolean.parseBoolean(isDraft));
                    arrReportAnalysis.add(reportAnalysis);
                }
                x = ReportDAO.newReportAnalysis(arrReportAnalysis);
            }

        }

        if (x) {
            if (isDraft.equalsIgnoreCase("true")) {
                rd = request.getRequestDispatcher("/ReportAccess?redirect=Saved&savedMessage=success");
                rd.forward(request, response);

            } else if (isDraft.equalsIgnoreCase("false")) {
                rd = request.getRequestDispatcher("/ReportAccess?redirect=submit&savedMessage=success");
                rd.forward(request, response);
            }

        } else {
            rd = request.getRequestDispatcher("/ReportAccess?redirect=Saved&savedMessage=error");
            rd.forward(request, response);
        }
    }
}
