/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author Shermaine
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
}
