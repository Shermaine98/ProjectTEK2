/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package dao.demo;

import dao.RecordDAO;
import db.DBConnectionFactoryStorageDB;
import model.Record;
import model.GlobalRecords;
import model.demo.HighestCompleted;
import model.demo.HighestCompletedAgeGroup;
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
public class HighestCompletedDAO {

    public ArrayList<HighestCompleted> ViewHighestCompleted() throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<HighestCompleted> ArrHighestCompleted = new ArrayList<>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * from highestCompleted");
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    HighestCompleted temp = new HighestCompleted();
                    ArrayList<HighestCompletedAgeGroup> arrHighestCompletedAgeGroup = new ArrayList<HighestCompletedAgeGroup>();
                    temp.setFormID(rs.getInt("formID"));
                    temp.setYear(rs.getInt("censusYear"));
                    temp.setLocation(rs.getString("location"));
                    temp.setSex(rs.getString("sex"));
                    temp.setAgeGroup(rs.getString("ageGroup"));
                    temp.setValidation(rs.getInt("Validation"));
                    temp.setReason(getReason(rs.getInt("Validation")));

                    PreparedStatement pstmt2 = conn.prepareStatement("SELECT * from highestcompletedagegroup WHERE  location = ? and formID = ? and ageGroup = ? ");
                    pstmt2.setString(1, temp.getLocation());
                    pstmt2.setInt(2, temp.getFormID());
                    pstmt2.setString(3, temp.getageGroup());
                    ResultSet rs2 = pstmt2.executeQuery();
                    
                    while (rs2.next()) {
                        HighestCompletedAgeGroup HighestCompletedAgeGroup = new HighestCompletedAgeGroup();
                        HighestCompletedAgeGroup.sethighestCompleted(rs2.getString("highestCompleted"));
                        HighestCompletedAgeGroup.setCount(rs2.getInt("count"));
                        HighestCompletedAgeGroup.setValidation(rs2.getInt("validation"));
                          HighestCompletedAgeGroup.setReason(getReason(rs2.getInt("validation")));
                        arrHighestCompletedAgeGroup.add(HighestCompletedAgeGroup);
                    }
                    temp.setHighestCompletedAgeGroup(arrHighestCompletedAgeGroup);
                    ArrHighestCompleted.add(temp);
                    pstmt2.close();
                }
                pstmt.close();

            }
            return ArrHighestCompleted;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<HighestCompleted> ViewHighestCompletedFormID(int formID) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<HighestCompleted> ArrHighestCompleted = new ArrayList<>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * from highestcompletedagegroup WHERE FORMID = ?");
                pstmt.setInt(1, formID);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    HighestCompleted temp = new HighestCompleted();
                    ArrayList<HighestCompletedAgeGroup> arrHighestCompletedAgeGroup = new ArrayList<HighestCompletedAgeGroup>();
                    temp.setFormID(rs.getInt("formID"));
                    temp.setYear(rs.getInt("censusYear"));
                    temp.setLocation(rs.getString("location"));
                    temp.setSex(rs.getString("sex"));
                    temp.setAgeGroup(rs.getString("ageGroup"));
                    temp.setTotal(rs.getInt("total"));
                    temp.setValidation(rs.getInt("Validation"));
                    temp.setReason(getReason(rs.getInt("Validation")));
                    
                    PreparedStatement pstmt2 = conn.prepareStatement("SELECT * from highestcompleted WHERE  location = ? and formID = ? and ageGroup = ? and sex = ?");
                    pstmt2.setString(1, temp.getLocation());
                    pstmt2.setInt(2, temp.getFormID());
                    pstmt2.setString(3, temp.getageGroup());
                    pstmt2.setString(4, temp.getSex());
                    ResultSet rs2 = pstmt2.executeQuery();
                    while (rs2.next()) {
                        HighestCompletedAgeGroup HighestCompletedAgeGroup = new HighestCompletedAgeGroup();
                        HighestCompletedAgeGroup.sethighestCompleted(rs2.getString("highestCompleted"));
                        HighestCompletedAgeGroup.setCount(rs2.getInt("count"));
                        HighestCompletedAgeGroup.setValidation(rs2.getInt("validation"));
                          HighestCompletedAgeGroup.setReason(getReason(rs2.getInt("validation")));
                        arrHighestCompletedAgeGroup.add(HighestCompletedAgeGroup);
                    }
                    temp.setHighestCompletedAgeGroup(arrHighestCompletedAgeGroup);
                    ArrHighestCompleted.add(temp);
                    pstmt2.close();
                }
                pstmt.close();
            }
            return ArrHighestCompleted;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public boolean EncodeHighestCompleted(ArrayList<HighestCompleted> model) {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            int rows;
            try (Connection conn = myFactory.getConnection()) {
                String query2 = "INSERT INTO highestCompleted"
                        + "(formID,censusYear,location,sex,ageGroup,highestCompleted,count,validation) "
                        + "VALUES (?,?,?,?,?,?,?,?);";

                String query = "INSERT INTO highestcompletedAgeGroup"
                        + "(formID,censusYear,location,sex,ageGroup,total, validation) "
                        + "VALUES (?,?,?,?,?,?,?);";

                PreparedStatement pstmt = conn.prepareStatement(query);
                PreparedStatement pstmt2 = conn.prepareStatement(query2);

                int i = 0;
                int z = 0;
                for (HighestCompleted object : model) {
                    pstmt.setInt(1, object.getFormID());
                    pstmt.setInt(2, object.getYear());
                    pstmt.setString(3, object.getLocation());
                    pstmt.setString(4, object.getSex());
                    pstmt.setString(5, object.getageGroup());
                    pstmt.setInt(6, object.getTotal());
                    pstmt.setInt(7, object.getValidation());
                    pstmt.addBatch();
                    i++;

                    for (HighestCompletedAgeGroup object2 : object.getHighestCompletedAgeGroup()) {
                        pstmt2.setInt(1, object.getFormID());
                        pstmt2.setInt(2, object.getYear());
                        pstmt2.setString(3, object.getLocation());
                        pstmt2.setString(4, object.getSex());
                        pstmt2.setString(5, object.getageGroup());
                        pstmt2.setString(6, object2.gethighestCompleted());
                        pstmt2.setInt(7, object2.getCount());
                        pstmt2.setInt(8, object2.isValidation());
                        pstmt2.addBatch();
                        z++;
                    }

                }

                if (i % 1000 == 0 || i == model.size()) {
                    //SOMETHING WRONG HERE 
                    pstmt.executeBatch();
                    pstmt2.executeBatch();

                }
                pstmt2.close();
                pstmt.close();
            }

            return true;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<GlobalRecords> getNullCountHighest() throws SQLException, ParseException {
        DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
        Integer i;
        try (Connection conn = myFactory.getConnection()) {
            i = 0;
            RecordDAO recordDAO = new RecordDAO();
            Record recordDEMO = new Record();
            ArrayList<GlobalRecords> errRecords = new ArrayList<>();

            String query = "SELECT formID, count(`VALIDATION`) as `ERROR` \n" +
                            "FROM highestcompleted \n" +
                            "WHERE `VALIDATION` != 1 AND formID > 400000000 AND formID < 499999999 \n" +
                            "GROUP BY formID";
            ResultSet rs;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    GlobalRecords temp = new GlobalRecords();
                    temp.setFormID(rs.getInt("formID"));
                    temp.setErrorLines(rs.getInt("ERROR"));
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

   

    public boolean deleteHighestCompleted(int formID) throws SQLException {
        boolean rows = false;
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            PreparedStatement pstmt;
            PreparedStatement pstmt2;
            try (Connection conn = myFactory.getConnection()) {
                String delete = "DELETE FROM storageDB.highestCompleted WHERE formID = ?";
                String delete2 = "DELETE FROM storageDB.highestcompletedagegroup WHERE formID = ?";
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
            getLogger(ByAgeGroupSexDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public Integer getFormID(int year) throws SQLException {
        DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
        Integer i;
        try (Connection conn = myFactory.getConnection()) {
            i = 0;
            String query = "SELECT MAX(formID) from highestCompleted";
            ResultSet rs;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    i = 400000000 + year;
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
