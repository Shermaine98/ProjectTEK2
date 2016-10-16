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
import model.analysis.FactEnrollment;

/**
 *
 * @author Gian
 */
public class GradeLevelDAO {
    public ArrayList<String> retrieveElementaryLevels() throws SQLException   {
        DBConnectionFactoryStarSchema myFactory =  DBConnectionFactoryStarSchema.getInstance();
        ArrayList<String> gradeLevel = new ArrayList<String>();
        try (Connection conn = myFactory.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM starschema.dim_gradelevel where `grade level` != 'SPED' and `grade level` != 'Kinder';");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                gradeLevel.add(rs.getString("grade level"));
            }
            pstmt.close();
        }
        return gradeLevel;
    }
}
