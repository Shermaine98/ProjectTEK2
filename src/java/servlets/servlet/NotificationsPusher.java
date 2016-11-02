/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package servlets.servlet;

import dao.NotificationDAO;
import model.TaskModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Gian Carlo Roxas
 * @author shermaine Sy
 * @author Geraldine Atayan
 *
 */
public class NotificationsPusher extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException parse exception
     * @throws java.sql.SQLException sql exception
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ArrayList<TaskModel> notification = new ArrayList<TaskModel>();
            String position = request.getParameter("position");
            int year = Calendar.getInstance().get(Calendar.YEAR);
            //IV - Health
//III - Education
//I - Demo
            NotificationDAO notificationDAO = new NotificationDAO();
            if (position.equalsIgnoreCase("Project Development Officer IV")) {
                ArrayList<TaskModel> ForApproval = new ArrayList<>();
                ForApproval = notificationDAO.NotificationApprovalRecord(year, "Health");
                ArrayList<TaskModel> delayed = new ArrayList<>();
                ArrayList<TaskModel> pending = new ArrayList<>();
                delayed = notificationDAO.NotificationAlertDelayedHead(year, position, "Health");
                pending = notificationDAO.NotificationAlertsPendingHead(year, position, "Health");
                notification.addAll(delayed);
                notification.addAll(pending);
                notification.addAll(ForApproval);
            } else if (position.equalsIgnoreCase("Project Development Officer I")) {
                ArrayList<TaskModel> ForApproval = new ArrayList<>();
                ForApproval = notificationDAO.NotificationApprovalRecord(year, "Demographics");
                ArrayList<TaskModel> delayed = new ArrayList<>();
                ArrayList<TaskModel> pending = new ArrayList<>();
                delayed = notificationDAO.NotificationAlertDelayedHead(year, position, "Demographics");
                pending = notificationDAO.NotificationAlertsPendingHead(year, position, "Demographics");
                notification.addAll(delayed);
                notification.addAll(pending);
                notification.addAll(ForApproval);
            } else if (position.equalsIgnoreCase("Project Development Officer III")) {

                ArrayList<TaskModel> ForApproval = new ArrayList<>();
                ForApproval = notificationDAO.NotificationApprovalRecord(year, "Education");
                ArrayList<TaskModel> delayed = new ArrayList<>();
                ArrayList<TaskModel> pending = new ArrayList<>();
                delayed = notificationDAO.NotificationAlertDelayedHead(year, position, "Education");
                pending = notificationDAO.NotificationAlertsPendingHead(year, position, "Education");
                notification.addAll(delayed);
                notification.addAll(pending);
                notification.addAll(ForApproval);
            } else if (position.equalsIgnoreCase("Administrative Aide VI")) {
                ArrayList<TaskModel> approvalsDone = new ArrayList<>();
                approvalsDone = notificationDAO.NotificationUploder(year, position);
                ArrayList<TaskModel> rejected = new ArrayList<>();
                rejected = notificationDAO.NotificationRejected(year, position);
                ArrayList<TaskModel> delayed = new ArrayList<>();
                ArrayList<TaskModel> pending = new ArrayList<>();
                delayed = notificationDAO.NotificationAlertDelayedUploader(year, position);
                pending = notificationDAO.NotificationAlertsPendingUploader(year, position);
                notification.addAll(delayed);
                notification.addAll(pending);
                notification.addAll(approvalsDone);
                notification.addAll(rejected);
            }

            JSONArray array = new JSONArray();
            for (int i = 0; i < notification.size(); i++) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("task", notification.get(i).getReportName());
                    obj.put("time", notification.get(i).getTimeStamp());
                    obj.put("name", notification.get(i).getName());
                    obj.put("status", notification.get(i).getStatus());
                    obj.put("formID", notification.get(i).getFormID());

                    array.put(obj);
                } catch (JSONException ex) {
                    Logger.getLogger(NotificationsPusher.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            out.print(array);
            out.flush();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            try {
                processRequest(request, response);
            } catch (ParseException ex) {
                Logger.getLogger(NotificationsPusher.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationsPusher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(NotificationsPusher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NotificationsPusher.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
