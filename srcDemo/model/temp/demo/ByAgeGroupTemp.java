/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package model.temp.demo;

import java.text.DecimalFormat;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */

public class ByAgeGroupTemp {

    private int formID;
    private int year;
    private String AgeGroup;
    private String barangay;
    private String femaleCount;
    private String maleCount;
    private String bothSex;
    private int validationNum;
    private String uploadedBy;
    private String errorReason;

    DecimalFormat df = new DecimalFormat("#,###");

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
     * @return the barangay
     */
    public String getBarangay() {
        return barangay;
    }

    /**
     * @param barangay the barangay to set
     */
    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    /**
     * @return the femaleCount
     */
    public String getFemaleCount() {
        return femaleCount;
    }

    /**
     * @param femaleCount the femaleCount to set
     */
    public void setFemaleCount(String femaleCount) {
        this.femaleCount = femaleCount;
    }

    /**
     * @return the maleCount
     */
    public String getMaleCount() {
        return maleCount;
    }

    /**
     * @param maleCount the maleCount to set
     */
    public void setMaleCount(String maleCount) {
        this.maleCount = maleCount;
    }

    /**
     * @return the bothSex
     */
    public String getBothSex() {
        return bothSex;
    }

    /**
     * @param bothSex the bothSex to set
     */
    public void setBothSex(String bothSex) {
        this.bothSex = bothSex;
    }

    /**
     * @return the approved
     */
    public int getvalidation() {
        return validationNum;
    }

    /**
     * @param validation is true or false
     */
    public void setvalidation(int validation) {
        this.validationNum = validation;
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
     * @param num the format value
     * @return the cBothSex
     */
    public String getFormat(String num) {
        String x = null;
        return x = df.format(num);

    }

    /**
     * @return the errorReason
     */
    public String getErrorReason() {
        return errorReason;
    }

    /**
     * @param errorReason the errorReason to set
     */
    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }
}
