/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package dao.etl;

import db.DBConnectionFactoryStarSchema;
import etl.Etl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 *
 */
public class DaoEtl {

    public boolean runETLDAO(int year) {
        try {
            DBConnectionFactoryStarSchema myFactory = DBConnectionFactoryStarSchema.getInstance();
            int rows;
            try (Connection conn = myFactory.getConnection()) {
                String query = " INSERT INTO dim_cencusyear "
                        + " (cencusYear) "
                        + " VALUES (?); ";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, year);
                rows = pstmt.executeUpdate();
            }
            if (rows > 0) {
                Etl etl = new Etl();
                return etl.run();
            }
        } catch (SQLException ex) {
            getLogger(DaoEtl.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkIntegrated(int year) {
        try {
            DBConnectionFactoryStarSchema myFactory = DBConnectionFactoryStarSchema.getInstance();
            int rows;
            try (Connection conn = myFactory.getConnection()) {
                String query = "SELECT EXISTS(SELECT * FROM dim_cencusyear WHERE cencusYear = ?) AS `EXISTS`; ";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, year);
                ResultSet rs = pstmt.executeQuery();
                rows = 0;
                if (rs.next()) {
                    rows = rs.getInt("EXISTS");
                }
                rs.close();
                pstmt.close();
                conn.close();
            }
            if (rows <= 0) {
                return false;
            } else if (rows > 0) {
                return true;
            }
        } catch (SQLException ex) {
            getLogger(DaoEtl.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }
}
