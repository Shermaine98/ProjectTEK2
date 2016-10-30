/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package dao;

import db.DBConnectionFactoryStorageDB;
import static db.DBConnectionFactoryStorageDB.getInstance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Gian Carlo Roxas
 * @author shermaine Sy
 * @author Geraldine Atayan
 *
 */
public class HealthDashboard {

   

    public int aHealthNutritionalStatus() {

        ArrayList<Integer> count = new ArrayList<>();
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement pstmt = conn.prepareStatement("SELECT d.censusYear \n"
                    + "FROM records d JOIN nutritional_status a\n"
                    + "	on d.formID=a.formID \n"
                    + "WHERE D.VALIDATED = 1 AND d.approved=0\n"
                    + "GROUP BY d.censusYear;");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                count.add(rs.getInt("censusYear"));
            }

            pstmt.close();
            rs.close();
            conn.close();
            return count.size();

        } catch (SQLException ex) {
            getLogger(DemoDashboard.class.getName()).log(SEVERE, null, ex);
        }
        return 0;
    }

  
    
    //NOT SURE IF HOSPITALS OR HEALTH CENTERS
    public int aHealthHospitals() {

        ArrayList<Integer> count = new ArrayList<>();
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement pstmt = conn.prepareStatement("SELECT d.censusYear \n"
                    + "FROM records d JOIN directory_health a\n"
                    + "	on d.formID=a.formID \n"
                    + "WHERE D.VALIDATED = 1 AND d.approved=0\n"
                    + "GROUP BY d.censusYear;");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                count.add(rs.getInt("censusYear"));
            }

            pstmt.close();
            rs.close();
            conn.close();
            return count.size();

        } catch (SQLException ex) {
            getLogger(DemoDashboard.class.getName()).log(SEVERE, null, ex);
        }
        return 0;
    }

    
    

    public int iHealthNutritionalStatus() {

        ArrayList<Integer> count = new ArrayList<>();
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement pstmt = conn.prepareStatement("SELECT d.censusYear \n"
                    + "FROM records d JOIN nutritional_status a \n"
                    + "	on d.formID = a.formID\n"
                    + "WHERE d.validated = 0\n"
                    + "GROUP BY d.censusYear;");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                count.add(rs.getInt("censusYear"));
            }

            pstmt.close();
            rs.close();
            conn.close();
            return count.size();

        } catch (SQLException ex) {
            getLogger(DemoDashboard.class.getName()).log(SEVERE, null, ex);
        }
        return 0;
    }

   

    public int iHealthHospitals() {

        ArrayList<Integer> count = new ArrayList<>();
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement pstmt = conn.prepareStatement("SELECT d.censusYear \n"
                    + "FROM records d JOIN directory_health a\n"
                    + "	on d.formID = a.formID\n"
                    + "WHERE d.validated = 0\n"
                    + "GROUP BY d.censusYear;");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                count.add(rs.getInt("censusYear"));
            }

            pstmt.close();
            rs.close();
            conn.close();
            return count.size();

        } catch (SQLException ex) {
            getLogger(DemoDashboard.class.getName()).log(SEVERE, null, ex);
        }
        return 0;
    }
}
