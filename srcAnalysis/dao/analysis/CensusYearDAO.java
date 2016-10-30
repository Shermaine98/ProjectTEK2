/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */


package dao.analysis;

import dao.demo.HighestCompletedDAO;
import db.DBConnectionFactoryStarSchema;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class CensusYearDAO extends HttpServlet {

    public ArrayList<Integer> retrieveYears () throws ParseException {
        try {
            DBConnectionFactoryStarSchema myFactory = DBConnectionFactoryStarSchema.getInstance();
            ArrayList<Integer> years = new ArrayList<Integer>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM DIM_CENCUSYEAR");
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    years.add(rs.getInt("CENCUSYEAR"));
                }
                pstmt.close();
            }
            return years;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    public int getLatestYear () throws ParseException {
        try {
            DBConnectionFactoryStarSchema myFactory = DBConnectionFactoryStarSchema.getInstance();
            int year = 0;
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT cencusyear \n" +
                    "FROM starschema.dim_cencusyear\n" +
                    "order by cencusYear desc\n" +
                    "limit 1;");
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    year = rs.getInt("CENCUSYEAR");
                }
                pstmt.close();
            }
            return year;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return 0;
    }

}
