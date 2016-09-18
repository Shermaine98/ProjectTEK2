/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import db.DBConnectionFactory;
import static db.DBConnectionFactory.getInstance;
import db.DBConnectionFactoryStorageDB;
import guest.model.Comments;
import guest.model.Topic;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Geraldine
 */
public class GuestDAO {

    public boolean addTopic(Topic newTopic) {
        try {
            DBConnectionFactory myFactory = getInstance();
            int rows;
            try (Connection conn = myFactory.getConnection()) {
                String query = "INSERT INTO FORUM\n"
                        + "(TITLE, BODY, TAG, DATEPOSTED, USERID, USERNAME) values (?, ?, ?, ?, ?, ?);";
                PreparedStatement pstmt = conn.prepareStatement(query);
                for (int i = 0; i < newTopic.getTags().size(); i++) {
                    pstmt.setString(1, newTopic.getTitle());
                    pstmt.setString(2, newTopic.getBody());
                    pstmt.setString(3, newTopic.getTags().get(i));
                    pstmt.setDate(4, newTopic.getDatePosted());
                    pstmt.setInt(5, newTopic.getUserID());
                    pstmt.setString(6, newTopic.getUsername());
                    rows = pstmt.executeUpdate();
                }
//                return rows == 1;
            }
            return true;
        } catch (SQLException ex) {
            getLogger(Accounts.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean addComment(Comments newComment) {
        try {
            DBConnectionFactory myFactory = getInstance();
            int rows;
            try (Connection conn = myFactory.getConnection()) {
                String query = "INSERT INTO COMMENT\n"
                        + "(USERNAME, USERID, FORUM_TITLE, COMMENT_BODY, DATE_POSTED) values (?, ?, ?, ?, ?);";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, newComment.getUsername());
                pstmt.setInt(2, newComment.getUserID());
                pstmt.setString(3, newComment.getForum_title());
                pstmt.setString(4, newComment.getComment());
                pstmt.setDate(5, newComment.getDatePosted());
                rows = pstmt.executeUpdate();
            }
            return rows == 1;
        } catch (SQLException ex) {
            getLogger(Accounts.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteComment(String forumTitle, String comment, int userID) {
        boolean rows = false;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            PreparedStatement pstmt;
            try (Connection conn = myFactory.getConnection()) {
                String delete = "DELETE FROM COMMENT\n"
                        + "WHERE FORUM_TITLE = ? AND COMMENT = ? AND USERID = ?;";
                pstmt = conn.prepareStatement(delete);
                pstmt.setString(1, forumTitle);
                pstmt.setString(2, comment);
                pstmt.setInt(3, userID);
                int isDeleted = pstmt.executeUpdate();
                if (isDeleted > 0) {
                    rows = true;
                }
                pstmt.close();
                conn.close();
            }
            return rows;
        } catch (SQLException ex) {
            getLogger(Accounts.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteTopic(String forumTitle, Date datePosted, int userID) {
        boolean rows = false;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            PreparedStatement pstmt;
            try (Connection conn = myFactory.getConnection()) {
                String delete = "DELETE FROM FORUM\n"
                        + "WHERE TITLE = ? AND USERID = ? AND DATEPOSTED = ?;";
                pstmt = conn.prepareStatement(delete);
                pstmt.setString(1, forumTitle);
                pstmt.setInt(2, userID);
                pstmt.setDate(3, datePosted);
                int isDeleted = pstmt.executeUpdate();
                if (isDeleted > 0) {
                    rows = true;
                }
                pstmt.close();
                conn.close();
            }
            return rows;
        } catch (SQLException ex) {
            getLogger(Accounts.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

}
