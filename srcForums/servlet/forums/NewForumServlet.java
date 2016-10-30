/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package servlet.forums;

import com.google.gson.Gson;
import dao.forums.ForumDAO;
import dao.forums.TagsDAO;
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
import javax.servlet.http.HttpSession;
import model.accounts.User;
import model.forums.Forums;
import model.forums.Tags;

/**
 *
 * @author Gian Carlo Roxas
 * @author shermaine Sy
 * @author Geraldine Atayan
 * 
 */

public class NewForumServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs 
     * @throws java.sql.SQLException sql exception
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        TagsDAO tagDAO = new TagsDAO();
        ForumDAO forumDAO = new ForumDAO();

        String forumTitle = request.getParameter("forumTitle");
        String body = request.getParameter("forumBody");
        String[] tags = request.getParameterValues("tags[]");
        HttpSession session = request.getSession();
        User chck = (User) session.getAttribute("user");

        ArrayList<String> arrSTags = new ArrayList<String>();
        ArrayList<Tags> arrtags = new ArrayList<Tags>();
        for (int i = 0; i < tags.length; i++) {
            arrSTags.add(tags[i].replaceAll("\\s", ""));
            Tags tag = new Tags();
            tag.setCreatedBy(chck.getUserID());
            tag.setTag(tags[i].replaceAll("\\s", ""));
            tag.setForumTitle(forumTitle);
            tag.setForumID(forumDAO.getForumID());
            arrtags.add(tag);

        }
        tagDAO.addTagRef(arrSTags);

        Forums forum = new Forums();
        forum.setForumTitle(forumTitle);
        forum.setBody(body);
        forum.setCreatedBy(chck.getUserID());

        boolean x = false;

        x = forumDAO.addForum(forum);

        if (x) {
            tagDAO.addTag(arrtags);
        }

        String json = new Gson().toJson(x);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(json);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(NewForumServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(NewForumServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
