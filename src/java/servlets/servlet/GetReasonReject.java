/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package servlets.servlet;

import dao.RecordDAO;
import dao.TaskDAO;
import model.Record;
import model.TaskModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import model.accounts.User;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Gian Carlo Roxas
 * @author shermaine Sy
 * @author Geraldine Atayan
 *
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
     * @throws java.text.ParseException parse exception
     * @throws java.sql.SQLException sql exception
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String formID = request.getParameter("formid");
            RecordDAO recordDAO = new RecordDAO();
            Record record = new Record();
//IV - Health
//III - Education
//I - Demo
            int year = Calendar.getInstance().get(Calendar.YEAR);
            HttpSession session = request.getSession();
            User chck = (User) session.getAttribute("user");

            TaskModel newtaskModelUploader = new TaskModel();
            SimpleDateFormat sdf = new SimpleDateFormat("MMM. dd, yyyy");

            record = recordDAO.GetForReasons(Integer.parseInt(formID));
            TaskDAO taskDAO = new TaskDAO();
            ArrayList<TaskModel> taskModel = taskDAO.getTaskUploaderStatus(year, chck.getPosition());

            for (int i = 0; i < taskModel.size(); i++) {
                if (taskModel.get(i).getFormID() == record.getFormID()) {
                    newtaskModelUploader.setreportName(taskModel.get(i).getReportName());
                    newtaskModelUploader.setDuedate(taskModel.get(i).getDuedate());

                }
            }
            JSONObject obj = new JSONObject();
            try {
                obj.put("task", newtaskModelUploader.getReportName());
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
