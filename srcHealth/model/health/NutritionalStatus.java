/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package model.health;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class NutritionalStatus {

    private int formID;
    private int censusYear;
    private String district;
    private String gradeLevel;
    private int totalMale;
    private int totalFemale;
    private int totalCount;
    private int pupilsWeighedTotal;
    private int pupilsWeighedMale;
    private int pupilsWeighedFemale;
    private boolean validation;

    private ArrayList<NutritionalStatusBMI> NutritionalStatusBMI;
    private String uploadedBy;
    private String approvedBy;

    DecimalFormat df = new DecimalFormat("#,###");
    
    public String getFormatcount(int count) {
        String scount = df.format(count);
        return scount;
    }

    public NutritionalStatus() {
        NutritionalStatusBMI = new ArrayList<NutritionalStatusBMI>();
        this.NutritionalStatusBMI = NutritionalStatusBMI;
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
     * @return the totalMale
     */
    public int getTotalMale() {
        return totalMale;
    }

    /**
     * @param totalMale the totalMale to set
     */
    public void setTotalMale(int totalMale) {
        this.totalMale = totalMale;
    }

    /**
     * @return the totaleFemale
     */
    public int getTotalFemale() {
        return totalFemale;
    }

    /**
     * @param totaleFemale the totaleFemale to set
     */
    public void setTotalFemale(int totalFemale) {
        this.totalFemale = totalFemale;
    }

    /**
     * @return the totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return the pupilsWeighedTotal
     */
    public int getPupilsWeighedTotal() {
        return pupilsWeighedTotal;
    }

    /**
     * @param pupilsWeighedTotal the pupilsWeighedTotal to set
     */
    public void setPupilsWeighedTotal(int pupilsWeighedTotal) {
        this.pupilsWeighedTotal = pupilsWeighedTotal;
    }

    /**
     * @return the pupilsWeighedMale
     */
    public int getPupilsWeighedMale() {
        return pupilsWeighedMale;
    }

    /**
     * @param pupilsWeighedMale the pupilsWeighedMale to set
     */
    public void setPupilsWeighedMale(int pupilsWeighedMale) {
        this.pupilsWeighedMale = pupilsWeighedMale;
    }

    /**
     * @return the pupilsWeighedFemale
     */
    public int getPupilsWeighedFemale() {
        return pupilsWeighedFemale;
    }

    /**
     * @param pupilsWeighedFemale the pupilsWeighedFemale to set
     */
    public void setPupilsWeighedFemale(int pupilsWeighedFemale) {
        this.pupilsWeighedFemale = pupilsWeighedFemale;
    }

    /**
     * @return the NutritionalStatusBMI
     */
    public ArrayList<NutritionalStatusBMI> getNutritionalStatusBMI() {
        return NutritionalStatusBMI;
    }

    /**
     * @param NutritionalStatusBMI the NutritionalStatusBMI to set
     */
    public void setNutritionalStatusBMI(ArrayList<NutritionalStatusBMI> NutritionalStatusBMI) {
        this.NutritionalStatusBMI = NutritionalStatusBMI;
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
