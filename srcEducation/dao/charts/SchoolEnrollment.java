/*
 *  Copyright (C) ProjectTEK - DLSU CCS 2016
 *  All rights Reserved   * 
 */


package dao.charts;

import dao.demo.HighestCompletedDAO;
import db.DBConnectionFactoryStorageDB;
import static db.DBConnectionFactoryStorageDB.getInstance;
import model.education.EnrollmentDet;
import model.education.Enrollment;
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
public class SchoolEnrollment {
    
    public ArrayList<Enrollment> retrieveSchoolStatistics(int censusyear, String classification) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            ArrayList<Enrollment> enrollment = new ArrayList<Enrollment>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT SCHOOLNAME, DISTRICT, SCHOOLTYPE, CLASSIFICATION, TOTALMALE, TOTALFEMALE, GRANDTOTAL, GENDERDISPARITYINDEX \n" +
                "FROM ENROLLMENT \n" +
                "WHERE CENSUSYEAR = ? AND CLASSIFICATION = ?");
                pstmt.setInt(1, censusyear);
                pstmt.setString(2, classification);
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    Enrollment temp = new Enrollment();
                    temp.setSchoolName(rs.getString("SCHOOLNAME"));
                    temp.setDistrict(rs.getString("DISTRICT"));
                    temp.setSchoolType(rs.getString("SCHOOLTYPE"));
                    temp.setClassification(rs.getString("CLASSIFICATION"));
                    temp.setTotalMale(rs.getInt("TOTALMALE"));
                    temp.setTotalFemale(rs.getInt("TOTALFEMALE"));
                    temp.setGrandTotal(rs.getInt("GRANDTOTAL"));
                    temp.setGenderDisparityIndex(rs.getDouble("GENDERDISPARITYINDEX"));
                    
                    PreparedStatement pstmt2 = conn.prepareStatement("SELECT GRADELEVEL, MALECOUNT, FEMALECOUNT, TOTALCOUNT \n" +
                    "FROM ENROLLMENT_DET \n" +
                    "WHERE CENSUSYEAR = ? AND CLASSIFICATION = ? AND SCHOOLNAME = ? ");
                    pstmt2.setInt(1, censusyear);
                    pstmt2.setString(2, classification);
                    pstmt2.setString(3, temp.getSchoolName());
                    ResultSet rs2 = pstmt2.executeQuery();
                    ArrayList<EnrollmentDet> arrDet = new ArrayList<EnrollmentDet>();
                    while(rs2.next()){
                        EnrollmentDet temp2 = new EnrollmentDet();
                        temp2.setGradeLevel(rs2.getString("GRADELEVEL"));
                        temp2.setMaleCount(rs2.getInt("MALECOUNT"));
                        temp2.setFemaleCount(rs2.getInt("FEMALECOUNT"));
                        temp2.setTotalCount(temp2.getFemaleCount()+temp2.getMaleCount());
                        arrDet.add(temp2);
                    }
                    temp.setEnrollmentDetArrayList(arrDet);
                    
                    enrollment.add(temp);
                }
                pstmt.close();
            }
            return enrollment;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<EnrollmentDet> retrieveGradeStatistics(int censusyear, String classification) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            ArrayList<EnrollmentDet> enrollment = new ArrayList<EnrollmentDet>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT CENSUSYEAR, CLASSIFICATION, GRADELEVEL, SUM(MALECOUNT) AS 'MALE', SUM(FEMALECOUNT) AS 'FEMALE' \n" +
                "FROM ENROLLMENT_DET \n" +
                "WHERE CENSUSYEAR = ? AND CLASSIFICATION = ? \n" +
                "GROUP BY GRADELEVEL;");
                pstmt.setInt(1, censusyear);
                pstmt.setString(2, classification);
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    EnrollmentDet temp = new EnrollmentDet();
                    temp.setGradeLevel(rs.getString("GRADELEVEL"));
                    temp.setMaleCount(rs.getInt("MALE"));
                    temp.setFemaleCount(rs.getInt("FEMALE"));
                    enrollment.add(temp);
                }
                pstmt.close();
            }
            return enrollment;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    public EnrollmentDet retrieveTotalMaleFemale(int censusyear, String classification) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            EnrollmentDet enrollmentMaleFemale = new EnrollmentDet();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT SUM(MALECOUNT) AS 'MALE', SUM(FEMALECOUNT) AS 'FEMALE'\n" +
                "FROM ENROLLMENT_DET \n" +
                "WHERE CENSUSYEAR = ? AND CLASSIFICATION = ? \n" +
                "GROUP BY CENSUSYEAR;");
                pstmt.setInt(1, censusyear);
                pstmt.setString(2, classification);
                ResultSet rs = pstmt.executeQuery();
                
                if (rs.next()) {
                    enrollmentMaleFemale.setFemaleCount(rs.getInt("FEMALE"));
                    enrollmentMaleFemale.setMaleCount(rs.getInt("MALE"));
                    System.out.println("FEMALE: "+enrollmentMaleFemale.getFemaleCount());
                    System.out.println("MALE: "+enrollmentMaleFemale.getMaleCount());
                }
                pstmt.close();
            }
            return enrollmentMaleFemale;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    
}
