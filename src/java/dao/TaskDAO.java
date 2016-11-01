/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package dao;

import db.DBConnectionFactory;
import db.DBConnectionFactoryStorageDB;
import model.TaskModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Gian Carlo Roxas
 * @author shermaine Sy
 * @author Geraldine Atayan
 *
 */
public class TaskDAO {

    
    DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

    public ArrayList<TaskModel> getTask(int year, String position) throws ParseException, SQLException {

        DBConnectionFactory myFactory1 = DBConnectionFactory.getInstance();
        ArrayList<TaskModel> taskModel;
        try (Connection conn = myFactory1.getConnection()) {
            taskModel = new ArrayList<>();
            String query = "select * from task where `position` = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, position);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int formId = rs.getInt("reportID")  + year;
                TaskModel taskModelTemp = new TaskModel();
                taskModelTemp.setReportType(rs.getString("reportType"));
                taskModelTemp.setSector(rs.getString("sector"));
                taskModelTemp.setDuedate(formatter.parse(year + "/" + rs.getInt("month") + "/" + rs.getInt("day")));
                String query2 = "select datediff(?,NOW()) as 'diffDate';";
                PreparedStatement pstmt2 = conn.prepareStatement(query2);
                pstmt2.setString(1, year + "/" + rs.getInt("month") + "/" + rs.getInt("day"));
                
                ResultSet rs2 = pstmt2.executeQuery();
                while(rs2.next()){
                    taskModelTemp.setDateDiff(rs2.getInt("diffDate"));
                }
                
                taskModelTemp.setreportName(rs.getString("reportName"));
                taskModelTemp.setFormID(formId);
                taskModel.add(taskModelTemp);
            }
          conn.close();
        }
        return taskModel;
    }

    public ArrayList<TaskModel> getTaskUploadeStatus(int year, String position) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            ArrayList<TaskModel> taskModels = getTask(year, position);
            try (Connection conn = myFactory.getConnection()) {
                int z = 0;
                ArrayList<TaskModel> taskModelFinal = new ArrayList<>();

                for (int i = 0; i < taskModels.size(); i++) {
                    String query = "SELECT * FROM RECORDS Where formID = ?;";
                    PreparedStatement pstmt1 = conn.prepareStatement(query);
                    int check = taskModels.get(i).getFormID();
                    pstmt1.setInt(1, check);
                    ResultSet rs = pstmt1.executeQuery();
                    TaskModel temp = new TaskModel();
                    if (rs.next()) {
                        temp.setDateDiff(taskModels.get(i).getDateDiff());
                        if (rs.getInt("APPROVED") == 1) {
                            temp.setreportName(taskModels.get(i).getReportName());
                            temp.setDuedate(taskModels.get(i).getDuedate());
                            temp.setReportType(taskModels.get(i).getReportType());
                            temp.setStatus("Completed");
                            temp.setSector(taskModels.get(i).getSector());
                            temp.setFormID(taskModels.get(i).getFormID());
                            taskModelFinal.add(temp);
                        } else if (rs.getInt("APPROVED") == -1) {
                            temp.setreportName(taskModels.get(i).getReportName());
                            temp.setDuedate(taskModels.get(i).getDuedate());
                            temp.setReportType(taskModels.get(i).getReportType());
                            temp.setSector(taskModels.get(i).getSector());
                            temp.setStatus("Rejected");
                            temp.setFormID(taskModels.get(i).getFormID());
                            taskModelFinal.add(temp);
                        } else if (rs.getInt("APPROVED") == 0 && rs.getInt("VALIDATED") == 0) {
                            temp.setreportName(taskModels.get(i).getReportName());
                            temp.setSector(taskModels.get(i).getSector());
                            temp.setDuedate(taskModels.get(i).getDuedate());
                            temp.setReportType(taskModels.get(i).getReportType());
                            temp.setStatus("Incomplete");
                            temp.setFormID(taskModels.get(i).getFormID());
                            taskModelFinal.add(temp);
                        } else if (rs.getInt("APPROVED") == 0 && rs.getInt("VALIDATED") == 1) {
                            temp.setreportName(taskModels.get(i).getReportName());
                            temp.setDuedate(taskModels.get(i).getDuedate());
                            temp.setSector(taskModels.get(i).getSector());
                            temp.setReportType(taskModels.get(i).getReportType());
                            temp.setStatus("For Approval");
                            temp.setFormID(taskModels.get(i).getFormID());
                            taskModelFinal.add(temp);
                        }
                    } else {
                        Date now = new Date();
                        int x = now.compareTo(taskModels.get(i).getDuedate());
                        if (x == 1) {
                            temp.setDateDiff(taskModels.get(i).getDateDiff());
                            temp.setreportName(taskModels.get(i).getReportName());
                            temp.setDuedate(taskModels.get(i).getDuedate());
                            temp.setReportType(taskModels.get(i).getReportType());
                            temp.setFormID(taskModels.get(i).getFormID());
                            temp.setSector(taskModels.get(i).getSector());
                            temp.setStatus("Delayed");
                            taskModelFinal.add(temp);
                        } else {
                            temp.setDateDiff(taskModels.get(i).getDateDiff());
                            temp.setreportName(taskModels.get(i).getReportName());
                            temp.setDuedate(taskModels.get(i).getDuedate());
                            temp.setReportType(taskModels.get(i).getReportType());
                            temp.setFormID(taskModels.get(i).getFormID());
                            temp.setSector(taskModels.get(i).getSector());
                            temp.setStatus("Pending");
                            taskModelFinal.add(temp);
                        }

                    }

                    rs.close();
                    pstmt1.close();
                }
                conn.close();
                return taskModelFinal;
            }

        } catch (SQLException ex) {
            getLogger(TaskDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<TaskModel> checkTaskHead(int year, String position, String sector) throws SQLException, ParseException {
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        try (Connection conn = myFactory.getConnection()) {
            int z = 0;
            ArrayList<TaskModel> taskModelFinal = new ArrayList<>();
            ArrayList<TaskModel> taskModel = getTask(year, position);

            for (int i = 0; i < taskModel.size(); i++) {
                if (taskModel.get(i).getReportType().equalsIgnoreCase("Analysis") && sector.equalsIgnoreCase(taskModel.get(i).getSector())) {
                    String checkExist = "SELECT isDraft from analysis_report where sector = ? and `year` = ?";
                    PreparedStatement pstmt = conn.prepareStatement(checkExist);
                    pstmt.setString(1, sector);
                    pstmt.setInt(2, year);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {

                        String status = rs.getString("isDraft");
                        if (status.equalsIgnoreCase("1")) {
                            TaskModel temp = new TaskModel();
                            temp.setreportName(taskModel.get(i).getReportName());
                            temp.setDuedate(taskModel.get(i).getDuedate());
                            temp.setStatus("Saved");
                            temp.setSector(taskModel.get(i).getSector());
                            temp.setReportType(taskModel.get(i).getReportType());
                            temp.setFormID(taskModel.get(i).getFormID());
                            taskModelFinal.add(temp);
                        } else if (status.equalsIgnoreCase("0")) {
                            TaskModel temp = new TaskModel();

                            temp.setreportName(taskModel.get(i).getReportName());
                            temp.setDuedate(taskModel.get(i).getDuedate());
                            temp.setStatus("Completed");
                            temp.setReportType(taskModel.get(i).getReportType());
                            temp.setFormID(taskModel.get(i).getFormID());
                            temp.setSector(taskModel.get(i).getSector());
                            taskModelFinal.add(temp);
                        }

                    } else {

                        TaskModel temp = new TaskModel();

                        Date now = new Date();
                        int x = now.compareTo(taskModel.get(i).getDuedate());
                        if (x == 1) {
                            temp.setreportName(taskModel.get(i).getReportName());
                            temp.setDuedate(taskModel.get(i).getDuedate());
                            temp.setReportType(taskModel.get(i).getReportType());
                            temp.setFormID(taskModel.get(i).getFormID());
                            temp.setSector(taskModel.get(i).getSector());
                            temp.setStatus("Delayed");
                            taskModelFinal.add(temp);
                        } else {
                            temp.setreportName(taskModel.get(i).getReportName());
                            temp.setDuedate(taskModel.get(i).getDuedate());
                            temp.setReportType(taskModel.get(i).getReportType());
                            temp.setFormID(taskModel.get(i).getFormID());
                            temp.setSector(taskModel.get(i).getSector());
                            temp.setStatus("Pending");
                            taskModelFinal.add(temp);
                        }
                    }

                    rs.close();
                    pstmt.close();
                }
            }

            for (int i = 0; i < taskModel.size(); i++) {
                if (taskModel.get(i).getReportType().equalsIgnoreCase("Matrix") && sector.equalsIgnoreCase(taskModel.get(i).getSector())) {
                    String checkExist = "SELECT isDraft from matrix_report where sector = ? and `year` = ?";
                    PreparedStatement pstmt = conn.prepareStatement(checkExist);
                    pstmt.setString(1, sector);
                    pstmt.setInt(2, year);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        String status = rs.getString("isDraft");
                        if (status.equalsIgnoreCase("1")) {
                            TaskModel temp = new TaskModel();
                            temp.setreportName(taskModel.get(i).getReportName());
                            temp.setDuedate(taskModel.get(i).getDuedate());
                            temp.setStatus("Saved");
                            temp.setSector(taskModel.get(i).getSector());
                            temp.setReportType(taskModel.get(i).getReportType());
                            temp.setFormID(taskModel.get(i).getFormID());
                            taskModelFinal.add(temp);
                        } else if (status.equalsIgnoreCase("0")) {
                            TaskModel temp = new TaskModel();

                            temp.setreportName(taskModel.get(i).getReportName());
                            temp.setDuedate(taskModel.get(i).getDuedate());
                            temp.setStatus("Completed");
                            temp.setReportType(taskModel.get(i).getReportType());
                            temp.setFormID(taskModel.get(i).getFormID());

                            temp.setSector(taskModel.get(i).getSector());
                            taskModelFinal.add(temp);
                        }
                    } else {

                        TaskModel temp = new TaskModel();

                        Date now = new Date();
                        int x = now.compareTo(taskModel.get(i).getDuedate());
                        if (x == 1) {
                            temp.setreportName(taskModel.get(i).getReportName());
                            temp.setDuedate(taskModel.get(i).getDuedate());
                            temp.setReportType(taskModel.get(i).getReportType());
                            temp.setFormID(taskModel.get(i).getFormID());

                            temp.setSector(taskModel.get(i).getSector());
                            temp.setStatus("Delayed");
                            taskModelFinal.add(temp);
                        } else {
                            temp.setreportName(taskModel.get(i).getReportName());
                            temp.setDuedate(taskModel.get(i).getDuedate());
                            temp.setReportType(taskModel.get(i).getReportType());
                            temp.setFormID(taskModel.get(i).getFormID());
                            temp.setSector(taskModel.get(i).getSector());
                            temp.setStatus("Pending");
                            taskModelFinal.add(temp);
                        }
                    }
                    rs.close();
                    pstmt.close();
                }
            }

            for (int i = 0; i < taskModel.size(); i++) {
                if (taskModel.get(i).getReportType().equalsIgnoreCase("Integrated") && sector.equalsIgnoreCase(taskModel.get(i).getSector())) {
                    String checkExist = "SELECT isDraft from integrated_report where sector = ? and `year` = ?";
                    PreparedStatement pstmt = conn.prepareStatement(checkExist);
                    pstmt.setString(1, sector);
                    pstmt.setInt(2, year);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        String status = rs.getString("isDraft");
                        if (status.equalsIgnoreCase("1")) {
                            TaskModel temp = new TaskModel();

                            temp.setreportName(taskModel.get(i).getReportName());
                            temp.setDuedate(taskModel.get(i).getDuedate());
                            temp.setStatus("Saved");
                            temp.setReportType(taskModel.get(i).getReportType());
                            temp.setFormID(taskModel.get(i).getFormID());

                            temp.setSector(taskModel.get(i).getSector());
                            taskModelFinal.add(temp);
                        } else if (status.equalsIgnoreCase("0")) {
                            TaskModel temp = new TaskModel();

                            temp.setreportName(taskModel.get(i).getReportName());
                            temp.setDuedate(taskModel.get(i).getDuedate());
                            temp.setStatus("Completed");
                            temp.setReportType(taskModel.get(i).getReportType());
                            temp.setFormID(taskModel.get(i).getFormID());

                            temp.setSector(taskModel.get(i).getSector());
                            taskModelFinal.add(temp);
                        }
                    } else {

                        TaskModel temp = new TaskModel();

                        Date now = new Date();
                        int x = now.compareTo(taskModel.get(i).getDuedate());
                        if (x == 1) {
                            temp.setreportName(taskModel.get(i).getReportName());
                            temp.setDuedate(taskModel.get(i).getDuedate());
                            temp.setReportType(taskModel.get(i).getReportType());
                            temp.setFormID(taskModel.get(i).getFormID());

                            temp.setSector(taskModel.get(i).getSector());
                            temp.setStatus("Delayed");
                            taskModelFinal.add(temp);
                        } else {
                            temp.setreportName(taskModel.get(i).getReportName());
                            temp.setDuedate(taskModel.get(i).getDuedate());
                            temp.setReportType(taskModel.get(i).getReportType());
                            temp.setFormID(taskModel.get(i).getFormID());
                            temp.setSector(taskModel.get(i).getSector());
                            temp.setStatus("Pending");
                            taskModelFinal.add(temp);
                        }
                    }
                    rs.close();
                    pstmt.close();

                }
            }
            conn.close();
            return taskModelFinal;
        }

    }
}

//Task Uploader
//Task Head

