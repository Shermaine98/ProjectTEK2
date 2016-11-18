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

        for (int i = 0; i < arrError.size(); i++) {
            if (arrError.get(i).getTotalFemale() == null
                    || arrError.get(i).getTotalMale() == null
                    || arrError.get(i).getGrandTotal() == null
                    || arrError.get(i).getGenderDisparityIndex() == null
                    || arrError.get(i).getTotalFemale().equalsIgnoreCase("")
                    || arrError.get(i).getTotalMale().equalsIgnoreCase("")
                    || arrError.get(i).getGrandTotal().equalsIgnoreCase("")
                    || arrError.get(i).getGenderDisparityIndex().equalsIgnoreCase("")) {

                enrollmentTemp = new EnrollmentTemp();
                //SET OTHER STUFF
              //  enrollmentTemp.setSchoolID(arrError.get(i).getSchoolID());
                enrollmentTemp.setSchoolName(arrError.get(i).getSchoolName());
                enrollmentTemp.setDistrict(arrError.get(i).getDistrict());
                enrollmentTemp.setSchoolType(arrError.get(i).getSchoolType());

                //END SET OTHER STUFF
                if (arrError.get(i).getTotalFemale() == null || arrError.get(i).getTotalFemale().equalsIgnoreCase("")) {
                    enrollmentTemp.setTotalFemale("");

                } else {
                    enrollmentTemp.setTotalFemale(arrError.get(i).getTotalFemale());
                }

                if (arrError.get(i).getTotalMale() == null || arrError.get(i).getTotalMale().equalsIgnoreCase("")) {
                    enrollmentTemp.setTotalMale("");

                } else {
                    enrollmentTemp.setTotalMale(arrError.get(i).getTotalMale());
                }

                if (arrError.get(i).getGrandTotal() == null || arrError.get(i).getGrandTotal().equalsIgnoreCase("")) {
                    enrollmentTemp.setGrandTotal("");
                } else {
                    enrollmentTemp.setGrandTotal(arrError.get(i).getGrandTotal());
                }

                if (arrError.get(i).getGenderDisparityIndex() == null || arrError.get(i).getGenderDisparityIndex().equalsIgnoreCase("")) {

                    enrollmentTemp.setGenderDisparityIndex("");
                } else {

                    enrollmentTemp.setGenderDisparityIndex(arrError.get(i).getGenderDisparityIndex());
                }
                enrollmentTemp.setValidation(-1);
                enrollmentTemp.setReason("Missing Field/s");
                arrEnrollmentDetTemp = new ArrayList<EnrollmentDetTemp>();
                for (int y = 0; y < arrError.get(i).getEnrollmentDetArrayList().size(); y++) {

                    EnrollmentDetTemp EnrollmentDetTemp = new EnrollmentDetTemp();
                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")) {
                            EnrollmentDetTemp.setTotalCount("");
                        } else {
                            EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        }

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")) {
                            EnrollmentDetTemp.setFemaleCount("");

                        } else {
                            EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        }

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {
                            EnrollmentDetTemp.setMaleCount("");
                        } else {
                            EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        }
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());
                        EnrollmentDetTemp.setValidation(-1);
                        EnrollmentDetTemp.setReason("Missing Field/s");
                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                    } else if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())) {
                            EnrollmentDetTemp.setTotalCount("");
                        } else {
                            EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        }

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())) {
                            EnrollmentDetTemp.setFemaleCount("");

                        } else {
                            EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        }

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {
                            EnrollmentDetTemp.setMaleCount("");
                        } else {
                            EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        }

                        EnrollmentDetTemp.setValidation(-2);
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());
                        EnrollmentDetTemp.setReason("Format Error");
                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);

                    } else if (Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            != Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            + Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        EnrollmentDetTemp.setValidation(-3);
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDetTemp.setReason("Summation Error");
                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                    } else {
                        EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        EnrollmentDetTemp.setValidation(1);
                        EnrollmentDetTemp.setReason("");
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                    }

                }

                enrollmentTemp.setEnrollmentDetArrayList(arrEnrollmentDetTemp);
                arrayError.add(enrollmentTemp);
            } else if (isNumeric(arrError.get(i).getTotalFemale())
                    || isNumeric(arrError.get(i).getTotalMale())
                    || isNumeric(arrError.get(i).getGrandTotal())
                    ) {

                //
                enrollmentTemp = new EnrollmentTemp();
                //SET OTHER STUFF
              //  enrollmentTemp.setSchoolID(arrError.get(i).getSchoolID());
                enrollmentTemp.setSchoolName(arrError.get(i).getSchoolName());
                enrollmentTemp.setDistrict(arrError.get(i).getDistrict());
                enrollmentTemp.setSchoolType(arrError.get(i).getSchoolType());

                //END SET OTHER STUFF
                //
                if (isNumeric(arrError.get(i).getTotalFemale())) {
                    enrollmentTemp.setTotalFemale("");

                } else {
                    enrollmentTemp.setTotalFemale(arrError.get(i).getTotalFemale());
                }

                if (isNumeric(arrError.get(i).getTotalMale())) {
                    enrollmentTemp.setTotalMale("");

                } else {
                    enrollmentTemp.setTotalMale(arrError.get(i).getTotalMale());
                }

                if (isNumeric(arrError.get(i).getGrandTotal())) {
                    enrollmentTemp.setGrandTotal("");
                } else {
                    enrollmentTemp.setGrandTotal(arrError.get(i).getGrandTotal());
                }

       

                    enrollmentTemp.setGenderDisparityIndex(arrError.get(i).getGenderDisparityIndex());
                
                enrollmentTemp.setValidation(-2);
                enrollmentTemp.setReason("Format Error/s");
                arrEnrollmentDetTemp = new ArrayList<EnrollmentDetTemp>();
                for (int y = 0; y < arrError.get(i).getEnrollmentDetArrayList().size(); y++) {

                    EnrollmentDetTemp EnrollmentDetTemp = new EnrollmentDetTemp();
                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")) {
                            EnrollmentDetTemp.setTotalCount("");
                        } else {
                            EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        }

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")) {
                            EnrollmentDetTemp.setFemaleCount("");

                        } else {
                            EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        }

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {
                            EnrollmentDetTemp.setMaleCount("");
                        } else {
                            EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        }
                        EnrollmentDetTemp.setValidation(-1);
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDetTemp.setReason("Missing Field/s");
                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                    } else if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())) {
                            EnrollmentDetTemp.setTotalCount("");
                        } else {
                            EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        }

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())) {
                            EnrollmentDetTemp.setFemaleCount("");

                        } else {
                            EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        }

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {
                            EnrollmentDetTemp.setMaleCount("");
                        } else {
                            EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        }
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDetTemp.setValidation(-2);
                        EnrollmentDetTemp.setReason("Format Error");
                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);

                    } else if (Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            != Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            + Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        EnrollmentDetTemp.setValidation(-3);
                        EnrollmentDetTemp.setReason("Summation Error");
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);

                    } else {
                        EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        EnrollmentDetTemp.setValidation(1);
                        EnrollmentDetTemp.setReason("");
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                    }

                }

                enrollmentTemp.setEnrollmentDetArrayList(arrEnrollmentDetTemp);
                arrayError.add(enrollmentTemp);
            } else if (Integer.parseInt(arrError.get(i).getGrandTotal())
                    != Integer.parseInt(arrError.get(i).getTotalMale())
                    + Integer.parseInt(arrError.get(i).getTotalFemale())) {

                //
                enrollmentTemp = new EnrollmentTemp();
                //SET OTHER STUFF
               // enrollmentTemp.setSchoolID(arrError.get(i).getSchoolID());
                enrollmentTemp.setSchoolName(arrError.get(i).getSchoolName());
                enrollmentTemp.setDistrict(arrError.get(i).getDistrict());
                enrollmentTemp.setSchoolType(arrError.get(i).getSchoolType());

                //END SET OTHER STUFF
                //
                enrollmentTemp.setTotalFemale(arrError.get(i).getTotalFemale());
                enrollmentTemp.setTotalMale(arrError.get(i).getTotalMale());
                enrollmentTemp.setGrandTotal(arrError.get(i).getGrandTotal());
                enrollmentTemp.setGenderDisparityIndex(arrError.get(i).getGenderDisparityIndex());

                enrollmentTemp.setValidation(-3);
                enrollmentTemp.setReason("Summation Error");
                arrEnrollmentDetTemp = new ArrayList<EnrollmentDetTemp>();
                for (int y = 0; y < arrError.get(i).getEnrollmentDetArrayList().size(); y++) {

                    EnrollmentDetTemp EnrollmentDetTemp = new EnrollmentDetTemp();
                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")) {
                            EnrollmentDetTemp.setTotalCount("");
                        } else {
                            EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        }

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")) {
                            EnrollmentDetTemp.setFemaleCount("");

                        } else {
                            EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        }

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {
                            EnrollmentDetTemp.setMaleCount("");
                        } else {
                            EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        }
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDetTemp.setValidation(-1);
                        EnrollmentDetTemp.setReason("Missing Field/s");
                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                    } else if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())) {
                            EnrollmentDetTemp.setTotalCount("");
                        } else {
                            EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        }

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())) {
                            EnrollmentDetTemp.setFemaleCount("");

                        } else {
                            EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        }

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {
                            EnrollmentDetTemp.setMaleCount("");
                        } else {
                            EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        }
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDetTemp.setValidation(-2);
                        EnrollmentDetTemp.setReason("Format Error");
                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);

                    } else if (Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            != Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            + Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        EnrollmentDetTemp.setValidation(-3);
                        EnrollmentDetTemp.setReason("Summation Error");
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                    } else {
                        EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        EnrollmentDetTemp.setValidation(1);
                        EnrollmentDetTemp.setReason("");
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                    }

                }

                enrollmentTemp.setEnrollmentDetArrayList(arrEnrollmentDetTemp);
                arrayError.add(enrollmentTemp);
            } else if (Double.parseDouble(String.format("%.2f", Float.parseFloat(arrError.get(i).getGenderDisparityIndex())))
                    != Double.parseDouble(String.format("%.2f", Float.parseFloat(arrError.get(i).getTotalFemale())
                    / Float.parseFloat(arrError.get(i).getTotalMale())))) {
                //
                enrollmentTemp = new EnrollmentTemp();
                //SET OTHER STUFF
               // enrollmentTemp.setSchoolID(arrError.get(i).getSchoolID());
                enrollmentTemp.setSchoolName(arrError.get(i).getSchoolName());
                enrollmentTemp.setDistrict(arrError.get(i).getDistrict());
                enrollmentTemp.setSchoolType(arrError.get(i).getSchoolType());

                //END SET OTHER STUFF
                //
                enrollmentTemp.setTotalFemale(arrError.get(i).getTotalFemale());
                enrollmentTemp.setTotalMale(arrError.get(i).getTotalMale());
                enrollmentTemp.setGrandTotal(arrError.get(i).getGrandTotal());
                enrollmentTemp.setGenderDisparityIndex(arrError.get(i).getGenderDisparityIndex());

                enrollmentTemp.setValidation(-4);
                enrollmentTemp.setReason("Quotient Error");
                arrEnrollmentDetTemp = new ArrayList<EnrollmentDetTemp>();
                for (int y = 0; y < arrError.get(i).getEnrollmentDetArrayList().size(); y++) {

                    EnrollmentDetTemp EnrollmentDetTemp = new EnrollmentDetTemp();
                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")) {
                            EnrollmentDetTemp.setTotalCount("");
                        } else {
                            EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        }

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")) {
                            EnrollmentDetTemp.setFemaleCount("");

                        } else {
                            EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        }

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {
                            EnrollmentDetTemp.setMaleCount("");
                        } else {
                            EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        }
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDetTemp.setValidation(-1);
                        EnrollmentDetTemp.setReason("Missing Field/s");
                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                    } else if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())) {
                            EnrollmentDetTemp.setTotalCount("");
                        } else {
                            EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        }

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())) {
                            EnrollmentDetTemp.setFemaleCount("");

                        } else {
                            EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        }

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {
                            EnrollmentDetTemp.setMaleCount("");
                        } else {
                            EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        }
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDetTemp.setValidation(-2);
                        EnrollmentDetTemp.setReason("Format Error");
                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);

                    } else if (Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            != Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            + Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        EnrollmentDetTemp.setValidation(-3);
                        EnrollmentDetTemp.setReason("Summation Error");
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                    } else {
                        EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        EnrollmentDetTemp.setValidation(1);
                        EnrollmentDetTemp.setReason("");
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                    }

                }

                enrollmentTemp.setEnrollmentDetArrayList(arrEnrollmentDetTemp);
                arrayError.add(enrollmentTemp);

            } else {

                boolean x = false;

                for (int y = 0; y < arrError.get(i).getEnrollmentDetArrayList().size(); y++) {
                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {

                        x = true;

                    } else if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        x = true;
                    } else if (Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            != Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            + Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        x = true;
                    }
                }

                // fix laman here
                if (x) {
                    //
                    enrollmentTemp = new EnrollmentTemp();
                    //SET OTHER STUFF
                   // enrollmentTemp.setSchoolID(arrError.get(i).getSchoolID());
                    enrollmentTemp.setSchoolName(arrError.get(i).getSchoolName());
                    enrollmentTemp.setDistrict(arrError.get(i).getDistrict());
                    enrollmentTemp.setSchoolType(arrError.get(i).getSchoolType());

                    //END SET OTHER STUFF
                    //
                    enrollmentTemp.setTotalFemale(arrError.get(i).getTotalFemale());
                    enrollmentTemp.setTotalMale(arrError.get(i).getTotalMale());
                    enrollmentTemp.setGrandTotal(arrError.get(i).getGrandTotal());
                    enrollmentTemp.setGenderDisparityIndex(arrError.get(i).getGenderDisparityIndex());
                    enrollmentTemp.setValidation(1);
                    enrollmentTemp.setReason("");

                    arrEnrollmentDetTemp = new ArrayList<EnrollmentDetTemp>();
                    for (int y = 0; y < arrError.get(i).getEnrollmentDetArrayList().size(); y++) {

                        EnrollmentDetTemp EnrollmentDetTemp = new EnrollmentDetTemp();
                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null
                                || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null
                                || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null
                                || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")
                                || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")
                                || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {

                            if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")) {
                                EnrollmentDetTemp.setTotalCount("");
                            } else {
                                EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                            }

                            if (arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")) {
                                EnrollmentDetTemp.setFemaleCount("");

                            } else {
                                EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                            }

                            if (arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {
                                EnrollmentDetTemp.setMaleCount("");
                            } else {
                                EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                            }
                            EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                            EnrollmentDetTemp.setValidation(-1);
                            EnrollmentDetTemp.setReason("Missing Field/s");
                            arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                        } else if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                                || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                                || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                            if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())) {
                                EnrollmentDetTemp.setTotalCount("");
                            } else {
                                EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                            }

                            if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())) {
                                EnrollmentDetTemp.setFemaleCount("");

                            } else {
                                EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                            }

                            if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {
                                EnrollmentDetTemp.setMaleCount("");
                            } else {
                                EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                            }
                            EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                            EnrollmentDetTemp.setValidation(-2);
                            EnrollmentDetTemp.setReason("Format Error");
                            arrEnrollmentDetTemp.add(EnrollmentDetTemp);

                        } else if (Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                                != Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                                + Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                            EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                            EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                            EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                            EnrollmentDetTemp.setValidation(-3);
                            EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                            EnrollmentDetTemp.setReason("Summation Error");
                            arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                        } else {
                            EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                            EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                            EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                            EnrollmentDetTemp.setValidation(1);
                            EnrollmentDetTemp.setReason("");
                            EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                            arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                        }

                    }

                    enrollmentTemp.setEnrollmentDetArrayList(arrEnrollmentDetTemp);
                    arrayError.add(enrollmentTemp);
                } else {
                    enrollment = new Enrollment();

                    // set stuff
                //    enrollment.setSchoolID(Integer.parseInt(arrError.get(i).getSchoolID()));
                    enrollment.setSchoolName(arrError.get(i).getSchoolName());
                    enrollment.setDistrict(arrError.get(i).getDistrict());
                    enrollment.setSchoolType(arrError.get(i).getSchoolType());


                    //END SET OTHER STUFF
                    //
                    enrollment.setTotalFemale(Integer.parseInt(arrError.get(i).getTotalFemale()));
                    enrollment.setTotalMale(Integer.parseInt(arrError.get(i).getTotalMale()));
                    enrollment.setGrandTotal(Integer.parseInt(arrError.get(i).getGrandTotal()));
                    enrollment.setGenderDisparityIndex(Double.parseDouble(arrError.get(i).getGenderDisparityIndex()));
                    enrollment.setValidation(1);
                    enrollment.setReason("");
                    arrEnrollmentDet = new ArrayList<EnrollmentDet>();
                    for (int y = 0; y < arrError.get(i).getEnrollmentDetArrayList().size(); y++) {
                        EnrollmentDet EnrollmentDet = new EnrollmentDet();
                        EnrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                        EnrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                        EnrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                        EnrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());
                        EnrollmentDet.setValidation(1);
                        EnrollmentDet.setReason("");
                        arrEnrollmentDet.add(EnrollmentDet);
                    }
                    enrollment.setEnrollmentDetArrayList(arrEnrollmentDet);
                    arrayNoError.add(enrollment);
                }

            }

        }
    }

    /**
     *
     * @param arrError temp stored data, all strings, @param classification -
     * public or private
     * @param year year
     * @param formID form id
     * @param classification
     */
    public EnrollmentChecker(ArrayList<EnrollmentTemp> arrError, int year, int formID, String classification) {

        Enrollment enrollment;
        EnrollmentTemp enrollmentTemp;
        arrayNoError = new ArrayList<>();
        arrayError = new ArrayList<>();

        ArrayList<EnrollmentDet> arrEnrollmentDet;
        ArrayList<EnrollmentDetTemp> arrEnrollmentDetTemp;

        for (int i = 0; i < arrError.size(); i++) {
            if (arrError.get(i).getTotalFemale() == null
                    || arrError.get(i).getTotalMale() == null
                    || arrError.get(i).getGrandTotal() == null
                    || arrError.get(i).getGenderDisparityIndex() == null
                    || arrError.get(i).getTotalFemale().equalsIgnoreCase("")
                    || arrError.get(i).getTotalMale().equalsIgnoreCase("")
                    || arrError.get(i).getGrandTotal().equalsIgnoreCase("")
                    || arrError.get(i).getGenderDisparityIndex().equalsIgnoreCase("")) {

                enrollmentTemp = new EnrollmentTemp();
                //SET OTHER STUFF

                enrollmentTemp.setFormID(formID);
                enrollmentTemp.setCensusYear(year);
                enrollmentTemp.setClassification(classification);
             //   enrollmentTemp.setSchoolID(arrError.get(i).getSchoolID());
                enrollmentTemp.setSchoolName(arrError.get(i).getSchoolName());
                enrollmentTemp.setDistrict(arrError.get(i).getDistrict());
                enrollmentTemp.setSchoolType(arrError.get(i).getSchoolType());

                //END SET OTHER STUFF
                if (arrError.get(i).getTotalFemale() == null || arrError.get(i).getTotalFemale().equalsIgnoreCase("")) {
                    enrollmentTemp.setTotalFemale("");

                } else {
                    enrollmentTemp.setTotalFemale(arrError.get(i).getTotalFemale());
                }

                if (arrError.get(i).getTotalMale() == null || arrError.get(i).getTotalMale().equalsIgnoreCase("")) {
                    enrollmentTemp.setTotalMale("");

                } else {
                    enrollmentTemp.setTotalMale(arrError.get(i).getTotalMale());
                }

                if (arrError.get(i).getGrandTotal() == null || arrError.get(i).getGrandTotal().equalsIgnoreCase("")) {
                    enrollmentTemp.setGrandTotal("");
                } else {
                    enrollmentTemp.setGrandTotal(arrError.get(i).getGrandTotal());
                }

                if (arrError.get(i).getGenderDisparityIndex() == null || arrError.get(i).getGenderDisparityIndex().equalsIgnoreCase("")) {

                    enrollmentTemp.setGenderDisparityIndex("");
                } else {

                    enrollmentTemp.setGenderDisparityIndex(arrError.get(i).getGenderDisparityIndex());
                }

                enrollmentTemp.setValidation(-1);
                enrollmentTemp.setReason("Missing Field/s");
                arrEnrollmentDetTemp = new ArrayList<EnrollmentDetTemp>();
                for (int y = 0; y < arrError.get(i).getEnrollmentDetArrayList().size(); y++) {

                    EnrollmentDetTemp EnrollmentDetTemp = new EnrollmentDetTemp();
                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")) {
                            EnrollmentDetTemp.setTotalCount("");
                        } else {
                            EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        }

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")) {
                            EnrollmentDetTemp.setFemaleCount("");

                        } else {
                            EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        }

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {
                            EnrollmentDetTemp.setMaleCount("");
                        } else {
                            EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        }
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDetTemp.setValidation(-1);
                        EnrollmentDetTemp.setReason("Missing Field/s");
                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                    } else if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())) {
                            EnrollmentDetTemp.setTotalCount("");
                        } else {
                            EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        }

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())) {
                            EnrollmentDetTemp.setFemaleCount("");

                        } else {
                            EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        }

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {
                            EnrollmentDetTemp.setMaleCount("");
                        } else {
                            EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        }
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDetTemp.setValidation(-2);
                        EnrollmentDetTemp.setReason("Format Error");
                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);

                    } else if (Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            != Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            + Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        EnrollmentDetTemp.setValidation(-3);
                        EnrollmentDetTemp.setReason("Summation Error");
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                    } else {
                        EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        EnrollmentDetTemp.setValidation(1);
                        EnrollmentDetTemp.setReason("");
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                    }

                }

                enrollmentTemp.setEnrollmentDetArrayList(arrEnrollmentDetTemp);
                arrayError.add(enrollmentTemp);
            } else if (isNumeric(arrError.get(i).getTotalFemale())
                    || isNumeric(arrError.get(i).getTotalMale())
                    || isNumeric(arrError.get(i).getGrandTotal())
                    ) {

                //
                enrollmentTemp = new EnrollmentTemp();
                //SET OTHER STUFF

                enrollmentTemp.setFormID(formID);
                enrollmentTemp.setCensusYear(year);
                enrollmentTemp.setClassification(classification);
               // enrollmentTemp.setSchoolID(arrError.get(i).getSchoolID());
                enrollmentTemp.setSchoolName(arrError.get(i).getSchoolName());
                enrollmentTemp.setDistrict(arrError.get(i).getDistrict());
                enrollmentTemp.setSchoolType(arrError.get(i).getSchoolType());

                //END SET OTHER STUFF
                //
                if (isNumeric(arrError.get(i).getTotalFemale())) {
                    enrollmentTemp.setTotalFemale("");

                } else {
                    enrollmentTemp.setTotalFemale(arrError.get(i).getTotalFemale());
                }

                if (isNumeric(arrError.get(i).getTotalMale())) {
                    enrollmentTemp.setTotalMale("");

                } else {
                    enrollmentTemp.setTotalMale(arrError.get(i).getTotalMale());
                }

                if (isNumeric(arrError.get(i).getGrandTotal())) {
                    enrollmentTemp.setGrandTotal("");
                } else {
                    enrollmentTemp.setGrandTotal(arrError.get(i).getGrandTotal());
                }

        
                    enrollmentTemp.setGenderDisparityIndex(arrError.get(i).getGenderDisparityIndex());
      

                enrollmentTemp.setValidation(-2);
                enrollmentTemp.setReason("Format Error/s");
                arrEnrollmentDetTemp = new ArrayList<EnrollmentDetTemp>();
                for (int y = 0; y < arrError.get(i).getEnrollmentDetArrayList().size(); y++) {

                    EnrollmentDetTemp EnrollmentDetTemp = new EnrollmentDetTemp();
                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")) {
                            EnrollmentDetTemp.setTotalCount("");
                        } else {
                            EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        }

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")) {
                            EnrollmentDetTemp.setFemaleCount("");

                        } else {
                            EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        }

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {
                            EnrollmentDetTemp.setMaleCount("");
                        } else {
                            EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        }

                        EnrollmentDetTemp.setValidation(-1);
                        EnrollmentDetTemp.setReason("Missing Field/s");
                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                    } else if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())) {
                            EnrollmentDetTemp.setTotalCount("");
                        } else {
                            EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        }

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())) {
                            EnrollmentDetTemp.setFemaleCount("");

                        } else {
                            EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        }

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {
                            EnrollmentDetTemp.setMaleCount("");
                        } else {
                            EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        }
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDetTemp.setValidation(-2);
                        EnrollmentDetTemp.setReason("Format Error");
                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);

                    } else if (Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            != Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            + Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        EnrollmentDetTemp.setValidation(-3);
                        EnrollmentDetTemp.setReason("Summation Error");
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                    } else {
                        EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        EnrollmentDetTemp.setValidation(1);
                        EnrollmentDetTemp.setReason("");
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                    }

                }

                enrollmentTemp.setEnrollmentDetArrayList(arrEnrollmentDetTemp);
                arrayError.add(enrollmentTemp);
            } else if (Integer.parseInt(arrError.get(i).getGrandTotal())
                    != Integer.parseInt(arrError.get(i).getTotalMale())
                    + Integer.parseInt(arrError.get(i).getTotalFemale())) {

                //
                enrollmentTemp = new EnrollmentTemp();
                //SET OTHER STUFF

                enrollmentTemp.setFormID(formID);
                enrollmentTemp.setCensusYear(year);
                enrollmentTemp.setClassification(classification);
               // enrollmentTemp.setSchoolID(arrError.get(i).getSchoolID());
                enrollmentTemp.setSchoolName(arrError.get(i).getSchoolName());
                enrollmentTemp.setDistrict(arrError.get(i).getDistrict());
                enrollmentTemp.setSchoolType(arrError.get(i).getSchoolType());

                //END SET OTHER STUFF
                //
                enrollmentTemp.setTotalFemale(arrError.get(i).getTotalFemale());
                enrollmentTemp.setTotalMale(arrError.get(i).getTotalMale());
                enrollmentTemp.setGrandTotal(arrError.get(i).getGrandTotal());
                enrollmentTemp.setGenderDisparityIndex(arrError.get(i).getGenderDisparityIndex());

                enrollmentTemp.setValidation(-3);
                enrollmentTemp.setReason("Summation Error");
                arrEnrollmentDetTemp = new ArrayList<EnrollmentDetTemp>();
                for (int y = 0; y < arrError.get(i).getEnrollmentDetArrayList().size(); y++) {

                    EnrollmentDetTemp EnrollmentDetTemp = new EnrollmentDetTemp();
                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")) {
                            EnrollmentDetTemp.setTotalCount("");
                        } else {
                            EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        }

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")) {
                            EnrollmentDetTemp.setFemaleCount("");

                        } else {
                            EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        }

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {
                            EnrollmentDetTemp.setMaleCount("");
                        } else {
                            EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        }
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDetTemp.setValidation(-1);
                        EnrollmentDetTemp.setReason("Missing Field/s");
                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                    } else if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())) {
                            EnrollmentDetTemp.setTotalCount("");
                        } else {
                            EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        }

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())) {
                            EnrollmentDetTemp.setFemaleCount("");

                        } else {
                            EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        }

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {
                            EnrollmentDetTemp.setMaleCount("");
                        } else {
                            EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        }
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDetTemp.setValidation(-2);
                        EnrollmentDetTemp.setReason("Format Error");
                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);

                    } else if (Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            != Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            + Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        EnrollmentDetTemp.setValidation(-3);
                        EnrollmentDetTemp.setReason("Summation Error");
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                    } else {
                        EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        EnrollmentDetTemp.setValidation(1);
                        EnrollmentDetTemp.setReason("");
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                    }

                }

                enrollmentTemp.setEnrollmentDetArrayList(arrEnrollmentDetTemp);
                arrayError.add(enrollmentTemp);
            } else if (Double.parseDouble(String.format("%.2f", Float.parseFloat(arrError.get(i).getGenderDisparityIndex())))
                    != Double.parseDouble(String.format("%.2f", Float.parseFloat(arrError.get(i).getTotalFemale())
                    / Float.parseFloat(arrError.get(i).getTotalMale())))) {
                //
                enrollmentTemp = new EnrollmentTemp();
                //SET OTHER STUFF

                enrollmentTemp.setFormID(formID);
                enrollmentTemp.setCensusYear(year);
                enrollmentTemp.setClassification(classification);
              //  enrollmentTemp.setSchoolID(arrError.get(i).getSchoolID());
                enrollmentTemp.setSchoolName(arrError.get(i).getSchoolName());
                enrollmentTemp.setDistrict(arrError.get(i).getDistrict());
                enrollmentTemp.setSchoolType(arrError.get(i).getSchoolType());

                //END SET OTHER STUFF
                //
                enrollmentTemp.setTotalFemale(arrError.get(i).getTotalFemale());
                enrollmentTemp.setTotalMale(arrError.get(i).getTotalMale());
                enrollmentTemp.setGrandTotal(arrError.get(i).getGrandTotal());
                enrollmentTemp.setGenderDisparityIndex(arrError.get(i).getGenderDisparityIndex());

                enrollmentTemp.setValidation(-4);
                enrollmentTemp.setReason("Quotient Error");
                arrEnrollmentDetTemp = new ArrayList<EnrollmentDetTemp>();
                for (int y = 0; y < arrError.get(i).getEnrollmentDetArrayList().size(); y++) {

                    EnrollmentDetTemp EnrollmentDetTemp = new EnrollmentDetTemp();
                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")) {
                            EnrollmentDetTemp.setTotalCount("");
                        } else {
                            EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        }

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")) {
                            EnrollmentDetTemp.setFemaleCount("");

                        } else {
                            EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        }

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {
                            EnrollmentDetTemp.setMaleCount("");
                        } else {
                            EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        }
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDetTemp.setValidation(-1);
                        EnrollmentDetTemp.setReason("Missing Field/s");
                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                    } else if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())) {
                            EnrollmentDetTemp.setTotalCount("");
                        } else {
                            EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        }

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())) {
                            EnrollmentDetTemp.setFemaleCount("");

                        } else {
                            EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        }

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {
                            EnrollmentDetTemp.setMaleCount("");
                        } else {
                            EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        }
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDetTemp.setValidation(-2);
                        EnrollmentDetTemp.setReason("Format Error");
                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);

                    } else if (Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            != Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            + Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        EnrollmentDetTemp.setValidation(-3);
                        EnrollmentDetTemp.setReason("Summation Error");
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                    } else {
                        EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                        EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                        EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                        EnrollmentDetTemp.setValidation(1);
                        EnrollmentDetTemp.setReason("");
                        EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                    }

                }

                enrollmentTemp.setEnrollmentDetArrayList(arrEnrollmentDetTemp);
                arrayError.add(enrollmentTemp);

            } else {

                boolean x = false;

                for (int y = 0; y < arrError.get(i).getEnrollmentDetArrayList().size(); y++) {
                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {

                        x = true;

                    } else if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        x = true;
                    } else if (Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            != Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            + Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        x = true;
                    }
                }

                // fix laman here
                if (x) {
                    //
                    enrollmentTemp = new EnrollmentTemp();
                    //SET OTHER STUFF
                    enrollmentTemp.setFormID(formID);
                    enrollmentTemp.setCensusYear(year);
                    enrollmentTemp.setClassification(classification);
               //     enrollmentTemp.setSchoolID(arrError.get(i).getSchoolID());
                    enrollmentTemp.setSchoolName(arrError.get(i).getSchoolName());
                    enrollmentTemp.setDistrict(arrError.get(i).getDistrict());
                    enrollmentTemp.setSchoolType(arrError.get(i).getSchoolType());

                    //END SET OTHER STUFF
                    //
                    enrollmentTemp.setTotalFemale(arrError.get(i).getTotalFemale());
                    enrollmentTemp.setTotalMale(arrError.get(i).getTotalMale());
                    enrollmentTemp.setGrandTotal(arrError.get(i).getGrandTotal());
                    enrollmentTemp.setGenderDisparityIndex(arrError.get(i).getGenderDisparityIndex());
                    enrollmentTemp.setValidation(1);
                    enrollmentTemp.setReason("");

                    arrEnrollmentDetTemp = new ArrayList<EnrollmentDetTemp>();
                    for (int y = 0; y < arrError.get(i).getEnrollmentDetArrayList().size(); y++) {

                        EnrollmentDetTemp EnrollmentDetTemp = new EnrollmentDetTemp();
                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null
                                || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null
                                || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null
                                || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")
                                || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")
                                || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {

                            if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")) {
                                EnrollmentDetTemp.setTotalCount("");
                            } else {
                                EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                            }

                            if (arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")) {
                                EnrollmentDetTemp.setFemaleCount("");

                            } else {
                                EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                            }

                            if (arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {
                                EnrollmentDetTemp.setMaleCount("");
                            } else {
                                EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                            }
                            EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                            EnrollmentDetTemp.setValidation(-1);
                            EnrollmentDetTemp.setReason("Missing Field/s");
                            arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                        } else if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                                || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                                || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                            if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())) {
                                EnrollmentDetTemp.setTotalCount("");
                            } else {
                                EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                            }

                            if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())) {
                                EnrollmentDetTemp.setFemaleCount("");

                            } else {
                                EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                            }

                            if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {
                                EnrollmentDetTemp.setMaleCount("");
                            } else {
                                EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                            }
                            EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                            EnrollmentDetTemp.setValidation(-2);
                            EnrollmentDetTemp.setReason("Format Error");
                            arrEnrollmentDetTemp.add(EnrollmentDetTemp);

                        } else if (Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                                != Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                                + Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                            EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                            EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                            EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                            EnrollmentDetTemp.setValidation(-3);
                            EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                            EnrollmentDetTemp.setReason("Summation Error");
                            arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                        } else {
                            EnrollmentDetTemp.setMaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount());
                            EnrollmentDetTemp.setFemaleCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount());
                            EnrollmentDetTemp.setTotalCount(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount());
                            EnrollmentDetTemp.setValidation(1);
                            EnrollmentDetTemp.setReason("");
                            EnrollmentDetTemp.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                            arrEnrollmentDetTemp.add(EnrollmentDetTemp);
                        }

                    }

                    enrollmentTemp.setEnrollmentDetArrayList(arrEnrollmentDetTemp);
                    arrayError.add(enrollmentTemp);
                } else {
                    enrollment = new Enrollment();

                    // set stuff
                    enrollment.setFormID(formID);
                    enrollment.setCensusYear(year);
                    enrollment.setClassification(classification);
               //     enrollment.setSchoolID(Integer.parseInt(arrError.get(i).getSchoolID()));
                    enrollment.setSchoolName(arrError.get(i).getSchoolName());
                    enrollment.setDistrict(arrError.get(i).getDistrict());
                    enrollment.setSchoolType(arrError.get(i).getSchoolType());

                    //END SET OTHER STUFF
                    //
                    enrollment.setTotalFemale(Integer.parseInt(arrError.get(i).getTotalFemale()));
                    enrollment.setTotalMale(Integer.parseInt(arrError.get(i).getTotalMale()));
                    enrollment.setGrandTotal(Integer.parseInt(arrError.get(i).getGrandTotal()));
                    enrollment.setGenderDisparityIndex(Double.parseDouble(arrError.get(i).getGenderDisparityIndex()));
                    enrollment.setValidation(1);
                    enrollment.setReason("");
                    arrEnrollmentDet = new ArrayList<EnrollmentDet>();

                    for (int y = 0; y < arrError.get(i).getEnrollmentDetArrayList().size(); y++) {
                        EnrollmentDet EnrollmentDet = new EnrollmentDet();
                        EnrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                        EnrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                        EnrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                        EnrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDet.setValidation(1);
                       EnrollmentDet.setReason("");
                        arrEnrollmentDet.add(EnrollmentDet);
                    }
                    enrollment.setEnrollmentDetArrayList(arrEnrollmentDet);
                    arrayNoError.add(enrollment);
                }

            }

        }
    }

    public EnrollmentChecker() {
    }

    public ArrayList<Enrollment> transformData(ArrayList<EnrollmentTemp> arrError, String classification) {
        Enrollment enrollment;
        ArrayList<EnrollmentDet> arrEnrollmentDet;
        ArrayList<Enrollment> transformData = new ArrayList<>();
        EnrollmentDet enrollmentDet;

        for (int i = 0; i < arrError.size(); i++) {
            if (arrError.get(i).getTotalFemale() == null
                    || arrError.get(i).getTotalMale() == null
                    || arrError.get(i).getGrandTotal() == null
                    || arrError.get(i).getGenderDisparityIndex() == null
                    || arrError.get(i).getTotalFemale().equalsIgnoreCase("")
                    || arrError.get(i).getTotalMale().equalsIgnoreCase("")
                    || arrError.get(i).getGrandTotal().equalsIgnoreCase("")
                    || arrError.get(i).getGenderDisparityIndex().equalsIgnoreCase("")) {

                enrollment = new Enrollment();
                //SET OTHER STUFF

                enrollment.setFormID(arrError.get(i).getFormID());
                enrollment.setCensusYear(arrError.get(i).getCensusYear());
            //    enrollment.setSchoolID(Integer.parseInt(arrError.get(i).getSchoolID()));
                enrollment.setSchoolName(arrError.get(i).getSchoolName());
                enrollment.setDistrict(arrError.get(i).getDistrict());
                enrollment.setSchoolType(arrError.get(i).getSchoolType());
                enrollment.setClassification(classification);
                //END SET OTHER STUFF
                if (arrError.get(i).getTotalFemale() == null || arrError.get(i).getTotalFemale().equalsIgnoreCase("")) {
                    enrollment.setTotalFemale(-1);

                } else {
                    enrollment.setTotalFemale(Integer.parseInt(arrError.get(i).getTotalFemale()));
                }

                if (arrError.get(i).getTotalMale() == null || arrError.get(i).getTotalMale().equalsIgnoreCase("")) {
                    enrollment.setTotalMale(-1);

                } else {
                    enrollment.setTotalMale(Integer.parseInt(arrError.get(i).getTotalMale()));
                }

                if (arrError.get(i).getGrandTotal() == null || arrError.get(i).getGrandTotal().equalsIgnoreCase("")) {
                    enrollment.setGrandTotal(-1);
                } else {
                    enrollment.setGrandTotal(Integer.parseInt(arrError.get(i).getGrandTotal()));
                }

                if (arrError.get(i).getGenderDisparityIndex() == null || arrError.get(i).getGenderDisparityIndex().equalsIgnoreCase("")) {

                    enrollment.setGenderDisparityIndex(-1);
                } else {

                    enrollment.setGenderDisparityIndex(Double.parseDouble(arrError.get(i).getGenderDisparityIndex()));
                }

                enrollment.setValidation(-1);
                arrEnrollmentDet = new ArrayList<EnrollmentDet>();
                for (int y = 0; y < arrError.get(i).getEnrollmentDetArrayList().size(); y++) {

                    EnrollmentDet EnrollmentDet = new EnrollmentDet();
                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")) {
                            EnrollmentDet.setTotalCount(-1);
                        } else {
                            EnrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                        }

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")) {
                            EnrollmentDet.setFemaleCount(-1);

                        } else {
                            EnrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                        }

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {
                            EnrollmentDet.setMaleCount(-1);
                        } else {
                            EnrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                        }
                        EnrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDet.setValidation(-1);
                        arrEnrollmentDet.add(EnrollmentDet);
                    } else if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())) {
                            EnrollmentDet.setTotalCount(-1);
                        } else {
                            EnrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                        }

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())) {
                            EnrollmentDet.setFemaleCount(-1);

                        } else {
                            EnrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                        }

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {
                            EnrollmentDet.setMaleCount(-1);
                        } else {
                            EnrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                        }
                        EnrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDet.setValidation(-2);
                        arrEnrollmentDet.add(EnrollmentDet);

                    } else if (Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            != Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            + Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        EnrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                        EnrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                        EnrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                        EnrollmentDet.setValidation(-3);
                        EnrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        arrEnrollmentDet.add(EnrollmentDet);
                    } else {
                        EnrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                        EnrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                        EnrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                        EnrollmentDet.setValidation(1);
                        EnrollmentDet.setReason("");
                        EnrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        arrEnrollmentDet.add(EnrollmentDet);
                    }

                }

                enrollment.setEnrollmentDetArrayList(arrEnrollmentDet);
                transformData.add(enrollment);
            } else if (isNumeric(arrError.get(i).getTotalFemale())
                    || isNumeric(arrError.get(i).getTotalMale())
                    || isNumeric(arrError.get(i).getGrandTotal())
                   ) {

                //
                enrollment = new Enrollment();
                //SET OTHER STUFF

                enrollment.setFormID(arrError.get(i).getFormID());
                enrollment.setCensusYear(arrError.get(i).getCensusYear());
               // enrollment.setSchoolID(Integer.parseInt(arrError.get(i).getSchoolID()));
                enrollment.setSchoolName(arrError.get(i).getSchoolName());
                enrollment.setDistrict(arrError.get(i).getDistrict());
                enrollment.setSchoolType(arrError.get(i).getSchoolType());
                 enrollment.setClassification(classification);

                //END SET OTHER STUFF
                //
                if (isNumeric(arrError.get(i).getTotalFemale())) {
                    enrollment.setTotalFemale(-1);

                } else {
                    enrollment.setTotalFemale(Integer.parseInt(arrError.get(i).getTotalFemale()));
                }

                if (isNumeric(arrError.get(i).getTotalMale())) {
                    enrollment.setTotalMale(-1);

                } else {
                    enrollment.setTotalMale(Integer.parseInt(arrError.get(i).getTotalMale()));
                }

                if (isNumeric(arrError.get(i).getGrandTotal())) {
                    enrollment.setGrandTotal(-1);
                } else {
                    enrollment.setGrandTotal(Integer.parseInt(arrError.get(i).getGrandTotal()));
                }

                    enrollment.setGenderDisparityIndex(Double.parseDouble(arrError.get(i).getGenderDisparityIndex()));
                
                enrollment.setValidation(-2);
                arrEnrollmentDet = new ArrayList<EnrollmentDet>();

                for (int y = 0; y < arrError.get(i).getEnrollmentDetArrayList().size(); y++) {

                    EnrollmentDet EnrollmentDet = new EnrollmentDet();
                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")) {
                            EnrollmentDet.setTotalCount(-1);
                        } else {
                            EnrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                        }

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")) {
                            EnrollmentDet.setFemaleCount(-1);

                        } else {
                            EnrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                        }

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {
                            EnrollmentDet.setMaleCount(-1);
                        } else {
                            EnrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                        }
                        EnrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDet.setValidation(-1);
                        arrEnrollmentDet.add(EnrollmentDet);
                    } else if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())) {
                            EnrollmentDet.setTotalCount(-1);
                        } else {
                            EnrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                        }

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())) {
                            EnrollmentDet.setFemaleCount(-1);

                        } else {
                            EnrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                        }

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {
                            EnrollmentDet.setMaleCount(-1);
                        } else {
                            EnrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                        }
                        EnrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDet.setValidation(-2);
                        arrEnrollmentDet.add(EnrollmentDet);

                    } else if (Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            != Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            + Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {
                        EnrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                        EnrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                        EnrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                        EnrollmentDet.setValidation(-3);
                        arrEnrollmentDet.add(EnrollmentDet);
                    } else {
                        EnrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                        EnrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                        EnrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                        EnrollmentDet.setValidation(1);
                        EnrollmentDet.setReason("");
                        arrEnrollmentDet.add(EnrollmentDet);
                    }

                }

                enrollment.setEnrollmentDetArrayList(arrEnrollmentDet);
                transformData.add(enrollment);
            } else if (Integer.parseInt(arrError.get(i).getGrandTotal())
                    != Integer.parseInt(arrError.get(i).getTotalMale())
                    + Integer.parseInt(arrError.get(i).getTotalFemale())) {

                //
                enrollment = new Enrollment();
                //SET OTHER STUFF

                enrollment.setFormID(arrError.get(i).getFormID());
                enrollment.setCensusYear(arrError.get(i).getCensusYear());
               // enrollment.setSchoolID(Integer.parseInt(arrError.get(i).getSchoolID()));
                enrollment.setSchoolName(arrError.get(i).getSchoolName());
                enrollment.setDistrict(arrError.get(i).getDistrict());
                enrollment.setSchoolType(arrError.get(i).getSchoolType());
                 enrollment.setClassification(classification);
                //END SET OTHER STUFF
                //
                enrollment.setTotalFemale(Integer.parseInt(arrError.get(i).getTotalFemale()));
                enrollment.setTotalMale(Integer.parseInt(arrError.get(i).getTotalMale()));
                enrollment.setGrandTotal(Integer.parseInt(arrError.get(i).getGrandTotal()));
                enrollment.setGenderDisparityIndex(Integer.parseInt(arrError.get(i).getGenderDisparityIndex()));

                enrollment.setValidation(-3);

                arrEnrollmentDet = new ArrayList<EnrollmentDet>();

                for (int y = 0; y < arrError.get(i).getEnrollmentDetArrayList().size(); y++) {

                    EnrollmentDet EnrollmentDet = new EnrollmentDet();
                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")) {
                            EnrollmentDet.setTotalCount(-1);
                        } else {
                            EnrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                        }

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")) {
                            EnrollmentDet.setFemaleCount(-1);

                        } else {
                            EnrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                        }

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {
                            EnrollmentDet.setMaleCount(-1);
                        } else {
                            EnrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                        }
                        EnrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDet.setValidation(-1);
                        arrEnrollmentDet.add(EnrollmentDet);
                    } else if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())) {
                            EnrollmentDet.setTotalCount(-1);
                        } else {
                            EnrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                        }

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())) {
                            EnrollmentDet.setFemaleCount(-1);

                        } else {
                            EnrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                        }

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {
                            EnrollmentDet.setMaleCount(-1);
                        } else {
                            EnrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                        }
                        EnrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDet.setValidation(-2);
                        arrEnrollmentDet.add(EnrollmentDet);

                    } else if (Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            != Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            + Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        EnrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                        EnrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                        EnrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                        EnrollmentDet.setValidation(-3);
                        EnrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        arrEnrollmentDet.add(EnrollmentDet);
                    } else {
                        EnrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                        EnrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                        EnrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                        EnrollmentDet.setValidation(1);
                        EnrollmentDet.setReason("");
                        
                        EnrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        arrEnrollmentDet.add(EnrollmentDet);
                    }

                }

                enrollment.setEnrollmentDetArrayList(arrEnrollmentDet);
                transformData.add(enrollment);
            } else if (Double.parseDouble(String.format("%.2f", Float.parseFloat(arrError.get(i).getGenderDisparityIndex())))
                    != Double.parseDouble(String.format("%.2f", Float.parseFloat(arrError.get(i).getTotalFemale())
                    / Float.parseFloat(arrError.get(i).getTotalMale())))){
                //
                enrollment = new Enrollment();
                //SET OTHER STUFF

                enrollment.setFormID(arrError.get(i).getFormID());
                enrollment.setCensusYear(arrError.get(i).getCensusYear());
               // enrollment.setSchoolID(Integer.parseInt(arrError.get(i).getSchoolID()));
                enrollment.setSchoolName(arrError.get(i).getSchoolName());
                enrollment.setDistrict(arrError.get(i).getDistrict());
                enrollment.setSchoolType(arrError.get(i).getSchoolType());
                 enrollment.setClassification(classification);
                //END SET OTHER STUFF
                //
                enrollment.setTotalFemale(Integer.parseInt(arrError.get(i).getTotalFemale()));
                enrollment.setTotalMale(Integer.parseInt(arrError.get(i).getTotalMale()));
                enrollment.setGrandTotal(Integer.parseInt(arrError.get(i).getGrandTotal()));
                enrollment.setGenderDisparityIndex(Integer.parseInt(arrError.get(i).getGenderDisparityIndex()));

                enrollment.setValidation(-4);
                arrEnrollmentDet = new ArrayList<EnrollmentDet>();

                for (int y = 0; y < arrError.get(i).getEnrollmentDetArrayList().size(); y++) {

                    EnrollmentDet EnrollmentDet = new EnrollmentDet();
                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")) {
                            EnrollmentDet.setTotalCount(-1);
                        } else {
                            EnrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                        }

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")) {
                            EnrollmentDet.setFemaleCount(-1);

                        } else {
                            EnrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                        }

                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {
                            EnrollmentDet.setMaleCount(-1);
                        } else {
                            EnrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                        }
                        EnrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDet.setValidation(-1);
                        arrEnrollmentDet.add(EnrollmentDet);
                    } else if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())) {
                            EnrollmentDet.setTotalCount(-1);
                        } else {
                            EnrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                        }

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())) {
                            EnrollmentDet.setFemaleCount(-1);

                        } else {
                            EnrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                        }

                        if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {
                            EnrollmentDet.setMaleCount(-1);
                        } else {
                            EnrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                        }
                        EnrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDet.setValidation(-2);
                        arrEnrollmentDet.add(EnrollmentDet);

                    } else if (Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            != Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            + Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        EnrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                        EnrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                        EnrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                        EnrollmentDet.setValidation(-3);
                        EnrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        arrEnrollmentDet.add(EnrollmentDet);
                    } else {
                        EnrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                        EnrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                        EnrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                        EnrollmentDet.setValidation(1);
                        EnrollmentDet.setReason("");
                        EnrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        arrEnrollmentDet.add(EnrollmentDet);
                    }

                }

                enrollment.setEnrollmentDetArrayList(arrEnrollmentDet);
                transformData.add(enrollment);

            } else {

                boolean x = false;

                for (int y = 0; y < arrError.get(i).getEnrollmentDetArrayList().size(); y++) {
                    if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")
                            || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {

                        x = true;

                    } else if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        x = true;
                    } else if (Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                            != Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                            + Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                        x = true;
                    }
                }

                // fix laman here
                if (x) {
                    //

                    enrollment = new Enrollment();
                    enrollment.setFormID(arrError.get(i).getFormID());
                    enrollment.setCensusYear(arrError.get(i).getCensusYear());
                 //   enrollment.setSchoolID(Integer.parseInt(arrError.get(i).getSchoolID()));
                    enrollment.setSchoolName(arrError.get(i).getSchoolName());
                    enrollment.setDistrict(arrError.get(i).getDistrict());
                    enrollment.setSchoolType(arrError.get(i).getSchoolType());
                     enrollment.setClassification(classification);
                    //END SET OTHER STUFF
                    //
                    enrollment.setTotalFemale(Integer.parseInt(arrError.get(i).getTotalFemale()));
                    enrollment.setTotalMale(Integer.parseInt(arrError.get(i).getTotalMale()));
                    enrollment.setGrandTotal(Integer.parseInt(arrError.get(i).getGrandTotal()));
                    enrollment.setGenderDisparityIndex(Double.parseDouble(arrError.get(i).getGenderDisparityIndex()));
                    enrollment.setValidation(1);
                    enrollment.setReason("");

                    arrEnrollmentDet = new ArrayList<EnrollmentDet>();

                    for (int y = 0; y < arrError.get(i).getEnrollmentDetArrayList().size(); y++) {

                        EnrollmentDet EnrollmentDet = new EnrollmentDet();
                        if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null
                                || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null
                                || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null
                                || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")
                                || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")
                                || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {

                            if (arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount().equalsIgnoreCase("")) {
                                EnrollmentDet.setTotalCount(-1);
                            } else {
                                EnrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                            }

                            if (arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount().equalsIgnoreCase("")) {
                                EnrollmentDet.setFemaleCount(-1);

                            } else {
                                EnrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                            }

                            if (arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount() == null || arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount().equalsIgnoreCase("")) {
                                EnrollmentDet.setMaleCount(-1);
                            } else {
                                EnrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                            }
                            EnrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                            EnrollmentDet.setValidation(-1);
                            arrEnrollmentDet.add(EnrollmentDet);
                        } else if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                                || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                                || isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                            if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())) {
                                EnrollmentDet.setTotalCount(-1);
                            } else {
                                EnrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                            }

                            if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())) {
                                EnrollmentDet.setFemaleCount(-1);

                            } else {
                                EnrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                            }

                            if (isNumeric(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {
                                EnrollmentDet.setMaleCount(-1);
                            } else {
                                EnrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                            }
                            EnrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                            EnrollmentDet.setValidation(-2);
                            arrEnrollmentDet.add(EnrollmentDet);

                        } else if (Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount())
                                != Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount())
                                + Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount())) {

                            EnrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                            EnrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                            EnrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                            EnrollmentDet.setValidation(-3);
                            EnrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                            arrEnrollmentDet.add(EnrollmentDet);
                        } else {
                            EnrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                            EnrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                            EnrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                            EnrollmentDet.setValidation(1);
                            EnrollmentDet.setReason("");
                            EnrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                            arrEnrollmentDet.add(EnrollmentDet);
                        }

                    }

                    enrollment.setEnrollmentDetArrayList(arrEnrollmentDet);
                    transformData.add(enrollment);
                } else {
                    enrollment = new Enrollment();

                    // set stuff
                    enrollment.setFormID(arrError.get(i).getFormID());
                    enrollment.setCensusYear(arrError.get(i).getCensusYear());
                  //  enrollment.setSchoolID(Integer.parseInt(arrError.get(i).getSchoolID()));
                    enrollment.setSchoolName(arrError.get(i).getSchoolName());
                    enrollment.setDistrict(arrError.get(i).getDistrict());
                    enrollment.setSchoolType(arrError.get(i).getSchoolType());
                     enrollment.setClassification(classification);

                    //END SET OTHER STUFF
                    //
                    enrollment.setTotalFemale(Integer.parseInt(arrError.get(i).getTotalFemale()));
                    enrollment.setTotalMale(Integer.parseInt(arrError.get(i).getTotalMale()));
                    enrollment.setGrandTotal(Integer.parseInt(arrError.get(i).getGrandTotal()));
                    enrollment.setGenderDisparityIndex(Double.parseDouble(arrError.get(i).getGenderDisparityIndex()));
                    enrollment.setValidation(1);
                    enrollment.setReason("");
                    arrEnrollmentDet = new ArrayList<EnrollmentDet>();
                    for (int y = 0; y < arrError.get(i).getEnrollmentDetArrayList().size(); y++) {
                        EnrollmentDet EnrollmentDet = new EnrollmentDet();
                        EnrollmentDet.setMaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                        EnrollmentDet.setFemaleCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                        EnrollmentDet.setTotalCount(Integer.parseInt(arrError.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                        EnrollmentDet.setGradeLevel(arrError.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                        EnrollmentDet.setValidation(1);
                        EnrollmentDet.setReason("");
                        arrEnrollmentDet.add(EnrollmentDet);
                    }
                    enrollment.setEnrollmentDetArrayList(arrEnrollmentDet);
                    arrayNoError.add(enrollment);
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
