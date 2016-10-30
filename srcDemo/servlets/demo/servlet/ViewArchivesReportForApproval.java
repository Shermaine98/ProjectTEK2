/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package servlets.demo.servlet;

import dao.demo.HighestCompletedDAO;
import dao.demo.MaritalStatusDAO;
import dao.demo.ByAgeGroupSexDAO;
import model.demo.ByAgeGroupSex;
import model.demo.HighestCompleted;
import model.demo.MaritalStatus;
import servlets.servlet.BaseServlet;
import java.io.IOException;
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
public class ViewArchivesReportForApproval extends BaseServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String formID = request.getParameter("formID");
        String page = request.getParameter("page");
        RequestDispatcher rd = null;

        // VIEW FOR APPROVAL
        if (page.equalsIgnoreCase("byAgeGroup")) {
            ByAgeGroupSexDAO DAO = new ByAgeGroupSexDAO();
            ArrayList<ByAgeGroupSex> byAgeGroupSex = new ArrayList<ByAgeGroupSex>();
            try {
                byAgeGroupSex = DAO.ViewByAgeGroupSexFormID(Integer.parseInt(formID));
            } catch (ParseException ex) {
                Logger.getLogger(ViewArchivesReportForApproval.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("ageGroupSexData", byAgeGroupSex);
            request.setAttribute("clicked", "forView");
            rd = request.getRequestDispatcher("/WEB-INF/JSPViewTables/Demo_AgeGroup.jsp");
            rd.forward(request, response);
        } else if (page.equalsIgnoreCase("maritalStatus")) {
            MaritalStatusDAO DAO = new MaritalStatusDAO();
            ArrayList<MaritalStatus> maritalStatus = new ArrayList<MaritalStatus>();
            try {
                maritalStatus = DAO.ViewMaritalStatusFormID(Integer.parseInt(formID));
            } catch (ParseException ex) {
                Logger.getLogger(ViewArchivesReportForApproval.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("maritalStatusData", maritalStatus);
            request.setAttribute("clicked", "forView");
            rd = request.getRequestDispatcher("/WEB-INF/JSPViewTables/Demo_MaritalStatus.jsp");
            rd.forward(request, response);
        } else if (page.equalsIgnoreCase("highestAttaintment")) {
            HighestCompletedDAO DAO = new HighestCompletedDAO();
            ArrayList<HighestCompleted> HighestCompleted = new ArrayList<HighestCompleted>();
            try {
                HighestCompleted = DAO.ViewHighestCompletedFormID(Integer.parseInt(formID));
            } catch (ParseException ex) {
                Logger.getLogger(ViewArchivesReportForApproval.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("SIZE: "+HighestCompleted.size());
            request.setAttribute("highestAttaintmentData", HighestCompleted);
            
            request.setAttribute("clicked", "forView");
            rd = request.getRequestDispatcher("/WEB-INF/JSPViewTables/Demo_HighestCompleted.jsp");
            rd.forward(request, response);
        }

        //VIEW FOR ADMIN TO APPROVE
        else if (page.equalsIgnoreCase("byAgeGroupApproval")) {
            ByAgeGroupSexDAO DAO = new ByAgeGroupSexDAO();
            ArrayList<ByAgeGroupSex> byAgeGroupSex = new ArrayList<ByAgeGroupSex>();
            try {
                byAgeGroupSex = DAO.ViewByAgeGroupSexFormID(Integer.parseInt(formID));
            } catch (ParseException ex) {
                Logger.getLogger(ViewArchivesReportForApproval.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("ageGroupSexData", byAgeGroupSex);
            request.setAttribute("clicked", "approvalAdmin");
            rd = request.getRequestDispatcher("/WEB-INF/JSPViewTables/Demo_AgeGroup.jsp");
            rd.forward(request, response);
        } else if (page.equalsIgnoreCase("maritalStatusApproval")) {
            MaritalStatusDAO DAO = new MaritalStatusDAO();
            ArrayList<MaritalStatus> maritalStatus = new ArrayList<MaritalStatus>();
            try {
                maritalStatus = DAO.ViewMaritalStatusFormID(Integer.parseInt(formID));
            } catch (ParseException ex) {
                Logger.getLogger(ViewArchivesReportForApproval.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("maritalStatusData", maritalStatus);
            request.setAttribute("clicked", "approvalAdmin");
            rd = request.getRequestDispatcher("/WEB-INF/JSPViewTables/Demo_MaritalStatus.jsp");
            rd.forward(request, response);
        } else if (page.equalsIgnoreCase("highestAttaintmentApproval")) {
            HighestCompletedDAO DAO = new HighestCompletedDAO();
            ArrayList<HighestCompleted> HighestCompleted = new ArrayList<HighestCompleted>();
            try {
                HighestCompleted = DAO.ViewHighestCompletedFormID(Integer.parseInt(formID));
            } catch (ParseException ex) {
                Logger.getLogger(ViewArchivesReportForApproval.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("highestAttaintmentData", HighestCompleted);
            request.setAttribute("clicked", "approvalAdmin");
            rd = request.getRequestDispatcher("/WEB-INF/JSPViewTables/Demo_HighestCompleted.jsp");
            rd.forward(request, response);
        }
        
        
        
        //VIEW FOR ADMIN TO APPROVE SEPECIF NOTIFACTION
        else if (page.equalsIgnoreCase("byAgeGroupApprovalS")) {
            ByAgeGroupSexDAO DAO = new ByAgeGroupSexDAO();
            ArrayList<ByAgeGroupSex> byAgeGroupSex = new ArrayList<ByAgeGroupSex>();
            try {
                byAgeGroupSex = DAO.ViewByAgeGroupSexFormID(200000000 + Integer.parseInt(formID));
            } catch (ParseException ex) {
                Logger.getLogger(ViewArchivesReportForApproval.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("ageGroupSexData", byAgeGroupSex);
            request.setAttribute("clicked", "approvalAdmin");
            rd = request.getRequestDispatcher("/WEB-INF/JSPViewTables/Demo_AgeGroup.jsp");
            rd.forward(request, response);
        } else if (page.equalsIgnoreCase("maritalStatusApprovalS")) {
            MaritalStatusDAO DAO = new MaritalStatusDAO();
            ArrayList<MaritalStatus> maritalStatus = new ArrayList<MaritalStatus>();
            try {
                maritalStatus = DAO.ViewMaritalStatusFormID(300000000 + Integer.parseInt(formID));
            } catch (ParseException ex) {
                Logger.getLogger(ViewArchivesReportForApproval.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("maritalStatusData", maritalStatus);
            request.setAttribute("clicked", "approvalAdmin");
            rd = request.getRequestDispatcher("/WEB-INF/JSPViewTables/Demo_MaritalStatus.jsp");
            rd.forward(request, response);
        } else if (page.equalsIgnoreCase("highestAttaintmentApprovalS")) {
            HighestCompletedDAO DAO = new HighestCompletedDAO();
            ArrayList<HighestCompleted> HighestCompleted = new ArrayList<HighestCompleted>();
            try {
                HighestCompleted = DAO.ViewHighestCompletedFormID(400000000 + Integer.parseInt(formID));
            } catch (ParseException ex) {
                Logger.getLogger(ViewArchivesReportForApproval.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("highestAttaintmentData", HighestCompleted);
            request.setAttribute("clicked", "approvalAdmin");
            rd = request.getRequestDispatcher("/WEB-INF/JSPViewTables/Demo_HighestCompleted.jsp");
            rd.forward(request, response);
        }
    }
}
