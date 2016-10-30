/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package checker.health;

import model.health.NutritionalStatus;
import model.health.NutritionalStatusBMI;
import model.temp.health.NutritionalStatusBMITemp;
import model.temp.health.NutritionalStatusTemp;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */

public class NutritionalStatusChecker {

    private ArrayList<NutritionalStatus> arrayNoError;
    private ArrayList<NutritionalStatusTemp> arrayError;
    private boolean trigger = false;

    /*
     *@ ArrErrorByAgeGroupSex temp stored data, all strings
     */
    public NutritionalStatusChecker(ArrayList<NutritionalStatusTemp> ArrError) {

        NutritionalStatus NutritionalStatus;
        NutritionalStatusTemp NutritionalStatusTemp;
        arrayNoError = new ArrayList<>();
        arrayError = new ArrayList<>();

        ArrayList<NutritionalStatusBMI> arrNutritionalStatusBMI;
        ArrayList<NutritionalStatusBMITemp> arrNutritionalStatusBMITemp;

        NutritionalStatusBMITemp NutritionalStatusBMITemp;
        NutritionalStatusBMI NutritionalStatusBMI;

        for (int i = 0; i < ArrError.size(); i++) {
            NutritionalStatusTemp = new NutritionalStatusTemp();
            arrNutritionalStatusBMITemp = new ArrayList<NutritionalStatusBMITemp>();
            arrNutritionalStatusBMI = new ArrayList<NutritionalStatusBMI>();
            NutritionalStatusTemp.setDistrict(ArrError.get(i).getDistrict());
            NutritionalStatusTemp.setGradeLevel(ArrError.get(i).getGradeLevel());
            NutritionalStatusTemp.setTotalMale(ArrError.get(i).getTotalMale());
            NutritionalStatusTemp.setTotalFemale(ArrError.get(i).getTotalFemale());
            NutritionalStatusTemp.setTotalCount(ArrError.get(i).getTotalCount());
            NutritionalStatusTemp.setValidation(false);
            NutritionalStatusTemp.setPupilsWeighedFemale(ArrError.get(i).getPupilsWeighedFemale());
            NutritionalStatusTemp.setPupilsWeighedMale(ArrError.get(i).getPupilsWeighedMale());
            NutritionalStatusTemp.setPupilsWeighedTotal(ArrError.get(i).getPupilsWeighedTotal());

            //CHECK UNG SA NutritionalStatus
            for (int y = 0; y < ArrError.get(i).getNutritionalStatusBMITemp().size(); y++) {
                NutritionalStatusBMITemp = new NutritionalStatusBMITemp();
                if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount() == null
                        || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount() == null
                        || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount().equalsIgnoreCase("")) {

                    if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount().equalsIgnoreCase("")) {
                        NutritionalStatusBMITemp.setFemaleCount("-1");
                    } else {
                        NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                    }

                    if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount().equalsIgnoreCase("")) {
                        NutritionalStatusBMITemp.setTotalCount("-1");
                    } else {
                        NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                    }

                    if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount().equalsIgnoreCase("")) {
                        NutritionalStatusBMITemp.setMaleCount("-1");
                    } else {
                        NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                    }

                    arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    NutritionalStatusTemp.setNutritionalStatusTemp(arrNutritionalStatusBMITemp);
                    arrayError.add(NutritionalStatusTemp);
                } else if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                        || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())
                        || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())) {

                    if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {
                        NutritionalStatusBMITemp.setFemaleCount("-1");
                    } else {
                        NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                    }

                    if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())) {
                        NutritionalStatusBMITemp.setTotalCount("-1");
                    } else {
                        NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                    }

                    if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())) {
                        NutritionalStatusBMITemp.setMaleCount("-1");
                    } else {
                        NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                    }

                    arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    NutritionalStatusTemp.setNutritionalStatusTemp(arrNutritionalStatusBMITemp);
                    arrayError.add(NutritionalStatusTemp);
                } else {
                    NutritionalStatusBMI = new NutritionalStatusBMI();

                    NutritionalStatusBMI.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                    NutritionalStatusBMI.setMaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount()));
                    NutritionalStatusBMI.setFemaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount()));
                    NutritionalStatusBMI.setTotalCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount()));
                    arrNutritionalStatusBMI.add(NutritionalStatusBMI);

                }
            }
            NutritionalStatus = new NutritionalStatus();
            NutritionalStatus.setDistrict(ArrError.get(i).getDistrict());
            NutritionalStatus.setGradeLevel(ArrError.get(i).getGradeLevel());
            NutritionalStatus.setTotalMale(Integer.parseInt(ArrError.get(i).getTotalMale()));
            NutritionalStatus.setTotalFemale(Integer.parseInt(ArrError.get(i).getTotalFemale()));
            NutritionalStatus.setTotalCount(Integer.parseInt(ArrError.get(i).getTotalCount()));
            NutritionalStatus.setValidation(false);
            NutritionalStatus.setPupilsWeighedFemale(Integer.parseInt(ArrError.get(i).getPupilsWeighedFemale()));
            NutritionalStatus.setPupilsWeighedMale(Integer.parseInt(ArrError.get(i).getPupilsWeighedMale()));
            NutritionalStatus.setPupilsWeighedTotal(Integer.parseInt(ArrError.get(i).getPupilsWeighedTotal()));
            NutritionalStatus.setNutritionalStatusBMI(arrNutritionalStatusBMI);
            arrayNoError.add(NutritionalStatus);
        }
    }

    public NutritionalStatusChecker(ArrayList<NutritionalStatusTemp> ArrError, int year, int formID) {

        NutritionalStatus NutritionalStatus;
        NutritionalStatusTemp NutritionalStatusTemp;
        arrayNoError = new ArrayList<>();
        arrayError = new ArrayList<>();

        ArrayList<NutritionalStatusBMI> arrNutritionalStatusBMI;
        ArrayList<NutritionalStatusBMITemp> arrNutritionalStatusBMITemp;

        NutritionalStatusBMITemp NutritionalStatusBMITemp;
        NutritionalStatusBMI NutritionalStatusBMI;

        for (int i = 0; i < ArrError.size(); i++) {
            NutritionalStatusTemp = new NutritionalStatusTemp();
            arrNutritionalStatusBMITemp = new ArrayList<NutritionalStatusBMITemp>();
            arrNutritionalStatusBMI = new ArrayList<NutritionalStatusBMI>();
            NutritionalStatusTemp.setFormID(formID);
            NutritionalStatusTemp.setCensusYear(year);
            NutritionalStatusTemp.setDistrict(ArrError.get(i).getDistrict());
            NutritionalStatusTemp.setGradeLevel(ArrError.get(i).getGradeLevel());
            NutritionalStatusTemp.setTotalMale(ArrError.get(i).getTotalMale());
            NutritionalStatusTemp.setTotalFemale(ArrError.get(i).getTotalFemale());
            NutritionalStatusTemp.setTotalCount(ArrError.get(i).getTotalCount());
            NutritionalStatusTemp.setValidation(false);
            NutritionalStatusTemp.setPupilsWeighedFemale(ArrError.get(i).getPupilsWeighedFemale());
            NutritionalStatusTemp.setPupilsWeighedMale(ArrError.get(i).getPupilsWeighedMale());
            NutritionalStatusTemp.setPupilsWeighedTotal(ArrError.get(i).getPupilsWeighedTotal());

            //CHECK UNG SA NutritionalStatus
            for (int y = 0; y < ArrError.get(i).getNutritionalStatusBMITemp().size(); y++) {
                NutritionalStatusBMITemp = new NutritionalStatusBMITemp();
                if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount() == null
                        || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount() == null
                        || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount().equalsIgnoreCase("")) {

                    if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount().equalsIgnoreCase("")) {
                        NutritionalStatusBMITemp.setFemaleCount("-1");
                    } else {
                        NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                    }

                    if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount().equalsIgnoreCase("")) {
                        NutritionalStatusBMITemp.setTotalCount("-1");
                    } else {
                        NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                    }

                    if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount().equalsIgnoreCase("")) {
                        NutritionalStatusBMITemp.setMaleCount("-1");
                    } else {
                        NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                    }

                    arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    NutritionalStatusTemp.setNutritionalStatusTemp(arrNutritionalStatusBMITemp);
                    arrayError.add(NutritionalStatusTemp);
                } else if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                        || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())
                        || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())) {

                    if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {
                        NutritionalStatusBMITemp.setFemaleCount("-1");
                    } else {
                        NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                    }

                    if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())) {
                        NutritionalStatusBMITemp.setTotalCount("-1");
                    } else {
                        NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                    }

                    if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())) {
                        NutritionalStatusBMITemp.setMaleCount("-1");
                    } else {
                        NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                    }

                    arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    NutritionalStatusTemp.setNutritionalStatusTemp(arrNutritionalStatusBMITemp);
                    arrayError.add(NutritionalStatusTemp);
                } else {
                    NutritionalStatusBMI = new NutritionalStatusBMI();

                    NutritionalStatusBMI.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                    NutritionalStatusBMI.setMaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount()));
                    NutritionalStatusBMI.setFemaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount()));
                    NutritionalStatusBMI.setTotalCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount()));
                    arrNutritionalStatusBMI.add(NutritionalStatusBMI);

                }
            }
            NutritionalStatus = new NutritionalStatus();
            NutritionalStatus.setFormID(formID);
            NutritionalStatus.setCensusYear(year);
            NutritionalStatus.setDistrict(ArrError.get(i).getDistrict());
            NutritionalStatus.setGradeLevel(ArrError.get(i).getGradeLevel());
            NutritionalStatus.setTotalMale(Integer.parseInt(ArrError.get(i).getTotalMale()));
            NutritionalStatus.setTotalFemale(Integer.parseInt(ArrError.get(i).getTotalFemale()));
            NutritionalStatus.setTotalCount(Integer.parseInt(ArrError.get(i).getTotalCount()));
            NutritionalStatus.setValidation(false);
            NutritionalStatus.setPupilsWeighedFemale(Integer.parseInt(ArrError.get(i).getPupilsWeighedFemale()));
            NutritionalStatus.setPupilsWeighedMale(Integer.parseInt(ArrError.get(i).getPupilsWeighedMale()));
            NutritionalStatus.setPupilsWeighedTotal(Integer.parseInt(ArrError.get(i).getPupilsWeighedTotal()));
            NutritionalStatus.setNutritionalStatusBMI(arrNutritionalStatusBMI);
            arrayNoError.add(NutritionalStatus);
        }
    }

    public NutritionalStatusChecker() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<NutritionalStatus> TransformData(ArrayList<NutritionalStatusTemp> ArrError) {

        ArrayList<NutritionalStatusBMI> arrNutritionalStatusBMI;
        NutritionalStatus NutritionalStatus;
        ArrayList<NutritionalStatus> TransformData = new ArrayList<>();
        NutritionalStatusBMI NutritionalStatusBMI = new NutritionalStatusBMI();
        for (int i = 0; i < ArrError.size(); i++) {
            NutritionalStatus = new NutritionalStatus();
            arrNutritionalStatusBMI = new ArrayList<NutritionalStatusBMI>();

            NutritionalStatus.setFormID(ArrError.get(i).getFormID());
            NutritionalStatus.setCensusYear(ArrError.get(i).getCensusYear());
            NutritionalStatus.setDistrict(ArrError.get(i).getDistrict());
            NutritionalStatus.setGradeLevel(ArrError.get(i).getGradeLevel());
            NutritionalStatus.setTotalMale(Integer.parseInt(ArrError.get(i).getTotalMale()));
            NutritionalStatus.setTotalFemale(Integer.parseInt(ArrError.get(i).getTotalFemale()));
            NutritionalStatus.setTotalCount(Integer.parseInt(ArrError.get(i).getTotalCount()));
            NutritionalStatus.setValidation(false);
            NutritionalStatus.setPupilsWeighedFemale(Integer.parseInt(ArrError.get(i).getPupilsWeighedFemale()));
            NutritionalStatus.setPupilsWeighedMale(Integer.parseInt(ArrError.get(i).getPupilsWeighedMale()));
            NutritionalStatus.setPupilsWeighedTotal(Integer.parseInt(ArrError.get(i).getPupilsWeighedTotal()));
            NutritionalStatus.setNutritionalStatusBMI(arrNutritionalStatusBMI);

            for (int y = 0; y < ArrError.get(i).getNutritionalStatusBMITemp().size(); y++) {
                NutritionalStatusBMI = new NutritionalStatusBMI();
                if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount() == null
                        || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount() == null
                        || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount().equalsIgnoreCase("")) {

                    if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount().equalsIgnoreCase("")) {
                        NutritionalStatusBMI.setFemaleCount(-1);
                    } else {
                        NutritionalStatusBMI.setFemaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount()));
                    }

                    if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount().equalsIgnoreCase("")) {
                        NutritionalStatusBMI.setTotalCount(-1);
                    } else {
                        NutritionalStatusBMI.setTotalCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount()));
                    }

                    if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount().equalsIgnoreCase("")) {
                        NutritionalStatusBMI.setMaleCount(-1);
                    } else {
                        NutritionalStatusBMI.setMaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount()));
                    }

                    arrNutritionalStatusBMI.add(NutritionalStatusBMI);
                    NutritionalStatus.setNutritionalStatusBMI(arrNutritionalStatusBMI);
                    TransformData.add(NutritionalStatus);
                } else if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                        || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())
                        || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())) {

                    if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {
                        NutritionalStatusBMI.setFemaleCount(-1);
                    } else {
                        NutritionalStatusBMI.setFemaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount()));
                    }

                    if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())) {
                        NutritionalStatusBMI.setTotalCount(-1);
                    } else {
                        NutritionalStatusBMI.setTotalCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount()));
                    }

                    if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())) {
                        NutritionalStatusBMI.setMaleCount(-1);
                    } else {
                        NutritionalStatusBMI.setMaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount()));
                    }

                    arrNutritionalStatusBMI.add(NutritionalStatusBMI);
                    NutritionalStatus.setNutritionalStatusBMI(arrNutritionalStatusBMI);
                    TransformData.add(NutritionalStatus);
                }

            }

        }
        return TransformData;
    }

    public static boolean isNumeric(String str) {
        return !StringUtils.isNumeric(str);
    }
//
//    public boolean checkGrandTotal(ArrayList<NutritionalStatusTemp> ArrErrorByAgeGroupSex) {
//        if (trigger == false) {
//
//            int totalInExcel = 0;
//            int maleInExcel = 0;
//            int femaleInExcel = 0;
//            int totalComputed = 0;
//            int maleComputed = 0;
//            int femaleComputed = 0;
//
//            for (int i = 0; i < ArrErrorByAgeGroupSex.size(); i++) {
//                if (!ArrErrorByAgeGroupSex.get(i).getDistrict().equalsIgnoreCase("Caloocan City")) {
//                    {
//                        maleComputed += Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getMaleCount());
//                        femaleComputed += Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getFemaleCount());
//                        totalComputed += (Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getMaleCount())
//                                + Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getFemaleCount()));
//                    }
//                }
//            }
//
//            for (int i = 0; i < ArrErrorByAgeGroupSex.size(); i++) {
//                if (ArrErrorByAgeGroupSex.get(i).getDistrict().equalsIgnoreCase("Caloocan City")) {
//                    totalInExcel = Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getTotal());
//                    maleInExcel = Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getMaleCount());
//                    femaleInExcel = Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getFemaleCount());
//
//                }
//            }
//
//            if (totalComputed == totalInExcel) {
//                if (maleComputed == maleInExcel) {
//                    if (femaleComputed == femaleInExcel) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }

    //i times 20
    public static boolean isAgeGroupComplete(ArrayList<NutritionalStatusTemp> ArrErrorByAgeGroupSex) {
//        boolean complete = true;//= str.chars().allMatch(Character::isDigit);
//
//        String ageGroup[] = new String[21];
//        ageGroup[0] = "All Ages";
//        ageGroup[1] = "Under 1";
//        ageGroup[2] = "1 - 4";
//        ageGroup[3] = "5 - 9";
//        ageGroup[4] = "10 - 14";
//        ageGroup[5] = "15 - 19";
//        ageGroup[6] = "20 - 24";
//        ageGroup[7] = "25 - 29";
//        ageGroup[8] = "30 - 34";
//        ageGroup[9] = "35 - 39";
//        ageGroup[10] = "40 - 44";
//        ageGroup[11] = "45 - 49";
//        ageGroup[12] = "50 - 54";
//        ageGroup[13] = "55 - 59";
//        ageGroup[14] = "60 - 64";
//        ageGroup[15] = "65 - 69";
//        ageGroup[16] = "70 - 74";
//        ageGroup[17] = "75 - 79";
//        ageGroup[18] = "80 and Over";
//        ageGroup[19] = "0 - 17";
//        ageGroup[20] = "18 and Over";
//
//        for (byAgeGroupTemp ArrErrorByAgeGroupSex1 : ArrErrorByAgeGroupSex) {
//            for (int x = 0; x < 20; x++) {
//                if (ArrErrorByAgeGroupSex.get(x).getBarangay().trim().equalsIgnoreCase(ageGroup[x].trim())) {
//
//                }
//            }
//        }

        return true;
    }

    /**
     * @return the arrayNoError
     */
    public ArrayList<NutritionalStatus> getArrayNoError() {
        return arrayNoError;
    }

    /**
     * @param arrayNoError the arrayNoError to set
     */
    public void setArrayNoError(ArrayList<NutritionalStatus> arrayNoError) {
        this.arrayNoError = arrayNoError;
    }

    /**
     * @return the arrayError
     */
    public ArrayList<NutritionalStatusTemp> getArrayError() {
        return arrayError;
    }

    /**
     * @param arrayError the arrayError to set
     */
    public void setArrayError(ArrayList<NutritionalStatusTemp> arrayError) {
        this.arrayError = arrayError;
    }
}
