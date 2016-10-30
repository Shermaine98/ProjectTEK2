/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */


package servlet.setdata;

import dao.RecordDAO;
import dao.charts.MaritalStatusChart;
import dao.demo.MaritalStatusDAO;
import model.Record;
import model.demo.MaritalStatus;
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
public class SetMaritalDataServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException parse exception
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String censusYear = request.getParameter("censusYear");
            JSONArray jarrayTable = new JSONArray();
            JSONArray jarrayMaleBarangays = new JSONArray();
            JSONArray jarrayFemaleBarangays = new JSONArray();
            JSONArray jarrayMaleAgeGroup = new JSONArray();
            JSONArray jarrayFemaleAgeGroup = new JSONArray();
            JSONObject objTotal = new JSONObject();
            JSONObject ObjectAll = new JSONObject();
            MaritalStatusChart chart = new MaritalStatusChart();

          
                int formID = 300000000 + parseInt(censusYear);
                ArrayList<MaritalStatus> MaritalTable = new MaritalStatusDAO().ViewMaritalStatusFormID(formID);
                MaritalStatus maritalStatus = chart.retrieveOverAllMaritalStatus(formID);
                ArrayList<MaritalStatus> maleBarangays = chart.retrieveMaleBarangays(formID);
                ArrayList<MaritalStatus> femaleBarangays = chart.retrieveFemaleBarangays(formID);
                ArrayList<MaritalStatus> maleAgeGroup = chart.retrieveMaleAgeGroup(formID);
                ArrayList<MaritalStatus> femaleAgeGroup = chart.retrieveFemaleAgeGroup(formID);

                JSONObject objArrMaritalStatus = new JSONObject();
                try {
                    objArrMaritalStatus.put("maritalStatusSingle", maritalStatus.getSingle());
                    objArrMaritalStatus.put("maritalStatusCommonLaw", maritalStatus.getCommonLawLiveIn());
                    objArrMaritalStatus.put("maritalStatusDivorced", maritalStatus.getDivorcedSeparated());
                    objArrMaritalStatus.put("maritalStatusMaried", maritalStatus.getMarried());
                    objArrMaritalStatus.put("maritalStatusUnknown", maritalStatus.getUnknown());
                    objArrMaritalStatus.put("maritalStatusWidowed", maritalStatus.getWidowed());
                } catch (JSONException ex) {
                    getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                }
                
               
                for (int i = 0; i < maleBarangays.size(); i++) {
                    JSONObject objarrMaleBarangays = new JSONObject();
                    try {
                        objarrMaleBarangays.put("arrMaleLocation", maleBarangays.get(i).getLocation());
                        objarrMaleBarangays.put("arrMaleSex", maleBarangays.get(i).getSex());
                        objarrMaleBarangays.put("arrMaleSingle", maleBarangays.get(i).getSingle());
                        objarrMaleBarangays.put("arrMaleMarried", maleBarangays.get(i).getMarried());
                        objarrMaleBarangays.put("arrMaleWidowed", maleBarangays.get(i).getWidowed());
                        objarrMaleBarangays.put("arrMaleSeparated", maleBarangays.get(i).getDivorcedSeparated());
                        objarrMaleBarangays.put("arrMaleLiveIn", maleBarangays.get(i).getCommonLawLiveIn());
                        objarrMaleBarangays.put("arrMaleUnknown", maleBarangays.get(i).getUnknown());
                        jarrayMaleBarangays.put(objarrMaleBarangays);
                    } catch (JSONException ex) {
                        getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                    }
                }
                
                for (int i = 0; i < femaleBarangays.size(); i++) {
                    JSONObject objarrFemaleBarangays = new JSONObject();
                    try {
                        objarrFemaleBarangays.put("arrFemaleLocation", femaleBarangays.get(i).getLocation());
                        objarrFemaleBarangays.put("arrFemaleSex", femaleBarangays.get(i).getSex());
                        objarrFemaleBarangays.put("arrFemaleSingle", femaleBarangays.get(i).getSingle());
                        objarrFemaleBarangays.put("arrFemaleMarried", femaleBarangays.get(i).getMarried());
                        objarrFemaleBarangays.put("arrFemaleWidowed", femaleBarangays.get(i).getWidowed());
                        objarrFemaleBarangays.put("arrFemaleSeparated", femaleBarangays.get(i).getDivorcedSeparated());
                        objarrFemaleBarangays.put("arrFemaleLiveIn", femaleBarangays.get(i).getCommonLawLiveIn());
                        objarrFemaleBarangays.put("arrFemaleUnknown", femaleBarangays.get(i).getUnknown());
                        jarrayFemaleBarangays.put(objarrFemaleBarangays);
                    } catch (JSONException ex) {
                        getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                    }
                }
                
                for (int i = 0; i < maleAgeGroup.size(); i++) {
                    JSONObject objarrMaleAgeGroup = new JSONObject();
                    try {
                        objarrMaleAgeGroup.put("arrMaleAgeGroupLocation", maleAgeGroup.get(i).getLocation());
                        objarrMaleAgeGroup.put("arrMaleAgeGroupAgeGroup", maleAgeGroup.get(i).getAgeGroup());
                        objarrMaleAgeGroup.put("arrMaleAgeGroupSex", maleAgeGroup.get(i).getSex());
                        objarrMaleAgeGroup.put("arrMaleAgeGroupSingle", maleAgeGroup.get(i).getSingle());
                        objarrMaleAgeGroup.put("arrMaleAgeGroupMarried", maleAgeGroup.get(i).getMarried());
                        objarrMaleAgeGroup.put("arrMaleAgeGroupWidowed", maleAgeGroup.get(i).getWidowed());
                        objarrMaleAgeGroup.put("arrMaleAgeGroupSeparated", maleAgeGroup.get(i).getDivorcedSeparated());
                        objarrMaleAgeGroup.put("arrMaleAgeGroupLiveIn", maleAgeGroup.get(i).getCommonLawLiveIn());
                        objarrMaleAgeGroup.put("arrMaleAgeGroupUnknown", maleAgeGroup.get(i).getUnknown());
                        jarrayMaleAgeGroup.put(objarrMaleAgeGroup);
                    } catch (JSONException ex) {
                        getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                    }
                }
                
                for (int i = 0; i < femaleAgeGroup.size(); i++) {
                    JSONObject objarrFemaleAgeGroup = new JSONObject();
                    try {
                        objarrFemaleAgeGroup.put("arrFemaleAgeGroupLocation", femaleAgeGroup.get(i).getLocation());
                        objarrFemaleAgeGroup.put("arrFemaleAgeGroupAgeGroup", femaleAgeGroup.get(i).getAgeGroup());
                        objarrFemaleAgeGroup.put("arrFemaleAgeGroupFemaleSex", femaleAgeGroup.get(i).getSex());
                        objarrFemaleAgeGroup.put("arrFemaleAgeGroupFemaleSingle", femaleAgeGroup.get(i).getSingle());
                        objarrFemaleAgeGroup.put("arrFemaleAgeGroupFemaleMarried", femaleAgeGroup.get(i).getMarried());
                        objarrFemaleAgeGroup.put("arrFemaleAgeGroupFemaleWidowed", femaleAgeGroup.get(i).getWidowed());
                        objarrFemaleAgeGroup.put("arrFemaleAgeGroupFemaleSeparated", femaleAgeGroup.get(i).getDivorcedSeparated());
                        objarrFemaleAgeGroup.put("arrFemaleAgeGroupFemaleLiveIn", femaleAgeGroup.get(i).getCommonLawLiveIn());
                        objarrFemaleAgeGroup.put("arrFemaleAgeGroupFemaleUnknown", femaleAgeGroup.get(i).getUnknown());
                        jarrayFemaleAgeGroup.put(objarrFemaleAgeGroup);
                    } catch (JSONException ex) {
                        getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                    }
                }
                

                Record records = new RecordDAO().GetbyFormID(formID);
                
                for (int i = 0; i < MaritalTable.size(); i++) {
                    JSONObject objTable = new JSONObject();
                    try {
                        objTable.put("location", MaritalTable.get(i).getLocation());
                        objTable.put("sex", MaritalTable.get(i).getSex());
                        objTable.put("ageGroup", MaritalTable.get(i).getAgeGroup());
                        objTable.put("single", MaritalTable.get(i).getSingle());
                        objTable.put("married", MaritalTable.get(i).getMarried());
                        objTable.put("widowed", MaritalTable.get(i).getWidowed());
                        objTable.put("divorced", MaritalTable.get(i).getDivorcedSeparated());
                        objTable.put("liveIn", MaritalTable.get(i).getCommonLawLiveIn());
                        objTable.put("unknown", MaritalTable.get(i).getUnknown());
                        objTable.put("total", MaritalTable.get(i).getTotal());
                        objTable.put("UploadedBy", records.getUploadedByByName());
                        objTable.put("ApprovedBy", records.getApprovedByName());
                        jarrayTable.put(objTable);
                    } catch (JSONException ex) {
                        getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                    }
                }

            
            ObjectAll.put("Total", objArrMaritalStatus);
            ObjectAll.put("maleBarangay", jarrayMaleBarangays);
            ObjectAll.put("femaleBarangay", jarrayFemaleBarangays);
            ObjectAll.put("maleAgeGroup", jarrayMaleAgeGroup);
            ObjectAll.put("femaleAgeGroup", jarrayFemaleAgeGroup);
            ObjectAll.put("maritalTable", jarrayTable);
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
