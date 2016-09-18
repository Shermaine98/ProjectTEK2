/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.setdata;

import dao.RecordDAO;
import dao.charts.ListOfHospitalDAO;
import dao.charts.NutritionalStatusChartsDAO;
import dao.charts.SchoolDirectory;
import model.Record;
import model.education.DirectorySchool;
import model.health.NutritionalStatus;
import model.health.NutritionalStatusBMI;
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
 * @author Gian
 */
public class SetNutritionalStatusDataServlet extends HttpServlet {

    private String normal = "Normal";
    private String severelyWasted = "Severely Wasted";
    private String wasted = "Wasted";
    private String overweight = "Overweight";
    private String obese = "Obese";
    
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
            JSONArray jarrayTotal = new JSONArray();
            JSONArray jarrayDistrict = new JSONArray();
            JSONArray jarrayGrade = new JSONArray();
            JSONObject ObjectAll = new JSONObject();
          
            int formID = 800000000 + parseInt(censusYear);
            NutritionalStatusChartsDAO chart = new NutritionalStatusChartsDAO();
            ArrayList<NutritionalStatus> table = chart.ViewNutritionalStatus(formID);
            ArrayList<NutritionalStatusBMI> total = chart.retrieveTotal(formID);
            ArrayList<NutritionalStatus> district = chart.retrieveByDistrict(formID);
            ArrayList<NutritionalStatus> grade = chart.retrieveGrade(formID);

            Record records = new RecordDAO().GetbyFormID(formID);
            
            
            for(int i = 0; i < table.size(); i++){
                JSONObject objTable = new JSONObject();
                JSONArray jarrayTableTemp = new JSONArray();
                try {
                    objTable.put("getDistrict", table.get(i).getDistrict());
                    objTable.put("getGradeLevel", table.get(i).getGradeLevel());
                    objTable.put("getTotalMale", table.get(i).getTotalMale());
                    objTable.put("getTotalFemale", table.get(i).getTotalFemale());
                    objTable.put("getTotalCount", table.get(i).getTotalCount());
                    objTable.put("getPupilsWeighedMale", table.get(i).getPupilsWeighedMale());
                    objTable.put("getPupilsWeighedFemale", table.get(i).getPupilsWeighedFemale());
                    objTable.put("getPupilsWeighedTotal", table.get(i).getPupilsWeighedTotal());
                    for(int y = 0; y < table.get(i).getNutritionalStatusBMI().size(); y++){
                        JSONObject objTableDet = new JSONObject();
                        objTableDet.put("getBMI", table.get(i).getNutritionalStatusBMI().get(y).getBMI());
                        objTableDet.put("getMaleCounts", table.get(i).getNutritionalStatusBMI().get(y).getMaleCount());
                        objTableDet.put("getFemaleCounts", table.get(i).getNutritionalStatusBMI().get(y).getFemaleCount());
                        objTableDet.put("getTotalCounts", table.get(i).getNutritionalStatusBMI().get(y).getTotalCount());
                        jarrayTableTemp.put(objTableDet);
                    }
                    objTable.put("getNutritionalStatusBMI", jarrayTableTemp);
                    jarrayTable.put(objTable);
                } catch (JSONException ex) {
                    getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            for(int i = 0; i < grade.size(); i++){
                JSONObject objTable = new JSONObject();
                JSONArray jarrayTableTemp = new JSONArray();
                try {
                    objTable.put("getDistrict", grade.get(i).getDistrict());
                    objTable.put("getGradeLevel", grade.get(i).getGradeLevel());
                    objTable.put("getTotalMale", grade.get(i).getTotalMale());
                    objTable.put("getTotalFemale", grade.get(i).getTotalFemale());
                    objTable.put("getTotalCount", grade.get(i).getTotalCount());
                    objTable.put("getPupilsWeighedMale", grade.get(i).getPupilsWeighedMale());
                    objTable.put("getPupilsWeighedFemale", grade.get(i).getPupilsWeighedFemale());
                    objTable.put("getPupilsWeighedTotal", grade.get(i).getPupilsWeighedTotal());
                    for(int y = 0; y < grade.get(i).getNutritionalStatusBMI().size(); y++){
                        JSONObject objTableDet = new JSONObject();
                        objTableDet.put("getBMI", grade.get(i).getNutritionalStatusBMI().get(y).getBMI());
                        objTableDet.put("getMaleCounts", grade.get(i).getNutritionalStatusBMI().get(y).getMaleCount());
                        objTableDet.put("getFemaleCounts", grade.get(i).getNutritionalStatusBMI().get(y).getFemaleCount());
                        objTableDet.put("getTotalCounts", grade.get(i).getNutritionalStatusBMI().get(y).getTotalCount());
                        jarrayTableTemp.put(objTableDet);
                    }
                    objTable.put("getNutritionalStatusBMI", jarrayTableTemp);
                    jarrayGrade.put(objTable);
                } catch (JSONException ex) {
                    getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            for(int i = 0; i < district.size(); i++){
                JSONObject objDistrict = new JSONObject();
                JSONArray jarrayDistrictTemp = new JSONArray();
                try {
                    objDistrict.put("getDistrict", district.get(i).getDistrict());
                    for(int y = 0; y < district.get(i).getNutritionalStatusBMI().size(); y++){
                        JSONObject objTableDet = new JSONObject();
                        objTableDet.put("getBMI", district.get(i).getNutritionalStatusBMI().get(y).getBMI());
                        System.out.println(district.get(i).getNutritionalStatusBMI().get(y).getBMI());
                        objTableDet.put("getMaleCounts", district.get(i).getNutritionalStatusBMI().get(y).getMaleCount());
                        objTableDet.put("getFemaleCounts", district.get(i).getNutritionalStatusBMI().get(y).getFemaleCount());
                        jarrayDistrictTemp.put(objTableDet);
                    }
                    objDistrict.put("BMI", jarrayDistrictTemp);
                    System.out.println(jarrayDistrictTemp);
                    jarrayDistrict.put(objDistrict);
                } catch (JSONException ex) {
                    getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            for(int i = 0; i < total.size(); i++){
                JSONObject objTable = new JSONObject();
                try {
                    objTable.put("BMI", total.get(i).getBMI());
                    objTable.put("male", total.get(i).getMaleCount());
                    objTable.put("female", total.get(i).getFemaleCount());
                    jarrayTotal.put(objTable);
                } catch (JSONException ex) {
                    getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            
            
            ObjectAll.put("total", jarrayTable);
            ObjectAll.put("highLevel", jarrayTotal);
            ObjectAll.put("district", jarrayDistrict);
            ObjectAll.put("grade", jarrayGrade);
            System.out.println("--\n"+jarrayDistrict);
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
