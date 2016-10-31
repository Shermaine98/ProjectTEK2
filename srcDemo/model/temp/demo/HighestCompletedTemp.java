/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package model.temp.demo;

import java.util.ArrayList;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 *
 */
public class HighestCompletedTemp {

    private int formID;
    private int year;
    private String location;
    private String sex;
    private String ageGroup;
    private String total;
    private int validation;
    private String reason;

    private ArrayList<HighestCompletedAgeGroupTemp> HighestCompletedAgeGroupTemp;

    public HighestCompletedTemp() {
        HighestCompletedAgeGroupTemp = new ArrayList<HighestCompletedAgeGroupTemp>();
        this.HighestCompletedAgeGroupTemp = HighestCompletedAgeGroupTemp;
    }

    public int getFormID() {
        return formID;
    }

    public void setFormID(int formID) {
        this.formID = formID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
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
     * @return the HighestCompletedAgeGroupTemp
     */
    public ArrayList<HighestCompletedAgeGroupTemp> getHighestCompletedAgeGroupTemp() {
        return HighestCompletedAgeGroupTemp;
    }

    /**
     * @param HighestCompletedAgeGroupTemp the HighestCompletedAgeGroupTemp to
     * set
     */
    public void setHighestCompletedAgeGroupTemp(ArrayList<HighestCompletedAgeGroupTemp> HighestCompletedAgeGroupTemp) {
        this.HighestCompletedAgeGroupTemp = HighestCompletedAgeGroupTemp;
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
