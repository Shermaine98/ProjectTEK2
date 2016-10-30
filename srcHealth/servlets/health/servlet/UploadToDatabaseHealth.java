/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */


package servlets.health.servlet;

import excel.health.ExcelNutritionalStatus;
import model.health.NutritionalStatus;
import model.temp.health.NutritionalStatusTemp;
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
public class UploadToDatabaseHealth extends BaseServlet {

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
            if (sheetNumber > -1 && uploadFile.equalsIgnoreCase("nutritionalStatus")) {
                ArrayList<NutritionalStatusTemp> arrTempError = new ArrayList<>();
                ArrayList<NutritionalStatus> arrTempNoError = new ArrayList<>();

                arrTempError = new ExcelNutritionalStatus(wb, sheetNumber).getArrayError();
                arrTempNoError = new ExcelNutritionalStatus(wb, sheetNumber).getArrayNoError();

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

                request.setAttribute("page", "Upload");
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/WEB-INF/JSPHealth/valiNutritionalStatus.jsp");
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
        } else if (sheetNumber == -1 && uploadFile.equalsIgnoreCase("nutritionalStatus")){
            request.setAttribute("saveToDB", "UploadError");
            RequestDispatcher rd = request.getRequestDispatcher("/RetrieveDataHealthServlet?redirect=percentageDist");
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
