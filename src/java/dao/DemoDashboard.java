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
public class DemoDashboard {

    public int aDemoAgeGroup() {

        ArrayList<Integer> count = new ArrayList<>();
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement pstmt = conn.prepareStatement("SELECT d.censusYear \n" +
"                    FROM records d JOIN age_group a\n" +
"                    on d.formID=a.formID\n" +
"                    WHERE D.VALIDATED = 1 AND d.approved=0\n" +
"                    GROUP BY d.censusYear;");
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

    public int aDemoMarital() {

        ArrayList<Integer> count = new ArrayList<>();
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement pstmt = conn.prepareStatement("SELECT d.censusYear \n"
                    + "FROM records d JOIN marital_status a\n"
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

    public int aDemoHighest() {

        ArrayList<Integer> count = new ArrayList<>();
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement pstmt = conn.prepareStatement("SELECT d.censusYear \n"
                    + "FROM records d JOIN highestCompleted a\n"
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

    public int iDemoAgeGroup() {

        ArrayList<Integer> count = new ArrayList<>();
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement pstmt = conn.prepareStatement("SELECT d.censusYear\n"
                    + "FROM RECORDS D JOIN AGE_GROUP A\n"
                    + "ON D.FORMID = A.FORMID\n"
                    + "WHERE D.VALIDATED = 0\n"
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

    public int iDemoMarital() {

        ArrayList<Integer> count = new ArrayList<>();
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement pstmt = conn.prepareStatement("SELECT d.censusYear\n"
                    + "FROM records d JOIN marital_status a\n"
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

    public int iDemoHighest() {

        ArrayList<Integer> count = new ArrayList<>();
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement pstmt = conn.prepareStatement("SELECT d.censusYear\n"
                    + "FROM records d JOIN highestCompleted a\n"
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
