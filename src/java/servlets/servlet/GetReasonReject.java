/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.servlet;

import DAO.recordDAO;
import Model.record;
import Model.taskModelUploader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Shermaine
 */
public class GetReasonReject extends HttpServlet {

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

            String formID = request.getParameter("formid");
            recordDAO recordDAO = new recordDAO();
            record record = new record();
//IV - Health
//III - Education
//I - Demo
            taskModelUploader newtaskModelUploader = new taskModelUploader();

            record = recordDAO.GetForReasons(Integer.parseInt(formID));
            taskModelUploader taskModelUploader = new taskModelUploader();
            taskModelUploader.taskModel(String.valueOf(record.getCensusYear()));
            taskModelUploader.getTaskModel();
            for (int i = 0; i < taskModelUploader.getTaskModel().size(); i++) {
                if (taskModelUploader.getTaskModel().get(i).getFormID() == record.getFormID()) {
                    newtaskModelUploader.setTask(taskModelUploader.getTaskModel().get(i).getTask());
                    newtaskModelUploader.setDuedate(taskModelUploader.getTaskModel().get(i).getDuedate());

                }
            }
            JSONObject obj = new JSONObject();
            try {
                obj.put("task", newtaskModelUploader.getTask());
                obj.put("dueDate", newtaskModelUploader.getDuedate());
                obj.put("cencusYear", record.getCensusYear());
                obj.put("reason", record.getReasons());
                obj.put("uplodedBy", record.getUploadedByByName());
                obj.put("rejectedBy", record.getApprovedByName());
            } catch (JSONException ex) {
                Logger.getLogger(NotificationsPusher.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            out.print(obj);
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
