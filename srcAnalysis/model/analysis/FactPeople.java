/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.analysis;

import java.util.ArrayList;

/**
 *
 * @author Gian
 */
public class FactPeople {
    private int censusYear;
    private int barangay;
    private String district;
    private String zone;
    private String gender;
    private int ageBracket;
    private int totalNoOfPeople;
    private int totalNoOfSingle;
    private int totalNoOfMarried;
    private int totalNoOfWidowed;
    private int totalNoOfDivorced;
    private int totalNoOfLiveIn;
    private int totalNoOfUnknown;
    private int totalNoOfNoEdu;
    private int totalNoOfPreschool;
    private int totalNoOf1stTo4thGrade;
    private int totalNoOf5thTo6thGrade;
    private int totalNoOfElemGrad;
    private int totalNoOfHSUndergrad;
    private int totalNoOfHSGrad;
    private int totalNoOfPSUndergrad;
    private int totalNoOfPSGrad;
    private int totalNoOfColUndergrad;
    private int totalNoOfDegreeHolder;
    private int totalNoOfBac;
    private int totalNoOfNotStated;
    private Boolean isOutlier;
    private double growthRate;
    private double participationRate;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
    
    public int getBarangay() {
        return barangay;
    }

    public void setBarangay(int barangay) {
        this.barangay = barangay;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Boolean getIsOutlier() {
        return isOutlier;
    }

    public void setIsOutlier(Boolean isOutlier) {
        this.isOutlier = isOutlier;
    }

    public double getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(double growthRate) {
        this.growthRate = growthRate;
    }

    public double getParticipationRate() {
        return participationRate;
    }

    public void setParticipationRate(double participationRate) {
        this.participationRate = participationRate;
    }

    public int getCensusYear() {
        return censusYear;
    }

    public void setCensusYear(int censusYear) {
        this.censusYear = censusYear;
    }

    public int getAgeBracket() {
        return ageBracket;
    }

    public void setAgeBracket(int ageBracket) {
        this.ageBracket = ageBracket;
    }

    public int getTotalNoOfPeople() {
        return totalNoOfPeople;
    }

    public void setTotalNoOfPeople(int totalNoOfPeople) {
        this.totalNoOfPeople = totalNoOfPeople;
    }

    public int getTotalNoOfSingle() {
        return totalNoOfSingle;
    }

    public void setTotalNoOfSingle(int totalNoOfSingle) {
        this.totalNoOfSingle = totalNoOfSingle;
    }

    public int getTotalNoOfMarried() {
        return totalNoOfMarried;
    }

    public void setTotalNoOfMarried(int totalNoOfMarried) {
        this.totalNoOfMarried = totalNoOfMarried;
    }

    public int getTotalNoOfWidowed() {
        return totalNoOfWidowed;
    }

    public void setTotalNoOfWidowed(int totalNoOfWidowed) {
        this.totalNoOfWidowed = totalNoOfWidowed;
    }

    public int getTotalNoOfDivorced() {
        return totalNoOfDivorced;
    }

    public void setTotalNoOfDivorced(int totalNoOfDivorced) {
        this.totalNoOfDivorced = totalNoOfDivorced;
    }

    public int getTotalNoOfLiveIn() {
        return totalNoOfLiveIn;
    }

    public void setTotalNoOfLiveIn(int totalNoOfLiveIn) {
        this.totalNoOfLiveIn = totalNoOfLiveIn;
    }

    public int getTotalNoOfUnknown() {
        return totalNoOfUnknown;
    }

    public void setTotalNoOfUnknown(int totalNoOfUnknown) {
        this.totalNoOfUnknown = totalNoOfUnknown;
    }

    public int getTotalNoOfNoEdu() {
        return totalNoOfNoEdu;
    }

    public void setTotalNoOfNoEdu(int totalNoOfNoEdu) {
        this.totalNoOfNoEdu = totalNoOfNoEdu;
    }

    public int getTotalNoOfPreschool() {
        return totalNoOfPreschool;
    }

    public void setTotalNoOfPreschool(int totalNoOfPreschool) {
        this.totalNoOfPreschool = totalNoOfPreschool;
    }

    public int getTotalNoOf1stTo4thGrade() {
        return totalNoOf1stTo4thGrade;
    }

    public void setTotalNoOf1stTo4thGrade(int totalNoOf1stTo4thGrade) {
        this.totalNoOf1stTo4thGrade = totalNoOf1stTo4thGrade;
    }

    public int getTotalNoOf5thTo6thGrade() {
        return totalNoOf5thTo6thGrade;
    }

    public void setTotalNoOf5thTo6thGrade(int totalNoOf5thTo6thGrade) {
        this.totalNoOf5thTo6thGrade = totalNoOf5thTo6thGrade;
    }

    public int getTotalNoOfElemGrad() {
        return totalNoOfElemGrad;
    }

    public void setTotalNoOfElemGrad(int totalNoOfElemGrad) {
        this.totalNoOfElemGrad = totalNoOfElemGrad;
    }

    public int getTotalNoOfHSUndergrad() {
        return totalNoOfHSUndergrad;
    }

    public void setTotalNoOfHSUndergrad(int totalNoOfHSUndergrad) {
        this.totalNoOfHSUndergrad = totalNoOfHSUndergrad;
    }

    public int getTotalNoOfHSGrad() {
        return totalNoOfHSGrad;
    }

    public void setTotalNoOfHSGrad(int totalNoOfHSGrad) {
        this.totalNoOfHSGrad = totalNoOfHSGrad;
    }

    public int getTotalNoOfPSUndergrad() {
        return totalNoOfPSUndergrad;
    }

    public void setTotalNoOfPSUndergrad(int totalNoOfPSUndergrad) {
        this.totalNoOfPSUndergrad = totalNoOfPSUndergrad;
    }

    public int getTotalNoOfPSGrad() {
        return totalNoOfPSGrad;
    }

    public void setTotalNoOfPSGrad(int totalNoOfPSGrad) {
        this.totalNoOfPSGrad = totalNoOfPSGrad;
    }

    public int getTotalNoOfColUndergrad() {
        return totalNoOfColUndergrad;
    }

    public void setTotalNoOfColUndergrad(int totalNoOfColUndergrad) {
        this.totalNoOfColUndergrad = totalNoOfColUndergrad;
    }

    public int getTotalNoOfDegreeHolder() {
        return totalNoOfDegreeHolder;
    }

    public void setTotalNoOfDegreeHolder(int totalNoOfDegreeHolder) {
        this.totalNoOfDegreeHolder = totalNoOfDegreeHolder;
    }

    public int getTotalNoOfBac() {
        return totalNoOfBac;
    }

    public void setTotalNoOfBac(int totalNoOfBac) {
        this.totalNoOfBac = totalNoOfBac;
    }

    public int getTotalNoOfNotStated() {
        return totalNoOfNotStated;
    }

    public void setTotalNoOfNotStated(int totalNoOfNotStated) {
        this.totalNoOfNotStated = totalNoOfNotStated;
    }
    
    public void outliers(ArrayList<Integer> years, ArrayList<FactPeople> people){
        for(int i = 0; i < years.size(); i++){
            // if + 2 is not null
            if (i+2 < years.size()){
                for(int a = 0; a < people.size(); a++){
                    //false outlier for 2 starting years
                    if(i == 0 || i == 1){
                        if(people.get(a).getCensusYear() == years.get(i)){
                            people.get(a).setIsOutlier(false);
                        }
                    }
                    //gets base population
                    if(years.get(i) == people.get(a).getCensusYear()){
                        int firstPopulation = people.get(a).getTotalNoOfPeople();
                        int firstBarangayNumber = people.get(a).getBarangay();
                        //gets the second population
                        for(int b = 0; b < people.size(); b++){
                            if(people.get(b).getCensusYear() == years.get(i+1)){ //if CensusYear = year after
                                if(people.get(b).getBarangay() == firstBarangayNumber){ //if same barangay
                                    int secondPopulation = people.get(b).getTotalNoOfPeople();
                                    int secondBarangayNumber = people.get(b).getBarangay();
                                    //gets the third population
                                    for(int c = 0; c < people.size(); c++){
                                        if(years.get(i+2) == people.get(c).getCensusYear()){
                                            if(secondBarangayNumber == people.get(c).getBarangay()){
                                                int thirdPopulation = people.get(c).getTotalNoOfPeople();
                                                float growthRate = getGrowthRateForTwoYears(secondPopulation, firstPopulation, 1);
                                                float peopleInTheGrowthRate = Math.abs(((float)secondPopulation * growthRate));
                                                float minusGrowthRate = secondPopulation - peopleInTheGrowthRate;
                                                float plusGrowthRate = secondPopulation + peopleInTheGrowthRate;
                                                if(thirdPopulation >= minusGrowthRate && thirdPopulation <= plusGrowthRate){
                                                    people.get(c).setIsOutlier(false);
                                                }
                                                else{
                                                    people.get(c).setIsOutlier(true);
                                                    System.out.println("------");
                                                    System.out.println("Baranagay: " + people.get(c).getBarangay());
                                                    System.out.println("Census Year: " + people.get(c).getCensusYear());
                                                    System.out.println("Growth Rate: " + growthRate);
                                                    System.out.println("People in the Growth Rate: " + peopleInTheGrowthRate);
                                                    System.out.print(minusGrowthRate + " > " + thirdPopulation + " > " + plusGrowthRate + "\n");
                                                    System.out.println("IsOutlier" +  people.get(c).getIsOutlier());
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
    
    //    public void setOutliers(ArrayList<Integer> years, ArrayList<FactPeople> people){
//        for(int i = 0; i < years.size(); i++){ //for years
//            if(i+2 < years.size()){
//            for(int x = 0; x < people.size(); x++) //for people
//            if(years.get(i) == people.get(x).getCensusYear()){ //if years = people.years
//                int barangay1 = people.get(x).getBarangay(); //gets barangay number of people given a year.
//                int totalPeople1 = people.get(x).getTotalNoOfPeople(); //po //gets the number of people in barnagay 1
//                if((i+1) < years.size() && years.get(i+1)!=null){
//                    for(int y = 0; y < people.size(); y++){
//                        if(people.get(y).getCensusYear() == years.get(i+1)){    
//                            if(people.get(y).getBarangay() == barangay1){
//                                int barangay2 = people.get(y).getBarangay();
//                                int totalPeople2 = people.get(y).getTotalNoOfPeople(); //pa
//                                double growthRate = getGrowthRateForTwoYears(totalPeople2, totalPeople1, 1);
//                                if((i+2) < years.size() && years.get(i+2)!=null){
//                                    for(int z = 0; z < people.size();z++){
//                                        if(people.get(z).getCensusYear() == years.get(i+2)){
//                                            if(people.get(z).getBarangay()== barangay2 &&
//                                                    people.get(z).getBarangay() == barangay1){
//                                                int totalPeople3 = people.get(z).getTotalNoOfPeople();
//                                                double peopleInTheGrowthRate = Math.abs(growthRate*totalPeople2);
//                                                double belowGrowthRate = totalPeople2 - peopleInTheGrowthRate;
//                                                double aboveGrowthRate = totalPeople2 + peopleInTheGrowthRate;
//                                                if(totalPeople3 >= belowGrowthRate && totalPeople3 <= aboveGrowthRate){
//                                                    people.get(z).setIsOutlier(false);
//                                                }
//                                                else{
//                                                    people.get(z).setIsOutlier(true);
//                                                    System.out.println("------");
//                                                    System.out.println("Baranagay: " + people.get(z).getBarangay());
//                                                    System.out.println("Census Year: " + people.get(z).getCensusYear());
//                                                    System.out.println("Census Year: " + people.get(z).getCensusYear());
//                                                    System.out.println("Growth Rate: " + growthRate);
//                                                    System.out.println("People in the Growth Rate: " + peopleInTheGrowthRate);
//                                                    System.out.print(belowGrowthRate + " > " + totalPeople3 + " > " + aboveGrowthRate + "\n");
//                                                    System.out.println("IsOutlier" +  people.get(z).getIsOutlier());
//                                                }
//                                                
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            }
//        }
//    }
}
