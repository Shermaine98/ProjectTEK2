/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.forums;

import dao.forums.ForumDAO;
import dao.forums.TagsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.forums.Forums;
import model.forums.Tags;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import servlets.servlet.BaseServlet;

/**
 *
 * @author Shermaine
 */
public class HotTopic extends BaseServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            ForumDAO forumDAO = new ForumDAO();
            TagsDAO tagsDAO = new TagsDAO();
            ArrayList<Forums> arrForum = new ArrayList<Forums>();
            arrForum = forumDAO.getHotTopic();

            JSONArray array = new JSONArray();

            for (int i = 0; i < arrForum.size(); i++) {
                JSONObject obj = new JSONObject();
                JSONArray arrayTag = new JSONArray();

                try {
                    obj.put("forumID", arrForum.get(i).getForumID());
                    obj.put("forumTitle", arrForum.get(i).getForumTitle());
                    obj.put("body", arrForum.get(i).getBody());
                    obj.put("dateCreated", arrForum.get(i).getDateCreated());
                    obj.put("createdBy", arrForum.get(i).getCreatedBy());
                    obj.put("reportCounts", arrForum.get(i).getReportCount());
                    obj.put("commentsCount", arrForum.get(i).getCommentsCount());
                    obj.put("favoritesCount", arrForum.get(i).getFavoritesCount());
                    obj.put("createdByName", arrForum.get(i).getCreatedByName());

                    ArrayList<Tags> arrTags = new ArrayList<Tags>();
                    arrTags = tagsDAO.getTags(arrForum.get(i).getForumID());

                    for (int j = 0; j < arrTags.size(); j++) {
                        JSONObject objTag = new JSONObject();
                        objTag.put("tag", arrTags.get(j).getTag());
                        arrayTag.put(objTag);
                    }

                    obj.put("tags", arrayTag);

                    array.put(obj);

                } catch (JSONException ex) {
                    Logger.getLogger(ServletForums.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            out.print(array);
            out.flush();
        } catch (ParseException ex) {
            Logger.getLogger(ServletForums.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
