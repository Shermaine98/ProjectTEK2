/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.education;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Shermaine
 */
public class Enrollment {

    private int formID;
    private int censusYear;
    private int schoolID;
    private String SchoolName;
    private String district;
    private String SchoolType;
    private String classification;
    private int totalMale;
    private int totalFemale;
    private int grandTotal;
    private double genderDisparityIndex;

    private String uploadedBy;
    private String approvedBy;

    DecimalFormat df = new DecimalFormat("#,###");
    
    
    public String getFormatcount(int count) {
        String scount = df.format(count);
        return scount;
    }
    
    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }
    
    private ArrayList<EnrollmentDet> enrollmentDetArrayList = new ArrayList<EnrollmentDet>();

    public ArrayList<EnrollmentDet> getEnrollmentDetArrayList() {
        return enrollmentDetArrayList;
    }

    public void setEnrollmentDetArrayList(ArrayList<EnrollmentDet> enrollmentDetArrayList) {
        this.enrollmentDetArrayList = enrollmentDetArrayList;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSchoolType() {
        return SchoolType;
    }

    public void setSchoolType(String SchoolType) {
        this.SchoolType = SchoolType;
    }

    public int getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(int grandTotal) {
        this.grandTotal = grandTotal;
    }

    public double getGenderDisparityIndex() {
        return genderDisparityIndex;
    }

    public void setGenderDisparityIndex(double genderDisparityIndex) {
        this.genderDisparityIndex = genderDisparityIndex;
    }
    
    /**
     * @return the formID
     */
    public int getFormID() {
        return formID;
    }

    /**
     * @param formID the formID to set
     */
    public void setFormID(int formID) {
        this.formID = formID;
    }

    /**
     * @return the censusYear
     */
    public int getCensusYear() {
        return censusYear;
    }

    /**
     * @param censusYear the censusYear to set
     */
    public void setCensusYear(int censusYear) {
        this.censusYear = censusYear;
    }

    /**
     * @return the SchoolName
     */
    public String getSchoolName() {
        return SchoolName;
    }

    /**
     * @param SchoolName the SchoolName to set
     */
    public void setSchoolName(String SchoolName) {
        this.SchoolName = SchoolName;
    }

    /**
     * @return the classification
     */
    public String getClassification() {
        return classification;
    }

    /**
     * @param classification the classification to set
     */
    public void setClassification(String classification) {
        this.classification = classification;
    }

    /**
     * @return the totalMale
     */
    public int getTotalMale() {
        return totalMale;
    }

    /**
     * @param totalMale the totalMale to set
     */
    public void setTotalMale(int totalMale) {
        this.totalMale = totalMale;
    }

    /**
     * @return the totalFemale
     */
    public int getTotalFemale() {
        return totalFemale;
    }

    /**
     * @param totalFemale the totalFemale to set
     */
    public void setTotalFemale(int totalFemale) {
        this.totalFemale = totalFemale;
    }

    /**
     * @return the uploadedBy
     */
    public String getUploadedBy() {
        return uploadedBy;
    }

    /**
     * @param uploadedBy the uploadedBy to set
     */
    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    /**
     * @return the approvedBy
     */
    public String getApprovedBy() {
        return approvedBy;
    }

    /**
     * @param approvedBy the approvedBy to set
     */
    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }
}
