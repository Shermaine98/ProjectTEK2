/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.analysis;

import dao.demo.HighestCompletedDAO;
import db.DBConnectionFactoryStarSchema;
import model.analysis.FactPeople;
import model.analysis.FactStudentNutrition;
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
 * @author Gian
 */
public class FactStudentNutritionDAO {
    public ArrayList<FactStudentNutrition> retrieveNutritionalStatus() throws ParseException {
        try {
            DBConnectionFactoryStarSchema myFactory = DBConnectionFactoryStarSchema.getInstance();
            ArrayList<FactStudentNutrition> factNutrition = new ArrayList<FactStudentNutrition>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT CENSUSYEAR, \n" +
                "	DISTRICT,\n" +
                "       SUM(PUPILSWEIGHED) AS 'PUPILSWEIGHED', \n" +
                "       SUM(totalNoOfSeverelyWasted) AS 'totalNoOfSeverelyWasted', \n" +
                "       SUM(totalNoOfWasted) AS 'totalNoOfWasted', \n" +
                "       SUM(totalNoOfNormal) AS 'totalNoOfNormal', \n" +
                "       SUM(totalNoOfOverweight) AS 'totalNoOfOverweight', \n" +
                "       SUM(totalNoOfObese) AS 'totalNoOfObese', \n" +
                "       IF (SUBSTRING(DISTRICT, 10, 5) = 'NORTH', 'NORTH', 'SOUTH') AS 'ZONE'\n" +
                "FROM FACT_STUDENTNUTRITION\n" +
                "WHERE DISTRICT != 'CALOOCAN CITY'\n" +
                "GROUP BY CENSUSYEAR, DISTRICT");
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    FactStudentNutrition temp = new FactStudentNutrition();
                    temp.setCensusYear(rs.getInt("CENSUSYEAR"));
                    temp.setDistrict(rs.getString("DISTRICT"));
                    temp.setPupilsWeighed(rs.getInt("PUPILSWEIGHED"));
                    temp.setTotalNoOfSeverelyWasted(rs.getInt("totalNoOfSeverelyWasted"));
                    temp.setTotalNoOfWasted(rs.getInt("totalNoOfWasted"));
                    temp.setTotalNoOfNormal(rs.getInt("totalNoOfNormal"));
                    temp.setTotalNoOfOverweight(rs.getInt("totalNoOfOverweight"));
                    temp.setTotalNoOfObese(rs.getInt("totalNoOfObese"));
                    temp.setZone(rs.getString("ZONE"));
                    factNutrition.add(temp);
                }
                pstmt.close();
            }
            return factNutrition;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<FactStudentNutrition> retrieveNutritionalStatusCommonChart() throws ParseException {
        try {
            DBConnectionFactoryStarSchema myFactory = DBConnectionFactoryStarSchema.getInstance();
            ArrayList<FactStudentNutrition> factNutrition = new ArrayList<FactStudentNutrition>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement(
                "SELECT 	GENDER, GRADELEVEL, CENSUSYEAR,\n" +
"                               DISTRICT, IF (SUBSTRING(DISTRICT, 10, 5) = 'NORTH', 'NORTH', 'SOUTH') AS 'ZONE',\n" +
"                               totalNoOfSeverelyWasted, totalNoOfWasted, totalNoOfNormal, totalNoOfOverweight, totalNoOfObese\n" +
                "FROM FACT_STUDENTNUTRITION\n" +
                "GROUP BY  CENSUSYEAR, DISTRICT, GRADELEVEL, GENDER\n" +
                "ORDER BY CENSUSYEAR, DISTRICT, GRADELEVEL, GENDER");
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    FactStudentNutrition temp = new FactStudentNutrition();
                    temp.setGender(rs.getString("GENDER"));
                    temp.setGradeLevel(rs.getString("GRADELEVEL"));
                    temp.setCensusYear(rs.getInt("CENSUSYEAR"));
                    temp.setDistrict(rs.getString("DISTRICT"));
                    temp.setZone(rs.getString("ZONE"));
                    temp.setTotalNoOfSeverelyWasted(rs.getInt("totalNoOfSeverelyWasted"));
                    temp.setTotalNoOfWasted(rs.getInt("totalNoOfWasted"));
                    temp.setTotalNoOfNormal(rs.getInt("totalNoOfNormal"));
                    temp.setTotalNoOfOverweight(rs.getInt("totalNoOfOverweight"));
                    temp.setTotalNoOfObese(rs.getInt("totalNoOfObese"));
                    factNutrition.add(temp);
                }
                pstmt.close();
            }
            return factNutrition;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    
    public ArrayList<String> retrieveGradeLevels() throws ParseException {
        try {
            DBConnectionFactoryStarSchema myFactory = DBConnectionFactoryStarSchema.getInstance();
            ArrayList<String> grades = new ArrayList<String>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT IF(GRADELEVEL = 'KINDER', 'Pre Elementary', GRADELEVEL) as 'gradeLevel'\n" +
                "FROM FACT_STUDENTNUTRITION\n" +
                "GROUP BY GRADELEVEL");
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    grades.add(rs.getString("gradeLevel"));
                }
                pstmt.close();
            }
            return grades;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
}
