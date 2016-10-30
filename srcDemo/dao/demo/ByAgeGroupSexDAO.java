/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package dao.demo;

import dao.RecordDAO;
import db.DBConnectionFactoryStorageDB;
import model.demo.ByAgeGroupSex;
import model.Record;
import model.GlobalRecords;
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
 * @author shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class ByAgeGroupSexDAO {

    public ArrayList<ByAgeGroupSex> ViewByAgeGroupSex() throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<ByAgeGroupSex> ArrByAgeGroupSex = new ArrayList<>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM age_group");
                ResultSet rs = pstmt.executeQuery();
                    RecordDAO RecordDAO = new RecordDAO(); 
                while (rs.next()) {
                    ByAgeGroupSex temp = new ByAgeGroupSex();
                    temp.setFormID(rs.getInt("formID"));
                    temp.setAgeGroup(rs.getString("ageGroup"));
                    temp.setYear(rs.getInt("censusYear"));
                    temp.setBarangay(rs.getString("location"));
                    temp.setBothSex(rs.getInt("bothSexes"));
                    temp.setMaleCount(rs.getInt("totalMale"));
                    temp.setFemaleCount(rs.getInt("totalFemale"));
                    temp.setValidation(rs.getInt("validation"));
                    temp.setUploadedBy(RecordDAO.GetUserNamebyFormID(temp.getFormID()));
                    ArrByAgeGroupSex.add(temp);
                }
                pstmt.close();
            }
            return ArrByAgeGroupSex;
        } catch (SQLException ex) {
            getLogger(ByAgeGroupSexDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<ByAgeGroupSex> ViewByAgeGroupSexFormID(int formID) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<ByAgeGroupSex> ArrByAgeGroupSex = new ArrayList<>();
            RecordDAO dao = new RecordDAO();
            try (Connection conn = myFactory.getConnection()) {

                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM age_group WHERE formID = ?");
                pstmt.setInt(1, formID);
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    ByAgeGroupSex temp = new ByAgeGroupSex();
                    temp.setFormID(rs.getInt("formID"));
                    temp.setAgeGroup(rs.getString("ageGroup"));
                    temp.setYear(rs.getInt("censusYear"));
                    temp.setBarangay(rs.getString("location"));
                    temp.setBothSex(rs.getInt("bothSexes"));
                    temp.setMaleCount(rs.getInt("totalMale"));
                    temp.setFemaleCount(rs.getInt("totalFemale"));
                    temp.setValidation(rs.getInt("validation"));
                    temp.setUploadedBy(dao.GetUserNamebyFormID(temp.getFormID()));
                    ArrByAgeGroupSex.add(temp);
                }
                pstmt.close();
            }
            return ArrByAgeGroupSex;
        } catch (SQLException ex) {
            getLogger(ByAgeGroupSexDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<ByAgeGroupSex> ViewByAgeGroupSexYear(int year) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<ByAgeGroupSex> ArrByAgeGroupSex = new ArrayList<>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM age_group WHERE censusYear = ?");
                pstmt.setInt(1, year);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    ByAgeGroupSex temp = new ByAgeGroupSex();
                    temp.setFormID(rs.getInt("formID"));
                    temp.setAgeGroup(rs.getString("ageGroup"));
                    temp.setYear(rs.getInt("censusYear"));
                    temp.setBothSex(rs.getInt("bothSexes"));
                    temp.setBarangay(rs.getString("location"));
                    temp.setMaleCount(rs.getInt("totalMale"));
                    temp.setFemaleCount(rs.getInt("totalFemale"));
                    temp.setValidation(rs.getInt("validation"));
                    ArrByAgeGroupSex.add(temp);
                }
                pstmt.close();
            }
            return ArrByAgeGroupSex;
        } catch (SQLException ex) {
            getLogger(ByAgeGroupSexDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public boolean EncodeByAgeGroupSex(ArrayList<ByAgeGroupSex> newByAgeGroupSex) {
        try {

            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            try (Connection conn = myFactory.getConnection()) {
                String query = " INSERT INTO age_group "
                        + " (formID,censusYear,location,ageGroup,bothSexes,totalMale,totalFemale,validation) "
                        + " VALUES (?,?,?,?,?,?,?,?); ";
                PreparedStatement pstmt = conn.prepareStatement(query);
                int i = 0;
                for (ByAgeGroupSex object : newByAgeGroupSex) {
                    pstmt.setInt(1, object.getFormID());
                    pstmt.setInt(2, object.getYear());
                    pstmt.setString(3, object.getBarangay());
                    pstmt.setString(4, object.getAgeGroup());
                    pstmt.setInt(5, object.getBothSex());
                    pstmt.setInt(6, object.getMaleCount());
                    pstmt.setInt(7, object.getFemaleCount());
                    pstmt.setInt(8, object.isvalidated());

                    pstmt.addBatch();
                    i++;
                }

                if (i % 1000 == 0 || i == newByAgeGroupSex.size()) {
                    pstmt.executeBatch();
                }

                pstmt.close();
            }
            return true;
        } catch (SQLException ex) {
            getLogger(ByAgeGroupSexDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public Integer getFormID(int year) throws SQLException {
        DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
        Integer i;
        try (Connection conn = myFactory.getConnection()) {
            i = 0;
            String query = "SELECT MAX(formID) from age_group";
            ResultSet rs;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    i = 200000000 + year;
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

            String query = "SELECT formID, count(`VALIDATION`) as `ERROR` \n" +
                            "FROM age_group \n" +
                            "WHERE `VALIDATION` != 1 AND formID > 200000000 AND formID < 299999999 \n" +
                            "GROUP BY formID";
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

    public boolean deleteAgeGroupRecord(int formID, int user) throws SQLException {
        boolean rows = false;
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            PreparedStatement pstmt;
            try (Connection conn = myFactory.getConnection()) {
                String delete = "DELETE FROM storageDB.AGE_GROUP WHERE formID = ?";
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
            getLogger(ByAgeGroupSexDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean deleteAgeGroupRecord(int formID) throws SQLException {
        boolean rows = false;
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            PreparedStatement pstmt;
            try (Connection conn = myFactory.getConnection()) {
                String delete = "DELETE FROM storageDB.AGE_GROUP WHERE formID = ?";
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
            getLogger(ByAgeGroupSexDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }
    
    public String getReason(int reason){
        String reasonError = "";
        
        switch (reason) {
            case -1:  reasonError = "Missing Field/s";
                     break;
            case -2:  reasonError = "Format Error";
                     break;
            case -3:  reasonError = "Summation Error";
                     break;
            default:  reasonError = "None";
                     break;
        }
            return reasonError;
    }
}
