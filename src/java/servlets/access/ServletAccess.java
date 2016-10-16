package servlets.access;

import dao.accounts.Accounts;
import dao.DemoDashboard;
import dao.EducDashboard;
import dao.HealthDashboard;
import dao.TaskDAO;
import model.accounts.User;
import model.TaskModelHead;
import model.TaskModelUploader;
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
 * @author Atayan
 * @author Roxas
 * @author Sy
 *
 */
public class ServletAccess extends BaseServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
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
                ArrayList<TaskModelUploader> arrayTask = taskDAO.checkTaskUploader(yearInString);
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
                HttpSession session = request.getSession();
                User chck = (User) session.getAttribute("user");

                if (chck.getPosition().equals("Project Development Officer IV")) {
                    ArrayList<TaskModelHead> arrayTask = taskDAO.checkTaskHead(yearInString, "Health");
                    ArrayList<TaskModelUploader> validated = taskDAO.getUploadedValidated(yearInString);
                    ArrayList<TaskModelUploader> approved = taskDAO.getUploadedApprovedReject(yearInString);
                    ArrayList<TaskModelUploader> notUploaded = taskDAO.getNotUploaded(yearInString);

                    request.setAttribute("notUploaded", notUploaded);
                    request.setAttribute("validated", validated);
                    request.setAttribute("approved", approved);
                    request.setAttribute("tasksHead", arrayTask);
                } else if (chck.getPosition().equals("Project Development Officer III")) {
                    ArrayList<TaskModelHead> arrayTask = taskDAO.checkTaskHead(yearInString, "Education");
                    request.setAttribute("tasksHead", arrayTask);
                } else if (chck.getPosition().equals("Project Development Officer I")) {
                    ArrayList<TaskModelHead> arrayTask = taskDAO.checkTaskHead(yearInString, "Demographics");
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

                request.setAttribute("internalUsers", internalUsers);
                request.setAttribute("externalUsers", externalUsers);
                request.setAttribute("notification", "none");
                request.setAttribute("page", "approvals");
                rd = request.getRequestDispatcher("/WEB-INF/JSPAccounts/approvals.jsp");
                rd.forward(request, response);
            } //UPDATES
            else if (redirect.equalsIgnoreCase("updates")) {
                ArrayList<User> internalUsers = accountsDAO.getApprovedInternalUsers();
                ArrayList<User> externalUsers = accountsDAO.getApprovedExternalUsers();
                request.setAttribute("internalUsers", internalUsers);
                request.setAttribute("externalUsers", externalUsers);
                request.setAttribute("page", "updatesInternal");
                rd = request.getRequestDispatcher("/WEB-INF/JSPAccounts/updateAccounts.jsp");
                rd.forward(request, response);
            } else if (redirect.equalsIgnoreCase("reportsLibrary")) {
                request.setAttribute("reportSet", "none");
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
            } else {
                request.getRequestDispatcher("index.jsp").include(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServletAccess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ServletAccess.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
