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
import static java.util.logging.Logger.getLogger;
import model.accounts.Comments;

/**
 *
 * @author Shermaine
 */
public class DaoComments {

    public boolean addComment(Comments comments) {
        try {
            DBConnectionFactory myFactory = getInstance();
            int rows;
            try (Connection conn = myFactory.getConnection()) {
                String query = "";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, comments.getUserID());
                pstmt.setString(2, comments.getUsername());
                pstmt.setDate(3, comments.getDatePosted());
                pstmt.setInt(4, comments.getUserID());
                rows = pstmt.executeUpdate();
            }
            return rows == 1;
        } catch (SQLException ex) {
            getLogger(DaoComments.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Comments> getComments(int forumID) throws ParseException {
        ArrayList<Comments> arrComments = new ArrayList<Comments>();

        try {
            DBConnectionFactory myFactory = getInstance();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("");
                pstmt.setInt(1, forumID);

                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    Comments comments = new Comments();
                    comments.setUserID(rs.getInt("userID"));
                    comments.setUsername(rs.getString("username"));
                    comments.setComment(rs.getString("comment"));
                    comments.setDatePosted(rs.getDate("dateCreated"));
                    arrComments.add(comments);

                }

                pstmt.close();
                rs.close();
            }

            return arrComments;
        } catch (SQLException ex) {
            getLogger(DaoComments.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
}
