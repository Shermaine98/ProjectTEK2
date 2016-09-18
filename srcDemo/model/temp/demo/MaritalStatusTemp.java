/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.temp.demo;

import java.text.DecimalFormat;

/**
 *
 * @author shermainesy
 */
public class MaritalStatusTemp {

    private int year;
    private int formID;
    private String location;
    private String sex;
    private String AgeGroup;
    private String total;
    private String single;
    private String married;
    private String widowed;
    private String divorcedSeparated;
    private String commonLawLiveIn;
    private String unknown;
    private boolean validation;
    private String uploadedBy;
    private String approvedBy;

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
    public String getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * @return the single
     */
    public String getSingle() {
        return single;
    }

    /**
     * @param single the single to set
     */
    public void setSingle(String single) {
        this.single = single;
    }

    /**
     * @return the married
     */
    public String getMarried() {
        return married;
    }

    /**
     * @param married the married to set
     */
    public void setMarried(String married) {
        this.married = married;
    }

    /**
     * @return the widowed
     */
    public String getWidowed() {
        return widowed;
    }

    /**
     * @param widowed the widowed to set
     */
    public void setWidowed(String widowed) {
        this.widowed = widowed;
    }

    /**
     * @return the divorcedSeparated
     */
    public String getDivorcedSeparated() {
        return divorcedSeparated;
    }

    /**
     * @param divorcedSeparated the divorcedSeparated to set
     */
    public void setDivorcedSeparated(String divorcedSeparated) {
        this.divorcedSeparated = divorcedSeparated;
    }

    /**
     * @return the commonLawLiveIn
     */
    public String getCommonLawLiveIn() {
        return commonLawLiveIn;
    }

    /**
     * @param commonLawLiveIn the commonLawLiveIn to set
     */
    public void setCommonLawLiveIn(String commonLawLiveIn) {
        this.commonLawLiveIn = commonLawLiveIn;
    }

    /**
     * @return the unknown
     */
    public String getUnknown() {
        return unknown;
    }

    /**
     * @param unknown the unknown to set
     */
    public void setUnknown(String unknown) {
        this.unknown = unknown;
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
     * @return the validation
     */
    public boolean isValidation() {
        return validation;
    }

    /**
     * @param validation the validation to set
     */
    public void setValidation(boolean validation) {
        this.validation = validation;
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
