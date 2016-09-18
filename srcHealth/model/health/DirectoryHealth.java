/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.health;

import java.text.DecimalFormat;

/**
 *
 * @author shermainesy
 */
public class DirectoryHealth {
    private int formID;
    private int Year;
    private String HospitalName;
    private String classification;
    private String addresss;
    private String telephone;
    private int NumberOfBeds;
    private int NumberOfDoctor;
    private int NumberOfNurses;
    private int NumberOfMidwives;
    private String category;
    private boolean validation;
    private boolean accreditation;
    private double latitude;
    private double longitude;
    private String uploadedBy;
    private String approvedBy;

    DecimalFormat df = new DecimalFormat("#,###");
    public String getFormatcount(int count) {
        String scount = df.format(count);
        return scount;
    }
    /**
     * @return the HospitalName
     */
    public String getHospitalName() {
        return HospitalName;
    }

    /**
     * @param HospitalName the HospitalName to set
     */
    public void setHospitalName(String HospitalName) {
        this.HospitalName = HospitalName;
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
     * @return the addresss
     */
    public String getAddresss() {
        return addresss;
    }

    /**
     * @param addresss the addresss to set
     */
    public void setAddresss(String addresss) {
        this.addresss = addresss;
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
     * @return the NumberOfBeds
     */
    public int getNumberOfBeds() {
        return NumberOfBeds;
    }

    /**
     * @param NumberOfBeds the NumberOfBeds to set
     */
    public void setNumberOfBeds(int NumberOfBeds) {
        this.NumberOfBeds = NumberOfBeds;
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
     * @return the Year
     */
    public int getYear() {
        return Year;
    }

    /**
     * @param Year the Year to set
     */
    public void setYear(int Year) {
        this.Year = Year;
    }

    /**
     * @return the NumberOfDoctor
     */
    public int getNumberOfDoctor() {
        return NumberOfDoctor;
    }

    /**
     * @param NumberOfDoctor the NumberOfDoctor to set
     */
    public void setNumberOfDoctor(int NumberOfDoctor) {
        this.NumberOfDoctor = NumberOfDoctor;
    }

    /**
     * @return the NumberOfNurses
     */
    public int getNumberOfNurses() {
        return NumberOfNurses;
    }

    /**
     * @param NumberOfNurses the NumberOfNurses to set
     */
    public void setNumberOfNurses(int NumberOfNurses) {
        this.NumberOfNurses = NumberOfNurses;
    }

    /**
     * @return the NumberOfMidwives
     */
    public int getNumberOfMidwives() {
        return NumberOfMidwives;
    }

    /**
     * @param NumberOfMidwives the NumberOfMidwives to set
     */
    public void setNumberOfMidwives(int NumberOfMidwives) {
        this.NumberOfMidwives = NumberOfMidwives;
    }

    /**
     * @return the accreditation
     */
    public boolean isAccreditation() {
        return accreditation;
    }

    /**
     * @param accreditation the accreditation to set
     */
    public void setAccreditation(boolean accreditation) {
        this.accreditation = accreditation;
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
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    
    
}
