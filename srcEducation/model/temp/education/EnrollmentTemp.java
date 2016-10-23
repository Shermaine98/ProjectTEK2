/*
 *  Copyright (C) ProjectTEK - DLSU CCS 2016
 *  All right Reserved   * 
 */

package model.temp.education;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class EnrollmentTemp {
    
    private int formID;
    private int censusYear;
    private String schoolID;
    private String SchoolName;
    private String district;
    private String schoolType;
    private String classification;//public or private
    private String totalMale;
    private String totalFemale;
    private String grandTotal;
    private String genderDisparityIndex;
    private String uploadedBy;
    private String approvedBy;

    DecimalFormat df = new DecimalFormat("#,###");
    
    public String getFormatcount(int count) {
        String scount = df.format(count);
        return scount;
    }

    public String getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }
    
    private ArrayList<EnrollmentDetTemp> enrollmentDetArrayList;

    public EnrollmentTemp() {
        enrollmentDetArrayList = new ArrayList<EnrollmentDetTemp>();
        this.enrollmentDetArrayList = enrollmentDetArrayList;
    }

    public ArrayList<EnrollmentDetTemp> getEnrollmentDetArrayList() {
        return enrollmentDetArrayList;
    }

    public void setEnrollmentDetArrayList(ArrayList<EnrollmentDetTemp> enrollmentDetArrayList) {
        this.enrollmentDetArrayList = enrollmentDetArrayList;
    }
    
    public int getFormID() {
        return formID;
    }

    public void setFormID(int formID) {
        this.formID = formID;
    }

    public int getCensusYear() {
        return censusYear;
    }

    public void setCensusYear(int censusYear) {
        this.censusYear = censusYear;
    }

    public String getSchoolName() {
        return SchoolName;
    }

    public void setSchoolName(String SchoolName) {
        this.SchoolName = SchoolName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getTotalMale() {
        return totalMale;
    }

    public void setTotalMale(String totalMale) {
        this.totalMale = totalMale;
    }

    public String getTotalFemale() {
        return totalFemale;
    }

    public void setTotalFemale(String totalFemale) {
        this.totalFemale = totalFemale;
    }

    public String getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getGenderDisparityIndex() {
        return genderDisparityIndex;
    }

    public void setGenderDisparityIndex(String genderDisparityIndex) {
        this.genderDisparityIndex = genderDisparityIndex;
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
