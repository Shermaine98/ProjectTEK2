/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package dao;

import db.DBConnectionFactory;
import static db.DBConnectionFactory.getInstance;
import db.DBConnectionFactoryStorageDB;
import model.TaskModelHead;
import model.TaskModelUploader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    SimpleDateFormat sdf = new SimpleDateFormat("MMM. dd, yyyy");

    public ArrayList<TaskModelUploader> getUploadedValidated(String year) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();

            try (Connection conn = myFactory.getConnection()) {
                int z = 0;
                ArrayList<TaskModelUploader> taskModelFinal = new ArrayList<TaskModelUploader>();
                TaskModelUploader taskModel = new TaskModelUploader();
                taskModel.taskModel(year);

                for (int i = 0; i < taskModel.getTaskModel().size(); i++) {
                    String query = "SELECT * FROM RECORDS WHERE FORMID = ? AND `VALIDATED` = 1 AND `APPROVED` = 0;";
                    PreparedStatement pstmt1 = conn.prepareStatement(query);
                    int check = taskModel.getTaskModel().get(i).getFormID();
                    pstmt1.setInt(1, check);
                    ResultSet rs = pstmt1.executeQuery();

                    if (rs.next()) {
                        TaskModelUploader temp = new TaskModelUploader();
                        temp.setTask(taskModel.getTaskModel().get(i).getTask());
                        temp.setDuedate(taskModel.getTaskModel().get(i).getDuedate());
                        temp.setsDueDate(sdf.format(taskModel.getTaskModel().get(i).getDuedate()));
                        temp.setStatus("For Approval");
                        temp.setReportType(taskModel.getTaskModel().get(i).getReportType());
                        //no use but to save for now
                        temp.setFormID(taskModel.getTaskModel().get(i).getFormID());
                        taskModelFinal.add(temp);
                    }
                    rs.close();
                    pstmt1.close();
                }

                for (int i = 0; i < taskModel.getTaskModel().size(); i++) {
                    String query = "SELECT * FROM RECORDS WHERE FORMID = ? AND `VALIDATED` = 0 AND `APPROVED` = 0;";
                    PreparedStatement pstmt1 = conn.prepareStatement(query);
                    int check = taskModel.getTaskModel().get(i).getFormID();
                    pstmt1.setInt(1, check);
                    ResultSet rs = pstmt1.executeQuery();

                    if (rs.next()) {
                        TaskModelUploader temp = new TaskModelUploader();
                        temp.setTask(taskModel.getTaskModel().get(i).getTask());
                        temp.setDuedate(taskModel.getTaskModel().get(i).getDuedate());
                        temp.setsDueDate(sdf.format(taskModel.getTaskModel().get(i).getDuedate()));
                        temp.setStatus("On-Going");
                        temp.setReportType(taskModel.getTaskModel().get(i).getReportType());
                        //no use but to save for now
                        temp.setFormID(taskModel.getTaskModel().get(i).getFormID());
                        taskModelFinal.add(temp);
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

    public ArrayList<TaskModelUploader> getUploadedApprovedReject(String year) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();

            try (Connection conn = myFactory.getConnection()) {
                int z = 0;
                ArrayList<TaskModelUploader> taskModelFinal = new ArrayList<TaskModelUploader>();
                TaskModelUploader taskModel = new TaskModelUploader();
                taskModel.taskModel(year);

                for (int i = 0; i < taskModel.getTaskModel().size(); i++) {
                    String query = "SELECT * FROM RECORDS WHERE FORMID = ? AND `APPROVED` = 1;";
                    PreparedStatement pstmt1 = conn.prepareStatement(query);
                    int check = taskModel.getTaskModel().get(i).getFormID();
                    pstmt1.setInt(1, check);
                    ResultSet rs = pstmt1.executeQuery();

                    if (rs.next()) {
                        TaskModelUploader temp = new TaskModelUploader();
                        temp.setTask(taskModel.getTaskModel().get(i).getTask());
                        temp.setDuedate(taskModel.getTaskModel().get(i).getDuedate());
                        temp.setsDueDate(sdf.format(taskModel.getTaskModel().get(i).getDuedate()));
                        temp.setStatus("Approved");
                        temp.setReportType(taskModel.getTaskModel().get(i).getReportType());
                        //no use but to save for now
                        temp.setFormID(taskModel.getTaskModel().get(i).getFormID());
                        taskModelFinal.add(temp);
                    }
                    rs.close();
                    pstmt1.close();
                }

                for (int i = 0; i < taskModel.getTaskModel().size(); i++) {
                    String checkExist = "SELECT FORMID FROM RECORDS WHERE FORMID = ? AND `APPROVED`=-1";
                    PreparedStatement pstmt1 = conn.prepareStatement(checkExist);
                    int check = taskModel.getTaskModel().get(i).getFormID();
                    pstmt1.setInt(1, check);
                    ResultSet rs = pstmt1.executeQuery();
                    if (rs.next()) {
                        z = rs.getInt("FORMID");
                        if (z > 0) {
                            TaskModelUploader temp = new TaskModelUploader();
                            temp.setTask(taskModel.getTaskModel().get(i).getTask());
                            temp.setDuedate(taskModel.getTaskModel().get(i).getDuedate());
                            temp.setsDueDate(sdf.format(taskModel.getTaskModel().get(i).getDuedate()));
                            temp.setStatus("Rejected");
                            temp.setReportType(taskModel.getTaskModel().get(i).getReportType());
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

    public ArrayList<TaskModelUploader> getNotUploaded(String year) throws ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();

            try (Connection conn = myFactory.getConnection()) {
                int z = 0;
                ArrayList<TaskModelUploader> taskModelFinal = new ArrayList<TaskModelUploader>();
                TaskModelUploader taskModel = new TaskModelUploader();
                taskModel.taskModel(year);
                taskModel.getTaskModel().size();

                for (int i = 0; i < taskModel.getTaskModel().size(); i++) {
                    String checkExist = "SELECT EXISTS(SELECT * FROM RECORDS WHERE FORMID = ?) AS `EXISTS`;";
                    PreparedStatement pstmt1 = conn.prepareStatement(checkExist);
                    int check = taskModel.getTaskModel().get(i).getFormID();
                    pstmt1.setInt(1, check);
                    ResultSet rs = pstmt1.executeQuery();

                    if (rs.next()) {
                        z = rs.getInt("EXISTS");
                        if (z == 0) {
                            TaskModelUploader temp = new TaskModelUploader();
                            temp.setTask(taskModel.getTaskModel().get(i).getTask());
                            temp.setDuedate(taskModel.getTaskModel().get(i).getDuedate());
                            temp.setsDueDate(sdf.format(taskModel.getTaskModel().get(i).getDuedate()));
                            temp.setStatus(taskModel.getTaskModel().get(i).getStatus());
                            temp.setReportType(taskModel.getTaskModel().get(i).getReportType());
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

    public ArrayList<TaskModelUploader> checkTaskUploader(String year) throws SQLException, ParseException {
        try {
            DBConnectionFactoryStorageDB myFactory = DBConnectionFactoryStorageDB.getInstance();

            try (Connection conn = myFactory.getConnection()) {
                int z = 0;
                ArrayList<TaskModelUploader> taskModelFinal = new ArrayList<TaskModelUploader>();
                TaskModelUploader taskModel = new TaskModelUploader();
                taskModel.taskModel(year);
                taskModel.getTaskModel().size();

                for (int i = 0; i < taskModel.getTaskModel().size(); i++) {
                    String checkExist = "SELECT EXISTS(SELECT * FROM RECORDS WHERE FORMID = ?) AS `EXISTS`;";
                    PreparedStatement pstmt1 = conn.prepareStatement(checkExist);
                    int check = taskModel.getTaskModel().get(i).getFormID();
                    pstmt1.setInt(1, check);
                    ResultSet rs = pstmt1.executeQuery();

                    if (rs.next()) {
                        z = rs.getInt("EXISTS");
                        if (z == 0) {
                            TaskModelUploader temp = new TaskModelUploader();
                            temp.setTask(taskModel.getTaskModel().get(i).getTask());
                            temp.setDuedate(taskModel.getTaskModel().get(i).getDuedate());
                            temp.setsDueDate(sdf.format(taskModel.getTaskModel().get(i).getDuedate()));
                            temp.setStatus(taskModel.getTaskModel().get(i).getStatus());
                            temp.setReportType(taskModel.getTaskModel().get(i).getReportType());
                            //no use but to save for now
                            temp.setFormID(taskModel.getTaskModel().get(i).getFormID());
                            taskModelFinal.add(temp);
                        }

                    }
                    rs.close();
                    pstmt1.close();
                }

                for (int i = 0; i < taskModel.getTaskModel().size(); i++) {
                    String checkExist = "SELECT FORMID FROM RECORDS WHERE FORMID = ? AND `APPROVED`=-1";
                    PreparedStatement pstmt1 = conn.prepareStatement(checkExist);
                    int check = taskModel.getTaskModel().get(i).getFormID();
                    pstmt1.setInt(1, check);
                    ResultSet rs = pstmt1.executeQuery();
                    if (rs.next()) {
                        z = rs.getInt("FORMID");
                        if (z > 0) {
                            TaskModelUploader temp = new TaskModelUploader();
                            temp.setTask(taskModel.getTaskModel().get(i).getTask());
                            temp.setDuedate(taskModel.getTaskModel().get(i).getDuedate());
                            temp.setsDueDate(sdf.format(taskModel.getTaskModel().get(i).getDuedate()));
                            temp.setStatus("Rejected");
                            temp.setReportType(taskModel.getTaskModel().get(i).getReportType());
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

    public ArrayList<TaskModelHead> checkTaskHead(String year, String sector) throws SQLException, ParseException {
        DBConnectionFactory myFactory = getInstance();
        try (Connection conn = myFactory.getConnection()) {
            int z = 0;
            ArrayList<TaskModelHead> taskModelFinal = new ArrayList<TaskModelHead>();
            TaskModelHead taskModel = new TaskModelHead();
            taskModel.taskModel(year);
            taskModel.getTaskModel().size();

            for (int i = 0; i < taskModel.getTaskModel().size(); i++) {
                if (taskModel.getTaskModel().get(i).getReportType().equalsIgnoreCase("Analysis") && sector.equalsIgnoreCase(taskModel.getTaskModel().get(i).getSector())) {
                    String checkExist = "SELECT isDraft from analysis_report where sector = ? and `year` = ?";
                    PreparedStatement pstmt = conn.prepareStatement(checkExist);
                    pstmt.setString(1, sector);
                     pstmt.setString(2, year);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {

                        String status = rs.getString("isDraft");
                        if (status.equalsIgnoreCase("1")) {
                            TaskModelHead temp = new TaskModelHead();
                            temp.setReport(taskModel.getTaskModel().get(i).getReport());
                            temp.setDuedate(taskModel.getTaskModel().get(i).getDuedate());
                            temp.setsDueDate(sdf.format(taskModel.getTaskModel().get(i).getDuedate()));
                            temp.setStatus("Saved");
                            temp.setReportType(taskModel.getTaskModel().get(i).getReportType());
                            temp.setFormID(taskModel.getTaskModel().get(i).getFormID());
                            taskModelFinal.add(temp);
                        }

                    } else {

                        TaskModelHead temp = new TaskModelHead();
                        temp.setReport(taskModel.getTaskModel().get(i).getReport());
                        temp.setDuedate(taskModel.getTaskModel().get(i).getDuedate());
                        temp.setsDueDate(sdf.format(taskModel.getTaskModel().get(i).getDuedate()));
                        temp.setStatus(taskModel.getTaskModel().get(i).getStatus());
                        temp.setReportType(taskModel.getTaskModel().get(i).getReportType());
                        temp.setFormID(taskModel.getTaskModel().get(i).getFormID());
                        taskModelFinal.add(temp);

                    }
                    rs.close();
                    pstmt.close();
                }
            }

            for (int i = 0; i < taskModel.getTaskModel().size(); i++) {
                if (taskModel.getTaskModel().get(i).getReportType().equalsIgnoreCase("Matrix") && sector.equalsIgnoreCase(taskModel.getTaskModel().get(i).getSector())) {
                    String checkExist = "SELECT isDraft from matrix_report where sector = ? and `year` = ?";
                    PreparedStatement pstmt = conn.prepareStatement(checkExist);
                    pstmt.setString(1, sector);
                      pstmt.setString(2, year);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        String status = rs.getString("isDraft");
                        if (status.equalsIgnoreCase("1")) {
                            TaskModelHead temp = new TaskModelHead();
                            temp.setReport(taskModel.getTaskModel().get(i).getReport());
                            temp.setDuedate(taskModel.getTaskModel().get(i).getDuedate());
                            temp.setsDueDate(sdf.format(taskModel.getTaskModel().get(i).getDuedate()));
                            temp.setStatus("Saved");
                            temp.setReportType(taskModel.getTaskModel().get(i).getReportType());
                            temp.setFormID(taskModel.getTaskModel().get(i).getFormID());
                            taskModelFinal.add(temp);
                        }
                    } else {
                        TaskModelHead temp = new TaskModelHead();
                        temp.setReport(taskModel.getTaskModel().get(i).getReport());
                        temp.setDuedate(taskModel.getTaskModel().get(i).getDuedate());
                        temp.setsDueDate(sdf.format(taskModel.getTaskModel().get(i).getDuedate()));
                        temp.setStatus(taskModel.getTaskModel().get(i).getStatus());
                        temp.setReportType(taskModel.getTaskModel().get(i).getReportType());
                        temp.setFormID(taskModel.getTaskModel().get(i).getFormID());
                        taskModelFinal.add(temp);

                    }
                    rs.close();
                    pstmt.close();
                }
            }

            for (int i = 0; i < taskModel.getTaskModel().size(); i++) {
                if (taskModel.getTaskModel().get(i).getReportType().equalsIgnoreCase("Integrated") && sector.equalsIgnoreCase(taskModel.getTaskModel().get(i).getSector())) {
                    String checkExist = "SELECT isDraft from integrated_report where sector = ? and `year` = ?";
                    PreparedStatement pstmt = conn.prepareStatement(checkExist);
                    pstmt.setString(1, sector);
                     pstmt.setString(2, year);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        String status = rs.getString("isDraft");
                        if (status.equalsIgnoreCase("1")) {
                            TaskModelHead temp = new TaskModelHead();
                            temp.setReport(taskModel.getTaskModel().get(i).getReport());
                            temp.setDuedate(taskModel.getTaskModel().get(i).getDuedate());
                            temp.setsDueDate(sdf.format(taskModel.getTaskModel().get(i).getDuedate()));
                            temp.setStatus("Saved");
                            temp.setReportType(taskModel.getTaskModel().get(i).getReportType());
                            temp.setFormID(taskModel.getTaskModel().get(i).getFormID());
                            taskModelFinal.add(temp);
                        }
                    } else {

                        TaskModelHead temp = new TaskModelHead();
                        temp.setReport(taskModel.getTaskModel().get(i).getReport());
                        temp.setDuedate(taskModel.getTaskModel().get(i).getDuedate());
                        temp.setsDueDate(sdf.format(taskModel.getTaskModel().get(i).getDuedate()));
                        temp.setStatus(taskModel.getTaskModel().get(i).getStatus());
                        temp.setReportType(taskModel.getTaskModel().get(i).getReportType());
                        temp.setFormID(taskModel.getTaskModel().get(i).getFormID());
                        taskModelFinal.add(temp);

                    }
                    rs.close();
                    pstmt.close();

                    return taskModelFinal;
                }
            }
        }
        return null;
    }

}
