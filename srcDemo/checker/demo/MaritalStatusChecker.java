/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package checker.demo;

import model.demo.MaritalStatus;
import model.temp.demo.MaritalStatusTemp;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Gian Carlo Roxas
 * @author shermaine Sy
 * @author Geraldine Atayan
 *
 */
public class MaritalStatusChecker {

    private ArrayList<MaritalStatus> arrayNoError;
    private ArrayList<MaritalStatusTemp> arrayError;

    /*
     *@ ArrError temp stored data, all strings
     */
    public  MaritalStatusChecker() {
    }

    public MaritalStatusChecker(ArrayList<MaritalStatusTemp> ArrError) {

        MaritalStatus MaritalStatus;
        MaritalStatusTemp MaritalStatusTemp;
        arrayNoError = new ArrayList<>();
        arrayError = new ArrayList<>();

        for (int i = 0; i < ArrError.size(); i++) {
            MaritalStatusTemp = new MaritalStatusTemp();
            if (ArrError.get(i).getLocation() == null
                    || ArrError.get(i).getSex() == null
                    || ArrError.get(i).getAgeGroup() == null
                    || ArrError.get(i).getTotal() == null
                    || ArrError.get(i).getSingle() == null
                    || ArrError.get(i).getMarried() == null
                    || ArrError.get(i).getWidowed() == null
                    || ArrError.get(i).getCommonLawLiveIn() == null
                    || ArrError.get(i).getDivorcedSeparated() == null
                    || ArrError.get(i).getUnknown() == null
                    || ArrError.get(i).getLocation().equalsIgnoreCase("")
                    || ArrError.get(i).getSex().equalsIgnoreCase("")
                    || ArrError.get(i).getAgeGroup().equalsIgnoreCase("")
                    || ArrError.get(i).getTotal().equalsIgnoreCase("")
                    || ArrError.get(i).getSingle().equalsIgnoreCase("")
                    || ArrError.get(i).getMarried().equalsIgnoreCase("")
                    || ArrError.get(i).getWidowed().equalsIgnoreCase("")
                    || ArrError.get(i).getCommonLawLiveIn().equalsIgnoreCase("")
                    || ArrError.get(i).getDivorcedSeparated().equalsIgnoreCase("")
                    || ArrError.get(i).getUnknown().equalsIgnoreCase("")) {

                MaritalStatusTemp.setLocation(ArrError.get(i).getLocation());
                MaritalStatusTemp.setAgeGroup(ArrError.get(i).getAgeGroup());
                MaritalStatusTemp.setSex(ArrError.get(i).getSex());
                MaritalStatusTemp.setValidation(-1);
                MaritalStatusTemp.setErrorReason("Missing Field/s");

                if (ArrError.get(i).getTotal() == null || ArrError.get(i).getTotal().equalsIgnoreCase("")) {
                    MaritalStatusTemp.setTotal("");
                } else {
                    MaritalStatusTemp.setTotal(ArrError.get(i).getTotal());
                }
                if (ArrError.get(i).getSingle() == null || ArrError.get(i).getSingle().equalsIgnoreCase("")) {
                    MaritalStatusTemp.setSingle("");
                } else {
                    MaritalStatusTemp.setSingle(ArrError.get(i).getSingle());
                }

                if (ArrError.get(i).getMarried() == null || ArrError.get(i).getMarried().equalsIgnoreCase("")) {
                    MaritalStatusTemp.setMarried("");
                } else {
                    MaritalStatusTemp.setMarried(ArrError.get(i).getMarried());
                }

                if (ArrError.get(i).getWidowed() == null || ArrError.get(i).getWidowed().equalsIgnoreCase("")) {
                    MaritalStatusTemp.setWidowed("");
                } else {
                    MaritalStatusTemp.setWidowed(ArrError.get(i).getWidowed());
                }

                if (ArrError.get(i).getCommonLawLiveIn() == null || ArrError.get(i).getCommonLawLiveIn().equalsIgnoreCase("")) {
                    MaritalStatusTemp.setCommonLawLiveIn("");
                } else {
                    MaritalStatusTemp.setCommonLawLiveIn(ArrError.get(i).getCommonLawLiveIn());
                }

                if (ArrError.get(i).getDivorcedSeparated() == null || ArrError.get(i).getDivorcedSeparated().equalsIgnoreCase("")) {
                    MaritalStatusTemp.setDivorcedSeparated("");
                } else {
                    MaritalStatusTemp.setDivorcedSeparated(ArrError.get(i).getDivorcedSeparated());
                }

                if (ArrError.get(i).getUnknown() == null || ArrError.get(i).getUnknown().equalsIgnoreCase("")) {
                    MaritalStatusTemp.setUnknown("");
                } else {
                    MaritalStatusTemp.setUnknown(ArrError.get(i).getUnknown());
                }

                arrayError.add(MaritalStatusTemp);
            } else if (isNumeric(ArrError.get(i).getTotal())
                    || isNumeric(ArrError.get(i).getSingle())
                    || isNumeric(ArrError.get(i).getMarried())
                    || isNumeric(ArrError.get(i).getWidowed())
                    || isNumeric(ArrError.get(i).getCommonLawLiveIn())
                    || isNumeric(ArrError.get(i).getDivorcedSeparated())
                    || isNumeric(ArrError.get(i).getUnknown())) {

                MaritalStatusTemp.setLocation(ArrError.get(i).getLocation());
                MaritalStatusTemp.setSex(ArrError.get(i).getSex());
                MaritalStatusTemp.setAgeGroup(ArrError.get(i).getAgeGroup());
                MaritalStatusTemp.setValidation(-2);
                MaritalStatusTemp.setErrorReason("Format Error");

                if (isNumeric(ArrError.get(i).getTotal())) {
                    MaritalStatusTemp.setTotal("");
                } else {
                    MaritalStatusTemp.setTotal(ArrError.get(i).getTotal());
                }
                if (isNumeric(ArrError.get(i).getSingle())) {
                    MaritalStatusTemp.setSingle("");
                } else {
                    MaritalStatusTemp.setSingle(ArrError.get(i).getSingle());
                }

                if (isNumeric(ArrError.get(i).getMarried())) {
                    MaritalStatusTemp.setMarried("");
                } else {
                    MaritalStatusTemp.setMarried(ArrError.get(i).getMarried());
                }

                if (isNumeric(ArrError.get(i).getWidowed())) {
                    MaritalStatusTemp.setWidowed("");
                } else {
                    MaritalStatusTemp.setWidowed(ArrError.get(i).getWidowed());
                }

                if (isNumeric(ArrError.get(i).getCommonLawLiveIn())) {
                    MaritalStatusTemp.setCommonLawLiveIn("");
                } else {
                    MaritalStatusTemp.setCommonLawLiveIn(ArrError.get(i).getCommonLawLiveIn());
                }

                if (isNumeric(ArrError.get(i).getDivorcedSeparated())) {
                    MaritalStatusTemp.setDivorcedSeparated("");
                } else {
                    MaritalStatusTemp.setDivorcedSeparated(ArrError.get(i).getDivorcedSeparated());
                }

                if (isNumeric(ArrError.get(i).getUnknown())) {
                    MaritalStatusTemp.setUnknown("");
                } else {
                    MaritalStatusTemp.setUnknown(ArrError.get(i).getUnknown());
                }
                arrayError.add(MaritalStatusTemp);
            } else if (parseInt(ArrError.get(i).getTotal())
                    != parseInt(ArrError.get(i).getSingle())
                    + parseInt(ArrError.get(i).getMarried())
                    + parseInt(ArrError.get(i).getWidowed())
                    + parseInt(ArrError.get(i).getCommonLawLiveIn())
                    + parseInt(ArrError.get(i).getDivorcedSeparated())
                    + parseInt(ArrError.get(i).getUnknown())) {

                MaritalStatusTemp.setLocation(ArrError.get(i).getLocation());
                MaritalStatusTemp.setSex(ArrError.get(i).getSex());
                MaritalStatusTemp.setAgeGroup(ArrError.get(i).getAgeGroup());
                MaritalStatusTemp.setTotal((ArrError.get(i).getTotal()));
                MaritalStatusTemp.setSingle((ArrError.get(i).getSingle()));
                MaritalStatusTemp.setMarried((ArrError.get(i).getMarried()));
                MaritalStatusTemp.setWidowed((ArrError.get(i).getWidowed()));
                MaritalStatusTemp.setCommonLawLiveIn((ArrError.get(i).getCommonLawLiveIn()));
                MaritalStatusTemp.setDivorcedSeparated((ArrError.get(i).getDivorcedSeparated()));
                MaritalStatusTemp.setUnknown((ArrError.get(i).getUnknown()));
                MaritalStatusTemp.setValidation(-3);
                MaritalStatusTemp.setErrorReason("Summation Error");
                arrayError.add(MaritalStatusTemp);
            } else {

                MaritalStatus = new MaritalStatus();
                MaritalStatus.setLocation(ArrError.get(i).getLocation());
                MaritalStatus.setSex(ArrError.get(i).getSex());
                MaritalStatus.setAgeGroup(ArrError.get(i).getAgeGroup());
                MaritalStatus.setTotal(parseInt(ArrError.get(i).getTotal()));
                MaritalStatus.setSingle(parseInt(ArrError.get(i).getSingle()));
                MaritalStatus.setMarried(parseInt(ArrError.get(i).getMarried()));
                MaritalStatus.setWidowed(parseInt(ArrError.get(i).getWidowed()));
                MaritalStatus.setCommonLawLiveIn(parseInt(ArrError.get(i).getCommonLawLiveIn()));
                MaritalStatus.setDivorcedSeparated(parseInt(ArrError.get(i).getDivorcedSeparated()));
                MaritalStatus.setUnknown(parseInt(ArrError.get(i).getUnknown()));
                MaritalStatus.setValidation(1);
                arrayNoError.add(MaritalStatus);
            }

        }
    }

    public MaritalStatusChecker(ArrayList<MaritalStatusTemp> ArrError, int year, int formID) {

        MaritalStatus MaritalStatus;
        MaritalStatusTemp MaritalStatusTemp;
        arrayNoError = new ArrayList<>();
        arrayError = new ArrayList<>();

        for (int i = 0; i < ArrError.size(); i++) {

            MaritalStatusTemp = new MaritalStatusTemp();

            if (ArrError.get(i).getLocation() == null
                    || ArrError.get(i).getSex() == null
                    || ArrError.get(i).getAgeGroup() == null
                    || ArrError.get(i).getTotal() == null
                    || ArrError.get(i).getSingle() == null
                    || ArrError.get(i).getMarried() == null
                    || ArrError.get(i).getWidowed() == null
                    || ArrError.get(i).getCommonLawLiveIn() == null
                    || ArrError.get(i).getDivorcedSeparated() == null
                    || ArrError.get(i).getUnknown() == null
                    || ArrError.get(i).getLocation().equalsIgnoreCase("")
                    || ArrError.get(i).getSex().equalsIgnoreCase("")
                    || ArrError.get(i).getAgeGroup().equalsIgnoreCase("")
                    || ArrError.get(i).getTotal().equalsIgnoreCase("")
                    || ArrError.get(i).getSingle().equalsIgnoreCase("")
                    || ArrError.get(i).getMarried().equalsIgnoreCase("")
                    || ArrError.get(i).getWidowed().equalsIgnoreCase("")
                    || ArrError.get(i).getCommonLawLiveIn().equalsIgnoreCase("")
                    || ArrError.get(i).getDivorcedSeparated().equalsIgnoreCase("")
                    || ArrError.get(i).getUnknown().equalsIgnoreCase("")) {

                MaritalStatusTemp.setFormID(formID);
                MaritalStatusTemp.setYear(year);
                MaritalStatusTemp.setLocation(ArrError.get(i).getLocation());
                MaritalStatusTemp.setAgeGroup(ArrError.get(i).getAgeGroup());
                MaritalStatusTemp.setSex(ArrError.get(i).getSex());
                MaritalStatusTemp.setValidation(-1);
                MaritalStatusTemp.setErrorReason("Missing Filed/s");

                if (ArrError.get(i).getTotal() == null || ArrError.get(i).getTotal().equalsIgnoreCase("")) {
                    MaritalStatusTemp.setTotal("");
                } else {
                    MaritalStatusTemp.setTotal(ArrError.get(i).getTotal());
                }
                if (ArrError.get(i).getSingle() == null || ArrError.get(i).getSingle().equalsIgnoreCase("")) {
                    MaritalStatusTemp.setSingle("");
                } else {
                    MaritalStatusTemp.setSingle(ArrError.get(i).getSingle());
                }

                if (ArrError.get(i).getMarried() == null || ArrError.get(i).getMarried().equalsIgnoreCase("")) {
                    MaritalStatusTemp.setMarried("");
                } else {
                    MaritalStatusTemp.setMarried(ArrError.get(i).getMarried());
                }

                if (ArrError.get(i).getWidowed() == null || ArrError.get(i).getWidowed().equalsIgnoreCase("")) {
                    MaritalStatusTemp.setWidowed("");
                } else {
                    MaritalStatusTemp.setWidowed(ArrError.get(i).getWidowed());
                }

                if (ArrError.get(i).getCommonLawLiveIn() == null || ArrError.get(i).getCommonLawLiveIn().equalsIgnoreCase("")) {
                    MaritalStatusTemp.setCommonLawLiveIn("");
                } else {
                    MaritalStatusTemp.setCommonLawLiveIn(ArrError.get(i).getCommonLawLiveIn());
                }

                if (ArrError.get(i).getDivorcedSeparated() == null || ArrError.get(i).getDivorcedSeparated().equalsIgnoreCase("")) {
                    MaritalStatusTemp.setDivorcedSeparated("");
                } else {
                    MaritalStatusTemp.setDivorcedSeparated(ArrError.get(i).getDivorcedSeparated());
                }

                if (ArrError.get(i).getUnknown() == null || ArrError.get(i).getUnknown().equalsIgnoreCase("")) {
                    MaritalStatusTemp.setUnknown("");
                } else {
                    MaritalStatusTemp.setUnknown(ArrError.get(i).getUnknown());
                }

                arrayError.add(MaritalStatusTemp);
            } else if (isNumeric(ArrError.get(i).getTotal())
                    || isNumeric(ArrError.get(i).getSingle())
                    || isNumeric(ArrError.get(i).getMarried())
                    || isNumeric(ArrError.get(i).getWidowed())
                    || isNumeric(ArrError.get(i).getCommonLawLiveIn())
                    || isNumeric(ArrError.get(i).getDivorcedSeparated())
                    || isNumeric(ArrError.get(i).getUnknown())) {
                MaritalStatusTemp.setFormID(formID);
                MaritalStatusTemp.setYear(year);
                MaritalStatusTemp.setLocation(ArrError.get(i).getLocation());
                MaritalStatusTemp.setSex(ArrError.get(i).getSex());
                MaritalStatusTemp.setAgeGroup(ArrError.get(i).getAgeGroup());

                MaritalStatusTemp.setValidation(-2);
                MaritalStatusTemp.setErrorReason("Format Error");

                if (isNumeric(ArrError.get(i).getTotal())) {
                    MaritalStatusTemp.setTotal("");
                } else {
                    MaritalStatusTemp.setTotal(ArrError.get(i).getTotal());
                }
                if (isNumeric(ArrError.get(i).getSingle())) {
                    MaritalStatusTemp.setSingle("");
                } else {
                    MaritalStatusTemp.setSingle(ArrError.get(i).getSingle());
                }

                if (isNumeric(ArrError.get(i).getMarried())) {
                    MaritalStatusTemp.setMarried("");
                } else {
                    MaritalStatusTemp.setMarried(ArrError.get(i).getMarried());
                }

                if (isNumeric(ArrError.get(i).getWidowed())) {
                    MaritalStatusTemp.setWidowed("");
                } else {
                    MaritalStatusTemp.setWidowed(ArrError.get(i).getWidowed());
                }

                if (isNumeric(ArrError.get(i).getCommonLawLiveIn())) {
                    MaritalStatusTemp.setCommonLawLiveIn("");
                } else {
                    MaritalStatusTemp.setCommonLawLiveIn(ArrError.get(i).getCommonLawLiveIn());
                }

                if (isNumeric(ArrError.get(i).getDivorcedSeparated())) {
                    MaritalStatusTemp.setDivorcedSeparated("");
                } else {
                    MaritalStatusTemp.setDivorcedSeparated(ArrError.get(i).getDivorcedSeparated());
                }

                if (isNumeric(ArrError.get(i).getUnknown())) {
                    MaritalStatusTemp.setUnknown("");
                } else {
                    MaritalStatusTemp.setUnknown(ArrError.get(i).getUnknown());
                }
                arrayError.add(MaritalStatusTemp);
            } else if (parseInt(ArrError.get(i).getTotal())
                    != parseInt(ArrError.get(i).getSingle())
                    + parseInt(ArrError.get(i).getMarried())
                    + parseInt(ArrError.get(i).getWidowed())
                    + parseInt(ArrError.get(i).getCommonLawLiveIn())
                    + parseInt(ArrError.get(i).getDivorcedSeparated())
                    + parseInt(ArrError.get(i).getUnknown())) {

                MaritalStatusTemp.setFormID(formID);
                MaritalStatusTemp.setYear(year);
                MaritalStatusTemp.setLocation(ArrError.get(i).getLocation());
                MaritalStatusTemp.setSex(ArrError.get(i).getSex());
                MaritalStatusTemp.setAgeGroup(ArrError.get(i).getAgeGroup());
                MaritalStatusTemp.setTotal((ArrError.get(i).getTotal()));
                MaritalStatusTemp.setSingle((ArrError.get(i).getSingle()));
                MaritalStatusTemp.setMarried((ArrError.get(i).getMarried()));
                MaritalStatusTemp.setWidowed((ArrError.get(i).getWidowed()));
                MaritalStatusTemp.setCommonLawLiveIn((ArrError.get(i).getCommonLawLiveIn()));
                MaritalStatusTemp.setDivorcedSeparated((ArrError.get(i).getDivorcedSeparated()));
                MaritalStatusTemp.setUnknown((ArrError.get(i).getUnknown()));
                MaritalStatusTemp.setValidation(-3);
                MaritalStatusTemp.setErrorReason("Summation error");
                arrayError.add(MaritalStatusTemp);
            } else {
                MaritalStatus = new MaritalStatus();

                MaritalStatus.setFormID(formID);
                MaritalStatus.setYear(year);
                MaritalStatus.setLocation(ArrError.get(i).getLocation());
                MaritalStatus.setSex(ArrError.get(i).getSex());
                MaritalStatus.setAgeGroup(ArrError.get(i).getAgeGroup());
                MaritalStatus.setTotal(parseInt(ArrError.get(i).getTotal()));
                MaritalStatus.setSingle(parseInt(ArrError.get(i).getSingle()));
                MaritalStatus.setMarried(parseInt(ArrError.get(i).getMarried()));
                MaritalStatus.setWidowed(parseInt(ArrError.get(i).getWidowed()));
                MaritalStatus.setCommonLawLiveIn(parseInt(ArrError.get(i).getCommonLawLiveIn()));
                MaritalStatus.setDivorcedSeparated(parseInt(ArrError.get(i).getDivorcedSeparated()));
                MaritalStatus.setUnknown(parseInt(ArrError.get(i).getUnknown()));
                MaritalStatus.setValidation(1);
                arrayNoError.add(MaritalStatus);
            }

        }
    }

    public ArrayList<MaritalStatus> TransformData(ArrayList<MaritalStatusTemp> ArrErrorA) {
        MaritalStatus MaritalStatus;
        ArrayList<MaritalStatus> TransformData = new ArrayList<>();

        for (int i = 0; i < ArrErrorA.size(); i++) {

            MaritalStatus = new MaritalStatus();

            if (ArrErrorA.get(i).getLocation() == null
                    || ArrErrorA.get(i).getSex() == null
                    || ArrErrorA.get(i).getAgeGroup() == null
                    || ArrErrorA.get(i).getTotal() == null
                    || ArrErrorA.get(i).getSingle() == null
                    || ArrErrorA.get(i).getMarried() == null
                    || ArrErrorA.get(i).getWidowed() == null
                    || ArrErrorA.get(i).getCommonLawLiveIn() == null
                    || ArrErrorA.get(i).getDivorcedSeparated() == null
                    || ArrErrorA.get(i).getUnknown() == null
                    || ArrErrorA.get(i).getLocation().equalsIgnoreCase("")
                    || ArrErrorA.get(i).getSex().equalsIgnoreCase("")
                    || ArrErrorA.get(i).getAgeGroup().equalsIgnoreCase("")
                    || ArrErrorA.get(i).getTotal().equalsIgnoreCase("")
                    || ArrErrorA.get(i).getSingle().equalsIgnoreCase("")
                    || ArrErrorA.get(i).getMarried().equalsIgnoreCase("")
                    || ArrErrorA.get(i).getWidowed().equalsIgnoreCase("")
                    || ArrErrorA.get(i).getCommonLawLiveIn().equalsIgnoreCase("")
                    || ArrErrorA.get(i).getDivorcedSeparated().equalsIgnoreCase("")
                    || ArrErrorA.get(i).getUnknown().equalsIgnoreCase("")) {

                MaritalStatus.setFormID(ArrErrorA.get(i).getFormID());
                MaritalStatus.setYear(ArrErrorA.get(i).getYear());
                MaritalStatus.setLocation(ArrErrorA.get(i).getLocation());
                MaritalStatus.setAgeGroup(ArrErrorA.get(i).getAgeGroup());
                MaritalStatus.setSex(ArrErrorA.get(i).getSex());
                MaritalStatus.setValidation(-1);
                MaritalStatus.setReasons("Missing Field/s");

                if (ArrErrorA.get(i).getTotal() == null || ArrErrorA.get(i).getTotal().equalsIgnoreCase("")) {
                    MaritalStatus.setTotal(-1);
                } else {
                    MaritalStatus.setTotal(Integer.parseInt(ArrErrorA.get(i).getTotal()));
                }
                if (ArrErrorA.get(i).getSingle() == null || ArrErrorA.get(i).getSingle().equalsIgnoreCase("")) {
                    MaritalStatus.setSingle(-1);
                } else {
                    MaritalStatus.setSingle(Integer.parseInt(ArrErrorA.get(i).getSingle()));
                }

                if (ArrErrorA.get(i).getMarried() == null || ArrErrorA.get(i).getMarried().equalsIgnoreCase("")) {
                    MaritalStatus.setMarried(-1);
                } else {
                    MaritalStatus.setMarried(Integer.parseInt(ArrErrorA.get(i).getMarried()));
                }

                if (ArrErrorA.get(i).getWidowed() == null || ArrErrorA.get(i).getWidowed().equalsIgnoreCase("")) {
                    MaritalStatus.setWidowed(-1);
                } else {
                    MaritalStatus.setWidowed(Integer.parseInt(ArrErrorA.get(i).getWidowed()));
                }

                if (ArrErrorA.get(i).getCommonLawLiveIn() == null || ArrErrorA.get(i).getCommonLawLiveIn().equalsIgnoreCase("")) {
                    MaritalStatus.setCommonLawLiveIn(-1);
                } else {
                    MaritalStatus.setCommonLawLiveIn(Integer.parseInt(ArrErrorA.get(i).getCommonLawLiveIn()));
                }

                if (ArrErrorA.get(i).getDivorcedSeparated() == null || ArrErrorA.get(i).getDivorcedSeparated().equalsIgnoreCase("")) {
                    MaritalStatus.setDivorcedSeparated(-1);
                } else {
                    MaritalStatus.setDivorcedSeparated(Integer.parseInt(ArrErrorA.get(i).getDivorcedSeparated()));
                }

                if (ArrErrorA.get(i).getUnknown() == null || ArrErrorA.get(i).getUnknown().equalsIgnoreCase("")) {
                    MaritalStatus.setUnknown(-1);
                } else {
                    MaritalStatus.setUnknown(Integer.parseInt(ArrErrorA.get(i).getUnknown()));
                }
                TransformData.add(MaritalStatus);
            } else if (isNumeric(ArrErrorA.get(i).getTotal())
                    || isNumeric(ArrErrorA.get(i).getSingle())
                    || isNumeric(ArrErrorA.get(i).getMarried())
                    || isNumeric(ArrErrorA.get(i).getWidowed())
                    || isNumeric(ArrErrorA.get(i).getCommonLawLiveIn())
                    || isNumeric(ArrErrorA.get(i).getDivorcedSeparated())
                    || isNumeric(ArrErrorA.get(i).getUnknown())) {

                MaritalStatus.setFormID(ArrErrorA.get(i).getFormID());
                MaritalStatus.setLocation(ArrErrorA.get(i).getLocation());
                MaritalStatus.setYear(ArrErrorA.get(i).getYear());
                MaritalStatus.setSex(ArrErrorA.get(i).getSex());
                MaritalStatus.setAgeGroup(ArrErrorA.get(i).getAgeGroup());
                MaritalStatus.setValidation(-2);
                MaritalStatus.setReasons("Format Error");

                if (isNumeric(ArrErrorA.get(i).getTotal())) {
                    MaritalStatus.setTotal(-1);
                } else {
                    MaritalStatus.setTotal(Integer.parseInt(ArrErrorA.get(i).getTotal()));
                }
                if (isNumeric(ArrErrorA.get(i).getSingle())) {
                    MaritalStatus.setSingle(-1);
                } else {
                    MaritalStatus.setSingle(Integer.parseInt(ArrErrorA.get(i).getSingle()));
                }

                if (isNumeric(ArrErrorA.get(i).getMarried())) {
                    MaritalStatus.setMarried(-1);
                } else {
                    MaritalStatus.setMarried(Integer.parseInt(ArrErrorA.get(i).getMarried()));
                }

                if (isNumeric(ArrErrorA.get(i).getWidowed())) {
                    MaritalStatus.setWidowed(-1);
                } else {
                    MaritalStatus.setWidowed(Integer.parseInt(ArrErrorA.get(i).getWidowed()));
                }

                if (isNumeric(ArrErrorA.get(i).getCommonLawLiveIn())) {
                    MaritalStatus.setCommonLawLiveIn(-1);
                } else {
                    MaritalStatus.setCommonLawLiveIn(Integer.parseInt(ArrErrorA.get(i).getCommonLawLiveIn()));
                }

                if (isNumeric(ArrErrorA.get(i).getDivorcedSeparated())) {
                    MaritalStatus.setDivorcedSeparated(-1);
                } else {
                    MaritalStatus.setDivorcedSeparated(Integer.parseInt(ArrErrorA.get(i).getDivorcedSeparated()));
                }

                if (isNumeric(ArrErrorA.get(i).getUnknown())) {
                    MaritalStatus.setUnknown(-1);
                } else {
                    MaritalStatus.setUnknown(Integer.parseInt(ArrErrorA.get(i).getUnknown()));
                }
                TransformData.add(MaritalStatus);
            } else if (parseInt(ArrErrorA.get(i).getTotal())
                    != parseInt(ArrErrorA.get(i).getSingle())
                    + parseInt(ArrErrorA.get(i).getMarried())
                    + parseInt(ArrErrorA.get(i).getWidowed())
                    + parseInt(ArrErrorA.get(i).getCommonLawLiveIn())
                    + parseInt(ArrErrorA.get(i).getDivorcedSeparated())
                    + parseInt(ArrErrorA.get(i).getUnknown())) {

                MaritalStatus.setFormID(ArrErrorA.get(i).getFormID());
                MaritalStatus.setLocation(ArrErrorA.get(i).getLocation());
                MaritalStatus.setYear(ArrErrorA.get(i).getYear());
                MaritalStatus.setSex(ArrErrorA.get(i).getSex());
                MaritalStatus.setAgeGroup(ArrErrorA.get(i).getAgeGroup());
                MaritalStatus.setTotal((Integer.parseInt(ArrErrorA.get(i).getTotal())));
                MaritalStatus.setSingle(Integer.parseInt((ArrErrorA.get(i).getSingle())));
                MaritalStatus.setMarried(Integer.parseInt((ArrErrorA.get(i).getMarried())));
                MaritalStatus.setWidowed(Integer.parseInt((ArrErrorA.get(i).getWidowed())));
                MaritalStatus.setCommonLawLiveIn(Integer.parseInt((ArrErrorA.get(i).getCommonLawLiveIn())));
                MaritalStatus.setDivorcedSeparated(Integer.parseInt((ArrErrorA.get(i).getDivorcedSeparated())));
                MaritalStatus.setUnknown((Integer.parseInt(ArrErrorA.get(i).getUnknown())));
                MaritalStatus.setValidation(-3);
                MaritalStatus.setReasons("Summation Error");
                TransformData.add(MaritalStatus);
            }
        }
        return TransformData;
    }

    public static boolean isNumeric(String str) {
        return !StringUtils.isNumeric(str);
    }

    /**
     * @return the arrayNoError
     */
    public ArrayList<MaritalStatus> getArrayNoError() {
        return arrayNoError;
    }

    /**
     * @param arrayNoError the arrayNoError to set
     */
    public void setArrayNoError(ArrayList<MaritalStatus> arrayNoError) {
        this.arrayNoError = arrayNoError;
    }

    /**
     * @return the arrayError
     */
    public ArrayList<MaritalStatusTemp> getArrayError() {
        return arrayError;
    }

    /**
     * @param arrayError the arrayError to set
     */
    public void setArrayError(ArrayList<MaritalStatusTemp> arrayError) {
        this.arrayError = arrayError;
    }
}
