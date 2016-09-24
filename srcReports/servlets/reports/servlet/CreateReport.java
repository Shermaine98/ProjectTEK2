/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.reports.servlet;

import dao.reports.ReportDAO;
import model.reports.ImageUtils;
import model.reports.Matrix;
import model.accounts.User;
import model.reports.ReportAnalysis;
import servlets.servlet.BaseServlet;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Atayan
 * @author Roxas
 * @author Sy
 *
 */
@MultipartConfig(maxFileSize = 16177215)
public class CreateReport extends BaseServlet {

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
        HttpSession session = request.getSession();
        User chck = (User) session.getAttribute("user");
        int year = Calendar.getInstance().get(Calendar.YEAR);
        RequestDispatcher rd = null;

        String isDraft = request.getParameter("isDraft");
        String reportType = request.getParameter("reportType");
        ReportDAO ReportDAO = new ReportDAO();
         String relativeWebPath = "/chartImages/";
        String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
        String loc = absoluteDiskPath.replace("build\\web\\chartImages\\", "web\\chartImages\\");
                
       
        //IV - Health
//III - Education
//I - Demo        

        String sector = null;
        if (chck.getPosition().equals("Project Development Officer IV")) {
            sector = "Health";
        } else if (chck.getPosition().equals("Project Development Officer III")) {
            sector = "Education";
        } else if (chck.getPosition().equals("Project Development Officer I")) {
            sector = "Demographics";
        }

        boolean x = false;

        // IF ANALYSIS  
        if (reportType.equalsIgnoreCase("analysis")) {

            ArrayList<ReportAnalysis> arrReportAnalysis = new ArrayList<ReportAnalysis>();
            String minedata[] = request.getParameterValues("imageSrc");
            String findings[] = request.getParameterValues("findings");

            int chartNumber = 0;
            try {
                chartNumber = ReportDAO.getChartReportAnalysisName();
            } catch (SQLException ex) {
                Logger.getLogger(CreateReport.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (int i = 0; i < minedata.length; i++) {
                
                ReportAnalysis reportAnalysis = new ReportAnalysis();
              BufferedImage  newImg = ImageUtils.decodeToImage(minedata[i].replace("data:image/png;base64,", ""));
                String name = chartNumber + ".png";
                File Save = new File(loc, name);
                ImageIO.write(newImg, "png", Save);
                reportAnalysis.setYear(year);
                reportAnalysis.setChartName(name);
                reportAnalysis.setText(findings[i]);
                reportAnalysis.setCreatedBy(chck.getUserID());
                reportAnalysis.setSector(sector);
                reportAnalysis.setIsDraft(Boolean.parseBoolean(isDraft));
                arrReportAnalysis.add(reportAnalysis);
                chartNumber++;
            }

            x = ReportDAO.newReportAnalysis(arrReportAnalysis);

        } else if (reportType.equalsIgnoreCase("matrix")) {
            //IF MATRIX

            ArrayList<Matrix> arrMatrix = new ArrayList<Matrix>();

            String minedata[] = request.getParameterValues("imageSrc");
            String observations[] = request.getParameterValues("observations");
            String Implications[] = request.getParameterValues("Implications");
            String Explanations[] = request.getParameterValues("Explanations");
            String Interventions[] = request.getParameterValues("Interventions");

            int chartNumber = 0;
            try {
                chartNumber = ReportDAO.getChartMatrixName();
            } catch (SQLException ex) {
                Logger.getLogger(CreateReport.class.getName()).log(Level.SEVERE, null, ex);
            }
             
            for (int i = 0; i < minedata.length; i++) {
                Matrix Matrix = new Matrix();

              
              BufferedImage  newImg = ImageUtils.decodeToImage(minedata[i].replace("data:image/png;base64,", ""));
                String name = chartNumber + ".png";
                File Save = new File(loc, name);
                ImageIO.write(newImg, "png", Save);
               
                
                Matrix.setChartName(name);
                Matrix.setYear(year);
                Matrix.setExplanations(Explanations[i]);
                Matrix.setObservations(observations[i]);
                Matrix.setImplications(Implications[i]);
                Matrix.setInternventions(Interventions[i]);
                Matrix.setIsDraft(Boolean.parseBoolean(isDraft));
                Matrix.setCreatedBy(chck.getUserID());
                Matrix.setSector(sector);
                arrMatrix.add(Matrix);
                chartNumber++;
                
            }
            x = ReportDAO.newReportMatrix(arrMatrix);

        }
        if (x) {
            request.setAttribute("savedMessage", "Saved");
            rd = request.getRequestDispatcher("/ReportAccess?redirect=Saved&savedMessage=success");
            rd.forward(request, response);
        } else {
            request.setAttribute("savedMessage", "Error");
            rd = request.getRequestDispatcher("/ReportAccess?redirect=Saved&savedMessage=Error");
            rd.forward(request, response);
        }
    }
}
