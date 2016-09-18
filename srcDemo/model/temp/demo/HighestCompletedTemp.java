/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.temp.demo;

import java.util.ArrayList;

/**
 *
 * @author shermainesy
 */
public class HighestCompletedTemp {
    
    private int formID;
    private int year;
    private String location;
    private String sex;
    private String ageGroup;
    private String total;
    
    private ArrayList<HighestCompletedAgeGroupTemp> HighestCompletedAgeGroupTemp;

    public HighestCompletedTemp() {
        HighestCompletedAgeGroupTemp = new  ArrayList<HighestCompletedAgeGroupTemp>();
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
     * @param HighestCompletedAgeGroupTemp the HighestCompletedAgeGroupTemp to set
     */
    public void setHighestCompletedAgeGroupTemp(ArrayList<HighestCompletedAgeGroupTemp> HighestCompletedAgeGroupTemp) {
        this.HighestCompletedAgeGroupTemp = HighestCompletedAgeGroupTemp;
    }

   
}
