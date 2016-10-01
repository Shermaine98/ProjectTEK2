/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.education.access;

import dao.forums.ForumDAO;
import servlets.servlet.BaseServlet;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.forums.Forums;

/**
 *
 * @author Atayan
 * @author Roxas
 * @author Sy
 *
 */
public class ForumSpecificServlet extends BaseServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("titleName");
        RequestDispatcher rd = null;
        ForumDAO forumDAO = new ForumDAO();
        Forums forum = new Forums();

        try {
            forum = forumDAO.getForum(title);
        } catch (ParseException ex) {
            Logger.getLogger(ForumSpecificServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("forum", forum);
        rd = request.getRequestDispatcher("forumSpecific.jsp");
        rd.forward(request, response);

    }
}
