/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */


package model.temp.education;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class EnrollmentDetTemp {
    private String gradeLevel;
    private String maleCount;
    private String femaleCount;
    private String totalCount;
   
    private String reason;
 private int validation;
    public int getValidation() {
        return validation;
    }

    public void setValidation(int validation) {
        this.validation = validation;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getMaleCount() {
        return maleCount;
    }

    public void setMaleCount(String maleCount) {
        this.maleCount = maleCount;
    }

    public String getFemaleCount() {
        return femaleCount;
    }

    public void setFemaleCount(String femaleCount) {
        this.femaleCount = femaleCount;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
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
