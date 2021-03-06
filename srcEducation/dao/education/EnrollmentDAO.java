/*
 *  ProjectTEK - DLSU CCS 2016
 * 
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

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 *
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
                    temp.setValidation(rs.getInt("Validation"));
                    temp.setReason(getReason(temp.getValidation()));

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
                        EnrollmentDetTemp.setValidation(rs2.getInt("validation"));
                        EnrollmentDetTemp.setReason(getReason(EnrollmentDetTemp.isValidation()));
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
                    temp.setValidation(rs.getInt("Validaton"));
                    temp.setReason(getReason(temp.getValidation()));

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
                        EnrollmentDetTemp.setValidation(rs2.getInt("validation"));
                        EnrollmentDetTemp.setReason(getReason(EnrollmentDetTemp.isValidation()));
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
                        + "totalMale,totalFemale,grandTotal,GenderDisparityIndex, validation) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?);";

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
                    pstmt.setInt(11, object.getValidation());
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
                        pstmt2.setInt(11, object2.isValidation());
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
                    + "WHERE `VALIDATION` = 0 AND formID > 140000000 AND formID < 149999999\n"
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
                    + "WHERE `VALIDATION` != 1 AND formID > 130000000 AND formID < 139999999 \n"
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

            ResultSet rs;

            ResultSet rs2;

            String query = "SELECT formID, count(`VALIDATION`) as `ERROR` \n"
                    + "FROM enrollment_det \n"
                    + "WHERE `VALIDATION` != 1 AND formID > 140000000 AND formID < 149999999 \n"
                    + "GROUP BY formID";

            String query2 = "SELECT formID, count(`VALIDATION`) as `ERROR` \n"
                    + "FROM enrollment \n"
                    + "WHERE `VALIDATION` != 1 AND formID > 140000000 AND formID < 149999999 \n"
                    + "GROUP BY formID";

            int enrollemnt = 0;
            int enrollmentdet = 0;
            int formID = 0;

            PreparedStatement pstmt = conn.prepareStatement(query);
            PreparedStatement pstmt2 = conn.prepareStatement(query2);
            rs = pstmt.executeQuery();
            rs2 = pstmt2.executeQuery();

            while (rs.next()) {
                enrollmentdet = rs.getInt("error");
                formID = rs.getInt("formID");
            }

            while (rs2.next()) {
                enrollemnt = rs2.getInt("error");
                formID = rs2.getInt("formID");
            }

            if (enrollemnt + enrollmentdet > 0) {

                GlobalRecords temp = new GlobalRecords();
                temp.setFormID(formID);
                temp.setErrorLines(enrollmentdet + enrollemnt);
                recordDEMO = recordDAO.GetbyFormID(temp.getFormID());
                temp.setCensusYear(recordDEMO.getCensusYear());
                temp.setUploadedByByName(recordDEMO.getUploadedByByName());
                ArrByAgeGroupSex.add(temp);

                conn.close();
            }
            //  rs.close();
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
                    + "WHERE `VALIDATION` != 1 AND formID > 160000000 AND formID < 169999999 \n"
                    + "GROUP BY formID";
            ResultSet rs;
            
            
             String query2 = "SELECT formID, count(`VALIDATION`) as `ERROR` \n"
                    + "FROM enrollment \n"
                    + "WHERE `VALIDATION` != 1 AND formID > 160000000 AND formID < 169999999 \n"
                    + "GROUP BY formID";
             ResultSet rs2;
           
              int enrollemnt = 0;
            int enrollmentdet = 0;
            int formID = 0;

            PreparedStatement pstmt = conn.prepareStatement(query);
            PreparedStatement pstmt2 = conn.prepareStatement(query2);
            rs = pstmt.executeQuery();
            rs2 = pstmt2.executeQuery();

            while (rs.next()) {
                enrollmentdet = rs.getInt("error");
                formID = rs.getInt("formID");
            }

            while (rs2.next()) {
                enrollemnt = rs2.getInt("error");
                formID = rs2.getInt("formID");
            }

            if (enrollemnt + enrollmentdet > 0) {

                GlobalRecords temp = new GlobalRecords();
                temp.setFormID(formID);
                temp.setErrorLines(enrollmentdet + enrollemnt);
                recordDEMO = recordDAO.GetbyFormID(temp.getFormID());
                temp.setCensusYear(recordDEMO.getCensusYear());
                temp.setUploadedByByName(recordDEMO.getUploadedByByName());
                ArrByAgeGroupSex.add(temp);

                conn.close();
            }
            //  rs.close();
            return ArrByAgeGroupSex;
        }
    }

    public String getReason(int reason) {
        String reasonError = "";

        switch (reason) {
            case -1:
                reasonError = "Missing Field/s";
                break;
            case -2:
                reasonError = "Format Error";
                break;
            case -3:
                reasonError = "Summation Error";
                break;
            case -4:
                reasonError = "Quotient Error";
                break;
            default:
                reasonError = "None";
                break;
        }
        return reasonError;
    }
}
