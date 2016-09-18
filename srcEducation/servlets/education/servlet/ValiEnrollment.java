/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.education.servlet;

import checker.education.EnrollmentChecker;
import dao.RecordDAO;
import dao.education.EducationRecordDAO;
import dao.education.EnrollmentDAO;
import model.education.EnrollmentDet;
import model.education.Enrollment;
import model.temp.education.EnrollmentDetTemp;
import model.temp.education.EnrollmentTemp;
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
 * @author Geraldine
 */
public class ValiEnrollment extends BaseServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        /**
         * REQUEST FOR THE PAGE IF UPLOAD OR UPDATE(EDIT DATA WITH ERRORS)
         * REQUEST FOR YEAR - YEAR OF UPDATE OR UPLOAD
         */
        String page = request.getParameter("page");
        String classification = request.getParameter("classification");
        String errorMessage = request.getParameter("errorMessage");
        String year = request.getParameter("year");
        String uploadedBy = request.getParameter("uploadedBy");
        
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        EducationRecordDAO EducationRecordDAO = new EducationRecordDAO();
        RecordDAO recordDAO = new RecordDAO();
        // FINAL ARRAY SAVE TO DB
        ArrayList<Enrollment> ArrEnrollment = new ArrayList<Enrollment>();

        /**
         * DETERMINE IF PUBLIC OR PRIVATE
         */
        int cFormID = 0;
        int formID = 0;
        System.out.println("THIS" + classification);
        if (classification.equalsIgnoreCase("private")) {

            cFormID = 140000000;

            /*
        
             GET THE FORM ID FIRST
        
             */
            try {
                formID = enrollmentDAO.getFormIDPrivate(Integer.parseInt(year.trim()), "private");
            } catch (SQLException ex) {
                Logger.getLogger(ValiEnrollment.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (classification.equalsIgnoreCase("public")) {
            cFormID = 160000000;

            /*
        
                GET THE FORM ID FIRST
        
             */
            try {
                formID = enrollmentDAO.getFormIDPublic(Integer.parseInt(year.trim()), "public");
            } catch (SQLException ex) {
                Logger.getLogger(ValiEnrollment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /**
         *
         * IF EDITED/UPDATE DELETE THE RECORD AND THE DATA IN TABLE
         *
         * ELSE UPLOAD - UPLOAD THE FILE TO DB
         *
         */

        if (page.equalsIgnoreCase("edited")) {
            try {
                enrollmentDAO.deleteenrollmentDetailsRecord(cFormID + (Integer.parseInt(year)));
                enrollmentDAO.deleteenrollmentRecord(cFormID + (Integer.parseInt(year)));
                recordDAO.deleteRecord(cFormID + Integer.parseInt(year));
            } catch (SQLException ex) {
                Logger.getLogger(ValiEnrollment.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (page.equalsIgnoreCase("upload")) {
           
            boolean reupload = false;
            try {
                reupload = recordDAO.checkExistRecordForReupload(cFormID, Integer.parseInt(year));

            } catch (SQLException ex) {
                Logger.getLogger(ValiEnrollment.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (reupload == true) {
                try {
                    enrollmentDAO.deleteenrollmentDetailsRecord(cFormID + (Integer.parseInt(year)));
                    enrollmentDAO.deleteenrollmentRecord(cFormID + (Integer.parseInt(year)));
                    recordDAO.deleteRecord(cFormID + Integer.parseInt(year));
                } catch (SQLException ex) {
                    Logger.getLogger(ValiEnrollment.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        if (errorMessage.equalsIgnoreCase("error")) {
            /**
             * RECHECK PARAMETERS FROM ERROR (THIS IS IF THE ERROR TABLE IS
             * EDITED
             */

            ArrayList<Enrollment> enrollmentEditedNoError = new ArrayList<Enrollment>();
            ArrayList<EnrollmentTemp> enrollmentStatusEditedTempError = new ArrayList<EnrollmentTemp>();

            /**
             * TEMPORARY STORAGE FOR THE ERROR ARRAYS
             */
            ArrayList<EnrollmentTemp> arrEnrollmentTemp = new ArrayList<EnrollmentTemp>();
            /**
             * TO SAVE THE TRANSFORMED DATA
             */

            ArrayList<Enrollment> trnasoformedData = new ArrayList<Enrollment>();

           String[] schoolNameError = request.getParameterValues("schoolNameError");
        String[] districtError = request.getParameterValues("districtError");
        String[] schoolTypeError = request.getParameterValues("schoolTypeError");
        String[] totalMaleError = request.getParameterValues("totalMaleError");
        String[] totalFemaleError = request.getParameterValues("totalFemaleError");
        String[] grandTotalError = request.getParameterValues("grandTotalError");
        String[] GenderDisparityIndexError = request.getParameterValues("GenderDisparityIndexError");

        String[] gradeLevelError = request.getParameterValues("gradeLevelError");
        String[] maleCountError = request.getParameterValues("maleCountError");
        String[] femaleCountError = request.getParameterValues("femaleCountError");
        String[] totalCountError = request.getParameterValues("totalCountError");

        EnrollmentTemp enrollmentTemp;
        ArrayList<EnrollmentDetTemp> arrEnrollmentDetTemp;

        boolean x = false;
        int y = 0;
        int stopper = 0;

        for (int i = 0; i < schoolNameError.length; i++) {
            enrollmentTemp = new EnrollmentTemp();
            enrollmentTemp.setFormID(formID);
            enrollmentTemp.setCensusYear(Integer.parseInt(year.trim()));
            enrollmentTemp.setDistrict(districtError[i]);
            enrollmentTemp.setSchoolName(schoolNameError[i]);
            enrollmentTemp.setSchoolType(schoolTypeError[i]);
            enrollmentTemp.setClassification(classification);
            enrollmentTemp.setTotalMale(totalMaleError[i].replaceAll(" ", "").replaceAll(",", ""));
            enrollmentTemp.setTotalFemale(totalFemaleError[i].replaceAll(" ", "").replaceAll(",", ""));
            enrollmentTemp.setGrandTotal(grandTotalError[i].replaceAll(" ", "").replaceAll(",", ""));
            enrollmentTemp.setGenderDisparityIndex(GenderDisparityIndexError[i].replaceAll(" ", "").replaceAll(",", ""));
            arrEnrollmentDetTemp = new ArrayList<EnrollmentDetTemp>();

            for (; y < 8 + stopper; y++) {
                
                EnrollmentDetTemp EnrollmentDetTemp = new EnrollmentDetTemp();
                EnrollmentDetTemp.setGradeLevel(gradeLevelError[y]);
                EnrollmentDetTemp.setFemaleCount(femaleCountError[y].replaceAll(" ", "").replaceAll(",", ""));
                EnrollmentDetTemp.setMaleCount(maleCountError[y].replaceAll(" ", "").replaceAll(",", ""));
                EnrollmentDetTemp.setTotalCount(totalCountError[y].replaceAll(" ", "").replaceAll(",", ""));
                EnrollmentDetTemp.setValidation(true);
                arrEnrollmentDetTemp.add(EnrollmentDetTemp);
            }

            stopper = y;
            enrollmentTemp.setEnrollmentDetArrayList(arrEnrollmentDetTemp);
            arrEnrollmentTemp.add(enrollmentTemp);
        }

            //TEMP GET ERROR THEN FIX VARIABLES TO BE SAVE IN DB / NO ERROR ADD IN ArrByAgeGroupSex
            enrollmentEditedNoError = new EnrollmentChecker(arrEnrollmentTemp, Integer.parseInt(year), formID).getArrayNoError();
            enrollmentStatusEditedTempError = new EnrollmentChecker(arrEnrollmentTemp, Integer.parseInt(year), formID).getArrayError();

            EnrollmentChecker toDb = new EnrollmentChecker();
            trnasoformedData = toDb.transformData(enrollmentStatusEditedTempError);

            ArrEnrollment.addAll(trnasoformedData);
            ArrEnrollment.addAll(enrollmentEditedNoError);
            //END FOR ERROR

        }

        String[] schoolName = request.getParameterValues("schoolName");
        String[] district = request.getParameterValues("district");
        String[] schoolType = request.getParameterValues("schoolType");
        String[] totalMale = request.getParameterValues("totalMale");
        String[] totalFemale = request.getParameterValues("totalFemale");
        String[] grandTotal = request.getParameterValues("grandTotal");
        String[] GenderDisparityIndex = request.getParameterValues("GenderDisparityIndex");

        String[] gradeLevel = request.getParameterValues("gradeLevel");
        String[] maleCount = request.getParameterValues("maleCount");
        String[] femaleCount = request.getParameterValues("femaleCount");
        String[] totalCount = request.getParameterValues("totalCount");

        Enrollment enrollment;
        ArrayList<EnrollmentDet> arrEnrollmentDet;

        boolean x = false;
        int y = 0;
        int stopper = 0;

        for (int i = 0; i < schoolName.length; i++) {
            enrollment = new Enrollment();
            enrollment.setFormID(formID);
            enrollment.setCensusYear(Integer.parseInt(year.trim()));
            enrollment.setDistrict(district[i]);
            enrollment.setSchoolName(schoolName[i]);
            enrollment.setSchoolType(schoolType[i]);
            enrollment.setClassification(classification);
            enrollment.setTotalMale(Integer.parseInt(totalMale[i].replaceAll(" ", "").replaceAll(",", "")));
            enrollment.setTotalFemale(Integer.parseInt(totalFemale[i].replaceAll(" ", "").replaceAll(",", "")));
            enrollment.setGrandTotal(Integer.parseInt(grandTotal[i].replaceAll(" ", "").replaceAll(",", "")));
            enrollment.setGenderDisparityIndex(Double.parseDouble(GenderDisparityIndex[i].replaceAll(" ", "").replaceAll(",", "")));
            arrEnrollmentDet = new ArrayList<EnrollmentDet>();

            for (; y < 8 + stopper; y++) {
                
                EnrollmentDet EnrollmentDet = new EnrollmentDet();
                EnrollmentDet.setGradeLevel(gradeLevel[y]);
                EnrollmentDet.setFemaleCount(Integer.parseInt(femaleCount[y].replaceAll(" ", "").replaceAll(",", "")));
                EnrollmentDet.setMaleCount(Integer.parseInt(maleCount[y].replaceAll(" ", "").replaceAll(",", "")));
                EnrollmentDet.setTotalCount(Integer.parseInt(totalCount[y].replaceAll(" ", "").replaceAll(",", "")));
                EnrollmentDet.setValidation(true);
                arrEnrollmentDet.add(EnrollmentDet);
            }

            stopper = y;
            enrollment.setEnrollmentDetArrayList(arrEnrollmentDet);
            ArrEnrollment.add(enrollment);
        }
        x = recordDAO.newRecord(formID, Integer.parseInt(year.trim()), Integer.parseInt(uploadedBy));

        if (x) {
            x = enrollmentDAO.EncodeEnrollment(ArrEnrollment);

            if (x) {
                try {

                    if (EducationRecordDAO.runEnrollmentDAO(formID, Integer.parseInt(year.trim()))) {
                        request.setAttribute("page", "Upload");
                        request.setAttribute("saveToDB", "successDB");
                        if (classification.equalsIgnoreCase("Private")) {
                            RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataEducationServlet?redirect=ePrivate");
                            rd.forward(request, response);
                        } else {
                            RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataEducationServlet?redirect=ePublic");
                            rd.forward(request, response);
                        }
                    } else if (!EducationRecordDAO.runEnrollmentDAO(formID, Integer.parseInt(year.trim()))) {
                        request.setAttribute("page", "Upload");
                        request.setAttribute("saveToDB", "SaveWithError");
                        if (classification.equalsIgnoreCase("Private")) {
                            RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataEducationServlet?redirect=ePrivate");
                            rd.forward(request, response);
                        } else {
                            RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataEducationServlet?redirect=ePublic");
                            rd.forward(request, response);
                        }
                    } else {
                        request.setAttribute("page", "Upload");
                        request.setAttribute("saveToDB", "Error");
                        if (classification.equalsIgnoreCase("Private")) {
                            RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataEducationServlet?redirect=ePrivate");
                            rd.forward(request, response);
                        } else {
                            RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataEducationServlet?redirect=ePublic");
                            rd.forward(request, response);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ValiEnrollment.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/ErrorHandler");
                request.setAttribute("page", "enrollment");
                request.setAttribute("action", "upload");
                rd.forward(request, response);
            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/ErrorHandler");
            request.setAttribute("page", "enrollment");
            request.setAttribute("action", "upload");
            rd.forward(request, response);
        }
    }
}
