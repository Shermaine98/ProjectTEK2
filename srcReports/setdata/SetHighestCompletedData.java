/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package setdata;

import dao.RecordDAO;
import dao.charts.HighestCompletedChart;
import model.Record;
import model.demo.HighestCompleted;
import model.demo.HighestCompletedAgeGroup;
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
public class SetHighestCompletedData extends HttpServlet {
    String male = "Male";
    String female = "Female";
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
            JSONArray jarrayOverallTotal = new JSONArray();
            JSONArray jarrayMaleLocation = new JSONArray();
            JSONArray jarrayFemaleLocation = new JSONArray();
            JSONArray jarrayMaleAgeGroup = new JSONArray();
            JSONArray jarrayFemaleAgeGroup = new JSONArray();
            JSONObject ObjectAll = new JSONObject();
          
            int formID = 400000000 + parseInt(censusYear);
            HighestCompletedChart chart = new HighestCompletedChart();
            ArrayList<HighestCompletedAgeGroup> totalChart = chart.retrieveTotalHighestCompleted(formID);
            ArrayList<HighestCompleted> maleLocation = chart.retrieveLocationHighestCompleted(formID, male);
            ArrayList<HighestCompleted> femaleLocation = chart.retrieveLocationHighestCompleted(formID, female);
            ArrayList<HighestCompleted> maleAgeGroup = chart.retrieveAgeGroupHighestCompleted(formID, male);
            ArrayList<HighestCompleted> femaleAgeGroup = chart.retrieveAgeGroupHighestCompleted(formID, female);
            ArrayList<HighestCompleted> table = chart.retrieveTable(formID);


            for (int i = 0; i < totalChart.size(); i++) {
                JSONObject objArrTotal = new JSONObject();
                try {
                    objArrTotal.put("arrHighestCompleted", totalChart.get(i).gethighestCompleted());
                    objArrTotal.put("arrCount", totalChart.get(i).getCount());
                    jarrayOverallTotal.put(objArrTotal);
                } catch (JSONException ex) {
                    getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                }

            }

            for (int i = 0; i < maleLocation.size(); i++) {
                JSONObject objarrMaleLocation = new JSONObject();
                JSONArray jarrayHighestCompleted = new JSONArray();
                try {
                    objarrMaleLocation.put("arrMaleLocationSex", maleLocation.get(i).getSex());
                    objarrMaleLocation.put("arrMaleLocationBarangay", maleLocation.get(i).getLocation());
                    for(int y = 0; y < maleLocation.get(i).getHighestCompletedAgeGroup().size(); y++){
                        JSONObject highestCompleted = new JSONObject();
                        highestCompleted.put("arrMaleLocationHighestCompleted", maleLocation.get(i).getHighestCompletedAgeGroup().get(y).gethighestCompleted());
                        highestCompleted.put("arrMaleLocationCount", maleLocation.get(i).getHighestCompletedAgeGroup().get(y).getCount());
                        jarrayHighestCompleted.put(highestCompleted);
                    }
                    objarrMaleLocation.put("arrMaleHighestCompletedLocation", jarrayHighestCompleted);
                    jarrayMaleLocation.put(objarrMaleLocation);
                } catch (JSONException ex) {
                    getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            Record records = new RecordDAO().GetbyFormID(formID);

            for (int i = 0; i < femaleLocation.size(); i++) {
                JSONObject objarrFemaleLocation = new JSONObject();
                JSONArray jarrayHighestCompleted = new JSONArray();
                try {
                    objarrFemaleLocation.put("arrFemaleLocationSex", femaleLocation.get(i).getSex());
                    objarrFemaleLocation.put("arrFemaleLocationBarangay", femaleLocation.get(i).getLocation());
                    for(int y = 0; y < femaleLocation.get(i).getHighestCompletedAgeGroup().size(); y++){
                        JSONObject highestCompleted = new JSONObject();
                        highestCompleted.put("arrFemaleLocationHighestCompleted", femaleLocation.get(i).getHighestCompletedAgeGroup().get(y).gethighestCompleted());
                        highestCompleted.put("arrFemaleLocationCount", femaleLocation.get(i).getHighestCompletedAgeGroup().get(y).getCount());
                        jarrayHighestCompleted.put(highestCompleted);
                    }
                    objarrFemaleLocation.put("arrFemaleHighestCompletedLocation", jarrayHighestCompleted);
                    
                    jarrayFemaleLocation.put(objarrFemaleLocation);
                } catch (JSONException ex) {
                    getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            for (int i = 0; i < maleAgeGroup.size(); i++) {
                JSONObject objarrMaleAgeGroup = new JSONObject();
                JSONArray jarrayHighestCompletedMale = new JSONArray();
                try {
                    objarrMaleAgeGroup.put("yearMale", maleAgeGroup.get(i).getYear());
                    objarrMaleAgeGroup.put("locationMale", maleAgeGroup.get(i).getLocation());
                    objarrMaleAgeGroup.put("sexMale", maleAgeGroup.get(i).getSex());
                    objarrMaleAgeGroup.put("ageGroupMale", maleAgeGroup.get(i).getageGroup());
                    objarrMaleAgeGroup.put("totalMale", maleAgeGroup.get(i).getTotal());
                    for(int y = 0; y < maleAgeGroup.get(i).getHighestCompletedAgeGroup().size(); y++){
                        JSONObject highestCompleted = new JSONObject();
                        highestCompleted.put("highestCompletedMale", maleAgeGroup.get(i).getHighestCompletedAgeGroup().get(y).gethighestCompleted());
                        highestCompleted.put("countMale", maleAgeGroup.get(i).getHighestCompletedAgeGroup().get(y).getCount());
                        jarrayHighestCompletedMale.put(highestCompleted);
                    }
                    objarrMaleAgeGroup.put("highestCompletedDetMale", jarrayHighestCompletedMale);
                    jarrayMaleAgeGroup.put(objarrMaleAgeGroup);
                } catch (JSONException ex) {
                    getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            for (int i = 0; i < femaleAgeGroup.size(); i++) {
                JSONObject objarrFemaleAgeGroup = new JSONObject();
                JSONArray jarrayHighestCompletedFemale = new JSONArray();
                try {
                    objarrFemaleAgeGroup.put("yearFemale", femaleAgeGroup.get(i).getYear());
                    objarrFemaleAgeGroup.put("locationFemale", femaleAgeGroup.get(i).getLocation());
                    objarrFemaleAgeGroup.put("sexFemale", femaleAgeGroup.get(i).getSex());
                    objarrFemaleAgeGroup.put("ageGroupFemale", femaleAgeGroup.get(i).getageGroup());
                    objarrFemaleAgeGroup.put("totalFemale", femaleAgeGroup.get(i).getTotal());
                    for(int y = 0; y < femaleAgeGroup.get(i).getHighestCompletedAgeGroup().size(); y++){
                        JSONObject highestCompleted = new JSONObject();
                        highestCompleted.put("highestCompletedFemale", femaleAgeGroup.get(i).getHighestCompletedAgeGroup().get(y).gethighestCompleted());
                        highestCompleted.put("countFemale", femaleAgeGroup.get(i).getHighestCompletedAgeGroup().get(y).getCount());
                        jarrayHighestCompletedFemale.put(highestCompleted);
                    }
                    objarrFemaleAgeGroup.put("highestCompletedDetFemale", jarrayHighestCompletedFemale);
                    jarrayFemaleAgeGroup.put(objarrFemaleAgeGroup);
                } catch (JSONException ex) {
                    getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            for (int i = 0; i < table.size(); i++) {
                JSONObject objarrTable = new JSONObject();
                JSONArray jarrayHighestCompleted = new JSONArray();
                try {
                    objarrTable.put("year", table.get(i).getYear());
                    objarrTable.put("location", table.get(i).getLocation());
                    objarrTable.put("sex", table.get(i).getSex());
                    objarrTable.put("ageGroup", table.get(i).getageGroup());
                    objarrTable.put("total", table.get(i).getTotal());
                    for(int y = 0; y < table.get(i).getHighestCompletedAgeGroup().size(); y++){
                        JSONObject highestCompleted = new JSONObject();
                        highestCompleted.put("highestCompleted", table.get(i).getHighestCompletedAgeGroup().get(y).gethighestCompleted());
                        highestCompleted.put("count", table.get(i).getHighestCompletedAgeGroup().get(y).getCount());
                        jarrayHighestCompleted.put(highestCompleted);
                    }
                    objarrTable.put("highestCompletedDet", jarrayHighestCompleted);
                    jarrayTable.put(objarrTable);
                } catch (JSONException ex) {
                    getLogger(SetDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }

            ObjectAll.put("total", jarrayOverallTotal);
            ObjectAll.put("maleLocation", jarrayMaleLocation);
            ObjectAll.put("femaleLocation", jarrayFemaleLocation);
            ObjectAll.put("maleAgeGroup", jarrayMaleAgeGroup);
            ObjectAll.put("femaleAgeGroup", jarrayFemaleAgeGroup);
            ObjectAll.put("table", jarrayTable);
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
