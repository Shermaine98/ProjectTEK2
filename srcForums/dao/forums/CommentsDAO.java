/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.forums;

import dao.RecordDAO;
import db.DBConnectionFactory;
import static db.DBConnectionFactory.getInstance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import static java.util.logging.Level.SEVERE;
import model.forums.Comments;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Shermaine
 */
public class CommentsDAO {

    public boolean addComment(Comments comments) {
        try {
            DBConnectionFactory myFactory = getInstance();
            int rows;
            try (Connection conn = myFactory.getConnection()) {
                String query = "INSERT INTO COMMENTS (comment, commentedBy, forumTitle, createdBy, forumID) values (?,?,?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, comments.getComment());
                pstmt.setInt(2, comments.getCommentedby());
                pstmt.setString(3, comments.getForumTitle());
                pstmt.setInt(4, comments.getCreatedBy());
                pstmt.setInt(5, comments.getForumID());
                rows = pstmt.executeUpdate();
            }
            return rows == 1;
        } catch (SQLException ex) {
            getLogger(CommentsDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Comments> getComments(int forumID, int User) throws ParseException {
        ArrayList<Comments> arrComments = new ArrayList<Comments>();
                RecordDAO recordsDAO = new RecordDAO();

        try {
            DBConnectionFactory myFactory = getInstance();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM COMMENTS WHERE forumID = ?");
                pstmt.setInt(1, forumID);

                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    Comments comments = new Comments();
                    comments.setIdComments(rs.getInt("idComment"));
                    comments.setCommentedDate(rs.getDate("commentedDate"));
                    comments.setComment(rs.getString("comment"));
                    comments.setCommentedby(rs.getInt("commentedBy"));
                    comments.setCommentedByName(recordsDAO.GetUserName(comments.getCommentedby()));
                    comments.setForumTitle(rs.getString("forumTitle"));
                    comments.setCreatedBy(rs.getInt("createdBy"));
                    comments.setForumID(rs.getInt("forumID"));
                    comments.setCommentCounts(getCommentsCount(comments.getIdComments()));
                    comments.setIsLiked(getUserCommentLike(comments.getIdComments(),User ));
                    arrComments.add(comments);

                }

                pstmt.close();
                rs.close();
            }

            return arrComments;
        } catch (SQLException ex) {
            getLogger(CommentsDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
     public int getCommentsCount(int commentID) throws ParseException {
        int count = 0;

        try {
            DBConnectionFactory myFactory = getInstance();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT count(idComment) AS 'CommentCount' FROM comments_favorite WHERE idComment = ?;");
                pstmt.setInt(1, commentID);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("CommentCount");

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
    
     
     public boolean getUserCommentLike(int commentID, int UserID) throws ParseException {
        try {
            DBConnectionFactory myFactory = getInstance();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM comments_favorite cf WHERE cf.idComment = ? AND cf.favoriteBy = ?");
                pstmt.setInt(1, commentID);
                pstmt.setInt(2, UserID);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return true;
                }

                pstmt.close();
                rs.close();
            }

            return false;
        } catch (SQLException ex) {
            getLogger(ForumDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }
}
