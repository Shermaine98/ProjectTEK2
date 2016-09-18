/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.charts;

import dao.demo.HighestCompletedDAO;
import db.DBConnectionFactoryStorageDB;
import static db.DBConnectionFactoryStorageDB.getInstance;
import model.demo.ByAgeGroupSex;
import model.demo.MaritalStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Gian
 */
public class MaritalStatusChart {
    public MaritalStatus retrieveOverAllMaritalStatus(int formID) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            MaritalStatus maritalStatus = new MaritalStatus();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT SUM(SINGLE) AS 'SINGLE',\n" +
                "           SUM(MARRIED) AS 'MARRIED',\n" +
                "           SUM(WIDOWED) AS 'WIDOWED',\n" +
                "           SUM(divorcedSeparated) AS 'DIVORCEDSEPARATED',\n" +
                "           SUM(COMMONLAWLIVEIN) AS 'COMMONLAWLIVEIN',\n" +
                "           SUM(UNKOWN) AS 'UNKNOWN'\n" +
                "FROM MARITAL_STATUS\n" +
                "WHERE FORMID = ? AND AGEGROUP != 'TOTAL' AND SEX != 'BOTH SEXES' AND LOCATION != 'CALOOCAN CITY'");
                pstmt.setInt(1, formID);
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    MaritalStatus temp = new MaritalStatus();
                    temp.setSingle(rs.getInt("SINGLE"));
                    temp.setMarried(rs.getInt("MARRIED"));
                    temp.setWidowed(rs.getInt("WIDOWED"));
                    temp.setDivorcedSeparated(rs.getInt("DIVORCEDSEPARATED"));
                    temp.setCommonLawLiveIn(rs.getInt("COMMONLAWLIVEIN"));
                    temp.setUnknown(rs.getInt("UNKNOWN"));
                    maritalStatus = temp;
                }
                pstmt.close();
            }
            return maritalStatus;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<MaritalStatus> retrieveMaleBarangays(int formID) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            ArrayList<MaritalStatus> arrMaritalStatus = new ArrayList<MaritalStatus>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT LOCATION,\n" +
                "        SEX, \n" +
                "        SUM(SINGLE) AS 'SINGLE',\n" +
                "        SUM(MARRIED) AS 'MARRIED', \n" +
                "        SUM(WIDOWED) AS 'WIDOWED', \n" +
                "        SUM(DIVORCEDSEPARATED) AS 'DIVORCEDSEPARATED', \n" +
                "        SUM(COMMONLAWLIVEIN) AS COMMONLAWLIVEIN, \n" +
                "        SUM(UNKOWN) AS 'UNKNOWN'\n" +
                "FROM 	MARITAL_STATUS\n" +
                "WHERE FORMID = ? AND AGEGROUP!='TOTAL' AND LOCATION != 'CALOOCAN CITY' AND SEX = 'MALE'\n" +
                "GROUP BY LOCATION, SEX;");
                pstmt.setInt(1, formID);
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    MaritalStatus temp = new MaritalStatus();
                    temp.setLocation(rs.getString("LOCATION"));
                    temp.setSex(rs.getString("SEX"));
                    temp.setSingle(rs.getInt("SINGLE"));
                    temp.setMarried(rs.getInt("MARRIED"));
                    temp.setWidowed(rs.getInt("WIDOWED"));
                    temp.setDivorcedSeparated(rs.getInt("DIVORCEDSEPARATED"));
                    temp.setCommonLawLiveIn(rs.getInt("COMMONLAWLIVEIN"));
                    temp.setUnknown(rs.getInt("UNKNOWN"));
                    arrMaritalStatus.add(temp);
                }
                pstmt.close();
            }
            return arrMaritalStatus;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<MaritalStatus> retrieveFemaleBarangays(int formID) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            ArrayList<MaritalStatus> arrMaritalStatus = new ArrayList<MaritalStatus>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT LOCATION,\n" +
                "        SEX, \n" +
                "        SUM(SINGLE) AS 'SINGLE',\n" +
                "        SUM(MARRIED) AS 'MARRIED', \n" +
                "        SUM(WIDOWED) AS 'WIDOWED', \n" +
                "        SUM(DIVORCEDSEPARATED) AS 'DIVORCEDSEPARATED', \n" +
                "        SUM(COMMONLAWLIVEIN) AS COMMONLAWLIVEIN, \n" +
                "        SUM(UNKOWN) AS 'UNKNOWN'\n" +
                "FROM 	MARITAL_STATUS\n" +
                "WHERE FORMID = ? AND AGEGROUP!='TOTAL' AND LOCATION != 'CALOOCAN CITY' AND SEX = 'FEMALE'\n" +
                "GROUP BY LOCATION, SEX;");
                pstmt.setInt(1, formID);
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    MaritalStatus temp = new MaritalStatus();
                    temp.setLocation(rs.getString("LOCATION"));
                    temp.setSex(rs.getString("SEX"));
                    temp.setSingle(rs.getInt("SINGLE"));
                    temp.setMarried(rs.getInt("MARRIED"));
                    temp.setWidowed(rs.getInt("WIDOWED"));
                    temp.setDivorcedSeparated(rs.getInt("DIVORCEDSEPARATED"));
                    temp.setCommonLawLiveIn(rs.getInt("COMMONLAWLIVEIN"));
                    temp.setUnknown(rs.getInt("UNKNOWN"));
                    arrMaritalStatus.add(temp);
                }
                pstmt.close();
            }
            return arrMaritalStatus;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<MaritalStatus> retrieveMaleAgeGroup(int formID) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            ArrayList<MaritalStatus> arrMaritalStatus = new ArrayList<MaritalStatus>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM marital_status\n" +
                    "WHERE FORMID = ? "
                    + "AND SEX = 'MALE' "
                    + "AND LOCATION !='CALOOCAN CITY' "
                    + "AND AGEGROUP!='TOTAL'\n" +
                    "ORDER BY LOCATION;");
                pstmt.setInt(1, formID);
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    MaritalStatus temp = new MaritalStatus();
                    temp.setLocation(rs.getString("LOCATION"));
                    temp.setAgeGroup(rs.getString("AGEGROUP"));
                    temp.setSex(rs.getString("SEX"));
                    temp.setSingle(rs.getInt("SINGLE"));
                    temp.setMarried(rs.getInt("MARRIED"));
                    temp.setWidowed(rs.getInt("WIDOWED"));
                    temp.setDivorcedSeparated(rs.getInt("DIVORCEDSEPARATED"));
                    temp.setCommonLawLiveIn(rs.getInt("COMMONLAWLIVEIN"));
                    temp.setUnknown(rs.getInt("UNKOWN"));
                    arrMaritalStatus.add(temp);
                }
                pstmt.close();
            }
            return arrMaritalStatus;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<MaritalStatus> retrieveFemaleAgeGroup(int formID) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            ArrayList<MaritalStatus> arrMaritalStatus = new ArrayList<MaritalStatus>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM marital_status\n" +
                    "WHERE FORMID = ? "
                    + "AND SEX = 'FEMALE' "
                    + "AND LOCATION !='CALOOCAN CITY' "
                    + "AND AGEGROUP!='TOTAL'\n" +
                    "ORDER BY LOCATION;");
                pstmt.setInt(1, formID);
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    MaritalStatus temp = new MaritalStatus();
                    temp.setLocation(rs.getString("LOCATION"));
                    temp.setAgeGroup(rs.getString("AGEGROUP"));
                    temp.setSex(rs.getString("SEX"));
                    temp.setSingle(rs.getInt("SINGLE"));
                    temp.setMarried(rs.getInt("MARRIED"));
                    temp.setWidowed(rs.getInt("WIDOWED"));
                    temp.setDivorcedSeparated(rs.getInt("DIVORCEDSEPARATED"));
                    temp.setCommonLawLiveIn(rs.getInt("COMMONLAWLIVEIN"));
                    temp.setUnknown(rs.getInt("UNKOWN"));
                    arrMaritalStatus.add(temp);
                }
                pstmt.close();
            }
            return arrMaritalStatus;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    
}
