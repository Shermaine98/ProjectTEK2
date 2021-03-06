/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package model.health;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class NutritionalStatusBMI {
    private String BMI;
    private int maleCount;
    private int femaleCount;
    private int totalCount;
     private int validation;
    private String reason;

    /**
     * @return the BMI
     */
    public String getBMI() {
        return BMI;
    }

    /**
     * @param BMI the BMI to set
     */
    public void setBMI(String BMI) {
        this.BMI = BMI;
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
