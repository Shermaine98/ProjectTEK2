/*
 *  Copyright (C) ProjectTEK - DLSU CCS 2016
 *  All right Reserved   * 
 */

package dao.charts;

import dao.demo.HighestCompletedDAO;
import db.DBConnectionFactoryStorageDB;
import static db.DBConnectionFactoryStorageDB.getInstance;
import model.education.DirectorySchool;
import model.education.ElemClassrooms;
import model.education.ElemTeachers;
import model.education.Seats;
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
public class SchoolDirectory {
    public ArrayList<DirectorySchool> retrieveDirectorySchool(int censusyear, String classification) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            ArrayList<DirectorySchool> directorySchool = new ArrayList<DirectorySchool>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT CENSUSYEAR, SCHOOLID, CLASSIFICATION, SCHOOLNAME" +
                " FROM DIRECTORY_SCHOOL WHERE CENSUSYEAR = ? AND CLASSIFICATION = ?");
                pstmt.setInt(1, censusyear);
                pstmt.setString(2, classification);
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    DirectorySchool temp = new DirectorySchool();
                    temp.setCensusYear(rs.getInt("CENSUSYEAR"));
                    temp.setSchoolID(rs.getInt("SCHOOLID"));
                    temp.setClassification(rs.getString("CLASSIFICATION"));
                    temp.setSchoolName(rs.getString("SCHOOLNAME"));
                    
                    ArrayList<ElemClassrooms> cr = new ArrayList<ElemClassrooms>();
                    PreparedStatement pstmt1 = conn.prepareStatement("SELECT * FROM ELEM_CLASSROOMS WHERE"
                            + " CENSUSYEAR = ? AND SCHOOLID  = ? AND CLASSIFICATION = ? AND SCHOOLNAME = ?");
                    pstmt1.setInt(1, censusyear);
                    pstmt1.setInt(2, temp.getSchoolID());
                    pstmt1.setString(3, temp.getClassification());
                    pstmt1.setString(4, temp.getSchoolName());
                    ResultSet rs1 = pstmt1.executeQuery();
                    
                    while(rs1.next()){
                        ElemClassrooms tempCR = new ElemClassrooms();
                        tempCR.setGradeLevel(rs1.getString("gradeLevel"));
                        tempCR.setClassroomCount(rs1.getInt("classroomCount"));
                        cr.add(tempCR);
                    }
                    temp.setElemClassrooms(cr);
                    
                    ArrayList<ElemTeachers> teacher = new ArrayList<ElemTeachers>();
                    PreparedStatement pstmt2 = conn.prepareStatement("SELECT * FROM ELEM_TEACHERS WHERE"
                            + " CENSUSYEAR = ? AND SCHOOLID  =? AND CLASSIFICATION = ? AND SCHOOLNAME = ?");
                    pstmt2.setInt(1, censusyear);
                    pstmt2.setInt(2, temp.getSchoolID());
                    pstmt2.setString(3, temp.getClassification());
                    pstmt2.setString(4, temp.getSchoolName());
                    ResultSet rs2 = pstmt2.executeQuery();
                    while(rs2.next()){
                        ElemTeachers tempTeach = new ElemTeachers();
                        tempTeach.setGradeLevel(rs2.getString("gradeLevel"));
                        tempTeach.setFemaleCount(rs2.getInt("femaleCount"));
                        tempTeach.setMaleCount(rs2.getInt("maleCount"));
                        teacher.add(tempTeach);
                    }
                    temp.setTeacher(teacher);
                    
                    ArrayList<Seats> arrSeats = new ArrayList<Seats>();
                    PreparedStatement pstmt3 = conn.prepareStatement("SELECT * FROM SEATS WHERE"
                            + " CENSUSYEAR = ? AND SCHOOLID  = ? AND CLASSIFICATION = ? AND SCHOOLNAME = ?");
                    pstmt3.setInt(1, censusyear);
                    pstmt3.setInt(2, temp.getSchoolID());
                    pstmt3.setString(3, temp.getClassification());
                    pstmt3.setString(4, temp.getSchoolName());
                    ResultSet rs3 = pstmt3.executeQuery();
                    while(rs3.next()){
                        Seats tempSeat = new Seats();
                        tempSeat.setGradeLevel(rs3.getString("gradeLevel"));
                        tempSeat.setSeatCount(rs3.getInt("seatsCount"));
                        arrSeats.add(tempSeat);
                    }
                    temp.setSeats(arrSeats);
                    
                    directorySchool.add(temp);
                }
                pstmt.close();
            }
            return directorySchool;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    
    public int retrieveTotalSeats(int censusyear, String classification) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            int seats = 0;
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT CENSUSYEAR, SCHOOLID, CLASSIFICATION, SCHOOLNAME, SUM(seatsCount) AS SEATSCOUNT\n" +
                    "FROM 	SEATS \n" +
                    "WHERE 	CENSUSYEAR = ? AND CLASSIFICATION = ?\n" +
                    "GROUP BY 	FORMID AND CLASSIFICATION");
                pstmt.setInt(1, censusyear);
                pstmt.setString(2, classification);
                ResultSet rs = pstmt.executeQuery();
                
                if (rs.next()) {
                    seats = rs.getInt("SEATSCOUNT");
                }
                pstmt.close();
            }
            return seats;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return 0;
    }
    
    public int retrieveTotalClassrooms(int censusyear, String classification) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            int classrooms = 0;
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT CENSUSYEAR, SCHOOLID, CLASSIFICATION, SCHOOLNAME, SUM(classroomCount) AS classroomCount\n" +
                    "FROM 	ELEM_CLASSROOMS  \n" +
                    "WHERE 	CENSUSYEAR = ? AND CLASSIFICATION = ? \n" +
                    "GROUP BY 	FORMID AND CLASSIFICATION");
                pstmt.setInt(1, censusyear);
                pstmt.setString(2, classification);
                ResultSet rs = pstmt.executeQuery();
                
                if (rs.next()) {
                    classrooms = rs.getInt("classroomCount");
                }
                pstmt.close();
            }
            return classrooms;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return 0;
    }
    
    public int retrieveTotalTeachers(int censusyear, String classification) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = getInstance();
            int teachers = 0;
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT CENSUSYEAR, SCHOOLID, CLASSIFICATION, SCHOOLNAME, SUM(femaleCount + maleCount) AS teachersCount\n" +
                    "FROM 	ELEM_TEACHERS  \n" +
                    "WHERE 	CENSUSYEAR = ? AND CLASSIFICATION = ? \n" +
                    "GROUP BY 	FORMID AND CLASSIFICATION");
                pstmt.setInt(1, censusyear);
                pstmt.setString(2, classification);
                ResultSet rs = pstmt.executeQuery();
                
                if (rs.next()) {
                    teachers = rs.getInt("teachersCount");
                }
                pstmt.close();
            }
            return teachers;
        } catch (SQLException ex) {
            getLogger(HighestCompletedDAO.class.getName()).log(SEVERE, null, ex);
        }
        return 0;
    }
    
    
}
