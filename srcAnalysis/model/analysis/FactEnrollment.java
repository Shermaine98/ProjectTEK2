/*
 *  Copyright (C) ProjectTEK - DLSU CCS 2016
 *  All right Reserved   * 
 */

package model.analysis;

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

    /**
     * @return the censusYear
     */
    public int getCensusYear() {
        return censusYear;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
    
    public int getCount() {
        return count;
    }

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
    
//    public void setOutliers(ArrayList<Integer> years, ArrayList<factEnrollment> people){
//        for(int i = 0; i < years.size(); i++){
//            for(int x = 0; x < people.size(); x++)
//            if(years.get(i) == people.get(x).getCensusYear()){
//                String district1 = people.get(x).getDistrict();
//                int totalPeople1 = (people.get(x).getMaleCount()+people.get(x).getFemaleCount()); //po
//                if((i+1) < years.size() && years.get(i+1)!=null){
//                    for(int y = 0; y < people.size(); y++){
//                        if(people.get(y).getCensusYear() == years.get(i+1)){
//                            if(people.get(y).getBarangay() == barangay1){
//                                int totalPeople2 = people.get(y).getTotalNoOfPeople(); //pa
//                                int timeDifference = years.get(i+1) - years.get(i);
//                                double growthRate = getGrowthRateForTwoYears(totalPeople2, totalPeople1, timeDifference);
//                                if((i+2) < years.size() && years.get(i+2)!=null){
//                                    for(int z = 0; z < people.size();z++){
//                                        int totalPeople3 = people.get(z).getTotalNoOfPeople();
//                                        double peopleInTheGrowthRate = Math.abs(growthRate*totalPeople2);
//                                        double belowGrowthRate = totalPeople2 - peopleInTheGrowthRate;
//                                        double aboveGrowthRate = totalPeople2 + peopleInTheGrowthRate;
//                                        
//                                        if(totalPeople3 >= belowGrowthRate && totalPeople3 <= aboveGrowthRate){
//                                            people.get(z).setIsOutlier(false);
//                                            System.out.println(false);
//                                        }
//                                        else{
//                                            people.get(z).setIsOutlier(true);
//                                            System.out.println(true);
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//    
//    
//    public double getGrowthRateForTwoYears(int pa, int po, int t){
//        //GROWTH RATE!
//        //1. Get the PA/PO
//        double papo = pa/po;
//        //2. Get the LOG of papo
//        double logPapo = Math.log10(papo);
//        //3. Divide log with time
//        double timedivide = logPapo/t;
//        //4. Antilog answer
//        double anti = Math.pow(10, timedivide);
//        //5. Minus 1, times 100
//        double growthRate = (anti-1)*100;
//        
//        return growthRate;
//    }
    
}
