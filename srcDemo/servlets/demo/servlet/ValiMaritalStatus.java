/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */
package servlets.demo.servlet;

import checker.demo.MaritalStatusChecker;
import dao.demo.MaritalStatusDAO;
import dao.RecordDAO;
import model.demo.MaritalStatus;
import model.temp.demo.MaritalStatusTemp;
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
public class ValiMaritalStatus extends BaseServlet {

    /**
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException servlet exception
     * @throws IOException servlet IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        PrintWriter out = response.getWriter();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        RecordDAO recordDAO = new RecordDAO();
        String errorMessage = request.getParameter("errorMessage");
         //ARRAY SAVE TO DB
            ArrayList<MaritalStatus> arrMaritalStatus = new ArrayList<MaritalStatus>();
        MaritalStatusDAO MaritalStatusDAO = new MaritalStatusDAO();
//        String year = "100000";

        if (page.equalsIgnoreCase("upload")) {
            boolean reupload = false;
            try {
                reupload = recordDAO.checkExistRecordForReupload(300000000, year);
            } catch (SQLException ex) {
                Logger.getLogger(ValiMaritalStatus.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (reupload == true) {
                try {
                    MaritalStatusDAO.deleteMaritalStatusRecord(300000000 + year);
                    recordDAO.deleteRecord(300000000 + year);
                } catch (SQLException ex) {
                    Logger.getLogger(ValiAgeByGrpServ.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (page.equalsIgnoreCase("edited")) {
            try {
                MaritalStatusDAO.deleteMaritalStatusRecord(300000000 + year);
                recordDAO.deleteRecord(300000000 + year);
            } catch (SQLException ex) {
                Logger.getLogger(ValiAgeByGrpServ.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        int formID = 0;
        try {
            formID = MaritalStatusDAO.getFormID(year);
        } catch (SQLException ex) {
            Logger.getLogger(ValiAgeByGrpServ.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (errorMessage.equalsIgnoreCase("error")) {

            String[] locationError = request.getParameterValues("locationError");
            String[] sexError = request.getParameterValues("sexError");
            String[] ageGroupError = request.getParameterValues("ageGroupError");
            String[] singleError = request.getParameterValues("singleError");
            String[] marriedError = request.getParameterValues("marriedError");
            String[] widowedError = request.getParameterValues("windowedError");
            String[] divorcedSeparatedError = request.getParameterValues("divorcedSeparatedError");
            String[] commonLawLiveInError = request.getParameterValues("commonLawLiveInError");
            String[] unknownError = request.getParameterValues("unknownError");
            String[] totalError = request.getParameterValues("totalError");
            String[] reasonError = request.getParameterValues("errorReason");

            ArrayList<MaritalStatusTemp> MaritalStatusTemp = new ArrayList<MaritalStatusTemp>();
            ArrayList<MaritalStatus> MaritalStatusTempEditedNoError = new ArrayList<MaritalStatus>();
            ArrayList<MaritalStatusTemp> MaritalStatusTempEditedError = new ArrayList<MaritalStatusTemp>();
            ArrayList<MaritalStatus> trnasoformedData = new ArrayList<MaritalStatus>();
           

            //FOR ERROR
            if (locationError != null) {
                for (int i = 0; i < locationError.length; i++) {
                    MaritalStatusTemp maritalStatusTempError = new MaritalStatusTemp();

                    maritalStatusTempError.setYear(year);
                    maritalStatusTempError.setFormID(formID);
                    maritalStatusTempError.setLocation(locationError[i]);
                    maritalStatusTempError.setAgeGroup(ageGroupError[i]);
                    maritalStatusTempError.setSex(sexError[i]);
                    maritalStatusTempError.setSingle(singleError[i].replaceAll(" ", "").replaceAll(",", ""));
                    maritalStatusTempError.setMarried(marriedError[i].replaceAll(" ", "").replaceAll(",", ""));
                    maritalStatusTempError.setWidowed(widowedError[i].replaceAll(" ", "").replaceAll(",", ""));
                    maritalStatusTempError.setDivorcedSeparated(divorcedSeparatedError[i].replaceAll(" ", "").replaceAll(",", ""));
                    maritalStatusTempError.setCommonLawLiveIn(commonLawLiveInError[i].replaceAll(" ", "").replaceAll(",", ""));
                    maritalStatusTempError.setUnknown(unknownError[i].replaceAll(" ", "").replaceAll(",", ""));
                    maritalStatusTempError.setTotal(totalError[i].replaceAll(" ", "").replaceAll(",", ""));
                    maritalStatusTempError.setValidation(Integer.parseInt(reasonError[i]));
                    MaritalStatusTemp.add(maritalStatusTempError);
                }

                //TEMP GET ERROR THEN FIX VARIABLES TO BE SAVE IN DB / NO ERROR ADD IN ArrByAgeGroupSex
                MaritalStatusTempEditedNoError = new MaritalStatusChecker(MaritalStatusTemp, year, formID).getArrayNoError();
                MaritalStatusTempEditedError = new MaritalStatusChecker(MaritalStatusTemp, year, formID).getArrayError();

                MaritalStatusChecker toDb = new MaritalStatusChecker();
                trnasoformedData = toDb.TransformData(MaritalStatusTempEditedError);

                arrMaritalStatus.addAll(trnasoformedData);
                arrMaritalStatus.addAll(MaritalStatusTempEditedNoError);
            }
        }
        //END FOR ERROR

        String[] location = request.getParameterValues("location");
        String[] sex = request.getParameterValues("sex");
        String[] ageGroup = request.getParameterValues("ageGroup");
        String[] single = request.getParameterValues("single");
        String[] married = request.getParameterValues("married");
        String[] widowed = request.getParameterValues("windowed");
        String[] divorcedSeparated = request.getParameterValues("divorcedSeparated");
        String[] commonLawLiveIn = request.getParameterValues("commonLawLiveIn");
        String[] unknown = request.getParameterValues("unknown");
        String[] total = request.getParameterValues("total");
        String uploadedBy = request.getParameter("uploadedBy");

        MaritalStatus MaritalStatus;

        boolean x = false;
        recordDAO.newRecord(formID, year, Integer.parseInt(uploadedBy));

        for (int i = 0; i < location.length; i++) {
            MaritalStatus = new MaritalStatus();
            MaritalStatus.setFormID(formID);
            MaritalStatus.setYear(year);
            MaritalStatus.setLocation(location[i]);
            MaritalStatus.setAgeGroup(ageGroup[i]);
            MaritalStatus.setSex(sex[i]);
            MaritalStatus.setSingle(Integer.parseInt(single[i].replaceAll(" ", "").replaceAll(",", "")));
            MaritalStatus.setMarried(Integer.parseInt(married[i].replaceAll(" ", "").replaceAll(",", "")));
            MaritalStatus.setWidowed(Integer.parseInt(widowed[i].replaceAll(" ", "").replaceAll(",", "")));
            MaritalStatus.setDivorcedSeparated(Integer.parseInt(divorcedSeparated[i].replaceAll(" ", "").replaceAll(",", "")));
            MaritalStatus.setCommonLawLiveIn(Integer.parseInt(commonLawLiveIn[i].replaceAll(" ", "").replaceAll(",", "")));
            MaritalStatus.setUnknown(Integer.parseInt(unknown[i].replaceAll(" ", "").replaceAll(",", "")));
            MaritalStatus.setTotal(Integer.parseInt(total[i].replaceAll(" ", "").replaceAll(",", "")));
            MaritalStatus.setValidation(1);
            arrMaritalStatus.add(MaritalStatus);
        }

        x = MaritalStatusDAO.EncodeMaritalStatus(arrMaritalStatus);

        if (x) {
            try {

                if (recordDAO.runRecordMaritalGroupDAO(formID, year)) {
                    request.setAttribute("page", "Upload");
                    request.setAttribute("saveToDB", "successDB");
                    RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataDemoServlet?redirect=MaritalStatus");
                    rd.forward(request, response);
                } else if (!recordDAO.runRecordMaritalGroupDAO(formID, year))  {
                    request.setAttribute("page", "Upload");
                    request.setAttribute("saveToDB", "SaveWithError");
                    RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataDemoServlet?redirect=MaritalStatus");
                    rd.forward(request, response);
                }  else {
                    request.setAttribute("page", "Upload");
                    request.setAttribute("saveToDB", "Error");
                    RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataDemoServlet?redirect=MaritalStatus");
                    rd.forward(request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ValiMaritalStatus.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/ErrorHandler");
            request.setAttribute("page", "Upload");
            request.setAttribute("maritalStatus", "Error");
            request.setAttribute("saveToDB", "Error");
            rd.forward(request, response);
        }

    }
}
