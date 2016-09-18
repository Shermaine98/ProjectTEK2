/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.demo.servlet;

import excel.demo.ExcelByAgeGroup;
import excel.demo.ExcelHighestAttainment;
import excel.demo.ExcelMaritalStatus;
import model.demo.ByAgeGroupSex;
import model.demo.HighestCompleted;
import model.demo.MaritalStatus;
import model.temp.demo.HighestCompletedTemp;
import model.temp.demo.MaritalStatusTemp;
import model.temp.demo.ByAgeGroupTemp;
import servlets.servlet.BaseServlet;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 *
 * @author Atayan
 * @author Roxas
 * @author Sy
 *
 */
@MultipartConfig(maxFileSize = 16177215)
public class UploadToDatabaseDemo extends BaseServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        InputStream inputStream = null;

        Part filePart = request.getPart("file");
        int sheetNumber = -1;

        inputStream = filePart.getInputStream();
        POIFSFileSystem fs = new POIFSFileSystem(inputStream);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        int numberSheet = wb.getNumberOfSheets();
        String uploadFile = request.getParameter("UploadFile");

        for (int i = 0; i < numberSheet; i++) {
            if (uploadFile.equalsIgnoreCase(wb.getSheetName(i).replaceAll(" ", ""))) {
                sheetNumber = i;
            }
        }

        if (sheetNumber != -1) {
            request.setAttribute("SheetName", "True");
            if (sheetNumber > -1 && uploadFile.equalsIgnoreCase("AgeGroup")) {
                ArrayList<ByAgeGroupTemp> arrTempError = new ArrayList<>();
                ArrayList<ByAgeGroupSex> arrTempNoError = new ArrayList<>();

                ExcelByAgeGroup excelByAgeGroup = new ExcelByAgeGroup(wb, sheetNumber);

                arrTempError = excelByAgeGroup.getArrayError();
                arrTempNoError = excelByAgeGroup.getArrayNoError();

                if (arrTempError.size() > 6) {
                    request.setAttribute("saveToDB", "ErrorMore");
                    RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataDemoServlet?redirect=byAgeGroupSex");
                    rd.forward(request, response);
                } else if (!arrTempError.isEmpty()) {
                    request.setAttribute("ErrorMessage", "Error");
                    request.setAttribute("ArrError", arrTempError);
                    request.setAttribute("ArrNoError", arrTempNoError);
                    request.setAttribute("GetYear", "2016");
                    request.setAttribute("page", "Upload");
                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPDemo/valiAgeBySex.jsp");
                    rd.forward(request, response);
                } else {
                    boolean checkGrandTotal = excelByAgeGroup.isCheckGrandTotal();
                    if (!checkGrandTotal) {
                        request.setAttribute("saveToDB", "ErrorGrand");
                        RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataDemoServlet?redirect=byAgeGroupSex");
                        rd.forward(request, response);
                    } else {
                        request.setAttribute("ErrorMessage", "NoError");
                        request.setAttribute("ArrNoError", arrTempNoError);
                        Calendar cal = Calendar.getInstance();
                        request.setAttribute("GetYear", "2016");
                        request.setAttribute("page", "Upload");
                        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPDemo/valiAgeBySex.jsp");
                        rd.forward(request, response);

                    }
                } //MARITAL
            } else if (sheetNumber > -1 && uploadFile.equalsIgnoreCase("MaritalStatus")) {
                ArrayList<MaritalStatusTemp> arrTempError = new ArrayList<>();
                ArrayList<MaritalStatus> arrTempNoError = new ArrayList<>();

                arrTempError = new ExcelMaritalStatus(wb, sheetNumber).getArrayError();
                arrTempNoError = new ExcelMaritalStatus(wb, sheetNumber).getArrayNoError();

                //boolean checkGrandTotal=ExcelMaritalStatus.isCheckGrandTotal();
                if (arrTempError.size() > 6) {
                    request.setAttribute("saveToDB", "ErrorMore");
                    RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataDemoServlet?redirect=MaritalStatus");
                    rd.forward(request, response);
//                } else if (!checkGrandTotal) {
//                    request.setAttribute("saveToDB", "ErrorGrand");
//                    RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataDemoServlet?redirect=MaritalStatus");
//                    rd.forward(request, response);
                } else {
                    if (!arrTempError.isEmpty()) {
                        request.setAttribute("ErrorMessage", "Error");
                        request.setAttribute("ArrError", arrTempError);
                        request.setAttribute("ArrNoError", arrTempNoError);

                    } else {
                        request.setAttribute("ErrorMessage", "NoError");
                        request.setAttribute("ArrNoError", arrTempNoError);

                    }
                    request.setAttribute("page", "Upload");
                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPDemo/valiMaritalStatus.jsp");
                    rd.forward(request, response);
                }
            } //HH POP
            else if (sheetNumber > -1 && uploadFile.equalsIgnoreCase("HHPop.5yrs.")) {

                ArrayList<HighestCompletedTemp> arrTempError = new ArrayList<>();
                ArrayList<HighestCompleted> arrTempNoError = new ArrayList<>();

                ExcelHighestAttainment excel = new ExcelHighestAttainment(wb, sheetNumber);

                arrTempError = excel.getArrayError();
                arrTempNoError = excel.getArrayNoError();
//                boolean checkGrandTotal = excel.isCheckGrandTotal();
               

                if (arrTempError.size() > 6) {
                    request.setAttribute("saveToDB", "ErrorMore");
                    RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataDemoServlet?redirect=HighestCompleted");
                    rd.forward(request, response);
//                } else if (!checkGrandTotal) {
//                    request.setAttribute("saveToDB", "ErrorGrand");
//                    RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataDemoServlet?redirect=MaritalStatus");
//                    rd.forward(request, response);
                } else {
                    if (!arrTempError.isEmpty()) {
                        request.setAttribute("ErrorMessage", "Error");
                        request.setAttribute("ArrError", arrTempError);
                        request.setAttribute("ArrNoError", arrTempNoError);

                    } else {
                        request.setAttribute("ErrorMessage", "NoError");
                        request.setAttribute("ArrNoError", arrTempNoError);

                    }
                    request.setAttribute("page", "Upload");
                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPDemo/valiHighestAttaintment.jsp");
                    rd.forward(request, response);
                }

            }
        } else if (sheetNumber == -1 && uploadFile.equalsIgnoreCase("AgeGroup")){
            request.setAttribute("saveToDB", "UploadError");
            RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataDemoServlet?redirect=byAgeGroupSex");
            rd.forward(request, response);
        } else if (sheetNumber == -1 && uploadFile.equalsIgnoreCase("MaritalStatus")){
            request.setAttribute("saveToDB", "UploadError");
            RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataDemoServlet?redirect=MaritalStatus");
            rd.forward(request, response);
        } else if (sheetNumber == -1 && uploadFile.equalsIgnoreCase("HHPop.5yrs.")){
            request.setAttribute("saveToDB", "UploadError");
            RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataDemoServlet?redirect=HighestCompleted");
            rd.forward(request, response);
        } else {
            ArrayList<String> arrSheet = new ArrayList<>();
            for (int i = 0; i < numberSheet; i++) {
                arrSheet.add(wb.getSheetName(i));
                request.setAttribute("SheetName", "False");
                request.setAttribute("SheetName", arrSheet);
                RequestDispatcher rd = request.getRequestDispatcher("/ErrorHandler");
                rd.forward(request, response);
            }
        }
    }
}
