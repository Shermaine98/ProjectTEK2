/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.temp.education;

/**
 *
 * @author Gian
 */
public class EnrollmentDetTemp {
    private String gradeLevel;
    private String maleCount;
    private String femaleCount;
    private String totalCount;
    private boolean validation;

    public boolean getValidation() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getMaleCount() {
        return maleCount;
    }

    public void setMaleCount(String maleCount) {
        this.maleCount = maleCount;
    }

    public String getFemaleCount() {
        return femaleCount;
    }

    public void setFemaleCount(String femaleCount) {
        this.femaleCount = femaleCount;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }
    
    
}
