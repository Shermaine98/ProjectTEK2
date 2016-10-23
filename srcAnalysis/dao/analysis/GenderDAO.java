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
public class GenderDAO {
    public ArrayList<String> retrieveGenderWithoutBothSexes () throws ParseException {
        try {
            DBConnectionFactoryStarSchema myFactory = DBConnectionFactoryStarSchema.getInstance();
            ArrayList<String> genders = new ArrayList<String>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM starschema.dim_gender WHERE GENDER != 'BOTH SEXES';");
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    genders.add(rs.getString("GENDER"));
                }
                pstmt.close();
            }
            return genders;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
}
