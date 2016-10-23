/*
 *  Copyright (C) ProjectTEK - DLSU CCS 2016
 *  All right Reserved   * 
 */

package servlet.getjson;

import dao.analysis.FactHospitalDAO;
import model.analysis.FactHospital;
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
public class FactHospitalServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        FactHospitalDAO factHospitalDAO = new FactHospitalDAO();
        ArrayList<FactHospital> factHospital = new ArrayList<FactHospital>();
        try {
            factHospital = factHospitalDAO.GetFactHospital();
        } catch (SQLException ex) {
            Logger.getLogger(FactHospitalServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        JSONArray array = new JSONArray();
        for (int i = 0; i < factHospital.size(); i++) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("Year", factHospital.get(i).getCensusYear());
                obj.put("Hospital Name", factHospital.get(i).getHospitalName());
                obj.put("Classification", factHospital.get(i).getClassification());
                obj.put("No. of Doctors", factHospital.get(i).getTotalNoOfDoctors());
                obj.put("No. of Midwives", factHospital.get(i).getTotalNoOfMidwives());
                obj.put("No. of Nurses", factHospital.get(i).getTotalNoOfNurses());
                obj.put("No. of Beds", factHospital.get(i).getTotalNoOfBeds());
                obj.put("Category", factHospital.get(i).getCategory());
                obj.put("Accredited", factHospital.get(i).isIsAccredited());
//                obj.put("Latitude", factHospital.get(i).getLatitude());
//                obj.put("Longitute", factHospital.get(i).getLongtitude());

                array.put(obj);
            } catch (JSONException ex) {
                Logger.getLogger(FactEducationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(array.toString());
    }

}
