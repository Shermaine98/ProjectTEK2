/*
 *  Copyright (C) ProjectTEK - DLSU CCS 2016
 *  All right Reserved   * 
 */

package servlets.search;

import dao.RecordDAO;
import model.Record;
import servlets.servlet.BaseServlet;
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
public class SearchAgeGroup extends BaseServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String year = request.getParameter("query");
        
            RecordDAO recordDAO = new RecordDAO();
            ArrayList<Record> recordsAge = new ArrayList<>();
            try {
                recordsAge = recordDAO.SearchYear(200000000, 299999999, year);
            } catch (ParseException ex) {
                getLogger(SearchAgeGroup.class.getName()).log(SEVERE, null, ex);
            }
  
            ArrayList<String> censusYear = new ArrayList<>();

            for (int i = 0; i < recordsAge.size(); i++) {
                    censusYear.add(valueOf(recordsAge.get(i).getCensusYear()));
                }
            Gson gson = new Gson();
            String json = gson.toJson(censusYear);
            response.getWriter().write("{\"suggestions\":" + json + "}");
        }

    }

