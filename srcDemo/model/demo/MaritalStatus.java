/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package model.demo;

import java.text.DecimalFormat;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */

public class MaritalStatus {
    private int formID;
    private int year;
    private String location;
    private String sex;
    private String AgeGroup;
    private int total;
    private int single;
    private int married;
    private int widowed;
    private int divorcedSeparated;
    private int commonLawLiveIn;
    private int unknown;
    private boolean validation;
    private String uploadedBy;
    private String approvedBy;

    DecimalFormat df = new DecimalFormat("#,###");
    
    public String getFormatcount(int count) {
        String scount = df.format(count);
        return scount;
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
     * @return the AgeGroup
     */
    public String getAgeGroup() {
        return AgeGroup;
    }

    /**
     * @param AgeGroup the AgeGroup to set
     */
    public void setAgeGroup(String AgeGroup) {
        this.AgeGroup = AgeGroup;
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
     * @return the single
     */
    public int getSingle() {
        return single;
    }

    /**
     * @param single the single to set
     */
    public void setSingle(int single) {
        this.single = single;
    }

    /**
     * @return the married
     */
    public int getMarried() {
        return married;
    }

    /**
     * @param married the married to set
     */
    public void setMarried(int married) {
        this.married = married;
    }

    /**
     * @return the widowed
     */
    public int getWidowed() {
        return widowed;
    }

    /**
     * @param widowed the widowed to set
     */
    public void setWidowed(int widowed) {
        this.widowed = widowed;
    }

    /**
     * @return the divorcedSeparated
     */
    public int getDivorcedSeparated() {
        return divorcedSeparated;
    }

    /**
     * @param divorcedSeparated the divorcedSeparated to set
     */
    public void setDivorcedSeparated(int divorcedSeparated) {
        this.divorcedSeparated = divorcedSeparated;
    }

    /**
     * @return the commonLawLiveIn
     */
    public int getCommonLawLiveIn() {
        return commonLawLiveIn;
    }

    /**
     * @param commonLawLiveIn the commonLawLiveIn to set
     */
    public void setCommonLawLiveIn(int commonLawLiveIn) {
        this.commonLawLiveIn = commonLawLiveIn;
    }

    /**
     * @return the unknown
     */
    public int getUnknown() {
        return unknown;
    }

    /**
     * @param unknown the unknown to set
     */
    public void setUnknown(int unknown) {
        this.unknown = unknown;
    }

    /**
     * @return the approved
     */
    public boolean getValidation() {
        return validation;
    }

    /**
     * @param validation true or false
     */
    public void setValidation(boolean validation) {
        this.validation = validation;
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
     * @return the uploadedBy
     */
    public String getUploadedBy() {
        return uploadedBy;
    }

    /**
     * @param uploadedBy the uploadedBy to set
     */
    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
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

    

}
