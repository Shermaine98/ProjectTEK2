/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.accounts;

import dao.accounts.Accounts;
import dao.DemoDashboard;
import dao.EducDashboard;
import dao.HealthDashboard;
import dao.TaskDAO;
import model.accounts.User;
import model.TaskModelHead;
import model.TaskModelUploader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Geraldine Atayan
 */
public class Login extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
      
        try (PrintWriter out = response.getWriter()) {
            
            
            String username = request.getParameter("username");
            String pass = request.getParameter("password");
            
            HttpSession session = request.getSession();
            ServletContext context = getServletContext();
            
             RequestDispatcher rd = context.getRequestDispatcher("/index.jsp");
//<editor-fold defaultstate="collapsed" desc="comment">

            Accounts dao = new Accounts();
            User user = new User();
            TaskDAO taskDAO = new TaskDAO();
//</editor-fold>
            
            
//<editor-fold defaultstate="collapsed" desc="comment">
            
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            String yearInString = String.valueOf(year);

 //</editor-fold>           

 //<editor-fold defaultstate="collapsed" desc="comment">

            
            if (dao.authenticate(username, pass)) {
                try {
                    user = dao.getUser(username, pass);
                } catch (ParseException ex) {
                    getLogger(Login.class.getName()).log(SEVERE, null, ex);
                }
               
                if (user.getDivision().equalsIgnoreCase("Social Development Planning Division")) {
                    DemoDashboard dDAO = new DemoDashboard();
                    HealthDashboard hDAO = new HealthDashboard();
                    EducDashboard eDAO = new EducDashboard();

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

           
//IV - Health
//II - Education
//I - Demo
                    if (user.getPosition().equals("Project Development Officer IV")) {
                        ArrayList<TaskModelHead> arrayTask = taskDAO.checkTaskHead(yearInString, "Health");
                        ArrayList<TaskModelUploader> validated = taskDAO.getUploadedValidated(yearInString);
                        ArrayList<TaskModelUploader> approved = taskDAO.getUploadedApprovedReject(yearInString);
                        ArrayList<TaskModelUploader> notUploaded = taskDAO.getNotUploaded(yearInString);

                        request.setAttribute("notUploaded", notUploaded);
                        request.setAttribute("validated", validated);
                        request.setAttribute("approved", approved);
                        request.setAttribute("tasksHead", arrayTask);
                        rd = context.getRequestDispatcher("/WEB-INF/home_PDO.jsp");
                    } else if (user.getPosition().equals("Project Development Officer III")) {
                        ArrayList<TaskModelHead> arrayTask = taskDAO.checkTaskHead(yearInString, "Education");
                        request.setAttribute("tasksHead", arrayTask);
                        rd = context.getRequestDispatcher("/WEB-INF/home_PDO.jsp");
                    } else if (user.getPosition().equals("Project Development Officer I")) {
                        ArrayList<TaskModelHead> arrayTask = taskDAO.checkTaskHead(yearInString, "Demographics");
                        request.setAttribute("tasksHead", arrayTask);
                        rd = context.getRequestDispatcher("/WEB-INF/home_PDO.jsp");
                    } else {
                        ArrayList<TaskModelUploader> arrayTask = taskDAO.checkTaskUploader(yearInString);
                        request.setAttribute("tasks", arrayTask);
                        rd = context.getRequestDispatcher("/WEB-INF/home.jsp");
                    }
                } else if (user.getDivision().equalsIgnoreCase("IT")) {
                    if (user.getPosition().equals("IT Admin")) {
                        ArrayList<User> internalUsers = dao.getApprovedInternalUsers();
                        ArrayList<User> externalUsers = dao.getApprovedExternalUsers();
                        ArrayList<User> eApprove = dao.getUserForApprovalOthers();
                        ArrayList<User> iApprove = dao.getUserForApprovalMember();
                        int userReg = internalUsers.size() + externalUsers.size() + eApprove.size() + iApprove.size();
                        request.setAttribute("page", "homeIT");
                        request.setAttribute("userRegistrations", userReg);
                        request.setAttribute("eApprove", eApprove.size());
                        request.setAttribute("iApprove", iApprove.size());
                    }
                    rd = context.getRequestDispatcher("/WEB-INF/home_IT.jsp");
                } else if (user.getDivision().equalsIgnoreCase("Others")) {
                    rd = context.getRequestDispatcher("/WEB-INF/home_others.jsp");
                } 
                 session.setAttribute("user", user);
                 session.setAttribute("successful", "successful");
                 rd.forward(request, response);
            
            } else {
                request.getRequestDispatcher("/index.jsp").include(request, response);
                out.print("Sorry, username or password error!");
            }
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
