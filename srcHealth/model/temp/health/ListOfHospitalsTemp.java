/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.temp.health;

/**
 *
 * @author Shermaine
 */
public class ListOfHospitalsTemp {
    private String HospitalName;
    private String classification;
    private String addresss;
    private String telephone;
    private String faxNumber;
    private String NumberOfBeds;
    private String category;
    private boolean validation;

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
     * @return the faxNumber
     */
    public String getFaxNumber() {
        return faxNumber;
    }

    /**
     * @param faxNumber the faxNumber to set
     */
    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    /**
     * @return the NumberOfBeds
     */
    public String getNumberOfBeds() {
        return NumberOfBeds;
    }

    /**
     * @param NumberOfBeds the NumberOfBeds to set
     */
    public void setNumberOfBeds(String NumberOfBeds) {
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
}
