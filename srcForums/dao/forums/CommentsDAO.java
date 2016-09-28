/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.forums;

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

    public ArrayList<Comments> getComments(int forumID) throws ParseException {
        ArrayList<Comments> arrComments = new ArrayList<Comments>();

        try {
            DBConnectionFactory myFactory = getInstance();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM COMMENTS");
                pstmt.setInt(1, forumID);

                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    Comments comments = new Comments();
                    comments.setIdComments(rs.getInt("idComment"));
                    comments.setCommentedDate(rs.getDate("commentedDate"));
                    comments.setComment(rs.getString("comment"));
                    comments.setCommentedby(rs.getInt("commentedBy"));
                    comments.setForumTitle(rs.getString("forumTitle"));
                    comments.setCreatedBy(rs.getInt("createdBy"));
                    comments.setForumID(rs.getInt("forumID"));
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
    
    
}
