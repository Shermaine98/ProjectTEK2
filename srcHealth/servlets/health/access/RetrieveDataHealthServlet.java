/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package servlets.health.access;

import dao.RecordDAO;
import dao.health.NutritionalStatusDAO;
import dao.health.DirectoryHospitalDAO;
import model.GlobalRecords;
import model.Record;
import model.health.DirectoryHealth;
import servlets.servlet.BaseServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */

public class RetrieveDataHealthServlet extends BaseServlet {

     /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException servlet exception
     * @throws IOException servlet IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String redirect = request.getParameter("redirect");
        String saveToDb = (String) request.getAttribute("saveToDB");
        RequestDispatcher rd = null;
        RecordDAO recordDAO = new RecordDAO();

        //Health       
        if (redirect.equalsIgnoreCase("percentageDist")) {
            NutritionalStatusDAO NutritionalStatusDAO = new NutritionalStatusDAO();
            ArrayList<GlobalRecords> ErrRecords = new ArrayList<>();
            ArrayList<Record> records = new ArrayList<>();
            try {
                ErrRecords = NutritionalStatusDAO.getValidationFalse();
                records = recordDAO.GetForApproval(800000000, 899999999);
            } catch (ParseException ex) {
                getLogger(RetrieveDataHealthServlet.class.getName()).log(SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(RetrieveDataHealthServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (saveToDb != null) {
                request.setAttribute("saveToDB", saveToDb);
                request.setAttribute("page", "percentageDist");
            } else {
                request.setAttribute("saveToDB", "none");
                request.setAttribute("page", "percentageDist");
            }

            request.setAttribute("validatedRecords", records);
            request.setAttribute("IncompleteRecords", ErrRecords);
            rd = request.getRequestDispatcher("/WEB-INF/JSPHealth/hPercentageDist.jsp");
            rd.forward(request, response);

        } else if (redirect.equalsIgnoreCase("directoryHosptial")) {
            DirectoryHospitalDAO directoryHospitalDAO = new DirectoryHospitalDAO();
            ArrayList<DirectoryHealth> directoryHealth = new ArrayList<DirectoryHealth>();
            try {
                directoryHealth = directoryHospitalDAO.ViewDirectoryHospitalRecent();
            } catch (ParseException ex) {
                Logger.getLogger(RetrieveDataHealthServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(RetrieveDataHealthServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
             if (saveToDb != null) {
                request.setAttribute("saveToDB", saveToDb);
                request.setAttribute("page", "directoryhospital");
            } else {
                request.setAttribute("saveToDB", "none");
                request.setAttribute("page", "directoryhospital");
            }

            
            request.setAttribute("directoryHealth", directoryHealth);
            rd = request.getRequestDispatcher("/WEB-INF/JSPHealth/hospitalDirectory.jsp");
            rd.forward(request, response);
        } //approval
        else if (redirect.equalsIgnoreCase("health_approval")) {
            ArrayList<Record> nutrition = new ArrayList<>();
            try {
                nutrition = recordDAO.GetForApproval(800000000, 899999999);
            } catch (ParseException ex) {
                Logger.getLogger(RetrieveDataHealthServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.setAttribute("page", "approvalAdmin");
            request.setAttribute("message", "none");
            request.setAttribute("NutritionalStatus", nutrition);
            
            ArrayList<Record> directory = new ArrayList<>();
            try {
                directory = recordDAO.GetForApproval(90000000, 99999999);
            } catch (ParseException ex) {
                Logger.getLogger(RetrieveDataHealthServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.setAttribute("message", "none");
            request.setAttribute("directory", directory);
            
            request.setAttribute("subject", "");
            rd = request.getRequestDispatcher("/WEB-INF/JSPApproval/health.jsp");
            rd.forward(request, response);
        } else if (redirect.contains("approval_")) {
            if (redirect.equalsIgnoreCase("approval_nutritional")) {
                request.setAttribute("subject", "nutritional");
            }

            ArrayList<Record> nutrition = new ArrayList<>();
            try {
                nutrition = recordDAO.GetForApproval(800000000, 899999999);
            } catch (ParseException ex) {
                Logger.getLogger(RetrieveDataHealthServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.setAttribute("page", "approvalAdmin");
            request.setAttribute("message", "none");
            request.setAttribute("NutritionalStatus", nutrition);
            rd = request.getRequestDispatcher("/WEB-INF/JSPApproval/health.jsp");
            rd.forward(request, response);
        }
    }
}
