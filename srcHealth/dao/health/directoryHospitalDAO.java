/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.health;

import dao.RecordDAO;
import db.DBConnectionFactoryStorageDB;
import model.health.DirectoryHealth;
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
 * @author Shermaine
 */
public class directoryHospitalDAO {

    public ArrayList<DirectoryHealth> ViewDirectoryHospitalRecent() throws ParseException, SQLException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<DirectoryHealth> ArrListOfHospitals = new ArrayList<>();
            Connection conn = myFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select t.*\n"
                    + " from directory_health t\n"
                    + " inner join (\n"
                    + " select hospitalName, max(censusyear) as MaxDate\n"
                    + " from directory_health\n"
                    + " group by hospitalName\n"
                    + ") tm on t.hospitalName = tm.hospitalName and t.censusYear = tm.MaxDate and t.`active` =  1");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                DirectoryHealth temp = new DirectoryHealth();
                temp.setFormID(rs.getInt("formID"));
                temp.setYear(rs.getInt("censusYear"));
                temp.setHospitalName(rs.getString("hospitalName"));
                temp.setClassification(rs.getString("classification"));
                temp.setAddresss(rs.getString("address"));
                temp.setTelephone(rs.getString("telephone"));
                temp.setNumberOfDoctor(rs.getInt("totalDoctors"));
                temp.setNumberOfNurses(rs.getInt("totalNurses"));
                temp.setNumberOfMidwives(rs.getInt("totalMidwives"));
                temp.setNumberOfBeds(rs.getInt("numberOfBeds"));
                temp.setCategory(rs.getString("category"));
                temp.setLatitude(rs.getDouble("latitude"));
                temp.setLatitude(rs.getDouble("longitude"));
                temp.setAccreditation(rs.getBoolean("isAccredited"));
                temp.setValidation(rs.getBoolean("active"));
                ArrListOfHospitals.add(temp);
            }
            pstmt.close();

            return ArrListOfHospitals;
        } catch (SQLException ex) {
            getLogger(directoryHospitalDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<DirectoryHealth> ViewByDirectoryHospital(int formID) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<DirectoryHealth> ArrListOfHospitals = new ArrayList<>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM directory_health WHERE formID = ?");
                pstmt.setInt(1, formID);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    DirectoryHealth temp = new DirectoryHealth();
                    temp.setFormID(rs.getInt("formID"));
                    temp.setYear(rs.getInt("censusYear"));
                    temp.setHospitalName(rs.getString("hospitalName"));
                    temp.setClassification(rs.getString("classification"));
                    temp.setAddresss(rs.getString("address"));
                    temp.setTelephone(rs.getString("telephone"));
                    temp.setNumberOfDoctor(rs.getInt("totalDoctors"));
                    temp.setNumberOfNurses(rs.getInt("totalNurses"));
                    temp.setNumberOfMidwives(rs.getInt("totalMidwives"));
                    temp.setNumberOfBeds(rs.getInt("numberOfBeds"));
                    temp.setCategory(rs.getString("category"));
                    temp.setLatitude(rs.getDouble("latitude"));
                    temp.setLatitude(rs.getDouble("longitude"));
                    temp.setAccreditation(rs.getBoolean("isAccredited"));
                    temp.setValidation(rs.getBoolean("active"));
                    ArrListOfHospitals.add(temp);
                }
                pstmt.close();
            }
            return ArrListOfHospitals;
        } catch (SQLException ex) {
            getLogger(directoryHospitalDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<DirectoryHealth> ViewDirectoryHospital(int year) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<DirectoryHealth> ArrListOfHospitals = new ArrayList<>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM directory_health WHERE censusYear = ?");
                pstmt.setInt(1, year);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {

                    DirectoryHealth temp = new DirectoryHealth();
                    temp.setFormID(rs.getInt("formID"));
                    temp.setYear(rs.getInt("censusYear"));
                    temp.setHospitalName(rs.getString("hospitalName"));
                    temp.setClassification(rs.getString("classification"));
                    temp.setAddresss(rs.getString("address"));
                    temp.setTelephone(rs.getString("telephone"));
                    temp.setNumberOfDoctor(rs.getInt("totalDoctors"));
                    temp.setNumberOfNurses(rs.getInt("totalNurses"));
                    temp.setNumberOfMidwives(rs.getInt("totalMidwives"));
                    temp.setNumberOfBeds(rs.getInt("numberOfBeds"));
                    temp.setCategory(rs.getString("category"));
                    temp.setLatitude(rs.getDouble("latitude"));
                    temp.setLatitude(rs.getDouble("longitude"));
                    temp.setAccreditation(rs.getBoolean("isAccredited"));
                    temp.setValidation(rs.getBoolean("active"));
                    ArrListOfHospitals.add(temp);
                }
                pstmt.close();
            }
            return ArrListOfHospitals;
        } catch (SQLException ex) {
            getLogger(directoryHospitalDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public boolean EncodeListOfHospitals(ArrayList<DirectoryHealth> newListOfHospitals) {
        try {

            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            try (Connection conn = myFactory.getConnection()) {
                String query = " INSERT INTO directory_health "
                        + " (formID,censusYear,hospitalName,classification,address,telephone,"
                        + "totalDoctors,totalNurses"
                        + ",totalMidwives,numberOfBeds,category,active) "
                        + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?); ";
                PreparedStatement pstmt = conn.prepareStatement(query);
                int i = 0;
                for (DirectoryHealth object : newListOfHospitals) {

                    pstmt.setInt(1, object.getFormID());
                    pstmt.setInt(2, object.getYear());
                    pstmt.setString(3, object.getHospitalName());
                    pstmt.setString(4, object.getClassification());
                    pstmt.setString(5, object.getAddresss());
                    pstmt.setString(6, object.getTelephone());
                    pstmt.setInt(7, object.getNumberOfDoctor());
                    pstmt.setInt(8, object.getNumberOfNurses());
                    pstmt.setInt(8, object.getNumberOfMidwives());
                    pstmt.setInt(10, object.getNumberOfBeds());
                    pstmt.setString(11, object.getCategory());
                    pstmt.setBoolean(12, object.isValidation());

                    pstmt.addBatch();
                    i++;
                }

                if (i % 1000 == 0 || i == newListOfHospitals.size()) {
                    pstmt.executeBatch();
                }

                pstmt.close();
            }
            return true;
        } catch (SQLException ex) {
            getLogger(directoryHospitalDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public Integer getFormID(int year) throws SQLException {
        DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
        Integer i;
        try (Connection conn = myFactory.getConnection()) {
            i = 0;
            String query = "SELECT MAX(formID) from directory_health";
            ResultSet rs;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    i = 90000000 + year;
                }
                conn.close();
            }
            rs.close();
        }
        return i;
    }

    public boolean setDirectoryInactive(String hospitalName, int censusYear) throws SQLException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            PreparedStatement pstmt;
            int rows;
            try (Connection conn = myFactory.getConnection()) {
                String updateValidation = "UPDATE directory_health  SET `active`= ? WHERE `hospitalName` = ? and `censusYear` = ?;";
                pstmt = conn.prepareStatement(updateValidation);
                pstmt.setBoolean(1, false);
                pstmt.setString(2, hospitalName);
                pstmt.setInt(3, censusYear);
                rows = pstmt.executeUpdate();
            }
            pstmt.close();
            return rows == 1;
        } catch (SQLException ex) {
            getLogger(directoryHospitalDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean UpdateOldDirectory(String hospitalName,
            int censusYear, int totalDoctors,
            int totalNurses, int midwives, int numberofBeds,
            String isAccredited, String category, String telephone, String classification) throws SQLException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            PreparedStatement pstmt;
            int rows;

            try (Connection conn = myFactory.getConnection()) {
                String updateValidation = "INSERT INTO directory_health\n"
                        + "(formID, censusYear, hospitalName, classification, address, \n"
                        + "telephone, totalDoctors, totalNurses, totalMidwives, numberOfBeds,\n"
                        + "category, isAccredited, latitude, longitude, `alter`, active)\n"
                        + "				SELECT ?, ?, d.hospitalName, ? , d.address, \n"
                        + "				?, ?, ?, ?, ?,\n"
                        + "				? , ?, d.latitude, d.longitude, 1 , 1\n"
                        + "                         FROM directory_health d\n"
                        + "                         WHERE d.active = '1'\n"
                        + "                         AND d.`alter` = '0' AND censusYear = ? AND hospitalName = ?;";

                pstmt = conn.prepareStatement(updateValidation);
                pstmt.setInt(1, 90000000 + censusYear);
                pstmt.setInt(2, censusYear + 1);
                pstmt.setString(3, classification);
                pstmt.setString(4, telephone);
                pstmt.setInt(5, totalDoctors);
                pstmt.setInt(6, totalNurses);
                pstmt.setInt(7, midwives);
                pstmt.setInt(8, numberofBeds);
                pstmt.setString(9, category);
                pstmt.setBoolean(10, Boolean.parseBoolean(isAccredited));
                pstmt.setInt(11, censusYear);
                pstmt.setString(12, hospitalName);

                rows = pstmt.executeUpdate();

                conn.close();
            }
            pstmt.close();
            if (rows > 1) {
                boolean x = setAlter(censusYear, 1, hospitalName);
                return x;
            }

        } catch (SQLException ex) {
            getLogger(directoryHospitalDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean SubmitAllHealth(int year, int uploadedBy) throws SQLException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            PreparedStatement pstmt;
            int rows = 0;

            RecordDAO recorddao = new RecordDAO();
            boolean x;
            x = recorddao.checkExistRecord(90000000, year);
            if (x == false) {
                x = recorddao.newRecord(90000000 + year, year, uploadedBy);
            } else {
                x = deleteHospitalDRecord(year);
                if (x) {
                    x = setActiveOrInactive(year - 1, 1);
                }
            }
            if (x) {
                try (Connection conn = myFactory.getConnection()) {
                    String updateValidation = "INSERT INTO directory_health\n"
                            + "(formID, censusYear, hospitalName, classification, address, \n"
                            + "telephone, totalDoctors, totalNurses, totalMidwives, numberOfBeds,\n"
                            + "category, isAccredited, latitude, longitude, `alter`, active)\n"
                            + "				SELECT ?, ?, d.hospitalName, d.classification, d.address, \n"
                            + "				d.telephone, d.totalDoctors, d.totalNurses, d.totalMidwives, d.numberOfBeds,\n"
                            + "				d.category, d.isAccredited, d.latitude, d.longitude, 0 , d.active\n"
                            + "                         FROM directory_health d\n"
                            + "                         WHERE d.active = '1'\n"
                            + "                         AND d.`alter` = '0';";
                    pstmt = conn.prepareStatement(updateValidation);
                    pstmt.setInt(1, 90000000 + year);
                    pstmt.setInt(2, year);
                    rows = pstmt.executeUpdate();
                    conn.close();
                    pstmt.close();
                }

            }

            if (rows > 0) {
                boolean y = setActiveOrInactive(year - 1, 0);
                if (y) {
                    y = UpdateValidation(90000000 + year);
                }

                return y;
            }

        } catch (SQLException ex) {
            getLogger(directoryHospitalDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean setActiveOrInactive(int year, int decision) throws SQLException {
        boolean rows = false;
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();

            try (Connection conn = myFactory.getConnection()) {

                //SET RECENT TO INACTIVE
                String updateS = "UPDATE directory_health  SET `active` = ? \n"
                        + "WHERE censusYear = ?";
                PreparedStatement pstmtS = conn.prepareStatement(updateS);
                pstmtS.setInt(1, decision);
                pstmtS.setInt(2, year);

                int isDeleted = pstmtS.executeUpdate();

                if (isDeleted > 0) {
                    conn.close();
                    pstmtS.close();
                    rows = true;
                }

            }
        } catch (SQLException ex) {
            getLogger(directoryHospitalDAO.class.getName()).log(SEVERE, null, ex);
        }
        return rows;
    }

    public boolean setAlter(int year, int decision, String hospitalName) throws SQLException {
        boolean rows = false;
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();

            try (Connection conn = myFactory.getConnection()) {

                //SET RECENT TO INACTIVE
                String updateS = "UPDATE directory_health  SET `alter` = ? \n"
                        + "WHERE censusYear = ? and hospitalName ?=  ";
                PreparedStatement pstmtS = conn.prepareStatement(updateS);
                pstmtS.setInt(1, decision);
                pstmtS.setInt(2, year);
                pstmtS.setString(3, hospitalName);

                int isDeleted = pstmtS.executeUpdate();

                if (isDeleted > 0) {
                    conn.close();
                    pstmtS.close();
                    rows = true;
                }

            }
        } catch (SQLException ex) {
            getLogger(directoryHospitalDAO.class.getName()).log(SEVERE, null, ex);
        }
        return rows;
    }

    public boolean deleteHospitalDRecord(int year) throws SQLException {
        boolean rows = false;
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            PreparedStatement pstmt1;

            try (Connection conn = myFactory.getConnection()) {

                String elem_classrooms = "DELETE FROM directory_health WHERE  censusYear = ? AND `alter`= 0";
                pstmt1 = conn.prepareStatement(elem_classrooms);
                pstmt1.setInt(1, year);

                int isDeleted2 = pstmt1.executeUpdate();

                if (isDeleted2 > 0) {
                    conn.close();
                    pstmt1.close();
                    rows = true;
                } else if (isDeleted2 == 0) {
                    conn.close();
                    pstmt1.close();
                    rows = true;
                }

            }
        } catch (SQLException ex) {
            getLogger(directoryHospitalDAO.class.getName()).log(SEVERE, null, ex);
        }
        return rows;
    }

    public boolean deleteHospitalOneRecord(int year, String hospitalName) throws SQLException {
        boolean rows = false;
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            PreparedStatement pstmt1;

            try (Connection conn = myFactory.getConnection()) {

                String elem_classrooms = "DELETE FROM directory_health WHERE  censusYear = ? AND hospitalName = ?";
                pstmt1 = conn.prepareStatement(elem_classrooms);
                pstmt1.setInt(1, year);
                pstmt1.setString(2, hospitalName);

                int isDeleted2 = pstmt1.executeUpdate();

                if (isDeleted2 > 0) {
                    conn.close();
                    pstmt1.close();
                    rows = true;
                }

            }
        } catch (SQLException ex) {
            getLogger(directoryHospitalDAO.class.getName()).log(SEVERE, null, ex);
        }
        return rows;
    }

    public boolean UpdateDirectory(String hospitalName,
            int censusYear, int totalDoctors,
            int totalNurses, int midwives, int numberofBeds,
            String isAccredited, String category,
            String telephone, String classification) throws SQLException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            PreparedStatement pstmt;
            int rows;
            try (Connection conn = myFactory.getConnection()) {
                String updateValidation = "UPDATE directory_health "
                        + " SET `totalDoctors`= ?, "
                        + " `totalNurses` = ?, "
                        + " `totalMidwives` = ?, "
                        + " `numberOfBeds` = ?,"
                        + " `category` =? , `isAccredited` = ?,"
                        + " `classification` = ?, `telephone` = ? "
                        + "  WHERE `hospitalName` = ? and `censusYear` = ?;";
                pstmt = conn.prepareStatement(updateValidation);
                pstmt.setInt(1, totalDoctors);
                pstmt.setInt(2, totalNurses);
                pstmt.setInt(3, midwives);
                pstmt.setInt(4, numberofBeds);
                pstmt.setString(5, category);
                pstmt.setBoolean(6, Boolean.parseBoolean(isAccredited));
                pstmt.setString(7, classification);
                pstmt.setString(8, telephone);
                pstmt.setString(9, hospitalName);
                pstmt.setInt(10, censusYear);
                rows = pstmt.executeUpdate();

                conn.close();
            }
            pstmt.close();
            return rows == 1;
        } catch (SQLException ex) {
            getLogger(directoryHospitalDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean UpdateValidation(int formID) throws SQLException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            PreparedStatement pstmt;
            int rows;
            try (Connection conn = myFactory.getConnection()) {
                String updateValidation = "UPDATE RECORDS  SET `validated`= 1 WHERE `FORMID` = ?;";
                pstmt = conn.prepareStatement(updateValidation);
                pstmt.setInt(1, formID);

                rows = pstmt.executeUpdate();

                pstmt.close();
                conn.close();
            }
            if (rows > 0) {
                return rows == 1;
            }
        } catch (SQLException ex) {
            getLogger(directoryHospitalDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean addNewHospital(DirectoryHealth directoryHealth) {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            int rows;

            try (Connection conn = myFactory.getConnection()) {
                String query = "INSERT INTO directory_health "
                        + " (formID, censusYear, hospitalName,"
                        + " classification, address, telephone, "
                        + " totalDoctors, totalNurses, totalMidwives, "
                        + " numberOfBeds, category, isAccredited, "
                        + " latitude, longitude, `alter` , `active`) "
                        + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); ";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, directoryHealth.getFormID());
                pstmt.setInt(2, directoryHealth.getYear());
                pstmt.setString(3, directoryHealth.getHospitalName());
                pstmt.setString(4, directoryHealth.getClassification());
                pstmt.setString(5, directoryHealth.getAddresss());
                pstmt.setString(6, directoryHealth.getTelephone());
                pstmt.setInt(7, directoryHealth.getNumberOfDoctor());
                pstmt.setInt(8, directoryHealth.getNumberOfNurses());
                pstmt.setInt(9, directoryHealth.getNumberOfMidwives());
                pstmt.setInt(10, directoryHealth.getNumberOfBeds());
                pstmt.setString(11, directoryHealth.getCategory());
                pstmt.setBoolean(12, directoryHealth.isAccreditation());
                pstmt.setDouble(13, directoryHealth.getLatitude());
                pstmt.setDouble(14, directoryHealth.getLongitude());
                pstmt.setBoolean(15, true);
                pstmt.setBoolean(16, true);

                rows = pstmt.executeUpdate();

                pstmt.close();
                conn.close();
            }
            return rows == 1;
        } catch (SQLException ex) {
            getLogger(directoryHospitalDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }
}
