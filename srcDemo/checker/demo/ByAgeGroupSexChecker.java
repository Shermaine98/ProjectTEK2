/*
 *  Copyright (C) ProjectTEK - DLSU CCS 2016
 *  All right Reserved   * 
 */

package checker.demo;

import model.demo.ByAgeGroupSex;
import model.temp.demo.ByAgeGroupTemp;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import static java.lang.Integer.parseInt;

/**
 *
 * @author Gian Carlo Roxas
 * @author shermaine Sy
 * @author Geraldine Atayan
 * 
 */

public class ByAgeGroupSexChecker {

    private ArrayList<ByAgeGroupSex> arrayNoError;
    private ArrayList<ByAgeGroupTemp> arrayError;
    private  boolean trigger = false;

    /*
     *@ ArrErrorByAgeGroupSex temp stored data, all strings
     */
    public ByAgeGroupSexChecker() {

    }

    public ByAgeGroupSexChecker(ArrayList<ByAgeGroupTemp> ArrErrorByAgeGroupSex) {

        ByAgeGroupSex ByAgeGroupSex;
        ByAgeGroupTemp byAgeGroupTemp;
        arrayNoError = new ArrayList<>();
        arrayError = new ArrayList<>();

        for (int i = 0; i < ArrErrorByAgeGroupSex.size(); i++) {
            byAgeGroupTemp = new ByAgeGroupTemp();
            if (ArrErrorByAgeGroupSex.get(i).getBarangay() == null
                    || ArrErrorByAgeGroupSex.get(i).getAgeGroup() == null
                    || ArrErrorByAgeGroupSex.get(i).getBothSex() == null
                    || ArrErrorByAgeGroupSex.get(i).getMaleCount() == null
                    || ArrErrorByAgeGroupSex.get(i).getFemaleCount() == null ||
                    ArrErrorByAgeGroupSex.get(i).getBarangay().equalsIgnoreCase("")
                    || ArrErrorByAgeGroupSex.get(i).getAgeGroup().equalsIgnoreCase("")
                    || ArrErrorByAgeGroupSex.get(i).getBothSex().equalsIgnoreCase("")
                    || ArrErrorByAgeGroupSex.get(i).getMaleCount().equalsIgnoreCase("")
                    || ArrErrorByAgeGroupSex.get(i).getFemaleCount().equalsIgnoreCase("")) {
                
                byAgeGroupTemp.setBarangay(ArrErrorByAgeGroupSex.get(i).getBarangay());
                byAgeGroupTemp.setAgeGroup(ArrErrorByAgeGroupSex.get(i).getAgeGroup());
                byAgeGroupTemp.setvalidation(-1);
                byAgeGroupTemp.setErrorReason("Missing Field/s");

                if (ArrErrorByAgeGroupSex.get(i).getBothSex() == null || ArrErrorByAgeGroupSex.get(i).getBothSex().equalsIgnoreCase("")) {
                    byAgeGroupTemp.setBothSex("");
                } else {
                    byAgeGroupTemp.setBothSex(ArrErrorByAgeGroupSex.get(i).getBothSex());
                }
                
                if (ArrErrorByAgeGroupSex.get(i).getMaleCount() == null || ArrErrorByAgeGroupSex.get(i).getMaleCount().equalsIgnoreCase("")) {
                    byAgeGroupTemp.setMaleCount("");
                } else {
                    byAgeGroupTemp.setMaleCount(ArrErrorByAgeGroupSex.get(i).getMaleCount());
                }

                if (ArrErrorByAgeGroupSex.get(i).getFemaleCount() == null || ArrErrorByAgeGroupSex.get(i).getFemaleCount().equalsIgnoreCase("")) {
                    byAgeGroupTemp.setFemaleCount("");
                } else {
                    byAgeGroupTemp.setFemaleCount(ArrErrorByAgeGroupSex.get(i).getFemaleCount());
                }
                trigger = true;
                arrayError.add(byAgeGroupTemp);
            } else if (isNumeric(ArrErrorByAgeGroupSex.get(i).getBothSex())
                    || isNumeric(ArrErrorByAgeGroupSex.get(i).getFemaleCount())
                    || isNumeric(ArrErrorByAgeGroupSex.get(i).getMaleCount())) {
                
                byAgeGroupTemp.setBarangay(ArrErrorByAgeGroupSex.get(i).getBarangay());
                byAgeGroupTemp.setAgeGroup(ArrErrorByAgeGroupSex.get(i).getAgeGroup());
                byAgeGroupTemp.setvalidation(-2);
                byAgeGroupTemp.setErrorReason("Format Error");

                if (isNumeric(ArrErrorByAgeGroupSex.get(i).getBothSex())) {
                    byAgeGroupTemp.setBothSex("");
                } else {
                    byAgeGroupTemp.setBothSex(ArrErrorByAgeGroupSex.get(i).getBothSex());
                }
                
                if (isNumeric(ArrErrorByAgeGroupSex.get(i).getMaleCount())) {
                    byAgeGroupTemp.setMaleCount("");
                } else {
                    byAgeGroupTemp.setMaleCount(ArrErrorByAgeGroupSex.get(i).getMaleCount());
                }

                if (isNumeric(ArrErrorByAgeGroupSex.get(i).getFemaleCount())) {
                    byAgeGroupTemp.setFemaleCount("");
                } else {
                    byAgeGroupTemp.setFemaleCount(ArrErrorByAgeGroupSex.get(i).getFemaleCount());
                }

                trigger = true;
                arrayError.add(byAgeGroupTemp);
            } else if (Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getBothSex())
                    != (Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getMaleCount())
                    + Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getFemaleCount()))) {
                

                byAgeGroupTemp.setBarangay(ArrErrorByAgeGroupSex.get(i).getBarangay());
                byAgeGroupTemp.setAgeGroup(ArrErrorByAgeGroupSex.get(i).getAgeGroup());
                byAgeGroupTemp.setvalidation(-3);
                 byAgeGroupTemp.setErrorReason("Summation Error");
                byAgeGroupTemp.setBothSex(ArrErrorByAgeGroupSex.get(i).getBothSex());
                byAgeGroupTemp.setMaleCount(ArrErrorByAgeGroupSex.get(i).getMaleCount());
                byAgeGroupTemp.setFemaleCount(ArrErrorByAgeGroupSex.get(i).getFemaleCount());
                arrayError.add(byAgeGroupTemp);
                trigger = true;
            } else {
                ByAgeGroupSex = new ByAgeGroupSex();
                ByAgeGroupSex.setAgeGroup(ArrErrorByAgeGroupSex.get(i).getAgeGroup());
                ByAgeGroupSex.setBarangay(ArrErrorByAgeGroupSex.get(i).getBarangay());
                ByAgeGroupSex.setBothSex(Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getBothSex()));
                ByAgeGroupSex.setFemaleCount(Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getFemaleCount()));
                ByAgeGroupSex.setMaleCount(Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getMaleCount()));
                ByAgeGroupSex.setValidation(1);
                arrayNoError.add(ByAgeGroupSex);
            }

        }
    }

    public ByAgeGroupSexChecker(ArrayList<ByAgeGroupTemp> ArrErrorByAgeGroupSex, int year, int formID) {

        ByAgeGroupTemp byAgeGroupTemp;
        ByAgeGroupSex ByAgeGroupSex;
        arrayNoError = new ArrayList<>();
        arrayError = new ArrayList<>();

        for (int i = 0; i < ArrErrorByAgeGroupSex.size(); i++) {
            byAgeGroupTemp = new ByAgeGroupTemp();
            if (ArrErrorByAgeGroupSex.get(i).getBarangay() == null
                    || ArrErrorByAgeGroupSex.get(i).getAgeGroup() == null
                    || ArrErrorByAgeGroupSex.get(i).getBothSex() == null
                    || ArrErrorByAgeGroupSex.get(i).getMaleCount() == null
                    || ArrErrorByAgeGroupSex.get(i).getFemaleCount() == null 
                    || ArrErrorByAgeGroupSex.get(i).getBarangay().equalsIgnoreCase("")
                    || ArrErrorByAgeGroupSex.get(i).getAgeGroup().equalsIgnoreCase("")
                    || ArrErrorByAgeGroupSex.get(i).getBothSex().equalsIgnoreCase("")
                    || ArrErrorByAgeGroupSex.get(i).getMaleCount().equalsIgnoreCase("")
                    || ArrErrorByAgeGroupSex.get(i).getFemaleCount().equalsIgnoreCase("")){
                byAgeGroupTemp.setFormID(formID);
                byAgeGroupTemp.setBarangay(ArrErrorByAgeGroupSex.get(i).getBarangay());
                byAgeGroupTemp.setAgeGroup(ArrErrorByAgeGroupSex.get(i).getAgeGroup());
                byAgeGroupTemp.setYear(year);
                byAgeGroupTemp.setvalidation(-1);
                byAgeGroupTemp.setErrorReason("Missing Filed/s");
                
                
                if (ArrErrorByAgeGroupSex.get(i).getBothSex() == null || ArrErrorByAgeGroupSex.get(i).getBothSex().equalsIgnoreCase("")) {
                    byAgeGroupTemp.setBothSex("");
                } else {
                    byAgeGroupTemp.setBothSex(ArrErrorByAgeGroupSex.get(i).getBothSex());
                }
                
                if (ArrErrorByAgeGroupSex.get(i).getMaleCount() == null || ArrErrorByAgeGroupSex.get(i).getMaleCount().equalsIgnoreCase("")) {
                    byAgeGroupTemp.setMaleCount("");
                } else {
                    byAgeGroupTemp.setMaleCount(ArrErrorByAgeGroupSex.get(i).getMaleCount());
                }

                if (ArrErrorByAgeGroupSex.get(i).getFemaleCount() == null || ArrErrorByAgeGroupSex.get(i).getFemaleCount().equalsIgnoreCase("")) {
                    byAgeGroupTemp.setFemaleCount("");
                } else {
                    byAgeGroupTemp.setFemaleCount(ArrErrorByAgeGroupSex.get(i).getFemaleCount());
                }
                
                arrayError.add(byAgeGroupTemp);
                 trigger = true;
            } else if (isNumeric(ArrErrorByAgeGroupSex.get(i).getBothSex())
                    || isNumeric(ArrErrorByAgeGroupSex.get(i).getFemaleCount())
                    || isNumeric(ArrErrorByAgeGroupSex.get(i).getMaleCount())) {

                byAgeGroupTemp.setFormID(formID);
                byAgeGroupTemp.setBarangay(ArrErrorByAgeGroupSex.get(i).getBarangay());
                byAgeGroupTemp.setAgeGroup(ArrErrorByAgeGroupSex.get(i).getAgeGroup());
                byAgeGroupTemp.setYear(year);
                byAgeGroupTemp.setvalidation(-2);
                byAgeGroupTemp.setErrorReason("Format Error");
                
                if (isNumeric(ArrErrorByAgeGroupSex.get(i).getBothSex())) {
                    byAgeGroupTemp.setBothSex("");
                } else {
                    byAgeGroupTemp.setBothSex(ArrErrorByAgeGroupSex.get(i).getBothSex());
                }
                if (isNumeric(ArrErrorByAgeGroupSex.get(i).getMaleCount())) {
                    byAgeGroupTemp.setMaleCount("");
                } else {
                    byAgeGroupTemp.setMaleCount(ArrErrorByAgeGroupSex.get(i).getMaleCount());
                }

                if (isNumeric(ArrErrorByAgeGroupSex.get(i).getFemaleCount())) {
                    byAgeGroupTemp.setFemaleCount("");
                } else {
                    byAgeGroupTemp.setFemaleCount(ArrErrorByAgeGroupSex.get(i).getFemaleCount());
                }
                trigger = true;
                arrayError.add(byAgeGroupTemp);
            } else if (Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getBothSex())
                    != (Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getMaleCount())
                    + Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getFemaleCount()))) {

                byAgeGroupTemp.setFormID(formID);
                byAgeGroupTemp.setBarangay(ArrErrorByAgeGroupSex.get(i).getBarangay());
                byAgeGroupTemp.setAgeGroup(ArrErrorByAgeGroupSex.get(i).getAgeGroup());
                byAgeGroupTemp.setYear(year);
                byAgeGroupTemp.setvalidation(-3);
                byAgeGroupTemp.setErrorReason("Summation error");
                byAgeGroupTemp.setBothSex(ArrErrorByAgeGroupSex.get(i).getBothSex());
                byAgeGroupTemp.setMaleCount(ArrErrorByAgeGroupSex.get(i).getMaleCount());
                byAgeGroupTemp.setFemaleCount(ArrErrorByAgeGroupSex.get(i).getFemaleCount());
                arrayError.add(byAgeGroupTemp);
                trigger = true;
            } else {
                ByAgeGroupSex = new ByAgeGroupSex();
                ByAgeGroupSex.setFormID(formID);
                ByAgeGroupSex.setAgeGroup(ArrErrorByAgeGroupSex.get(i).getAgeGroup());
                ByAgeGroupSex.setBarangay(ArrErrorByAgeGroupSex.get(i).getBarangay());
                ByAgeGroupSex.setBothSex(Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getBothSex()));
                ByAgeGroupSex.setFemaleCount(Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getFemaleCount()));
                ByAgeGroupSex.setMaleCount(Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getMaleCount()));
                ByAgeGroupSex.setYear(year);
                ByAgeGroupSex.setValidation(1);
                arrayNoError.add(ByAgeGroupSex);
            }

        }
    }

    public ArrayList<ByAgeGroupSex> TransformData(ArrayList<ByAgeGroupTemp> ArrErrorByAgeGroupSex) {

        ByAgeGroupSex ByAgeGroupSex;
        ArrayList<ByAgeGroupSex> TransformData = new ArrayList<>();

        for (int i = 0; i < ArrErrorByAgeGroupSex.size(); i++) {
           ByAgeGroupSex = new ByAgeGroupSex();
            if (ArrErrorByAgeGroupSex.get(i).getBarangay() == null
                    || ArrErrorByAgeGroupSex.get(i).getAgeGroup() == null
                    || ArrErrorByAgeGroupSex.get(i).getBothSex() == null
                    || ArrErrorByAgeGroupSex.get(i).getMaleCount() == null
                    || ArrErrorByAgeGroupSex.get(i).getFemaleCount() == null|| 
                        ArrErrorByAgeGroupSex.get(i).getBarangay().equalsIgnoreCase("")
                    || ArrErrorByAgeGroupSex.get(i).getAgeGroup().equalsIgnoreCase("")
                    || ArrErrorByAgeGroupSex.get(i).getBothSex().equalsIgnoreCase("")
                    || ArrErrorByAgeGroupSex.get(i).getMaleCount().equalsIgnoreCase("")
                    || ArrErrorByAgeGroupSex.get(i).getFemaleCount().equalsIgnoreCase("")){

                    ByAgeGroupSex.setFormID(ArrErrorByAgeGroupSex.get(i).getFormID());
                    ByAgeGroupSex.setBarangay(ArrErrorByAgeGroupSex.get(i).getBarangay());
                    ByAgeGroupSex.setAgeGroup(ArrErrorByAgeGroupSex.get(i).getAgeGroup());
                    ByAgeGroupSex.setYear(ArrErrorByAgeGroupSex.get(i).getYear());
                    ByAgeGroupSex.setValidation(-1);
                    ByAgeGroupSex.setReasonError("Missing Field/s");

                 if (ArrErrorByAgeGroupSex.get(i).getBothSex() == null || ArrErrorByAgeGroupSex.get(i).getBothSex().equalsIgnoreCase("")) {
                    ByAgeGroupSex.setBothSex(-1);
                } else {
                    ByAgeGroupSex.setBothSex(Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getBothSex()));
                }
                
                if (ArrErrorByAgeGroupSex.get(i).getMaleCount() == null || ArrErrorByAgeGroupSex.get(i).getMaleCount().equalsIgnoreCase("")) {
                    ByAgeGroupSex.setMaleCount(-1);
                } else {
                    ByAgeGroupSex.setMaleCount(Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getMaleCount()));
                }

                if (ArrErrorByAgeGroupSex.get(i).getFemaleCount() == null || ArrErrorByAgeGroupSex.get(i).getFemaleCount().equalsIgnoreCase("")) {
                    ByAgeGroupSex.setFemaleCount(-1);
                } else {
                    ByAgeGroupSex.setFemaleCount(Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getFemaleCount()));
                }
                 trigger = true;
                TransformData.add(ByAgeGroupSex);
                //Check if string == string and 
                // @todo int == int
            } else if (isNumeric(ArrErrorByAgeGroupSex.get(i).getBothSex())
                    || isNumeric(ArrErrorByAgeGroupSex.get(i).getFemaleCount())
                    || isNumeric(ArrErrorByAgeGroupSex.get(i).getMaleCount())) {

                    ByAgeGroupSex.setFormID(ArrErrorByAgeGroupSex.get(i).getFormID());
                    ByAgeGroupSex.setBarangay(ArrErrorByAgeGroupSex.get(i).getBarangay());
                    ByAgeGroupSex.setAgeGroup(ArrErrorByAgeGroupSex.get(i).getAgeGroup());
                    ByAgeGroupSex.setYear(ArrErrorByAgeGroupSex.get(i).getYear());
                    ByAgeGroupSex.setValidation(-2);
                    ByAgeGroupSex.setReasonError("Format Error");

                if (isNumeric(ArrErrorByAgeGroupSex.get(i).getBothSex())) {
                    ByAgeGroupSex.setBothSex(-1);
                } else {
                    ByAgeGroupSex.setBothSex(Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getBothSex()));
                }
                
                if (isNumeric(ArrErrorByAgeGroupSex.get(i).getMaleCount())) {
                    ByAgeGroupSex.setMaleCount(-1);
                } else {
                    ByAgeGroupSex.setMaleCount(Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getMaleCount()));
                }

                if (isNumeric(ArrErrorByAgeGroupSex.get(i).getFemaleCount())) {
                    ByAgeGroupSex.setFemaleCount(-1);
                } else {
                    ByAgeGroupSex.setFemaleCount(Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getFemaleCount()));
                }

                
                trigger = true;
                TransformData.add(ByAgeGroupSex);
            } else if (parseInt(ArrErrorByAgeGroupSex.get(i).getBothSex())
                    != (parseInt(ArrErrorByAgeGroupSex.get(i).getMaleCount())
                    + parseInt(ArrErrorByAgeGroupSex.get(i).getFemaleCount()))) {
                
                ByAgeGroupSex.setFormID(ArrErrorByAgeGroupSex.get(i).getFormID());
                ByAgeGroupSex.setBarangay(ArrErrorByAgeGroupSex.get(i).getBarangay());
                ByAgeGroupSex.setAgeGroup(ArrErrorByAgeGroupSex.get(i).getAgeGroup());
                ByAgeGroupSex.setYear(ArrErrorByAgeGroupSex.get(i).getYear());
                ByAgeGroupSex.setValidation(-3);
                ByAgeGroupSex.setReasonError("Summation Error");
                ByAgeGroupSex.setBothSex(Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getBothSex()));
                ByAgeGroupSex.setMaleCount(Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getMaleCount()));
                ByAgeGroupSex.setFemaleCount(Integer.parseInt(ArrErrorByAgeGroupSex.get(i).getFemaleCount()));
                TransformData.add(ByAgeGroupSex);
                trigger = true;
            }

        }
        return TransformData;
    }

    
    public static boolean isNumeric(String str) {
        return !StringUtils.isNumeric(str);
    }

    public boolean checkGrandTotal(ArrayList<ByAgeGroupTemp> ArrErrorByAgeGroupSex) {
      if( trigger == false){
        
        int totalInExcel = 0;
        int maleInExcel = 0;
        int femaleInExcel = 0;
        int totalComputed = 0;
        int maleComputed = 0;
        int femaleComputed = 0;
        for (int i = 0; i < ArrErrorByAgeGroupSex.size(); i++) {
            if (!ArrErrorByAgeGroupSex.get(i).getBarangay().equalsIgnoreCase("Caloocan City")) {
                if (!ArrErrorByAgeGroupSex.get(i).getAgeGroup().equalsIgnoreCase("All Ages")
                        && !ArrErrorByAgeGroupSex.get(i).getAgeGroup().equalsIgnoreCase("0 - 17")
                        && !ArrErrorByAgeGroupSex.get(i).getAgeGroup().equalsIgnoreCase("18 and Over")) {
                    maleComputed += parseInt(ArrErrorByAgeGroupSex.get(i).getMaleCount());
                    femaleComputed += parseInt(ArrErrorByAgeGroupSex.get(i).getFemaleCount());
                    totalComputed += (parseInt(ArrErrorByAgeGroupSex.get(i).getMaleCount())
                            + parseInt(ArrErrorByAgeGroupSex.get(i).getFemaleCount()));
                }
            }
        }
       
        for (int i = 0; i < ArrErrorByAgeGroupSex.size(); i++) {
            if (ArrErrorByAgeGroupSex.get(i).getBarangay().equalsIgnoreCase("Caloocan City")) {
                if (ArrErrorByAgeGroupSex.get(i).getAgeGroup().equalsIgnoreCase("All Ages")) {
                    totalInExcel = parseInt(ArrErrorByAgeGroupSex.get(i).getBothSex());
                    maleInExcel = parseInt(ArrErrorByAgeGroupSex.get(i).getMaleCount());
                    femaleInExcel = parseInt(ArrErrorByAgeGroupSex.get(i).getFemaleCount());
                   
                }
            }
        }

        if (totalComputed == totalInExcel) {
            if (maleComputed == maleInExcel) {
                if (femaleComputed == femaleInExcel) {
                    return true;
                }
            }
        }
      }

        return false;
    }

    //i times 20
    public static boolean isAgeGroupComplete(ArrayList<ByAgeGroupTemp> ArrErrorByAgeGroupSex) {
        boolean complete = true;//= str.chars().allMatch(Character::isDigit);

        String ageGroup[] = new String[21];
        ageGroup[0] = "All Ages";
        ageGroup[1] = "Under 1";
        ageGroup[2] = "1 - 4";
        ageGroup[3] = "5 - 9";
        ageGroup[4] = "10 - 14";
        ageGroup[5] = "15 - 19";
        ageGroup[6] = "20 - 24";
        ageGroup[7] = "25 - 29";
        ageGroup[8] = "30 - 34";
        ageGroup[9] = "35 - 39";
        ageGroup[10] = "40 - 44";
        ageGroup[11] = "45 - 49";
        ageGroup[12] = "50 - 54";
        ageGroup[13] = "55 - 59";
        ageGroup[14] = "60 - 64";
        ageGroup[15] = "65 - 69";
        ageGroup[16] = "70 - 74";
        ageGroup[17] = "75 - 79";
        ageGroup[18] = "80 and Over";
        ageGroup[19] = "0 - 17";
        ageGroup[20] = "18 and Over";

        for (ByAgeGroupTemp ArrErrorByAgeGroupSex1 : ArrErrorByAgeGroupSex) {
            for (int x = 0; x < 20; x++) {
                if (ArrErrorByAgeGroupSex.get(x).getBarangay().trim().equalsIgnoreCase(ageGroup[x].trim())) {

                }
            }
        }

        return true;
    }

    /**
     * @return the arrayNoError
     */
    public ArrayList<ByAgeGroupSex> getArrayNoError() {
        return arrayNoError;
    }

    /**
     * @param arrayNoError the arrayNoError to set
     */
    public void setArrayNoError(ArrayList<ByAgeGroupSex> arrayNoError) {
        this.arrayNoError = arrayNoError;
    }

    /**
     * @return the arrayError
     */
    public ArrayList<ByAgeGroupTemp> getArrayError() {
        return arrayError;
    }

    /**
     * @param arrayError the arrayError to set
     */
    public void setArrayError(ArrayList<ByAgeGroupTemp> arrayError) {
        this.arrayError = arrayError;
    }

}
