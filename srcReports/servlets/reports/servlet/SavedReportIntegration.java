/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.reports.servlet;

import dao.reports.ReportDAO;
import model.reports.Integrated;
import model.accounts.User;
import servlets.servlet.BaseServlet;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Atayan
 * @author Roxas
 * @author Sy
 *
 */
public class SavedReportIntegration extends BaseServlet {

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

        // IF ANALYSIS  

        try {
            //DELETE OLD
            
            x = ReportDAO.checkIfExistIntegrated(sector, year);
            if(x){
               x = ReportDAO.deleteIntegratedAnalysis(sector, year);
            }else{
                x = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SavedReportIntegration.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (x) {
            String text = request.getParameter("text");

            Integrated Integrated = new Integrated();

            Integrated.setSector(sector);
            Integrated.setText(text);
            Integrated.setIsDraft(Boolean.parseBoolean(isDraft));
            Integrated.setYear(year);
            Integrated.setCreatedBy(chck.getUserID());

            x = ReportDAO.newIntegratedAnalysis(year, Integrated);
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
