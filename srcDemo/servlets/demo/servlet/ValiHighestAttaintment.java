/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.demo.servlet;


import checker.demo.HighestCompletedChecker;
import dao.RecordDAO;
import dao.demo.HighestCompletedDAO;
import model.demo.HighestCompleted;
import model.demo.HighestCompletedAgeGroup;
import model.temp.demo.HighestCompletedAgeGroupTemp;
import model.temp.demo.HighestCompletedTemp;
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

public class ValiHighestAttaintment extends BaseServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     PrintWriter out = response.getWriter();
        /**
         * REQUEST FOR THE PAGE IF UPLOAD OR UPDATE(EDIT DATA WITH ERRORS)
         * REQUEST FOR YEAR - YEAR OF UPDATE OR UPLOAD
         */
        out.print("page");
        String page = request.getParameter("page");
        String errorMessage = request.getParameter("errorMessage");
        String year = request.getParameter("year");
        String uploadedBy = request.getParameter("uploadedBy");

        HighestCompletedDAO HighestCompletedDAO = new HighestCompletedDAO();
        RecordDAO recordDAO = new RecordDAO();

        //FINAL ARRAY SAVE TO DB
        ArrayList<HighestCompleted> ArrhighestCompleted = new ArrayList<HighestCompleted>();
        out.print(page);
        /**
         *
         * IF EDITED/UPDATE DELETE THE RECORD AND THE DATA IN TABLE
         *
         * ELSE UPLOAD - UPLOAD THE FILE TO DB
         *
         */
        
        if (page.equalsIgnoreCase("edited")) {
            try {
                HighestCompletedDAO.deleteHighestCompleted(400000000 + (Integer.parseInt(year.trim())));
                recordDAO.deleteRecord(40000000 + (Integer.parseInt(year.trim())));
            } catch (SQLException ex) {
                Logger.getLogger(ValiHighestAttaintment.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (page.equalsIgnoreCase("upload")) {
            boolean reupload = false;

            try {
                reupload = recordDAO.checkExistRecordForReupload(400000000, Integer.parseInt(year.trim()));

            } catch (SQLException ex) {
                Logger.getLogger(ValiHighestAttaintment.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (reupload == true) {
                try {
                    HighestCompletedDAO.deleteHighestCompleted(400000000 + (Integer.parseInt(year.trim())));
                    recordDAO.deleteRecord(400000000 + (Integer.parseInt(year.trim())));
                } catch (SQLException ex) {
                    Logger.getLogger(ValiAgeByGrpServ.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        /*
        
        GET THE FORM ID FIRST
        
         */
        int formID = 0;
        try {
            formID = HighestCompletedDAO.getFormID(Integer.parseInt(year.trim()));
        } catch (SQLException ex) {
            Logger.getLogger(ValiAgeByGrpServ.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (errorMessage.equalsIgnoreCase("error")) {
            /**
             * RECHECK PARAMETERS FROM ERROR (THIS IS IF THE ERROR TABLE IS
             * EDITED
             */
            ArrayList<HighestCompleted> HighestCompletedEditedNoError = new ArrayList<HighestCompleted>();
            ArrayList<HighestCompletedTemp> HighestCompletedEditedTempError = new ArrayList<HighestCompletedTemp>();

            /**
             * TEMPORARY STORAGE FOR THE ERROR ARRAYS
             */
            ArrayList<HighestCompletedTemp> arrHighestCompletedTemp = new ArrayList<HighestCompletedTemp>();

            /**
             * TO SAVE THE TRANSFORMED DATA
             */
            ArrayList<HighestCompleted> trnasoformedData = new ArrayList<HighestCompleted>();

        String[] locationError = request.getParameterValues("locationError");
        String[] sexError = request.getParameterValues("sexError");
        String[] ageGroupError = request.getParameterValues("ageGroupError");
        String[] totalError = request.getParameterValues("totalError");

        String[] highestCompletedError = request.getParameterValues("highestCompletedError");
        String[] countError = request.getParameterValues("countError");

        HighestCompletedTemp HighestCompletedTemp;
        ArrayList<HighestCompletedAgeGroupTemp> arrHighestCompletedAgeGroupTemp;

        boolean x = false;

        int y = 0;
        int stopper=0;

        for (int i = 0; i < locationError.length; i++) {
            HighestCompletedTemp = new HighestCompletedTemp();
            HighestCompletedTemp.setFormID(formID);
            HighestCompletedTemp.setYear(Integer.parseInt(year.trim()));
            HighestCompletedTemp.setLocation(locationError[i]);
            HighestCompletedTemp.setAgeGroup(ageGroupError[i]);
            HighestCompletedTemp.setSex(sexError[i]);
            HighestCompletedTemp.setTotal(highestCompletedError[i].replaceAll(" ", "").replaceAll(",", ""));
            arrHighestCompletedAgeGroupTemp = new ArrayList<HighestCompletedAgeGroupTemp>();

            for (; y < 16 + stopper; y++) {
                HighestCompletedAgeGroupTemp HighestCompletedAgeGroup = new HighestCompletedAgeGroupTemp();
                HighestCompletedAgeGroup.sethighestAttaintment(highestCompletedError[y]);
                HighestCompletedAgeGroup.setCount(countError[y].replaceAll(" ", "").replaceAll(",", ""));
                HighestCompletedAgeGroup.setValidation(true);
                arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroup);          
            }
            stopper = y;
            HighestCompletedTemp.setHighestCompletedAgeGroupTemp(arrHighestCompletedAgeGroupTemp);
            arrHighestCompletedTemp.add(HighestCompletedTemp);
        }

            //TEMP GET ERROR THEN FIX VARIABLES TO BE SAVE IN DB / NO ERROR ADD IN ArrByAgeGroupSex
            HighestCompletedEditedNoError = new HighestCompletedChecker(arrHighestCompletedTemp, Integer.parseInt(year), formID).getArrayNoError();
            HighestCompletedEditedTempError = new HighestCompletedChecker(arrHighestCompletedTemp, Integer.parseInt(year), formID).getArrayError();

            HighestCompletedChecker toDb = new HighestCompletedChecker();
            trnasoformedData = toDb.TransformData(HighestCompletedEditedTempError);

            ArrhighestCompleted.addAll(trnasoformedData);
            ArrhighestCompleted.addAll(HighestCompletedEditedNoError);

            //END FOR ERROR        
        }

        String[] location = request.getParameterValues("location");
        String[] sex = request.getParameterValues("sex");
        String[] ageGroup = request.getParameterValues("ageGroup");
        String[] total = request.getParameterValues("total");

        String[] highestCompleted = request.getParameterValues("highestCompleted");
        String[] count = request.getParameterValues("count");

        HighestCompleted HighestCompleted;
        ArrayList<HighestCompletedAgeGroup> arrHighestCompletedAgeGroup;

        boolean x = false;

        int y = 0;
        int stopper=0;

        for (int i = 0; i < location.length; i++) {
            HighestCompleted = new HighestCompleted();
            HighestCompleted.setFormID(formID);
            HighestCompleted.setYear(Integer.parseInt(year.trim()));
            HighestCompleted.setLocation(location[i]);
            HighestCompleted.setAgeGroup(ageGroup[i]);
            HighestCompleted.setSex(sex[i]);
            HighestCompleted.setTotal(Integer.parseInt(total[i].replaceAll(" ", "").replaceAll(",", "")));
            arrHighestCompletedAgeGroup = new ArrayList<HighestCompletedAgeGroup>();

            for (; y < 16 + stopper; y++) {
                HighestCompletedAgeGroup HighestCompletedAgeGroup = new HighestCompletedAgeGroup();
                HighestCompletedAgeGroup.sethighestCompleted(highestCompleted[y]);
                HighestCompletedAgeGroup.setCount(Integer.parseInt(count[y].replaceAll(" ", "").replaceAll(",", "")));
                HighestCompletedAgeGroup.setValidation(true);
                arrHighestCompletedAgeGroup.add(HighestCompletedAgeGroup);          
            }
            stopper = y;
            HighestCompleted.setHighestCompletedAgeGroup(arrHighestCompletedAgeGroup);
            ArrhighestCompleted.add(HighestCompleted);
        }

        x = recordDAO.newRecord(formID, Integer.parseInt(year.trim()), Integer.parseInt(uploadedBy));
        if (x) {
            x = HighestCompletedDAO.EncodeHighestCompleted(ArrhighestCompleted);

            if (x) {
                try {

                    if (recordDAO.runRecordHighestDAO(formID, Integer.parseInt(year.trim()))) {
                        request.setAttribute("page", "Upload");
                        request.setAttribute("saveToDB", "successDB");
                        RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataDemoServlet?redirect=HighestCompleted");
                        rd.forward(request, response);
                    } else if (!recordDAO.runRecordHighestDAO(formID, Integer.parseInt(year.trim()))) {
                        request.setAttribute("page", "Upload");
                        request.setAttribute("saveToDB", "SaveWithError");
                        RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataDemoServlet?redirect=HighestCompleted");
                        rd.forward(request, response);
                    } else {
                        request.setAttribute("page", "Upload");
                        request.setAttribute("saveToDB", "Error");
                        RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataDemoServlet?redirect=HighestCompleted");
                        rd.forward(request, response);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ValiAgeByGrpServ.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/ErrorHandler");
                request.setAttribute("page", "AgeGroup");
                request.setAttribute("action", "uploading");
                rd.forward(request, response);
            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/ErrorHandler");
            request.setAttribute("page", "AgeGroup");
            request.setAttribute("action", "uploading");
            rd.forward(request, response);
        }
    }
}
