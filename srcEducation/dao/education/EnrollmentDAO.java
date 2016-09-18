/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.education;

import dao.RecordDAO;
import db.DBConnectionFactoryStorageDB;
import model.GlobalRecords;
import model.Record;
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
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Shermaine
 */
public class EnrollmentDAO {

    public ArrayList<Enrollment> ViewEnrollmentFormID(int formID, String classification) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<Enrollment> ArrEnrollment = new ArrayList<>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM enrollment WHERE formID = ? and classification = ? ");
                pstmt.setInt(1, formID);
                pstmt.setString(2, classification);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    Enrollment temp = new Enrollment();
                    ArrayList<EnrollmentDet> arrenrollmentDet = new ArrayList<EnrollmentDet>();
                    temp.setFormID(rs.getInt("formID"));
                    temp.setCensusYear(rs.getInt("censusYear"));
                    temp.setSchoolName(rs.getString("schoolName"));
                    temp.setDistrict(rs.getString("district"));
                    temp.setSchoolType(rs.getString("schoolType"));
                    temp.setClassification(rs.getString("classification"));
                    temp.setTotalMale(rs.getInt("totalMale"));
                    temp.setTotalFemale(rs.getInt("totalFemale"));
                    temp.setGrandTotal(rs.getInt("grandTotal"));
                    temp.setGenderDisparityIndex(rs.getDouble("GenderDisparityIndex"));

                    PreparedStatement pstmt2 = conn.prepareStatement("SELECT * FROM enrollment_det WHERE schoolName = ? and formID =? ");
                    pstmt2.setString(1, temp.getSchoolName());
                    pstmt2.setInt(2, temp.getFormID());
                    ResultSet rs2 = pstmt2.executeQuery();
                    while (rs2.next()) {
                        EnrollmentDet EnrollmentDetTemp = new EnrollmentDet();
                        EnrollmentDetTemp.setGradeLevel(rs2.getString("gradeLevel"));
                        EnrollmentDetTemp.setMaleCount(rs2.getInt("maleCount"));
                        EnrollmentDetTemp.setFemaleCount(rs2.getInt("femaleCount"));
                        EnrollmentDetTemp.setTotalCount(rs2.getInt("totalCount"));
                        EnrollmentDetTemp.setValidation(rs2.getBoolean("validation"));
                        arrenrollmentDet.add(EnrollmentDetTemp);
                    }
                    temp.setEnrollmentDetArrayList(arrenrollmentDet);
                    ArrEnrollment.add(temp);
                    pstmt2.close();
                }
                pstmt.close();
            }
            return ArrEnrollment;
        } catch (SQLException ex) {
            getLogger(EnrollmentDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Enrollment> ViewEnrollment() throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<Enrollment> ArrEnrollment = new ArrayList<>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM enrollment");

                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    Enrollment temp = new Enrollment();
                    ArrayList<EnrollmentDet> arrenrollmentDet = new ArrayList<EnrollmentDet>();
                    temp.setFormID(rs.getInt("formID"));
                    temp.setCensusYear(rs.getInt("censusYear"));
                    temp.setSchoolName(rs.getString("schoolName"));
                    temp.setDistrict(rs.getString("district"));
                    temp.setSchoolType(rs.getString("schoolType"));
                    temp.setClassification(rs.getString("classification"));
                    temp.setTotalMale(rs.getInt("totalMale"));
                    temp.setTotalFemale(rs.getInt("totalFemale"));
                    temp.setGrandTotal(rs.getInt("grandTotal"));
                    temp.setGenderDisparityIndex(rs.getDouble("GenderDisparityIndex"));

                    PreparedStatement pstmt2 = conn.prepareStatement("SELECT * FROM enrollment_det WHERE schoolName = ? and formID =? ");
                    pstmt2.setString(1, temp.getSchoolName());
                    pstmt2.setInt(2, temp.getFormID());
                    ResultSet rs2 = pstmt2.executeQuery();
                    while (rs2.next()) {
                        EnrollmentDet EnrollmentDetTemp = new EnrollmentDet();
                        EnrollmentDetTemp.setGradeLevel(rs2.getString("gradeLevel"));
                        EnrollmentDetTemp.setMaleCount(rs2.getInt("maleCount"));
                        EnrollmentDetTemp.setFemaleCount(rs2.getInt("femaleCount"));
                        EnrollmentDetTemp.setTotalCount(rs2.getInt("totalCount"));
                        EnrollmentDetTemp.setValidation(rs2.getBoolean("validation"));
                        arrenrollmentDet.add(EnrollmentDetTemp);
                    }
                    temp.setEnrollmentDetArrayList(arrenrollmentDet);
                    ArrEnrollment.add(temp);
                    pstmt2.close();
                }
                pstmt.close();
            }
            return ArrEnrollment;
        } catch (SQLException ex) {
            getLogger(EnrollmentDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public Integer getFormIDPrivate(int year, String classification) throws SQLException {
        DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
        Integer i;
        try (Connection conn = myFactory.getConnection()) {
            i = 0;
            String query = "SELECT MAX(formID) from enrollment where classification = ?";
            ResultSet rs;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, classification);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    i = 140000000 + year;
                }
                conn.close();
            }
            rs.close();
        }
        return i;
    }

    public Integer getFormIDPublic(int year, String classification) throws SQLException {
        DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
        Integer i;
        try (Connection conn = myFactory.getConnection()) {
            i = 0;
            String query = "SELECT MAX(formID) from enrollment where classification = ?";
            ResultSet rs;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, classification);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    i = 160000000 + year;
                }
                conn.close();
            }
            rs.close();
        }
        return i;
    }

    public boolean EncodeEnrollment(ArrayList<Enrollment> newEnrollment) {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            int rows;
            try (Connection conn = myFactory.getConnection()) {

                String query = "INSERT INTO enrollment"
                        + "(formID,censusYear,schoolName,district,schoolType,classification,"
                        + "totalMale,totalFemale,grandTotal,GenderDisparityIndex) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?);";

                String query2 = "INSERT INTO enrollment_det"
                        + "(formID,censusYear,schoolName,district,schoolType,classification,"
                        + "gradeLevel,maleCount,femaleCount,totalCount,validation) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
                PreparedStatement pstmt = conn.prepareStatement(query);
                PreparedStatement pstmt2 = conn.prepareStatement(query2);

                int i = 0;
                int z = 0;
                for (Enrollment object : newEnrollment) {

                    pstmt.setInt(1, object.getFormID());
                    pstmt.setInt(2, object.getCensusYear());
                    pstmt.setString(3, object.getSchoolName());
                    pstmt.setString(4, object.getDistrict());
                    pstmt.setString(5, object.getSchoolType());
                    pstmt.setString(6, object.getClassification());
                    pstmt.setInt(7, object.getTotalMale());
                    pstmt.setInt(8, object.getTotalFemale());
                    pstmt.setInt(9, object.getGrandTotal());
                    pstmt.setDouble(10, object.getGenderDisparityIndex());
                    pstmt.addBatch();
                    i++;

                    for (EnrollmentDet object2 : object.getEnrollmentDetArrayList()) {
                        pstmt2.setInt(1, object.getFormID());
                        pstmt2.setInt(2, object.getCensusYear());
                        pstmt2.setString(3, object.getSchoolName());
                        pstmt2.setString(4, object.getDistrict());
                        pstmt2.setString(5, object.getSchoolType());
                        pstmt2.setString(6, object.getClassification());
                        pstmt2.setString(7, object2.getGradeLevel());
                        pstmt2.setInt(8, object2.getMaleCount());
                        pstmt2.setInt(9, object2.getFemaleCount());
                        pstmt2.setInt(10, object2.getTotalCount());
                        pstmt2.setBoolean(11, object2.isValidation());
                        pstmt2.addBatch();
                        z++;
                    }

                }

                if (i % 1000 == 0 || i == newEnrollment.size()) {
                    pstmt.executeBatch();
                    pstmt2.executeBatch();
                }
                pstmt2.close();
                pstmt.close();
            }

            return true;
        } catch (SQLException ex) {
            getLogger(EnrollmentDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteenrollmentRecord(int formID) throws SQLException {
        boolean rows = false;
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            PreparedStatement pstmt;
            try (Connection conn = myFactory.getConnection()) {
                String delete = "DELETE FROM enrollment WHERE formID = ?";
                pstmt = conn.prepareStatement(delete);
                pstmt.setInt(1, formID);
                int isDeleted = pstmt.executeUpdate();
                if (isDeleted > 0) {
                    rows = true;
                }
            }
            pstmt.close();
            return rows;
        } catch (SQLException ex) {
            getLogger(EnrollmentDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteenrollmentRecordAll(int formID) throws SQLException {
        boolean rows = false;
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            PreparedStatement pstmt;
            PreparedStatement pstmt2;
            try (Connection conn = myFactory.getConnection()) {
                String delete = "DELETE FROM enrollment_det WHERE formID = ?";
                String delete2 = "DELETE FROM enrollment WHERE formID = ?";
                pstmt = conn.prepareStatement(delete);
                pstmt2 = conn.prepareStatement(delete2);
                pstmt.setInt(1, formID);
                pstmt2.setInt(1, formID);
                int isDeleted = pstmt.executeUpdate();
                int isDeleted2 = pstmt2.executeUpdate();
                if (isDeleted > 0 && isDeleted2 > 0) {
                    rows = true;
                }
            }
            pstmt.close();
            return rows;
        } catch (SQLException ex) {
            getLogger(EnrollmentDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteenrollmentDetailsRecord(int formID) throws SQLException {
        boolean rows = false;
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            PreparedStatement pstmt;
            try (Connection conn = myFactory.getConnection()) {
                String delete = "DELETE FROM enrollment_det WHERE formID = ?";
                pstmt = conn.prepareStatement(delete);
                pstmt.setInt(1, formID);
                int isDeleted = pstmt.executeUpdate();
                if (isDeleted > 0) {
                    rows = true;
                }
            }
            pstmt.close();
            return rows;
        } catch (SQLException ex) {
            getLogger(EnrollmentDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<GlobalRecords> getValidationFalseKinderPrivate() throws SQLException, ParseException {
        DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
        int i;
        try (Connection conn = myFactory.getConnection()) {
            i = 0;
            RecordDAO recordDAO = new RecordDAO();
            Record recordDEMO = new Record();
            ArrayList<GlobalRecords> ArrByAgeGroupSex = new ArrayList<>();

            String query = "SELECT formID, count(`VALIDATION`) as `ERROR` \n"
                    + "FROM enrollment_det \n"
                    + "WHERE `VALIDATION` = 0 AND formID > 130000000 AND formID < 139999999 \n"
                    + "GROUP BY formID";
            ResultSet rs;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    GlobalRecords temp = new GlobalRecords();
                    temp.setFormID(rs.getInt("formID"));
                    temp.setErrorLines(rs.getInt("error"));
                    recordDEMO = recordDAO.GetbyFormID(temp.getFormID());
                    temp.setCensusYear(recordDEMO.getCensusYear());
                    temp.setUploadedByByName(recordDEMO.getUploadedByByName());
                    ArrByAgeGroupSex.add(temp);
                }
                conn.close();
            }
            rs.close();
            return ArrByAgeGroupSex;
        }
    }

    public ArrayList<GlobalRecords> getValidationFalseKinderPublic() throws SQLException, ParseException {
        DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
        int i;
        try (Connection conn = myFactory.getConnection()) {
            i = 0;
            RecordDAO recordDAO = new RecordDAO();
            Record recordDEMO = new Record();
            ArrayList<GlobalRecords> ArrByAgeGroupSex = new ArrayList<>();

            String query = "SELECT formID, count(`VALIDATION`) as `ERROR` \n"
                    + "FROM enrollment_det \n"
                    + "WHERE `VALIDATION` = 0 AND formID > 130000000 AND formID < 139999999 \n"
                    + "GROUP BY formID";
            ResultSet rs;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    GlobalRecords temp = new GlobalRecords();
                    temp.setFormID(rs.getInt("formID"));
                    temp.setErrorLines(rs.getInt("error"));
                    recordDEMO = recordDAO.GetbyFormID(temp.getFormID());
                    temp.setCensusYear(recordDEMO.getCensusYear());
                    temp.setUploadedByByName(recordDEMO.getUploadedByByName());
                    ArrByAgeGroupSex.add(temp);
                }
                conn.close();
            }
            rs.close();
            return ArrByAgeGroupSex;
        }
    }

    public ArrayList<GlobalRecords> getValidationFalsePrivateElementary() throws SQLException, ParseException {
        DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
        int i;
        try (Connection conn = myFactory.getConnection()) {
            i = 0;
            RecordDAO recordDAO = new RecordDAO();
            Record recordDEMO = new Record();
            ArrayList<GlobalRecords> ArrByAgeGroupSex = new ArrayList<>();

            String query = "SELECT formID, count(`VALIDATION`) as `ERROR` \n"
                    + "FROM enrollment_det \n"
                    + "WHERE `VALIDATION` = 0 AND formID > 150000000 AND formID < 159999999 \n"
                    + "GROUP BY formID";
            ResultSet rs;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    GlobalRecords temp = new GlobalRecords();
                    temp.setFormID(rs.getInt("formID"));
                    temp.setErrorLines(rs.getInt("error"));
                    recordDEMO = recordDAO.GetbyFormID(temp.getFormID());
                    temp.setCensusYear(recordDEMO.getCensusYear());
                    temp.setUploadedByByName(recordDEMO.getUploadedByByName());
                    ArrByAgeGroupSex.add(temp);
                }
                conn.close();
            }
            rs.close();
            return ArrByAgeGroupSex;
        }
    }

    public ArrayList<GlobalRecords> getValidationFalsePublicElementary() throws SQLException, ParseException {
        DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
        int i;
        try (Connection conn = myFactory.getConnection()) {
            i = 0;
            RecordDAO recordDAO = new RecordDAO();
            Record recordDEMO = new Record();
            ArrayList<GlobalRecords> ArrByAgeGroupSex = new ArrayList<>();

            String query = "SELECT formID, count(`VALIDATION`) as `ERROR` \n"
                    + "FROM enrollment_det \n"
                    + "WHERE `VALIDATION` = 0 AND formID > 160000000 AND formID < 169999999 \n"
                    + "GROUP BY formID";
            ResultSet rs;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    GlobalRecords temp = new GlobalRecords();
                    temp.setFormID(rs.getInt("formID"));
                    temp.setErrorLines(rs.getInt("error"));
                    recordDEMO = recordDAO.GetbyFormID(temp.getFormID());
                    temp.setCensusYear(recordDEMO.getCensusYear());
                    temp.setUploadedByByName(recordDEMO.getUploadedByByName());
                    ArrByAgeGroupSex.add(temp);
                }
                conn.close();
            }
            rs.close();
            return ArrByAgeGroupSex;
        }
    }

}