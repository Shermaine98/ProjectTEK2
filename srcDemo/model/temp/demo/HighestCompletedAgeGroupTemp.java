/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package model.temp.demo;

import java.text.DecimalFormat;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class HighestCompletedAgeGroupTemp {

   private String highestAttaintment;
   private String count;
   private int validation;
   private String reason;

    DecimalFormat df = new DecimalFormat("#,###");
    
   
    public String gethighestAttaintment() {
        return highestAttaintment;
    }

    public void sethighestAttaintment(String highestAttaintment) {
        this.highestAttaintment = highestAttaintment;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int isValidation() {
        return validation;
    }

    public void setValidation(int validation) {
        this.validation = validation;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

}
