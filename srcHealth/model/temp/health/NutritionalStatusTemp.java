/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package model.temp.health;

import java.util.ArrayList;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */

public class NutritionalStatusTemp {
    private int formID;
    private int censusYear;
    private String district;
    private String gradeLevel;
    private String totalMale;
    private String totalFemale;
    private String totalCount;
    private String pupilsWeighedTotal;
    private String pupilsWeighedMale;
    private String pupilsWeighedFemale;
    private int validation;
    private String reason;
    
    private ArrayList<NutritionalStatusBMITemp> NutritionalStatusBMITemp;

    public NutritionalStatusTemp() {
        NutritionalStatusBMITemp = new ArrayList<NutritionalStatusBMITemp>();
        this.NutritionalStatusBMITemp = NutritionalStatusBMITemp;
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
     * @return the district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district the district to set
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * @return the gradeLevel
     */
    public String getGradeLevel() {
        return gradeLevel;
    }

    /**
     * @param gradeLevel the gradeLevel to set
     */
    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    /**
     * @return the validation
     */
    public int isValidation() {
        return validation;
    }

    /**
     * @param validation the validation to set
     */
    public void setValidation(int validation) {
        this.validation = validation;
    }

    /**
     * @return the totalMale
     */
    public String getTotalMale() {
        return totalMale;
    }

    /**
     * @param totalMale the totalMale to set
     */
    public void setTotalMale(String totalMale) {
        this.totalMale = totalMale;
    }

    /**
     * @return the totaleFemale
     */
    public String getTotalFemale() {
        return totalFemale;
    }

    /**
     * @param totalFemale total female
     */
    public void setTotalFemale(String totalFemale) {
        this.totalFemale = totalFemale;
    }

    /**
     * @return the totalCount
     */
    public String getTotalCount() {
        return totalCount;
    }

    /**
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return the pupilsWeighedTotal
     */
    public String getPupilsWeighedTotal() {
        return pupilsWeighedTotal;
    }

    /**
     * @param pupilsWeighedTotal the pupilsWeighedTotal to set
     */
    public void setPupilsWeighedTotal(String pupilsWeighedTotal) {
        this.pupilsWeighedTotal = pupilsWeighedTotal;
    }

    /**
     * @return the pupilsWeighedMale
     */
    public String getPupilsWeighedMale() {
        return pupilsWeighedMale;
    }

    /**
     * @param pupilsWeighedMale the pupilsWeighedMale to set
     */
    public void setPupilsWeighedMale(String pupilsWeighedMale) {
        this.pupilsWeighedMale = pupilsWeighedMale;
    }

    /**
     * @return the pupilsWeighedFemale
     */
    public String getPupilsWeighedFemale() {
        return pupilsWeighedFemale;
    }

    /**
     * @param pupilsWeighedFemale the pupilsWeighedFemale to set
     */
    public void setPupilsWeighedFemale(String pupilsWeighedFemale) {
        this.pupilsWeighedFemale = pupilsWeighedFemale;
    }

    /**
     * @return the NutritionalStatusTemp
     */
    public ArrayList<NutritionalStatusBMITemp> getNutritionalStatusBMITemp() {
        return NutritionalStatusBMITemp;
    }

    /**
     * @param NutritionalStatusBMITemp the temp nutritional
     */
    public void setNutritionalStatusTemp(ArrayList<NutritionalStatusBMITemp> NutritionalStatusBMITemp) {
        this.NutritionalStatusBMITemp = NutritionalStatusBMITemp;
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
