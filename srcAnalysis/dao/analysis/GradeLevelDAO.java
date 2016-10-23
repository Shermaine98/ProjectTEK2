/*
 *  Copyright (C) ProjectTEK - DLSU CCS 2016
 *  All right Reserved   * 
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
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
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
