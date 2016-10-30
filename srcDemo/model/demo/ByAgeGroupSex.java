/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package model.demo;
import java.text.DecimalFormat;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class ByAgeGroupSex {

    private int formID;
    private int year;
    private String AgeGroup;
    private String barangay;
    private int femaleCount;
    private int maleCount;
    private int bothSex;
    private int errorline;
    private String uploadedBy;
    private int validationNum;
    private String reasonError;

    DecimalFormat df = new DecimalFormat("#,###");

    public int getBothSex() {
        return bothSex;
    }

    public void setBothSex(int bothSex) {
        this.bothSex = bothSex;
    }

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
    public int getFemaleCount() {
        return femaleCount;
    }

    /**
     * @param femaleCount the femaleCount to set
     */
    public void setFemaleCount(int femaleCount) {
        this.femaleCount = femaleCount;
    }

    /**
     * @return the maleCount
     */
    public int getMaleCount() {
        return maleCount;
    }

    /**
     * @param maleCount the maleCount to set
     */
    public void setMaleCount(int maleCount) {
        this.maleCount = maleCount;
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
     * @return the approved
     */
    public int isvalidated() {
        return isValidation();
    }

    /**
     * @param validation is true or false
     */
    public void setValidation(int validation) {
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
     * @return the errorline
     */
    public int getErrorline() {
        return errorline;
    }

    /**
     * @param errorline the errorline to set
     */
    public void setErrorline(int errorline) {
        this.errorline = errorline;
    }

    /**
     * @return the validation
     */
    public int isValidation() {
        return validationNum;
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

    
    public String getFormatcount(int count) {
        String scount = df.format(count);
        return scount;
    }

    /**
     * @return the reasonError
     */
    public String getReasonError() {
        return reasonError;
    }

    /**
     * @param reasonError the reasonError to set
     */
    public void setReasonError(String reasonError) {
        this.reasonError = reasonError;
    }

}
