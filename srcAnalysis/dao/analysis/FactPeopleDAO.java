/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.analysis;

import DAODemo.HighestCompletedDAO;
import DB.DBConnectionFactoryStarSchema;
import DB.DBConnectionFactoryStorageDB;
import static DB.DBConnectionFactoryStorageDB.getInstance;
import ModelAnalysis.FactPeople;
import ModelDemo.ByAgeGroupSex;
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
}
