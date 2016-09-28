/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.analysis;

import db.DBConnectionFactoryStarSchema;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.analysis.FactEnrollment;
import model.analysis.FactSchoolTeachersFacilities;

/**
 *
 * @author giancarloroxas
 */
public class FactSchoolTeachersFacilitiesDAO {
    public ArrayList<FactSchoolTeachersFacilities> retrieveNumberOfClassrooms() throws SQLException   {

        DBConnectionFactoryStarSchema myFactory =  DBConnectionFactoryStarSchema.getInstance();
        ArrayList<FactSchoolTeachersFacilities> arrClassrooms = new ArrayList<>();
        try (Connection conn = myFactory.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT CENSUSYEAR, DISTRICT, NOOFCLASSROOMS, IF (SUBSTRING(DISTRICT, 10, 5) = 'NORTH', 'NORTH', 'SOUTH') AS 'ZONE' \n" +
"            FROM fact_schoolteachersfacilities \n" +
"            GROUP BY CENSUSYEAR, DISTRICT ;");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                FactSchoolTeachersFacilities temp = new FactSchoolTeachersFacilities();
                temp.setCensusYear(rs.getInt("CENSUSYEAR"));
                temp.setDistrict(rs.getString("DISTRICT"));
                temp.setNoOfClassrooms(rs.getInt("NOOFCLASSROOMS"));
                temp.setZone(rs.getString("ZONE"));
                arrClassrooms.add(temp);
            }
            pstmt.close();
        }
        return arrClassrooms;
    }
    
    public ArrayList<FactSchoolTeachersFacilities> retrieveNumberOfClassroomsTeachers() throws SQLException   {

        DBConnectionFactoryStarSchema myFactory =  DBConnectionFactoryStarSchema.getInstance();
        ArrayList<FactSchoolTeachersFacilities> arrClassrooms = new ArrayList<>();
        try (Connection conn = myFactory.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT CENSUSYEAR, DISTRICT, NOOFCLASSROOMS, NOOFTEACHERS, IF (SUBSTRING(DISTRICT, 10, 5) = 'NORTH', 'NORTH', 'SOUTH') AS 'ZONE' \n" +
"            FROM fact_schoolteachersfacilities \n" +
"            GROUP BY CENSUSYEAR, DISTRICT; ");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                FactSchoolTeachersFacilities temp = new FactSchoolTeachersFacilities();
                temp.setCensusYear(rs.getInt("CENSUSYEAR"));
                temp.setDistrict(rs.getString("DISTRICT"));
                temp.setNoOfClassrooms(rs.getInt("NOOFCLASSROOMS"));
                temp.setNoOfTeachers(rs.getInt("NOOFTEACHERS"));
                temp.setZone(rs.getString("ZONE"));
                arrClassrooms.add(temp);
            }
            pstmt.close();
        }
        return arrClassrooms;
    }
}
