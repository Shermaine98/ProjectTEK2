/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package servlets.servlet;

import dao.RecordDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gian Carlo Roxas
 * @author shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class UploadCheckerDirectory extends BaseServlet {

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
        int year = Calendar.getInstance().get(Calendar.YEAR);
        boolean x = false;
        String decision = "upload";
        // DEMO
        RecordDAO recordDAO = new RecordDAO();

        if (page.equalsIgnoreCase("schoolDirectory")) {

            String classification = request.getParameter("classification");

            if (classification.equalsIgnoreCase("Private")) {
                try {
                    x = recordDAO.checkExistRecordUploadChecker(120000000, year);
                    
                    if (x) {
                        x = recordDAO.checkExistYearApproved(120000000, year);
                        if (x == true) {
                            decision = "approved";
                        } else {
                            decision = "reUpload";
                        }
                    } else {
                        decision = "upload";
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(UploadCheckerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                try {
                    x = recordDAO.checkExistRecordUploadChecker(190000000, year);
                    if (x) {
                        x = recordDAO.checkExistYearApproved(190000000, year);
                        if (x == true) {
                            decision = "approved";
                        } else {
                            decision = "reUpload";
                        }
                    } else {
                        decision = "upload";
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(UploadCheckerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } 
        else if (page.equalsIgnoreCase("hospital")) {
            try {
                //If exsist
                x = recordDAO.checkExistRecordUploadChecker(90000000, year);
                if (x == true) {
                    x = recordDAO.checkExistYearApproved(90000000, year);
                    if (x == true) {
                        decision = "approved";
                    } else {
                        decision = "reUpload";
                    }
                } else {
                    decision = "upload";
                }

            } catch (SQLException ex) {
                getLogger(UploadCheckerServlet.class.getName()).log(SEVERE, null, ex);
            }
        }  else if (page.equalsIgnoreCase("hospital")) {
            try {
                //If exsist
                x = recordDAO.checkExistRecordUploadChecker(90000000, year);
                if (x == true) {
                    x = recordDAO.checkExistYearApproved(90000000, year);
                    if (x == true) {
                        decision = "approved";
                    } else {
                        decision = "reUpload";
                    }
                } else {
                    decision = "upload";
                }

            } catch (SQLException ex) {
                getLogger(UploadCheckerServlet.class.getName()).log(SEVERE, null, ex);
            }
        }
        String json = new Gson().toJson(decision);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(json);

    }

}