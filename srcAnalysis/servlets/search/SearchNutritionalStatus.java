/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package servlets.search;

import dao.RecordDAO;
import model.Record;
import servlets.servlet.BaseServlet;
import servlets.demo.servlet.ApproveDemoServlet;
import com.google.gson.Gson;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import static java.util.logging.Level.SEVERE;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static java.lang.String.valueOf;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class SearchNutritionalStatus extends BaseServlet {

   /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException servlet exception
     * @throws IOException servlet IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String year = request.getParameter("query");
        String page = request.getParameter("page");
        RecordDAO recordDAO = new RecordDAO();
        ArrayList<Record> recordsMarital = new ArrayList<>();
        
        try {
            recordsMarital = recordDAO.SearchYear(800000000, 899999999 , year);
        } catch (ParseException ex) {
            getLogger(ApproveDemoServlet.class.getName()).log(SEVERE, null, ex);
        }

        ArrayList<String> censusYear = new ArrayList<>();

        for (int i = 0; i < recordsMarital.size(); i++) {
                censusYear.add(valueOf(recordsMarital.get(i).getCensusYear()));
        }
        
        Gson gson = new Gson();
        String json = gson.toJson(censusYear);
        response.getWriter().write("{\"suggestions\":" + json + "}");
    }

    }

