/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package dao.health;

import dao.RecordDAO;
import db.DBConnectionFactoryStorageDB;
import model.GlobalRecords;
import model.Record;
import model.health.NutritionalStatus;
import model.health.NutritionalStatusBMI;
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


public class NutritionalStatusDAO {

    public ArrayList<NutritionalStatus> ViewNutritionalStatus(int formID) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<NutritionalStatus> ArrNutritionalStatus = new ArrayList<>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM nutritional_status WHERE formID = ?");
                pstmt.setInt(1, formID);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    NutritionalStatus temp = new NutritionalStatus();
                    ArrayList<NutritionalStatusBMI> arrenrollmentDet = new ArrayList<NutritionalStatusBMI>();
                    temp.setFormID(rs.getInt("formID"));
                    temp.setCensusYear(rs.getInt("censusYear"));
                    temp.setDistrict(rs.getString("district"));
                    temp.setGradeLevel(rs.getString("gradeLevel"));
                    temp.setTotalMale(rs.getInt("totalMale"));
                    temp.setTotalFemale(rs.getInt("totalFemale"));
                    temp.setTotalCount(rs.getInt("totalCount"));
                    temp.setPupilsWeighedMale(rs.getInt("pupilsWeighedMale"));
                    temp.setPupilsWeighedFemale(rs.getInt("pupilsWeighedFemale"));
                    temp.setPupilsWeighedTotal(rs.getInt("pupilsWeighedTotal"));
                    temp.setValidation(rs.getInt("validation"));

                    PreparedStatement pstmt2 = conn.prepareStatement("SELECT * FROM nutritional_status_BMI WHERE  formID = ? and gradeLevel = ? and district = ?");
                    pstmt2.setInt(1, temp.getFormID());
                    pstmt2.setString(2, temp.getGradeLevel());
                    pstmt2.setString(3, temp.getDistrict());
                    ResultSet rs2 = pstmt2.executeQuery();
                    while (rs2.next()) {
                        NutritionalStatusBMI NutritionalStatusBMITemp = new NutritionalStatusBMI();
                        NutritionalStatusBMITemp.setBMI(rs2.getString("BMI"));
                        NutritionalStatusBMITemp.setMaleCount(rs2.getInt("maleCount"));
                        NutritionalStatusBMITemp.setFemaleCount(rs2.getInt("femaleCount"));
                        NutritionalStatusBMITemp.setTotalCount(rs2.getInt("totalCount"));
                        arrenrollmentDet.add(NutritionalStatusBMITemp);
                    }
                    temp.setNutritionalStatusBMI(arrenrollmentDet);
                    ArrNutritionalStatus.add(temp);
                    pstmt2.close();
                }
                pstmt.close();
            }
            return ArrNutritionalStatus;
        } catch (SQLException ex) {
            getLogger(NutritionalStatusDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<NutritionalStatus> ViewNutritionalStatus() throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<NutritionalStatus> ArrNutritionalStatus = new ArrayList<>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM nutritional_status");
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    NutritionalStatus temp = new NutritionalStatus();
                    ArrayList<NutritionalStatusBMI> arrenrollmentDet = new ArrayList<NutritionalStatusBMI>();
                    temp.setFormID(rs.getInt("formID"));
                    temp.setCensusYear(rs.getInt("censusYear"));
                    temp.setDistrict(rs.getString("district"));
                    temp.setGradeLevel(rs.getString("gradeLevel"));
                    temp.setTotalMale(rs.getInt("totalMale"));
                    temp.setTotalFemale(rs.getInt("totalFemale"));
                    temp.setTotalCount(rs.getInt("totalCount"));
                    temp.setPupilsWeighedMale(rs.getInt("pupilsWeighedMale"));
                    temp.setPupilsWeighedFemale(rs.getInt("pupilsWeighedFemale"));
                    temp.setPupilsWeighedTotal(rs.getInt("pupilsWeighedTotal"));
                    temp.setValidation(rs.getInt("validation"));

                     PreparedStatement pstmt2 = conn.prepareStatement("SELECT * FROM nutritional_status_BMI WHERE  formID = ? and gradeLevel = ? and district = ?");
                    pstmt2.setInt(1, temp.getFormID());
                    pstmt2.setString(2, temp.getGradeLevel());
                    pstmt2.setString(3, temp.getDistrict());
                    ResultSet rs2 = pstmt2.executeQuery();
                    while (rs2.next()) {
                        NutritionalStatusBMI NutritionalStatusBMITemp = new NutritionalStatusBMI();
                        NutritionalStatusBMITemp.setBMI(rs2.getString("BMI"));
                        NutritionalStatusBMITemp.setMaleCount(rs2.getInt("maleCount"));
                        NutritionalStatusBMITemp.setFemaleCount(rs2.getInt("femaleCount"));
                        NutritionalStatusBMITemp.setTotalCount(rs2.getInt("totalCount"));
                        arrenrollmentDet.add(NutritionalStatusBMITemp);
                    }
                    temp.setNutritionalStatusBMI(arrenrollmentDet);
                    ArrNutritionalStatus.add(temp);
                    pstmt2.close();
                }
                pstmt.close();
            }
            return ArrNutritionalStatus;
        } catch (SQLException ex) {
            getLogger(NutritionalStatusDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }


    public boolean EncodeNutritionalStatus(ArrayList<NutritionalStatus> newNutritionalStatus) {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            int rows;
            try (Connection conn = myFactory.getConnection()) {

                String query = "INSERT INTO nutritional_status"
                        + "(censusYear,formID,district,gradeLevel,totalMale,totalFemale,"
                        + "totalCount,pupilsWeighedMale,pupilsWeighedFemale,pupilsWeighedTotal,validation) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?);";

                String query2 = "INSERT INTO nutritional_status_BMI"
                        + "(censusYear,formID,district,gradeLevel,BMI,maleCount,femaleCount,totalCount) "
                        + "VALUES (?,?,?,?,?,?,?,?);";
                PreparedStatement pstmt = conn.prepareStatement(query);
                PreparedStatement pstmt2 = conn.prepareStatement(query2);

                int i = 0;
                int z = 0;
                for (NutritionalStatus object : newNutritionalStatus) {

                    pstmt.setInt(1, object.getCensusYear());
                    pstmt.setInt(2, object.getFormID());
                    pstmt.setString(3, object.getDistrict());
                    pstmt.setString(4, object.getGradeLevel());
                    pstmt.setInt(5, object.getTotalMale());
                    pstmt.setInt(6, object.getTotalFemale());
                    pstmt.setInt(7, object.getTotalCount());
                    pstmt.setInt(8, object.getPupilsWeighedMale());
                    pstmt.setInt(9, object.getPupilsWeighedFemale());
                    pstmt.setInt(10, object.getPupilsWeighedTotal());
                    pstmt.setInt(11, object.isValidation());
                    pstmt.addBatch();
                    i++;

                    for (NutritionalStatusBMI object2 : object.getNutritionalStatusBMI()) {
                        pstmt2.setInt(1, object.getCensusYear());
                        pstmt2.setInt(2, object.getFormID());
                        pstmt2.setString(3, object.getDistrict());
                        pstmt2.setString(4, object.getGradeLevel());
                        pstmt2.setString(5, object2.getBMI());
                        pstmt2.setInt(6, object2.getMaleCount());
                        pstmt2.setInt(7, object2.getFemaleCount());
                        pstmt2.setInt(8, object2.getTotalCount());
                        pstmt2.addBatch();
                        z++;
                    }

                }

                if (i % 1000 == 0 || i == newNutritionalStatus.size()) {
                    pstmt.executeBatch();
                    pstmt2.executeBatch();
                }
                pstmt2.close();
                pstmt.close();
            }

            return true;
        } catch (SQLException ex) {
            getLogger(NutritionalStatusDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public Integer getFormID(int year) throws SQLException {
        DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
        Integer i;
        try (Connection conn = myFactory.getConnection()) {
            i = 0;
            String query = "SELECT MAX(formID) from nutritional_status";
            ResultSet rs;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    i = 800000000 + year;
                }
                conn.close();
            }
            rs.close();
        }
        return i;
    }

    public ArrayList<GlobalRecords> getValidationFalse() throws SQLException, ParseException {
        DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
        int i;
        try (Connection conn = myFactory.getConnection()) {
            i = 0;
            RecordDAO recordDAO = new RecordDAO();
            Record recordDEMO = new Record();
            ArrayList<GlobalRecords> ArrByAgeGroupSex = new ArrayList<>();

            String query = "SELECT formID, count(`VALIDATION`) as `ERROR` \n"
                    + "FROM nutritional_status \n"
                    + "WHERE `VALIDATION` = 0 AND formID > 700000000 AND formID < 799999999 \n"
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

    public boolean deleteNutritionalStatusRecordAll(int formID) throws SQLException {
        boolean rows = false;
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            PreparedStatement pstmt;
            PreparedStatement pstmt2;
            try (Connection conn = myFactory.getConnection()) {
                String delete = "DELETE FROM nutritional_status_BMI WHERE formID = ?";
                String delete2 = "DELETE FROM nutritional_status WHERE formID = ?";
                
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
            getLogger(NutritionalStatusDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteNutritionalStatusBMIRecord(int formID, int user) throws SQLException {
        boolean rows = false;
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            PreparedStatement pstmt;
            try (Connection conn = myFactory.getConnection()) {
                String delete = "DELETE FROM nutritional_status_BMI WHERE formID = ?";
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
            getLogger(NutritionalStatusDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteNutritionalStatusRecord(int formID) throws SQLException {
        boolean rows = false;
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            PreparedStatement pstmt;
            try (Connection conn = myFactory.getConnection()) {
                String delete = "DELETE FROM nutritional_status WHERE formID = ?";
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
            getLogger(NutritionalStatusDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }
}
