/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */


package model.education;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class DirectorySchool {

    private int censusYear;
    private int formID;
    private int schoolID;
    private String schoolName;
    private String address;
    private String classification;
    private double latitude;
    private double longitude;
    private boolean active;
    private boolean alter;
    private ArrayList<ElemClassrooms> elemClassrooms;
    private ArrayList<Seats> seats;
    private ArrayList<ElemTeachers> teacher;

    private String uploadedBy;
    private String approvedBy;

    DecimalFormat df = new DecimalFormat("#,###");

    public DirectorySchool() {
        elemClassrooms = new ArrayList<ElemClassrooms>();
        seats = new ArrayList<Seats>();
        teacher = new ArrayList<ElemTeachers>();
        this.elemClassrooms = elemClassrooms;
        this.seats = seats;
        this.teacher = teacher;
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
     * @return the schoolID
     */
    public int getSchoolID() {
        return schoolID;
    }

    /**
     * @param schoolID the schoolID to set
     */
    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    /**
     * @return the schoolName
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * @param schoolName the schoolName to set
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     * @return the elemClassrooms
     */
    public ArrayList<ElemClassrooms> getElemClassrooms() {
        return elemClassrooms;
    }

    /**
     * @param elemClassrooms the elemClassrooms to set
     */
    public void setElemClassrooms(ArrayList<ElemClassrooms> elemClassrooms) {
        this.elemClassrooms = elemClassrooms;
    }

    /**
     * @return the seats
     */
    public ArrayList<Seats> getSeats() {
        return seats;
    }

    /**
     * @param seats the seats to set
     */
    public void setSeats(ArrayList<Seats> seats) {
        this.seats = seats;
    }

    /**
     * @return the teacher
     */
    public ArrayList<ElemTeachers> getTeacher() {
        return teacher;
    }

    /**
     * @param teacher the teacher to set
     */
    public void setTeacher(ArrayList<ElemTeachers> teacher) {
        this.teacher = teacher;
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
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public String getFormatcount(int count) {
        String scount = df.format(count);
        return scount;
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

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the validation
     */
    public boolean isAlter() {
        return alter;
    }

    /**
     * @param alter is true or false
     */
    public void setAlter(boolean alter) {
        this.alter = alter;
    }

}
