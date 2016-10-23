/*
 *  Copyright (C) ProjectTEK - DLSU CCS 2016
 *  All right Reserved   * 
 */

package dao.analysis;

import db.DBConnectionFactoryStarSchema;
import model.analysis.FactEnrollment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class FactEnrollmentDAO {

    public ArrayList<FactEnrollment> GetFactEnrollment() throws SQLException   {

        DBConnectionFactoryStarSchema myFactory =  DBConnectionFactoryStarSchema.getInstance();
        ArrayList<FactEnrollment> ArrfactEnrollment = new ArrayList<>();
        try (Connection conn = myFactory.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * from fact_enrollment");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                FactEnrollment temp = new FactEnrollment();
                temp.setCensusYear(rs.getInt("censusYear"));
                temp.setSchoolName(rs.getString("schoolName"));
                temp.setDistrict(rs.getString("district"));
                temp.setType(rs.getString("type"));
                temp.setLevel(rs.getString("level"));
                temp.setClassification(rs.getString("classification"));
                temp.setMaleCount(rs.getInt("maleCount"));
                temp.setFemaleCount(rs.getInt("femaleCount"));
                temp.setGenderDisparityIndex(rs.getDouble("genderDisparityIndex"));
                ArrfactEnrollment.add(temp);

            }
            pstmt.close();
        }
        return ArrfactEnrollment;
    }
    
    public ArrayList<FactEnrollment> retrieveKinderEnrollment() throws SQLException   {

        DBConnectionFactoryStarSchema myFactory =  DBConnectionFactoryStarSchema.getInstance();
        ArrayList<FactEnrollment> ArrfactEnrollment = new ArrayList<>();
        try (Connection conn = myFactory.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT *, IF (SUBSTRING(DISTRICT, 10, 5) = 'NORTH', 'NORTH', 'SOUTH') AS 'ZONE'\n" +
            "FROM starschema.fact_enrollment\n" +
            "WHERE LEVEL = 'KINDER';;");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                FactEnrollment temp = new FactEnrollment();
                temp.setCensusYear(rs.getInt("censusYear"));
                temp.setSchoolName(rs.getString("schoolName"));
                temp.setDistrict(rs.getString("district"));
                temp.setType(rs.getString("type"));
                temp.setLevel(rs.getString("level"));
                temp.setClassification(rs.getString("classification"));
                temp.setMaleCount(rs.getInt("maleCount"));
                temp.setFemaleCount(rs.getInt("femaleCount"));
                temp.setGenderDisparityIndex(rs.getDouble("genderDisparityIndex"));
                temp.setZone(rs.getString("zone"));
                ArrfactEnrollment.add(temp);
            }
            pstmt.close();
        }
        return ArrfactEnrollment;
    }
    
    public ArrayList<FactEnrollment> retrieveElementaryEnrollment() throws SQLException   {

        DBConnectionFactoryStarSchema myFactory =  DBConnectionFactoryStarSchema.getInstance();
        ArrayList<FactEnrollment> ArrfactEnrollment = new ArrayList<>();
        try (Connection conn = myFactory.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT *, IF (SUBSTRING(DISTRICT, 10, 5) = 'NORTH', 'NORTH', 'SOUTH') AS 'ZONE' \n" +
            "FROM starschema.fact_enrollment\n" +
            "WHERE LEVEL != 'KINDER' AND LEVEL!='SPED';");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                FactEnrollment temp = new FactEnrollment();
                temp.setCensusYear(rs.getInt("censusYear"));
                temp.setSchoolName(rs.getString("schoolName"));
                temp.setDistrict(rs.getString("district"));
                temp.setType(rs.getString("type"));
                temp.setLevel(rs.getString("level"));
                temp.setClassification(rs.getString("classification"));
                temp.setMaleCount(rs.getInt("maleCount"));
                temp.setFemaleCount(rs.getInt("femaleCount"));
                temp.setGenderDisparityIndex(rs.getDouble("genderDisparityIndex"));
                temp.setZone(rs.getString("zone"));
                ArrfactEnrollment.add(temp);
            }
            pstmt.close();
        }
        return ArrfactEnrollment;
    }
    
    public ArrayList<FactEnrollment> retrieveSchools() throws SQLException   {

        DBConnectionFactoryStarSchema myFactory =  DBConnectionFactoryStarSchema.getInstance();
        ArrayList<FactEnrollment> ArrfactEnrollment = new ArrayList<>();
        try (Connection conn = myFactory.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT schoolName, district, classification FROM starschema.fact_enrollment group by schoolName,district, classification order by schoolName, district, classification;");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                FactEnrollment temp = new FactEnrollment();
                temp.setSchoolName(rs.getString("schoolName"));
                temp.setDistrict(rs.getString("district"));
                temp.setClassification(rs.getString("classification"));
                ArrfactEnrollment.add(temp);
            }
            pstmt.close();
        }
        return ArrfactEnrollment;
    }
    
    public ArrayList<FactEnrollment> retrieveEnrollmentForSchoolGoingAge() throws SQLException   {

        DBConnectionFactoryStarSchema myFactory =  DBConnectionFactoryStarSchema.getInstance();
        ArrayList<FactEnrollment> ArrfactEnrollment = new ArrayList<>();
        try (Connection conn = myFactory.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT CENSUSYEAR, DISTRICT, SUM(MALECOUNT + FEMALECOUNT) AS 'ENROLLMENT', IF (SUBSTRING(DISTRICT, 10, 5) = 'NORTH', 'NORTH', 'SOUTH') AS 'ZONE'\n" +
            "FROM FACT_ENROLLMENT\n" +
            "GROUP BY CENSUSYEAR, DISTRICT");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                FactEnrollment temp = new FactEnrollment();
                temp.setCensusYear(rs.getInt("censusYear"));
                temp.setDistrict(rs.getString("DISTRICT"));
                temp.setCount(rs.getInt("ENROLLMENT"));
                temp.setZone(rs.getString("ZONE"));
                ArrfactEnrollment.add(temp);
            }
            pstmt.close();
        }
        return ArrfactEnrollment;
    }
    
    public ArrayList<FactEnrollment> retrieveEstablishedNumberOfStudents() throws SQLException   {

        DBConnectionFactoryStarSchema myFactory =  DBConnectionFactoryStarSchema.getInstance();
        ArrayList<FactEnrollment> ArrfactEnrollment = new ArrayList<>();
        try (Connection conn = myFactory.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT censusYear, district, IF(level = 'KINDER', 'Pre Elementary', level) as 'level', SUM(maleCount) as 'maleCount', SUM(femaleCount) as 'femaleCount' FROM starschema.fact_enrollment group by censusYear, district, level order by censusYear, district, level;");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                FactEnrollment temp = new FactEnrollment();
                temp.setCensusYear(rs.getInt("censusYear"));
                temp.setDistrict(rs.getString("DISTRICT"));
                temp.setLevel(rs.getString("level"));
                temp.setMaleCount(rs.getInt("maleCount"));
                temp.setFemaleCount(rs.getInt("femaleCount"));
                ArrfactEnrollment.add(temp);
            }
            pstmt.close();
        }
        return ArrfactEnrollment;
    }
}
