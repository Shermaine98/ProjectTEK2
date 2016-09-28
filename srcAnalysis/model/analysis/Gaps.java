/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.analysis;

import java.util.ArrayList;

/**
 *
 * @author giancarloroxas
 */
public class Gaps {
    int censusYear, gap;
    String district, zone;

    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }
    
    public int getCensusYear() {
        return censusYear;
    }

    public void setCensusYear(int censusYear) {
        this.censusYear = censusYear;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
    
    public ArrayList<Gaps> calculateGaps(ArrayList<FactSchoolTeachersFacilities> classrooms, ArrayList<FactEnrollment> enrollment){
        ArrayList<Gaps> gaps = new ArrayList<Gaps>();
        
        for(int i = 0; i < classrooms.size(); i++){
            for(int x = 0; x < enrollment.size(); x++){
                if(classrooms.get(i).getCensusYear() == enrollment.get(x).getCensusYear()
                        && classrooms.get(i).getDistrict() == enrollment.get(x).getDistrict()
                        && classrooms.get(i).getZone() == enrollment.get(x).getZone()){
                    Gaps temp = new Gaps();
                    temp.setCensusYear(classrooms.get(i).getCensusYear());
                    temp.setDistrict(classrooms.get(i).getDistrict());
                    temp.setZone(classrooms.get(i).getZone());
                    int difference = (classrooms.get(i).getNoOfClassrooms()*45) - (enrollment.get(x).getFemaleCount()+enrollment.get(x).getMaleCount());
                    temp.setGap(difference);
                }
            }
        }
        
        return gaps;
    }
    
}
