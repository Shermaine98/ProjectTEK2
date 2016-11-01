/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package dao;

import db.DBConnectionFactoryStorageDB;
import model.TaskModel;
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

/**
 *
 * @author Gian Carlo Roxas
 * @author shermaine Sy
 * @author Geraldine Atayan
 *
 */
public class NotificationDAO {

    SimpleDateFormat sdf = new SimpleDateFormat("MMM. dd, yyyy hh:mm a");

    public ArrayList<TaskModel> NotificationUploder(int year, String position) throws SQLException, ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();
            TaskDAO taskDAO = new TaskDAO();
            ArrayList<TaskModel> taskModel = taskDAO.getTaskUploadeStatus(year, position);
            try (Connection conn = myFactory.getConnection()) {
                int z = 0;
                ArrayList<TaskModel> taskModelFinal = new ArrayList<TaskModel>();

                Date now = new Date();
                Timestamp currentTimeStamp = new Timestamp(now.getTime());
                RecordDAO recordDAO = new RecordDAO();

                //GETTING 5 DAYS NOTIFICATION
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, -5);
                Date todate1 = cal.getTime();

                Timestamp prevStamp = new Timestamp(todate1.getTime());
                for (int i = 0; i < taskModel.size(); i++) {
                    String checkExist = "SELECT R.TimeUploaded, R.approved, R.approvedBy from RECORDS R\n"
                            + "WHERE R.FORMID = ?\n"
                            + " AND R.TimeUploaded > ?\n"
                            + " AND R.TimeUploaded < ? AND R.approved = 1;";
                    PreparedStatement pstmt1 = conn.prepareStatement(checkExist);
                    int check = taskModel.get(i).getFormID();
                    pstmt1.setInt(1, check);
                    pstmt1.setTimestamp(2, prevStamp);
                    pstmt1.setTimestamp(3, currentTimeStamp);
                    ResultSet rs = pstmt1.executeQuery();

                    if (rs.next()) {
                        z = rs.getRow();
                        if (z > 0) {
                            TaskModel temp = new TaskModel();
                            temp.setreportName(taskModel.get(i).getReportName());
                            temp.setDuedate(taskModel.get(i).getDuedate());
                            temp.setReportType(taskModel.get(i).getReportType());
                            //no use but to save for now
                            temp.setFormID(taskModel.get(i).getFormID());
                            temp.setStatus("Approved");
                            temp.setName(recordDAO.GetUserName(rs.getInt("R.approvedBy")));
                            temp.setTimeStamp(sdf.format(rs.getTimestamp("R.TimeUploaded")));
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

    public ArrayList<TaskModel> NotificationRejected(int year, String position) throws SQLException, ParseException {
        try {
            TaskDAO taskDAO = new TaskDAO();
            ArrayList<TaskModel> taskModel = taskDAO.getTaskUploadeStatus(year, position);
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();

            try (Connection conn = myFactory.getConnection()) {
                int z = 0;
                ArrayList<TaskModel> taskModelFinal = new ArrayList<TaskModel>();

                Date now = new Date();
                Timestamp currentTimeStamp = new Timestamp(now.getTime());
                RecordDAO recordDAO = new RecordDAO();

                //GETTING 5 DAYS NOTIFICATION
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, -5);
                Date todate1 = cal.getTime();
                Timestamp prevStamp = new Timestamp(todate1.getTime());
                for (int i = 0; i < taskModel.size(); i++) {
                    String checkExist = "SELECT R.TimeUploaded, R.approved, R.approvedBy from RECORDS R\n"
                            + "WHERE R.FORMID = ?\n"
                            + " AND R.TimeUploaded > ?\n"
                            + " AND R.TimeUploaded < ? AND R.approved = -1;";
                    PreparedStatement pstmt1 = conn.prepareStatement(checkExist);
                    int check = taskModel.get(i).getFormID();
                    pstmt1.setInt(1, check);
                    pstmt1.setTimestamp(2, prevStamp);
                    pstmt1.setTimestamp(3, currentTimeStamp);
                    ResultSet rs = pstmt1.executeQuery();

                    if (rs.next()) {
                        z = rs.getRow();
                        if (z > 0) {
                            TaskModel temp = new TaskModel();
                            temp.setreportName(taskModel.get(i).getReportName());
                            temp.setDuedate(taskModel.get(i).getDuedate());
                            temp.setReportType(taskModel.get(i).getReportType());
                            //no use but to save for now
                            temp.setStatus("Rejected");
                            temp.setFormID(taskModel.get(i).getFormID());
                            temp.setName(recordDAO.GetUserName(rs.getInt("R.approvedBy")));
                            temp.setTimeStamp(sdf.format(rs.getTimestamp("R.TimeUploaded")));
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

    public ArrayList<TaskModel> NotificationApprovalRecord(int year, String position) throws SQLException, ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();

            try (Connection conn = myFactory.getConnection()) {
                RecordDAO recordDAO = new RecordDAO();
                int z = 0;
                ArrayList<TaskModel> taskModelFinal = new ArrayList<TaskModel>();
                TaskDAO taskDAO = new TaskDAO();
                ArrayList<TaskModel> taskModel = taskDAO.getTaskUploadeStatus(year, position);

                Date now = new Date();
                Timestamp currentTimeStamp = new Timestamp(now.getTime());

                //GETTING 5 DAYS NOTIFICATION
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, -5);
                Date todate1 = cal.getTime();
                Timestamp prevStamp = new Timestamp(todate1.getTime());
                for (int i = 0; i < taskModel.size(); i++) {
                    String checkExist = "SELECT R.TimeUploaded, R.approved, R.UploadedBy from RECORDS R\n"
                            + "WHERE R.FORMID = ?\n"
                            + "  AND R.TimeUploaded > ? \n"
                            + "  AND R.TimeUploaded < ? AND R.approved = 0;";
                    PreparedStatement pstmt1 = conn.prepareStatement(checkExist);
                    int check = taskModel.get(i).getFormID();
                    pstmt1.setInt(1, check);
                    pstmt1.setTimestamp(2, prevStamp);
                    pstmt1.setTimestamp(3, currentTimeStamp);
                    ResultSet rs = pstmt1.executeQuery();

                    if (rs.next()) {
                           z = rs.getRow();
                         if (z > 0) {
                            TaskModel temp = new TaskModel();
                            temp.setreportName(taskModel.get(i).getReportName());
                            temp.setDuedate(taskModel.get(i).getDuedate());
                            temp.setTimeStamp(sdf.format(rs.getTimestamp("R.TimeUploaded")));
                            temp.setReportType(taskModel.get(i).getReportType());
                            temp.setStatus("Approval");
                            temp.setName(recordDAO.GetUserName(rs.getInt("R.UploadedBy")));
                            //no use but to save for now
                            temp.setFormID(taskModel.get(i).getFormID());
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

    public ArrayList<TaskModel> NotificationAlertUploader(int year, String user) throws SQLException, ParseException {

        ArrayList<TaskModel> taskModelFinal = new ArrayList<TaskModel>();
        TaskDAO taskDAO = new TaskDAO();
        ArrayList<TaskModel> taskModel = taskDAO.getTaskUploadeStatus(year, user);
        Date todate1 = new Date();
        RecordDAO recordDAO = new RecordDAO();
        for (int i = 0; i < taskModel.size(); i++) {
            
            if (taskModel.get(i).getStatus().equalsIgnoreCase("Delayed")) {
                //long dateDelayed = Math.round(taskModel.get(i).getDuedate().getTime() - todate1.getTime());
                int dateDelayed = Math.abs(taskModel.get(i).getDateDiff());
                TaskModel temp = new TaskModel();
                temp.setreportName(taskModel.get(i).getReportName());
                temp.setDuedate(taskModel.get(i).getDuedate());
                temp.setTimeStamp(String.valueOf(dateDelayed));
                temp.setReportType(taskModel.get(i).getReportType());
                temp.setStatus(taskModel.get(i).getStatus());
                temp.setFormID(taskModel.get(i).getFormID());
                temp.setName("none");
                taskModelFinal.add(temp);
            }
        }

        return taskModelFinal;

    }

}
