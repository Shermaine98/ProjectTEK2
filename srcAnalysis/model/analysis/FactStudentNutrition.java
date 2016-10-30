/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */


package model.analysis;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class FactStudentNutrition {
    private int censusYear;
    private String district;
    private String gradeLevel;
    private String gender;
    private int pupilsWeighed;
    private int totalNoOfSeverelyWasted;
    private int totalNoOfWasted;
    private int totalNoOfNormal;
    private int totalNoOfOverweight;
    private int totalNoOfObese;
    private String zone; 
    private boolean isOutlier;

    public boolean isIsOutlier() {
        return isOutlier;
    }

    public void setIsOutlier(boolean isOutlier) {
        this.isOutlier = isOutlier;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public int getCensusYear() {
        return censusYear;
    }

    public void setCensusYear(int censusYear) {
        this.censusYear = censusYear;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPupilsWeighed() {
        return pupilsWeighed;
    }

    public void setPupilsWeighed(int pupilsWeighed) {
        this.pupilsWeighed = pupilsWeighed;
    }

    public int getTotalNoOfSeverelyWasted() {
        return totalNoOfSeverelyWasted;
    }

    public void setTotalNoOfSeverelyWasted(int totalNoOfSeverelyWasted) {
        this.totalNoOfSeverelyWasted = totalNoOfSeverelyWasted;
    }

    public int getTotalNoOfWasted() {
        return totalNoOfWasted;
    }

    public void setTotalNoOfWasted(int totalNoOfWasted) {
        this.totalNoOfWasted = totalNoOfWasted;
    }

    public int getTotalNoOfNormal() {
        return totalNoOfNormal;
    }

    public void setTotalNoOfNormal(int totalNoOfNormal) {
        this.totalNoOfNormal = totalNoOfNormal;
    }

    public int getTotalNoOfOverweight() {
        return totalNoOfOverweight;
    }

    public void setTotalNoOfOverweight(int totalNoOfOverweight) {
        this.totalNoOfOverweight = totalNoOfOverweight;
    }

    public int getTotalNoOfObese() {
        return totalNoOfObese;
    }

    public void setTotalNoOfObese(int totalNoOfObese) {
        this.totalNoOfObese = totalNoOfObese;
    }
    
    
}
