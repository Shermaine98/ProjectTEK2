/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */


package model.analysis;

import java.util.ArrayList;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class FactEnrollment {
    private int censusYear;
    private String schoolName;
    private String district;
    private String type;
    private String level;
    private String classification;
    private String zone;
    private int maleCount;
    private int femaleCount;
    private int count;
    private double genderDisparityIndex;
    private boolean maleOutlier;
    private boolean femaleOutlier;
    private boolean bothOutlier;

    /**
     * 
     * @return gets the MaleOutlier boolean
     */
    public boolean isMaleOutlier() {
        return maleOutlier;
    }

    /**
     * 
     * @param maleOutlier is the boolean to be set on the object
     */
    public void setMaleOutlier(boolean maleOutlier) {
        this.maleOutlier = maleOutlier;
    }

    /**
     * 
     * @return gets the FemaleOutlier boolean
     */
    public boolean isFemaleOutlier() {
        return femaleOutlier;
    }

    /**
     * 
     * @param femaleOutlier is the boolean to be set on the object
     */
    public void setFemaleOutlier(boolean femaleOutlier) {
        this.femaleOutlier = femaleOutlier;
    }

    /**
     * 
     * @return gets the BothOutlier boolean
     */
    public boolean isBothOutlier() {
        return bothOutlier;
    }

    /**
     * 
     * @param bothOutlier is the boolean to be set on the object
     */
    public void setBothOutlier(boolean bothOutlier) {
        this.bothOutlier = bothOutlier;
    }
    
    /**
     * @return the censusYear
     */
    public int getCensusYear() {
        return censusYear;
    }

    /**
     * 
     * @return the zone
     */
    public String getZone() {
        return zone;
    }
    
    /**
     * 
     * @param zone is the zone to set
     */
    public void setZone(String zone) {
        this.zone = zone;
    }
    
    /**
     * 
     * @return gets the count
     */
    public int getCount() {
        return count;
    }

    /**
     * 
     * @param count is the count to be set
     */
    public void setCount(int count) {
        this.count = count;
    }
    

    /**
     * @param censusYear the censusYear to set
     */
    public void setCensusYear(int censusYear) {
        this.censusYear = censusYear;
    }

    /**
     * @return the schoolName
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * @param schoolName the schoolName to set
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     * @return the district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district the district to set
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return the classification
     */
    public String getClassification() {
        return classification;
    }

    /**
     * @param classification the classification to set
     */
    public void setClassification(String classification) {
        this.classification = classification;
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
     * @return the genderDisparityIndex
     */
    public double getGenderDisparityIndex() {
        return genderDisparityIndex;
    }

    /**
     * @param genderDisparityIndex the genderDisparityIndex to set
     */
    public void setGenderDisparityIndex(double genderDisparityIndex) {
        this.genderDisparityIndex = genderDisparityIndex;
    }
        
    //if same YEAR, CLASSIFICATION, SCHOOL NAME, GRADE
    public void outlierEnrollment(ArrayList<Integer> years, ArrayList<FactEnrollment> enrollment){
        for(int i = 0; i < years.size(); i++){
            // if + 2 is not null
            if (i+2 < years.size()){
                for(int a = 0; a < enrollment.size(); a++){
                    //false outlier for 2 starting years
                    if(i == 0 || i == 1){
                        if(enrollment.get(a).getCensusYear() == years.get(i)){
                            enrollment.get(a).setMaleOutlier(false);
                            enrollment.get(a).setFemaleOutlier(false);
                            enrollment.get(a).setBothOutlier(false);
                        }
                    }
                    //gets base population
                    if(years.get(i) == enrollment.get(a).getCensusYear()){
                        
                        int firstMale = enrollment.get(a).getMaleCount();
                        int firstFemale = enrollment.get(a).getFemaleCount();
                        int firstYear = enrollment.get(a).getCensusYear();
                        String firstSchoolName = enrollment.get(a).getSchoolName();
                        String firstClassification = enrollment.get(a).getClassification();
                        String firstGradeLevel = enrollment.get(a).getLevel();
                        //gets the second population
                        for(int b = 0; b < enrollment.size(); b++){
                            if(enrollment.get(b).getCensusYear() == years.get(i+1)){ //if CensusYear = year after
                                if(enrollment.get(b).getSchoolName().equalsIgnoreCase(firstSchoolName) && enrollment.get(b).getClassification().equalsIgnoreCase(firstClassification) && enrollment.get(b).getLevel().equalsIgnoreCase(firstGradeLevel)){ 
                                    //System.out.println(1);
                                    int secondMale = enrollment.get(b).getMaleCount();
                                    int secondFemale = enrollment.get(b).getFemaleCount();
                                    int secondYear = enrollment.get(b).getCensusYear();
                                    String secondSchoolName = enrollment.get(b).getSchoolName();
                                    String secondClassification = enrollment.get(b).getClassification();
                                    String secondGradeLevel = enrollment.get(b).getLevel();
                                    //gets the third population
                                    for(int c = 0; c < enrollment.size(); c++){
                                        if(years.get(i+2) == enrollment.get(c).getCensusYear()){
                                            if(secondSchoolName.equalsIgnoreCase(enrollment.get(c).getSchoolName()) && enrollment.get(c).getClassification().equalsIgnoreCase(secondClassification) && enrollment.get(c).getLevel().equalsIgnoreCase(secondGradeLevel)){
                                                //System.out.println(2);
                                                int thirdMale   = enrollment.get(c).getMaleCount();
                                                int thirdFemale = enrollment.get(c).getFemaleCount();
                                                
                                                float maleGrowthRate    = (float) getGrowthRateForTwoYears(secondMale, firstMale, 1);
                                                float femaleGrowthRate  = (float) getGrowthRateForTwoYears(secondFemale, firstFemale, 1);
                                                
                                                float maleInTheGrowthRate   = Math.abs(((float)secondMale   * (maleGrowthRate/100)));
                                                float femaleInTheGrowthRate = Math.abs(((float)secondFemale * (femaleGrowthRate/100)));
                                                
                                                float maleMinusGrowthRate   = Math.round(secondMale     - maleInTheGrowthRate   - 100);
                                                float femaleMinusGrowthRate = Math.round(secondFemale   - femaleInTheGrowthRate - 100);
                                                
                                                float malePlusGrowthRate    = Math.round(secondMale     + maleInTheGrowthRate   + 100);
                                                float femalePlusGrowthRate  = Math.round(secondFemale   + femaleInTheGrowthRate + 100);
                                                
                                                if(thirdMale >= maleMinusGrowthRate && thirdMale <= malePlusGrowthRate){
                                                    enrollment.get(c).setMaleOutlier(false);
                                                }
                                                else{
                                                    if(maleGrowthRate <= 0){
                                                        System.out.println("YES");
                                                        if(maleGrowthRate == 0){
                                                            if(thirdMale >= secondMale-100 && thirdMale <= secondMale+100){
                                                                enrollment.get(c).setMaleOutlier(false);
                                                            } else {
                                                                enrollment.get(c).setMaleOutlier(true);
                                                            }
                                                        }
                                                        else if (maleGrowthRate < 0){
                                                            if(thirdMale >= secondMale-100 && thirdMale <= secondMale-100){
                                                                enrollment.get(c).setMaleOutlier(false);
                                                            } else {
                                                                enrollment.get(c).setMaleOutlier(true);
                                                            }
                                                        }
                                                       // people.get(c).setIsSingleOutlier(false);
                                                    }
                                                    else if(Float.isNaN(maleGrowthRate)){
                                                        if(thirdMale >= -100 && thirdMale <= 100){
                                                            enrollment.get(c).setMaleOutlier(false);
                                                        } else {
                                                            enrollment.get(c).setMaleOutlier(true);
                                                        }
                                                    }else{
                                                        enrollment.get(c).setMaleOutlier(true);
                                                    }
                                                }
                                                
                                                if(thirdFemale >= femaleMinusGrowthRate && thirdFemale <= femalePlusGrowthRate){
                                                    enrollment.get(c).setFemaleOutlier(false);
                                                }
                                                else{
                                                    if(femaleGrowthRate <= 0){
                                                        System.out.println("YES");
                                                        if(femaleGrowthRate == 0){
                                                            if(thirdFemale >= secondFemale-25 && thirdFemale <= secondFemale+25){
                                                                enrollment.get(c).setFemaleOutlier(false);
                                                            } else {
                                                                enrollment.get(c).setFemaleOutlier(true);
                                                            }
                                                        }
                                                        else if (femaleGrowthRate < 0){
                                                            if(thirdFemale >= secondFemale+25 && thirdFemale <= secondFemale-25){
                                                                enrollment.get(c).setFemaleOutlier(false);
                                                            } else {
                                                                enrollment.get(c).setFemaleOutlier(true);
                                                            }
                                                        }
                                                       // people.get(c).setIsSingleOutlier(false);
                                                    }
                                                    else if(Float.isNaN(femaleGrowthRate)){
                                                        if(thirdFemale >= -25 && thirdFemale <= 25){
                                                            enrollment.get(c).setFemaleOutlier(false);
                                                        } else {
                                                            enrollment.get(c).setFemaleOutlier(true);
                                                        }
                                                    }else{
                                                        enrollment.get(c).setFemaleOutlier(true);
                                                    }
                                                }
                                                if(enrollment.get(c).isMaleOutlier()){
                                                    System.out.println("---MALE----");
                                                    System.out.println("SchoolName: " + enrollment.get(c).getSchoolName());
                                                    System.out.println("Level: " + enrollment.get(c).getLevel());
                                                    System.out.println("Census Year: " + enrollment.get(c).getCensusYear());
                                                    System.out.println("Growth Rate: " + maleGrowthRate);
                                                    System.out.println("People in the Growth Rate: " + maleInTheGrowthRate);
                                                    System.out.println("First Population: " +  firstMale);
                                                    System.out.println("Second Population: " + secondMale);
                                                    System.out.println("Third Population: " + thirdMale);
                                                    System.out.print(maleMinusGrowthRate + " > " + thirdMale + " > " + malePlusGrowthRate + "\n");
                                                    enrollment.get(c).setBothOutlier(true);
                                                }
                                                if(enrollment.get(c).isFemaleOutlier()){
                                                    System.out.println("---FEMALE----");
                                                    System.out.println("SchoolName: " + enrollment.get(c).getSchoolName());
                                                    System.out.println("Census Year: " + enrollment.get(c).getCensusYear());
                                                    System.out.println("Level: " + enrollment.get(c).getLevel());
                                                    System.out.println("Growth Rate: " + femaleGrowthRate);
                                                    System.out.println("People in the Growth Rate: " + femaleInTheGrowthRate);
                                                    System.out.println("First Population: " +  firstFemale);
                                                    System.out.println("Second Population: " + secondFemale);
                                                    System.out.println("Third Population: " + thirdFemale);
                                                    System.out.print(femaleMinusGrowthRate + " > " + thirdFemale + " > " + femalePlusGrowthRate + "\n");
                                                    enrollment.get(c).setBothOutlier(true);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    
    public float getGrowthRateForTwoYears(int pa, int po, int t){
        //GROWTH RATE!
        //1. Get the PA/PO
        float papo = ((float)pa/po); 
        
        //2. Get the LOG of papo
        float logPapo = ((float)Math.log10(papo)); 
        //3. Divide log with time
        float timedivide = ((float)logPapo/t); 
        //4. Antilog answer
        float anti = ((float)Math.pow(10, timedivide)); 
        //5. Minus 1, times 100
        float growthRate = ((float)(anti-1)*100); 
        
        return growthRate;
    }
    
}
