/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package servlets.health.servlet;

import dao.health.NutritionalStatusDAO;
import dao.health.DirectoryHospitalDAO;
import model.health.NutritionalStatus;
import model.health.DirectoryHealth;
import servlets.servlet.BaseServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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

public class ViewReportForApprovalHealth extends BaseServlet {

    /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException servlet exception
     * @throws IOException servlet IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String formID = request.getParameter("formID");
        String page = request.getParameter("page");
        PrintWriter out = response.getWriter();

        RequestDispatcher rd = null;

        // VIEW FOR APPROVAL
        if (page.equalsIgnoreCase("nutritional")) {
            NutritionalStatusDAO DAO = new NutritionalStatusDAO();
            ArrayList<NutritionalStatus> nutritionalStatus = new ArrayList<NutritionalStatus>();
            try {
                nutritionalStatus = DAO.ViewNutritionalStatus(Integer.parseInt(formID));
            } catch (ParseException ex) {
                Logger.getLogger(ViewReportForApprovalHealth.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("nutritional", nutritionalStatus);
            request.setAttribute("clicked", "forView");
            rd = request.getRequestDispatcher("/WEB-INF/JSPViewTables/Health_NutritionalStatus.jsp");
            rd.forward(request, response);
        }

        //VIEW FOR ADMIN TO APPROVE
     else  if (page.equalsIgnoreCase("nutritionalApproval")) {
            NutritionalStatusDAO DAO = new NutritionalStatusDAO();
            ArrayList<NutritionalStatus> nutritionalStatus = new ArrayList<NutritionalStatus>();
            try {
                nutritionalStatus = DAO.ViewNutritionalStatus(Integer.parseInt(formID));
        
            } catch (ParseException ex) {
                Logger.getLogger(ViewReportForApprovalHealth.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println(formID);
            out.println(nutritionalStatus.size());
            request.setAttribute("nutritional", nutritionalStatus);
            request.setAttribute("clicked", "approvalAdmin");
            rd = request.getRequestDispatcher("/WEB-INF/JSPViewTables/Health_NutritionalStatus.jsp");
            rd.forward(request, response);
        }
     
     else  if (page.equalsIgnoreCase("directoryApproval")) {
            DirectoryHospitalDAO DAO = new DirectoryHospitalDAO();
            ArrayList<DirectoryHealth> directoryHealth = new ArrayList<DirectoryHealth>();
            try {
                directoryHealth = DAO.ViewByDirectoryHospital(Integer.parseInt(formID));
        
            } catch (ParseException ex) {
                Logger.getLogger(ViewReportForApprovalHealth.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("directoryHealth", directoryHealth);
            rd = request.getRequestDispatcher("/WEB-INF/JSPViewTables/hospitalDirectoryApproval.jsp");
            rd.forward(request, response);
        }
     
        
            //VIEW FOR ADMIN TO APPROVE SPECIFIC FROM NOTIFICATION
       else if (page.equalsIgnoreCase("nutritionalApprovalS")) {
            NutritionalStatusDAO DAO = new NutritionalStatusDAO();
            ArrayList<NutritionalStatus> nutritionalStatus = new ArrayList<NutritionalStatus>();
            try {
                nutritionalStatus = DAO.ViewNutritionalStatus(800000000 +Integer.parseInt(formID));
        
            } catch (ParseException ex) {
                Logger.getLogger(ViewReportForApprovalHealth.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println(formID);
            out.println(nutritionalStatus.size());
            request.setAttribute("nutritional", nutritionalStatus);
            request.setAttribute("clicked", "approvalAdmin");
            rd = request.getRequestDispatcher("/WEB-INF/JSPViewTables/Health_NutritionalStatus.jsp");
            rd.forward(request, response);
        }
    }
}
