/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.reports;

import dao.RecordDAO;
import db.DBConnectionFactory;
import static db.DBConnectionFactory.getInstance;
import model.reports.Integrated;
import model.reports.Matrix;
import model.reports.ReportAnalysis;
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
 * @author Shermaine
 */
public class ReportDAO {

    public boolean newReportAnalysis(ArrayList<ReportAnalysis> reportChart) {
        try {
            DBConnectionFactory myFactory = getInstance();
            int rows;
            try (Connection conn = myFactory.getConnection()) {

                String query2 = "INSERT INTO analysis_report "
                        + "(year,sector ,ChartName,text, isDraft, createdBy)"
                        + " values (?,?,?,?,?,?);";
                PreparedStatement pstmt2 = conn.prepareStatement(query2);

                for (int i = 0; i < reportChart.size(); i++) {
                    pstmt2.setInt(1, reportChart.get(i).getYear());
                    pstmt2.setString(2, reportChart.get(i).getSector());
                    pstmt2.setString(3, reportChart.get(i).getChartName());
                    pstmt2.setString(4, reportChart.get(i).getText());
                    pstmt2.setBoolean(5, reportChart.get(i).isIsDraft());
                    System.out.println(reportChart.get(i).isIsDraft());
                    pstmt2.setInt(6, reportChart.get(i).getCreatedBy());
                    rows = pstmt2.executeUpdate();
                }
                pstmt2.close();
                conn.close();
                return true;
            }

        } catch (SQLException ex) {
            getLogger(ReportDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean newIntegratedAnalysis(int year, Integrated Integrated) {
        try {
            DBConnectionFactory myFactory = getInstance();
            int rows;
            try (Connection conn = myFactory.getConnection()) {

                String query2 = "INSERT INTO integrated_report "
                        + "(year,sector, text, isDraft, createdBy)"
                        + " values (?,?,?,?,?);";
                PreparedStatement pstmt2 = conn.prepareStatement(query2);

               
                    pstmt2.setInt(1, year);
                    pstmt2.setString(2, Integrated.getSector());
                    pstmt2.setString(3, Integrated.getText());
                    pstmt2.setBoolean(4, Integrated.isIsDraft());
                    pstmt2.setInt(5, Integrated.getCreatedBy());
                    rows = pstmt2.executeUpdate();
                
                pstmt2.close();

                conn.close();
                return true;
            }

        } catch (SQLException ex) {
            getLogger(ReportDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean newReportMatrix(ArrayList<Matrix> Matrix) {
        try {
            DBConnectionFactory myFactory = getInstance();
            int rows;
            try (Connection conn = myFactory.getConnection()) {

                String query2 = " INSERT INTO matrix_report "
                        + " (year, sector, ChartName,"
                        + "Implications,observations,explanations, interventions, isDraft, createdBy) "
                        + "values (?,?,?,?,?,?,?,?,?);";
                PreparedStatement pstmt2 = conn.prepareStatement(query2);

                for (int i = 0; i < Matrix.size(); i++) {
                    pstmt2.setInt(1, Matrix.get(i).getYear());
                    pstmt2.setString(2, Matrix.get(i).getSector());
                    pstmt2.setString(3, Matrix.get(i).getChartName());
                    pstmt2.setString(4, Matrix.get(i).getImplications());
                    pstmt2.setString(5, Matrix.get(i).getObservations());
                    pstmt2.setString(6, Matrix.get(i).getExplanations());
                    pstmt2.setString(7, Matrix.get(i).getInternventions());
                    pstmt2.setBoolean(8, Matrix.get(i).isIsDraft());
                    pstmt2.setInt(9, Matrix.get(i).getCreatedBy());
                    rows = pstmt2.executeUpdate();
                }
                 pstmt2.close();
                    conn.close();
                return true;
            }
        } catch (SQLException ex) {
            getLogger(ReportDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public Integer getChartMatrixName() throws SQLException {
        DBConnectionFactory myFactory = getInstance();
        Integer i;
        try (Connection conn = myFactory.getConnection()) {
            i = 0;
            String query = "SELECT MAX(counter) as `counter` from matrix_report;";
            ResultSet rs;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    i = rs.getInt("counter") + 1;
                    i += 300000;
                }
                conn.close();
            }
            rs.close();
        }
        return i;
    }

    public Integer getChartReportAnalysisName() throws SQLException {
        DBConnectionFactory myFactory = getInstance();
        Integer i;
        try (Connection conn = myFactory.getConnection()) {
            i = 0;
            String query = "SELECT MAX(counter) as `counter` from analysis_report;";
            ResultSet rs;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    i = rs.getInt("counter") + 1;
                    i += 200000;
                }
                conn.close();
            }
            rs.close();
        }
        return i;
    }

    public boolean checkIfExistReportAnalysis(String sector, int year) throws SQLException {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            int i;
            try (Connection conn = myFactory.getConnection()) {
                String checkExist = "SELECT EXISTS(SELECT * FROM analysis_report WHERE sector = ? and `year` = ?) AS `EXISTS`;";
                PreparedStatement pstmt1 = conn.prepareStatement(checkExist);
                pstmt1.setString(1, sector);
                pstmt1.setInt(2, year);
                ResultSet rs = pstmt1.executeQuery();
                i = 0;
                if (rs.next()) {
                    i = rs.getInt("EXISTS");
                }
                rs.close();
                pstmt1.close();
                conn.close();
            }
            if (i <= 0) {
                return false;
            } else if (i > 0) {
                return true;
            }
        } catch (SQLException ex) {
            getLogger(ReportDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

      public boolean checkIfExistIntegrated(String sector, int year) throws SQLException {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            int i;
            try (Connection conn = myFactory.getConnection()) {
                String checkExist = "SELECT EXISTS(SELECT * FROM integrated_report WHERE sector = ? and `year` = ?) AS `EXISTS`;";
                PreparedStatement pstmt1 = conn.prepareStatement(checkExist);
                pstmt1.setString(1, sector);
                pstmt1.setInt(2, year);
                ResultSet rs = pstmt1.executeQuery();
                i = 0;
                if (rs.next()) {
                    i = rs.getInt("EXISTS");
                }
                rs.close();
                pstmt1.close();
                conn.close();
            }
            if (i <= 0) {
                return false;
            } else if (i > 0) {
                return true;
            }
        } catch (SQLException ex) {
            getLogger(ReportDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }
    public boolean checkIfExistIntegratedSaved(String sector, int year) throws SQLException {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            int i;
            try (Connection conn = myFactory.getConnection()) {
                String checkExist = "SELECT EXISTS(SELECT * FROM integrated_report WHERE sector = ? and `year` = ? and `isDraft` = 1) AS `EXISTS`;";
                PreparedStatement pstmt1 = conn.prepareStatement(checkExist);
                pstmt1.setString(1, sector);
                pstmt1.setInt(2, year);
                ResultSet rs = pstmt1.executeQuery();
                i = 0;
                if (rs.next()) {
                    i = rs.getInt("EXISTS");
                }
                rs.close();
                pstmt1.close();
                conn.close();
            }
            if (i <= 0) {
                return false;
            } else if (i > 0) {
                return true;
            }
        } catch (SQLException ex) {
            getLogger(ReportDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }
    
     public boolean checkIfExistIntegratedSubmit(String sector, int year) throws SQLException {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            int i;
            try (Connection conn = myFactory.getConnection()) {
                String checkExist = "SELECT EXISTS(SELECT * FROM integrated_report WHERE sector = ? and `year` = ? and `isDraft` = 0) AS `EXISTS`;";
                PreparedStatement pstmt1 = conn.prepareStatement(checkExist);
                pstmt1.setString(1, sector);
                pstmt1.setInt(2, year);
                ResultSet rs = pstmt1.executeQuery();
                i = 0;
                if (rs.next()) {
                    i = rs.getInt("EXISTS");
                }
                rs.close();
                pstmt1.close();
                conn.close();
            }
            if (i <= 0) {
                return false;
            } else if (i > 0) {
                return true;
            }
        } catch (SQLException ex) {
            getLogger(ReportDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }
      
    public boolean checkIfExistReportAnalysisSaved(String sector, int year) throws SQLException {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            int i;
            try (Connection conn = myFactory.getConnection()) {
                String checkExist = "SELECT EXISTS(SELECT * FROM analysis_report WHERE sector = ? and `year` = ? and `isDraft` = 1) AS `EXISTS`;";
                PreparedStatement pstmt1 = conn.prepareStatement(checkExist);
                pstmt1.setString(1, sector);
                pstmt1.setInt(2, year);
                ResultSet rs = pstmt1.executeQuery();
                i = 0;
                if (rs.next()) {
                    i = rs.getInt("EXISTS");
                }
                rs.close();
                pstmt1.close();
                conn.close();
            }
            if (i <= 0) {
                return false;
            } else if (i > 0) {
                return true;
            }
        } catch (SQLException ex) {
            getLogger(ReportDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkIfExistMatrix(String sector, int year) throws SQLException {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            int i;
            try (Connection conn = myFactory.getConnection()) {
                String checkExist = "SELECT EXISTS(SELECT * FROM matrix_report WHERE sector = ? and `year` = ? ) AS `EXISTS`;";
                PreparedStatement pstmt1 = conn.prepareStatement(checkExist);
                pstmt1.setString(1, sector);
                pstmt1.setInt(2, year);
                ResultSet rs = pstmt1.executeQuery();
                i = 0;
                if (rs.next()) {
                    i = rs.getInt("EXISTS");
                }
                rs.close();
                pstmt1.close();
                conn.close();
            }
            if (i <= 0) {

                return false;
            } else if (i > 0) {

                return true;
            }
        } catch (SQLException ex) {
            getLogger(ReportDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkIfExistMatrixSubmit(String sector, int year) throws SQLException {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            int i;
            try (Connection conn = myFactory.getConnection()) {
                String checkExist = "SELECT EXISTS(SELECT * FROM matrix_report WHERE sector = ? and `year` = ?  and `isDraft` = 0) AS `EXISTS`;";
                PreparedStatement pstmt1 = conn.prepareStatement(checkExist);
                pstmt1.setString(1, sector);
                pstmt1.setInt(2, year);
                ResultSet rs = pstmt1.executeQuery();
                i = 0;
                if (rs.next()) {
                    i = rs.getInt("EXISTS");
                }
                rs.close();
                pstmt1.close();
                conn.close();
            }
            if (i <= 0) {
                return false;
            } else if (i > 0) {
                return true;
            }
        } catch (SQLException ex) {
            getLogger(ReportDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkIfExistReportAnalysisSubmit(String sector, int year) throws SQLException {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            int i;
            try (Connection conn = myFactory.getConnection()) {
                String checkExist = "SELECT EXISTS(SELECT * FROM analysis_report WHERE sector = ? and `year` = ?  and `isDraft` = 0) AS `EXISTS`;";
                PreparedStatement pstmt1 = conn.prepareStatement(checkExist);
                pstmt1.setString(1, sector);
                pstmt1.setInt(2, year);
                ResultSet rs = pstmt1.executeQuery();
                i = 0;
                if (rs.next()) {
                    i = rs.getInt("EXISTS");
                }
                rs.close();
                pstmt1.close();
                conn.close();
            }
            if (i <= 0) {
                return false;
            } else if (i > 0) {
                return true;
            }
        } catch (SQLException ex) {
            getLogger(ReportDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkIfExistMatrixSaved(String sector, int year) throws SQLException {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            int i;
            try (Connection conn = myFactory.getConnection()) {
                String checkExist = "SELECT EXISTS(SELECT * FROM matrix_report WHERE sector = ? and `year` = ?  and `isDraft` = 1) AS `EXISTS`;";
                PreparedStatement pstmt1 = conn.prepareStatement(checkExist);
                pstmt1.setString(1, sector);
                pstmt1.setInt(2, year);
                ResultSet rs = pstmt1.executeQuery();
                i = 0;
                if (rs.next()) {
                    i = rs.getInt("EXISTS");
                }
                rs.close();
                pstmt1.close();
                conn.close();
            }
            if (i <= 0) {
                return false;
            } else if (i > 0) {
                return true;
            }
        } catch (SQLException ex) {
            getLogger(ReportDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

//    public boolean saveReportDraft(int formID, int user) throws SQLException {
//        try {
//            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
//            PreparedStatement pstmt;
//            int rows;
//            try (Connection conn = myFactory.getConnection()) {
//                String updateValidation = "UPDATE report  SET `isDraft`= ?, WHERE `reportID` = ?;";
//                pstmt = conn.prepareStatement(updateValidation);
//                pstmt.setBoolean(1, true);
//                pstmt.setInt(2, user);
//                pstmt.setInt(3, formID);
//                rows = pstmt.executeUpdate();
//
//                pstmt.close();
//                conn.close();
//            }
//            return rows == 1;
//        } catch (SQLException ex) {
//            getLogger(ReportDAO.class.getName()).log(SEVERE, null, ex);
//        }
//        return false;
//    }

    public ArrayList<Integer> SearchYearAnylsis(String year, String sector) throws ParseException {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            ArrayList<Integer> records;
            try (Connection conn = myFactory.getConnection()) {
                records = new ArrayList<>();
                String search = year + "%";
                PreparedStatement pstmt = conn.prepareStatement("SELECT `year` FROM analysis_report where `year` LIKE ? AND `isDraft` = 0 AND sector = ?;");
                pstmt.setString(1, search);
                pstmt.setString(2, sector);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    int number;
                    number = rs.getInt("year");
                    records.add(number);
                }
                pstmt.close();
                rs.close();
                conn.close();
            }
            return records;
        } catch (SQLException ex) {
            getLogger(ReportDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Integer> SearchYearIntegrated(String year, String sector) throws ParseException {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            ArrayList<Integer> records;
            try (Connection conn = myFactory.getConnection()) {
                records = new ArrayList<>();
                String search = year + "%";
                PreparedStatement pstmt = conn.prepareStatement("SELECT `year` FROM integrated_report where `year` LIKE ? AND `isDraft` = 0 AND sector = ?;");
                pstmt.setString(1, search);
                pstmt.setString(2, sector);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    int number;
                    number = rs.getInt("year");
                    records.add(number);
                }
                pstmt.close();
                rs.close();
                conn.close();
            }
            return records;
        } catch (SQLException ex) {
            getLogger(ReportDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Integer> SearchYearMatrix(String year, String sector) throws ParseException {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            ArrayList<Integer> records;
            try (Connection conn = myFactory.getConnection()) {
                records = new ArrayList<>();
                String search = year + "%";
                PreparedStatement pstmt = conn.prepareStatement("SELECT `year` FROM matrix_report where `year` LIKE ? AND `isDraft` = 0 AND sector = ?;");
                pstmt.setString(1, search);
                pstmt.setString(2, sector);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    int number;
                    number = rs.getInt("year");
                    records.add(number);
                }
                pstmt.close();
                rs.close();
                conn.close();
            }
            return records;
        } catch (SQLException ex) {
            getLogger(ReportDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }



    public ArrayList<ReportAnalysis> GetReportAnalysisbySectorSaved(String sector) throws ParseException {
        RecordDAO recordDAO = new RecordDAO();
        DBConnectionFactory myFactory = getInstance();
        int rows;
        try (Connection conn = myFactory.getConnection()) {
            ArrayList<ReportAnalysis> arrReportAnalysis = new ArrayList<ReportAnalysis>();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM accounts.analysis_report WHERE `isDraft` = 1 AND `sector` = ?;");
            pstmt.setString(1, sector);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ReportAnalysis reportAnalysis = new ReportAnalysis();
                reportAnalysis.setYear(rs.getInt("year"));
                reportAnalysis.setSector(rs.getString("sector"));
                reportAnalysis.setChartName(rs.getString("chartName"));
                reportAnalysis.setText(rs.getString("text"));
                reportAnalysis.setIsDraft(rs.getBoolean("isDraft"));
                reportAnalysis.setCreatedByName(recordDAO.GetUserName(rs.getInt("createdBy")));
                arrReportAnalysis.add(reportAnalysis);
            }
            pstmt.close();
            rs.close();
            conn.close();
            return arrReportAnalysis;
        } catch (SQLException ex) {
            getLogger(ReportDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;

    }
    
    
     public Integrated GetReportIntegratedSaved(String sector) throws ParseException {
        RecordDAO recordDAO = new RecordDAO();
        DBConnectionFactory myFactory = getInstance();
        int rows;
        try (Connection conn = myFactory.getConnection()) {
            Integrated Integrated = new Integrated();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM accounts.integrated_report WHERE `isDraft` = 1 AND `sector` = ?;");
            pstmt.setString(1, sector);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Integrated.setYear(rs.getInt("year"));
                Integrated.setSector(rs.getString("sector"));
                Integrated.setText(rs.getString("text"));
                Integrated.setIsDraft(rs.getBoolean("isDraft"));
                Integrated.setCreatedByName(recordDAO.GetUserName(rs.getInt("createdBy")));
            }
            pstmt.close();
            rs.close();
            conn.close();
            return Integrated;
        } catch (SQLException ex) {
            getLogger(ReportDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;

    }

    public ArrayList<Matrix> GetReportMatrixSectorSaved(String sector) throws ParseException {
        RecordDAO recordDAO = new RecordDAO();
        DBConnectionFactory myFactory = getInstance();
        int rows;
        try (Connection conn = myFactory.getConnection()) {
            ArrayList<Matrix> arrMatrix = new ArrayList<Matrix>();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM accounts.matrix_report WHERE `isDraft` = 1 AND `sector` = ?;");
            pstmt.setString(1, sector);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Matrix Matrix = new Matrix();
                Matrix.setYear(rs.getInt("year"));
                Matrix.setSector(rs.getString("sector"));
                Matrix.setChartName(rs.getString("chartName"));
                Matrix.setObservations(rs.getString("observations"));
                Matrix.setInternventions(rs.getString("Interventions"));
                Matrix.setImplications(rs.getString("Implications"));
                Matrix.setExplanations(rs.getString("Explanations"));
                Matrix.setIsDraft(rs.getBoolean("isDraft"));
                Matrix.setCreatedByName(recordDAO.GetUserName(rs.getInt("createdBy")));
                arrMatrix.add(Matrix);
            }
            pstmt.close();
            rs.close();
            conn.close();
            return arrMatrix;
        } catch (SQLException ex) {
            getLogger(ReportDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;

    }

    public ArrayList<ReportAnalysis> GetReportAnalysisbySectorSubmitted(String sector, String year) throws ParseException {
        RecordDAO recordDAO = new RecordDAO();
        DBConnectionFactory myFactory = getInstance();
        int rows;
        try (Connection conn = myFactory.getConnection()) {
            ArrayList<ReportAnalysis> arrReportAnalysis = new ArrayList<ReportAnalysis>();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM accounts.analysis_report WHERE `isDraft` = 0 AND `sector` = ? AND `year` = ?;");
            pstmt.setString(1, sector);
            pstmt.setString(2, year);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ReportAnalysis reportAnalysis = new ReportAnalysis();
                reportAnalysis.setYear(rs.getInt("year"));
                reportAnalysis.setSector(rs.getString("sector"));
                reportAnalysis.setChartName(rs.getString("chartName"));
                reportAnalysis.setText(rs.getString("text"));
                reportAnalysis.setIsDraft(rs.getBoolean("isDraft"));
                reportAnalysis.setCreatedByName(recordDAO.GetUserName(rs.getInt("createdBy")));
                arrReportAnalysis.add(reportAnalysis);
            }
            pstmt.close();
            rs.close();
            conn.close();
            return arrReportAnalysis;
        } catch (SQLException ex) {
            getLogger(ReportDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;

    }

    public ArrayList<Matrix> GetReportMatrixSectorSubmitted(String sector, String year) throws ParseException {
        RecordDAO recordDAO = new RecordDAO();
        DBConnectionFactory myFactory = getInstance();
        int rows;
        try (Connection conn = myFactory.getConnection()) {
            ArrayList<Matrix> arrMatrix = new ArrayList<Matrix>();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM accounts.matrix_report WHERE `isDraft` = 0 AND `sector` = ? AND `year` = ?;");
            pstmt.setString(1, sector);
            pstmt.setString(2, year);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Matrix Matrix = new Matrix();
                Matrix.setYear(rs.getInt("year"));
                Matrix.setSector(rs.getString("sector"));
                Matrix.setChartName(rs.getString("chartName"));
                Matrix.setObservations(rs.getString("observations"));
                Matrix.setInternventions(rs.getString("Interventions"));
                Matrix.setImplications(rs.getString("Implications"));
                Matrix.setExplanations(rs.getString("Explanations"));
                Matrix.setIsDraft(rs.getBoolean("isDraft"));
                Matrix.setCreatedByName(recordDAO.GetUserName(rs.getInt("createdBy")));
                arrMatrix.add(Matrix);
            }
            pstmt.close();
            rs.close();
            conn.close();
            return arrMatrix;
        } catch (SQLException ex) {
            getLogger(ReportDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;

    }
    
    
    public Integrated GetReportIntegratedSubmitted(String sector, String year) throws ParseException {
        RecordDAO recordDAO = new RecordDAO();
        DBConnectionFactory myFactory = getInstance();
        int rows;
        try (Connection conn = myFactory.getConnection()) {
            Integrated Integrated = new   Integrated();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM integrated_report WHERE `isDraft` = 0 AND `sector` = ? AND `year` = ?;");
            pstmt.setString(1, sector);
            pstmt.setString(2, year);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Integrated.setYear(rs.getInt("year"));
                Integrated.setSector(rs.getString("sector"));
                Integrated.setText(rs.getString("text"));
                Integrated.setIsDraft(rs.getBoolean("isDraft"));
                Integrated.setCreatedByName(recordDAO.GetUserName(rs.getInt("createdBy")));            
            }
            pstmt.close();
            rs.close();
            conn.close();
            return Integrated;
        } catch (SQLException ex) {
            getLogger(ReportDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;

    }

    public boolean deleteIntegratedAnalysis(String sector, int year) throws SQLException {
        boolean rows = false;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            PreparedStatement pstmt;
            try (Connection conn = myFactory.getConnection()) {
                String delete = "DELETE FROM integrated_report WHERE sector = ? and `year` = ?";
                pstmt = conn.prepareStatement(delete);
                pstmt.setString(1, sector);
                 pstmt.setInt(2, year);
                int isDeleted = pstmt.executeUpdate();
                if (isDeleted > 0) {
                    rows = true;
                }
                pstmt.close();
                conn.close();
            }
            return rows;
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean deleteRecordReportAnalysis(String sector, int year) throws SQLException {
        boolean rows = false;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            PreparedStatement pstmt;
            try (Connection conn = myFactory.getConnection()) {
                String delete = "DELETE FROM analysis_report WHERE sector = ? and `year` = ?";
                pstmt = conn.prepareStatement(delete);
                pstmt.setString(1, sector);
                pstmt.setInt(2, year);
                int isDeleted = pstmt.executeUpdate();
                if (isDeleted > 0) {
                    rows = true;
                }
                pstmt.close();
                conn.close();
            }
            return rows;
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteRecordMatrix(String sector, int year) throws SQLException {
        boolean rows = false;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            PreparedStatement pstmt;
            try (Connection conn = myFactory.getConnection()) {
                String delete = "DELETE FROM matrix_report WHERE sector = ? and `year` = ?";
                pstmt = conn.prepareStatement(delete);
                pstmt.setString(1, sector);
                 pstmt.setInt(2, year);
                int isDeleted = pstmt.executeUpdate();
                if (isDeleted > 0) {
                    rows = true;
                }
                pstmt.close();
                conn.close();
            }
            return rows;
        } catch (SQLException ex) {
            getLogger(RecordDAO.class.getName()).log(SEVERE, null, ex);
        }
        return false;
    }
}
