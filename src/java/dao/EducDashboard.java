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
public class EducDashboard {

    public int aEducEnrollmentPublic() {

        ArrayList<Integer> count = new ArrayList<>();
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement pstmt = conn.prepareStatement("SELECT d.formID FROM records d JOIN enrollment_det a\n"
                    + "	on d.formID=a.formID \n"
                    + "            WHERE CLASSIFICATION='PUBLIC'  AND D.VALIDATED = 1 AND d.approved=0\n"
                    + "                 GROUP BY d.formID;");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                count.add(rs.getInt("formID"));
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

    public int iEducEnrollmentPublic() {

        ArrayList<Integer> count = new ArrayList<>();
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement pstmt = conn.prepareStatement("SELECT d.formID FROM records d JOIN enrollment_det a\n"
                    + "on d.formID=a.formID\n"
                    + "WHERE CLASSIFICATION='PUBLIC'  and  D.VALIDATED = 0 \n"
                    + "GROUP BY d.formID;");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                count.add(rs.getInt("formID"));
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

    public int aEducEnrollmentPrivate() {

        ArrayList<Integer> count = new ArrayList<>();
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement pstmt = conn.prepareStatement("SELECT d.formID FROM records d JOIN enrollment_det a\n"
                    + "	on d.formID=a.formID \n"
                    + "            WHERE CLASSIFICATION='Private'  AND D.VALIDATED = 1 AND d.approved=0\n"
                    + "                 GROUP BY d.formID;");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                count.add(rs.getInt("formID"));
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

    public int iEducEnrollmentPrivate() {

        ArrayList<Integer> count = new ArrayList<>();
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement pstmt = conn.prepareStatement("SELECT d.formID FROM records d JOIN enrollment_det a\n"
                    + "on d.formID=a.formID\n"
                    + "WHERE CLASSIFICATION='PRIVATE'  and  D.VALIDATED = 0 \n"
                    + "GROUP BY d.formID;");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                count.add(rs.getInt("formID"));
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

    public int aEducPublicDirectory() {

        ArrayList<Integer> count = new ArrayList<>();
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement pstmt = conn.prepareStatement("SELECT d.formID \n"
                    + "FROM records d JOIN directory_school a\n"
                    + "	on d.formID=a.formID \n"
                    + "WHERE CLASSIFICATION='PUBLIC' AND \n"
                    + " D.VALIDATED = 1 AND d.approved=0\n"
                    + " GROUP BY d.formID;");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                count.add(rs.getInt("formID"));
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

    public int iEducPublicDirectory() {

        ArrayList<Integer> count = new ArrayList<>();
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement pstmt = conn.prepareStatement("SELECT d.formID \n"
                    + "FROM records d \n"
                    + "JOIN directory_school a\n"
                    + "on d.formID = a.formID\n"
                    + "WHERE a.classification='PUBLIC' AND d.VALIDATED = '0'\n"
                    + "GROUP BY d.formID;");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                count.add(rs.getInt("formID"));
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

    public int aEducPrivateDirectory() {

        ArrayList<Integer> count = new ArrayList<>();
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement pstmt = conn.prepareStatement("SELECT d.formID \n"
                    + "FROM records d JOIN directory_school a\n"
                    + "	on d.formID=a.formID \n"
                    + "WHERE CLASSIFICATION='Private' AND \n"
                    + " D.VALIDATED = 1 AND d.approved=0\n"
                    + " GROUP BY d.formID;");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                count.add(rs.getInt("formID"));
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

    public int iEducPrivateDirectory() {

        ArrayList<Integer> count = new ArrayList<>();
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            Connection conn = myFactory.getConnection();

            PreparedStatement pstmt = conn.prepareStatement("SELECT d.formID \n"
                    + "FROM records d \n"
                    + "JOIN directory_school a\n"
                    + "on d.formID = a.formID\n"
                    + "WHERE a.classification='Private' AND d.VALIDATED = '0'\n"
                    + "GROUP BY d.formID;");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                count.add(rs.getInt("formID"));
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
