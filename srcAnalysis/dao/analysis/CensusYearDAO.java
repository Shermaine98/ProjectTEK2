/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.analysis;

import dao.analysis.FactPeopleDAO;
import dao.demo.HighestCompletedDAO;
import db.DBConnectionFactoryStarSchema;
import model.analysis.FactPeople;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Gian
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
