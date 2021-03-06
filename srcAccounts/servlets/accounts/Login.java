/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package servlets.accounts;

import dao.accounts.Accounts;
import dao.DemoDashboard;
import dao.EducDashboard;
import dao.HealthDashboard;
import dao.TaskDAO;
import model.accounts.User;
import model.TaskModel;
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
 * @author Gian Carlo Roxas
 * @author shermaine Sy
 * @author Geraldine Atayan
 * 
 */

public class Login extends HttpServlet {

   /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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
                        ArrayList<TaskModel> arrayTask = taskDAO.checkTaskHead(year,user.getPosition(), "Health");
                         ArrayList<TaskModel> taskUploader = taskDAO.getTaskUploaderStatus(year,"Administrative Aide VI");
                        request.setAttribute("tasks", taskUploader);
                        request.setAttribute("tasksHead", arrayTask);
                        rd = context.getRequestDispatcher("/WEB-INF/home_PDO.jsp");
                    } else if (user.getPosition().equals("Project Development Officer III")) {
                        ArrayList<TaskModel> arrayTask = taskDAO.checkTaskHead(year, user.getPosition(),"Education");
                        request.setAttribute("tasksHead", arrayTask);
                        rd = context.getRequestDispatcher("/WEB-INF/home_PDO.jsp");
                    } else if (user.getPosition().equals("Project Development Officer I")) {
                        ArrayList<TaskModel> arrayTask = taskDAO.checkTaskHead(year, user.getPosition(),"Demographics");
                        request.setAttribute("tasksHead", arrayTask);
                        rd = context.getRequestDispatcher("/WEB-INF/home_PDO.jsp");
                    } else {
                        ArrayList<TaskModel> taskUploader = taskDAO.getTaskUploaderStatus(year,user.getPosition());
                        request.setAttribute("tasks", taskUploader);
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
