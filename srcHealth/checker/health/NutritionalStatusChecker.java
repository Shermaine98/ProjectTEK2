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

        NutritionalStatusBMI NutritionalStatusBMI;

        for (int i = 0; i < ArrError.size(); i++) {
            if (ArrError.get(i).getPupilsWeighedFemale() == null
                    || ArrError.get(i).getPupilsWeighedMale() == null
                    || ArrError.get(i).getPupilsWeighedTotal() == null
                    || ArrError.get(i).getTotalCount() == null
                    || ArrError.get(i).getTotalFemale() == null
                    || ArrError.get(i).getTotalMale() == null
                    || ArrError.get(i).getPupilsWeighedFemale().equalsIgnoreCase("")
                    || ArrError.get(i).getPupilsWeighedMale().equalsIgnoreCase("")
                    || ArrError.get(i).getPupilsWeighedTotal().equalsIgnoreCase("")
                    || ArrError.get(i).getTotalCount().equalsIgnoreCase("")
                    || ArrError.get(i).getTotalFemale().equalsIgnoreCase("")
                    || ArrError.get(i).getTotalMale().equalsIgnoreCase("")) {

                NutritionalStatusTemp = new NutritionalStatusTemp();
                NutritionalStatusTemp.setDistrict(ArrError.get(i).getDistrict());
                NutritionalStatusTemp.setGradeLevel(ArrError.get(i).getGradeLevel());
                NutritionalStatusTemp.setDistrict(ArrError.get(i).getDistrict());
                NutritionalStatusTemp.setValidation(-1);
                NutritionalStatusTemp.setReason("Missing Fieald/s");

                if (ArrError.get(i).getPupilsWeighedFemale() == null || ArrError.get(i).getPupilsWeighedFemale().equalsIgnoreCase("")) {
                    NutritionalStatusTemp.setPupilsWeighedFemale("");

                } else {
                    NutritionalStatusTemp.setPupilsWeighedFemale(ArrError.get(i).getPupilsWeighedFemale());
                }

                if (ArrError.get(i).getPupilsWeighedMale() == null || ArrError.get(i).getPupilsWeighedMale().equalsIgnoreCase("")) {
                    NutritionalStatusTemp.setPupilsWeighedMale("");
                } else {

                    NutritionalStatusTemp.setPupilsWeighedMale(ArrError.get(i).getPupilsWeighedMale());

                }

                if (ArrError.get(i).getPupilsWeighedTotal() == null || ArrError.get(i).getPupilsWeighedTotal().equalsIgnoreCase("")) {
                    NutritionalStatusTemp.setPupilsWeighedTotal("");
                } else {
                    NutritionalStatusTemp.setPupilsWeighedTotal(ArrError.get(i).getPupilsWeighedTotal());
                }

                if (ArrError.get(i).getTotalCount() == null || ArrError.get(i).getTotalCount().equalsIgnoreCase("")) {
                    NutritionalStatusTemp.setTotalCount("");
                } else {
                    NutritionalStatusTemp.setTotalCount(ArrError.get(i).getTotalCount());
                }

                if (ArrError.get(i).getTotalFemale() == null || ArrError.get(i).getTotalFemale().equalsIgnoreCase("")) {
                    NutritionalStatusTemp.setTotalFemale("");
                } else {
                    NutritionalStatusTemp.setTotalFemale(ArrError.get(i).getTotalFemale());
                }

                if (ArrError.get(i).getTotalMale() == null || ArrError.get(i).getTotalMale().equalsIgnoreCase("")) {
                    NutritionalStatusTemp.setTotalMale("");
                } else {
                    NutritionalStatusTemp.setTotalMale(ArrError.get(i).getTotalMale());
                }

                arrNutritionalStatusBMITemp = new ArrayList<NutritionalStatusBMITemp>();
                for (int y = 0; y < ArrError.get(i).getNutritionalStatusBMITemp().size(); y++) {
                    NutritionalStatusBMITemp NutritionalStatusBMITemp = new NutritionalStatusBMITemp();
                    if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount() == null
                            || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount() == null
                            || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount().equalsIgnoreCase("")) {
                        if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount().equalsIgnoreCase("")) {
                            NutritionalStatusBMITemp.setFemaleCount("");
                        } else {
                            NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                        }

                        if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount().equalsIgnoreCase("")) {
                            NutritionalStatusBMITemp.setTotalCount("");
                        } else {
                            NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                        }

                        if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount().equalsIgnoreCase("")) {
                            NutritionalStatusBMITemp.setMaleCount("");
                        } else {
                            NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                        }
                        NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());

                        NutritionalStatusBMITemp.setValidation(-1);
                        NutritionalStatusBMITemp.setReason("Missing Field/s");
                        arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    } else if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                            || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())
                            || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())) {

                        if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {
                            NutritionalStatusBMITemp.setFemaleCount("");
                        } else {
                            NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                        }

                        if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())) {
                            NutritionalStatusBMITemp.setTotalCount("");
                        } else {
                            NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                        }

                        if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())) {
                            NutritionalStatusBMITemp.setMaleCount("");
                        } else {
                            NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                        }
                        NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMITemp.setValidation(-2);
                        NutritionalStatusBMITemp.setReason("Format Error");
                        arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    } else if (Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                            != Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())
                            + Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {

                        NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                        NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                        NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                        NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMITemp.setValidation(-3);
                        NutritionalStatusBMITemp.setReason("Summation Error");
                        arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    } else {
                        NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                        NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                        NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                        NutritionalStatusBMITemp.setValidation(1);
                        NutritionalStatusBMITemp.setReason("");
                        arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    }
                }

                NutritionalStatusTemp.setNutritionalStatusTemp(arrNutritionalStatusBMITemp);
                arrayError.add(NutritionalStatusTemp);
            } else if (isNumeric(ArrError.get(i).getPupilsWeighedFemale())
                    || isNumeric(ArrError.get(i).getPupilsWeighedMale())
                    || isNumeric(ArrError.get(i).getPupilsWeighedTotal())
                    || isNumeric(ArrError.get(i).getTotalCount())
                    || isNumeric(ArrError.get(i).getTotalFemale())
                    || isNumeric(ArrError.get(i).getTotalMale())) {

                NutritionalStatusTemp = new NutritionalStatusTemp();
                NutritionalStatusTemp.setDistrict(ArrError.get(i).getDistrict());
                NutritionalStatusTemp.setGradeLevel(ArrError.get(i).getGradeLevel());
                NutritionalStatusTemp.setDistrict(ArrError.get(i).getDistrict());
                NutritionalStatusTemp.setValidation(-2);
                NutritionalStatusTemp.setReason("Format Error");

                if (isNumeric(ArrError.get(i).getPupilsWeighedFemale())) {
                    NutritionalStatusTemp.setPupilsWeighedFemale("");

                } else {
                    NutritionalStatusTemp.setPupilsWeighedFemale(ArrError.get(i).getPupilsWeighedFemale());
                }

                if (isNumeric(ArrError.get(i).getPupilsWeighedMale())) {
                    NutritionalStatusTemp.setPupilsWeighedMale("");
                } else {

                    NutritionalStatusTemp.setPupilsWeighedMale(ArrError.get(i).getPupilsWeighedMale());

                }

                if (isNumeric(ArrError.get(i).getPupilsWeighedTotal())) {
                    NutritionalStatusTemp.setPupilsWeighedTotal("");
                } else {
                    NutritionalStatusTemp.setPupilsWeighedTotal(ArrError.get(i).getPupilsWeighedTotal());
                }

                if (isNumeric(ArrError.get(i).getTotalCount())) {
                    NutritionalStatusTemp.setTotalCount("");
                } else {
                    NutritionalStatusTemp.setTotalCount(ArrError.get(i).getTotalCount());
                }

                if (isNumeric(ArrError.get(i).getTotalFemale())) {
                    NutritionalStatusTemp.setTotalFemale("");
                } else {
                    NutritionalStatusTemp.setTotalFemale(ArrError.get(i).getTotalFemale());
                }

                if (isNumeric(ArrError.get(i).getTotalMale())) {
                    NutritionalStatusTemp.setTotalMale("");
                } else {
                    NutritionalStatusTemp.setTotalMale(ArrError.get(i).getTotalMale());
                }

                arrNutritionalStatusBMITemp = new ArrayList<NutritionalStatusBMITemp>();
                for (int y = 0; y < ArrError.get(i).getNutritionalStatusBMITemp().size(); y++) {
                    NutritionalStatusBMITemp NutritionalStatusBMITemp = new NutritionalStatusBMITemp();
                    if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount() == null
                            || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount() == null
                            || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount().equalsIgnoreCase("")) {

                        if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount().equalsIgnoreCase("")) {
                            NutritionalStatusBMITemp.setFemaleCount("");
                        } else {
                            NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                        }

                        if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount().equalsIgnoreCase("")) {
                            NutritionalStatusBMITemp.setTotalCount("");
                        } else {
                            NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                        }

                        if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount().equalsIgnoreCase("")) {
                            NutritionalStatusBMITemp.setMaleCount("");
                        } else {
                            NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                        }
                        NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());

                        NutritionalStatusBMITemp.setValidation(-1);
                        NutritionalStatusBMITemp.setReason("Missing Field/s");
                        arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    } else if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                            || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())
                            || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())) {

                        if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {
                            NutritionalStatusBMITemp.setFemaleCount("");
                        } else {
                            NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                        }

                        if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())) {
                            NutritionalStatusBMITemp.setTotalCount("");
                        } else {
                            NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                        }

                        if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())) {
                            NutritionalStatusBMITemp.setMaleCount("");
                        } else {
                            NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                        }
                        NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMITemp.setValidation(-2);
                        NutritionalStatusBMITemp.setReason("Format Error");
                        arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    } else if (Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                            != Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())
                            + Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {

                        NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                        NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                        NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                        NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMITemp.setValidation(-3);
                        NutritionalStatusBMITemp.setReason("Summation Error");
                        arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    } else {
                        NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                        NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                        NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                        NutritionalStatusBMITemp.setValidation(1);
                            NutritionalStatusBMITemp.setReason("");
                        arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    }
                }

                NutritionalStatusTemp.setNutritionalStatusTemp(arrNutritionalStatusBMITemp);
                arrayError.add(NutritionalStatusTemp);
            } else if (Integer.parseInt(ArrError.get(i).getPupilsWeighedTotal())
                    != Integer.parseInt(ArrError.get(i).getPupilsWeighedFemale()) + Integer.parseInt(ArrError.get(i).getPupilsWeighedMale())
                    || Integer.parseInt(ArrError.get(i).getTotalCount())
                    != Integer.parseInt(ArrError.get(i).getTotalFemale()) + Integer.parseInt(ArrError.get(i).getTotalMale())) {

                NutritionalStatusTemp = new NutritionalStatusTemp();
                NutritionalStatusTemp.setDistrict(ArrError.get(i).getDistrict());
                NutritionalStatusTemp.setGradeLevel(ArrError.get(i).getGradeLevel());
                NutritionalStatusTemp.setDistrict(ArrError.get(i).getDistrict());
                NutritionalStatusTemp.setValidation(-3);
                NutritionalStatusTemp.setReason("Summation Error");

                NutritionalStatusTemp.setPupilsWeighedFemale(ArrError.get(i).getPupilsWeighedFemale());
                NutritionalStatusTemp.setPupilsWeighedMale(ArrError.get(i).getPupilsWeighedMale());
                NutritionalStatusTemp.setPupilsWeighedTotal(ArrError.get(i).getPupilsWeighedTotal());
                NutritionalStatusTemp.setTotalCount(ArrError.get(i).getTotalCount());
                NutritionalStatusTemp.setTotalFemale(ArrError.get(i).getTotalFemale());
                NutritionalStatusTemp.setTotalMale(ArrError.get(i).getTotalMale());

                arrNutritionalStatusBMITemp = new ArrayList<NutritionalStatusBMITemp>();
                for (int y = 0; y < ArrError.get(i).getNutritionalStatusBMITemp().size(); y++) {
                    NutritionalStatusBMITemp NutritionalStatusBMITemp = new NutritionalStatusBMITemp();
                    if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount() == null
                            || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount() == null
                            || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount().equalsIgnoreCase("")) {

                        if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount().equalsIgnoreCase("")) {
                            NutritionalStatusBMITemp.setFemaleCount("");
                        } else {
                            NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                        }

                        if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount().equalsIgnoreCase("")) {
                            NutritionalStatusBMITemp.setTotalCount("");
                        } else {
                            NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                        }

                        if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount().equalsIgnoreCase("")) {
                            NutritionalStatusBMITemp.setMaleCount("");
                        } else {
                            NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                        }
                        NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());

                        NutritionalStatusBMITemp.setValidation(-1);
                        NutritionalStatusBMITemp.setReason("Missing Field/s");
                        arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    } else if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                            || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())
                            || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())) {

                        if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {
                            NutritionalStatusBMITemp.setFemaleCount("");
                        } else {
                            NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                        }

                        if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())) {
                            NutritionalStatusBMITemp.setTotalCount("");
                        } else {
                            NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                        }

                        if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())) {
                            NutritionalStatusBMITemp.setMaleCount("");
                        } else {
                            NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                        }
                        NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMITemp.setValidation(-2);
                        NutritionalStatusBMITemp.setReason("Format Error");
                        arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    } else if (Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                            != Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())
                            + Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {

                        NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                        NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                        NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                        NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMITemp.setValidation(-3);
                        NutritionalStatusBMITemp.setReason("Summation Error");
                        arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    } else {
                        NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                        NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                        NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                        NutritionalStatusBMITemp.setValidation(1);
                        NutritionalStatusBMITemp.setReason("");
                        arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    }
                }

                NutritionalStatusTemp.setNutritionalStatusTemp(arrNutritionalStatusBMITemp);
                arrayError.add(NutritionalStatusTemp);

            } else {

                boolean x = false;

                for (int y = 0; y < ArrError.get(i).getNutritionalStatusBMITemp().size(); y++) {
                    NutritionalStatusBMITemp NutritionalStatusBMITemp = new NutritionalStatusBMITemp();
                    if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount() == null
                            || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount() == null
                            || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount().equalsIgnoreCase("")) {

                        x = true;
                    } else if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                            || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())
                            || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())) {
                        x = true;
                    } else if (Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                            != Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())
                            + Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {
                        x = true;
                    }
                }

                if (x) {
                    NutritionalStatusTemp = new NutritionalStatusTemp();
                    NutritionalStatusTemp.setDistrict(ArrError.get(i).getDistrict());
                    NutritionalStatusTemp.setGradeLevel(ArrError.get(i).getGradeLevel());
                    NutritionalStatusTemp.setDistrict(ArrError.get(i).getDistrict());
                    NutritionalStatusTemp.setValidation(1);
                     NutritionalStatusTemp.setReason("");

                    NutritionalStatusTemp.setPupilsWeighedFemale(ArrError.get(i).getPupilsWeighedFemale());
                    NutritionalStatusTemp.setPupilsWeighedMale(ArrError.get(i).getPupilsWeighedMale());
                    NutritionalStatusTemp.setPupilsWeighedTotal(ArrError.get(i).getPupilsWeighedTotal());
                    NutritionalStatusTemp.setTotalCount(ArrError.get(i).getTotalCount());
                    NutritionalStatusTemp.setTotalFemale(ArrError.get(i).getTotalFemale());
                    NutritionalStatusTemp.setTotalMale(ArrError.get(i).getTotalMale());

                    arrNutritionalStatusBMITemp = new ArrayList<NutritionalStatusBMITemp>();
                    for (int y = 0; y < ArrError.get(i).getNutritionalStatusBMITemp().size(); y++) {
                        NutritionalStatusBMITemp NutritionalStatusBMITemp = new NutritionalStatusBMITemp();
                        if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount() == null
                                || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount() == null
                                || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount().equalsIgnoreCase("")) {

                            if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount().equalsIgnoreCase("")) {
                                NutritionalStatusBMITemp.setFemaleCount("");
                            } else {
                                NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                            }

                            if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount().equalsIgnoreCase("")) {
                                NutritionalStatusBMITemp.setTotalCount("");
                            } else {
                                NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                            }

                            if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount().equalsIgnoreCase("")) {
                                NutritionalStatusBMITemp.setMaleCount("");
                            } else {
                                NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                            }
                            NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());

                            NutritionalStatusBMITemp.setValidation(-1);
                            NutritionalStatusBMITemp.setReason("Missing Field/s");
                            arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                        } else if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                                || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())
                                || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())) {

                            if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {
                                NutritionalStatusBMITemp.setFemaleCount("");
                            } else {
                                NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                            }

                            if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())) {
                                NutritionalStatusBMITemp.setTotalCount("");
                            } else {
                                NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                            }

                            if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())) {
                                NutritionalStatusBMITemp.setMaleCount("");
                            } else {
                                NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                            }
                            NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                            NutritionalStatusBMITemp.setValidation(-2);
                            NutritionalStatusBMITemp.setReason("Format Error");
                            arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                        } else if (Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                                != Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())
                                + Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {

                            NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                            NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                            NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                            NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                            NutritionalStatusBMITemp.setValidation(-3);
                            NutritionalStatusBMITemp.setReason("Summation Error");
                            arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                        } else {
                            NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                            NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                            NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                            NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                            NutritionalStatusBMITemp.setValidation(1);
                            NutritionalStatusBMITemp.setReason("");
                            arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                        }
                    }

                    NutritionalStatusTemp.setNutritionalStatusTemp(arrNutritionalStatusBMITemp);
                    arrayError.add(NutritionalStatusTemp);

                } else {
                    NutritionalStatus = new NutritionalStatus();
                    NutritionalStatus.setDistrict(ArrError.get(i).getDistrict());
                    NutritionalStatus.setGradeLevel(ArrError.get(i).getGradeLevel());
                    NutritionalStatus.setTotalMale(Integer.parseInt(ArrError.get(i).getTotalMale()));
                    NutritionalStatus.setTotalFemale(Integer.parseInt(ArrError.get(i).getTotalFemale()));
                    NutritionalStatus.setTotalCount(Integer.parseInt(ArrError.get(i).getTotalCount()));
                    NutritionalStatus.setValidation(1);
                    NutritionalStatus.setReason("");
                    NutritionalStatus.setPupilsWeighedFemale(Integer.parseInt(ArrError.get(i).getPupilsWeighedFemale()));
                    NutritionalStatus.setPupilsWeighedMale(Integer.parseInt(ArrError.get(i).getPupilsWeighedMale()));
                    NutritionalStatus.setPupilsWeighedTotal(Integer.parseInt(ArrError.get(i).getPupilsWeighedTotal()));

                    arrNutritionalStatusBMI = new ArrayList<>();
                    for (int y = 0; y < ArrError.get(i).getNutritionalStatusBMITemp().size(); y++) {
                        NutritionalStatusBMI = new NutritionalStatusBMI();
                        NutritionalStatusBMI.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMI.setMaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount()));
                        NutritionalStatusBMI.setFemaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount()));
                        NutritionalStatusBMI.setTotalCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount()));
                        NutritionalStatusBMI.setValidation(1);
                         NutritionalStatusBMI.setReason("");
                        arrNutritionalStatusBMI.add(NutritionalStatusBMI);

                    }
                    NutritionalStatus.setNutritionalStatusBMI(arrNutritionalStatusBMI);
                    arrayNoError.add(NutritionalStatus);
                }

            }

        }
    }

    public NutritionalStatusChecker(ArrayList<NutritionalStatusTemp> ArrError, int year, int formID) {

        NutritionalStatus NutritionalStatus;
        NutritionalStatusTemp NutritionalStatusTemp;
        arrayNoError = new ArrayList<>();
        arrayError = new ArrayList<>();

        ArrayList<NutritionalStatusBMI> arrNutritionalStatusBMI;
        ArrayList<NutritionalStatusBMITemp> arrNutritionalStatusBMITemp;

        NutritionalStatusBMI NutritionalStatusBMI;

        for (int i = 0; i < ArrError.size(); i++) {
            if (ArrError.get(i).getPupilsWeighedFemale() == null
                    || ArrError.get(i).getPupilsWeighedMale() == null
                    || ArrError.get(i).getPupilsWeighedTotal() == null
                    || ArrError.get(i).getTotalCount() == null
                    || ArrError.get(i).getTotalFemale() == null
                    || ArrError.get(i).getTotalMale() == null
                    || ArrError.get(i).getPupilsWeighedFemale().equalsIgnoreCase("")
                    || ArrError.get(i).getPupilsWeighedMale().equalsIgnoreCase("")
                    || ArrError.get(i).getPupilsWeighedTotal().equalsIgnoreCase("")
                    || ArrError.get(i).getTotalCount().equalsIgnoreCase("")
                    || ArrError.get(i).getTotalFemale().equalsIgnoreCase("")
                    || ArrError.get(i).getTotalMale().equalsIgnoreCase("")) {

                NutritionalStatusTemp = new NutritionalStatusTemp();
                NutritionalStatusTemp.setFormID(formID);
                NutritionalStatusTemp.setCensusYear(year);
                NutritionalStatusTemp.setDistrict(ArrError.get(i).getDistrict());
                NutritionalStatusTemp.setGradeLevel(ArrError.get(i).getGradeLevel());
                NutritionalStatusTemp.setDistrict(ArrError.get(i).getDistrict());
                NutritionalStatusTemp.setValidation(-1);
                NutritionalStatusTemp.setReason("Missing Fieald/s");

                if (ArrError.get(i).getPupilsWeighedFemale() == null || ArrError.get(i).getPupilsWeighedFemale().equalsIgnoreCase("")) {
                    NutritionalStatusTemp.setPupilsWeighedFemale("");

                } else {
                    NutritionalStatusTemp.setPupilsWeighedFemale(ArrError.get(i).getPupilsWeighedFemale());
                }

                if (ArrError.get(i).getPupilsWeighedMale() == null || ArrError.get(i).getPupilsWeighedMale().equalsIgnoreCase("")) {
                    NutritionalStatusTemp.setPupilsWeighedMale("");
                } else {

                    NutritionalStatusTemp.setPupilsWeighedMale(ArrError.get(i).getPupilsWeighedMale());

                }

                if (ArrError.get(i).getPupilsWeighedTotal() == null || ArrError.get(i).getPupilsWeighedTotal().equalsIgnoreCase("")) {
                    NutritionalStatusTemp.setPupilsWeighedTotal("");
                } else {
                    NutritionalStatusTemp.setPupilsWeighedTotal(ArrError.get(i).getPupilsWeighedTotal());
                }

                if (ArrError.get(i).getTotalCount() == null || ArrError.get(i).getTotalCount().equalsIgnoreCase("")) {
                    NutritionalStatusTemp.setTotalCount("");
                } else {
                    NutritionalStatusTemp.setTotalCount(ArrError.get(i).getTotalCount());
                }

                if (ArrError.get(i).getTotalFemale() == null || ArrError.get(i).getTotalFemale().equalsIgnoreCase("")) {
                    NutritionalStatusTemp.setTotalFemale("");
                } else {
                    NutritionalStatusTemp.setTotalFemale(ArrError.get(i).getTotalFemale());
                }

                if (ArrError.get(i).getTotalMale() == null || ArrError.get(i).getTotalMale().equalsIgnoreCase("")) {
                    NutritionalStatusTemp.setTotalMale("");
                } else {
                    NutritionalStatusTemp.setTotalMale(ArrError.get(i).getTotalMale());
                }

                arrNutritionalStatusBMITemp = new ArrayList<NutritionalStatusBMITemp>();
                for (int y = 0; y < ArrError.get(i).getNutritionalStatusBMITemp().size(); y++) {
                    NutritionalStatusBMITemp NutritionalStatusBMITemp = new NutritionalStatusBMITemp();
                    if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount() == null
                            || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount() == null
                            || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount().equalsIgnoreCase("")) {
                        if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount().equalsIgnoreCase("")) {
                            NutritionalStatusBMITemp.setFemaleCount("");
                        } else {
                            NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                        }

                        if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount().equalsIgnoreCase("")) {
                            NutritionalStatusBMITemp.setTotalCount("");
                        } else {
                            NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                        }

                        if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount().equalsIgnoreCase("")) {
                            NutritionalStatusBMITemp.setMaleCount("");
                        } else {
                            NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                        }
                        NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());

                        NutritionalStatusBMITemp.setValidation(-1);
                        NutritionalStatusBMITemp.setReason("Missing Field/s");
                        arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    } else if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                            || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())
                            || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())) {

                        if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {
                            NutritionalStatusBMITemp.setFemaleCount("");
                        } else {
                            NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                        }

                        if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())) {
                            NutritionalStatusBMITemp.setTotalCount("");
                        } else {
                            NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                        }

                        if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())) {
                            NutritionalStatusBMITemp.setMaleCount("");
                        } else {
                            NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                        }
                        NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMITemp.setValidation(-2);
                        NutritionalStatusBMITemp.setReason("Format Error");
                        arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    } else if (Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                            != Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())
                            + Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {

                        NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                        NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                        NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                        NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMITemp.setValidation(-3);
                        NutritionalStatusBMITemp.setReason("Summation Error");
                        arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    } else {
                        NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                        NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                        NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                        NutritionalStatusBMITemp.setValidation(1);
                         NutritionalStatusBMITemp.setReason("");
                        arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    }
                }

                NutritionalStatusTemp.setNutritionalStatusTemp(arrNutritionalStatusBMITemp);
                arrayError.add(NutritionalStatusTemp);
            } else if (isNumeric(ArrError.get(i).getPupilsWeighedFemale())
                    || isNumeric(ArrError.get(i).getPupilsWeighedMale())
                    || isNumeric(ArrError.get(i).getPupilsWeighedTotal())
                    || isNumeric(ArrError.get(i).getTotalCount())
                    || isNumeric(ArrError.get(i).getTotalFemale())
                    || isNumeric(ArrError.get(i).getTotalMale())) {

                NutritionalStatusTemp = new NutritionalStatusTemp();
                NutritionalStatusTemp.setFormID(formID);
                NutritionalStatusTemp.setCensusYear(year);

                NutritionalStatusTemp.setDistrict(ArrError.get(i).getDistrict());
                NutritionalStatusTemp.setGradeLevel(ArrError.get(i).getGradeLevel());
                NutritionalStatusTemp.setDistrict(ArrError.get(i).getDistrict());
                NutritionalStatusTemp.setValidation(-2);
                NutritionalStatusTemp.setReason("Format Error");

                if (isNumeric(ArrError.get(i).getPupilsWeighedFemale())) {
                    NutritionalStatusTemp.setPupilsWeighedFemale("");

                } else {
                    NutritionalStatusTemp.setPupilsWeighedFemale(ArrError.get(i).getPupilsWeighedFemale());
                }

                if (isNumeric(ArrError.get(i).getPupilsWeighedMale())) {
                    NutritionalStatusTemp.setPupilsWeighedMale("");
                } else {

                    NutritionalStatusTemp.setPupilsWeighedMale(ArrError.get(i).getPupilsWeighedMale());

                }

                if (isNumeric(ArrError.get(i).getPupilsWeighedTotal())) {
                    NutritionalStatusTemp.setPupilsWeighedTotal("");
                } else {
                    NutritionalStatusTemp.setPupilsWeighedTotal(ArrError.get(i).getPupilsWeighedTotal());
                }

                if (isNumeric(ArrError.get(i).getTotalCount())) {
                    NutritionalStatusTemp.setTotalCount("");
                } else {
                    NutritionalStatusTemp.setTotalCount(ArrError.get(i).getTotalCount());
                }

                if (isNumeric(ArrError.get(i).getTotalFemale())) {
                    NutritionalStatusTemp.setTotalFemale("");
                } else {
                    NutritionalStatusTemp.setTotalFemale(ArrError.get(i).getTotalFemale());
                }

                if (isNumeric(ArrError.get(i).getTotalMale())) {
                    NutritionalStatusTemp.setTotalMale("");
                } else {
                    NutritionalStatusTemp.setTotalMale(ArrError.get(i).getTotalMale());
                }

                arrNutritionalStatusBMITemp = new ArrayList<NutritionalStatusBMITemp>();
                for (int y = 0; y < ArrError.get(i).getNutritionalStatusBMITemp().size(); y++) {
                    NutritionalStatusBMITemp NutritionalStatusBMITemp = new NutritionalStatusBMITemp();
                    if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount() == null
                            || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount() == null
                            || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount().equalsIgnoreCase("")) {

                        if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount().equalsIgnoreCase("")) {
                            NutritionalStatusBMITemp.setFemaleCount("");
                        } else {
                            NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                        }

                        if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount().equalsIgnoreCase("")) {
                            NutritionalStatusBMITemp.setTotalCount("");
                        } else {
                            NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                        }

                        if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount().equalsIgnoreCase("")) {
                            NutritionalStatusBMITemp.setMaleCount("");
                        } else {
                            NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                        }
                        NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());

                        NutritionalStatusBMITemp.setValidation(-1);
                        NutritionalStatusBMITemp.setReason("Missing Field/s");
                        arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    } else if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                            || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())
                            || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())) {

                        if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {
                            NutritionalStatusBMITemp.setFemaleCount("");
                        } else {
                            NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                        }

                        if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())) {
                            NutritionalStatusBMITemp.setTotalCount("");
                        } else {
                            NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                        }

                        if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())) {
                            NutritionalStatusBMITemp.setMaleCount("");
                        } else {
                            NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                        }
                        NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMITemp.setValidation(-2);
                        NutritionalStatusBMITemp.setReason("Format Error");
                        arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    } else if (Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                            != Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())
                            + Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {

                        NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                        NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                        NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                        NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMITemp.setValidation(-3);
                        NutritionalStatusBMITemp.setReason("Summation Error");
                        arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    } else {
                        NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                        NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                        NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                        NutritionalStatusBMITemp.setValidation(1);
                         NutritionalStatusBMITemp.setReason("");
                        arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    }
                }

                NutritionalStatusTemp.setNutritionalStatusTemp(arrNutritionalStatusBMITemp);
                arrayError.add(NutritionalStatusTemp);
            } else if (Integer.parseInt(ArrError.get(i).getPupilsWeighedTotal())
                    != Integer.parseInt(ArrError.get(i).getPupilsWeighedFemale()) + Integer.parseInt(ArrError.get(i).getPupilsWeighedMale())
                    || Integer.parseInt(ArrError.get(i).getTotalCount())
                    != Integer.parseInt(ArrError.get(i).getTotalFemale()) + Integer.parseInt(ArrError.get(i).getTotalMale())) {

                NutritionalStatusTemp = new NutritionalStatusTemp();
                NutritionalStatusTemp.setFormID(formID);
                NutritionalStatusTemp.setCensusYear(year);
                NutritionalStatusTemp.setDistrict(ArrError.get(i).getDistrict());
                NutritionalStatusTemp.setGradeLevel(ArrError.get(i).getGradeLevel());
                NutritionalStatusTemp.setDistrict(ArrError.get(i).getDistrict());
                NutritionalStatusTemp.setValidation(-3);
                NutritionalStatusTemp.setReason("Summation Error");

                NutritionalStatusTemp.setPupilsWeighedFemale(ArrError.get(i).getPupilsWeighedFemale());
                NutritionalStatusTemp.setPupilsWeighedMale(ArrError.get(i).getPupilsWeighedMale());
                NutritionalStatusTemp.setPupilsWeighedTotal(ArrError.get(i).getPupilsWeighedTotal());
                NutritionalStatusTemp.setTotalCount(ArrError.get(i).getTotalCount());
                NutritionalStatusTemp.setTotalFemale(ArrError.get(i).getTotalFemale());
                NutritionalStatusTemp.setTotalMale(ArrError.get(i).getTotalMale());

                arrNutritionalStatusBMITemp = new ArrayList<NutritionalStatusBMITemp>();
                for (int y = 0; y < ArrError.get(i).getNutritionalStatusBMITemp().size(); y++) {
                    NutritionalStatusBMITemp NutritionalStatusBMITemp = new NutritionalStatusBMITemp();
                    if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount() == null
                            || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount() == null
                            || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount().equalsIgnoreCase("")) {

                        if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount().equalsIgnoreCase("")) {
                            NutritionalStatusBMITemp.setFemaleCount("");
                        } else {
                            NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                        }

                        if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount().equalsIgnoreCase("")) {
                            NutritionalStatusBMITemp.setTotalCount("");
                        } else {
                            NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                        }

                        if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount().equalsIgnoreCase("")) {
                            NutritionalStatusBMITemp.setMaleCount("");
                        } else {
                            NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                        }
                        NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());

                        NutritionalStatusBMITemp.setValidation(-1);
                        NutritionalStatusBMITemp.setReason("Missing Field/s");
                        arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    } else if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                            || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())
                            || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())) {

                        if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {
                            NutritionalStatusBMITemp.setFemaleCount("");
                        } else {
                            NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                        }

                        if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())) {
                            NutritionalStatusBMITemp.setTotalCount("");
                        } else {
                            NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                        }

                        if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())) {
                            NutritionalStatusBMITemp.setMaleCount("");
                        } else {
                            NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                        }
                        NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMITemp.setValidation(-2);
                        NutritionalStatusBMITemp.setReason("Format Error");
                        arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    } else if (Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                            != Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())
                            + Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {

                        NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                        NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                        NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                        NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMITemp.setValidation(-3);
                        NutritionalStatusBMITemp.setReason("Summation Error");
                        arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    } else {
                        NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                        NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                        NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                        NutritionalStatusBMITemp.setValidation(1);
                        
                         NutritionalStatusBMITemp.setReason("");
                        arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                    }
                }

                NutritionalStatusTemp.setNutritionalStatusTemp(arrNutritionalStatusBMITemp);
                arrayError.add(NutritionalStatusTemp);

            } else {

                boolean x = false;

                for (int y = 0; y < ArrError.get(i).getNutritionalStatusBMITemp().size(); y++) {
                    NutritionalStatusBMITemp NutritionalStatusBMITemp = new NutritionalStatusBMITemp();
                    if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount() == null
                            || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount() == null
                            || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount().equalsIgnoreCase("")) {

                        x = true;
                    } else if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                            || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())
                            || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())) {
                        x = true;
                    } else if (Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                            != Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())
                            + Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {
                        x = true;
                    }
                }

                if (x) {
                    NutritionalStatusTemp = new NutritionalStatusTemp();

                    NutritionalStatusTemp.setFormID(formID);
                    NutritionalStatusTemp.setCensusYear(year);
                    NutritionalStatusTemp.setDistrict(ArrError.get(i).getDistrict());
                    NutritionalStatusTemp.setGradeLevel(ArrError.get(i).getGradeLevel());
                    NutritionalStatusTemp.setDistrict(ArrError.get(i).getDistrict());
                    NutritionalStatusTemp.setValidation(1);

                         NutritionalStatusTemp.setReason("");
                    NutritionalStatusTemp.setPupilsWeighedFemale(ArrError.get(i).getPupilsWeighedFemale());
                    NutritionalStatusTemp.setPupilsWeighedMale(ArrError.get(i).getPupilsWeighedMale());
                    NutritionalStatusTemp.setPupilsWeighedTotal(ArrError.get(i).getPupilsWeighedTotal());
                    NutritionalStatusTemp.setTotalCount(ArrError.get(i).getTotalCount());
                    NutritionalStatusTemp.setTotalFemale(ArrError.get(i).getTotalFemale());
                    NutritionalStatusTemp.setTotalMale(ArrError.get(i).getTotalMale());

                    arrNutritionalStatusBMITemp = new ArrayList<NutritionalStatusBMITemp>();
                    for (int y = 0; y < ArrError.get(i).getNutritionalStatusBMITemp().size(); y++) {
                        NutritionalStatusBMITemp NutritionalStatusBMITemp = new NutritionalStatusBMITemp();
                        if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount() == null
                                || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount() == null
                                || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount().equalsIgnoreCase("")) {

                            if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount().equalsIgnoreCase("")) {
                                NutritionalStatusBMITemp.setFemaleCount("");
                            } else {
                                NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                            }

                            if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount().equalsIgnoreCase("")) {
                                NutritionalStatusBMITemp.setTotalCount("");
                            } else {
                                NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                            }

                            if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount() == null || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount().equalsIgnoreCase("")) {
                                NutritionalStatusBMITemp.setMaleCount("");
                            } else {
                                NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                            }
                            NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());

                            NutritionalStatusBMITemp.setValidation(-1);
                            NutritionalStatusBMITemp.setReason("Missing Field/s");
                            arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                        } else if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                                || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())
                                || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())) {

                            if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {
                                NutritionalStatusBMITemp.setFemaleCount("");
                            } else {
                                NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                            }

                            if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())) {
                                NutritionalStatusBMITemp.setTotalCount("");
                            } else {
                                NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                            }

                            if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())) {
                                NutritionalStatusBMITemp.setMaleCount("");
                            } else {
                                NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                            }
                            NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                            NutritionalStatusBMITemp.setValidation(-2);
                            NutritionalStatusBMITemp.setReason("Format Error");
                            arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                        } else if (Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                                != Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())
                                + Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {

                            NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                            NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                            NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                            NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                            NutritionalStatusBMITemp.setValidation(-3);
                            NutritionalStatusBMITemp.setReason("Summation Error");
                            arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                        } else {
                            NutritionalStatusBMITemp.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                            NutritionalStatusBMITemp.setMaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount());
                            NutritionalStatusBMITemp.setFemaleCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount());
                            NutritionalStatusBMITemp.setTotalCount(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount());
                            NutritionalStatusBMITemp.setValidation(1);
                            NutritionalStatusBMITemp.setReason("");
                            arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
                        }
                    }

                    NutritionalStatusTemp.setNutritionalStatusTemp(arrNutritionalStatusBMITemp);
                    arrayError.add(NutritionalStatusTemp);

                } else {
                    NutritionalStatus = new NutritionalStatus();
                    NutritionalStatus.setFormID(formID);
                    NutritionalStatus.setCensusYear(year);
                    NutritionalStatus.setDistrict(ArrError.get(i).getDistrict());
                    NutritionalStatus.setGradeLevel(ArrError.get(i).getGradeLevel());
                    NutritionalStatus.setTotalMale(Integer.parseInt(ArrError.get(i).getTotalMale()));
                    NutritionalStatus.setTotalFemale(Integer.parseInt(ArrError.get(i).getTotalFemale()));
                    NutritionalStatus.setTotalCount(Integer.parseInt(ArrError.get(i).getTotalCount()));
                    NutritionalStatus.setValidation(1);
                     NutritionalStatus.setReason("");
                    NutritionalStatus.setPupilsWeighedFemale(Integer.parseInt(ArrError.get(i).getPupilsWeighedFemale()));
                    NutritionalStatus.setPupilsWeighedMale(Integer.parseInt(ArrError.get(i).getPupilsWeighedMale()));
                    NutritionalStatus.setPupilsWeighedTotal(Integer.parseInt(ArrError.get(i).getPupilsWeighedTotal()));

                    arrNutritionalStatusBMI = new ArrayList<>();
                    for (int y = 0; y < ArrError.get(i).getNutritionalStatusBMITemp().size(); y++) {
                        NutritionalStatusBMI = new NutritionalStatusBMI();
                        NutritionalStatusBMI.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMI.setMaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount()));
                        NutritionalStatusBMI.setFemaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount()));
                        NutritionalStatusBMI.setTotalCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount()));
                        NutritionalStatusBMI.setValidation(1);
                        NutritionalStatusBMI.setReason("");
                        arrNutritionalStatusBMI.add(NutritionalStatusBMI);

                    }
                    NutritionalStatus.setNutritionalStatusBMI(arrNutritionalStatusBMI);
                    arrayNoError.add(NutritionalStatus);
                }

            }

        }
    }

    public NutritionalStatusChecker() {

    }

    public ArrayList<NutritionalStatus> TransformData(ArrayList<NutritionalStatusTemp> ArrError) {

        ArrayList<NutritionalStatusBMI> arrNutritionalStatusBMI;
        NutritionalStatus NutritionalStatus;
        ArrayList<NutritionalStatus> TransformData = new ArrayList<>();
      
        for (int i = 0; i < ArrError.size(); i++) {
            if (ArrError.get(i).getPupilsWeighedFemale() == null
                    || ArrError.get(i).getPupilsWeighedMale() == null
                    || ArrError.get(i).getPupilsWeighedTotal() == null
                    || ArrError.get(i).getTotalCount() == null
                    || ArrError.get(i).getTotalFemale() == null
                    || ArrError.get(i).getTotalMale() == null
                    || ArrError.get(i).getPupilsWeighedFemale().equalsIgnoreCase("")
                    || ArrError.get(i).getPupilsWeighedMale().equalsIgnoreCase("")
                    || ArrError.get(i).getPupilsWeighedTotal().equalsIgnoreCase("")
                    || ArrError.get(i).getTotalCount().equalsIgnoreCase("")
                    || ArrError.get(i).getTotalFemale().equalsIgnoreCase("")
                    || ArrError.get(i).getTotalMale().equalsIgnoreCase("")) {

                NutritionalStatus = new NutritionalStatus();
                 NutritionalStatus.setFormID(ArrError.get(i).getFormID());
                    NutritionalStatus.setCensusYear(ArrError.get(i).getCensusYear());
                NutritionalStatus.setDistrict(ArrError.get(i).getDistrict());
                NutritionalStatus.setGradeLevel(ArrError.get(i).getGradeLevel());
                NutritionalStatus.setDistrict(ArrError.get(i).getDistrict());
                NutritionalStatus.setValidation(-1);
                NutritionalStatus.setReason("Missing Fieald/s");

                if (ArrError.get(i).getPupilsWeighedFemale() == null || ArrError.get(i).getPupilsWeighedFemale().equalsIgnoreCase("")) {
                    NutritionalStatus.setPupilsWeighedFemale(-1);

                } else {
                    NutritionalStatus.setPupilsWeighedFemale(Integer.parseInt(ArrError.get(i).getPupilsWeighedFemale()));
                }

                if (ArrError.get(i).getPupilsWeighedMale() == null || ArrError.get(i).getPupilsWeighedMale().equalsIgnoreCase("")) {
                    NutritionalStatus.setPupilsWeighedMale(-1);
                } else {

                    NutritionalStatus.setPupilsWeighedMale(Integer.parseInt(ArrError.get(i).getPupilsWeighedMale()));

                }

                if (ArrError.get(i).getPupilsWeighedTotal() == null || ArrError.get(i).getPupilsWeighedTotal().equalsIgnoreCase("")) {
                    NutritionalStatus.setPupilsWeighedTotal(-1);
                } else {
                    NutritionalStatus.setPupilsWeighedTotal(Integer.parseInt(ArrError.get(i).getPupilsWeighedTotal()));
                }

                if (ArrError.get(i).getTotalCount() == null || ArrError.get(i).getTotalCount().equalsIgnoreCase("")) {
                    NutritionalStatus.setTotalCount(-1);
                } else {
                    NutritionalStatus.setTotalCount(Integer.parseInt(ArrError.get(i).getTotalCount()));
                }

                if (ArrError.get(i).getTotalFemale() == null || ArrError.get(i).getTotalFemale().equalsIgnoreCase("")) {
                    NutritionalStatus.setTotalFemale(-1);
                } else {
                    NutritionalStatus.setTotalFemale(Integer.parseInt(ArrError.get(i).getTotalFemale()));
                }

                if (ArrError.get(i).getTotalMale() == null || ArrError.get(i).getTotalMale().equalsIgnoreCase("")) {
                    NutritionalStatus.setTotalMale(-1);
                } else {
                    NutritionalStatus.setTotalMale(Integer.parseInt(ArrError.get(i).getTotalMale()));
                }

                arrNutritionalStatusBMI = new ArrayList<NutritionalStatusBMI>();
                for (int y = 0; y < ArrError.get(i).getNutritionalStatusBMITemp().size(); y++) {
                    NutritionalStatusBMI NutritionalStatusBMI = new NutritionalStatusBMI();
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
                        NutritionalStatusBMI.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());

                        NutritionalStatusBMI.setValidation(-1);
                        NutritionalStatusBMI.setReason("Missing Field/s");
                        arrNutritionalStatusBMI.add(NutritionalStatusBMI);
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
                        NutritionalStatusBMI.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMI.setValidation(-2);
                        NutritionalStatusBMI.setReason("Format Error");
                        arrNutritionalStatusBMI.add(NutritionalStatusBMI);
                    } else if (Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                            != Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())
                            + Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {

                        NutritionalStatusBMI.setFemaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount()));
                        NutritionalStatusBMI.setTotalCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount()));
                        NutritionalStatusBMI.setMaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount()));
                        NutritionalStatusBMI.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMI.setValidation(-3);
                        NutritionalStatusBMI.setReason("Summation Error");
                        arrNutritionalStatusBMI.add(NutritionalStatusBMI);
                    } else {
                        NutritionalStatusBMI.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMI.setMaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount()));
                        NutritionalStatusBMI.setFemaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount()));
                        NutritionalStatusBMI.setTotalCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount()));
                        NutritionalStatusBMI.setValidation(1);
                        arrNutritionalStatusBMI.add(NutritionalStatusBMI);
                    }
                }

                NutritionalStatus.setNutritionalStatusBMI(arrNutritionalStatusBMI);
                TransformData.add(NutritionalStatus);
            } else if (isNumeric(ArrError.get(i).getPupilsWeighedFemale())
                    || isNumeric(ArrError.get(i).getPupilsWeighedMale())
                    || isNumeric(ArrError.get(i).getPupilsWeighedTotal())
                    || isNumeric(ArrError.get(i).getTotalCount())
                    || isNumeric(ArrError.get(i).getTotalFemale())
                    || isNumeric(ArrError.get(i).getTotalMale())) {

                NutritionalStatus = new NutritionalStatus();
                     NutritionalStatus.setFormID(ArrError.get(i).getFormID());
                    NutritionalStatus.setCensusYear(ArrError.get(i).getCensusYear());
                NutritionalStatus.setGradeLevel(ArrError.get(i).getGradeLevel());
                NutritionalStatus.setDistrict(ArrError.get(i).getDistrict());
                NutritionalStatus.setValidation(-2);
                NutritionalStatus.setReason("Format Error");

                if (isNumeric(ArrError.get(i).getPupilsWeighedFemale())) {
                    NutritionalStatus.setPupilsWeighedFemale(-1);

                } else {
                    NutritionalStatus.setPupilsWeighedFemale(Integer.parseInt(ArrError.get(i).getPupilsWeighedFemale()));
                }

                if (isNumeric(ArrError.get(i).getPupilsWeighedMale())) {
                    NutritionalStatus.setPupilsWeighedMale(-1);
                } else {

                    NutritionalStatus.setPupilsWeighedMale(Integer.parseInt(ArrError.get(i).getPupilsWeighedMale()));

                }

                if (isNumeric(ArrError.get(i).getPupilsWeighedTotal())) {
                    NutritionalStatus.setPupilsWeighedTotal(-1);
                } else {
                    NutritionalStatus.setPupilsWeighedTotal(Integer.parseInt(ArrError.get(i).getPupilsWeighedTotal()));
                }

                if (isNumeric(ArrError.get(i).getTotalCount())) {
                    NutritionalStatus.setTotalCount(-1);
                } else {
                    NutritionalStatus.setTotalCount(Integer.parseInt(ArrError.get(i).getTotalCount()));
                }

                if (isNumeric(ArrError.get(i).getTotalFemale())) {
                    NutritionalStatus.setTotalFemale(-1);
                } else {
                    NutritionalStatus.setTotalFemale(Integer.parseInt(ArrError.get(i).getTotalFemale()));
                }

                if (isNumeric(ArrError.get(i).getTotalMale())) {
                    NutritionalStatus.setTotalMale(-1);
                } else {
                    NutritionalStatus.setTotalMale(Integer.parseInt(ArrError.get(i).getTotalMale()));
                }

               arrNutritionalStatusBMI = new ArrayList<NutritionalStatusBMI>();
                for (int y = 0; y < ArrError.get(i).getNutritionalStatusBMITemp().size(); y++) {
                    NutritionalStatusBMI NutritionalStatusBMI = new NutritionalStatusBMI();
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
                        NutritionalStatusBMI.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());

                        NutritionalStatusBMI.setValidation(-1);
                        NutritionalStatusBMI.setReason("Missing Field/s");
                        arrNutritionalStatusBMI.add(NutritionalStatusBMI);
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
                        NutritionalStatusBMI.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMI.setValidation(-2);
                        NutritionalStatusBMI.setReason("Format Error");
                        arrNutritionalStatusBMI.add(NutritionalStatusBMI);
                    } else if (Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                            != Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())
                            + Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {

                        NutritionalStatusBMI.setFemaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount()));
                        NutritionalStatusBMI.setTotalCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount()));
                        NutritionalStatusBMI.setMaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount()));
                        NutritionalStatusBMI.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMI.setValidation(-3);
                        NutritionalStatusBMI.setReason("Summation Error");
                        arrNutritionalStatusBMI.add(NutritionalStatusBMI);
                    } else {
                        NutritionalStatusBMI.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMI.setMaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount()));
                        NutritionalStatusBMI.setFemaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount()));
                        NutritionalStatusBMI.setTotalCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount()));
                        NutritionalStatusBMI.setValidation(1);
                        arrNutritionalStatusBMI.add(NutritionalStatusBMI);
                    }
                }

                NutritionalStatus.setNutritionalStatusBMI(arrNutritionalStatusBMI);
                TransformData.add(NutritionalStatus);
            } else if (Integer.parseInt(ArrError.get(i).getPupilsWeighedTotal())
                    != Integer.parseInt(ArrError.get(i).getPupilsWeighedFemale()) + Integer.parseInt(ArrError.get(i).getPupilsWeighedMale())
                    || Integer.parseInt(ArrError.get(i).getTotalCount())
                    != Integer.parseInt(ArrError.get(i).getTotalFemale()) + Integer.parseInt(ArrError.get(i).getTotalMale())) {

                      NutritionalStatus = new NutritionalStatus();
                           NutritionalStatus.setFormID(ArrError.get(i).getFormID());
                    NutritionalStatus.setCensusYear(ArrError.get(i).getCensusYear());
                NutritionalStatus.setGradeLevel(ArrError.get(i).getGradeLevel());
                NutritionalStatus.setDistrict(ArrError.get(i).getDistrict());
                NutritionalStatus.setValidation(-3);
                NutritionalStatus.setReason("Summation Error");

                NutritionalStatus.setPupilsWeighedFemale(Integer.parseInt(ArrError.get(i).getPupilsWeighedFemale()));
                NutritionalStatus.setPupilsWeighedMale(Integer.parseInt(ArrError.get(i).getPupilsWeighedMale()));
                NutritionalStatus.setPupilsWeighedTotal(Integer.parseInt(ArrError.get(i).getPupilsWeighedTotal()));
                NutritionalStatus.setTotalCount(Integer.parseInt(ArrError.get(i).getTotalCount()));
                NutritionalStatus.setTotalFemale(Integer.parseInt(ArrError.get(i).getTotalFemale()));
                NutritionalStatus.setTotalMale(Integer.parseInt(ArrError.get(i).getTotalMale()));

                 arrNutritionalStatusBMI = new ArrayList<NutritionalStatusBMI>();
                for (int y = 0; y < ArrError.get(i).getNutritionalStatusBMITemp().size(); y++) {
                    NutritionalStatusBMI NutritionalStatusBMI = new NutritionalStatusBMI();
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
                        NutritionalStatusBMI.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());

                        NutritionalStatusBMI.setValidation(-1);
                        NutritionalStatusBMI.setReason("Missing Field/s");
                        arrNutritionalStatusBMI.add(NutritionalStatusBMI);
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
                        NutritionalStatusBMI.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMI.setValidation(-2);
                        NutritionalStatusBMI.setReason("Format Error");
                        arrNutritionalStatusBMI.add(NutritionalStatusBMI);
                    } else if (Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                            != Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())
                            + Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {

                        NutritionalStatusBMI.setFemaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount()));
                        NutritionalStatusBMI.setTotalCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount()));
                        NutritionalStatusBMI.setMaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount()));
                        NutritionalStatusBMI.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMI.setValidation(-3);
                        NutritionalStatusBMI.setReason("Summation Error");
                        arrNutritionalStatusBMI.add(NutritionalStatusBMI);
                    } else {
                        NutritionalStatusBMI.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMI.setMaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount()));
                        NutritionalStatusBMI.setFemaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount()));
                        NutritionalStatusBMI.setTotalCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount()));
                        NutritionalStatusBMI.setValidation(1);
                        arrNutritionalStatusBMI.add(NutritionalStatusBMI);
                    }
                }

                NutritionalStatus.setNutritionalStatusBMI(arrNutritionalStatusBMI);
                TransformData.add(NutritionalStatus);

            } else {

                boolean x = false;

                for (int y = 0; y < ArrError.get(i).getNutritionalStatusBMITemp().size(); y++) {
                    NutritionalStatusBMITemp NutritionalStatusBMITemp = new NutritionalStatusBMITemp();
                    if (ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount() == null
                            || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount() == null
                            || ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount().equalsIgnoreCase("")) {

                        x = true;
                    } else if (isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                            || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())
                            || isNumeric(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())) {
                        x = true;
                    } else if (Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                            != Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())
                            + Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {
                        x = true;
                    }
                }

                if (x) {
                        NutritionalStatus = new NutritionalStatus();
                             NutritionalStatus.setFormID(ArrError.get(i).getFormID());
                    NutritionalStatus.setCensusYear(ArrError.get(i).getCensusYear());
                     NutritionalStatus.setGradeLevel(ArrError.get(i).getGradeLevel());
                       NutritionalStatus.setDistrict(ArrError.get(i).getDistrict());
                    NutritionalStatus.setValidation(1);
                    NutritionalStatus.setReason("");

                    NutritionalStatus.setPupilsWeighedFemale(Integer.parseInt(ArrError.get(i).getPupilsWeighedFemale()));
                    NutritionalStatus.setPupilsWeighedMale(Integer.parseInt(ArrError.get(i).getPupilsWeighedMale()));
                    NutritionalStatus.setPupilsWeighedTotal(Integer.parseInt(ArrError.get(i).getPupilsWeighedTotal()));
                    NutritionalStatus.setTotalCount(Integer.parseInt(ArrError.get(i).getTotalCount()));
                    NutritionalStatus.setTotalFemale(Integer.parseInt(ArrError.get(i).getTotalFemale()));
                    NutritionalStatus.setTotalMale(Integer.parseInt(ArrError.get(i).getTotalMale()));

                     arrNutritionalStatusBMI = new ArrayList<NutritionalStatusBMI>();
                for (int y = 0; y < ArrError.get(i).getNutritionalStatusBMITemp().size(); y++) {
                    NutritionalStatusBMI NutritionalStatusBMI = new NutritionalStatusBMI();
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
                        NutritionalStatusBMI.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());

                        NutritionalStatusBMI.setValidation(-1);
                        NutritionalStatusBMI.setReason("Missing Field/s");
                        arrNutritionalStatusBMI.add(NutritionalStatusBMI);
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
                        NutritionalStatusBMI.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMI.setValidation(-2);
                        NutritionalStatusBMI.setReason("Format Error");
                        arrNutritionalStatusBMI.add(NutritionalStatusBMI);
                    } else if (Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount())
                            != Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount())
                            + Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount())) {

                        NutritionalStatusBMI.setFemaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount()));
                        NutritionalStatusBMI.setTotalCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount()));
                        NutritionalStatusBMI.setMaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount()));
                        NutritionalStatusBMI.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMI.setValidation(-3);
                        NutritionalStatusBMI.setReason("Summation Error");
                        arrNutritionalStatusBMI.add(NutritionalStatusBMI);
                    } else {
                        NutritionalStatusBMI.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMI.setMaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount()));
                        NutritionalStatusBMI.setFemaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount()));
                        NutritionalStatusBMI.setTotalCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount()));
                        NutritionalStatusBMI.setValidation(1);
                        NutritionalStatusBMI.setReason("");
                        arrNutritionalStatusBMI.add(NutritionalStatusBMI);
                    }
                }

                NutritionalStatus.setNutritionalStatusBMI(arrNutritionalStatusBMI);
                TransformData.add(NutritionalStatus);

                } else {
                    NutritionalStatus = new NutritionalStatus();
                         NutritionalStatus.setFormID(ArrError.get(i).getFormID());
                    NutritionalStatus.setCensusYear(ArrError.get(i).getCensusYear());
                     NutritionalStatus.setDistrict(ArrError.get(i).getDistrict());
                    NutritionalStatus.setGradeLevel(ArrError.get(i).getGradeLevel());
                    NutritionalStatus.setTotalMale(Integer.parseInt(ArrError.get(i).getTotalMale()));
                    NutritionalStatus.setTotalFemale(Integer.parseInt(ArrError.get(i).getTotalFemale()));
                    NutritionalStatus.setTotalCount(Integer.parseInt(ArrError.get(i).getTotalCount()));
                    NutritionalStatus.setValidation(1);
                     NutritionalStatus.setReason("");
                    NutritionalStatus.setPupilsWeighedFemale(Integer.parseInt(ArrError.get(i).getPupilsWeighedFemale()));
                    NutritionalStatus.setPupilsWeighedMale(Integer.parseInt(ArrError.get(i).getPupilsWeighedMale()));
                    NutritionalStatus.setPupilsWeighedTotal(Integer.parseInt(ArrError.get(i).getPupilsWeighedTotal()));

                    arrNutritionalStatusBMI = new ArrayList<>();
                    for (int y = 0; y < ArrError.get(i).getNutritionalStatusBMITemp().size(); y++) {
                      NutritionalStatusBMI  NutritionalStatusBMI = new NutritionalStatusBMI();
                        NutritionalStatusBMI.setBMI(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getBMI());
                        NutritionalStatusBMI.setMaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getMaleCount()));
                        NutritionalStatusBMI.setFemaleCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getFemaleCount()));
                        NutritionalStatusBMI.setTotalCount(Integer.parseInt(ArrError.get(i).getNutritionalStatusBMITemp().get(y).getTotalCount()));
                        NutritionalStatusBMI.setValidation(1);
                         NutritionalStatusBMI.setReason("");
                        arrNutritionalStatusBMI.add(NutritionalStatusBMI);

                    }
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
