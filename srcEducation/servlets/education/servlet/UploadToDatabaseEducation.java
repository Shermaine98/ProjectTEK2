/*
 *  Copyright (C) ProjectTEK - DLSU CCS 2016
 *  All right Reserved   * 
 */

package servlets.education.servlet;

import excel.education.ExcelEnrollment;
import model.education.Enrollment;
import model.temp.education.EnrollmentTemp;
import servlets.servlet.BaseServlet;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
@MultipartConfig(maxFileSize = 16177215)
public class UploadToDatabaseEducation extends BaseServlet {

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

        InputStream inputStream = null;

        Part filePart = request.getPart("file");
        int sheetNumber = -1;

        inputStream = filePart.getInputStream();
        POIFSFileSystem fs = new POIFSFileSystem(inputStream);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        int numberSheet = wb.getNumberOfSheets();
        String uploadFile = request.getParameter("UploadFile");
        String classification = request.getParameter("classification");

        for (int i = 0; i < numberSheet; i++) {
            if (wb.getSheetName(i).replaceAll(" ", "").toLowerCase().startsWith(uploadFile.toLowerCase())) {
                sheetNumber = i;
            }
        }
        
        
        if (sheetNumber != -1) {
            request.setAttribute("SheetName", "True");
            if (sheetNumber > -1 && classification.equalsIgnoreCase("ePrivate")) {
                ArrayList<EnrollmentTemp> arrTempError = new ArrayList<>();
                ArrayList<Enrollment> arrTempNoError = new ArrayList<>();

                arrTempError = new ExcelEnrollment(wb, sheetNumber).getArrayError();
                arrTempNoError = new ExcelEnrollment(wb, sheetNumber).getArrayNoError();

   
                if (!arrTempError.isEmpty()) {
                    request.setAttribute("ErrorMessage", "Error");
                    request.setAttribute("ArrError", arrTempError);
                    request.setAttribute("ArrNoError", arrTempNoError);
                } else {
                    request.setAttribute("ErrorMessage", "NoError");
                    request.setAttribute("ArrNoError", arrTempNoError);
                }
                request.setAttribute("classification", "Private");
                request.setAttribute("page", "Upload");
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/WEB-INF/JSPEducation/valiEnrollment.jsp");
                rd.forward(request, response);

            } else if (sheetNumber > -1 && classification.equalsIgnoreCase("ePublic")) {
                ArrayList<EnrollmentTemp> arrTempError = new ArrayList<>();
                ArrayList<Enrollment> arrTempNoError = new ArrayList<>();

                arrTempError = new ExcelEnrollment(wb, sheetNumber).getArrayError();
                arrTempNoError = new ExcelEnrollment(wb, sheetNumber).getArrayNoError();

                if (arrTempError.size() > 6) {
                    request.setAttribute("ErrorMessage", "ErrorMore");
                    request.setAttribute("ArrError", arrTempError);
                    request.setAttribute("ArrNoError", arrTempNoError);
                } else if (!arrTempError.isEmpty()) {
                    request.setAttribute("ErrorMessage", "Error");
                    request.setAttribute("ArrError", arrTempError);
                    request.setAttribute("ArrNoError", arrTempNoError);
                } else {
                    request.setAttribute("ErrorMessage", "NoError");
                    request.setAttribute("ArrNoError", arrTempNoError);
                }

                request.setAttribute("classification", "Public");
                request.setAttribute("page", "Upload");
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/WEB-INF/JSPEducation/valiEnrollment.jsp");
                rd.forward(request, response);

            }
        } else if (sheetNumber == -1 && classification.equalsIgnoreCase("ePublic")){
            request.setAttribute("saveToDB", "UploadError");
            RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataEducationServlet?redirect=ePublic");
            rd.forward(request, response);
            
        } else if (sheetNumber == -1 && classification.equalsIgnoreCase("ePrivate")){
            request.setAttribute("saveToDB", "UploadError");
            RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataEducationServlet?redirect=ePrivate");
            rd.forward(request, response);
            
        } else {
            
            RequestDispatcher rd = request.getRequestDispatcher("/ErrorHandler");
            rd.forward(request, response);

        }

    }
}
