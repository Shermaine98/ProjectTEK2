/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package dao.charts;

import dao.demo.HighestCompletedDAO;
import db.DBConnectionFactoryStorageDB;
import static db.DBConnectionFactoryStorageDB.getInstance;
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
public class HighestCompletedChart {
    public ArrayList<HighestCompletedAgeGroup> retrieveTotalHighestCompleted(int formID) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            ArrayList<HighestCompletedAgeGroup> highestCompleted = new ArrayList<HighestCompletedAgeGroup>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT HIGHESTCOMPLETED, SUM(COUNT) AS 'COUNT' FROM HIGHESTCOMPLETED\n" +
                    "WHERE   FORMID = ? \n" +
                    "        AND LOCATION != 'CALOOCAN CITY'\n" +
                    "        AND HIGHESTCOMPLETED NOT LIKE '%ELEMENTARY'\n" +
                    "        AND HIGHESTCOMPLETED NOT LIKE'%High School'\n" +
                    "        AND HIGHESTCOMPLETED NOT LIKE '%Post Secondary'\n" +
                    "        AND SEX != 'BOTH SEXES'\n"  +
                    "GROUP BY HIGHESTCOMPLETED;");
                pstmt.setInt(1, formID);
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    HighestCompletedAgeGroup temp = new HighestCompletedAgeGroup();
                    temp.sethighestCompleted(rs.getString("HIGHESTCOMPLETED"));
                    temp.setCount(rs.getInt("COUNT"));
                    highestCompleted.add(temp);
                }
                pstmt.close();
            }
            return highestCompleted;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<HighestCompleted> retrieveLocationHighestCompleted(int formID, String sex) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            ArrayList<HighestCompleted> highestCompleted = new ArrayList<HighestCompleted>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT SEX, LOCATION  \n" +
                "FROM HIGHESTCOMPLETEDAGEGROUP\n" +
                "WHERE 	FORMID = ? \n" +
                "       AND LOCATION != 'CALOOCAN CITY' \n" +
                "       AND SEX = ?" +
                "GROUP BY SEX, LOCATION");
                pstmt.setInt(1, formID);
                pstmt.setString(2, sex);
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    HighestCompleted temp = new HighestCompleted();
                    temp.setLocation(rs.getString("LOCATION"));
                    temp.setSex(rs.getString("SEX"));
                    
                    PreparedStatement pstmt2 = conn.prepareStatement("SELECT SEX, LOCATION,  HIGHESTCOMPLETED, SUM(COUNT) as 'count' \n" +
                    "FROM    HIGHESTCOMPLETED \n" +
                    "WHERE   FORMID = ? \n" +
                    "        AND LOCATION = ? \n" +
                    "        AND HIGHESTCOMPLETED NOT LIKE '%ELEMENTARY'\n" +
                    "        AND HIGHESTCOMPLETED NOT LIKE'%High School'\n" +
                    "        AND HIGHESTCOMPLETED NOT LIKE '%Post Secondary'\n" +
                    "        AND SEX = ?\n" +
                    "GROUP BY SEX, LOCATION, HIGHESTCOMPLETED ;");
                    pstmt2.setInt(1, formID);
                    pstmt2.setString(2, temp.getLocation());
                    pstmt2.setString(3, temp.getSex());
                    ResultSet rs2 = pstmt2.executeQuery();
                    ArrayList<HighestCompletedAgeGroup> arrAgeGroup = new ArrayList<HighestCompletedAgeGroup>();
                    while(rs2.next()){
                        HighestCompletedAgeGroup temp2 = new HighestCompletedAgeGroup();
                        temp2.sethighestCompleted(rs2.getString("HIGHESTCOMPLETED"));
                        temp2.setCount(rs2.getInt("COUNT"));
                        arrAgeGroup.add(temp2);
                    }
                    temp.setHighestCompletedAgeGroup(arrAgeGroup);
                    pstmt2.close();
                    highestCompleted.add(temp);
                }
                pstmt.close();
            }
            return highestCompleted;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<HighestCompleted> retrieveAgeGroupHighestCompleted(int formID, String sex) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            ArrayList<HighestCompleted> highestCompleted = new ArrayList<HighestCompleted>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * \n" +
                "FROM HIGHESTCOMPLETEDAGEGROUP \n" +
                "WHERE FORMID = ? \n" +
                "      AND SEX = ? ;");
                pstmt.setInt(1, formID);
                pstmt.setString(2, sex);
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    HighestCompleted temp = new HighestCompleted();
                    temp.setYear(rs.getInt("CENSUSYEAR"));
                    temp.setLocation(rs.getString("LOCATION"));
                    temp.setSex(rs.getString("SEX"));
                    temp.setAgeGroup(rs.getString("AGEGROUP"));
                    temp.setTotal(rs.getInt("TOTAL"));
                    
                    PreparedStatement pstmt2 = conn.prepareStatement("SELECT * \n" +
                    "FROM 	HIGHESTCOMPLETED\n" +
                    "WHERE 	FORMID = ? \n" +
                    "		AND SEX = ? \n" +
                    "		AND LOCATION = ? \n" +
                    "           AND AGEGROUP = ? \n" +
                    "		AND HIGHESTCOMPLETED NOT LIKE '%ELEMENTARY'\n" +
                    "           AND HIGHESTCOMPLETED NOT LIKE'%High School'\n" +
                    "           AND HIGHESTCOMPLETED NOT LIKE '%Post Secondary'");
                    pstmt2.setInt(1, formID);
                    pstmt2.setString(2, temp.getSex());
                    pstmt2.setString(3, temp.getLocation());
                    pstmt2.setString(4, temp.getageGroup());
                    ResultSet rs2 = pstmt2.executeQuery();
                    ArrayList<HighestCompletedAgeGroup> arrAgeGroup = new ArrayList<HighestCompletedAgeGroup>();
                    while(rs2.next()){
                        HighestCompletedAgeGroup temp2 = new HighestCompletedAgeGroup();
                        temp2.sethighestCompleted(rs2.getString("HIGHESTCOMPLETED"));
                        temp2.setCount(rs2.getInt("COUNT"));
                        arrAgeGroup.add(temp2);
                    }
                    temp.setHighestCompletedAgeGroup(arrAgeGroup);
                    pstmt2.close();
                    highestCompleted.add(temp);
                }
                pstmt.close();
            }
            return highestCompleted;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<HighestCompleted> retrieveTable(int formID) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            ArrayList<HighestCompleted> highestCompleted = new ArrayList<HighestCompleted>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * \n" +
                "FROM HIGHESTCOMPLETEDAGEGROUP \n" +
                "WHERE FORMID = ?;");
                pstmt.setInt(1, formID);
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    HighestCompleted temp = new HighestCompleted();
                    temp.setYear(rs.getInt("CENSUSYEAR"));
                    temp.setLocation(rs.getString("LOCATION"));
                    temp.setSex(rs.getString("SEX"));
                    temp.setAgeGroup(rs.getString("AGEGROUP"));
                    temp.setTotal(rs.getInt("TOTAL"));
                    
                    PreparedStatement pstmt2 = conn.prepareStatement("SELECT * \n" +
                    "FROM 	HIGHESTCOMPLETED\n" +
                    "WHERE 	FORMID = ? \n" +
                    "		AND SEX = ? \n" +
                    "		AND LOCATION = ? \n" +
                    "           AND AGEGROUP = ?");
                    pstmt2.setInt(1, formID);
                    pstmt2.setString(2, temp.getSex());
                    pstmt2.setString(3, temp.getLocation());
                    pstmt2.setString(4, temp.getageGroup());
                    ResultSet rs2 = pstmt2.executeQuery();
                    ArrayList<HighestCompletedAgeGroup> arrAgeGroup = new ArrayList<HighestCompletedAgeGroup>();
                    while(rs2.next()){
                        HighestCompletedAgeGroup temp2 = new HighestCompletedAgeGroup();
                        temp2.sethighestCompleted(rs2.getString("HIGHESTCOMPLETED"));
                        temp2.setCount(rs2.getInt("COUNT"));
                        arrAgeGroup.add(temp2);
                    }
                    temp.setHighestCompletedAgeGroup(arrAgeGroup);
                    pstmt2.close();
                    highestCompleted.add(temp);
                }
                pstmt.close();
            }
            return highestCompleted;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
}
