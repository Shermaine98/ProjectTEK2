/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.forums;

import dao.RecordDAO;
import db.DBConnectionFactory;
import static db.DBConnectionFactory.getInstance;
import model.forums.Forums;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Shermaine
 */
public class ForumDAO {

    public boolean addForum(Forums forum) {
        try {
            DBConnectionFactory myFactory = getInstance();
            int rows;
            try (Connection conn = myFactory.getConnection()) {
                String query = "INSERT INTO FORUMS (forumTitle, createdBy, body, reportCount) VALUES (?,?,?,?) ";
                PreparedStatement pstmt = conn.prepareStatement(query);

                pstmt.setString(1, forum.getForumTitle());
                pstmt.setInt(2, forum.getCreatedBy());
                pstmt.setString(3, forum.getBody());
                pstmt.setInt(4, forum.getReportCount());

                rows = pstmt.executeUpdate();
                conn.close();
            }
            
            return rows == 1;
        } catch (SQLException ex) {
            getLogger(ForumDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Forums> getForums() throws ParseException {
        ArrayList<Forums> arrForums = new ArrayList<Forums>();
        RecordDAO recordsDAO = new RecordDAO();
        try {
            DBConnectionFactory myFactory = getInstance();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM FORUMS");

                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    Forums forums = new Forums();
                    forums.setForumID(rs.getInt("forumID"));
                    forums.setCreatedBy(rs.getInt("createdBy"));
                    forums.setForumTitle(rs.getString("forumTitle"));
                    forums.setBody(rs.getString("body"));
                    forums.setReportCount(rs.getInt("reportCount"));
                    forums.setDateCreated(rs.getDate("dateCreated"));
                    forums.setFavoritesCount(getFavorites(forums.getForumID()));
                    forums.setCommentsCount(getCommentsCount(forums.getForumID()));
                    forums.setCreatedByName(recordsDAO.GetUserName(forums.getCreatedBy()));
                    arrForums.add(forums);

                }

                pstmt.close();
                rs.close();
            }

            return arrForums;
        } catch (SQLException ex) {
            getLogger(ForumDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public int getFavorites(int forumID) throws ParseException {
        int count = 0;

        try {
            DBConnectionFactory myFactory = getInstance();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT count(forumTitle) AS 'FavoritesCount' FROM forums_favorite WHERE forumID = ?;");
                pstmt.setInt(1, forumID);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("FavoritesCount");

                }

                pstmt.close();
                rs.close();
            }

            return count;
        } catch (SQLException ex) {
            getLogger(ForumDAO.class.getName()).log(SEVERE, null, ex);
        }
        return 0;
    }

    public int getCommentsCount(int forumID) throws ParseException {
        int count = 0;

        try {
            DBConnectionFactory myFactory = getInstance();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT count(forumTitle) AS 'CommentsCounts' FROM comments WHERE forumID = ?;");
                pstmt.setInt(1, forumID);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("CommentsCounts");

                }

                pstmt.close();
                rs.close();
            }

            return count;
        } catch (SQLException ex) {
            getLogger(ForumDAO.class.getName()).log(SEVERE, null, ex);
        }
        return 0;
    }

}
