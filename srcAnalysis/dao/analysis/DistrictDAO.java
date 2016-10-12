/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author Gian
 */
public class DistrictDAO {
    public ArrayList<String> retrieveDistricts () throws ParseException {
        try {
            DBConnectionFactoryStarSchema myFactory = DBConnectionFactoryStarSchema.getInstance();
            ArrayList<String> district = new ArrayList<String>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM DIM_DISTRICT");
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    district.add(rs.getString("DISTRICT"));
                }
                pstmt.close();
            }
            return district;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<String> retrieveDistrictsForNutritionalStatus () throws ParseException {
        try {
            DBConnectionFactoryStarSchema myFactory = DBConnectionFactoryStarSchema.getInstance();
            ArrayList<String> district = new ArrayList<String>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT IF(DISTRICT = 'Caloocan City', 'SPED', DISTRICT) AS 'DISTRICT' FROM DIM_DISTRICT");
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    district.add(rs.getString("DISTRICT"));
                }
                pstmt.close();
            }
            return district;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
}
