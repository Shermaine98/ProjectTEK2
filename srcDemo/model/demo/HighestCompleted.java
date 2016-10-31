/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package model.demo;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */

public class HighestCompleted {

    private int formID;
    private int year;
    private String location;
    private String sex;
    private String ageGroup;
    private String uploadedBy;
    private int total;
    private int validation;
    private String reason;

    private ArrayList<HighestCompletedAgeGroup> HighestCompletedAgeGroup;
    private String approvedBy;

    DecimalFormat df = new DecimalFormat("#,###");

    public String getFormatCount(int count) {
        String scount = df.format(count);
        return scount;
    }
    
    public HighestCompleted() {
        HighestCompletedAgeGroup = new ArrayList<HighestCompletedAgeGroup>();
        this.HighestCompletedAgeGroup = HighestCompletedAgeGroup;
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
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the highestAttainment
     */
    public String getageGroup() {
        return ageGroup;
    }

    /**
     * @param highestAttainment the highestAttainment to set
     */
    public void setAgeGroup(String highestAttainment) {
        this.ageGroup = highestAttainment;
    }

    /**
     * @return the HighestCompletedAgeGroup
     */
    public ArrayList<HighestCompletedAgeGroup> getHighestCompletedAgeGroup() {
        return HighestCompletedAgeGroup;
    }

    /**
     * @param HighestCompletedAgeGroup the HighestCompletedAgeGroup to set
     */
    public void setHighestCompletedAgeGroup(ArrayList<HighestCompletedAgeGroup> HighestCompletedAgeGroup) {
        this.HighestCompletedAgeGroup = HighestCompletedAgeGroup;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the approvedBy
     */
    public String getApprovedBy() {
        return approvedBy;
    }

    /**
     * @param approvedBy the approvedBy to set
     */
    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    /**
     * @return the validation
     */
    public int getValidation() {
        return validation;
    }

    /**
     * @param validation the validation to set
     */
    public void setValidation(int validation) {
        this.validation = validation;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

}
