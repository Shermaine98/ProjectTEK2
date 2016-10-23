/*
 *  Copyright (C) ProjectTEK - DLSU CCS 2016
 *  All right Reserved   * 
 */

package servlet.setdata;

import dao.charts.ByAgeGroupChart;
import dao.demo.ByAgeGroupSexDAO;
import dao.RecordDAO;
import model.demo.ByAgeGroupSex;
import model.Record;
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
public class SetDataServlet extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String censusYear = request.getParameter("censusYear");
            JSONArray jarrayTable = new JSONArray();
            JSONArray jarrByAgeGroup = new JSONArray();
            JSONArray jarrayChartTotalMF = new JSONArray();
            JSONArray jarrayTotalAgeGroupSex = new JSONArray();
            JSONArray jarrayAgeGroupPerBarangay = new JSONArray();
            JSONObject objTotal = new JSONObject();
            JSONObject ObjectAll = new JSONObject();
            ByAgeGroupChart chart = new ByAgeGroupChart();

          
                int formID = 200000000 + parseInt(censusYear);
                ArrayList<ByAgeGroupSex> ByAgeGroupSexTable = new ByAgeGroupSexDAO().ViewByAgeGroupSexFormID(formID);
                ByAgeGroupSex byAgeGroupTotal = chart.retrieveTotalFemale(formID);
                ArrayList<ByAgeGroupSex> arrByAgeGroup = chart.retrieveByAgeGroupSex(formID);
                ArrayList<ByAgeGroupSex> arrTotalMF = chart.retrieveTotalMaleFemalePerBgy(formID);
                ArrayList<ByAgeGroupSex> arrTotalAgeGroup = chart.retrieveAgeGroupSex(formID);
                ArrayList<ByAgeGroupSex> arrAgeGroupPerBarangay = chart.retrieveAgeGroupPerBarangay(formID);

                try {
                    objTotal.put("TotalBarangay", byAgeGroupTotal.getBarangay());
                    objTotal.put("TotalMale", byAgeGroupTotal.getMaleCount());
                    objTotal.put("TotalFemale", byAgeGroupTotal.getFemaleCount());
                } catch (JSONException ex) {
                    getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                }

                for (int i = 0; i < arrByAgeGroup.size(); i++) {
                    JSONObject objArrByAgeGroup = new JSONObject();
                    try {
                        objArrByAgeGroup.put("arrByAgeGrouplocation", arrByAgeGroup.get(i).getBarangay());
                        objArrByAgeGroup.put("arrByAgeGroupAgeGroup", arrByAgeGroup.get(i).getAgeGroup());
                        objArrByAgeGroup.put("arrByAgeGroupBothSexes", arrByAgeGroup.get(i).getBothSex());
                        objArrByAgeGroup.put("arrByAgeGroupMale", arrByAgeGroup.get(i).getMaleCount());
                        objArrByAgeGroup.put("arrByAgeGroupFemale", arrByAgeGroup.get(i).getFemaleCount());
                        jarrByAgeGroup.put(objArrByAgeGroup);
                    } catch (JSONException ex) {
                        getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                    }
                }
                
                for (int i = 0; i < arrTotalAgeGroup.size(); i++) {
                    JSONObject objArrTotalByAgeGroup = new JSONObject();
                    try {
                        objArrTotalByAgeGroup.put("ageGroup", arrTotalAgeGroup.get(i).getAgeGroup());
                        objArrTotalByAgeGroup.put("male", arrTotalAgeGroup.get(i).getMaleCount());
                        objArrTotalByAgeGroup.put("female", arrTotalAgeGroup.get(i).getFemaleCount());
                        jarrayTotalAgeGroupSex.put(objArrTotalByAgeGroup);
                    } catch (JSONException ex) {
                        getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                    }
                }
                
                for (int i = 0; i < arrAgeGroupPerBarangay.size(); i++) {
                    JSONObject objArrTotalByAgeGroup = new JSONObject();
                    try {
                        objArrTotalByAgeGroup.put("location", arrAgeGroupPerBarangay.get(i).getBarangay());
                        objArrTotalByAgeGroup.put("ageGroup", arrAgeGroupPerBarangay.get(i).getAgeGroup());
                        objArrTotalByAgeGroup.put("male", arrAgeGroupPerBarangay.get(i).getMaleCount());
                        objArrTotalByAgeGroup.put("female", arrAgeGroupPerBarangay.get(i).getFemaleCount());
                        jarrayAgeGroupPerBarangay.put(objArrTotalByAgeGroup);
                    } catch (JSONException ex) {
                        getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                    }
                }

                for (int i = 0; i < arrTotalMF.size(); i++) {
                    JSONObject objarrTotalMF = new JSONObject();
                    try {
                        objarrTotalMF.put("arrTotalMFlocation", arrTotalMF.get(i).getBarangay());
                        objarrTotalMF.put("arrTotalMFAgeGroup", arrTotalMF.get(i).getAgeGroup());
                        objarrTotalMF.put("arrTotalMFMale", arrTotalMF.get(i).getMaleCount());
                        objarrTotalMF.put("arrTotalMFFemale", arrTotalMF.get(i).getFemaleCount());
                        jarrayChartTotalMF.put(objarrTotalMF);
                    } catch (JSONException ex) {
                        getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                    }

                }
                Record records = new RecordDAO().GetbyFormID(formID);

                for (int i = 0; i < ByAgeGroupSexTable.size(); i++) {
                    JSONObject objTable = new JSONObject();
                    try {
                        objTable.put("location", ByAgeGroupSexTable.get(i).getBarangay());
                        objTable.put("AgeGroup", ByAgeGroupSexTable.get(i).getAgeGroup());
                        objTable.put("BothSexes", ByAgeGroupSexTable.get(i).getBothSex());
                        objTable.put("Male", ByAgeGroupSexTable.get(i).getMaleCount());
                        objTable.put("Female", ByAgeGroupSexTable.get(i).getFemaleCount());
                        objTable.put("UploadedBy", records.getUploadedByByName());
                        objTable.put("ApprovedBy", records.getApprovedByName());
                        jarrayTable.put(objTable);
                    } catch (JSONException ex) {
                        getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                    }
                }

            
            ObjectAll.put("Total", objTotal);
            ObjectAll.put("arrByAgeGroup", jarrByAgeGroup);
            ObjectAll.put("arrTotalMFFemale", jarrayChartTotalMF);
            ObjectAll.put("ByAgeGroupSexTable", jarrayTable);
            ObjectAll.put("totalAgeGroupSex", jarrayTotalAgeGroupSex);
            ObjectAll.put("ageGroupBarangay", jarrayAgeGroupPerBarangay);
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
