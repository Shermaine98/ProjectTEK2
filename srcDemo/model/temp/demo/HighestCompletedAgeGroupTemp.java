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

    String highestAttaintment;
    String count;
    boolean validation;

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

    public boolean isValidation() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }

}
