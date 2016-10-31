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

        for (int i = 0; i < ArrError.size(); i++) {
            if (ArrError.get(i).getTotal() == null || ArrError.get(i).getTotal().equalsIgnoreCase("")) {
                HighestCompletedTemp = new HighestCompletedTemp();
                HighestCompletedTemp.setLocation(ArrError.get(i).getLocation());
                HighestCompletedTemp.setSex(ArrError.get(i).getSex());
                HighestCompletedTemp.setAgeGroup(ArrError.get(i).getAgeGroup());

                arrHighestCompletedAgeGroupTemp = new ArrayList<HighestCompletedAgeGroupTemp>();
                HighestCompletedTemp.setTotal("");
                for (int y = 0; y < ArrError.get(i).getHighestCompletedAgeGroupTemp().size(); y++) {
                    HighestCompletedAgeGroupTemp HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    if (ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount() == null
                            || ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount().equalsIgnoreCase("")) {

                        HighestCompletedAgeGroupTemp.setCount("");
                        HighestCompletedAgeGroupTemp.setValidation(-1);
                        HighestCompletedAgeGroupTemp.setReason("Missing Field/s");
                        HighestCompletedAgeGroupTemp.sethighestAttaintment(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                        arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    } else if (isNumeric(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount())) {
                        HighestCompletedAgeGroupTemp.setValidation(-2);
                        HighestCompletedAgeGroupTemp.setReason("Format Error");
                        HighestCompletedAgeGroupTemp.setCount("");
                        HighestCompletedAgeGroupTemp.sethighestAttaintment(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                        arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    } else {
                        HighestCompletedAgeGroupTemp.setCount(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount());
                        HighestCompletedAgeGroupTemp.sethighestAttaintment(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                        HighestCompletedAgeGroupTemp.setValidation(1);
                        HighestCompletedAgeGroupTemp.setReason("");
                        arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);

                    }
                }
                HighestCompletedTemp.setValidation(-1);
                HighestCompletedTemp.setReason("Missing Field/s");
                HighestCompletedTemp.setHighestCompletedAgeGroupTemp(arrHighestCompletedAgeGroupTemp);
                arrayError.add(HighestCompletedTemp);
            } else if (isNumeric(ArrError.get(i).getTotal())) {
                HighestCompletedTemp = new HighestCompletedTemp();
                HighestCompletedTemp.setLocation(ArrError.get(i).getLocation());
                HighestCompletedTemp.setSex(ArrError.get(i).getSex());
                HighestCompletedTemp.setAgeGroup(ArrError.get(i).getAgeGroup());

                arrHighestCompletedAgeGroupTemp = new ArrayList<HighestCompletedAgeGroupTemp>();
                HighestCompletedTemp.setTotal("");
                for (int y = 0; y < ArrError.get(y).getHighestCompletedAgeGroupTemp().size(); y++) {
                    HighestCompletedAgeGroupTemp HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    if (ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount() == null
                            || ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount().equalsIgnoreCase("")) {

                        HighestCompletedAgeGroupTemp.setCount("");
                        HighestCompletedAgeGroupTemp.setValidation(-1);
                        HighestCompletedAgeGroupTemp.setReason("Missing Field/s");
                        HighestCompletedAgeGroupTemp.sethighestAttaintment(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                        arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    } else if (isNumeric(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount())) {
                        HighestCompletedAgeGroupTemp.setValidation(-2);
                        HighestCompletedAgeGroupTemp.setReason("Format Error");
                        HighestCompletedAgeGroupTemp.setCount("");
                        HighestCompletedAgeGroupTemp.sethighestAttaintment(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                        arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    } else {
                        HighestCompletedAgeGroupTemp.setCount(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount());
                        HighestCompletedAgeGroupTemp.sethighestAttaintment(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                        HighestCompletedAgeGroupTemp.setValidation(1);
                        HighestCompletedAgeGroupTemp.setReason("");
                        arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    }
                }
                HighestCompletedTemp.setValidation(-1);
                HighestCompletedTemp.setReason("Missing Field/s");
                HighestCompletedTemp.setHighestCompletedAgeGroupTemp(arrHighestCompletedAgeGroupTemp);
                arrayError.add(HighestCompletedTemp);
            } else {
                // ELSE IF TOTAL IS NOT NULL OR STRING

                arrHighestCompletedAgeGroupTemp = new ArrayList<HighestCompletedAgeGroupTemp>();

                boolean x = false;

                //CHECKING IF MAY MAGERRROR IF MAY MAG EERROR, TEMP NA LAHAT GAGAMITIN
                //IF WALA NA MAGERROR NOT TEMP
                for (int y = 0; y < ArrError.get(i).getHighestCompletedAgeGroupTemp().size(); y++) {
                    if (ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount() == null
                            || ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount().equalsIgnoreCase("")) {
                        x = true;
                    } else if (isNumeric(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount())) {
                        x = true;
                    }
                }

                // HERE NYA AAYUSIN UNG LAMAN
                if (x) {
                    HighestCompletedTemp = new HighestCompletedTemp();
                    HighestCompletedTemp.setLocation(ArrError.get(i).getLocation());
                    HighestCompletedTemp.setSex(ArrError.get(i).getSex());
                    HighestCompletedTemp.setAgeGroup(ArrError.get(i).getAgeGroup());
                    HighestCompletedTemp.setTotal(ArrError.get(i).getTotal());
                    for (int y = 0; y < ArrError.get(i).getHighestCompletedAgeGroupTemp().size(); y++) {
                        HighestCompletedAgeGroupTemp HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                        if (ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount() == null
                                || ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount().equalsIgnoreCase("")) {

                            HighestCompletedAgeGroupTemp.setValidation(-1);
                            HighestCompletedAgeGroupTemp.setReason("Missing Field/s");
                            HighestCompletedAgeGroupTemp.setCount("");
                            HighestCompletedAgeGroupTemp.sethighestAttaintment(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                            arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                        } else if (isNumeric(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount())) {
                            HighestCompletedAgeGroupTemp.setValidation(-2);
                            HighestCompletedAgeGroupTemp.setReason("Format Error");
                            HighestCompletedAgeGroupTemp.setCount("");
                            HighestCompletedAgeGroupTemp.sethighestAttaintment(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                            arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                        } else {
                            HighestCompletedAgeGroupTemp.setCount(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount());
                            HighestCompletedAgeGroupTemp.sethighestAttaintment(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                            HighestCompletedAgeGroupTemp.setValidation(1);
                            HighestCompletedAgeGroupTemp.setReason("");
                            arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                        }

                    }
                    HighestCompletedTemp.setValidation(1);
                    HighestCompletedTemp.setHighestCompletedAgeGroupTemp(arrHighestCompletedAgeGroupTemp);
                    arrayError.add(HighestCompletedTemp);
                } else {
                    int total = 0;
                    arrHighestCompletedAgeGroup = new ArrayList<>();
                    for (int y = 0; y < ArrError.get(i).getHighestCompletedAgeGroupTemp().size(); y++) {
                        HighestCompletedAgeGroup HighestCompletedAgeGroup = new HighestCompletedAgeGroup();
                        HighestCompletedAgeGroup.setCount(Integer.parseInt(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount()));
                        HighestCompletedAgeGroup.sethighestCompleted(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                        HighestCompletedAgeGroup.setValidation(1);
                        arrHighestCompletedAgeGroup.add(HighestCompletedAgeGroup);

                        total += Integer.parseInt(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount());
                    }

//                    if (total != Integer.parseInt(ArrError.get(i).getTotal())) {
//                        HighestCompletedTemp = new HighestCompletedTemp();
//                        HighestCompletedTemp.setLocation(ArrError.get(i).getLocation());
//                        HighestCompletedTemp.setSex(ArrError.get(i).getSex());
//                        HighestCompletedTemp.setAgeGroup(ArrError.get(i).getAgeGroup());
//
//                        for (int y = 0; y < ArrError.get(i).getHighestCompletedAgeGroupTemp().size(); y++) {
//                            HighestCompletedAgeGroupTemp HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
//                            HighestCompletedAgeGroupTemp.setCount("");
//                            HighestCompletedAgeGroupTemp.sethighestAttaintment(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
//                            arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
//                        }
//                        HighestCompletedTemp.setHighestCompletedAgeGroupTemp(arrHighestCompletedAgeGroupTemp);
//                        arrayError.add(HighestCompletedTemp);
//                    } else {
                    HighestCompleted = new HighestCompleted();
                    HighestCompleted.setLocation(ArrError.get(i).getLocation());
                    HighestCompleted.setSex(ArrError.get(i).getSex());
                    HighestCompleted.setAgeGroup(ArrError.get(i).getAgeGroup());
                    HighestCompleted.setTotal(Integer.parseInt(ArrError.get(i).getTotal()));
                    HighestCompleted.setHighestCompletedAgeGroup(arrHighestCompletedAgeGroup);
                    HighestCompleted.setValidation(1);
                    arrayNoError.add(HighestCompleted);
//                    }

                }

            }
        }

    }

    public HighestCompletedChecker(ArrayList<HighestCompletedTemp> ArrError, int year, int formID) {

        HighestCompleted HighestCompleted;
        HighestCompletedTemp HighestCompletedTemp;
        arrayNoError = new ArrayList<>();
        arrayError = new ArrayList<>();

        ArrayList<HighestCompletedAgeGroup> arrHighestCompletedAgeGroup;
        ArrayList<HighestCompletedAgeGroupTemp> arrHighestCompletedAgeGroupTemp;

        for (int i = 0; i < ArrError.size(); i++) {
            if (ArrError.get(i).getTotal() == null || ArrError.get(i).getTotal().equalsIgnoreCase("")) {
                HighestCompletedTemp = new HighestCompletedTemp();
                HighestCompletedTemp.setYear(year);
                HighestCompletedTemp.setFormID(formID);
                HighestCompletedTemp.setLocation(ArrError.get(i).getLocation());
                HighestCompletedTemp.setSex(ArrError.get(i).getSex());
                HighestCompletedTemp.setAgeGroup(ArrError.get(i).getAgeGroup());

                arrHighestCompletedAgeGroupTemp = new ArrayList<HighestCompletedAgeGroupTemp>();
                HighestCompletedTemp.setTotal("");
                for (int y = 0; y < ArrError.get(i).getHighestCompletedAgeGroupTemp().size(); y++) {
                    HighestCompletedAgeGroupTemp HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    if (ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount() == null
                            || ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount().equalsIgnoreCase("")) {

                        HighestCompletedAgeGroupTemp.setCount("");
                        HighestCompletedAgeGroupTemp.setValidation(-1);
                        HighestCompletedAgeGroupTemp.setReason("Missing Field/s");
                        HighestCompletedAgeGroupTemp.sethighestAttaintment(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                        arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    } else if (isNumeric(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount())) {
                        HighestCompletedAgeGroupTemp.setValidation(-2);
                        HighestCompletedAgeGroupTemp.setReason("Format Error");
                        HighestCompletedAgeGroupTemp.setCount("");
                        HighestCompletedAgeGroupTemp.sethighestAttaintment(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                        arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    } else {
                        HighestCompletedAgeGroupTemp.setCount(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount());
                        HighestCompletedAgeGroupTemp.sethighestAttaintment(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                        HighestCompletedAgeGroupTemp.setValidation(1);

                        HighestCompletedAgeGroupTemp.setReason("");
                        arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);

                    }
                }
                HighestCompletedTemp.setValidation(-1);
                HighestCompletedTemp.setReason("Missing Field/s");
                HighestCompletedTemp.setHighestCompletedAgeGroupTemp(arrHighestCompletedAgeGroupTemp);
                arrayError.add(HighestCompletedTemp);
            } else if (isNumeric(ArrError.get(i).getTotal())) {
                HighestCompletedTemp = new HighestCompletedTemp();
                HighestCompletedTemp.setYear(year);
                HighestCompletedTemp.setFormID(formID);
                HighestCompletedTemp.setLocation(ArrError.get(i).getLocation());
                HighestCompletedTemp.setSex(ArrError.get(i).getSex());
                HighestCompletedTemp.setAgeGroup(ArrError.get(i).getAgeGroup());

                arrHighestCompletedAgeGroupTemp = new ArrayList<HighestCompletedAgeGroupTemp>();
                HighestCompletedTemp.setTotal("");
                for (int y = 0; y < ArrError.get(y).getHighestCompletedAgeGroupTemp().size(); y++) {
                    HighestCompletedAgeGroupTemp HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                    if (ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount() == null
                            || ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount().equalsIgnoreCase("")) {

                        HighestCompletedAgeGroupTemp.setCount("");
                        HighestCompletedAgeGroupTemp.setValidation(-1);
                        HighestCompletedAgeGroupTemp.setReason("Missing Field/s");
                        HighestCompletedAgeGroupTemp.sethighestAttaintment(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                        arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    } else if (isNumeric(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount())) {
                        HighestCompletedAgeGroupTemp.setValidation(-2);
                        HighestCompletedAgeGroupTemp.setReason("Format Error");
                        HighestCompletedAgeGroupTemp.setCount("");
                        HighestCompletedAgeGroupTemp.sethighestAttaintment(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                        arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    } else {
                        HighestCompletedAgeGroupTemp.setCount(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount());
                        HighestCompletedAgeGroupTemp.sethighestAttaintment(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                        HighestCompletedAgeGroupTemp.setValidation(1);

                        HighestCompletedAgeGroupTemp.setReason("");
                        arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                    }
                }
                HighestCompletedTemp.setValidation(-1);
                HighestCompletedTemp.setReason("Missing Field/s");
                HighestCompletedTemp.setHighestCompletedAgeGroupTemp(arrHighestCompletedAgeGroupTemp);
                arrayError.add(HighestCompletedTemp);
            } else {
                // ELSE IF TOTAL IS NOT NULL OR STRING

                arrHighestCompletedAgeGroupTemp = new ArrayList<HighestCompletedAgeGroupTemp>();

                boolean x = false;

                //CHECKING IF MAY MAGERRROR IF MAY MAG EERROR, TEMP NA LAHAT GAGAMITIN
                //IF WALA NA MAGERROR NOT TEMP
                for (int y = 0; y < ArrError.get(i).getHighestCompletedAgeGroupTemp().size(); y++) {
                    if (ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount() == null
                            || ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount().equalsIgnoreCase("")) {
                        x = true;
                    } else if (isNumeric(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount())) {
                        x = true;
                    }
                }

                // HERE NYA AAYUSIN UNG LAMAN
                if (x) {
                    HighestCompletedTemp = new HighestCompletedTemp();
                    HighestCompletedTemp.setYear(year);
                    HighestCompletedTemp.setFormID(formID);
                    HighestCompletedTemp.setLocation(ArrError.get(i).getLocation());
                    HighestCompletedTemp.setSex(ArrError.get(i).getSex());
                    HighestCompletedTemp.setAgeGroup(ArrError.get(i).getAgeGroup());
                    HighestCompletedTemp.setTotal(ArrError.get(i).getTotal());
                    for (int y = 0; y < ArrError.get(i).getHighestCompletedAgeGroupTemp().size(); y++) {
                        HighestCompletedAgeGroupTemp HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
                        if (ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount() == null
                                || ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount().equalsIgnoreCase("")) {

                            HighestCompletedAgeGroupTemp.setValidation(-1);
                            HighestCompletedAgeGroupTemp.setReason("Missing Field/s");
                            HighestCompletedAgeGroupTemp.setCount("");
                            HighestCompletedAgeGroupTemp.sethighestAttaintment(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                            arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                        } else if (isNumeric(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount())) {
                            HighestCompletedAgeGroupTemp.setValidation(-2);
                            HighestCompletedAgeGroupTemp.setReason("Format Error");
                            HighestCompletedAgeGroupTemp.setCount("");
                            HighestCompletedAgeGroupTemp.sethighestAttaintment(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                            arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                        } else {
                            HighestCompletedAgeGroupTemp.setCount(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount());
                            HighestCompletedAgeGroupTemp.sethighestAttaintment(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                            HighestCompletedAgeGroupTemp.setValidation(1);
                            arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
                        }

                    }
                    HighestCompletedTemp.setValidation(1);
                    HighestCompletedTemp.setHighestCompletedAgeGroupTemp(arrHighestCompletedAgeGroupTemp);
                    arrayError.add(HighestCompletedTemp);
                } else {
                    int total = 0;
                    arrHighestCompletedAgeGroup = new ArrayList<>();
                    for (int y = 0; y < ArrError.get(i).getHighestCompletedAgeGroupTemp().size(); y++) {
                        HighestCompletedAgeGroup HighestCompletedAgeGroup = new HighestCompletedAgeGroup();
                        HighestCompletedAgeGroup.setCount(Integer.parseInt(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount()));
                        HighestCompletedAgeGroup.sethighestCompleted(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                        HighestCompletedAgeGroup.setValidation(1);
                        arrHighestCompletedAgeGroup.add(HighestCompletedAgeGroup);

                        total += Integer.parseInt(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount());
                    }

//                    if (total != Integer.parseInt(ArrError.get(i).getTotal())) {
//                        HighestCompletedTemp = new HighestCompletedTemp();
//                        HighestCompletedTemp.setLocation(ArrError.get(i).getLocation());
//                        HighestCompletedTemp.setSex(ArrError.get(i).getSex());
//                        HighestCompletedTemp.setAgeGroup(ArrError.get(i).getAgeGroup());
//
//                        for (int y = 0; y < ArrError.get(i).getHighestCompletedAgeGroupTemp().size(); y++) {
//                            HighestCompletedAgeGroupTemp HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
//                            HighestCompletedAgeGroupTemp.setCount("");
//                            HighestCompletedAgeGroupTemp.sethighestAttaintment(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
//                            arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
//                        }
//                        HighestCompletedTemp.setHighestCompletedAgeGroupTemp(arrHighestCompletedAgeGroupTemp);
//                        arrayError.add(HighestCompletedTemp);
//                    } else {
                    HighestCompleted = new HighestCompleted();
                    HighestCompleted.setFormID(formID);
                    HighestCompleted.setYear(year);
                    HighestCompleted.setLocation(ArrError.get(i).getLocation());
                    HighestCompleted.setSex(ArrError.get(i).getSex());
                    HighestCompleted.setAgeGroup(ArrError.get(i).getAgeGroup());
                    HighestCompleted.setTotal(Integer.parseInt(ArrError.get(i).getTotal()));
                    HighestCompleted.setHighestCompletedAgeGroup(arrHighestCompletedAgeGroup);
                    HighestCompleted.setValidation(1);
                    arrayNoError.add(HighestCompleted);
//                    }

                }

            }
        }
    }

    public ArrayList<HighestCompleted> TransformData(ArrayList<HighestCompletedTemp> ArrError) {
        HighestCompleted HighestCompleted;
        ArrayList<HighestCompleted> TransformData = new ArrayList<>();

        ArrayList<HighestCompletedAgeGroup> arrHighestCompletedAgeGroup;

        for (int i = 0; i < ArrError.size(); i++) {
            if (ArrError.get(i).getTotal() == null || ArrError.get(i).getTotal().equalsIgnoreCase("")) {
                HighestCompleted = new HighestCompleted();
                HighestCompleted.setYear(ArrError.get(i).getYear());
                HighestCompleted.setFormID(ArrError.get(i).getFormID());
                HighestCompleted.setLocation(ArrError.get(i).getLocation());
                HighestCompleted.setSex(ArrError.get(i).getSex());
                HighestCompleted.setAgeGroup(ArrError.get(i).getAgeGroup());

                arrHighestCompletedAgeGroup = new ArrayList<HighestCompletedAgeGroup>();
                HighestCompleted.setTotal(-1);
                for (int y = 0; y < ArrError.get(i).getHighestCompletedAgeGroupTemp().size(); y++) {
                    HighestCompletedAgeGroup HighestCompletedAgeGroup = new HighestCompletedAgeGroup();
                    if (ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount() == null
                            || ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount().equalsIgnoreCase("")) {

                        HighestCompletedAgeGroup.setCount(-1);
                        HighestCompletedAgeGroup.setValidation(-1);
                        HighestCompletedAgeGroup.sethighestCompleted(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                        arrHighestCompletedAgeGroup.add(HighestCompletedAgeGroup);
                    } else if (isNumeric(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount())) {
                        HighestCompletedAgeGroup.setValidation(-2);
                        HighestCompletedAgeGroup.setCount(-1);
                        HighestCompletedAgeGroup.sethighestCompleted(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                        arrHighestCompletedAgeGroup.add(HighestCompletedAgeGroup);
                    } else {
                        HighestCompletedAgeGroup.setCount(Integer.parseInt(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount()));
                        HighestCompletedAgeGroup.sethighestCompleted(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                        HighestCompletedAgeGroup.setValidation(1);
                        arrHighestCompletedAgeGroup.add(HighestCompletedAgeGroup);

                    }
                }
                HighestCompleted.setValidation(-1);
                HighestCompleted.setHighestCompletedAgeGroup(arrHighestCompletedAgeGroup);
                TransformData.add(HighestCompleted);
            } else if (isNumeric(ArrError.get(i).getTotal())) {
                HighestCompleted = new HighestCompleted();
                HighestCompleted.setYear(ArrError.get(i).getYear());
                HighestCompleted.setFormID(ArrError.get(i).getFormID());
                HighestCompleted.setLocation(ArrError.get(i).getLocation());
                HighestCompleted.setSex(ArrError.get(i).getSex());
                HighestCompleted.setAgeGroup(ArrError.get(i).getAgeGroup());

                arrHighestCompletedAgeGroup = new ArrayList<HighestCompletedAgeGroup>();
                HighestCompleted.setTotal(-1);
                for (int y = 0; y < ArrError.get(y).getHighestCompletedAgeGroupTemp().size(); y++) {
                    HighestCompletedAgeGroup HighestCompletedAgeGroup = new HighestCompletedAgeGroup();
                    if (ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount() == null
                            || ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount().equalsIgnoreCase("")) {

                        HighestCompletedAgeGroup.setCount(-1);
                        HighestCompletedAgeGroup.setValidation(-1);
                        HighestCompletedAgeGroup.sethighestCompleted(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                        arrHighestCompletedAgeGroup.add(HighestCompletedAgeGroup);
                    } else if (isNumeric(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount())) {
                        HighestCompletedAgeGroup.setValidation(-2);
                        HighestCompletedAgeGroup.setCount(-1);
                        HighestCompletedAgeGroup.sethighestCompleted(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                        arrHighestCompletedAgeGroup.add(HighestCompletedAgeGroup);
                    } else {
                        HighestCompletedAgeGroup.setCount(Integer.parseInt(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount()));
                        HighestCompletedAgeGroup.sethighestCompleted(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                        HighestCompletedAgeGroup.setValidation(1);
                        arrHighestCompletedAgeGroup.add(HighestCompletedAgeGroup);
                    }
                }
                HighestCompleted.setValidation(-1);
                HighestCompleted.setHighestCompletedAgeGroup(arrHighestCompletedAgeGroup);
                TransformData.add(HighestCompleted);
            } else {
                // ELSE IF TOTAL IS NOT NULL OR STRING

                arrHighestCompletedAgeGroup = new ArrayList<HighestCompletedAgeGroup>();

                boolean x = false;

                //CHECKING IF MAY MAGERRROR IF MAY MAG EERROR, TEMP NA LAHAT GAGAMITIN
                //IF WALA NA MAGERROR NOT TEMP
                for (int y = 0; y < ArrError.get(i).getHighestCompletedAgeGroupTemp().size(); y++) {
                    if (ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount() == null
                            || ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount().equalsIgnoreCase("")) {
                        x = true;
                    } else if (isNumeric(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount())) {
                        x = true;
                    }
                }

                // HERE NYA AAYUSIN UNG LAMAN
                if (x) {
                    HighestCompleted = new HighestCompleted();
                    HighestCompleted.setYear(ArrError.get(i).getYear());
                    HighestCompleted.setFormID(ArrError.get(i).getFormID());
                    HighestCompleted.setLocation(ArrError.get(i).getLocation());
                    HighestCompleted.setSex(ArrError.get(i).getSex());
                    HighestCompleted.setAgeGroup(ArrError.get(i).getAgeGroup());
                    HighestCompleted.setTotal(Integer.parseInt(ArrError.get(i).getTotal()));
                    for (int y = 0; y < ArrError.get(i).getHighestCompletedAgeGroupTemp().size(); y++) {
                        HighestCompletedAgeGroup HighestCompletedAgeGroup = new HighestCompletedAgeGroup();
                        if (ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount() == null
                                || ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount().equalsIgnoreCase("")) {

                            HighestCompletedAgeGroup.setValidation(-1);
                            HighestCompletedAgeGroup.setCount(-1);
                            HighestCompletedAgeGroup.sethighestCompleted(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                            arrHighestCompletedAgeGroup.add(HighestCompletedAgeGroup);
                        } else if (isNumeric(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount())) {
                            HighestCompletedAgeGroup.setValidation(-2);
                            HighestCompletedAgeGroup.setCount(-1);
                            HighestCompletedAgeGroup.sethighestCompleted(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                            arrHighestCompletedAgeGroup.add(HighestCompletedAgeGroup);
                        } else {
                            HighestCompletedAgeGroup.setCount(Integer.parseInt(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount()));
                            HighestCompletedAgeGroup.sethighestCompleted(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                            HighestCompletedAgeGroup.setValidation(1);
                            arrHighestCompletedAgeGroup.add(HighestCompletedAgeGroup);
                        }

                    }
                    HighestCompleted.setValidation(1);
                    HighestCompleted.setHighestCompletedAgeGroup(arrHighestCompletedAgeGroup);
                    TransformData.add(HighestCompleted);
                } else {
                    int total = 0;
                    arrHighestCompletedAgeGroup = new ArrayList<>();
                    for (int y = 0; y < ArrError.get(i).getHighestCompletedAgeGroupTemp().size(); y++) {
                        HighestCompletedAgeGroup HighestCompletedAgeGroup = new HighestCompletedAgeGroup();
                        HighestCompletedAgeGroup.setCount(Integer.parseInt(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount()));
                        HighestCompletedAgeGroup.sethighestCompleted(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
                        HighestCompletedAgeGroup.setValidation(1);
                        arrHighestCompletedAgeGroup.add(HighestCompletedAgeGroup);

                        total += Integer.parseInt(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).getCount());
                    }

//                    if (total != Integer.parseInt(ArrError.get(i).getTotal())) {
//                        HighestCompletedTemp = new HighestCompletedTemp();
//                        HighestCompletedTemp.setLocation(ArrError.get(i).getLocation());
//                        HighestCompletedTemp.setSex(ArrError.get(i).getSex());
//                        HighestCompletedTemp.setAgeGroup(ArrError.get(i).getAgeGroup());
//
//                        for (int y = 0; y < ArrError.get(i).getHighestCompletedAgeGroupTemp().size(); y++) {
//                            HighestCompletedAgeGroupTemp HighestCompletedAgeGroupTemp = new HighestCompletedAgeGroupTemp();
//                            HighestCompletedAgeGroupTemp.setCount("");
//                            HighestCompletedAgeGroupTemp.sethighestAttaintment(ArrError.get(i).getHighestCompletedAgeGroupTemp().get(y).gethighestAttaintment());
//                            arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroupTemp);
//                        }
//                        HighestCompletedTemp.setHighestCompletedAgeGroupTemp(arrHighestCompletedAgeGroupTemp);
//                        arrayError.add(HighestCompletedTemp);
//                    } else {
                    HighestCompleted = new HighestCompleted();
                    HighestCompleted.setFormID(ArrError.get(i).getFormID());
                    HighestCompleted.setYear(ArrError.get(i).getYear());
                    HighestCompleted.setLocation(ArrError.get(i).getLocation());
                    HighestCompleted.setSex(ArrError.get(i).getSex());
                    HighestCompleted.setAgeGroup(ArrError.get(i).getAgeGroup());
                    HighestCompleted.setTotal(Integer.parseInt(ArrError.get(i).getTotal()));
                    HighestCompleted.setHighestCompletedAgeGroup(arrHighestCompletedAgeGroup);
                    HighestCompleted.setValidation(1);
                    TransformData.add(HighestCompleted);
//                    }

                }

            }
        }
        return TransformData;
    }

    public static boolean isNumeric(String str) {
        return !StringUtils.isNumeric(str);
    }

    public HighestCompletedChecker() {
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
