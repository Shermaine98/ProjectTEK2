/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Gian Carlo Roxas
 * @author shermaine Sy
 * @author Geraldine Atayan
 *
 */
public class TaskModel {

    private String reportName;
    private Date duedate;
    private int formID;
    private String reportType;
    private String status;
    private String sector;
    private String name;
    private String timeStamp;
    private int dateDiff;

    SimpleDateFormat sdf = new SimpleDateFormat("MMM. dd, yyyy");
    public void taskModel() {
    }

    public int getDateDiff() {
        return dateDiff;
    }

    public void setDateDiff(int dateDiff) {
        this.dateDiff = dateDiff;
    }

    /**
     * @return the task
     */
    public String getReportName() {
        return reportName;
    }

    /**
     * @param reportName the report name
     */
    public void setreportName(String reportName) {
        this.reportName = reportName;
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
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(String timeStamp) {
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

    /**
     * @return the sduedate
     */
    public String getSduedate() {
        return sdf.format(duedate);
    }
   
}
