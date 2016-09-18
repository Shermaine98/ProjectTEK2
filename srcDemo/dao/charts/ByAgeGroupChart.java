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
public class ByAgeGroupChart {
    public ArrayList<ByAgeGroupSex> retrieveByAgeGroupSex(int formID) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            ArrayList<ByAgeGroupSex> arrByAgeGroup = new ArrayList<>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT LOCATION, AGEGROUP, TOTALMALE, TOTALFEMALE\n"
                        + "FROM AGE_GROUP\n"
                        + "WHERE FORMID=?\n"
                        + "AND AGEGROUP!='ALL AGES'\n" +
                        "    AND AGEGROUP!='18 AND OVER'\n" +
                        "    AND AGEGROUP!='0 - 17'");
                pstmt.setInt(1, formID);
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    ByAgeGroupSex temp = new ByAgeGroupSex();
                    temp.setBarangay(rs.getString("LOCATION"));
                    temp.setAgeGroup(rs.getString("AGEGROUP"));
                    temp.setMaleCount(rs.getInt("TOTALMALE"));
                    temp.setFemaleCount(rs.getInt("TOTALFEMALE"));
                    arrByAgeGroup.add(temp);
                }
                pstmt.close();
            }
            return arrByAgeGroup;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<ByAgeGroupSex> retrieveAgeGroupSex(int formID) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            ArrayList<ByAgeGroupSex> arrByAgeGroup = new ArrayList<>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT AGEGROUP, SUM(TOTALMALE) AS 'TOTALMALE', SUM(TOTALFEMALE) AS 'TOTALFEMALE'\n" +
                "FROM 	AGE_GROUP\n" +
                "WHERE 	FORMID=?\n" +
                "	AND AGEGROUP!='ALL AGES'\n" +
                "	AND AGEGROUP!='18 AND OVER'\n" +
                "	AND AGEGROUP!='0 - 17'\n" +
                "       AND LOCATION!='CALOOCAN CITY'\n" +
                "GROUP BY AGEGROUP\n" +
                "order by length(AGEGROUP), AGEGROUP");
                pstmt.setInt(1, formID);
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    ByAgeGroupSex temp = new ByAgeGroupSex();
                    temp.setAgeGroup(rs.getString("AGEGROUP"));
                    temp.setMaleCount(rs.getInt("TOTALMALE"));
                    temp.setFemaleCount(rs.getInt("TOTALFEMALE"));
                    arrByAgeGroup.add(temp);
                }
                pstmt.close();
            }
            return arrByAgeGroup;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<ByAgeGroupSex> retrieveAgeGroupPerBarangay(int formID) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            ArrayList<ByAgeGroupSex> arrByAgeGroup = new ArrayList<>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT LOCATION, AGEGROUP, TOTALMALE, TOTALFEMALE FROM storagedb.age_group\n" +
                    "WHERE  FORMID=?\n" +
                    "       AND AGEGROUP!='ALL AGES'\n" +
                    "       AND AGEGROUP!='18 AND OVER'\n" +
                    "       AND AGEGROUP!='0 - 17'\n" +
                    "       AND LOCATION!='CALOOCAN CITY'\n" +
                    "order by length(LOCATION), LOCATION;");
                pstmt.setInt(1, formID);
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    ByAgeGroupSex temp = new ByAgeGroupSex();
                    temp.setBarangay(rs.getString("LOCATION"));
                    temp.setAgeGroup(rs.getString("AGEGROUP"));
                    temp.setMaleCount(rs.getInt("TOTALMALE"));
                    temp.setFemaleCount(rs.getInt("TOTALFEMALE"));
                    arrByAgeGroup.add(temp);
                }
                pstmt.close();
            }
            return arrByAgeGroup;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<ByAgeGroupSex> retrieveTotalMaleFemalePerBgy(int formID) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            ArrayList<ByAgeGroupSex> arrByAgeGroup = new ArrayList<>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT LOCATION, SUM(TOTALMALE) AS 'MALE', SUM(TOTALFEMALE) AS 'Female'\n" +
                        "FROM AGE_GROUP\n" +
                        "WHERE FORMID=? AND LOCATION != 'CALOOCAN CITY'\n" +
                        "GROUP BY LOCATION\n" +
                        "ORDER BY LOCATION;");
                pstmt.setInt(1, formID);
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    ByAgeGroupSex temp = new ByAgeGroupSex();
                    temp.setBarangay(rs.getString("LOCATION"));
                    temp.setMaleCount(rs.getInt("MALE"));
                    temp.setFemaleCount(rs.getInt("Female"));
                    arrByAgeGroup.add(temp);
                }
                pstmt.close();
            }
            return arrByAgeGroup;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    public ByAgeGroupSex retrieveTotalFemale(int formID) throws ParseException{
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            ByAgeGroupSex ByAgeGroup = new ByAgeGroupSex();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT SUM(TOTALMALE) AS 'TOTALMALE', SUM(TOTALFEMALE) AS 'TOTALFEMALE'\n" +
                        "FROM AGE_GROUP\n" +
                        "WHERE FORMID=?");
                pstmt.setInt(1, formID);
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    ByAgeGroupSex temp = new ByAgeGroupSex();
                    temp.setBarangay("CALOOCAN CITY");
                    temp.setMaleCount(rs.getInt("TOTALMALE"));
                    temp.setFemaleCount(rs.getInt("TOTALFEMALE"));
                    ByAgeGroup = temp;
                }
                pstmt.close();
            }
            return ByAgeGroup;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
}
