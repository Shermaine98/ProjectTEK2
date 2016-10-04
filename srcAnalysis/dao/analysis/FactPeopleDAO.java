/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.analysis;

import dao.demo.HighestCompletedDAO;
import db.DBConnectionFactoryStarSchema;
import db.DBConnectionFactoryStorageDB;
import static db.DBConnectionFactoryStorageDB.getInstance;
import model.analysis.FactPeople;
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
import model.analysis.FactDemo;

/**
 *
 * @author Gian
 */
public class FactPeopleDAO {
    public ArrayList<FactPeople> retrieveByDistrict() throws ParseException {
        try {
            DBConnectionFactoryStarSchema myFactory = DBConnectionFactoryStarSchema.getInstance();
            ArrayList<FactPeople> factPeople = new ArrayList<FactPeople>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT CENSUSYEAR, "
                        + "L.DISTRICT, "
                        + "SUM(TOTALNOOFPEOPLE) AS 'PEOPLE', "
                        + "IF (SUBSTRING(DISTRICT, 10, 5) = 'NORTH', 'NORTH', 'SOUTH') AS 'ZONE'\n" +
                    "FROM Fact_people FP JOIN DIM_BARANGAY BGY ON FP.BARANGAY = BGY.BARANGAY\n" +
                    "			 JOIN LOCATION L ON BGY.BARANGAY = L.BARANGAY\n" +
                    "                    AND L.BARANGAY = FP.BARANGAY\n" +
                    "GROUP BY CENSUSYEAR, L.DISTRICT;");
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    FactPeople temp = new FactPeople();
                    temp.setCensusYear(rs.getInt("CENSUSYEAR"));
                    temp.setDistrict(rs.getString("DISTRICT"));
                    temp.setTotalNoOfPeople(rs.getInt("PEOPLE"));
                    temp.setZone(rs.getString("ZONE"));
                    if(temp.getTotalNoOfPeople()<= 0)
                        temp.setIsOutlier(true);
                    else
                        temp.setIsOutlier(false);
                    
                    factPeople.add(temp);
                }
                pstmt.close();
            }
            return factPeople;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
     public ArrayList<FactDemo> GetFactPeople() throws SQLException {

        DBConnectionFactoryStarSchema myFactory = DBConnectionFactoryStarSchema.getInstance();
        ArrayList<FactDemo> ArrfactPeople = new ArrayList<>();
        try (Connection conn = myFactory.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * from fact_people");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                FactDemo temp = new FactDemo();
                temp.setCensusYear(rs.getInt("censusYear"));
                temp.setAgeBracket(rs.getString("ageBracket"));
                temp.setGender(rs.getString("gender"));
                temp.setBarangay(rs.getInt("barangay"));
                temp.setTotalNoOfPeople(rs.getInt("totalNoOfPeople"));
                temp.setTotalNoOfSingle(rs.getInt("totalNoOfSingle"));
                temp.setTotalNoOfMarried(rs.getInt("totalNoOfMarried"));
                temp.setTotalNoOfWidowed(rs.getInt("totalNoOfWidowed"));
                temp.setTotalNoOfDivorced(rs.getInt("totalNoOfDivorced"));
                temp.setTotalNoOfLiveIn(rs.getInt("totalNoOfLiveIn"));
                temp.setTotalNoOfUnknown(rs.getInt("totalNoOfUnknown"));
                temp.setTotalNoOfEdu(rs.getInt("totalNoOfNoEdu"));
                temp.setTotalNoOfPreSchool(rs.getInt("totalNoOfPreSchool"));
                temp.setTotalNoOf1sTo4thGrade(rs.getInt("totalNoOf1stTo4thGrade"));
                temp.setTotalNoOf5thto6thGrade(rs.getInt("totalNoOf5thTo6thGrade"));
                temp.setTotalNoOfElemGrad(rs.getInt("totalNoOfElemGrad"));
                temp.setTotalNoOfHSGrad(rs.getInt("totalNoOfHSUndergrad"));
                temp.setTotalNoOfHSGrad(rs.getInt("totalNoOfHSGrad"));
                temp.setTotalNoOfPSUndergrad(rs.getInt("totalNoOfPSUndergrad"));
                temp.setTotalNoOFPSGrad(rs.getInt("totalNoOfPSGrad"));
                temp.setTotalNoOFColUndergrand(rs.getInt("totalNoOfColUndergrad"));
                temp.setTotalNoOfDegreeHolder(rs.getInt("totalNoOfDegreeHolder"));
                temp.setTotalNOofBac(rs.getInt("totalNoOfBac"));
                temp.setTotalNoOfNotStated(rs.getInt("totalNoOfNotStated"));

                ArrfactPeople.add(temp);

            }

            pstmt.close();
        }
        return ArrfactPeople;
    }
    
    public ArrayList<FactPeople> retrieveByDistrict2() throws ParseException {
        try {
            DBConnectionFactoryStarSchema myFactory = DBConnectionFactoryStarSchema.getInstance();
            ArrayList<FactPeople> factPeople = new ArrayList<FactPeople>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT CENSUSYEAR, "
                        + "L.DISTRICT, "
                        + "SUM(TOTALNOOFPEOPLE) AS 'PEOPLE', "
                        + "IF (SUBSTRING(DISTRICT, 10, 5) = 'NORTH', 'NORTH', 'SOUTH') AS 'ZONE'\n" +
                    "FROM Fact_people FP JOIN DIM_BARANGAY BGY ON FP.BARANGAY = BGY.BARANGAY\n" +
                    "			 JOIN LOCATION L ON BGY.BARANGAY = L.BARANGAY\n" +
                    "                    AND L.BARANGAY = FP.BARANGAY\n" +
                    "GROUP BY CENSUSYEAR, L.DISTRICT;");
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    FactPeople temp = new FactPeople();
                    temp.setCensusYear(rs.getInt("CENSUSYEAR"));
                    temp.setDistrict(rs.getString("DISTRICT"));
                    temp.setTotalNoOfPeople(rs.getInt("PEOPLE"));
                    temp.setZone(rs.getString("ZONE"));
                    if(temp.getTotalNoOfPeople()<= 0)
                        temp.setIsOutlier(true);
                    else
                        temp.setIsOutlier(false);
                    
                    factPeople.add(temp);
                }
                pstmt.close();
            }
            return factPeople;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<FactPeople> retrieveSchoolGoingAge() throws ParseException {
        try {
            DBConnectionFactoryStarSchema myFactory = DBConnectionFactoryStarSchema.getInstance();
            ArrayList<FactPeople> factPeople = new ArrayList<FactPeople>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT FP.CENSUSYEAR, FP.BARANGAY, SUM(TOTALNOOFPEOPLE) AS 'PEOPLE', DISTRICT, IF (SUBSTRING(DISTRICT, 10, 5) = 'NORTH', 'NORTH', 'SOUTH') AS 'ZONE'\n" +
                "FROM FACT_PEOPLE FP JOIN DIM_BARANGAY DB ON DB.BARANGAY = FP.BARANGAY \n" +
                "		     JOIN LOCATION L ON FP.BARANGAY = L.BARANGAY AND FP.BARANGAY = DB.BARANGAY\n" +
                "WHERE    FP.AGEBRACKET = '5 - 9' \n" +
                "      OR FP.AGEBRACKET = '10 - 14' \n" +
//                "      OR FP.AGEBRACKET = '15 - 19' \n" + //ELEMENTARY SCHOOL-GOING AGE ONLY
//                "      OR FP.AGEBRACKET = '20 - 24' \n" + //http://www.gov.ph/k-12/  http://www.classbase.com/Countries/philippines/Education-System
                "GROUP BY FP.BARANGAY, FP.CENSUSYEAR\n" + 
                "ORDER BY CENSUSYEAR, BARANGAY;");
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    FactPeople temp = new FactPeople();
                    temp.setCensusYear(rs.getInt("CENSUSYEAR"));
                    temp.setBarangay(rs.getInt("BARANGAY"));
                    temp.setDistrict(rs.getString("DISTRICT"));
                    temp.setTotalNoOfPeople(rs.getInt("PEOPLE"));
                    temp.setZone(rs.getString("ZONE"));
                    if(temp.getTotalNoOfPeople()<= 0)
                        temp.setIsOutlier(true);
                    else
                        temp.setIsOutlier(false);
                    factPeople.add(temp);
                }
                pstmt.close();
            }
            return factPeople;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<FactPeople> retrieveAllPeople() throws ParseException {
        try {
            DBConnectionFactoryStarSchema myFactory = DBConnectionFactoryStarSchema.getInstance();
            ArrayList<FactPeople> factPeople = new ArrayList<FactPeople>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT FP.CENSUSYEAR, FP.BARANGAY, SUM(TOTALNOOFPEOPLE) AS 'PEOPLE', DISTRICT, IF (SUBSTRING(DISTRICT, 10, 5) = 'NORTH', 'NORTH', 'SOUTH') AS 'ZONE'\n" +
                "FROM FACT_PEOPLE FP JOIN DIM_BARANGAY DB ON DB.BARANGAY = FP.BARANGAY \n" +
                "		     JOIN LOCATION L ON FP.BARANGAY = L.BARANGAY AND FP.BARANGAY = DB.BARANGAY\n" +
                "GROUP BY FP.BARANGAY, FP.CENSUSYEAR\n" +
                "ORDER BY FP.BARANGAY, FP.CENSUSYEAR;");
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    FactPeople temp = new FactPeople();
                    temp.setCensusYear(rs.getInt("CENSUSYEAR"));
                    temp.setBarangay(rs.getInt("BARANGAY"));
                    temp.setDistrict(rs.getString("DISTRICT"));
                    temp.setTotalNoOfPeople(rs.getInt("PEOPLE"));
                    temp.setZone(rs.getString("ZONE"));
                    if(temp.getTotalNoOfPeople()<= 0)
                        temp.setIsOutlier(true);
                    else
                        temp.setIsOutlier(false);
                    factPeople.add(temp);
                }
                pstmt.close();
            }
            return factPeople;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<FactPeople> retrieveMaritalStatus() throws ParseException {
        try {
            DBConnectionFactoryStarSchema myFactory = DBConnectionFactoryStarSchema.getInstance();
            ArrayList<FactPeople> factPeople = new ArrayList<FactPeople>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT GENDER, FP.CENSUSYEAR, FP.BARANGAY, \n" +
"			 SUM(totalNoOfWidowed) AS 'WIDOWED', \n" +
            "            SUM(totalNoOfUnknown) AS 'UNKNOWN', \n" +
            "            SUM(totalNoOfMarried) AS 'MARRIED', \n" +
            "            SUM(totalNoOfDivorced) AS 'DIVORCED', \n" +
            "            SUM(totalNoOfLiveIn) AS 'LIVE-IN', \n" +
            "            SUM(totalNoOfSingle) AS 'SINGLE',\n" +
"                        DISTRICT, IF (SUBSTRING(DISTRICT, 10, 5) = 'NORTH', 'NORTH', 'SOUTH') AS 'ZONE'\n" +
"                FROM FACT_PEOPLE FP JOIN DIM_BARANGAY DB ON DB.BARANGAY = FP.BARANGAY \n" +
"                		     JOIN LOCATION L ON FP.BARANGAY = L.BARANGAY AND FP.BARANGAY = DB.BARANGAY\n" +
"                GROUP BY  CENSUSYEAR, DISTRICT, FP.BARANGAY, GENDER\n" +
"                ORDER BY CENSUSYEAR, DISTRICT, FP.BARANGAY, GENDER");
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    FactPeople temp = new FactPeople();
                    temp.setCensusYear(rs.getInt("CENSUSYEAR"));
                    temp.setBarangay(rs.getInt("BARANGAY"));
                    temp.setDistrict(rs.getString("DISTRICT"));
                    temp.setTotalNoOfWidowed(rs.getInt("WIDOWED"));
                    temp.setTotalNoOfUnknown(rs.getInt("UNKNOWN"));
                    temp.setTotalNoOfMarried(rs.getInt("MARRIED"));
                    temp.setTotalNoOfDivorced(rs.getInt("DIVORCED"));
                    temp.setTotalNoOfLiveIn(rs.getInt("LIVE-IN"));
                    temp.setTotalNoOfSingle(rs.getInt("SINGLE"));
                    temp.setZone(rs.getString("ZONE"));
                    temp.setGender(rs.getString("GENDER"));
                    if(temp.getTotalNoOfPeople()<= 0)
                        temp.setIsOutlier(true);
                    else
                        temp.setIsOutlier(false);
                    factPeople.add(temp);
                }
                pstmt.close();
            }
            return factPeople;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
}
