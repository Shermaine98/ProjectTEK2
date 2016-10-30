/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */


package dao.analysis;

import db.DBConnectionFactoryStarSchema;
import model.analysis.FactHospital;
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
public class FactHospitalDAO {

    public ArrayList<FactHospital> GetFactHospital() throws SQLException {

        DBConnectionFactoryStarSchema myFactory = DBConnectionFactoryStarSchema.getInstance();
        ArrayList<FactHospital> ArrfactHospital = new ArrayList<>();
        try (Connection conn = myFactory.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * from fact_hospital");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                FactHospital temp = new FactHospital();
                temp.setCensusYear(rs.getInt("censusYear"));
                temp.setHospitalName(rs.getString("hospitalName"));
                temp.setClassification(rs.getString("classification"));
                temp.setTotalNoOfDoctors(rs.getInt("totalNoOfDoctors"));
                temp.setTotalNoOfNurses(rs.getInt("totalNoOfNurses"));
                temp.setTotalNoOfMidwives(rs.getInt("totalNoOfMidwives"));
                temp.setTotalNoOfBeds(rs.getInt("totalNoOfBeds"));
                temp.setCategory(rs.getString("category"));
                temp.setIsAccredited(rs.getBoolean("isAccredited"));
                temp.setLatitude(rs.getDouble("latitude"));
                temp.setLongtitude(rs.getDouble("longitude"));
                ArrfactHospital.add(temp);
            }
            pstmt.close();
            rs.close();
        }
        return ArrfactHospital;
    }
    
    public ArrayList<FactHospital> retrieveHospitalsBedsDocsNurses() throws SQLException {

        DBConnectionFactoryStarSchema myFactory = DBConnectionFactoryStarSchema.getInstance();
        ArrayList<FactHospital> ArrfactHospital = new ArrayList<>();
        try (Connection conn = myFactory.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT CENSUSYEAR,\n" +
            "       CASE HOSPITALNAME\n" +
            "       WHEN 'Acebedo General Hospital' THEN 'Tanque'\n" +
            "       WHEN 'Bermudez Polmedic Hospital' THEN 'Tanque'\n" +
            "       WHEN 'Dr. Jose Rodriguez Memorial Hospital' THEN 'Tanque'\n" +
            "       WHEN 'John Paul Hospital' THEN 'Pobcaran (Poblacion-Caliparan)'\n" +
            "       WHEN 'Our Lady of Lourdes Hospital of Caybiga, Inc.' THEN 'Pobcaran (Poblacion-Caliparan)'\n" +
            "       WHEN 'Markvim Hospital' THEN 'Caloocan North III'\n" +
            "       WHEN 'Martinez Memorial Hospital' THEN 'Caloocan North II'\n" +
            "       WHEN 'MCU-FD Tanchoco Medical Foundation Hospital' THEN 'Caloocan North I'\n" +
            "       WHEN 'Nodado General Hospital' THEN 'Caloocan North IV'\n" +
            "       WHEN 'Our Lady of Grace Hospital' THEN 'Caloocan North II'\n" +
            "       WHEN 'Pres. Diosdado Macapagal Memorial Medical Center' THEN 'Caloocan North I'\n" +
            "       ELSE 'Caloocan North III'\n" +
            "       END AS 'DISTRICT',\n" +
            "	    IF (HOSPITALNAME = 'Acebedo General Hospital' \n" +
            "	    OR HOSPITALNAME = 'Bermudez Polmedic Hospital'\n" +
            "       OR HOSPITALNAME = 'Dr. Jose Rodriguez Memorial Hospital'\n" +
            "       OR HOSPITALNAME = 'John Paul Hospital'\n" +
            "       OR HOSPITALNAME = 'Our Lady of Lourdes Hospital of Caybiga, Inc.', 'SOUTH', 'NORTH') AS 'ZONE',\n" +
            "       SUM(TOTALNOOFBEDS) AS 'BEDS',\n" +
            "       SUM(TOTALNOOFDOCTORS) 'DOCTORS',\n" +
            "       SUM(TOTALNOOFNURSES) AS 'NURSES'\n" +
            "FROM FACT_HOSPITAL\n" +
            "GROUP BY CENSUSYEAR, DISTRICT");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                FactHospital temp = new FactHospital();
                temp.setCensusYear(rs.getInt("censusYear"));
                temp.setDistrict(rs.getString("DISTRICT"));
                temp.setZone(rs.getString("ZONE"));
                temp.setTotalNoOfNurses(rs.getInt("NURSES"));
                temp.setTotalNoOfBeds(rs.getInt("BEDS"));
                temp.setTotalNoOfDoctors(rs.getInt("DOCTORS"));
                ArrfactHospital.add(temp);
            }
            pstmt.close();
            rs.close();
        }
        return ArrfactHospital;
    }

}
