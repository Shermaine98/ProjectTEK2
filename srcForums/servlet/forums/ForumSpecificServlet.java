/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.forums;

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
import javax.servlet.http.HttpSession;
import model.accounts.User;
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

        String forumId = request.getParameter("forumId");
        RequestDispatcher rd = null;
        ForumDAO forumDAO = new ForumDAO();
        Forums forum = new Forums();
        
         HttpSession session = request.getSession();
         User chck = (User) session.getAttribute("user");
        try {
            forum = forumDAO.getForum(Integer.parseInt(forumId), chck.getUserID());
        } catch (ParseException ex) {
            Logger.getLogger(ForumSpecificServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("forum", forum);
        rd = request.getRequestDispatcher("/WEB-INF/forumSpecific.jsp");
        rd.forward(request, response);

    }
}
