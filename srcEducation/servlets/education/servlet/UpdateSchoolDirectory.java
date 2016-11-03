/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */


package servlets.education.servlet;

import dao.education.DirectorySchoolDAO;
import servlets.servlet.BaseServlet;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class UpdateSchoolDirectory extends BaseServlet {

    /**
     *
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String redirect = request.getParameter("redirect");
        String classification = request.getParameter("classification");
        // ServletContext context= getServletContext();
        DirectorySchoolDAO directorySchoolDAO = new DirectorySchoolDAO();

        if (redirect.equalsIgnoreCase("invalid")) {
            try {
                String schoolName = request.getParameter("schoolName");
                String censusYear = request.getParameter("censusYear");

                boolean x = directorySchoolDAO.setDirectoryInactive(schoolName.trim(), Integer.parseInt(censusYear));

                String json = new Gson().toJson(x);
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(json);
            } catch (SQLException ex) {
                Logger.getLogger(UpdateSchoolDirectory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (redirect.equalsIgnoreCase("submitAll")) {
            boolean x;
            String censusYear = request.getParameter("censusYear");
            String uploadedBy = request.getParameter("uploadedBy");

            if (classification.equalsIgnoreCase("private")) {
                x = directorySchoolDAO.SubtmitAllPrivate(Integer.parseInt(censusYear), Integer.parseInt(uploadedBy));
            } else {
                x = directorySchoolDAO.SubtmitAllPublic(Integer.parseInt(censusYear), Integer.parseInt(uploadedBy));
            }

            if (x) {
                request.setAttribute("page", "Upload");
                request.setAttribute("saveToDB", "SuccessDB");
                if (classification.equalsIgnoreCase("private")) {
                    RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataEducationServlet?redirect=privateDirectory");
                    rd.forward(request, response);
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataEducationServlet?redirect=publicDirectory");
                    rd.forward(request, response);
                }

            } else {
                request.setAttribute("page", "Upload");
                request.setAttribute("saveToDB", "notSuccess");
                if (classification.equalsIgnoreCase("private")) {
                    RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataEducationServlet?redirect=privateDirectory");
                    rd.forward(request, response);
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataEducationServlet?redirect=publicDirectory");
                    rd.forward(request, response);
                }

            }

        }

    }
}
