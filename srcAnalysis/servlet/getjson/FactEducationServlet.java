/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */


package servlet.getjson;

import dao.analysis.FactEnrollmentDAO;
import model.analysis.FactEnrollment;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class FactEducationServlet extends HttpServlet {

     /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException servlet exception
     * @throws IOException servlet IOException
     */
    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        FactEnrollmentDAO factEnrollmentDAO = new FactEnrollmentDAO();
        ArrayList<FactEnrollment> factEnrollment = new ArrayList<FactEnrollment>();
        try {
            factEnrollment = factEnrollmentDAO.GetFactEnrollment();
        } catch (SQLException ex) {
            Logger.getLogger(FactEducationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         JSONArray array = new JSONArray();
            for (int i = 0; i < factEnrollment.size(); i++) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("Year", factEnrollment.get(i).getCensusYear());
                    obj.put("School Name", factEnrollment.get(i).getSchoolName());
                    obj.put("District", factEnrollment.get(i).getDistrict());
                    obj.put("Type", factEnrollment.get(i).getType());
                    obj.put("Grade Level", factEnrollment.get(i).getLevel());
                    obj.put("Classification", factEnrollment.get(i).getClassification());
                    obj.put("Male Count", factEnrollment.get(i).getMaleCount());
                    obj.put("Female Count", factEnrollment.get(i).getFemaleCount());
                    obj.put("Both Sexes Count", factEnrollment.get(i).getFemaleCount() + factEnrollment.get(i).getMaleCount());
                    obj.put("Gender Disparity Index", factEnrollment.get(i).getGenderDisparityIndex());

                    array.put(obj);
                } catch (JSONException ex) {
                    Logger.getLogger(FactEducationServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        String json = new Gson().toJson(factEnrollment);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(array.toString());
    }

}
