/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package dao.demo;

import dao.RecordDAO;
import db.DBConnectionFactoryStorageDB;
import model.demo.MaritalStatus;
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

public class MaritalStatusDAO {
    
    public ArrayList<MaritalStatus> ViewMaritalStatus() throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<MaritalStatus> ArrMaritalStatus = new ArrayList<>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * marital_status");
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    MaritalStatus temp = new MaritalStatus();
                    temp.setFormID(rs.getInt("formID"));
                    temp.setYear(rs.getInt("censusYear"));
                    temp.setLocation(rs.getString("location"));
                    temp.setAgeGroup(rs.getString("ageGroup"));
                    temp.setSex(rs.getString("sex"));
                    temp.setTotal(rs.getInt("total"));
                    temp.setSingle(rs.getInt("single"));
                    temp.setMarried(rs.getInt("married"));
                    temp.setWidowed(rs.getInt("widowed"));
                    temp.setDivorcedSeparated(rs.getInt("divorcedSeparated"));
                    temp.setCommonLawLiveIn(rs.getInt("commonLawLiveIn"));
                    temp.setUnknown(rs.getInt("unkown"));
                    temp.setValidation(rs.getInt("validation"));
                    temp.setReasons(getReason(rs.getInt("validation")));
                    
                    ArrMaritalStatus.add(temp);
                }
                pstmt.close();
            }
            return ArrMaritalStatus;
        } catch (SQLException ex) {
            getLogger(MaritalStatusDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    
    
     public ArrayList<MaritalStatus> ViewMaritalStatusFormID(int formID) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<MaritalStatus> ArrMaritalStatus = new ArrayList<>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM marital_status WHERE formID = ?");
                pstmt.setInt(1, formID);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    MaritalStatus temp = new MaritalStatus();
                    temp.setFormID(rs.getInt("formID"));
                    temp.setYear(rs.getInt("censusYear"));
                    temp.setLocation(rs.getString("location"));
                    temp.setAgeGroup(rs.getString("ageGroup"));
                    temp.setSex(rs.getString("sex"));
                    temp.setTotal(rs.getInt("total"));
                    temp.setSingle(rs.getInt("single"));
                    temp.setMarried(rs.getInt("married"));
                    temp.setWidowed(rs.getInt("widowed"));
                    temp.setDivorcedSeparated(rs.getInt("divorcedSeparated"));
                    temp.setCommonLawLiveIn(rs.getInt("commonLawLiveIn"));
                    temp.setUnknown(rs.getInt("unkown"));
                    temp.setValidation(rs.getInt("validation"));
                    temp.setReasons(getReason(rs.getInt("validation")));
                    ArrMaritalStatus.add(temp);
                }
                pstmt.close();
            }
            return ArrMaritalStatus;
        } catch (SQLException ex) {
            getLogger(MaritalStatusDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    
     public boolean EncodeMaritalStatus(ArrayList<MaritalStatus> newMaritalStatus) {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            try (Connection conn = myFactory.getConnection()) {
                int rows = 0;
                String query = "INSERT INTO marital_status"
                        + "(formID,censusYear,location,ageGroup,sex, total, single, married, widowed, divorcedSeparated, commonLawLiveIn, unkown, validation) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
                PreparedStatement pstmt = conn.prepareStatement(query);
                int i=0;
                for(MaritalStatus object: newMaritalStatus ){
                    
                    pstmt.setInt(1, object.getFormID());
                    pstmt.setInt(2, object.getYear());
                    pstmt.setString(3, object.getLocation());
                    pstmt.setString(4, object.getAgeGroup());
                    pstmt.setString(5, object.getSex());
                    pstmt.setInt(6, object.getTotal());
                    pstmt.setInt(7, object.getSingle());
                    pstmt.setInt(8, object.getMarried());
                    pstmt.setInt(9, object.getWidowed());
                    pstmt.setInt(10, object.getDivorcedSeparated());
                    pstmt.setInt(11, object.getCommonLawLiveIn());
                    pstmt.setInt(12, object.getUnknown());
                    pstmt.setInt(13, object.getValidation());
                    
                    pstmt.addBatch();
                    i++;
                }
                
                if(i%1000==0 || i== newMaritalStatus.size()){
                    pstmt.executeBatch();
                }
                
                pstmt.close();
            }

             return true;
        } catch (SQLException ex) {
            getLogger(MaritalStatusDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }
      //@TODO
     
      public ArrayList<GlobalRecords> getNullCountMarital() throws SQLException, ParseException {
        DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
        Integer i;
        try (Connection conn = myFactory.getConnection()) {
            i = 0;
            RecordDAO recordDAO = new RecordDAO();
            Record recordDEMO = new Record();
            ArrayList<GlobalRecords> errRecords = new ArrayList<>();

             String query = "SELECT formID, count(`VALIDATION`) as `ERROR` \n" +
                            "FROM age_group \n" +
                            "WHERE `VALIDATION` = 0 AND formID > 300000000 AND formID < 399999999 \n" +
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
                    errRecords.add(temp);
                }
                conn.close();
            }

            rs.close();
            return errRecords;
        }
    }
      
  public boolean deleteMaritalStatusRecord(int formID, int user) throws SQLException {
        boolean rows = false;
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            PreparedStatement pstmt;
            try (Connection conn = myFactory.getConnection()) {
                String delete = "DELETE FROM storageDB.marital_status WHERE formID = ?";
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
            getLogger(MaritalStatusDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }    
  
  public boolean deleteMaritalStatusRecord(int formID) throws SQLException {
        boolean rows = false;
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            PreparedStatement pstmt;
            try (Connection conn = myFactory.getConnection()) {
                String delete = "DELETE FROM storageDB.marital_status WHERE formID = ?";
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
            getLogger(MaritalStatusDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }  
  
   public Integer getFormID(int year) throws SQLException {
        DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
        Integer i;
        try (Connection conn = myFactory.getConnection()) {
            i = 0;
            String query = "SELECT MAX(formID) from marital_status";
            ResultSet rs;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    i = 300000000 + year;
                }
                conn.close();
            }
            rs.close();
        }
        return i;
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
