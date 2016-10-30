/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package checker.demo;

import model.demo.HighestCompleted;
import model.demo.HighestCompletedAgeGroup;
import model.temp.demo.HighestCompletedAgeGroupTemp;
import model.temp.demo.HighestCompletedTemp;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
/**
 *
 * @author Gian Carlo Roxas
 * @author shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class HighestCompletedChecker {

    private ArrayList<HighestCompleted> arrayNoError;
    private ArrayList<HighestCompletedTemp> arrayError;
    private boolean trigger = false;

    /*
     *@ ArrError temp stored data, all strings
     */
    public HighestCompletedChecker(ArrayList<HighestCompletedTemp> ArrError) {

        HighestCompleted HighestCompleted;
        HighestCompletedTemp HighestCompletedTemp;
        arrayNoError = new ArrayList<>();
        arrayError = new ArrayList<>();

        ArrayList<HighestCompletedAgeGroup> arrHighestCompletedAgeGroup;
        ArrayList<HighestCompletedAgeGroupTemp> arrHighestCompletedAgeGroupTemp;

        HighestCompletedAgeGroupTemp HighestCompletedAgeGroupTemp;
        HighestCompletedAgeGroup HighestCompletedAgeGroup;

        for (int i = 0; i < ArrError.size(); i++) {
            HighestCompletedTemp = new HighestCompletedTemp();
            arrHighestCompletedAgeGroup = new ArrayList<HighestCompletedAgeGroup>();
            arrHighestCompletedAgeGroupTemp = new ArrayList<HighestCompletedAgeGroupTemp>();

            HighestCompletedTemp.setLocation(ArrError.get(i).getLocation());
            HighestCompletedTemp.setSex(ArrError.get(i).getSex());
            HighestCompletedTemp.setAgeGroup(ArrError.get(i).getAgeGroup());
            HighestCompletedTemp.setTotal(ArrError.get(i).getTotal());
            //CHECK UNG SA NutritionalStatus
            for (int y = 0; y < ArrError.get(i).getHighestCompletedAgeGroupTemp().size(); y++) {
                HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                if (ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount() == null
                        || ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount().equalsIgnoreCase("")) {

                    if (ArrError.get(i).getHighestCompletedAgeGroupTemp()
                            .get(y).getCount() == null || ArrError.get(i)
                            .getHighestCompletedAgeGroupTemp().get(y).
                            getCount().equalsIgnoreCase("")) {
                        HighestCompletedAgeGroupTemp.setCount("-1");
                    } else {
                        HighestCompletedAgeGroupTemp.setCount(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount());
                    }

                    HighestCompletedAgeGroupTemp.setValidation(false);
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    HighestCompletedTemp.setHighestCompletedAgeGroupTemp(arrHighestCompletedAgeGroupTemp);
                    arrayError.add(HighestCompletedTemp);
                } else if (isNumeric(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount())) {

                    if (isNumeric(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount())) {
                        HighestCompletedAgeGroupTemp.setCount("-1");
                    } else {
                        HighestCompletedAgeGroupTemp.setCount(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount());
                    }
                    HighestCompletedAgeGroupTemp.setValidation(false);
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    HighestCompletedTemp.setHighestCompletedAgeGroupTemp(arrHighestCompletedAgeGroupTemp);
                    arrayError.add(HighestCompletedTemp);
                } else {
                    HighestCompletedAgeGroup = new HighestCompletedAgeGroup();
                    HighestCompletedAgeGroup.sethighestCompleted(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                    HighestCompletedAgeGroup.setCount(Integer.parseInt(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount()));
                    HighestCompletedAgeGroup.setValidation(true);
                    arrHighestCompletedAgeGroup.add(HighestCompletedAgeGroup);
                }
            }

            HighestCompleted = new HighestCompleted();
            HighestCompleted.setLocation(ArrError.get(i).getLocation());
            HighestCompleted.setSex(ArrError.get(i).getSex());
            HighestCompleted.setAgeGroup(ArrError.get(i).getAgeGroup());
            HighestCompleted.setTotal(Integer.parseInt(ArrError.get(i).getTotal()));
            HighestCompleted.setHighestCompletedAgeGroup(arrHighestCompletedAgeGroup);
            arrayNoError.add(HighestCompleted);
        }
    }

    public HighestCompletedChecker(ArrayList<HighestCompletedTemp> ArrError, int year, int formID) {

        HighestCompleted HighestCompleted;
        HighestCompletedTemp HighestCompletedTemp;
        arrayNoError = new ArrayList<>();
        arrayError = new ArrayList<>();

        ArrayList<HighestCompletedAgeGroup> arrHighestCompletedAgeGroup;
        ArrayList<HighestCompletedAgeGroupTemp> arrHighestCompletedAgeGroupTemp;

        HighestCompletedAgeGroupTemp HighestCompletedAgeGroupTemp;
        HighestCompletedAgeGroup HighestCompletedAgeGroup;

        for (int i = 0; i < ArrError.size(); i++) {
            HighestCompletedTemp = new HighestCompletedTemp();
            arrHighestCompletedAgeGroup = new ArrayList<HighestCompletedAgeGroup>();
            arrHighestCompletedAgeGroupTemp = new ArrayList<HighestCompletedAgeGroupTemp>();
            HighestCompletedTemp.setFormID(formID);
            HighestCompletedTemp.setYear(year);
            HighestCompletedTemp.setLocation(ArrError.get(i).getLocation());
            HighestCompletedTemp.setSex(ArrError.get(i).getSex());
            HighestCompletedTemp.setAgeGroup(ArrError.get(i).getAgeGroup());
            HighestCompletedTemp.setTotal(ArrError.get(i).getTotal());
            //CHECK UNG SA NutritionalStatus
            for (int y = 0; y < ArrError.get(i).getHighestCompletedAgeGroupTemp().size(); y++) {
                HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                if (ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount() == null
                        || ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount().equalsIgnoreCase("")) {

                    if (ArrError.get(i).getHighestCompletedAgeGroupTemp()
                            .get(y).getCount() == null || ArrError.get(i)
                            .getHighestCompletedAgeGroupTemp().get(y).
                            getCount().equalsIgnoreCase("")) {
                        HighestCompletedAgeGroupTemp.setCount("-1");
                    } else {
                        HighestCompletedAgeGroupTemp.setCount(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount());
                    }

                    HighestCompletedAgeGroupTemp.setValidation(false);
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);

                    HighestCompletedTemp.setHighestCompletedAgeGroupTemp(arrHighestCompletedAgeGroupTemp);
                    arrayError.add(HighestCompletedTemp);
                } else if (isNumeric(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount())) {

                    if (isNumeric(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount())) {
                        HighestCompletedAgeGroupTemp.setCount("-1");
                    } else {
                        HighestCompletedAgeGroupTemp.setCount(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount());
                    }
                    HighestCompletedAgeGroupTemp.setValidation(false);
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);

                    HighestCompletedTemp.setHighestCompletedAgeGroupTemp(arrHighestCompletedAgeGroupTemp);
                    arrayError.add(HighestCompletedTemp);
                } else {
                    HighestCompletedAgeGroup = new HighestCompletedAgeGroup();
                    HighestCompletedAgeGroup.sethighestCompleted(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                    HighestCompletedAgeGroup.setCount(Integer.parseInt(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount()));
                    HighestCompletedAgeGroup.setValidation(true);
                    arrHighestCompletedAgeGroup.add(HighestCompletedAgeGroup);
                }
            }

            HighestCompleted = new HighestCompleted();
            HighestCompleted.setFormID(formID);
            HighestCompleted.setYear(year);
            HighestCompleted.setLocation(ArrError.get(i).getLocation());
            HighestCompleted.setSex(ArrError.get(i).getSex());
            HighestCompleted.setAgeGroup(ArrError.get(i).getAgeGroup());
            HighestCompleted.setTotal(Integer.parseInt(ArrError.get(i).getTotal()));
            HighestCompleted.setHighestCompletedAgeGroup(arrHighestCompletedAgeGroup);
            arrayNoError.add(HighestCompleted);
        }
    }

    public ArrayList<HighestCompleted> TransformData(ArrayList<HighestCompletedTemp> ArrError) {
        HighestCompleted HighestCompleted;
        ArrayList<HighestCompleted> TransformData = new ArrayList<>();

        ArrayList<HighestCompletedAgeGroup> arrHighestCompletedAgeGroup;

        HighestCompletedAgeGroup HighestCompletedAgeGroup;
        for (int i = 0; i < ArrError.size(); i++) {
            HighestCompleted = new HighestCompleted();
            arrHighestCompletedAgeGroup = new ArrayList<HighestCompletedAgeGroup>();

            HighestCompleted.setFormID(ArrError.get(i).getFormID());
            HighestCompleted.setYear(ArrError.get(i).getYear());
            HighestCompleted.setLocation(ArrError.get(i).getLocation());
            HighestCompleted.setSex(ArrError.get(i).getSex());
            HighestCompleted.setAgeGroup(ArrError.get(i).getAgeGroup());
            HighestCompleted.setTotal(Integer.parseInt(ArrError.get(i).getTotal()));
            //CHECK UNG SA NutritionalStatus
            for (int y = 0; y < ArrError.get(i).getHighestCompletedAgeGroupTemp().size(); y++) {
                HighestCompletedAgeGroup = new HighestCompletedAgeGroup();
                if (ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount() == null
                        || ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount().equalsIgnoreCase("")) {

                    if (ArrError.get(i).getHighestCompletedAgeGroupTemp()
                            .get(y).getCount() == null || ArrError.get(i)
                            .getHighestCompletedAgeGroupTemp().get(y).
                            getCount().equalsIgnoreCase("")) {
                        HighestCompletedAgeGroup.setCount(-1);
                    } else {
                        HighestCompletedAgeGroup.setCount(Integer.parseInt(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount()));
                    }

                    HighestCompletedAgeGroup.setValidation(false);
                    arrHighestCompletedAgeGroup.add(HighestCompletedAgeGroup);
                } else if (isNumeric(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount())) {

                    if (isNumeric(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount())) {
                        HighestCompletedAgeGroup.setCount(-1);
                    } else {
                        HighestCompletedAgeGroup.setCount(Integer.parseInt(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount()));
                    }
                    HighestCompletedAgeGroup.setValidation(false);
                    arrHighestCompletedAgeGroup.add(HighestCompletedAgeGroup);
                }
            }
            TransformData.add(HighestCompleted);
        }
        return TransformData;
    }

    public static boolean isNumeric(String str) {
        return !StringUtils.isNumeric(str);
    }

    public HighestCompletedChecker() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the arrayNoError
     */
    public ArrayList<HighestCompleted> getArrayNoError() {
        return arrayNoError;
    }

    /**
     * @param arrayNoError the arrayNoError to set
     */
    public void setArrayNoError(ArrayList<HighestCompleted> arrayNoError) {
        this.arrayNoError = arrayNoError;
    }

    /**
     * @return the arrayError
     */
    public ArrayList<HighestCompletedTemp> getArrayError() {
        return arrayError;
    }

    /**
     * @param arrayError the arrayError to set
     */
    public void setArrayError(ArrayList<HighestCompletedTemp> arrayError) {
        this.arrayError = arrayError;
    }
}
