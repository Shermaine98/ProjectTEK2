/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */


package dao.analysis;

import dao.demo.HighestCompletedDAO;
import db.DBConnectionFactoryStarSchema;
import model.analysis.FactPeople;
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
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class FactAllDAO {
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
