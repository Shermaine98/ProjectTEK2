/*
 *  ProjectTEK - DLSU CCS 2016
 * 
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
import java.util.Calendar;
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
public class ValiHighestAttaintment extends BaseServlet {

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        /**
         * REQUEST FOR THE PAGE IF UPLOAD OR UPDATE(EDIT DATA WITH ERRORS)
         * REQUEST FOR YEAR - YEAR OF UPDATE OR UPLOAD
         */
        String page = request.getParameter("page");
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String uploadedBy = request.getParameter("uploadedBy");
        String errorMessage = request.getParameter("errorMessage");

        HighestCompletedDAO HighestCompletedDAO = new HighestCompletedDAO();
        RecordDAO recordDAO = new RecordDAO();

        //FINAL ARRAY SAVE TO DB
        ArrayList<HighestCompleted> ArrhighestCompleted = new ArrayList<HighestCompleted>();

        /**
         *
         * IF EDITED/UPDATE DELETE THE RECORD AND THE DATA IN TABLE
         *
         * ELSE UPLOAD - UPLOAD THE FILE TO DB
         *
         */
        if (page.equalsIgnoreCase("edited")) {
            try {
                HighestCompletedDAO.deleteHighestCompleted(400000000 + year);
                recordDAO.deleteRecord(400000000 + year);
            } catch (SQLException ex) {
                Logger.getLogger(ValiHighestAttaintment.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (page.equalsIgnoreCase("upload")) {
            boolean reupload = false;

            try {
                reupload = recordDAO.checkExistRecordForReupload(400000000, year);

            } catch (SQLException ex) {
                Logger.getLogger(ValiHighestAttaintment.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (reupload == true) {
                try {
                    HighestCompletedDAO.deleteHighestCompleted(400000000 + year);
                    recordDAO.deleteRecord(400000000 + year);
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
            formID = HighestCompletedDAO.getFormID(year);
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
            String[] validation = request.getParameterValues("validation");

            String[] highestCompletedError = request.getParameterValues("highestCompletedError");
            String[] countError = request.getParameterValues("countError");
            String[] validation2 = request.getParameterValues("validation2");

            HighestCompletedTemp HighestCompletedTemp;
            ArrayList<HighestCompletedAgeGroupTemp> arrHighestCompletedAgeGroupTemp;

            boolean x = false;

            int y = 0;
            int stopper = 0;

            for (int i = 0; i < locationError.length; i++) {
                HighestCompletedTemp = new HighestCompletedTemp();
                HighestCompletedTemp.setFormID(formID);
                HighestCompletedTemp.setYear(year);
                HighestCompletedTemp.setLocation(locationError[i]);
                HighestCompletedTemp.setAgeGroup(ageGroupError[i]);
                HighestCompletedTemp.setSex(sexError[i]);
                HighestCompletedTemp.setTotal(totalError[i].replaceAll(" ", "").replaceAll(",", ""));
                HighestCompletedTemp.setValidation(Integer.parseInt(validation[i]));
                arrHighestCompletedAgeGroupTemp = new ArrayList<HighestCompletedAgeGroupTemp>();

                for (; y < 16 + stopper; y++) {
                    HighestCompletedAgeGroupTemp HighestCompletedAgeGroup = new HighestCompletedAgeGroupTemp();
                    HighestCompletedAgeGroup.sethighestAttaintment(highestCompletedError[y]);
                    HighestCompletedAgeGroup.setCount(countError[y].replaceAll(" ", "").replaceAll(",", ""));
                    HighestCompletedAgeGroup.setValidation(Integer.parseInt(validation2[y]));
                    arrHighestCompletedAgeGroupTemp.add(HighestCompletedAgeGroup);
                }
                stopper = y;
                HighestCompletedTemp.setHighestCompletedAgeGroupTemp(arrHighestCompletedAgeGroupTemp);
                arrHighestCompletedTemp.add(HighestCompletedTemp);
            }

            //TEMP GET ERROR THEN FIX VARIABLES TO BE SAVE IN DB / NO ERROR ADD IN ArrByAgeGroupSex
            HighestCompletedEditedNoError = new HighestCompletedChecker(arrHighestCompletedTemp, year, formID).getArrayNoError();
            HighestCompletedEditedTempError = new HighestCompletedChecker(arrHighestCompletedTemp, year, formID).getArrayError();

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
        int stopper = 0;

        for (int i = 0; i < location.length; i++) {
            HighestCompleted = new HighestCompleted();
            HighestCompleted.setFormID(formID);
            HighestCompleted.setYear(year);
            HighestCompleted.setLocation(location[i]);
            HighestCompleted.setAgeGroup(ageGroup[i]);
            HighestCompleted.setSex(sex[i]);
            HighestCompleted.setTotal(Integer.parseInt(total[i].replaceAll(" ", "").replaceAll(",", "")));
             HighestCompleted.setValidation(1);
            arrHighestCompletedAgeGroup = new ArrayList<HighestCompletedAgeGroup>();

            for (; y < 16 + stopper; y++) {
                HighestCompletedAgeGroup HighestCompletedAgeGroup = new HighestCompletedAgeGroup();
                HighestCompletedAgeGroup.sethighestCompleted(highestCompleted[y]);
                HighestCompletedAgeGroup.setCount(Integer.parseInt(count[y].replaceAll(" ", "").replaceAll(",", "")));
                HighestCompletedAgeGroup.setValidation(1);
                arrHighestCompletedAgeGroup.add(HighestCompletedAgeGroup);
            }
            stopper = y;
            HighestCompleted.setHighestCompletedAgeGroup(arrHighestCompletedAgeGroup);
            ArrhighestCompleted.add(HighestCompleted);
        }

        x = recordDAO.newRecord(formID, year, Integer.parseInt(uploadedBy));
        if (x) {
            x = HighestCompletedDAO.EncodeHighestCompleted(ArrhighestCompleted);

            if (x) {
                try {

                    if (recordDAO.runRecordHighestDAO(formID, year)) {
                        request.setAttribute("page", "Upload");
                        request.setAttribute("saveToDB", "successDB");
                        RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataDemoServlet?redirect=HighestCompleted");
                        rd.forward(request, response);
                    } else if (!recordDAO.runRecordHighestDAO(formID, year)) {
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
                request.setAttribute("page", "Upload");
                request.setAttribute("ValiHighestAttaintment", "Error");
                request.setAttribute("saveToDB", "Error");
                rd.forward(request, response);
            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/ErrorHandler");
            request.setAttribute("page", "Upload");
            request.setAttribute("ValiHighestAttaintment", "Error");
            request.setAttribute("saveToDB", "Error");
            rd.forward(request, response);
        }
    }
}