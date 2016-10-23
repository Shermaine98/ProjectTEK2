/*
 *  Copyright (C) ProjectTEK - DLSU CCS 2016
 *  All right Reserved   * 
 */

package servlet.access.reports;

import dao.reports.ReportDAO;
import model.reports.Integrated;
import model.reports.Matrix;
import model.accounts.User;
import model.reports.ReportAnalysis;
import servlets.servlet.BaseServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */

public class ReportAccess extends BaseServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String redirect = request.getParameter("redirect");
        RequestDispatcher rd = null;
        ReportDAO recordDAO = new ReportDAO();
        HttpSession session = request.getSession();
        User chck = (User) session.getAttribute("user");
        int year = Calendar.getInstance().get(Calendar.YEAR);

//IV - Health
//III - Education
//I - Demo        
//matrix
//education = 70000
//health = 80000
//demo = 90000
//
//analysis 
//education = 40000
//health = 30000
//demo = 50000
//CREATE REPORT....
        String sector = null;

        if (chck.getPosition().equals("Project Development Officer IV")) {
            sector = "Health";
        } else if (chck.getPosition().equals("Project Development Officer III")) {
            sector = "Education";
        } else if (chck.getPosition().equals("Project Development Officer I")) {
            sector = "Demographics";
        }

        if (redirect.equalsIgnoreCase("createReport")) {
            boolean x = false, y = false, z = false;
            try {
                //CHECK IF ALL SUBMITTED
                boolean checkSubmitM = recordDAO.checkIfExistMatrixSubmit(sector, year);
                boolean checkSubmitA = recordDAO.checkIfExistReportAnalysisSubmit(sector, year);
                boolean checkSubmitI = recordDAO.checkIfExistIntegratedSubmit(sector, year);

                //CHECK IF ALL SAVED
                boolean checkSubmitMs = recordDAO.checkIfExistMatrixSaved(sector, year);
                boolean checkSubmitAs = recordDAO.checkIfExistReportAnalysisSaved(sector, year);
                boolean checkSubmitIs = recordDAO.checkIfExistIntegratedSaved(sector, year);

                //CHECK IF ALL CREATED
                boolean checkExistMs = recordDAO.checkIfExistMatrix(sector, year);
                boolean checkExistAs = recordDAO.checkIfExistReportAnalysis(sector, year);
                boolean checkExistIs = recordDAO.checkIfExistIntegrated(sector, year);
                int i = 0;
                if (checkSubmitM && checkSubmitA && checkSubmitI) {
                    request.setAttribute("countAlert", "submitted");
                } else if (checkSubmitMs && checkSubmitAs && checkSubmitIs) {
                    request.setAttribute("countAlert", "draft");
                } else if (checkExistMs && checkExistAs && checkExistIs) {
                    request.setAttribute("countAlert", "either");
                } else {

                    x = recordDAO.checkIfExistMatrix(sector, year);
                    y = recordDAO.checkIfExistReportAnalysis(sector, year);
                    z = recordDAO.checkIfExistIntegrated(sector, year);

                    if (!x) {
                        i++;
                        request.setAttribute("Matrix", "Matrix");
                    } else {
                        request.setAttribute("Matrix", "none");
                    }

                    if (!y) {
                        i++;
                        request.setAttribute("Analysis", "Analysis");
                    } else {
                        request.setAttribute("Analysis", "none");
                    }

                    if (!z) {
                        i++;
                        request.setAttribute("Integrated", "Integrated");
                    } else {
                        request.setAttribute("Integrated", "none");
                    }
                }
                request.setAttribute("counter", i);
                request.setAttribute("sector", sector);
                rd = request.getRequestDispatcher("/WEB-INF/JSPAnalysis/CreateReport.jsp");
                rd.forward(request, response);

            } catch (SQLException ex) {
                Logger.getLogger(ReportAccess.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (redirect.equalsIgnoreCase("addChartReport")) {
            String reportTitle = request.getParameter("type");

            if (reportTitle.contains("Matrix")) {
                request.setAttribute("type", "matrix");
                rd = request.getRequestDispatcher("/WEB-INF/JSPAnalysis/DataManipulation.jsp");
            } else if (reportTitle.contains("Integrated")) {
                request.setAttribute("type", "Integrated");
                rd = request.getRequestDispatcher("/WEB-INF/JSPAnalysis/IntegratedReports.jsp");
            }  else if (reportTitle.contains("Analysis")) {
                request.setAttribute("type", "analysis");
                rd = request.getRequestDispatcher("/WEB-INF/JSPAnalysis/DataManipulation.jsp");
            }else {
                request.setAttribute("type", "none");
                rd = request.getRequestDispatcher("/WEB-INF/JSPAnalysis/DataManipulation.jsp");
            }

            rd.forward(request, response);
        } else if (redirect.equalsIgnoreCase("Saved")) {
             int i = 0;
            try {
                ArrayList<ReportAnalysis> ReportAnalysisSaved = new ArrayList<ReportAnalysis>();
                ArrayList<Matrix> MatrixSaved = new ArrayList<Matrix>();
                Integrated IntegratedSaved = new Integrated();

                //CHECK IF ALL SUBMITTED
                boolean checkSubmitM = recordDAO.checkIfExistMatrixSubmit(sector, year);
                boolean checkSubmitA = recordDAO.checkIfExistReportAnalysisSubmit(sector, year);
                boolean checkSubmitI = recordDAO.checkIfExistIntegratedSubmit(sector, year);

                //CHECK IF ALL SAVED
                boolean checkSubmitMs = recordDAO.checkIfExistMatrixSaved(sector, year);
                boolean checkSubmitAs = recordDAO.checkIfExistReportAnalysisSaved(sector, year);
                boolean checkSubmitIs = recordDAO.checkIfExistIntegratedSaved(sector, year);

                //CHECK IF ALL CREATED
                boolean checkExistMs = recordDAO.checkIfExistMatrix(sector, year);
                boolean checkExistAs = recordDAO.checkIfExistReportAnalysis(sector, year);
                boolean checkExistIs = recordDAO.checkIfExistIntegrated(sector, year);

               

                if (checkSubmitM && checkSubmitA && checkSubmitI) {
                    request.setAttribute("countAlert", "submitted");
                } else if (!checkExistMs && !checkExistAs && !checkExistIs) {
                    request.setAttribute("countAlert", "createReport");
                } else if (checkSubmitMs || checkSubmitAs || checkSubmitIs) {

                    if (checkSubmitMs) {
                        MatrixSaved = recordDAO.GetReportMatrixSectorSaved(sector);
                        for (int z = 0; z < MatrixSaved.size(); z++) {
                            MatrixSaved.get(z).setChartPath("chartImages/" + MatrixSaved.get(z).getChartName());
                        }
                        request.setAttribute("MatrixSaved", MatrixSaved);
                        request.setAttribute("Matrix", "Matrix");
                        i++;
                    } else {
                        request.setAttribute("Matrix", "none");
                    }

                    if (checkSubmitAs) {
                        ReportAnalysisSaved = recordDAO.GetReportAnalysisbySectorSaved(sector);
                        for (int z = 0; z < ReportAnalysisSaved.size(); z++) {
                            ReportAnalysisSaved.get(z).setChartPath("chartImages/" + ReportAnalysisSaved.get(z).getChartName());
                        }
                        request.setAttribute("Analysis", "Analysis");
                        request.setAttribute("reportAnalysis", ReportAnalysisSaved);
                        i++;
                    } else {
                        request.setAttribute("Analysis", "none");
                    }

                    if (checkSubmitIs) {
                        IntegratedSaved = recordDAO.GetReportIntegratedSaved(sector);
                        request.setAttribute("Integrated", "Integrated");
                        request.setAttribute("IntegratedSaved", IntegratedSaved);
                        i++;
                    } else {
                        request.setAttribute("Integrated", "none");
                    }

                } else {
                    request.setAttribute("countAlert", "either");
                }

                String navi = request.getParameter("Navi");
                if (navi != null) {
                    request.setAttribute("savedMessage", "none");
                }else{
                  String message = request.getParameter("savedMessage");
                  request.setAttribute("savedMessage", message);
                }

               
                request.setAttribute("sector", sector);
                request.setAttribute("counter", i);
                rd = request.getRequestDispatcher("/WEB-INF/JSPAnalysis/SavedReports.jsp");
                rd.forward(request, response);
            } catch (ParseException ex) {
                Logger.getLogger(ReportAccess.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ReportAccess.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (redirect.equalsIgnoreCase("submit")) {
            request.setAttribute("savedMessage", "succes");
            rd = request.getRequestDispatcher("/WEB-INF/JSPAnalysis/PublishedReports.jsp");
            rd.forward(request, response);

        }

    }
}
