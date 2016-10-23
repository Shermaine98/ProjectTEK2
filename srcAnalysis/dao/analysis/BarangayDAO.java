/*
 *  Copyright (C) ProjectTEK - DLSU CCS 2016
 *  All right Reserved   * 
 */

package dao.analysis;

import dao.demo.HighestCompletedDAO;
import db.DBConnectionFactoryStarSchema;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class BarangayDAO {
 
    public ArrayList<Integer> retrieveBarangays () throws ParseException {
        try {
            DBConnectionFactoryStarSchema myFactory = DBConnectionFactoryStarSchema.getInstance();
            ArrayList<Integer> bgy = new ArrayList<Integer>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM DIM_BARANGAY");
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    bgy.add(rs.getInt("BARANGAY"));
                }
                pstmt.close();
            }
            return bgy;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
}
