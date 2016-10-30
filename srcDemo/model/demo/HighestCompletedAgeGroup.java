/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package model.demo;

import java.text.DecimalFormat;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */

public class HighestCompletedAgeGroup  {

    String highestCompleted;
    int count;
    String cCount;
    
    

    DecimalFormat df = new DecimalFormat("#,###");

    
    public String getFormatcount(int count) {
        String scount = df.format(count);
        return scount;
    }
    
    public String getcCount() {
        return cCount;
    }

    public void setcCount(int cCount) {
        this.cCount = df.format(cCount);
    }
    boolean validation;

    public HighestCompletedAgeGroup() {
    }

    public String gethighestCompleted() {
        return highestCompleted;
    }

    public void sethighestCompleted(String highestCompleted) {
        this.highestCompleted = highestCompleted;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isValidation() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }

}
