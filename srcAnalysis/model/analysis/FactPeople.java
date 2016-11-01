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
public class FactPeople {
    private int censusYear;
    private int barangay;
    private String district;
    private String zone;
    private String gender;
    private String ageBracket;
    private int totalNoOfPeople;
    private int totalNoOfSingle;
    private boolean isSingleOutlier;
    private int totalNoOfMarried;
    private boolean isMarriedOutlier;
    private int totalNoOfWidowed;
    private boolean isWidowedOutlier;
    private int totalNoOfDivorced;
    private boolean isDivorcedOutlier;
    private int totalNoOfLiveIn;
    private boolean isLiveInOutlier;
    private int totalNoOfUnknown;
    private boolean isUnknownOutlier;
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

    public boolean isIsSingleOutlier() {
        return isSingleOutlier;
    }

    public void setIsSingleOutlier(boolean isSingleOutlier) {
        this.isSingleOutlier = isSingleOutlier;
    }

    public boolean isIsMarriedOutlier() {
        return isMarriedOutlier;
    }

    public void setIsMarriedOutlier(boolean isMarriedOutlier) {
        this.isMarriedOutlier = isMarriedOutlier;
    }

    public boolean isIsWidowedOutlier() {
        return isWidowedOutlier;
    }

    public void setIsWidowedOutlier(boolean isWidowedOutlier) {
        this.isWidowedOutlier = isWidowedOutlier;
    }

    public boolean isIsDivorcedOutlier() {
        return isDivorcedOutlier;
    }

    public void setIsDivorcedOutlier(boolean isDivorcedOutlier) {
        this.isDivorcedOutlier = isDivorcedOutlier;
    }

    public boolean isIsLiveInOutlier() {
        return isLiveInOutlier;
    }

    public void setIsLiveInOutlier(boolean isLiveInOutlier) {
        this.isLiveInOutlier = isLiveInOutlier;
    }

    public boolean isIsUnknownOutlier() {
        return isUnknownOutlier;
    }

    public void setIsUnknownOutlier(boolean isUnknownOutlier) {
        this.isUnknownOutlier = isUnknownOutlier;
    }
    
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

    public String getAgeBracket() {
        return ageBracket;
    }

    public void setAgeBracket(String ageBracket) {
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
    
    public void outliersHHPopSexAgeGroup(ArrayList<Integer> years, ArrayList<FactPeople> people){
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
                        String firstGender = people.get(a).getGender();
                        String firstAgeGroup = people.get(a).getAgeBracket();
                        //gets the second population
                        for(int b = 0; b < people.size(); b++){
                            if(people.get(b).getCensusYear() == years.get(i+1)){ //if CensusYear = year after
                                if(people.get(b).getBarangay() == firstBarangayNumber && people.get(b).getGender().equalsIgnoreCase(firstGender) && people.get(b).getAgeBracket().equalsIgnoreCase(firstAgeGroup)){ //if same barangay
                                    int secondPopulation = people.get(b).getTotalNoOfPeople();
                                    int secondBarangayNumber = people.get(b).getBarangay();
                                    String secondGender = people.get(b).getGender();
                                    String secondAgeGroup = people.get(b).getAgeBracket() ;
                                    //gets the third population
                                    for(int c = 0; c < people.size(); c++){
                                        if(years.get(i+2) == people.get(c).getCensusYear()){
                                            if(secondBarangayNumber == people.get(c).getBarangay() && people.get(c).getGender().equalsIgnoreCase(secondGender) && people.get(c).getAgeBracket().equalsIgnoreCase(secondAgeGroup)){
                                                int thirdPopulation = people.get(c).getTotalNoOfPeople();
                                                float growthRate = getGrowthRateForTwoYears(secondPopulation, firstPopulation, 1);
                                                float peopleInTheGrowthRate = Math.abs(((float)secondPopulation * growthRate));
                                                float minusGrowthRate = secondPopulation - peopleInTheGrowthRate;
                                                float plusGrowthRate = secondPopulation + peopleInTheGrowthRate;
                                                if(thirdPopulation >= minusGrowthRate && thirdPopulation <= plusGrowthRate){
                                                    people.get(c).setIsOutlier(false);
                                                }
                                                else{
                                                    float secondGrowthRate = Math.abs(getGrowthRateForTwoYears(thirdPopulation, secondPopulation, 1));
                                                    if(Math.abs(growthRate) <= 0){
                                                        people.get(c).setIsOutlier(false);
                                                    } else{
                                                        people.get(c).setIsOutlier(true);
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
    }
    
    public void setOutliersMaritalStatus(ArrayList<Integer> years, ArrayList<FactPeople> people){
        for(int i = 0; i < years.size(); i++){
            // if + 2 is not null
            if (i+2 < years.size()){
                for(int a = 0; a < people.size(); a++){
                    //false outlier for 2 starting years
                    if(i == 0 || i == 1){
                        if(people.get(a).getCensusYear() == years.get(i)){
                            people.get(a).setIsSingleOutlier(false);
                            people.get(a).setIsMarriedOutlier(false);
                            people.get(a).setIsWidowedOutlier(false);
                            people.get(a).setIsLiveInOutlier(false);
                            people.get(a).setIsDivorcedOutlier(false);
                            people.get(a).setIsUnknownOutlier(false);
                        }
                    }
                    //gets base population
                    if(years.get(i) == people.get(a).getCensusYear()){
                        int firstSingle = people.get(a).getTotalNoOfSingle();
                        int firstMarried = people.get(a).getTotalNoOfMarried();
                        int firstWidowed = people.get(a).getTotalNoOfWidowed();
                        int firstLiveIn = people.get(a).getTotalNoOfLiveIn();
                        int firstDivorced = people.get(a).getTotalNoOfDivorced();
                        int firstUnknown = people.get(a).getTotalNoOfUnknown();
                        
                        int firstBarangayNumber = people.get(a).getBarangay();
                        String firstGender = people.get(a).getGender();
                        //gets the second population
                        for(int b = 0; b < people.size(); b++){
                            if(people.get(b).getCensusYear() == years.get(i+1)){ //if CensusYear = year after
                                if(people.get(b).getBarangay() == firstBarangayNumber && people.get(b).getGender().equalsIgnoreCase(firstGender)){ //if same barangay
                                    int secondSingle = people.get(b).getTotalNoOfSingle();
                                    int secondMarried = people.get(b).getTotalNoOfMarried();
                                    int secondWidowed = people.get(b).getTotalNoOfWidowed();
                                    int secondLiveIn = people.get(b).getTotalNoOfLiveIn();
                                    int secondDivorced = people.get(b).getTotalNoOfDivorced();
                                    int secondUnknown = people.get(b).getTotalNoOfUnknown();
                                    int secondBarangayNumber = people.get(b).getBarangay();
                                    String secondGender = people.get(b).getGender();
                                    //gets the third population
                                    for(int c = 0; c < people.size(); c++){
                                        if(years.get(i+2) == people.get(c).getCensusYear()){
                                            if(secondBarangayNumber == people.get(c).getBarangay() && people.get(c).getGender().equalsIgnoreCase(secondGender)){
                                                //Gets third population
                                                int thirdSingle = people.get(c).getTotalNoOfSingle();
                                                int thirdMarried = people.get(c).getTotalNoOfMarried();
                                                int thirdWidowed = people.get(c).getTotalNoOfWidowed();
                                                int thirdLiveIn = people.get(c).getTotalNoOfLiveIn();
                                                int thirdDivorced = people.get(c).getTotalNoOfDivorced();
                                                int thirdUnknown = people.get(c).getTotalNoOfUnknown();
                                                //Calculate Growth Rate
                                                float growthRateSingle      = getGrowthRateForTwoYears(secondSingle, firstSingle, 1);
                                                float growthRateMarried     = getGrowthRateForTwoYears(secondMarried, firstMarried, 1);
                                                float growthRateWidowed     = getGrowthRateForTwoYears(secondWidowed, firstWidowed, 1);
                                                float growthRateLiveIn      = getGrowthRateForTwoYears(secondLiveIn, firstLiveIn, 1);
                                                float growthRateDivorced    = getGrowthRateForTwoYears(secondDivorced, firstDivorced, 1);
                                                float growthRateUnknown     = getGrowthRateForTwoYears(secondUnknown, firstUnknown, 1);
                                                //Calculates how much people are included in the growthRate
                                                float peopleInTheGrowthRateSingle   = Math.abs(((float)secondSingle     * (growthRateSingle/100)));
                                                float peopleInTheGrowthRateMarried  = Math.abs(((float)secondMarried    * (growthRateMarried/100)));
                                                float peopleInTheGrowthRateWidowed  = Math.abs(((float)secondWidowed    * (growthRateWidowed/100)));
                                                float peopleInTheGrowthRateLiveIn   = Math.abs(((float)secondLiveIn     * (growthRateLiveIn/100)));
                                                float peopleInTheGrowthRateDivorced = Math.abs(((float)secondDivorced   * (growthRateDivorced/100)));
                                                float peopleInTheGrowthRateUnknown  = Math.abs(((float)secondUnknown    * (growthRateUnknown/100)));
                                                //gets the range. This is the minus side
                                                float minusGrowthRateSingle     = Math.round(secondSingle - peopleInTheGrowthRateSingle    -25);
                                                float minusGrowthRateMarried    = Math.round(secondMarried - peopleInTheGrowthRateMarried  -25);
                                                float minusGrowthRateWidowed    = Math.round(secondWidowed - peopleInTheGrowthRateWidowed  -25);
                                                float minusGrowthRateLiveIn     = Math.round(secondLiveIn - peopleInTheGrowthRateLiveIn    -25);
                                                float minusGrowthRateDivorced   = Math.round(secondDivorced - peopleInTheGrowthRateDivorced-25);
                                                float minusGrowthRateUnknown    = Math.round(secondUnknown - peopleInTheGrowthRateUnknown  -25);
                                                //gets the range. This is the plus side
                                                float plusGrowthRateSingle      = Math.round(secondSingle + peopleInTheGrowthRateSingle     +25);
                                                float plusGrowthRateMarried     = Math.round(secondMarried + peopleInTheGrowthRateMarried   +25);
                                                float plusGrowthRateWidowed     = Math.round(secondWidowed + peopleInTheGrowthRateWidowed   +25);
                                                float plusGrowthRateLiveIn      = Math.round(secondLiveIn + peopleInTheGrowthRateLiveIn     +25);
                                                float plusGrowthRateDivorced    = Math.round( secondDivorced + peopleInTheGrowthRateDivorced+25);
                                                float plusGrowthRateUnknown     = Math.round(secondUnknown + peopleInTheGrowthRateUnknown   +25);
                                                
                                                if(thirdSingle >= minusGrowthRateSingle && thirdSingle <= plusGrowthRateSingle){
                                                    people.get(c).setIsSingleOutlier(false);
                                                }
                                                else{
                                                    if(growthRateSingle <= 0){
                                                        System.out.println("YES");
                                                        if(growthRateSingle == 0){
                                                            if(thirdSingle >= secondSingle-25 && thirdSingle <= secondSingle+25){
                                                                people.get(c).setIsSingleOutlier(false);
                                                            } else {
                                                                people.get(c).setIsSingleOutlier(true);
                                                            }
                                                        }
                                                        else if (growthRateSingle < 0){
                                                            if(thirdSingle >= secondSingle+25 && thirdSingle <= secondSingle-25){
                                                                people.get(c).setIsSingleOutlier(false);
                                                            } else {
                                                                people.get(c).setIsSingleOutlier(true);
                                                            }
                                                        }
                                                       // people.get(c).setIsSingleOutlier(false);
                                                    }
                                                    else if(Float.isNaN(growthRateSingle)){
                                                        if(thirdSingle >= -25 && thirdSingle <= 25){
                                                            people.get(c).setIsSingleOutlier(false);
                                                        } else {
                                                            people.get(c).setIsSingleOutlier(true);
                                                        }
                                                    }else{
                                                        people.get(c).setIsSingleOutlier(true);
                                                    }
                                                }
                                                
                                                if(thirdMarried >= minusGrowthRateMarried && thirdMarried <= plusGrowthRateMarried){
                                                    people.get(c).setIsMarriedOutlier(false);
                                                }
                                                else{
                                                    if(growthRateMarried <= 0){
                                                        //System.out.println("YES");
                                                        if(growthRateMarried == 0){
                                                            if(thirdMarried >= secondMarried-25 && thirdMarried <= secondMarried+25){
                                                                people.get(c).setIsMarriedOutlier(false);
                                                            } else {
                                                                people.get(c).setIsMarriedOutlier(true);
                                                            }
                                                        }
                                                        else if (growthRateMarried < 0){
                                                            if(thirdMarried >= secondMarried+25 && thirdMarried <= secondMarried-25){
                                                                people.get(c).setIsMarriedOutlier(false);
                                                            } else {
                                                                people.get(c).setIsMarriedOutlier(true);
                                                            }
                                                        }
                                                        //people.get(c).setIsMarriedOutlier(false);
                                                    }
                                                    else if(Float.isNaN(growthRateMarried)){
                                                        if(thirdMarried >= -25 && thirdMarried <= 25){
                                                            people.get(c).setIsMarriedOutlier(false);
                                                        } else {
                                                            people.get(c).setIsMarriedOutlier(true);
                                                        }
                                                    }else{
                                                        people.get(c).setIsMarriedOutlier(true);
                                                    }
                                                }
                                                
                                                if(thirdWidowed >= minusGrowthRateWidowed && thirdWidowed <= plusGrowthRateWidowed){
                                                    people.get(c).setIsWidowedOutlier(false);
                                                }
                                                else{
                                                    if(growthRateWidowed <= 0){
                                                        System.out.println("YES");
                                                        if(growthRateWidowed == 0){
                                                            if(thirdWidowed >= secondWidowed-25 && thirdWidowed <= secondWidowed+25){
                                                                people.get(c).setIsWidowedOutlier(false);
                                                            } else {
                                                                people.get(c).setIsWidowedOutlier(true);
                                                            }
                                                        }
                                                        else if (growthRateWidowed < 0){
                                                            if(thirdWidowed >= secondWidowed+25 && thirdWidowed <= secondWidowed-25){
                                                                people.get(c).setIsWidowedOutlier(false);
                                                            } else {
                                                                people.get(c).setIsWidowedOutlier(true);
                                                            }
                                                        }
                                                        //people.get(c).setIsWidowedOutlier(false);
                                                    }
                                                    else if(Float.isNaN(growthRateWidowed)){
                                                        if(thirdWidowed >= -25 && thirdWidowed <= 25){
                                                            people.get(c).setIsWidowedOutlier(false);
                                                        } else {
                                                            people.get(c).setIsWidowedOutlier(true);
                                                        }
                                                    }else{
                                                        people.get(c).setIsWidowedOutlier(true);
                                                    }
                                                }
                                                
                                                if(thirdLiveIn >= minusGrowthRateLiveIn && thirdLiveIn <= plusGrowthRateLiveIn){
                                                    people.get(c).setIsLiveInOutlier(false);
                                                }
                                                else{
                                                    if(growthRateLiveIn <= 0){
                                                        System.out.println("YES");
                                                        if(growthRateLiveIn == 0){
                                                            if(thirdLiveIn >= secondLiveIn-25 && thirdLiveIn <= secondLiveIn+25){
                                                                people.get(c).setIsLiveInOutlier(false);
                                                            } else {
                                                                people.get(c).setIsLiveInOutlier(true);
                                                            }
                                                        }
                                                        else if (growthRateLiveIn < 0){
                                                            if(thirdLiveIn >= secondLiveIn+25 && thirdLiveIn <= secondLiveIn-25){
                                                                people.get(c).setIsLiveInOutlier(false);
                                                            } else {
                                                                people.get(c).setIsLiveInOutlier(true);
                                                            }
                                                        }
                                                        //people.get(c).setIsLiveInOutlier(false);
                                                    }
                                                    else if(Float.isNaN(growthRateLiveIn)){
                                                        if(thirdLiveIn >= -25 && thirdLiveIn <= 25){
                                                            people.get(c).setIsLiveInOutlier(false);
                                                        } else {
                                                            people.get(c).setIsLiveInOutlier(true);
                                                        }
                                                    }else{
                                                        people.get(c).setIsLiveInOutlier(true);
                                                    }
                                                }
                                                
                                                if(thirdDivorced >= minusGrowthRateDivorced && thirdDivorced <= plusGrowthRateDivorced){
                                                    people.get(c).setIsDivorcedOutlier(false);
                                                }
                                                else{
                                                    if(growthRateDivorced <= 0){
                                                        System.out.println("YES");
                                                        if(growthRateDivorced == 0){
                                                            if(thirdDivorced >= secondDivorced-25 && thirdDivorced <= secondDivorced+25){
                                                                people.get(c).setIsDivorcedOutlier(false);
                                                            } else {
                                                                people.get(c).setIsDivorcedOutlier(true);
                                                            }
                                                        }
                                                        else if (growthRateDivorced < 0){
                                                            if(thirdDivorced >= secondDivorced+25 && thirdDivorced <= secondDivorced-25){
                                                                people.get(c).setIsDivorcedOutlier(false);
                                                            } else {
                                                                people.get(c).setIsDivorcedOutlier(true);
                                                            }
                                                        }
                                                        //people.get(c).setIsDivorcedOutlier(false);
                                                    }
                                                    else if(Float.isNaN(growthRateDivorced)){
                                                        if(thirdDivorced >= -25 && thirdDivorced <= 25){
                                                            people.get(c).setIsDivorcedOutlier(false);
                                                        } else {
                                                            people.get(c).setIsDivorcedOutlier(true);
                                                        }
                                                    }else{
                                                        people.get(c).setIsDivorcedOutlier(true);
                                                        
                                                    }
                                                }
                                                
                                                if(thirdUnknown >= minusGrowthRateUnknown && thirdUnknown <= plusGrowthRateUnknown){
                                                    people.get(c).setIsUnknownOutlier(false);
                                                }
                                                else{
                                                    if(growthRateUnknown <= 0){
                                                        System.out.println("YES");
                                                        if(growthRateUnknown == 0){
                                                            if(thirdUnknown >= secondUnknown-25 && thirdUnknown <= secondUnknown+25){
                                                                people.get(c).setIsUnknownOutlier(false);
                                                            } else {
                                                                people.get(c).setIsUnknownOutlier(true);
                                                            }
                                                        }
                                                        else if (growthRateUnknown < 0){
                                                            if(thirdUnknown >= secondUnknown+25 && thirdUnknown <= secondUnknown-25){
                                                                people.get(c).setIsUnknownOutlier(false);
                                                            } else {
                                                                people.get(c).setIsUnknownOutlier(true);
                                                            }
                                                        }
                                                        //people.get(c).setIsUnknownOutlier(false);
                                                    }
                                                    else if(Float.isNaN(growthRateUnknown)){
                                                        if(thirdUnknown >= -25 && thirdUnknown <= 25){
                                                            people.get(c).setIsUnknownOutlier(false);
                                                        } else {
                                                            people.get(c).setIsUnknownOutlier(true);
                                                        }
                                                    }else{
                                                        people.get(c).setIsUnknownOutlier(true);
                                                    }
                                                }
                                                
                                                
                                                //PRINT ERRORS
                                                if(people.get(c).isIsWidowedOutlier()){
                                                    System.out.println("---WIDOWED----");
                                                    System.out.println("Baranagay: " + people.get(c).getBarangay());
                                                    System.out.println("Census Year: " + people.get(c).getCensusYear());
                                                    System.out.println("Growth Rate: " + growthRateWidowed);
                                                    System.out.println("People in the Growth Rate: " + peopleInTheGrowthRateWidowed);
                                                    System.out.println("First Population: " +  firstWidowed);
                                                    System.out.println("Second Population: " + secondWidowed);
                                                    System.out.print(minusGrowthRateWidowed + " > " + thirdWidowed + " > " + plusGrowthRateWidowed + "\n");
                                                    //System.out.println("IsOutlier" +  people.get(c).get());
                                                    System.out.println("Gender: " +  people.get(c).getGender());
                                                    //System.out.println("Age Group: " +  people.get(c).getAgeBracket());
                                                }
                                                if(people.get(c).isIsDivorcedOutlier()){
                                                    System.out.println("---DIVORCED----");
                                                    System.out.println("Baranagay: " + people.get(c).getBarangay());
                                                    System.out.println("Census Year: " + people.get(c).getCensusYear());
                                                    System.out.println("Growth Rate: " + growthRateDivorced);
                                                    System.out.println("People in the Growth Rate: " + peopleInTheGrowthRateDivorced);
                                                    System.out.println("First Population: " +  firstDivorced);
                                                    System.out.println("Second Population: " + secondDivorced);
                                                    System.out.print(minusGrowthRateDivorced + " > " + thirdDivorced + " > " + plusGrowthRateDivorced + "\n");
                                                    //System.out.println("IsOutlier" +  people.get(c).get());
                                                    System.out.println("Gender: " +  people.get(c).getGender());
                                                    //System.out.println("Age Group: " +  people.get(c).getAgeBracket());
                                                }
                                                if(people.get(c).isIsLiveInOutlier()){
                                                    System.out.println("---LIVE IN----");
                                                    System.out.println("Baranagay: " + people.get(c).getBarangay());
                                                    System.out.println("Census Year: " + people.get(c).getCensusYear());
                                                    System.out.println("Growth Rate: " + growthRateLiveIn);
                                                    System.out.println("People in the Growth Rate: " + peopleInTheGrowthRateLiveIn);
                                                    System.out.println("First Population: " +  firstLiveIn);
                                                    System.out.println("Second Population: " + secondLiveIn);
                                                    System.out.print(minusGrowthRateLiveIn + " > " + thirdLiveIn + " > " + plusGrowthRateLiveIn + "\n");
                                                    //System.out.println("IsOutlier" +  people.get(c).get());
                                                    System.out.println("Gender: " +  people.get(c).getGender());
                                                    //System.out.println("Age Group: " +  people.get(c).getAgeBracket());
                                                }
                                                if(people.get(c).isIsMarriedOutlier()){
                                                    System.out.println("---MARRIED----");
                                                    System.out.println("Baranagay: " + people.get(c).getBarangay());
                                                    System.out.println("Census Year: " + people.get(c).getCensusYear());
                                                    System.out.println("Growth Rate: " + growthRateMarried);
                                                    System.out.println("People in the Growth Rate: " + peopleInTheGrowthRateMarried);
                                                    System.out.println("First Population: " +  firstMarried);
                                                    System.out.println("Second Population: " + secondMarried);
                                                    System.out.print(minusGrowthRateMarried + " > " + thirdMarried + " > " + plusGrowthRateMarried + "\n");
                                                    //System.out.println("IsOutlier" +  people.get(c).get());
                                                    System.out.println("Gender: " +  people.get(c).getGender());
                                                    //System.out.println("Age Group: " +  people.get(c).getAgeBracket());
                                                }
                                                if(people.get(c).isIsUnknownOutlier()){
                                                    System.out.println("---UNKNOWN----");
                                                    System.out.println("Baranagay: " + people.get(c).getBarangay());
                                                    System.out.println("Census Year: " + people.get(c).getCensusYear());
                                                    System.out.println("Growth Rate: " + growthRateUnknown);
                                                    System.out.println("People in the Growth Rate: " + peopleInTheGrowthRateUnknown);
                                                    System.out.println("First Population: " +  firstUnknown);
                                                    System.out.println("Second Population: " + secondUnknown);
                                                    System.out.print(minusGrowthRateUnknown + " > " + thirdUnknown + " > " + plusGrowthRateUnknown + "\n");
                                                    //System.out.println("IsOutlier" +  people.get(c).get());
                                                    System.out.println("Gender: " +  people.get(c).getGender());
                                                    //System.out.println("Age Group: " +  people.get(c).getAgeBracket());
                                                }
                                                if(people.get(c).isIsSingleOutlier()){
                                                    System.out.println("---SINGLE----");
                                                    System.out.println("Baranagay: " + people.get(c).getBarangay());
                                                    System.out.println("Census Year: " + people.get(c).getCensusYear());
                                                    System.out.println("Growth Rate: " + growthRateSingle);
                                                    System.out.println("People in the Growth Rate: " + peopleInTheGrowthRateSingle);
                                                    System.out.println("First Population: " +  firstSingle);
                                                    System.out.println("Second Population: " + secondSingle);
                                                    System.out.println("Third Population: " + thirdSingle);
                                                    System.out.print(minusGrowthRateSingle + " > " + thirdSingle + " > " + plusGrowthRateSingle + "\n");
                                                    //System.out.println("IsOutlier" +  people.get(c).get());
                                                    System.out.println("Gender: " +  people.get(c).getGender());
                                                    //System.out.println("Age Group: " +  people.get(c).getAgeBracket());
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
