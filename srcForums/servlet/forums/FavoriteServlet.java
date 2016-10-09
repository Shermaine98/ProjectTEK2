/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.forums;

import com.google.gson.Gson;
import dao.forums.CommentsDAO;
import dao.forums.ForumDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.accounts.User;
import model.forums.ForumsFavorite;

/**
 *
 * @author Shermaine
 */
public class FavoriteServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String decision = request.getParameter("decision");
        String isLike = request.getParameter("isLike");
        HttpSession session = request.getSession();
        User chck = (User) session.getAttribute("user");
        ForumDAO forumDAO = new ForumDAO();
        CommentsDAO commentsDAO = new CommentsDAO();

        boolean x = false;
        if (decision.equalsIgnoreCase("forum")) {
            ForumsFavorite forumFavorite = new ForumsFavorite();
            String forumTitle = request.getParameter("forumTitle");
            String forumId = request.getParameter("forumId");
            String createdBy = request.getParameter("createdby");
            if (isLike.equalsIgnoreCase("true")) {
                forumFavorite.setForumTitle(forumTitle.trim());
                forumFavorite.setForumID(Integer.parseInt(forumId.trim()));
                forumFavorite.setCreatedBy(Integer.parseInt(createdBy.trim()));
                forumFavorite.setFavoriteBy(chck.getUserID());
                x = forumDAO.addFavorite(forumFavorite);
            } else if (isLike.equalsIgnoreCase("false")) {
                x = forumDAO.deleteFavorite(chck.getUserID(), Integer.parseInt(forumId));
            }

        } else if (decision.equalsIgnoreCase("comment")) {
            String commentId = request.getParameter("commentID");
            if (isLike.equalsIgnoreCase("true")) {
                x = commentsDAO.addCommentFavorite(Integer.parseInt(commentId.trim()), chck.getUserID());
            } else if (isLike.equalsIgnoreCase("false")) {
                x = commentsDAO.deleteCommentFavorite(chck.getUserID(), Integer.parseInt(commentId));

            }
        }

        if (x) {
            String json = new Gson().toJson(x);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(json);
        }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
