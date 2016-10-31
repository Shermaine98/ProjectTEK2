/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package dao;

import db.DBConnectionFactory;
import db.DBConnectionFactoryStorageDB;
import model.Record;
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
public class RecordDAO {

    public boolean checkExistRecordWithNotApproved(int formID, int year) throws SQLException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            int i;
            try (Connection conn = myFactory.getConnection()) {
                String checkExist = "SELECT EXISTS(SELECT * FROM RECORDS WHERE FORMID = ? AND `APPROVED` = 0) AS `EXISTS`;";
                PreparedStatement pstmt1 = conn.prepareStatement(checkExist);
                int check = formID + year;
                pstmt1.setInt(1, check);
                ResultSet rs = pstmt1.executeQuery();
                i = 0;
                if (rs.next()) {
                    i = rs.getInt("EXISTS");
                }
                rs.close();
                pstmt1.close();
                conn.close();
            }
            if (i <= 0) {
                return false;
            } else if (i > 0) {
                return true;
            }
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }
    
    
    public boolean checkExistRecord(int formID, int year) throws SQLException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            int i;
            try (Connection conn = myFactory.getConnection()) {
                String checkExist = "SELECT EXISTS(SELECT * FROM RECORDS WHERE FORMID = ?) AS `EXISTS`;";
                PreparedStatement pstmt1 = conn.prepareStatement(checkExist);
                int check = formID + year;
                pstmt1.setInt(1, check);
                ResultSet rs = pstmt1.executeQuery();
                i = 0;
                if (rs.next()) {
                    i = rs.getInt("EXISTS");
                }
                
                rs.close();
                pstmt1.close();
                conn.close();
            }
            if (i <= 0) {
                return false;
            } else if (i > 0) {
                return true;
            }
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }
    
    
    public boolean checkExistRecordUploadChecker(int formID, int year) throws SQLException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            int i;
            try (Connection conn = myFactory.getConnection()) {
                String checkExist = "SELECT EXISTS(SELECT * FROM RECORDS WHERE FORMID = ? AND `APPROVED` != -1) AS `EXISTS`;";
                PreparedStatement pstmt1 = conn.prepareStatement(checkExist);
                int check = formID + year;
                pstmt1.setInt(1, check);
                ResultSet rs = pstmt1.executeQuery();
                i = 0;
                if (rs.next()) {
                    i = rs.getInt("EXISTS");
                }
                rs.close();
                pstmt1.close();
                conn.close();
            }
            if (i <= 0) {
                return false;
            } else if (i > 0) {
                return true;
            }
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }
    
        public boolean checkExistRecordReupload(int formID, int year) throws SQLException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            int i;
            try (Connection conn = myFactory.getConnection()) {
                String checkExist = "SELECT EXISTS(SELECT * FROM RECORDS WHERE FORMID = ? AND `APPROVED` = -1 ) AS `EXISTS`;";
                PreparedStatement pstmt1 = conn.prepareStatement(checkExist);
                int check = formID + year;
                pstmt1.setInt(1, check);
                ResultSet rs = pstmt1.executeQuery();
                i = 0;
                if (rs.next()) {
                    i = rs.getInt("EXISTS");
                }
                rs.close();
                pstmt1.close();
                conn.close();
            }
            if (i <= 0) {
                return false;
            } else if (i > 0) {
                return true;
            }
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteRecord(int formID) throws SQLException {
        boolean rows = false;
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            PreparedStatement pstmt;
            try (Connection conn = myFactory.getConnection()) {
                String delete = "DELETE FROM RECORDS WHERE formID = ?";
                pstmt = conn.prepareStatement(delete);
                pstmt.setInt(1, formID);
                int isDeleted = pstmt.executeUpdate();
                if (isDeleted > 0) {
                    rows = true;
                }
                pstmt.close();
                conn.close();
            }
            return rows;
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkExistYearApproved(int formID, int year) throws SQLException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            int i;
            try (Connection conn = myFactory.getConnection()) {
                String checkExist = "SELECT EXISTS(SELECT * FROM RECORDS WHERE FORMID = ? AND `APPROVED` = 1) AS `EXISTS`;";
                PreparedStatement pstmt1 = conn.prepareStatement(checkExist);
                int check = formID + year;
                pstmt1.setInt(1, check);
                ResultSet rs = pstmt1.executeQuery();
                i = 0;
                if (rs.next()) {
                    i = rs.getInt("EXISTS");
                }
                rs.close();
                pstmt1.close();
                conn.close();
            }
            if (i <= 0) {
                return false;
            } else if (i > 0) {
                return true;
            }
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkExistRecordForReupload(int formID, int year) throws SQLException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            int i;
            try (Connection conn = myFactory.getConnection()) {
                String checkExist = "SELECT EXISTS(SELECT * FROM RECORDS WHERE FORMID = ?) AS `EXISTS`;";
                PreparedStatement pstmt1 = conn.prepareStatement(checkExist);
                int check = formID + year;
                pstmt1.setInt(1, check);
                ResultSet rs = pstmt1.executeQuery();
                i = 0;
                if (rs.next()) {
                    i = rs.getInt("EXISTS");

                }
                rs.close();
                pstmt1.close();
                conn.close();
            }
            if (i <= 0) {
                return false;
            } else if (i > 0) {
                return true;
            }
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean runRecordAgeGroupDAO(int formID, int year) throws SQLException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            try (Connection conn = myFactory.getConnection()) {
                String update = "SELECT IF(`VALIDATION` = 1, 'TRUE', 'FALSE') AS `RESULT` \n"
                        + " FROM AGE_GROUP \n"
                        + " WHERE FORMID = ? \n"
                        + " ORDER BY `VALIDATION` \n"
                        + " LIMIT 1;";
                PreparedStatement pstmt2 = conn.prepareStatement(update);
                pstmt2.setInt(1, formID);
                ResultSet rs2 = pstmt2.executeQuery();
                if (rs2.next()) {
                    String updateValidation = " UPDATE RECORDS \n"
                            + " SET `validated` = ? \n"
                            + " WHERE FORMID = ?;";
                    //UPDATE IF ALL ARE TRUE
                    try (PreparedStatement pstmt3 = conn.prepareStatement(updateValidation)) {
                        //UPDATE IF ALL ARE TRUE
                        String validation = rs2.getString("RESULT");
                        if (validation.equalsIgnoreCase("true")) {
                            pstmt3.setBoolean(1, true);
                            pstmt3.setInt(2, formID);
                            pstmt3.executeUpdate();
                            return true;
                            //UPDATE IF ALL ARE FALSE
                        } else if (validation.equalsIgnoreCase("false")) {
                            pstmt3.setBoolean(1, false);
                            pstmt3.setInt(2, formID);
                            pstmt3.executeUpdate();
                            return false;
                        }
                        rs2.close();
                        pstmt2.close();
                        pstmt3.close();

                        conn.close();
                    }
                }
            }
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean runRecordHighestDAO(int formID, int year) throws SQLException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            try (Connection conn = myFactory.getConnection()) {
               
                String update = "SELECT IF(`VALIDATION` = 1, 'TRUE', 'FALSE') AS `RESULT` \n"
                        + " FROM highestCompleted \n"
                        + " WHERE FORMID = ? \n"
                        + " ORDER BY `VALIDATION` \n"
                        + " LIMIT 1;";
                PreparedStatement pstmt2 = conn.prepareStatement(update);
                pstmt2.setInt(1, formID);
                ResultSet rs2 = pstmt2.executeQuery();
                if (rs2.next()) {
                    String updateValidation = " UPDATE RECORDS \n"
                            + " SET `validated` = ? \n"
                            + " WHERE FORMID = ?;";
                    //UPDATE IF ALL ARE TRUE
                    try (PreparedStatement pstmt3 = conn.prepareStatement(updateValidation)) {
                        //UPDATE IF ALL ARE TRUE
                        String validation = rs2.getString("RESULT");
                        if (validation.equalsIgnoreCase("true")) {
                            pstmt3.setBoolean(1, true);
                            pstmt3.setInt(2, formID);
                            pstmt3.executeUpdate();
                            return true;
                            //UPDATE IF ALL ARE FALSE
                        } else if (validation.equalsIgnoreCase("false")) {
                            pstmt3.setBoolean(1, false);
                            pstmt3.setInt(2, formID);
                            pstmt3.executeUpdate();
                            return false;
                        }
                        rs2.close();
                        pstmt2.close();
                        conn.close();

                    }
                }
            }
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean runRecordMaritalGroupDAO(int formID, int year) throws SQLException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            try (Connection conn = myFactory.getConnection()) {
                String update = "SELECT IF(`VALIDATION` = 1, 'TRUE', 'FALSE') AS `RESULT` \n"
                        + " FROM marital_status \n"
                        + " WHERE FORMID = ? \n"
                        + " ORDER BY `VALIDATION` \n"
                        + " LIMIT 1;";
                PreparedStatement pstmt2 = conn.prepareStatement(update);
                pstmt2.setInt(1, formID);
                ResultSet rs2 = pstmt2.executeQuery();
                if (rs2.next()) {
                    String updateValidation = " UPDATE records \n"
                            + " SET `validated` = ? \n"
                            + " WHERE FORMID = ?;";
                    //UPDATE IF ALL ARE TRUE
                    try (PreparedStatement pstmt3 = conn.prepareStatement(updateValidation)) {
                        //UPDATE IF ALL ARE TRUE
                        String validation = rs2.getString("RESULT");
                        if (validation.equalsIgnoreCase("true")) {
                            pstmt3.setBoolean(1, true);
                            pstmt3.setInt(2, formID);
                            pstmt3.executeUpdate();
                            return true;
                            //UPDATE IF ALL ARE FALSE
                        } else if (validation.equalsIgnoreCase("false")) {
                            pstmt3.setBoolean(1, false);
                            pstmt3.setInt(2, formID);
                            pstmt3.executeUpdate();
                            return false;
                        }
                        rs2.close();
                        pstmt2.close();
                        conn.close();
                    }
                }
            }
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean newRecord(int formID, int year, int uploadedBy) {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            int rows;
            try (Connection conn = myFactory.getConnection()) {
                String query = " INSERT INTO RECORDS "
                        + " (censusYear,formID,approved,approvedBy,validated,uploadedBy) "
                        + " VALUES (?,?,?,?,?,?); ";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, year);
                pstmt.setInt(2, formID);
                pstmt.setBoolean(3, false);
                pstmt.setInt(4, 0);
                pstmt.setBoolean(5, false);
                pstmt.setInt(6, uploadedBy);
                rows = pstmt.executeUpdate();

                pstmt.close();
                conn.close();
            }
            return rows == 1;
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean approvedRecord(int formID, int user) throws SQLException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            PreparedStatement pstmt;
            int rows;
            try (Connection conn = myFactory.getConnection()) {
                String updateValidation = "UPDATE RECORDS  SET `approved`= ?, `approvedBy`= ? WHERE `FORMID` = ?;";
                pstmt = conn.prepareStatement(updateValidation);
                pstmt.setBoolean(1, true);
                pstmt.setInt(2, user);
                pstmt.setInt(3, formID);
                rows = pstmt.executeUpdate();

                pstmt.close();
                conn.close();
            }
            return rows == 1;
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean rejectRecord(int formID, int user, String comments) throws SQLException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            PreparedStatement pstmt;
            int rows;
            try (Connection conn = myFactory.getConnection()) {
                String updateValidation = "UPDATE RECORDS  SET `approved`= ?, `approvedBy`= ?, `comments` = ? WHERE `FORMID` = ?;";
                pstmt = conn.prepareStatement(updateValidation);
                pstmt.setInt(1, -1);
                pstmt.setInt(2, user);
                pstmt.setString(3, comments);
                pstmt.setInt(4, formID);

                rows = pstmt.executeUpdate();

                pstmt.close();
                conn.close();
            }

            return rows == 1;
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public Record GetForReasons(int form) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            Record records;
            try (Connection conn = myFactory.getConnection()) {
                records = new Record();
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM RECORDS where formID = ? AND `approved` = -1 AND `validated` = 1 ;");
                pstmt.setInt(1, form);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    records.setFormID(rs.getInt("formID"));
                    records.setCensusYear(rs.getInt("censusYear"));
                    records.setReasons(rs.getString("comments"));
                    records.setApprovedByName(GetUserName(rs.getInt("approvedBy")));
                    records.setUploadedByByName(GetUserName(rs.getInt("uploadedBy")));
                }
                pstmt.close();
                rs.close();
                conn.close();
            }
            return records;
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Record> GetForApproval(int form, int limit) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<Record> records;
            try (Connection conn = myFactory.getConnection()) {
                records = new ArrayList<>();
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM RECORDS where formID >= ? AND formID <= ? AND `approved` = 0 AND `validated` = 1 ;");
                pstmt.setInt(1, form);
                pstmt.setInt(2, limit);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    Record temp = new Record();
                    temp.setFormID(rs.getInt("formID"));
                    temp.setCensusYear(rs.getInt("censusYear"));
                    temp.setValidation(rs.getBoolean("validated"));
                    temp.setApproved(rs.getInt("approved"));
                    temp.setUploadedBy(rs.getInt("uploadedBy"));
                    temp.setApprovedByName(GetUserName(rs.getInt("approvedBy")));
                    temp.setUploadedByByName(GetUserName(rs.getInt("uploadedBy")));
                    records.add(temp);
                }
                pstmt.close();
                rs.close();
                conn.close();
            }
            return records;
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    //REJECTED NOT USED BUT BAKA
    public ArrayList<Record> GetRejected(int form, int limit) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<Record> records;
            try (Connection conn = myFactory.getConnection()) {
                records = new ArrayList<>();
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM RECORDS where formID >= ? AND formID <= ? AND `approved` = -1 AND `validated` = 1 ;");
                pstmt.setInt(1, form);
                pstmt.setInt(2, limit);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    Record temp = new Record();
                    temp.setFormID(rs.getInt("formID"));
                    temp.setCensusYear(rs.getInt("censusYear"));
                    temp.setValidation(rs.getBoolean("validated"));
                    temp.setApproved(rs.getInt("approved"));
                    temp.setUploadedBy(rs.getInt("uploadedBy"));
                    temp.setApprovedByName(GetUserName(rs.getInt("approvedBy")));
                    temp.setUploadedByByName(GetUserName(rs.getInt("uploadedBy")));
                    records.add(temp);
                }
                pstmt.close();
                rs.close();
                conn.close();
            }
            return records;
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Record> GetAllValidated(int form, int limit) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<Record> records;
            try (Connection conn = myFactory.getConnection()) {
                records = new ArrayList<>();
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM RECORDS where formID >= ? AND formID <= ? AND `validated` = 1;");
                pstmt.setInt(1, form);
                pstmt.setInt(2, limit);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    Record temp = new Record();
                    temp.setFormID(rs.getInt("formID"));
                    temp.setCensusYear(rs.getInt("censusYear"));
                    temp.setValidation(rs.getBoolean("validated"));
                    temp.setApproved(rs.getInt("approved"));
                    temp.setUploadedBy(rs.getInt("uploadedBy"));
                    temp.setApprovedByName(GetUserName(rs.getInt("approvedBy")));
                    temp.setUploadedByByName(GetUserName(rs.getInt("uploadedBy")));
                    records.add(temp);
                }
                pstmt.close();
                rs.close();
                conn.close();
            }
            return records;
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public Record GetbyFormID(int form) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            Record temp;
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM RECORDS where formID = ?;");
                pstmt.setInt(1, form);
                ResultSet rs = pstmt.executeQuery();
                temp = new Record();
                while (rs.next()) {

                    temp.setFormID(rs.getInt("formID"));
                    temp.setCensusYear(rs.getInt("censusYear"));
                    temp.setValidation(rs.getBoolean("validated"));
                    temp.setApproved(rs.getInt("approved"));
                    temp.setUploadedBy(rs.getInt("uploadedBy"));
                    temp.setApprovedByName(GetUserName(rs.getInt("approvedBy")));
                    temp.setUploadedByByName(GetUserName(rs.getInt("uploadedBy")));

                }
                pstmt.close();
                rs.close();
                conn.close();
            }
            return temp;
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Record> SearchYear(int form, int limit, String year) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<Record> records;
            try (Connection conn = myFactory.getConnection()) {
                records = new ArrayList<>();
                String search = year + "%";
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM RECORDS where formID >= ? AND formID <= ? AND censusYear LIKE ? AND `APPROVED` = 1 AND `validated` = 1;");
                pstmt.setInt(1, form);
                pstmt.setInt(2, limit);
                pstmt.setString(3, search);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    Record temp = new Record();
                    temp.setFormID(rs.getInt("formID"));
                    temp.setCensusYear(rs.getInt("censusYear"));
                    temp.setValidation(rs.getBoolean("validated"));
                    temp.setApproved(rs.getInt("approved"));
                    temp.setUploadedBy(rs.getInt("uploadedBy"));
                    temp.setApprovedByName(GetUserName(rs.getInt("approvedBy")));
                    temp.setUploadedByByName(GetUserName(rs.getInt("uploadedBy")));
                    records.add(temp);
                }
                pstmt.close();
                rs.close();
                conn.close();
            }
            return records;
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public String GetUserName(int userId) throws ParseException {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            String user;
            try (Connection conn = myFactory.getConnection()) {
                ArrayList<Record> records = new ArrayList<>();
                PreparedStatement pstmt = conn.prepareStatement("SELECT CONCAT(u.firstName,\" \", u.lastName) AS `name` FROM users U WHERE userID = ?;");
                pstmt.setInt(1, userId);
                ResultSet rs = pstmt.executeQuery();
                user = null;
                while (rs.next()) {
                    user = rs.getString("name");
                }
                pstmt.close();
                rs.close();
                conn.close();
            }
            return user;
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    
        public ArrayList<Integer> GetAllYear() throws ParseException {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            String user;
            ArrayList<Integer> allyear = new ArrayList<Integer>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT censusYear FROM storagedb.records GROUP BY censusYear;");      
                ResultSet rs = pstmt.executeQuery();
                user = null;
                while (rs.next()) {
                    int x;
                    x = rs.getInt("censusYear");
                    allyear.add(x);
                }
                
                pstmt.close();
                rs.close();
                conn.close();
                return allyear;
            }
          
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    public String GetUserNamebyFormID(int formID) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            String user = null;
            try (Connection conn = myFactory.getConnection()) {
                ArrayList<Record> records = new ArrayList<>();
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM RECORDS where formID = ?;");
                pstmt.setInt(1, formID);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    user = GetUserName(rs.getInt("uploadedBy"));
                }
                pstmt.close();
                rs.close();
                conn.close();
            }
            return user;
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
}
