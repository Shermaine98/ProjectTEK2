/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.education;

/**
 *
 * @author Gian
 */
public class EnrollmentDet {
    private String gradeLevel;
    private int maleCount;
    private int femaleCount;
    private int totalCount;
    private boolean validation;

    public boolean isValidation() {
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

    public int getMaleCount() {
        return maleCount;
    }

    public void setMaleCount(int maleCount) {
        this.maleCount = maleCount;
    }

    public int getFemaleCount() {
        return femaleCount;
    }

    public void setFemaleCount(int femaleCount) {
        this.femaleCount = femaleCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    
    
    
    
}
