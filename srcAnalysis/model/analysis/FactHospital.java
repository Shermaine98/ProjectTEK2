/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.analysis;

/**
 *
 * @author Shermaine
 */
public class FactHospital {
    private int CensusYear;
    private String hospitalName;
    private String classification;
    private String district;
    private String zone;
    private int totalNoOfDoctors;
    private int totalNoOfNurses;
    private int totalNoOfMidwives;
    private int totalNoOfBeds;
    private String category;
    private boolean isAccredited;
    private double latitude;
    private double longtitude;
    private boolean isOutlier;

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
    
    /**
     * @return the CensusYear
     */
    public int getCensusYear() {
        return CensusYear;
    }

    /**
     * @param CensusYear the CensusYear to set
     */
    public void setCensusYear(int CensusYear) {
        this.CensusYear = CensusYear;
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
     * @return the totalNoOfDoctors
     */
    public int getTotalNoOfDoctors() {
        return totalNoOfDoctors;
    }

    /**
     * @param totalNoOfDoctors the totalNoOfDoctors to set
     */
    public void setTotalNoOfDoctors(int totalNoOfDoctors) {
        this.totalNoOfDoctors = totalNoOfDoctors;
    }

    /**
     * @return the totalNoOfNurses
     */
    public int getTotalNoOfNurses() {
        return totalNoOfNurses;
    }

    /**
     * @param totalNoOfNurses the totalNoOfNurses to set
     */
    public void setTotalNoOfNurses(int totalNoOfNurses) {
        this.totalNoOfNurses = totalNoOfNurses;
    }

    /**
     * @return the totalNoOfMidwives
     */
    public int getTotalNoOfMidwives() {
        return totalNoOfMidwives;
    }

    /**
     * @param totalNoOfMidwives the totalNoOfMidwives to set
     */
    public void setTotalNoOfMidwives(int totalNoOfMidwives) {
        this.totalNoOfMidwives = totalNoOfMidwives;
    }

    /**
     * @return the totalNoOfBeds
     */
    public int getTotalNoOfBeds() {
        return totalNoOfBeds;
    }

    /**
     * @param totalNoOfBeds the totalNoOfBeds to set
     */
    public void setTotalNoOfBeds(int totalNoOfBeds) {
        this.totalNoOfBeds = totalNoOfBeds;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the isAccredited
     */
    public boolean isIsAccredited() {
        return isAccredited;
    }

    /**
     * @param isAccredited the isAccredited to set
     */
    public void setIsAccredited(boolean isAccredited) {
        this.isAccredited = isAccredited;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longtitude
     */
    public double getLongtitude() {
        return longtitude;
    }

    /**
     * @param longtitude the longtitude to set
     */
    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
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
}
