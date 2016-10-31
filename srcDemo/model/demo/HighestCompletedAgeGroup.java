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
public class HighestCompletedAgeGroup {

    private String highestCompleted;
    private int count;
    private int Validation;
    private String Reason;

    DecimalFormat df = new DecimalFormat("#,###");

    public String getFormatcount(int count) {
        String scount = df.format(count);
        return scount;
    }

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

    public int isValidation() {
        return Validation;
    }

    public void setValidation(int validation) {
        this.Validation = Validation;
    }

    /**
     * @return the Reason
     */
    public String getReason() {
        return Reason;
    }

    /**
     * @param Reason the Reason to set
     */
    public void setReason(String Reason) {
        this.Reason = Reason;
    }

}
