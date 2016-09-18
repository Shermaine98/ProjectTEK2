/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import db.DBConnectionFactoryStorageDB;
import model.TaskModelUploader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import static java.util.logging.Logger.getLogger;
import static java.util.logging.Logger.getLogger;
import static java.util.logging.Logger.getLogger;
import static java.util.logging.Logger.getLogger;
import static java.util.logging.Logger.getLogger;
import static java.util.logging.Logger.getLogger;
import static java.util.logging.Logger.getLogger;
import static java.util.logging.Logger.getLogger;
import static java.util.logging.Logger.getLogger;
import static java.util.logging.Logger.getLogger;
import static java.util.logging.Logger.getLogger;
import static java.util.logging.Logger.getLogger;
import static java.util.logging.Logger.getLogger;
import static java.util.logging.Logger.getLogger;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Shermaine
 */
public class NotificationDAO {
    SimpleDateFormat sdf = new SimpleDateFormat("MMM. dd, yyyy hh:mm a");

    public ArrayList<TaskModelUploader> NotificationUploder(String year) throws SQLException, ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();

            try (Connection conn = myFactory.getConnection()) {
                int z = 0;
                ArrayList<TaskModelUploader> taskModelFinal = new ArrayList<TaskModelUploader>();
                TaskModelUploader taskModel = new TaskModelUploader();
                taskModel.taskModel(year);
                taskModel.getTaskModel().size();
                Date now = new Date();
                Timestamp currentTimeStamp = new Timestamp(now.getTime());
                RecordDAO recordDAO = new RecordDAO();

                //GETTING 5 DAYS NOTIFICATION
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, -5);
                Date todate1 = cal.getTime();
                Timestamp prevStamp = new Timestamp(todate1.getTime());
                for (int i = 0; i < taskModel.getTaskModel().size(); i++) {
                    String checkExist = "SELECT R.TimeUploaded, R.approved, R.approvedBy from RECORDS R\n"
                            + "WHERE R.FORMID = ?\n"
                            + " AND R.TimeUploaded > ?\n"
                            + " AND R.TimeUploaded < ? AND R.approved = 1;";
                    PreparedStatement pstmt1 = conn.prepareStatement(checkExist);
                    int check = taskModel.getTaskModel().get(i).getFormID();
                    pstmt1.setInt(1, check);
                    pstmt1.setTimestamp(2, prevStamp);
                    pstmt1.setTimestamp(3, currentTimeStamp);
                    ResultSet rs = pstmt1.executeQuery();

                    if (rs.next()) {
                        z = rs.getRow();
                        if (z > 0) {
                            TaskModelUploader temp = new TaskModelUploader();
                            temp.setTask(taskModel.getTaskModel().get(i).getTask());
                            temp.setDuedate(taskModel.getTaskModel().get(i).getDuedate());
                            temp.setReportType(taskModel.getTaskModel().get(i).getReportType());
                            //no use but to save for now
                            temp.setFormID(taskModel.getTaskModel().get(i).getFormID());
                            temp.setStatus("Approved");
                            temp.setName(recordDAO.GetUserName(rs.getInt("R.approvedBy")));
                            temp.setTimeStampString(sdf.format(rs.getTimestamp("R.TimeUploaded")));
                            taskModelFinal.add(temp);
                        }
                    }
                    rs.close();
                    pstmt1.close();
                }
                return taskModelFinal;
            }

        } catch (SQLException ex) {
            getLogger(TaskDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
    
    
    public ArrayList<TaskModelUploader> NotificationRejected(String year) throws SQLException, ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();

            try (Connection conn = myFactory.getConnection()) {
                int z = 0;
                ArrayList<TaskModelUploader> taskModelFinal = new ArrayList<TaskModelUploader>();
                TaskModelUploader taskModel = new TaskModelUploader();
                taskModel.taskModel(year);
                taskModel.getTaskModel().size();
                Date now = new Date();
                Timestamp currentTimeStamp = new Timestamp(now.getTime());
                RecordDAO recordDAO = new RecordDAO();

                //GETTING 5 DAYS NOTIFICATION
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, -5);
                Date todate1 = cal.getTime();
                Timestamp prevStamp = new Timestamp(todate1.getTime());
                for (int i = 0; i < taskModel.getTaskModel().size(); i++) {
                    String checkExist = "SELECT R.TimeUploaded, R.approved, R.approvedBy from RECORDS R\n"
                            + "WHERE R.FORMID = ?\n"
                            + " AND R.TimeUploaded > ?\n"
                            + " AND R.TimeUploaded < ? AND R.approved = -1;";
                    PreparedStatement pstmt1 = conn.prepareStatement(checkExist);
                    int check = taskModel.getTaskModel().get(i).getFormID();
                    pstmt1.setInt(1, check);
                    pstmt1.setTimestamp(2, prevStamp);
                    pstmt1.setTimestamp(3, currentTimeStamp);
                    ResultSet rs = pstmt1.executeQuery();

                    if (rs.next()) {
                        z = rs.getRow();
                        if (z > 0) {
                            TaskModelUploader temp = new TaskModelUploader();
                            temp.setTask(taskModel.getTaskModel().get(i).getTask());
                            temp.setDuedate(taskModel.getTaskModel().get(i).getDuedate());
                            temp.setReportType(taskModel.getTaskModel().get(i).getReportType());
                            //no use but to save for now
                            temp.setStatus("Rejected");
                            temp.setFormID(taskModel.getTaskModel().get(i).getFormID());
                            temp.setName(recordDAO.GetUserName(rs.getInt("R.approvedBy")));
                            temp.setTimeStampString(sdf.format(rs.getTimestamp("R.TimeUploaded")));
                            taskModelFinal.add(temp);
                        }
                    }
                    rs.close();
                    pstmt1.close();
                }
                return taskModelFinal;
            }

        } catch (SQLException ex) {
            getLogger(TaskDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<TaskModelUploader> NotificationApprovalRecord(String year) throws SQLException, ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();

            try (Connection conn = myFactory.getConnection()) {
                RecordDAO recordDAO = new RecordDAO();
                int z = 0;
                ArrayList<TaskModelUploader> taskModelFinal = new ArrayList<TaskModelUploader>();
                TaskModelUploader taskModel = new TaskModelUploader();
                taskModel.taskModel(year);
                taskModel.getTaskModel().size();

                Date now = new Date();
                Timestamp currentTimeStamp = new Timestamp(now.getTime());

                //GETTING 5 DAYS NOTIFICATION
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, -5);
                Date todate1 = cal.getTime();
                Timestamp prevStamp = new Timestamp(todate1.getTime());
                for (int i = 0; i < taskModel.getTaskModel().size(); i++) {
                    String checkExist = "SELECT R.TimeUploaded, R.approved, R.UploadedBy from RECORDS R\n"
                            + "WHERE R.FORMID = ?\n"
                            + "  AND R.TimeUploaded > ? \n"
                            + "  AND R.TimeUploaded < ? AND R.approved = 0;";
                    PreparedStatement pstmt1 = conn.prepareStatement(checkExist);
                    int check = taskModel.getTaskModel().get(i).getFormID();
                    pstmt1.setInt(1, check);
                    pstmt1.setTimestamp(2, prevStamp);
                    pstmt1.setTimestamp(3, currentTimeStamp);
                    ResultSet rs = pstmt1.executeQuery();

                    if (rs.next()) {
                        z = rs.getRow();
                        if (z > 0) {
                            TaskModelUploader temp = new TaskModelUploader();
                            temp.setTask(taskModel.getTaskModel().get(i).getTask());
                            temp.setDuedate(taskModel.getTaskModel().get(i).getDuedate());
                            temp.setTimeStampString(sdf.format(rs.getTimestamp("R.TimeUploaded")));
                            temp.setReportType(taskModel.getTaskModel().get(i).getReportType());
                            temp.setStatus("Approval");
                            temp.setName(recordDAO.GetUserName(rs.getInt("R.UploadedBy")));
                            //no use but to save for now
                            temp.setFormID(taskModel.getTaskModel().get(i).getFormID());
                            taskModelFinal.add(temp);
                        }
                    }
                    rs.close();
                    pstmt1.close();
                }
                return taskModelFinal;
            }

        } catch (SQLException ex) {
            getLogger(TaskDAO.class.getName()).log(SEVERE, null, ex);
        }
        return null;
    }
}
