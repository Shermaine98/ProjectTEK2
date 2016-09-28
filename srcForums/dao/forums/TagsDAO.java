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
import model.forums.Tags;

/**
 *
 * @author Shermaine
 */
public class TagsDAO {
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
}
