/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package servlets.health.servlet;

import dao.RecordDAO;
import dao.health.HelathRecordsDAO;
import dao.health.NutritionalStatusDAO;
import checker.health.NutritionalStatusChecker;
import model.health.NutritionalStatus;
import model.health.NutritionalStatusBMI;
import model.temp.health.NutritionalStatusBMITemp;
import model.temp.health.NutritionalStatusTemp;
import servlets.servlet.BaseServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */

public class ValiNutritionalStatus extends BaseServlet {
  /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException servlet exception
     * @throws IOException servlet IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        /**
         * REQUEST FOR THE PAGE IF UPLOAD OR UPDATE(EDIT DATA WITH ERRORS)
         * REQUEST FOR YEAR - YEAR OF UPDATE OR UPLOAD
         */
        String page = request.getParameter("page");
        String errorMessage = request.getParameter("errorMessage");
        String year = request.getParameter("year");
        String uploadedBy = request.getParameter("uploadedBy");

        NutritionalStatusDAO NutritionalStatusDAO = new NutritionalStatusDAO();
        HelathRecordsDAO HelathRecordsDAO = new HelathRecordsDAO();
        RecordDAO recordDAO = new RecordDAO();
        // FINAL ARRAY SAVE TO DB
        ArrayList<NutritionalStatus> ArrNutritionalStatus = new ArrayList<NutritionalStatus>();

        /**
         *
         * IF EDITED/UPDATE DELETE THE RECORD AND THE DATA IN TABLE
         *
         * ELSE UPLOAD - UPLOAD THE FILE TO DB
         *
         */
        if (page.equalsIgnoreCase("edited")) {
            try {
                NutritionalStatusDAO.deleteNutritionalStatusRecordAll(800000000 + (Integer.parseInt(year)));
                recordDAO.deleteRecord(800000000 + Integer.parseInt(year));
            } catch (SQLException ex) {
                Logger.getLogger(ValiNutritionalStatus.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (page.equalsIgnoreCase("upload")) {
            boolean reupload = false;
            try {
                reupload = recordDAO.checkExistRecordForReupload(800000000, Integer.parseInt(year));
            } catch (SQLException ex) {
                Logger.getLogger(ValiNutritionalStatus.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (reupload == true) {
                try {
                    NutritionalStatusDAO.deleteNutritionalStatusRecordAll(800000000 + (Integer.parseInt(year)));
                    recordDAO.deleteRecord(800000000 + (Integer.parseInt(year)));
                } catch (SQLException ex) {
                    Logger.getLogger(ValiNutritionalStatus.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        /*
        
        GET THE FORM ID FIRST
        
         */
        int formID = 0;
        try {
            formID = NutritionalStatusDAO.getFormID(Integer.parseInt(year.trim()));
        } catch (SQLException ex) {
            Logger.getLogger(ValiNutritionalStatus.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (errorMessage.equalsIgnoreCase("error")) {
            /**
             * RECHECK PARAMETERS FROM ERROR (THIS IS IF THE ERROR TABLE IS
             * EDITED
             */

            ArrayList<NutritionalStatus> NutritionalStatusEditedNoError = new ArrayList<NutritionalStatus>();
            ArrayList<NutritionalStatusTemp> NutritionalStatusEditedTempError = new ArrayList<NutritionalStatusTemp>();

            /**
             * TEMPORARY STORAGE FOR THE ERROR ARRAYS
             */
            ArrayList<NutritionalStatusTemp> NutritionalStatusTemp = new ArrayList<NutritionalStatusTemp>();
            /**
             * TO SAVE THE TRANSFORMED DATA
             */

            ArrayList<NutritionalStatus> trnasoformedData = new ArrayList<NutritionalStatus>();

        String[] districtError = request.getParameterValues("districtError");
        String[] gradeLevelError = request.getParameterValues("gradeLevelError");
        String[] totalMaleError = request.getParameterValues("totalMaleError");
        String[] totalFemaleError = request.getParameterValues("totalFemaleError");
        String[] totalCountError = request.getParameterValues("totalCountError");
        String[] pupilsWeighedMaleError = request.getParameterValues("pupilsWeighedMaleError");
        String[] pupilsWeighedFemaleError = request.getParameterValues("pupilsWeighedFemaleError");
        String[] pupilsWeighedTotalError = request.getParameterValues("pupilsWeighedTotalError");

        String[] BMIError = request.getParameterValues("bmiError");
        String[] BmaleCountError = request.getParameterValues("maleCountError");
        String[] BfemaleCountError = request.getParameterValues("femaleCountError");
        String[] BtotalCountError = request.getParameterValues("bTotalCountError");

        NutritionalStatusTemp nutritionalStatusTemp;
        ArrayList<NutritionalStatusBMITemp> arrNutritionalStatusBMITemp;

        boolean x = false;
        int y = 0;
        int stopper = 0;
        
        for (int i = 0; i < districtError.length; i++) {
            nutritionalStatusTemp = new NutritionalStatusTemp();
            nutritionalStatusTemp.setFormID(formID);
            nutritionalStatusTemp.setCensusYear(Integer.parseInt(year.trim()));
            nutritionalStatusTemp.setDistrict(districtError[i]);
            nutritionalStatusTemp.setGradeLevel(gradeLevelError[i]);
            nutritionalStatusTemp.setTotalMale(totalMaleError[i].replaceAll(" ", "").replaceAll(",", ""));
            nutritionalStatusTemp.setTotalFemale(totalFemaleError[i].replaceAll(" ", "").replaceAll(",", ""));
            nutritionalStatusTemp.setTotalCount(totalCountError[i].replaceAll(" ", "").replaceAll(",", ""));
            nutritionalStatusTemp.setPupilsWeighedMale(pupilsWeighedMaleError[i].replaceAll(" ", "").replaceAll(",", ""));
            nutritionalStatusTemp.setPupilsWeighedFemale(pupilsWeighedFemaleError[i].replaceAll(" ", "").replaceAll(",", ""));
            nutritionalStatusTemp.setPupilsWeighedTotal(pupilsWeighedTotalError[i].replaceAll(" ", "").replaceAll(",", ""));
            nutritionalStatusTemp.setValidation(-1);
            arrNutritionalStatusBMITemp = new ArrayList<NutritionalStatusBMITemp>();
            
            for (; y < 5 + stopper; y++) {
                NutritionalStatusBMITemp NutritionalStatusBMITemp = new NutritionalStatusBMITemp();
                NutritionalStatusBMITemp.setBMI(BMIError[y]);
                NutritionalStatusBMITemp.setFemaleCount(BfemaleCountError[y].replaceAll(" ", "").replaceAll(",", ""));
                NutritionalStatusBMITemp.setMaleCount(BmaleCountError[y].replaceAll(" ", "").replaceAll(",", ""));
                NutritionalStatusBMITemp.setTotalCount(BtotalCountError[y].replaceAll(" ", "").replaceAll(",", ""));
                arrNutritionalStatusBMITemp.add(NutritionalStatusBMITemp);
              
            }
            stopper = y;
            nutritionalStatusTemp.setNutritionalStatusTemp(arrNutritionalStatusBMITemp);
            NutritionalStatusTemp.add(nutritionalStatusTemp);
        }

            //TEMP GET ERROR THEN FIX VARIABLES TO BE SAVE IN DB / NO ERROR ADD IN ArrByAgeGroupSex
            NutritionalStatusEditedNoError = new NutritionalStatusChecker(NutritionalStatusTemp, Integer.parseInt(year), formID).getArrayNoError();
            NutritionalStatusEditedTempError = new NutritionalStatusChecker(NutritionalStatusTemp, Integer.parseInt(year), formID).getArrayError();

            NutritionalStatusChecker toDb = new NutritionalStatusChecker();
            trnasoformedData = toDb.TransformData(NutritionalStatusEditedTempError);

            ArrNutritionalStatus.addAll(trnasoformedData);
            ArrNutritionalStatus.addAll(NutritionalStatusEditedNoError);
            //END FOR ERROR

        }

        String[] district = request.getParameterValues("district");
        String[] gradeLevel = request.getParameterValues("gradeLevel");
        String[] totalMale = request.getParameterValues("totalMale");
        String[] totalFemale = request.getParameterValues("totalFemale");
        String[] totalCount = request.getParameterValues("totalCount");
        String[] pupilsWeighedMale = request.getParameterValues("pupilsWeighedMale");
        String[] pupilsWeighedFemale = request.getParameterValues("pupilsWeighedFemale");
        String[] pupilsWeighedTotal = request.getParameterValues("pupilsWeighedTotal");

        String[] BMI = request.getParameterValues("bmi");
        String[] BmaleCount = request.getParameterValues("maleCount");
        String[] BfemaleCount = request.getParameterValues("femaleCount");
        String[] BtotalCount = request.getParameterValues("bTotalCount");

        NutritionalStatus nutritionalStatus;
        ArrayList<NutritionalStatusBMI> arrNutritionalStatusBMI;

        boolean x = false;
        int y = 0;
        int stopper = 0;
        
        for (int i = 0; i < district.length; i++) {
            nutritionalStatus = new NutritionalStatus();
            nutritionalStatus.setFormID(formID);
            nutritionalStatus.setCensusYear(Integer.parseInt(year.trim()));
            nutritionalStatus.setDistrict(district[i]);
            nutritionalStatus.setGradeLevel(gradeLevel[i]);
            nutritionalStatus.setTotalMale(Integer.parseInt(totalMale[i].replaceAll(" ", "").replaceAll(",", "")));
            nutritionalStatus.setTotalFemale(Integer.parseInt(totalFemale[i].replaceAll(" ", "").replaceAll(",", "")));
            nutritionalStatus.setTotalCount(Integer.parseInt(totalCount[i].replaceAll(" ", "").replaceAll(",", "")));
            nutritionalStatus.setPupilsWeighedMale(Integer.parseInt(pupilsWeighedMale[i].replaceAll(" ", "").replaceAll(",", "")));
            nutritionalStatus.setPupilsWeighedFemale(Integer.parseInt(pupilsWeighedFemale[i].replaceAll(" ", "").replaceAll(",", "")));
            nutritionalStatus.setPupilsWeighedTotal(Integer.parseInt(pupilsWeighedTotal[i].replaceAll(" ", "").replaceAll(",", "")));
             nutritionalStatus.setValidation(1);
            arrNutritionalStatusBMI = new ArrayList<NutritionalStatusBMI>();
            for (; y < 5 + stopper; y++) {
                NutritionalStatusBMI NutritionalStatusBMI = new NutritionalStatusBMI();
                NutritionalStatusBMI.setBMI(BMI[y]);
                NutritionalStatusBMI.setFemaleCount(Integer.parseInt(BfemaleCount[y].replaceAll(" ", "").replaceAll(",", "")));
                NutritionalStatusBMI.setMaleCount(Integer.parseInt(BmaleCount[y].replaceAll(" ", "").replaceAll(",", "")));
                NutritionalStatusBMI.setTotalCount(Integer.parseInt(BtotalCount[y].replaceAll(" ", "").replaceAll(",", "")));
                arrNutritionalStatusBMI.add(NutritionalStatusBMI);
              
            }
            stopper = y;
            nutritionalStatus.setNutritionalStatusBMI(arrNutritionalStatusBMI);
            ArrNutritionalStatus.add(nutritionalStatus);
        }
        x = recordDAO.newRecord(formID, Integer.parseInt(year.trim()), Integer.parseInt(uploadedBy));

        if (x) {
            x = NutritionalStatusDAO.EncodeNutritionalStatus(ArrNutritionalStatus);

            if (x) {
                try {

                    if (HelathRecordsDAO.RunNutritionalStatus(formID, Integer.parseInt(year.trim()))) {
                        request.setAttribute("page", "Upload");
                        request.setAttribute("saveToDB", "successDB");
                        RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataHealthServlet?redirect=percentageDist");
                        rd.forward(request, response);
                    } else if (!HelathRecordsDAO.RunNutritionalStatus(formID, Integer.parseInt(year.trim()))) {
                        request.setAttribute("page", "Upload");
                        request.setAttribute("saveToDB", "SaveWithError");
                        RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataHealthServlet?redirect=percentageDist");
                        rd.forward(request, response);
                    } else {
                        request.setAttribute("page", "Upload");
                        request.setAttribute("saveToDB", "Error");
                        RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataHealthServlet?redirect=percentageDist");
                        rd.forward(request, response);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ValiNutritionalStatus.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/ErrorHandler");
                request.setAttribute("page", "percentageDist");
                request.setAttribute("action", "upload");
                rd.forward(request, response);
            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/ErrorHandler");
            request.setAttribute("page", "percentageDist");
            request.setAttribute("action", "upload");
            rd.forward(request, response);
        }
    }
}
