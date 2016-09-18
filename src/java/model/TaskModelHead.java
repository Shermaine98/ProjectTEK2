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
public class TaskModelHead {

    private String report;
    private Date duedate;
    private String sDueDate;
    private int formID;
    private String reportType;
    private String status;
    private String sector;
    private String name;
    private Timestamp timeStamp;
    private Date now;

    private ArrayList<TaskModelHead> taskModel;
    DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

    public void taskModel(String year) throws ParseException {
        now = new Date();
        //  Date test = new Date();
        //  test = formatter.parse(year  +"/"+  "02"  +"/"+  "30");
        int x = 0;
        taskModel = new ArrayList<TaskModelHead>();

        TaskModelHead IntegrationDemo = new TaskModelHead();
        //THIS IS FOR ENROLLMENT PUBLIC

        IntegrationDemo.setReport("Integrated Demographics Analysis");
        IntegrationDemo.setDuedate(formatter.parse(year + "/" + "10" + "/" + "30"));
        IntegrationDemo.setSector("Demographics");
        IntegrationDemo.setReportType("Integrated");
        x = now.compareTo(IntegrationDemo.getDuedate());

        if (x == 1) {
            IntegrationDemo.setStatus("Delayed");
        } else {
            IntegrationDemo.setStatus("Pending");
        }
        taskModel.add(IntegrationDemo);

        TaskModelHead IntegrationHeatlh = new TaskModelHead();
        //THIS IS FOR ENROLLMENT PUBLIC

        IntegrationHeatlh.setReport("Integrated Health Analysis");
        IntegrationHeatlh.setDuedate(formatter.parse(year + "/" + "10" + "/" + "30"));
        IntegrationHeatlh.setSector("Health");
        IntegrationHeatlh.setReportType("Integrated");
        x = now.compareTo(IntegrationHeatlh.getDuedate());

        if (x == 1) {
            IntegrationHeatlh.setStatus("Delayed");
        } else {
            IntegrationHeatlh.setStatus("Pending");
        }
        taskModel.add(IntegrationHeatlh);

        TaskModelHead Integration = new TaskModelHead();
        //THIS IS FOR ENROLLMENT PUBLIC

        Integration.setReport("Integrated Education Analysis");
        Integration.setDuedate(formatter.parse(year + "/" + "10" + "/" + "30"));
        Integration.setSector("Education");
        Integration.setReportType("Integrated");
        x = now.compareTo(Integration.getDuedate());

        if (x == 1) {
            Integration.setStatus("Delayed");
        } else {
            Integration.setStatus("Pending");
        }
        taskModel.add(Integration);

        TaskModelHead education = new TaskModelHead();
        //THIS IS FOR ENROLLMENT PUBLIC
        education.setReport("Education Analysis Matrix ");
        education.setDuedate(formatter.parse(year + "/" + "10" + "/" + "30"));
        education.setSector("Education");
        education.setReportType("Matrix");
        x = now.compareTo(education.getDuedate());

        if (x == 1) {
            education.setStatus("Delayed");
        } else {
            education.setStatus("Pending");
        }
        taskModel.add(education);

        TaskModelHead Health = new TaskModelHead();
        //THIS IS FOR ENROLLMENT PUBLIC
        Health.setReport("Health Analysis Matrix");
        Health.setDuedate(formatter.parse(year + "/" + "10" + "/" + "30"));
        Health.setSector("Health");
        Health.setReportType("Matrix");
        x = now.compareTo(education.getDuedate());

        if (x == 1) {
            Health.setStatus("Delayed");
        } else {
            Health.setStatus("Pending");
        }

        taskModel.add(Health);

        TaskModelHead demo = new TaskModelHead();
        //THIS IS FOR ENROLLMENT PUBLIC
        demo.setReport("Demographics Analysis Matrix");
        demo.setDuedate(formatter.parse(year + "/" + "10" + "/" + "30"));
        demo.setSector("Demographics");
        demo.setReportType("Matrix");
        x = now.compareTo(demo.getDuedate());

        if (x == 1) {
            demo.setStatus("Delayed");
        } else {
            demo.setStatus("Pending");
        }

        taskModel.add(demo);

        TaskModelHead educationAnalysis = new TaskModelHead();
        //THIS IS FOR ENROLLMENT PUBLIC
        educationAnalysis.setReport("Education Analysis");
        educationAnalysis.setDuedate(formatter.parse(year + "/" + "10" + "/" + "30"));
        educationAnalysis.setReportType("Analysis");
        educationAnalysis.setSector("Education");
        x = now.compareTo(educationAnalysis.getDuedate());

        if (x == 1) {
            educationAnalysis.setStatus("Delayed");
        } else {
            educationAnalysis.setStatus("Pending");
        }

        taskModel.add(educationAnalysis);

        TaskModelHead HealthAnalysis = new TaskModelHead();
        //THIS IS FOR ENROLLMENT PUBLIC
        HealthAnalysis.setReport("Health Analysis");
        HealthAnalysis.setDuedate(formatter.parse(year + "/" + "10" + "/" + "30"));
        HealthAnalysis.setReportType("Analysis");
        HealthAnalysis.setSector("Health");

        x = now.compareTo(education.getDuedate());

        if (x == 1) {
            HealthAnalysis.setStatus("Delayed");
        } else {
            HealthAnalysis.setStatus("Pending");
        }

        taskModel.add(HealthAnalysis);

        TaskModelHead DemoAnalysis = new TaskModelHead();
        //THIS IS FOR ENROLLMENT PUBLIC
        DemoAnalysis.setReport("Demographics Analysis");
        DemoAnalysis.setDuedate(formatter.parse(year + "/" + "10" + "/" + "30"));
        DemoAnalysis.setReportType("Analysis");
        DemoAnalysis.setSector("Demographics");
        x = now.compareTo(DemoAnalysis.getDuedate());

        if (x == 1) {
            DemoAnalysis.setStatus("Delayed");
        } else {
            DemoAnalysis.setStatus("Pending");
        }

        taskModel.add(DemoAnalysis);

        this.taskModel = taskModel;
    }

    /**
     * @return the task
     */
    public String getReport() {
        return report;
    }

    /**
     * @param task the task to set
     */
    public void setReport(String report) {
        this.report = report;
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
    public ArrayList<TaskModelHead> getTaskModel() {
        return taskModel;
    }

    /**
     * @param taskModel the taskModel to set
     */
    public void setTaskModel(ArrayList<TaskModelHead> taskModel) {
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

    /**
     * @return the sector
     */
    public String getSector() {
        return sector;
    }

    /**
     * @param sector the sector to set
     */
    public void setSector(String sector) {
        this.sector = sector;
    }
}
