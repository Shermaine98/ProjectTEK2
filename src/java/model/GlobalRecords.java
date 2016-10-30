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
public class GlobalRecords {
    private int formID;
    private int censusYear;
    private int uploadedBy;
    private String uploadedByByName;
    private int errorLines;

    /**
     * @return the formName
     */
    public int getFormID() {
        return formID;
    }

    /**
     * @param formID the form ID
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
     * @return the errorLines
     */
    public int getErrorLines() {
        return errorLines;
    }

    /**
     * @param errorLines the errorLines to set
     */
    public void setErrorLines(int errorLines) {
        this.errorLines = errorLines;
    }
    
}
