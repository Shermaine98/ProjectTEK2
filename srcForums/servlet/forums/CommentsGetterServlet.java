/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package servlet.forums;

import dao.forums.CommentsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.accounts.User;
import model.forums.Comments;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Gian Carlo Roxas
 * @author shermaine Sy
 * @author Geraldine Atayan
 * 
 */

public class CommentsGetterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException parse exception
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        CommentsDAO commentsDAO = new CommentsDAO();

        String forumID = request.getParameter("forumID");

        ArrayList<Comments> arrComments = new ArrayList<Comments>();
       HttpSession session = request.getSession();

        User chck = (User) session.getAttribute("user");
        
        arrComments = commentsDAO.getComments(Integer.parseInt(forumID), chck.getUserID());

        JSONArray array = new JSONArray();

        for (int i = 0; i < arrComments.size(); i++) {
            JSONObject obj = new JSONObject();

            try {
                obj.put("commentID", arrComments.get(i).getIdComments());
                obj.put("comment", arrComments.get(i).getComment());
                obj.put("commentedBy", arrComments.get(i).getCommentedby());
                obj.put("commentedByName", arrComments.get(i).getCommentedByName());
                obj.put("dateOfComment", arrComments.get(i).getDateOfComment());
                obj.put("dateCreated", arrComments.get(i).getCommentedDate());
                obj.put("forumID", arrComments.get(i).getForumID());
                obj.put("forumTitle", arrComments.get(i).getForumTitle());
                obj.put("commentCount", arrComments.get(i).getCommentCounts());
                obj.put("isLike", arrComments.get(i).isIsLiked());

                array.put(obj);

            } catch (JSONException ex) {
                Logger.getLogger(ServletForums.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        out.print(array);
        out.flush();
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
        } catch (ParseException ex) {
            Logger.getLogger(CommentsGetterServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(CommentsGetterServlet.class.getName()).log(Level.SEVERE, null, ex);
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
