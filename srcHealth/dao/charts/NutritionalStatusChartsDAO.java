/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.charts;

import db.DBConnectionFactoryStorageDB;
import model.health.NutritionalStatus;
import model.health.NutritionalStatusBMI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Gian
 */
public class NutritionalStatusChartsDAO {
    public ArrayList<NutritionalStatus> ViewNutritionalStatus(int formID) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<NutritionalStatus> ArrNutritionalStatus = new ArrayList<>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM nutritional_status WHERE formID = ?");
                pstmt.setInt(1, formID);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    NutritionalStatus temp = new NutritionalStatus();
                    ArrayList<NutritionalStatusBMI> arrenrollmentDet = new ArrayList<NutritionalStatusBMI>();
                    temp.setFormID(rs.getInt("formID"));
                    temp.setCensusYear(rs.getInt("censusYear"));
                    temp.setDistrict(rs.getString("district"));
                    temp.setGradeLevel(rs.getString("gradeLevel"));
                    temp.setTotalMale(rs.getInt("totalMale"));
                    temp.setTotalFemale(rs.getInt("totalFemale"));
                    temp.setTotalCount(rs.getInt("totalCount"));
                    temp.setPupilsWeighedMale(rs.getInt("pupilsWeighedMale"));
                    temp.setPupilsWeighedFemale(rs.getInt("pupilsWeighedFemale"));
                    temp.setPupilsWeighedTotal(rs.getInt("pupilsWeighedTotal"));
                    temp.setValidation(rs.getBoolean("validation"));

                    PreparedStatement pstmt2 = conn.prepareStatement("SELECT * FROM nutritional_status_BMI WHERE  formID = ? and gradeLevel = ? and district = ?");
                    pstmt2.setInt(1, temp.getFormID());
                    pstmt2.setString(2, temp.getGradeLevel());
                    pstmt2.setString(3, temp.getDistrict());
                    ResultSet rs2 = pstmt2.executeQuery();
                    while (rs2.next()) {
                        NutritionalStatusBMI NutritionalStatusBMITemp = new NutritionalStatusBMI();
                        NutritionalStatusBMITemp.setBMI(rs2.getString("BMI"));
                        NutritionalStatusBMITemp.setMaleCount(rs2.getInt("maleCount"));
                        NutritionalStatusBMITemp.setFemaleCount(rs2.getInt("femaleCount"));
                        NutritionalStatusBMITemp.setTotalCount(rs2.getInt("totalCount"));
                        arrenrollmentDet.add(NutritionalStatusBMITemp);
                    }
                    temp.setNutritionalStatusBMI(arrenrollmentDet);
                    ArrNutritionalStatus.add(temp);
                    pstmt2.close();
                }
                pstmt.close();
            }
            return ArrNutritionalStatus;
        } catch (SQLException ex) {
            getLogger(dao.health.NutritionalStatusDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<NutritionalStatus> retrieveGrade(int formID) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<NutritionalStatus> ArrNutritionalStatus = new ArrayList<>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * \n" +
                "FROM 	nutritional_status \n" +
                "WHERE	FORMID = ?\n" +
                "	AND DISTRICT != 'CALOOCAN CITY'\n" +
                "       AND GRADELEVEL != 'GRAND TOTAL'\n" +
                "	AND DISTRICT != 'GRAND TOTAL'\n" +
                "       AND GRADELEVEL != 'PRE ELEM - GRADE VI';");
                pstmt.setInt(1, formID);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    NutritionalStatus temp = new NutritionalStatus();
                    ArrayList<NutritionalStatusBMI> arrenrollmentDet = new ArrayList<NutritionalStatusBMI>();
                    temp.setFormID(rs.getInt("formID"));
                    temp.setCensusYear(rs.getInt("censusYear"));
                    temp.setDistrict(rs.getString("district"));
                    temp.setGradeLevel(rs.getString("gradeLevel"));
                    temp.setTotalMale(rs.getInt("totalMale"));
                    temp.setTotalFemale(rs.getInt("totalFemale"));
                    temp.setTotalCount(rs.getInt("totalCount"));
                    temp.setPupilsWeighedMale(rs.getInt("pupilsWeighedMale"));
                    temp.setPupilsWeighedFemale(rs.getInt("pupilsWeighedFemale"));
                    temp.setPupilsWeighedTotal(rs.getInt("pupilsWeighedTotal"));
                    temp.setValidation(rs.getBoolean("validation"));

                    PreparedStatement pstmt2 = conn.prepareStatement("SELECT * FROM nutritional_status_BMI WHERE  formID = ? and gradeLevel = ? and district = ?");
                    pstmt2.setInt(1, temp.getFormID());
                    pstmt2.setString(2, temp.getGradeLevel());
                    pstmt2.setString(3, temp.getDistrict());
                    ResultSet rs2 = pstmt2.executeQuery();
                    while (rs2.next()) {
                        NutritionalStatusBMI NutritionalStatusBMITemp = new NutritionalStatusBMI();
                        NutritionalStatusBMITemp.setBMI(rs2.getString("BMI"));
                        NutritionalStatusBMITemp.setMaleCount(rs2.getInt("maleCount"));
                        NutritionalStatusBMITemp.setFemaleCount(rs2.getInt("femaleCount"));
                        NutritionalStatusBMITemp.setTotalCount(rs2.getInt("totalCount"));
                        arrenrollmentDet.add(NutritionalStatusBMITemp);
                    }
                    temp.setNutritionalStatusBMI(arrenrollmentDet);
                    ArrNutritionalStatus.add(temp);
                    pstmt2.close();
                }
                pstmt.close();
            }
            return ArrNutritionalStatus;
        } catch (SQLException ex) {
            getLogger(dao.health.NutritionalStatusDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<NutritionalStatusBMI> retrieveTotal(int formID) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<NutritionalStatusBMI> ArrNutritionalStatus = new ArrayList<>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT DISTRICT, BMI, SUM(MALECOUNT) AS 'MALE', SUM(FEMALECOUNT) AS 'FEMALE'\n" +
                "FROM 	nutritional_status_bmi\n" +
                "WHERE	FORMID = ? \n" +
                "	AND DISTRICT != 'CALOOCAN CITY'\n" +
                "       AND GRADELEVEL != 'GRAND TOTAL'\n" +
                "	AND DISTRICT != 'GRAND TOTAL'\n" +
                "       AND GRADELEVEL != 'PRE ELEM - GRADE VI'\n" +
                "GROUP BY BMI;");
                pstmt.setInt(1, formID);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    NutritionalStatusBMI temp = new NutritionalStatusBMI();
                    temp.setBMI(rs.getString("BMI"));
                    temp.setMaleCount(rs.getInt("MALE"));
                    temp.setFemaleCount(rs.getInt("FEMALE"));
                    ArrNutritionalStatus.add(temp);
                }
                pstmt.close();
            }
            return ArrNutritionalStatus;
        } catch (SQLException ex) {
            getLogger(dao.health.NutritionalStatusDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<NutritionalStatus> retrieveByDistrict(int formID) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<NutritionalStatus> ArrNutritionalStatus = new ArrayList<>();
            try (Connection conn = myFactory.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT DISTRICT\n" +
                "FROM   nutritional_status\n" +
                "WHERE      FORMID=?\n" +
                "           AND DISTRICT != 'CALOOCAN CITY'\n" +
                "           AND GRADELEVEL != 'GRAND TOTAL' \n" +
                "           AND DISTRICT != 'GRAND TOTAL' \n" +
                "GROUP BY   DISTRICT;");
                pstmt.setInt(1, formID);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    NutritionalStatus temp = new NutritionalStatus();
                    temp.setDistrict(rs.getString("district"));

                    PreparedStatement pstmt2 = conn.prepareStatement("SELECT DISTRICT, BMI, SUM(MALECOUNT) AS 'MALECOUNT', SUM(FEMALECOUNT) AS 'FEMALECOUNT'\n" +
                    "FROM 	nutritional_status_bmi\n" +
                    "WHERE	FORMID = ? \n" +
                    "		AND DISTRICT = ? \n" +
                    "           AND GRADELEVEL != 'GRAND TOTAL' \n" +
                    "		AND DISTRICT != 'GRAND TOTAL' \n" +
                    "           AND GRADELEVEL != 'PRE ELEM - GRADE VI' \n" +
                    "GROUP BY	BMI, DISTRICT;");
                    pstmt2.setInt(1, formID);
                    pstmt2.setString(2, temp.getDistrict());
                    ResultSet rs2 = pstmt2.executeQuery();
                    
                    ArrayList<NutritionalStatusBMI> tempDet = new ArrayList<NutritionalStatusBMI>();
                    while (rs2.next()) {
                        NutritionalStatusBMI NutritionalStatusBMITemp = new NutritionalStatusBMI();
                        NutritionalStatusBMITemp.setBMI(rs2.getString("BMI"));
                        NutritionalStatusBMITemp.setMaleCount(rs2.getInt("maleCount"));
                        NutritionalStatusBMITemp.setFemaleCount(rs2.getInt("femaleCount"));
                        tempDet.add(NutritionalStatusBMITemp);
                    }
                    temp.setNutritionalStatusBMI(tempDet);
                    ArrNutritionalStatus.add(temp);
                    pstmt2.close();
                }
                pstmt.close();
            }
            return ArrNutritionalStatus;
        } catch (SQLException ex) {
            getLogger(dao.health.NutritionalStatusDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
}
