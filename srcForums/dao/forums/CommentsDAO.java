/*
 *  ProjectTEK - DLSU CCS 2016
 * 
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
 * @author Gian Carlo Roxas
 * @author shermaine Sy
 * @author Geraldine Atayan
 * 
 */

public class CommentsDAO {

    public boolean addComment(Comments comments) {
        try {
            DBConnectionFactory myFactory = getInstance();
            int rows;
            try (Connection conn = myFactory.getConnection()) {
                String query = "INSERT INTO COMMENTS (idComment, comment, commentedBy, forumTitle, createdBy, forumID) values (?, ?,?,?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, getCommentID());
                pstmt.setString(2, comments.getComment());
                pstmt.setInt(3, comments.getCommentedby());
                pstmt.setString(4, comments.getForumTitle());
                pstmt.setInt(5, comments.getCreatedBy());
                pstmt.setInt(6, comments.getForumID());
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
                PreparedStatement pstmt = conn.prepareStatement("SELECT *, DATE_FORMAT(commentedDate,'%b. %d, %Y %h:%i %p') as 'dateOfComment' FROM accounts.comments where forumid=?;");
                pstmt.setInt(1, forumID);

                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    Comments comments = new Comments();
                    comments.setIdComments(rs.getInt("idComment"));
                    comments.setCommentedDate(rs.getDate("commentedDate"));
                    comments.setDateOfComment(rs.getString("dateOfComment"));
                    comments.setComment(rs.getString("comment"));
                    comments.setCommentedby(rs.getInt("commentedBy"));
                    comments.setCommentedByName(recordsDAO.GetUserName(comments.getCommentedby()));
                    comments.setForumTitle(rs.getString("forumTitle"));
                    comments.setCreatedBy(rs.getInt("createdBy"));
                    comments.setForumID(rs.getInt("forumID"));
                    comments.setCommentCounts(getCommentsCount(comments.getIdComments()));
                    comments.setIsLiked(getUserCommentLike(comments.getIdComments(), User));
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

    public Integer getCommentID() throws SQLException {
        DBConnectionFactory myFactory = getInstance();
        Integer i = 11111;
        try (Connection conn = myFactory.getConnection()) {
            String query = "SELECT MAX(idComment) as `idComment` from comments";
            ResultSet rs;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    i = 0;
                    i = rs.getInt("idComment") + 1;
                    return i;
                }
                conn.close();
            }
            rs.close();
        }
        return i;
    }

    public boolean addCommentFavorite(int commentID, int userID) {
        try {
            DBConnectionFactory myFactory = getInstance();
            int rows;
            try (Connection conn = myFactory.getConnection()) {
                String query = "INSERT INTO `comments_favorite` (idComment, favoriteBy) VALUES (?,?) ";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, commentID);
                pstmt.setInt(2, userID);
                rows = pstmt.executeUpdate();
                conn.close();
            }

            return rows == 1;
        } catch (SQLException ex) {
            getLogger(ForumDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteCommentFavorite(int userId, int commentID) {
        boolean x = false;
        try {
            DBConnectionFactory myFactory = getInstance();

            try (Connection conn = myFactory.getConnection()) {
                String query = "DELETE FROM `comments_favorite` WHERE favoriteBy = ? AND idComment = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, userId);
                pstmt.setInt(2, commentID);

                int isDeleted = pstmt.executeUpdate();
                if (isDeleted > 0) {
                    return true;
                }

            }
        } catch (SQLException ex) {
            getLogger(ForumDAO.class.getName()).log(SEVERE, null, ex);
        }
        return x;
    }
}
