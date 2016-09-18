/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.charts;

import dao.demo.HighestCompletedDAO;
import db.DBConnectionFactoryStorageDB;
import static db.DBConnectionFactoryStorageDB.getInstance;
import model.demo.MaritalStatus;
import model.health.DirectoryHealth;
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
public class ListOfHospitalDAO {
    public ArrayList<DirectoryHealth> retrieveAllHospitals(int formID, String classification) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            ArrayList<DirectoryHealth> hospitals = new ArrayList<DirectoryHealth>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM storagedb.directory_health \n" +
                "WHERE FORMID = ? AND CLASSIFICATION = ?;");
                pstmt.setInt(1, formID);
                pstmt.setString(2, classification);
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    DirectoryHealth temp = new DirectoryHealth();
                    temp.setYear(rs.getInt("CENSUSYEAR"));
                    temp.setHospitalName(rs.getString("HOSPITALNAME"));
                    temp.setAddresss(rs.getString("ADDRESS"));
                    temp.setTelephone(rs.getString("TELEPHONE"));
                    temp.setNumberOfDoctor(rs.getInt("TOTALDOCTORS"));
                    temp.setNumberOfNurses(rs.getInt("totalNurses"));
                    temp.setNumberOfMidwives(rs.getInt("totalMidwives"));
                    temp.setNumberOfBeds(rs.getInt("numberofBeds"));
                    temp.setAccreditation(rs.getBoolean("isAccredited"));
                    hospitals.add(temp);
                }
                pstmt.close();
            }
            return hospitals;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    public DirectoryHealth retrieveOverAllHospitals(int formID, String classification) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            DirectoryHealth hospital = new DirectoryHealth();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT hospitalName, \n" +
                "	 classification, \n" +
                "	 sum(totalDoctors) as 'totalDoctors', \n" +
                "        sum(totalNurses) as 'totalNurses', \n" +
                "        sum(totalMidwives) as 'totalMidwives', \n" +
                "        sum(numberofBeds) as 'numberofBeds' \n" +
                "FROM storagedb.directory_health \n" +
                "WHERE FORMID = ? AND CLASSIFICATION = ? \n" +
                "GROUP BY CLASSIFICATION");
                pstmt.setInt(1, formID);
                pstmt.setString(2, classification);
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    DirectoryHealth temp = new DirectoryHealth();
                    temp.setClassification(rs.getString("CLASSIFICATION"));
                    temp.setNumberOfDoctor(rs.getInt("TOTALDOCTORS"));
                    temp.setNumberOfNurses(rs.getInt("totalNurses"));
                    temp.setNumberOfMidwives(rs.getInt("totalMidwives"));
                    temp.setNumberOfBeds(rs.getInt("numberofBeds"));
                    hospital=temp;
                }
                pstmt.close();
            }
            return hospital;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
}
