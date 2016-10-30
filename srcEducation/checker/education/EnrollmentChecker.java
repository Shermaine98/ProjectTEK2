/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */



package checker.education;

import model.education.EnrollmentDet;
import model.education.Enrollment;
import model.temp.education.EnrollmentDetTemp;
import model.temp.education.EnrollmentTemp;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */

public class EnrollmentChecker {

    private ArrayList<Enrollment> arrayNoError;
    private ArrayList<EnrollmentTemp> arrayError;

    /**
     *
     * @param arrError temp stored data, all strings
     */
    public EnrollmentChecker(ArrayList<EnrollmentTemp> arrError) {

        Enrollment enrollment;
        EnrollmentTemp enrollmentTemp;
        arrayNoError = new ArrayList<>();
        arrayError = new ArrayList<>();

        ArrayList<EnrollmentDet> arrEnrollmentDet;
        ArrayList<EnrollmentDetTemp> arrEnrollmentDetTemp;

        EnrollmentDetTemp enrollmentDetTemp;
        EnrollmentDet enrollmentDet;

        for (int i = 0; i < arrError.size(); i++) {
            enrollmentTemp = new EnrollmentTemp();
            arrEnrollmentDetTemp = new ArrayList<EnrollmentDetTemp>();
            arrEnrollmentDet = new ArrayList<EnrollmentDet>();

            enrollmentTemp.setSchoolID(arrError.get(i).getSchoolID());
            enrollmentTemp.setSchoolName(arrError.get(i).getSchoolName());
            enrollmentTemp.setDistrict(arrError.get(i).getDistrict());
            enrollmentTemp.setSchoolType(enrollmentTemp.getSchoolType());
         //   enrollmentTemp.setClassification(classification);
            enrollmentTemp.setTotalMale(String.valueOf(enrollmentTemp.getTotalMale()));
            enrollmentTemp.setTotalFemale(String.valueOf(enrollmentTemp.getTotalFemale()));
            enrollmentTemp.setGrandTotal(String.valueOf(enrollmentTemp.getGrandTotal()));
            enrollmentTemp.setGenderDisparityIndex(String.valueOf(enrollmentTemp.getGenderDisparityIndex()));

            for (int y = 0; y < arrError.get(i).getEnrollmentDetArrayList().size(); y++) {
                enrollmentDetTemp = new EnrollmentDetTemp();
                //if a field is null
                if (arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null
                        || arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel() == null
                        || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null
                        || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null) {

                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")) {
                        enrollmentDetTemp.setFemaleCount("-1");
                    } else {
                        enrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                    }

                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {
                        enrollmentDetTemp.setMaleCount("-1");
                    } else {
                        enrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                    }

                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel().equalsIgnoreCase("")) {
                        enrollmentDetTemp.setGradeLevel("-1");
                    } else {
                        enrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());
                    }

                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")) {
                        enrollmentDetTemp.setTotalCount("-1");
                    } else {
                        enrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                    }

                    enrollmentDetTemp.setValidation(false); //sets the validation to false
                    arrEnrollmentDetTemp.add(enrollmentDetTemp); //adds the normalized to the mother class
                    enrollmentTemp.setEnrollmentDetArrayList(arrEnrollmentDetTemp); //set
                    arrayError.add(enrollmentTemp);
                } //if a field is not numeric
                else if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                        || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                        || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                    if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())) {
                        enrollmentDetTemp.setFemaleCount("-1");
                    } else {
                        enrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                    }

                    if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {
                        enrollmentDetTemp.setMaleCount("-1");
                    } else {
                        enrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                    }

                    if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())) {
                        enrollmentDetTemp.setTotalCount("-1");
                    } else {
                        enrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                    }

                    enrollmentDetTemp.setValidation(false); //sets the validation to false
                    arrEnrollmentDetTemp.add(enrollmentDetTemp); //adds the normalized to the mother class
                    enrollmentTemp.setEnrollmentDetArrayList(arrEnrollmentDetTemp); //set
                    arrayError.add(enrollmentTemp);
                } else {

                    enrollmentDet = new EnrollmentDet();

                    enrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                    enrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                    enrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                    enrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());
                    enrollmentDet.setValidation(true);
                    arrEnrollmentDet.add(enrollmentDet);

                }
//                enrollment = new enrollment();
//                enrollment.setSchoolName(arrError.get(i).getSchoolName());
//                enrollment.setDistrict(arrError.get(i).getDistrict());
//                enrollment.setSchoolType(arrError.get(i).getSchoolType());
//                System.out.println(arrError.get(i).getSchoolName() +" " + arrError.get(i).getDistrict());
//               // enrollment.setClassification(classification);
//                System.out.println(arrError.get(i).getTotalMale());
//                enrollment.setTotalMale(Integer.parseInt(arrError.get(i).getTotalMale()));
//                enrollment.setTotalFemale(Integer.parseInt(arrError.get(i).getTotalFemale()));
//                enrollment.setGrandTotal(Integer.parseInt(arrError.get(i).getGrandTotal()));
//                enrollment.setGenderDisparityIndex(Double.parseDouble(arrError.get(i).getGenderDisparityIndex()));
//          
//                enrollment.setEnrollmentDetArrayList(arrEnrollmentDet);
//                arrayNoError.add(enrollment);
            }
            enrollment = new Enrollment();
            enrollment.setSchoolName(arrError.get(i).getSchoolName());
            enrollment.setDistrict(arrError.get(i).getDistrict());
            enrollment.setSchoolType(arrError.get(i).getSchoolType());
            System.out.println(arrError.get(i).getSchoolName() +" " + arrError.get(i).getDistrict());
           // enrollment.setClassification(classification);
            System.out.println(arrError.get(i).getTotalMale());
            enrollment.setTotalMale(Integer.parseInt(arrError.get(i).getTotalMale()));
            enrollment.setTotalFemale(Integer.parseInt(arrError.get(i).getTotalFemale()));
            enrollment.setGrandTotal(Integer.parseInt(arrError.get(i).getGrandTotal()));
            enrollment.setGenderDisparityIndex(Double.parseDouble(arrError.get(i).getGenderDisparityIndex()));

            enrollment.setEnrollmentDetArrayList(arrEnrollmentDet);
            arrayNoError.add(enrollment);
        }
    }

    /**
     *
     * @param arrError temp stored data, all strings, @param classification -
     * public or private
     * @param year year
     * @param formID form id
     */
    public EnrollmentChecker(ArrayList<EnrollmentTemp> arrError, int year, int formID) {

        Enrollment enrollment;
        EnrollmentTemp enrollmentTemp;
        arrayNoError = new ArrayList<>();
        arrayError = new ArrayList<>();

        ArrayList<EnrollmentDet> arrEnrollmentDet;
        ArrayList<EnrollmentDetTemp> arrEnrollmentDetTemp;

        EnrollmentDetTemp enrollmentDetTemp;
        EnrollmentDet enrollmentDet;

        for (int i = 0; i < arrError.size(); i++) {
            enrollmentTemp = new EnrollmentTemp();
            arrEnrollmentDetTemp = new ArrayList<EnrollmentDetTemp>();
            arrEnrollmentDet = new ArrayList<EnrollmentDet>();

            enrollmentTemp.setSchoolID(arrError.get(i).getSchoolID());
            enrollmentTemp.setSchoolName(arrError.get(i).getSchoolName());
            enrollmentTemp.setDistrict(arrError.get(i).getDistrict());
            enrollmentTemp.setSchoolType(enrollmentTemp.getSchoolType());
            enrollmentTemp.setClassification(enrollmentTemp.getClassification());
            enrollmentTemp.setTotalMale(String.valueOf(enrollmentTemp.getTotalMale()));
            enrollmentTemp.setTotalFemale(String.valueOf(enrollmentTemp.getTotalFemale()));
            enrollmentTemp.setGrandTotal(String.valueOf(enrollmentTemp.getGrandTotal()));
            enrollmentTemp.setGenderDisparityIndex(String.valueOf(enrollmentTemp.getGenderDisparityIndex()));

            for (int y = 0; y < arrError.get(i).getEnrollmentDetArrayList().size(); y++) {
                enrollmentDetTemp = new EnrollmentDetTemp();
                //if a field is null
                if (arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null
                        || arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel() == null
                        || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null
                        || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null) {

                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")) {
                        enrollmentDetTemp.setFemaleCount("-1");
                    } else {
                        enrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                    }

                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {
                        enrollmentDetTemp.setMaleCount("-1");
                    } else {
                        enrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                    }

                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel().equalsIgnoreCase("")) {
                        enrollmentDetTemp.setGradeLevel("-1");
                    } else {
                        enrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());
                    }

                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")) {
                        enrollmentDetTemp.setTotalCount("-1");
                    } else {
                        enrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                    }

                    enrollmentDetTemp.setValidation(false); //sets the validation to false
                    arrEnrollmentDetTemp.add(enrollmentDetTemp); //adds the normalized to the mother class
                    enrollmentTemp.setEnrollmentDetArrayList(arrEnrollmentDetTemp); //set
                    arrayError.add(enrollmentTemp);
                } //if a field is not numeric
                else if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                        || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                        || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                    if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())) {
                        enrollmentDetTemp.setFemaleCount("-1");
                    } else {
                        enrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                    }

                    if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {
                        enrollmentDetTemp.setMaleCount("-1");
                    } else {
                        enrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                    }

                    if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())) {
                        enrollmentDetTemp.setTotalCount("-1");
                    } else {
                        enrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                    }

                    enrollmentDetTemp.setValidation(false); //sets the validation to false
                    arrEnrollmentDetTemp.add(enrollmentDetTemp); //adds the normalized to the mother class
                    enrollmentTemp.setEnrollmentDetArrayList(arrEnrollmentDetTemp); //set
                    arrayError.add(enrollmentTemp);
                } else {

                    enrollmentDet = new EnrollmentDet();

                    enrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                    enrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                    enrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                    enrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());
                    enrollmentDet.setValidation(true);
                    arrEnrollmentDetTemp.add(enrollmentDetTemp);

                }
                enrollment = new Enrollment();
                enrollment.setSchoolName(arrError.get(i).getSchoolName());
                enrollment.setDistrict(arrError.get(i).getDistrict());
                enrollment.setSchoolType(enrollmentTemp.getSchoolType());
                enrollment.setClassification(enrollmentTemp.getClassification());
                enrollment.setTotalMale(Integer.parseInt(enrollmentTemp.getTotalMale()));
                enrollment.setTotalFemale(Integer.parseInt(enrollmentTemp.getTotalFemale()));
                enrollment.setGrandTotal(Integer.parseInt(enrollmentTemp.getGrandTotal()));
                enrollment.setGenderDisparityIndex(Double.parseDouble(enrollmentTemp.getGenderDisparityIndex()));
                enrollment.setEnrollmentDetArrayList(arrEnrollmentDet);
                arrayNoError.add(enrollment);
            }
        }
    }

    public EnrollmentChecker() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Enrollment> transformData(ArrayList<EnrollmentTemp> arrError) {
        Enrollment enrollment;
        ArrayList<EnrollmentDet> arrEnrollmentDet;
        ArrayList<Enrollment> transformData = new ArrayList<>();
        EnrollmentDet enrollmentDet;

        for (int i = 0; i < arrError.size(); i++) {
            enrollment = new Enrollment();
            arrEnrollmentDet = new ArrayList<EnrollmentDet>();
            for (int y = 0; y < arrError.get(i).getEnrollmentDetArrayList().size(); y++) {
                enrollmentDet = new EnrollmentDet();
                enrollment.setCensusYear(arrError.get(i).getCensusYear());
                enrollment.setFormID(arrError.get(i).getFormID());
                enrollment.setSchoolID(Integer.parseInt(arrError.get(i).getSchoolID()));
                enrollment.setSchoolName(arrError.get(i).getSchoolName());
                enrollment.setDistrict(arrError.get(i).getDistrict());
                enrollment.setSchoolType(arrError.get(i).getSchoolType());
                enrollment.setClassification(arrError.get(i).getClassification());
                enrollment.setTotalMale(Integer.parseInt(arrError.get(i).getTotalMale()));
                enrollment.setTotalFemale(Integer.parseInt(arrError.get(i).getTotalFemale()));
                enrollment.setGrandTotal(Integer.parseInt(arrError.get(i).getGrandTotal()));
                enrollment.setGenderDisparityIndex(Integer.parseInt(arrError.get(i).getGenderDisparityIndex()));

                if (arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null
                        || arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel() == null
                        || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null
                        || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null
                        || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")
                        || arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel().equalsIgnoreCase("")
                        || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")
                        || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")) {

                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")) {
                        enrollmentDet.setFemaleCount(-1);
                    } else {
                        enrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                    }

                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {
                        enrollmentDet.setMaleCount(-1);
                    } else {
                        enrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                    }

                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel().equalsIgnoreCase("")) {
                        enrollmentDet.setGradeLevel("-1");
                    } else {
                        enrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());
                    }

                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")) {
                        enrollmentDet.setTotalCount(-1);
                    } else {
                        enrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                    }

                    enrollmentDet.setValidation(false); //sets the validation to false
                    arrEnrollmentDet.add(enrollmentDet); //adds the normalized to the mother class
                    enrollment.setEnrollmentDetArrayList(arrEnrollmentDet); //set
                    transformData.add(enrollment);
                } else if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                        || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                        || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                    if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())) {
                        enrollmentDet.setFemaleCount(-1);
                    } else {
                        enrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                    }

                    if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {
                        enrollmentDet.setMaleCount(-1);
                    } else {
                        enrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                    }

                    if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())) {
                        enrollmentDet.setTotalCount(-1);
                    } else {
                        enrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                    }

                    enrollmentDet.setValidation(false); //sets the validation to false
                    arrEnrollmentDet.add(enrollmentDet); //adds the normalized to the mother class
                    enrollment.setEnrollmentDetArrayList(arrEnrollmentDet); //set
                    transformData.add(enrollment);
                }
            }
        }
        return transformData;
    }

    public static boolean isNumeric(String str) {
        return !StringUtils.isNumeric(str);
    }


    public ArrayList<Enrollment> getArrayNoError() {
        return arrayNoError;
    }

    public void setArrayNoError(ArrayList<Enrollment> arrayNoError) {
        this.arrayNoError = arrayNoError;
    }

    public ArrayList<EnrollmentTemp> getArrayError() {
        return arrayError;
    }

    public void setArrayError(ArrayList<EnrollmentTemp> arrayError) {
        this.arrayError = arrayError;
    }

}
