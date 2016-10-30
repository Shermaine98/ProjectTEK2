/*
 *  ProjectTEK - DLSU CCS 2016
 * 
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
import model.forums.Tags;

/**
 *
 * @author Gian Carlo Roxas
 * @author shermaine Sy
 * @author Geraldine Atayan
 * 
 */

public class TagsDAO {

    public boolean addTag(ArrayList<Tags> tag) {
        try {
            DBConnectionFactory myFactory = getInstance();
            int rows = 0;
            try (Connection conn = myFactory.getConnection()) {

                String query = "INSERT INTO TAGS (tag, forumID, forumTitle, createdBy) VALUES (?,?,?,?) ";
                PreparedStatement pstmt = conn.prepareStatement(query);
                
                for (int i = 0; i < tag.size(); i++) {
                    pstmt.setString(1, tag.get(i).getTag());
                    pstmt.setInt(2, tag.get(i).getForumID());
                    pstmt.setString(3, tag.get(i).getForumTitle());
                    pstmt.setInt(4, tag.get(i).getCreatedBy());

                    rows = pstmt.executeUpdate();
                }
                conn.close();
            }

            return rows == 1;
        } catch (SQLException ex) {
            getLogger(TagsDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Tags> getTags(int forumID) throws ParseException {
        ArrayList<Tags> arrTags = new ArrayList<Tags>();

        try {
            DBConnectionFactory myFactory = getInstance();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM TAGS WHERE forumID = ?");
                pstmt.setInt(1, forumID);

                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    Tags tag = new Tags();
                    tag.setTag(rs.getString("tag"));

                    arrTags.add(tag);

                }

                pstmt.close();
                rs.close();
            }

            return arrTags;
        } catch (SQLException ex) {
            getLogger(TagsDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Tags> getAllRefTags() throws ParseException {
        ArrayList<Tags> arrTags = new ArrayList<Tags>();

        try {
            DBConnectionFactory myFactory = getInstance();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM `TAGS-REF`;");

                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {

                    Tags tag = new Tags();
                    tag.setTag(rs.getString("tag"));
                    arrTags.add(tag);
                }

                pstmt.close();
                rs.close();
            }

            return arrTags;
        } catch (SQLException ex) {
            getLogger(TagsDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public boolean isTagExist(String tag) {

        try {
            DBConnectionFactory myFactory = getInstance();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM `TAGS-REF` WHERE tag = ?;");
                pstmt.setString(1, tag);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {

                    return true;
                }

                pstmt.close();
                rs.close();
            }

            return false;
        } catch (SQLException ex) {
            getLogger(TagsDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean addTagRef(ArrayList<String> tags) {
        try {
            DBConnectionFactory myFactory = getInstance();
            try (Connection conn = myFactory.getConnection()) {

                for (int i = 0; i < tags.size(); i++) {
                    if (!isTagExist(tags.get(i))) {
                        String query = "INSERT INTO `TAGS-REF` (tag) VALUES (?) ";
                        PreparedStatement pstmt = conn.prepareStatement(query);
                        pstmt.setString(1, tags.get(i));
                        pstmt.executeUpdate();

                    }
                }
                conn.close();
                return true;

            }

        } catch (SQLException ex) {
            getLogger(TagsDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }
}
