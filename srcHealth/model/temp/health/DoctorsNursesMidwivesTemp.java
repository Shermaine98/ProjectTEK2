/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.temp.health;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */

public class DoctorsNursesMidwivesTemp {
    private int formID;
    private int censusYear;
    private String hospitalName;
    private String classification;
    private String address;
    private String telephone;
    private String totalDoctors;
    private String totalNurses;
    private String totalMidwives;
    private boolean validaiton;

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
     * @return the censusYear
     */
    public int getCensusYear() {
        return censusYear;
    }

    /**
     * @param censusYear the censusYear to set
     */
    public void setCensusYear(int censusYear) {
        this.censusYear = censusYear;
    }

    /**
     * @return the hospitalName
     */
    public String getHospitalName() {
        return hospitalName;
    }

    /**
     * @param hospitalName the hospitalName to set
     */
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    /**
     * @return the classification
     */
    public String getClassification() {
        return classification;
    }

    /**
     * @param classification the classification to set
     */
    public void setClassification(String classification) {
        this.classification = classification;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the totalDoctors
     */
    public String getTotalDoctors() {
        return totalDoctors;
    }

    /**
     * @param totalDoctors the totalDoctors to set
     */
    public void setTotalDoctors(String totalDoctors) {
        this.totalDoctors = totalDoctors;
    }

    /**
     * @return the totalNurses
     */
    public String getTotalNurses() {
        return totalNurses;
    }

    /**
     * @param totalNurses the totalNurses to set
     */
    public void setTotalNurses(String totalNurses) {
        this.totalNurses = totalNurses;
    }

    /**
     * @return the totalMidwives
     */
    public String getTotalMidwives() {
        return totalMidwives;
    }

    /**
     * @param totalMidwives the totalMidwives to set
     */
    public void setTotalMidwives(String totalMidwives) {
        this.totalMidwives = totalMidwives;
    }

    /**
     * @return the validaiton
     */
    public boolean isValidaiton() {
        return validaiton;
    }

    /**
     * @param validaiton the validaiton to set
     */
    public void setValidaiton(boolean validaiton) {
        this.validaiton = validaiton;
    }
    
}
