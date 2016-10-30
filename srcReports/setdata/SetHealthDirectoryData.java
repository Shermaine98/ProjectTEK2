/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package setdata;

import dao.RecordDAO;
import dao.charts.ListOfHospitalDAO;
import model.Record;
import model.health.DirectoryHealth;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import static java.util.logging.Level.SEVERE;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static java.lang.Integer.parseInt;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class SetHealthDirectoryData extends HttpServlet {

    private String privateClassification = "Private Hospital";
    private String publicClassification = "Government Hospital";
    
    /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String censusYear = request.getParameter("censusYear");
            
            JSONArray jarrayPrivateTable = new JSONArray();
            JSONArray jarrayPublicTable = new JSONArray();
            JSONArray jarrayPrivateTotal = new JSONArray();
            JSONArray jarrayPublicTotal = new JSONArray();
            JSONObject ObjectAll = new JSONObject();
          
            int formID = 90000000 + parseInt(censusYear);
            ListOfHospitalDAO hospitalDAO = new ListOfHospitalDAO();
            
            DirectoryHealth overallPrivHealth = hospitalDAO.retrieveOverAllHospitals(formID, privateClassification);
            DirectoryHealth overallPubHealth = hospitalDAO.retrieveOverAllHospitals(formID, publicClassification);
            
            ArrayList<DirectoryHealth> privHospitals = hospitalDAO.retrieveAllHospitals(formID, privateClassification);
            ArrayList<DirectoryHealth> pubHospitals = hospitalDAO.retrieveAllHospitals(formID, publicClassification);
            
            Record records = new RecordDAO().GetbyFormID(formID);
            
            JSONObject objPrivTotal = new JSONObject();
            try {
                objPrivTotal.put("totalPrivateClassification", overallPrivHealth.getClassification() + "s");
                objPrivTotal.put("totalPrivateDoctors", overallPrivHealth.getNumberOfDoctor());
                objPrivTotal.put("totalPrivateNurses", overallPrivHealth.getNumberOfNurses());
                objPrivTotal.put("totalPrivateMidwives", overallPrivHealth.getNumberOfMidwives());
                objPrivTotal.put("totalPrivateBeds", overallPrivHealth.getNumberOfBeds());
            } catch (JSONException ex) {
                getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
            }
            
            JSONObject objPubTotal = new JSONObject();
            try {
                objPubTotal.put("totalPublicClassification", overallPubHealth.getClassification() + "s");
                objPubTotal.put("totalPublicDoctors", overallPubHealth.getNumberOfDoctor());
                objPubTotal.put("totalPublicNurses", overallPubHealth.getNumberOfNurses());
                objPubTotal.put("totalPublicMidwives", overallPubHealth.getNumberOfMidwives());
                objPubTotal.put("totalPublicBeds", overallPubHealth.getNumberOfBeds());
            } catch (JSONException ex) {
                getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
            }
            
            
            for(int i = 0; i < privHospitals.size(); i++){
                JSONObject objArrPrivateDirectory = new JSONObject();
                try {
                    objArrPrivateDirectory.put("privateDirectoryYear", privHospitals.get(i).getYear());
                    objArrPrivateDirectory.put("privateDirectoryClassification", privateClassification);
                    objArrPrivateDirectory.put("privateDirectoryName", privHospitals.get(i).getHospitalName());
                    objArrPrivateDirectory.put("privateDirectoryAddress", privHospitals.get(i).getAddresss());
                    objArrPrivateDirectory.put("privateDirectoryTelephone", privHospitals.get(i).getTelephone());
                    objArrPrivateDirectory.put("privateDirectoryDoctors", privHospitals.get(i).getNumberOfDoctor());
                    objArrPrivateDirectory.put("privateDirectoryNurses", privHospitals.get(i).getNumberOfNurses());
                    objArrPrivateDirectory.put("privateDirectoryMidwives", privHospitals.get(i).getNumberOfMidwives());
                    objArrPrivateDirectory.put("privateDirectoryBeds", privHospitals.get(i).getNumberOfBeds());
                    objArrPrivateDirectory.put("privateDirectoryAccredited", privHospitals.get(i).isAccreditation());
                    jarrayPrivateTable.put(objArrPrivateDirectory);
                } catch (JSONException ex) {
                    getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            for(int i = 0; i < pubHospitals.size(); i++){
                JSONObject objArrPublicDirectory = new JSONObject();
                try {
                    objArrPublicDirectory.put("publicDirectoryYear", pubHospitals.get(i).getYear());
                    objArrPublicDirectory.put("publicDirectoryName", pubHospitals.get(i).getHospitalName());
                    objArrPublicDirectory.put("publicDirectoryClassification", publicClassification);
                    objArrPublicDirectory.put("publicDirectoryAddress", pubHospitals.get(i).getAddresss());
                    objArrPublicDirectory.put("publicDirectoryTelephone", pubHospitals.get(i).getTelephone());
                    objArrPublicDirectory.put("publicDirectoryDoctors", pubHospitals.get(i).getNumberOfDoctor());
                    objArrPublicDirectory.put("publicDirectoryNurses", pubHospitals.get(i).getNumberOfNurses());
                    objArrPublicDirectory.put("publicDirectoryMidwives", pubHospitals.get(i).getNumberOfMidwives());
                    objArrPublicDirectory.put("publicDirectoryBeds", pubHospitals.get(i).getNumberOfBeds());
                    objArrPublicDirectory.put("publicDirectoryAccredited", pubHospitals.get(i).isAccreditation());
                    jarrayPublicTable.put(objArrPublicDirectory);
                } catch (JSONException ex) {
                    getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }

            ObjectAll.put("privTotal", objPrivTotal);
            ObjectAll.put("pubTotal", objPubTotal);
            ObjectAll.put("privTable", jarrayPrivateTable);
            ObjectAll.put("pubTable", jarrayPublicTable);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write("[" + ObjectAll.toString() + "]");
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
            processRequest(request, response);
        } catch (ParseException ex) {
            getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
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
            getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
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
