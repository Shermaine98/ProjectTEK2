/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package servlets.access;

import dao.accounts.Accounts;
import dao.DemoDashboard;
import dao.EducDashboard;
import dao.HealthDashboard;
import dao.TaskDAO;
import model.accounts.User;
import model.TaskModel;
import servlets.servlet.BaseServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
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
 * @author shermaine Sy
 * @author Geraldine Atayan
 *
 */
public class ServletAccess extends BaseServlet {

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String redirect = request.getParameter("redirect");
//                HttpSession session = request.getSession();
// ServletContext context= getServletContext();
            RequestDispatcher rd = null;
            Accounts accountsDAO = new Accounts();

            DemoDashboard dDAO = new DemoDashboard();
            HealthDashboard hDAO = new HealthDashboard();
            EducDashboard eDAO = new EducDashboard();
            TaskDAO taskDAO = new TaskDAO();

            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            String yearInString = String.valueOf(year);

            HttpSession session = request.getSession();
            User chck = (User) session.getAttribute("user");

//Approval Demo
            request.setAttribute("aDemoAgeGroup", dDAO.aDemoAgeGroup());
            request.setAttribute("aDemoMarital", dDAO.aDemoMarital());
            request.setAttribute("aDemoHighest", dDAO.aDemoHighest());

//Approval Educ
            request.setAttribute("aEducPublicEnrollment", eDAO.aEducEnrollmentPublic());
            request.setAttribute("aEducPublicDirectory", eDAO.aEducPublicDirectory());
            request.setAttribute("aEducPrivateEnrollment", eDAO.aEducEnrollmentPrivate());
            request.setAttribute("aEducPrivateDirectory", eDAO.aEducPrivateDirectory());

//Approval Health
            request.setAttribute("aHealthNutritionalStatus", hDAO.aHealthNutritionalStatus());
            request.setAttribute("aHealthHospital", hDAO.aHealthHospitals());

//Incomplete Demo
            request.setAttribute("iDemoAgeGroup", dDAO.iDemoAgeGroup());
            request.setAttribute("iDemoMarital", dDAO.iDemoMarital());
            request.setAttribute("iDemoHighest", dDAO.iDemoHighest());

//Incomplete Educ
            request.setAttribute("iEducPublicEnrollment", eDAO.iEducEnrollmentPublic());
            request.setAttribute("iEducPublicDirectory", eDAO.iEducPublicDirectory());
            request.setAttribute("iEducPrivateEnrollment", eDAO.iEducEnrollmentPrivate());
            request.setAttribute("iEducPrivateDirectory", eDAO.iEducPrivateDirectory());

//Incomplete Health
            request.setAttribute("iHealthNutritionalStatus", hDAO.iHealthNutritionalStatus());
            request.setAttribute("iHealthHospital", hDAO.iHealthHospitals());

//HOME   
            if (redirect.equalsIgnoreCase("home")) {
                ArrayList<TaskModel> arrayTask = taskDAO.getTaskUploaderStatus(year, chck.getPosition());
                request.setAttribute("tasks", arrayTask);
                request.setAttribute("page", "home");
                rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
                rd.forward(request, response);
            } else if (redirect.equalsIgnoreCase("homeIT")) {
                ArrayList<User> internalUsers = accountsDAO.getApprovedInternalUsers();
                ArrayList<User> externalUsers = accountsDAO.getApprovedExternalUsers();
                ArrayList<User> eApprove = accountsDAO.getUserForApprovalOthers();
                ArrayList<User> iApprove = accountsDAO.getUserForApprovalMember();
                int userReg = internalUsers.size() + externalUsers.size() + eApprove.size() + iApprove.size();
                request.setAttribute("page", "homeIT");
                request.setAttribute("userRegistrations", userReg);
                request.setAttribute("eApprove", eApprove.size());
                request.setAttribute("iApprove", iApprove.size());
                rd = request.getRequestDispatcher("/WEB-INF/home_IT.jsp");
                rd.forward(request, response);
            } else if (redirect.equalsIgnoreCase("homePDO")) {

                if (chck.getPosition().equals("Project Development Officer IV")) {
                    ArrayList<TaskModel> arrayTask = taskDAO.checkTaskHead(year, chck.getPosition(), "Health");
                    ArrayList<TaskModel> taskUploader = taskDAO.getTaskUploaderStatus(year, "Administrative Aide VI");

                    request.setAttribute("tasks", taskUploader);
                    request.setAttribute("tasksHead", arrayTask);
                } else if (chck.getPosition().equals("Project Development Officer III")) {
                    ArrayList<TaskModel> arrayTask = taskDAO.checkTaskHead(year, chck.getPosition(), "Education");
                    request.setAttribute("tasksHead", arrayTask);
                } else if (chck.getPosition().equals("Project Development Officer I")) {
                    ArrayList<TaskModel> arrayTask = taskDAO.checkTaskHead(year, chck.getPosition(), "Demographics");
                    request.setAttribute("tasksHead", arrayTask);
                }
                rd = request.getRequestDispatcher("/WEB-INF/home_PDO.jsp");
                rd.forward(request, response);
            } else if (redirect.equalsIgnoreCase("home_guest")) {
                //  request.setAttribute("", "");
                rd = request.getRequestDispatcher("/WEB-INF/home_others.jsp");
                rd.forward(request, response);
            } //APPROVALS
            else if (redirect.equalsIgnoreCase("approvals")) {
                ArrayList<User> externalUsers = new ArrayList<>();
                ArrayList<User> internalUsers = new ArrayList<>();
                externalUsers = accountsDAO.getUserForApprovalOthers();
                internalUsers = accountsDAO.getUserForApprovalMember();

                request.setAttribute("type", "default");
                request.setAttribute("internalUsers", internalUsers);
                request.setAttribute("externalUsers", externalUsers);
                request.setAttribute("notification", "none");
                request.setAttribute("page", "approvals");
                rd = request.getRequestDispatcher("/WEB-INF/JSPAccounts/approvals.jsp");
                rd.forward(request, response);
            } else if (redirect.equalsIgnoreCase("eapprovals")) {
                ArrayList<User> externalUsers = new ArrayList<>();
                ArrayList<User> internalUsers = new ArrayList<>();
                externalUsers = accountsDAO.getUserForApprovalOthers();
                internalUsers = accountsDAO.getUserForApprovalMember();

                request.setAttribute("type", "external");
                request.setAttribute("internalUsers", internalUsers);
                request.setAttribute("externalUsers", externalUsers);
                request.setAttribute("notification", "none");
                request.setAttribute("page", "approvals");
                rd = request.getRequestDispatcher("/WEB-INF/JSPAccounts/approvals.jsp");
                rd.forward(request, response);
            } else if (redirect.equalsIgnoreCase("iapprovals")) {
                ArrayList<User> externalUsers = new ArrayList<>();
                ArrayList<User> internalUsers = new ArrayList<>();
                externalUsers = accountsDAO.getUserForApprovalOthers();
                internalUsers = accountsDAO.getUserForApprovalMember();

                request.setAttribute("type", "internal");
                request.setAttribute("internalUsers", internalUsers);
                request.setAttribute("externalUsers", externalUsers);
                request.setAttribute("notification", "none");
                request.setAttribute("page", "approvals");
                rd = request.getRequestDispatcher("/WEB-INF/JSPAccounts/approvals.jsp");
                rd.forward(request, response);
            }//UPDATES
            else if (redirect.equalsIgnoreCase("updates")) {
                ArrayList<User> internalUsers = accountsDAO.getApprovedInternalUsers();
                ArrayList<User> externalUsers = accountsDAO.getApprovedExternalUsers();
                request.setAttribute("internalUsers", internalUsers);
                request.setAttribute("externalUsers", externalUsers);
                request.setAttribute("page", "updatesInternal");
                rd = request.getRequestDispatcher("/WEB-INF/JSPAccounts/updateAccounts.jsp");
                rd.forward(request, response);
            } else if (redirect.equalsIgnoreCase("reportsLibrary")) {

                rd = request.getRequestDispatcher("/WEB-INF/reportLibrary.jsp");
                rd.forward(request, response);
            } //analysis, create report , pubished reports
            else if (redirect.equalsIgnoreCase("SavedReports")) {
                rd = request.getRequestDispatcher("/ReportAccess?redirect=Saved&Navi=true");
                request.setAttribute("savedMessage", "none");
                rd.forward(request, response);
            } else if (redirect.equalsIgnoreCase("CreateReport")) {
                rd = request.getRequestDispatcher("/ReportAccess?redirect=createReport");
                rd.forward(request, response);
            } else if (redirect.equalsIgnoreCase("PublishedReports")) {
                request.setAttribute("savedMessage", "none");
                rd = request.getRequestDispatcher("/WEB-INF/JSPAnalysis/PublishedReports.jsp");
                rd.forward(request, response);
            } else if (redirect.equalsIgnoreCase("DataManipulation")) {
                request.setAttribute("type", "none");
                rd = request.getRequestDispatcher("/WEB-INF/JSPAnalysis/DataManipulation.jsp");
                rd.forward(request, response);
            } else if (redirect.equalsIgnoreCase("StandardManipulation")) {
                rd = request.getRequestDispatcher("/WEB-INF/JSPAnalysis/StandardManipulation.jsp");
                rd.forward(request, response);
            } //from notificaiton
            else if (redirect.equalsIgnoreCase("reportAgeGroup")) {
                request.setAttribute("reportSet", "ageGroup");
                rd = request.getRequestDispatcher("/WEB-INF/reportLibrary.jsp");
                rd.forward(request, response);
            } else if (redirect.equalsIgnoreCase("reportMarital")) {
                request.setAttribute("reportSet", "marital");
                rd = request.getRequestDispatcher("/WEB-INF/reportLibrary.jsp");
                rd.forward(request, response);
            } else if (redirect.equalsIgnoreCase("reportSchoolDirectory")) {
                String c = request.getParameter("classification");
                request.setAttribute("reportSet", "schoolDirectory");
                request.setAttribute("classification", c);
                rd = request.getRequestDispatcher("/WEB-INF/reportLibrary.jsp");
                rd.forward(request, response);
            } else if (redirect.equalsIgnoreCase("reportNutritional")) {
                request.setAttribute("reportSet", "nutritional");
                rd = request.getRequestDispatcher("/WEB-INF/reportLibrary.jsp");
                rd.forward(request, response);
            } else if (redirect.equalsIgnoreCase("reportEnrollment")) {
                String c = request.getParameter("classification");
                request.setAttribute("reportSet", "Enrollment");
                request.setAttribute("classification", c);
                rd = request.getRequestDispatcher("/WEB-INF/reportLibrary.jsp");
                rd.forward(request, response);
            } else if (redirect.equalsIgnoreCase("reportHighestCompleted")) {
                request.setAttribute("reportSet", "HighestCompleted");
                rd = request.getRequestDispatcher("/WEB-INF/reportLibrary.jsp");
                rd.forward(request, response);
            } else if (redirect.equalsIgnoreCase("reportHospital")) {
                request.setAttribute("reportSet", "Hospital");
                rd = request.getRequestDispatcher("/WEB-INF/reportLibrary.jsp");
                rd.forward(request, response);
            } //FORUMS
            else if (redirect.equalsIgnoreCase("forums")) {
                rd = request.getRequestDispatcher("/WEB-INF/home_others.jsp");
                rd.forward(request, response);
            } else if (redirect.equalsIgnoreCase("ReportSearchForum")) {
                String yearReport = request.getParameter("yearReport");
                String sectorReport = request.getParameter("sectorReport");
                String reportTitle = request.getParameter("reportTitle");

                request.setAttribute("savedMessage", "none");
                request.setAttribute("ReportSearch", "ReportSearch");
                request.setAttribute("yearReport", yearReport);
                request.setAttribute("sectorReport", sectorReport);
                request.setAttribute("reportTitle", reportTitle);
                rd = request.getRequestDispatcher("/WEB-INF/JSPAnalysis/PublishedReports.jsp");
                rd.forward(request, response);
            } else if (redirect.equalsIgnoreCase("viewReportPDO")) {
                String reportTitle = request.getParameter("reportTitle");
                String sectorReport = "none";
                
                if (chck.getPosition().equals("Project Development Officer IV")) {
                    sectorReport = "Health";

                } else if (chck.getPosition().equals("Project Development Officer III")) {
                    sectorReport = "Education";

                } else if (chck.getPosition().equals("Project Development Officer I")) {
                    sectorReport = "Demographics";
                }
                request.setAttribute("savedMessage", "none");
                request.setAttribute("ReportSearch", "ReportSearch");
                request.setAttribute("yearReport", String.valueOf(year));
                request.setAttribute("sectorReport", sectorReport);
                request.setAttribute("reportTitle", reportTitle);
                rd = request.getRequestDispatcher("/WEB-INF/JSPAnalysis/PublishedReports.jsp");
                rd.forward(request, response);
            } else {
                request.getRequestDispatcher("index.jsp").include(request, response);
            }
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(ServletAccess.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
