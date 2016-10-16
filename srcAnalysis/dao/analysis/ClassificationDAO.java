/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.analysis;

import db.DBConnectionFactoryStarSchema;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Gian
 */
public class ClassificationDAO {
    public ArrayList<String> retrieveClassifications() throws SQLException   {
        DBConnectionFactoryStarSchema myFactory =  DBConnectionFactoryStarSchema.getInstance();
        ArrayList<String> gradeLevel = new ArrayList<String>();
        try (Connection conn = myFactory.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM starschema.dim_classification;");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                gradeLevel.add(rs.getString("classification"));
            }
            pstmt.close();
        }
        return gradeLevel;
    }
}
