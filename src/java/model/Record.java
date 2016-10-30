/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package model;

/**
 *
 * @author Gian Carlo Roxas
 * @author shermaine Sy
 * @author Geraldine Atayan
 *
 */
public class Record {
    
    private int formID;
    private int censusYear;
    private boolean validation;
    private int approved;
    private int approvedBy;
     private String reasons;
    private String approvedByName;
    private int uploadedBy;
    private String uploadedByByName;

    /**
     * @return the formName
     */
    public int getFormID() {
        return formID;
    }

    /**
     * @param formID
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
     * @return the approved
     */
    public int isApproved() {
        return approved;
    }

    /**
     * @param approved the approved to set
     */
    public void setApproved(int approved) {
        this.approved = approved;
    }

    /**
     * @return the approvedBy
     */
    public int getApprovedBy() {
        return approvedBy;
    }

    /**
     * @param approvedBy the approvedBy to set
     */
    public void setApprovedBy(int approvedBy) {
        this.approvedBy = approvedBy;
    }

    /**
     * @return the approvedByName
     */
    public String getApprovedByName() {
        return approvedByName;
    }

    /**
     * @param approvedByName the approvedByName to set
     */
    public void setApprovedByName(String approvedByName) {
        this.approvedByName = approvedByName;
    }

    /**
     * @return the uploadedBy
     */
    public int getUploadedBy() {
        return uploadedBy;
    }

    /**
     * @param uploadedBy the uploadedBy to set
     */
    public void setUploadedBy(int uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    /**
     * @return the uploadedByByName
     */
    public String getUploadedByByName() {
        return uploadedByByName;
    }

    /**
     * @param uploadedByByName the uploadedByByName to set
     */
    public void setUploadedByByName(String uploadedByByName) {
        this.uploadedByByName = uploadedByByName;
    }

    /**
     * @return the reasons
     */
    public String getReasons() {
        return reasons;
    }

    /**
     * @param reasons the reasons to set
     */
    public void setReasons(String reasons) {
        this.reasons = reasons;
    }
    
    
}
