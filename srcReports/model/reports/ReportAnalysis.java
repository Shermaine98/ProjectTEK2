/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package model.reports;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */

public class ReportAnalysis {
    private int year;
    private String sector;
    private String chartName;
    private String text;
    private String chartPath;
    private int createdBy;
    private boolean isDraft;
    private String createdByName;
    
    /**
     * @return the chartName
     */
    public String getChartName() {
        return chartName;
    }

    /**
     * @param chartName the chartName to set
     */
    public void setChartName(String chartName) {
        this.chartName = chartName;
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
     * @return the chartPath
     */
    public String getChartPath() {
        return chartPath;
    }

    /**
     * @param chartPath the chartPath to set
     */
    public void setChartPath(String chartPath) {
        this.chartPath = chartPath;
    }

    /**
     * @return the sector
     */
    public String getSector() {
        return sector;
    }

    /**
     * @param sector the sector to set
     */
    public void setSector(String sector) {
        this.sector = sector;
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
