/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.education;

/**
 *
 * @author Shermaine
 */
public class Seats {
    private String gradeLevel;
    private int seatCount;

    /**
     * @return the gradeLevel
     */
    public String getGradeLevel() {
        return gradeLevel;
    }

    /**
     * @param gradeLevel the gradeLevel to set
     */
    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    /**
     * @return the seatCount
     */
    public int getSeatCount() {
        return seatCount;
    }

    /**
     * @param seatCount the seatCount to set
     */
    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }
    
}
