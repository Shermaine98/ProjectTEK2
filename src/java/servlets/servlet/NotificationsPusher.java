/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.servlet;

import DAO.notificationDAO;
import Model.taskModelUploader;
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
 * @author Shermaine
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
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            ArrayList<taskModelUploader> notification = new ArrayList<taskModelUploader>();
            String position = request.getParameter("position");
             int year = Calendar.getInstance().get(Calendar.YEAR);
    //IV - Health
//III - Education
//I - Demo
            notificationDAO notificationDAO = new notificationDAO();
            if (position.equals("IT Admin")) {

            } else if (position.equalsIgnoreCase("Project Development Officer IV")) {
                ArrayList<taskModelUploader> temp = new ArrayList<taskModelUploader>();
                temp = notificationDAO.NotificationApprovalRecord(String.valueOf(year));
                for (taskModelUploader temp1 : temp) {
                    if (temp1.getReportType().equalsIgnoreCase("Health")) {
                        notification.add(temp1);
                    }
                }
            } else if (position.equalsIgnoreCase("Project Development Officer I")) {
                ArrayList<taskModelUploader> temp = new ArrayList<taskModelUploader>();
                temp = notificationDAO.NotificationApprovalRecord(String.valueOf(year));
                for (taskModelUploader temp1 : temp) {
                    if (temp1.getReportType().equalsIgnoreCase("Demographics")) {
                        notification.add(temp1);
                    }
                }
            } else if (position.equalsIgnoreCase("Project Development Officer III")) {
                ArrayList<taskModelUploader> temp = new ArrayList<taskModelUploader>();
                temp = notificationDAO.NotificationApprovalRecord(String.valueOf(year));
                for (taskModelUploader temp1 : temp) {
                    if (temp1.getReportType().equalsIgnoreCase("Education")) {
                        notification.add(temp1);
                    }
                }
            } else {
                ArrayList<taskModelUploader> rejected = new ArrayList<taskModelUploader>();
                ArrayList<taskModelUploader> approved = new ArrayList<taskModelUploader>();
                approved = notificationDAO.NotificationUploder(String.valueOf(year));
                rejected = notificationDAO.NotificationRejected(String.valueOf(year));
                notification.addAll(approved);
                notification.addAll(rejected);

            }

            JSONArray array = new JSONArray();
            for (int i = 0; i < notification.size(); i++) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("task", notification.get(i).getTask());
                    obj.put("time", notification.get(i).getTimeStampString());
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
