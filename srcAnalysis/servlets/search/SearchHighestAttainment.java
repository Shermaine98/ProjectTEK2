/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.search;

import DAO.recordDAO;
import Model.record;
import Servlets.BaseServlet;
import ServletsDemo.ApproveDemoServlet;
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
import static java.lang.String.valueOf;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Shermaine
 */
public class SearchHighestAttainment extends BaseServlet {

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
        String page = request.getParameter("page");
        
      
            recordDAO recordDAO = new recordDAO();
            ArrayList<record> recordsHighest = new ArrayList<>();
             
                 
                 
            try {
                recordsHighest = recordDAO.SearchYear(400000000, 499999999, year);
            } catch (ParseException ex) {
                getLogger(ApproveDemoServlet.class.getName()).log(SEVERE, null, ex);
            }
  
            ArrayList<String> censusYear = new ArrayList<>();

            for (int i = 0; i < recordsHighest.size(); i++) {
                    censusYear.add(valueOf(recordsHighest.get(i).getCensusYear()));
                }
            
             
            Gson gson = new Gson();
            String json = gson.toJson(censusYear);
            response.getWriter().write("{\"suggestions\":" + json + "}");
        }

    }

