/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.demo.servlet;

import checker.demo.ByAgeGroupSexChecker;
import dao.demo.ByAgeGroupSexDAO;
import dao.RecordDAO;
import model.demo.ByAgeGroupSex;
import model.temp.demo.ByAgeGroupTemp;
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

public class ValiAgeByGrpServ extends BaseServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = request.getParameter("page");
        PrintWriter out = response.getWriter();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        ByAgeGroupSexDAO ByAgeGroupSexDAO = new ByAgeGroupSexDAO();
        RecordDAO recordDAO = new RecordDAO();
        String errorMessage = request.getParameter("errorMessage");
        //ARRAY SAVE TO DB
        ArrayList<ByAgeGroupSex> ArrByAgeGroupSex = new ArrayList<ByAgeGroupSex>();

        if (page.equalsIgnoreCase("upload")) {
            boolean reupload = false;
            try {
                reupload = recordDAO.checkExistRecordForReupload(200000000, year);
            } catch (SQLException ex) {
                Logger.getLogger(ValiAgeByGrpServ.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (reupload == true) {
                try {
                    ByAgeGroupSexDAO.deleteAgeGroupRecord(200000000 + year);
                    recordDAO.deleteRecord(200000000 + year);
                } catch (SQLException ex) {
                    Logger.getLogger(ValiAgeByGrpServ.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (page.equalsIgnoreCase("edited")) {
            try {
                ByAgeGroupSexDAO.deleteAgeGroupRecord(200000000 + year);
                recordDAO.deleteRecord(200000000 + year);
            } catch (SQLException ex) {
                Logger.getLogger(ValiAgeByGrpServ.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        int formID = 0;
        try {
            formID = ByAgeGroupSexDAO.getFormID(year);
        } catch (SQLException ex) {
            Logger.getLogger(ValiAgeByGrpServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (errorMessage.equalsIgnoreCase("error")) {

            String[] locationError = request.getParameterValues("locationError");
            String[] ageGroupError = request.getParameterValues("ageGroupError");
            String[] bothSexesError = request.getParameterValues("bothSexesError");
            String[] maleError = request.getParameterValues("maleError");
            String[] femaleError = request.getParameterValues("femaleError");
            String[] reasonError = request.getParameterValues("errorReason");

            ArrayList<ByAgeGroupTemp> byAgeGroupTemp = new ArrayList<ByAgeGroupTemp>();
            ArrayList<ByAgeGroupSex> byAgeGroupEditedNoError = new ArrayList<ByAgeGroupSex>();
            ArrayList<ByAgeGroupTemp> byAgeGroupEditedError = new ArrayList<ByAgeGroupTemp>();
            ArrayList<ByAgeGroupSex> trnasoformedData = new ArrayList<ByAgeGroupSex>();

            //FOR ERROR
            if (locationError != null) {
                for (int i = 0; i < locationError.length; i++) {
                    ByAgeGroupTemp byAgeGroupTempError = new ByAgeGroupTemp();

                    byAgeGroupTempError.setFormID(formID);
                    byAgeGroupTempError.setBarangay(locationError[i]);
                    byAgeGroupTempError.setAgeGroup(ageGroupError[i]);
                    byAgeGroupTempError.setBothSex(bothSexesError[i].replaceAll(" ", "").replaceAll(",", ""));
                    byAgeGroupTempError.setMaleCount(maleError[i].replaceAll(" ", "").replaceAll(",", ""));
                    byAgeGroupTempError.setFemaleCount(femaleError[i].replaceAll(" ", "").replaceAll(",", ""));
                    byAgeGroupTempError.setYear(year);
                    byAgeGroupTempError.setvalidation(Integer.parseInt(reasonError[i]));

                    byAgeGroupTemp.add(byAgeGroupTempError);
                }

                //TEMP GET ERROR THEN FIX VARIABLES TO BE SAVE IN DB / NO ERROR ADD IN ArrByAgeGroupSex
                byAgeGroupEditedNoError = new ByAgeGroupSexChecker(byAgeGroupTemp, year, formID).getArrayNoError();
                byAgeGroupEditedError = new ByAgeGroupSexChecker(byAgeGroupTemp, year, formID).getArrayError();

                ByAgeGroupSexChecker toDb = new ByAgeGroupSexChecker();
                trnasoformedData = toDb.TransformData(byAgeGroupEditedError);

                ArrByAgeGroupSex.addAll(trnasoformedData);
                ArrByAgeGroupSex.addAll(byAgeGroupEditedNoError);
            }
        }
        //END FOR ERROR
        
        String[] location = request.getParameterValues("location");
        String[] ageGroup = request.getParameterValues("ageGroup");
        String[] bothSexes = request.getParameterValues("bothSexes");
        String[] male = request.getParameterValues("male");
        String[] female = request.getParameterValues("female");
        String uploadedBy = request.getParameter("uploadedBy");

        ByAgeGroupSex byAgeGroupSex;

        boolean x = false;
        recordDAO.newRecord(formID, year, Integer.parseInt(uploadedBy));
        for (int i = 0; i < location.length; i++) {
            byAgeGroupSex = new ByAgeGroupSex();
            byAgeGroupSex.setFormID(formID);
            byAgeGroupSex.setYear(year);
            byAgeGroupSex.setBarangay(location[i]);
            byAgeGroupSex.setAgeGroup(ageGroup[i]);
            byAgeGroupSex.setBothSex(Integer.parseInt(bothSexes[i].replaceAll(" ", "").replaceAll(",", "")));
            byAgeGroupSex.setFemaleCount(Integer.parseInt(male[i].replaceAll(" ", "").replaceAll(",", "")));
            byAgeGroupSex.setMaleCount(Integer.parseInt(female[i].replaceAll(" ", "").replaceAll(",", "")));
            byAgeGroupSex.setValidation(1);
            ArrByAgeGroupSex.add(byAgeGroupSex);
        }

        x = ByAgeGroupSexDAO.EncodeByAgeGroupSex(ArrByAgeGroupSex);

        if (x) {
            try {
                if (recordDAO.runRecordAgeGroupDAO(formID, year))  {
                    request.setAttribute("page", "Upload");
                    request.setAttribute("saveToDB", "successDB");
                    RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataDemoServlet?redirect=byAgeGroupSex");
                    rd.forward(request, response);
                } else if (!recordDAO.runRecordAgeGroupDAO(formID, year))  {
                    request.setAttribute("page", "Upload");
                    request.setAttribute("saveToDB", "SaveWithError");
                    RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataDemoServlet?redirect=byAgeGroupSex");
                    rd.forward(request, response);
                } else {
                    request.setAttribute("page", "Upload");
                    request.setAttribute("saveToDB", "Error");
                    RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataDemoServlet?redirect=byAgeGroupSex");
                    rd.forward(request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ValiAgeByGrpServ.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/ErrorHandler");
            request.setAttribute("page", "Upload");
            request.setAttribute("AgeGroupSuccess", "Error");
            request.setAttribute("saveToDB", "Error");
            rd.forward(request, response);
        }
    }
}
