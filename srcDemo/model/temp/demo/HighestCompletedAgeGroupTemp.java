/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.temp.demo;

import java.text.DecimalFormat;

/**
 *
 * @author Shermaine
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
