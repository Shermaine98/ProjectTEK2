/*
 *  Copyright (C) ProjectTEK - DLSU CCS 2016
 *  All right Reserved   * 
 */

package model.reports;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */


public class Integrated {
    
    private String Sector;
    private String text;
    private boolean isDraft;
    private int createdBy;
    private int year;
    private String createdByName;

    /**
     * @return the Sector
     */
    public String getSector() {
        return Sector;
    }

    /**
     * @param Sector the Sector to set
     */
    public void setSector(String Sector) {
        this.Sector = Sector;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the isDraft
     */
    public boolean isIsDraft() {
        return isDraft;
    }

    /**
     * @param isDraft the isDraft to set
     */
    public void setIsDraft(boolean isDraft) {
        this.isDraft = isDraft;
    }

    /**
     * @return the createdBy
     */
    public int getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
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
     * @return the createdByName
     */
    public String getCreatedByName() {
        return createdByName;
    }

    /**
     * @param createdByName the createdByName to set
     */
    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }
    
}
