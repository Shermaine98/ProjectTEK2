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
public class byAgeGroupTemp {

    private int formID;
    private int year;
    private String AgeGroup;
    private String barangay;
    private String femaleCount;
    private String maleCount;
    private String bothSex;
    private boolean validation;
    private String uploadedBy;

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
    public boolean getvalidation() {
        return validation;
    }

    /**
     * @param approved the approved to set
     */
    public void setvalidation(boolean validation) {
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
     * @return the cBothSex
     */
    public String getFormat(String num) {
        String x = null;
        return x = df.format(num);

    }
}
