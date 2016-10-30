/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */


package servlet.getjson;

import dao.analysis.FactPeopleDAO;
import model.analysis.FactDemo;
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
public class FactPeopleServlet extends HttpServlet {

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        FactPeopleDAO factPeopleDAO = new FactPeopleDAO();
        ArrayList<FactDemo> factPeople = new ArrayList<FactDemo>();

        try {
            factPeople = factPeopleDAO.GetFactPeople();
        } catch (SQLException ex) {
            Logger.getLogger(FactHospitalServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONArray array = new JSONArray();
        for (int i = 0; i < factPeople.size(); i++) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("Census Year", factPeople.get(i).getCensusYear());
                obj.put("Age Bracket", factPeople.get(i).getAgeBracket());
                obj.put("Barangay", factPeople.get(i).getBarangay());
                obj.put("Total No. Of People", factPeople.get(i).getTotalNoOfPeople());
                obj.put("Total No. Of Single", factPeople.get(i).getTotalNoOfSingle());
                obj.put("Total No. Of Married", factPeople.get(i).getTotalNoOfMarried());
                obj.put("Total No. Of Widowed", factPeople.get(i).getTotalNoOfWidowed());
                obj.put("Total No. Of Divorced", factPeople.get(i).getTotalNoOfDivorced());
                obj.put("Total No. Of Unknown", factPeople.get(i).getTotalNoOfUnknown());
                
                obj.put("Total No. Of Education", factPeople.get(i).getTotalNoOfEdu());
                obj.put("Total No. Of Pre-School", factPeople.get(i).getTotalNoOfPreSchool());
                obj.put("Total No. of 1st to 4th Grade", factPeople.get(i).getTotalNoOf1sTo4thGrade());
                obj.put("Total No. of 5st to 6th Grade", factPeople.get(i).getTotalNoOf5thto6thGrade());
                 obj.put("Total No. of No Elementary Graduate", factPeople.get(i).getTotalNoOfElemGrad());
                
                
                obj.put("Total No. of HS Graduates", factPeople.get(i).getTotalNoOfHSGrad());
                obj.put("Total No. of HS Under-Graduates", factPeople.get(i).getTotalNOOfHSUndergrand());
                obj.put("Total No. of  Post-Secondary Undergrand", factPeople.get(i).getTotalNoOfPreSchool());
                obj.put("Total No. of  Post-Secondary Granduates", factPeople.get(i).getTotalNoOFPSGrad());
                
                obj.put("Total No. of College Undergrand Granduates", factPeople.get(i).getTotalNoOFColUndergrand());
                 obj.put("Total No. of College Degree Holder", factPeople.get(i).getTotalNoOfDegreeHolder());
                 
                obj.put("Total No. of No Baccalaureate", factPeople.get(i).getTotalNoOfEdu());
                obj.put("Total No. of Not Stated", factPeople.get(i).getTotalNoOfNotStated());

                array.put(obj);
            } catch (JSONException ex) {
                Logger.getLogger(FactPeopleServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

          response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(array.toString());
    }
}
