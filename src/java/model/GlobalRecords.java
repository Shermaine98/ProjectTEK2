/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Shermaine
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
     * @param formName the formName to set
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
