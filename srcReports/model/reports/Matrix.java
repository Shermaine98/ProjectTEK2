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

public class Matrix {

   
    private int year;
    private String sector;
    private String chartName;
    private String observations;
    private String Implications;
    private String explanations;
    private String internventions;
    private boolean isDraft;
    private String createdByName;
    private int createdBy;
    private String chartPath;

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
     * @return the chartNumber
     */
    public String getChartName() {
        return chartName;
    }

    /**
     */
    public void setchartName(String chartName) {
        this.setChartName(chartName);
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
     * @param chartName the chartName to set
     */
    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    /**
     * @return the observations
     */
    public String getObservations() {
        return observations;
    }

    /**
     * @param observations the observations to set
     */
    public void setObservations(String observations) {
        this.observations = observations;
    }

    /**
     * @return the Implications
     */
    public String getImplications() {
        return Implications;
    }

    /**
     * @param Implications the Implications to set
     */
    public void setImplications(String Implications) {
        this.Implications = Implications;
    }

    /**
     * @return the explanations
     */
    public String getExplanations() {
        return explanations;
    }

    /**
     * @param explanations the explanations to set
     */
    public void setExplanations(String explanations) {
        this.explanations = explanations;
    }

    /**
     * @return the internventions
     */
    public String getInternventions() {
        return internventions;
    }

    /**
     * @param internventions the internventions to set
     */
    public void setInternventions(String internventions) {
        this.internventions = internventions;
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
