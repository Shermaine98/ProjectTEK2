/*
 *  Copyright (C) ProjectTEK - DLSU CCS 2016
 *  All right Reserved   * 
 */

package servlets.education.servlet;

import dao.education.DirectorySchoolDAO;
import servlets.servlet.BaseServlet;
import com.google.gson.Gson;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import static java.util.logging.Level.SEVERE;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class SearchSchoolNamePublic extends BaseServlet {

        /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String name = request.getParameter("query");
        
            DirectorySchoolDAO directorySchoolDAO = new DirectorySchoolDAO();
            ArrayList<String> schoolName = new ArrayList<>();
            try {
                schoolName = directorySchoolDAO.SearchSchoolNamePublic(name);
            } catch (ParseException | SQLException ex) {
                getLogger(SearchSchoolNamePublic.class.getName()).log(SEVERE, null, ex);
            }
             System.out.println(name);
             System.out.println(schoolName.size());
  
            Gson gson = new Gson();
            String json = gson.toJson(schoolName);
            response.getWriter().write("{\"suggestions\":" + json + "}");
        }

    }

