/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Shermaine
 */
public class TaskModelUploader {

    private String task;
    private Date duedate;
    private String sDueDate;
    private int formID;
    private String reportType;
    private String status;
    private String name;
    private Timestamp timeStamp;
    private Date now;
    String Date;

    private ArrayList<TaskModelUploader> taskModel;
    DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

    public void taskModel(String year) throws ParseException {
        now = new Date();
        //  Date test = new Date();
        //  test = formatter.parse(year  +"/"+  "02"  +"/"+  "30");
        int x = 0;
        taskModel = new ArrayList<TaskModelUploader>();
        TaskModelUploader tempEnrollmentPub = new TaskModelUploader();
        //THIS IS FOR ENROLLMENT PUBLIC
        tempEnrollmentPub.setTask("Enrollment in Public School");
        tempEnrollmentPub.setDuedate(formatter.parse(year + "/" + "07" + "/" + "30"));
        tempEnrollmentPub.setFormID(160000000 + Integer.parseInt(year));
        tempEnrollmentPub.setReportType("Education");
        x = now.compareTo(tempEnrollmentPub.getDuedate());
        if (x == 1) {
            tempEnrollmentPub.setStatus("Delayed");
        } else {
            tempEnrollmentPub.setStatus("Pending");
        }
        taskModel.add(tempEnrollmentPub);
        TaskModelUploader tempEnrollmentPri = new TaskModelUploader();

        //
        tempEnrollmentPri.setTask("Enrollment in Private School");
        tempEnrollmentPri.setDuedate(formatter.parse(year + "/" + "07" + "/" + "30"));
        tempEnrollmentPri.setFormID(140000000 + Integer.parseInt(year));
        tempEnrollmentPri.setReportType("Education");
        x = now.compareTo(tempEnrollmentPri.getDuedate());
        if (x == 1) {
            tempEnrollmentPri.setStatus("Delayed");
        } else {
            tempEnrollmentPri.setStatus("Pending");
        }
        taskModel.add(tempEnrollmentPri);
        TaskModelUploader tempNumberTPub = new TaskModelUploader();

        //
        tempNumberTPub.setTask("Number of Teachers and Classrooms for Public Schools");
        tempNumberTPub.setDuedate(formatter.parse(year + "/" + "07" + "/" + "30"));
        tempNumberTPub.setFormID(190000000 + Integer.parseInt(year));
        tempNumberTPub.setReportType("Education");

        x = now.compareTo(tempNumberTPub.getDuedate());
        if (x == 1) {
            tempNumberTPub.setStatus("Delayed");
        } else {
            tempNumberTPub.setStatus("Pending");
        }
        taskModel.add(tempNumberTPub);

        //
        TaskModelUploader tempNumberTPri = new TaskModelUploader();
        tempNumberTPri.setTask("Number of Teachers and Classrooms for Private Schools");
        tempNumberTPri.setDuedate(formatter.parse(year + "/" + "07" + "/" + "30"));
        tempNumberTPri.setFormID(120000000 + Integer.parseInt(year));

        tempNumberTPri.setReportType("Education");
        x = now.compareTo(tempNumberTPri.getDuedate());
        if (x == 1) {
            tempNumberTPri.setStatus("Delayed");
        } else {
            tempNumberTPri.setStatus("Pending");
        }
        taskModel.add(tempNumberTPri);

        //
        TaskModelUploader tempAge = new TaskModelUploader();
        tempAge.setTask("Household Population by Age Group and Sex");
        tempAge.setDuedate(formatter.parse(year + "/" + "07" + "/" + "30"));
        tempAge.setFormID(200000000+ Integer.parseInt(year));
        tempAge.setReportType("Demographics");

        x = now.compareTo(tempAge.getDuedate());
        if (x == 1) {
            tempAge.setStatus("Delayed");
        } else {
            tempAge.setStatus("Pending");
        }
        taskModel.add(tempAge);

        //
        TaskModelUploader tempHighest = new TaskModelUploader();
        tempHighest.setTask("Household Population 5 years old & over by Highest Grade/Year Completed, Age Group and Sex");
        tempHighest.setDuedate(formatter.parse(year + "/" + "07" + "/" + "30"));
        tempHighest.setFormID(400000000+ Integer.parseInt(year));
        tempHighest.setReportType("Demographics");

        x = now.compareTo(tempHighest.getDuedate());
        if (x == 1) {
            tempHighest.setStatus("Delayed");
        } else {
            tempHighest.setStatus("Pending");
        }
        taskModel.add(tempHighest);

        //
        TaskModelUploader tempMarital = new TaskModelUploader();
        tempMarital.setTask("Household Population 10 years old & over by Age Group, Sex and Marital Status");
        tempMarital.setDuedate(formatter.parse(year + "/" + "07" + "/" + "30"));
        tempMarital.setFormID(300000000+ Integer.parseInt(year));
        tempMarital.setReportType("Demographics");

        x = now.compareTo(tempMarital.getDuedate());
        if (x == 1) {
            tempMarital.setStatus("Delayed");
        } else {
            tempMarital.setStatus("Pending");
        }
        taskModel.add(tempMarital);

        //
        TaskModelUploader tempNutritional = new TaskModelUploader();
        tempNutritional.setTask("Percentage Distribution of Elementary School Children in Each District in the Division of Caloocan by Nutritional Status/By Gender");
        tempNutritional.setDuedate(formatter.parse(year + "/" + "03" + "/" + "30"));
        tempNutritional.setFormID(800000000+ Integer.parseInt(year));
        tempNutritional.setReportType("Health");

        x = now.compareTo(tempNutritional.getDuedate());
        if (x == 1) {
            tempNutritional.setStatus("Delayed");
        } else {
            tempNutritional.setStatus("Pending");
        }
        taskModel.add(tempNutritional);

        //
        TaskModelUploader tempHospitals = new TaskModelUploader();
        tempHospitals.setTask("List of Hospitals");
        tempHospitals.setDuedate(formatter.parse(year + "/" + "03" + "/" + "30"));
        tempHospitals.setFormID(90000000+ Integer.parseInt(year));
        tempHospitals.setReportType("Health");

        x = now.compareTo(tempHospitals.getDuedate());

        if (x == 1) {
            tempHospitals.setStatus("Delayed");
        } else {
            tempHospitals.setStatus("Pending");
        }
        taskModel.add(tempHospitals);

        this.taskModel = taskModel;
    }

    public ArrayList<String> getAllTasks(){
        ArrayList<String> taskNames = new ArrayList<>();
        taskNames.add("Enrollment in Public School");
        taskNames.add("Enrollment in Private School");
        taskNames.add("Number of Teachers and Classrooms for Public Schools");
        taskNames.add("Number of Teachers and Classrooms for Private Schools");
        taskNames.add("Household Population by Age Group and Sex");
        taskNames.add("Household Population 5 years old & over by Highest Grade/Year Completed, Age Group and Sex");
        taskNames.add("Household Population 10 years old & over by Age Group, Sex and Marital Status");
        taskNames.add("Percentage Distribution of Elementary School Children in Each District in the Division of Caloocan by Nutritional Status/By Gender");
        taskNames.add("List of Hospitals");
        return taskNames;
    }
    
    /**
     * @return the task
     */
    public String getTask() {
        return task;
    }

    /**
     * @param task the task to set
     */
    public void setTask(String task) {
        this.task = task;
    }

    /**
     * @return the duedate
     */
    public Date getDuedate() {
        return duedate;
    }

    /**
     * @param duedate the duedate to set
     */
    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    /**
     * @return the taskModel
     */
    public ArrayList<TaskModelUploader> getTaskModel() {
        return taskModel;
    }

    /**
     * @param taskModel the taskModel to set
     */
    public void setTaskModel(ArrayList<TaskModelUploader> taskModel) {
        this.taskModel = taskModel;
    }

    /**
     * @return the formID
     */
    public int getFormID() {
        return formID;
    }

    /**
     * @param formID the formID to set
     */
    public void setFormID(int formID) {
        this.formID = formID;
    }

    /**
     * @return the sDueDate
     */
    public String getsDueDate() {
        return sDueDate;
    }

    /**
     * @param sDueDate the sDueDate to set
     */
    public void setsDueDate(String sDueDate) {
        this.sDueDate = sDueDate;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the reportType
     */
    public String getReportType() {
        return reportType;
    }

    /**
     * @param reportType the reportType to set
     */
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    /**
     * @return the timeStamp
     */
    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

     /**
     * @return the timeStamp
     */
    public String getTimeStampString() {
        return Date;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStampString(String timeStamp) {
        this.Date = timeStamp;
    }


    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
