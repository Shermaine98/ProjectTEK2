/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author Shermaine
 */
public class UploadCheckerServlet extends BaseServlet {

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
        int year = Calendar.getInstance().get(Calendar.YEAR);
        boolean x = false;
        String decision = "upload";
        // DEMO
        RecordDAO recordDAO = new RecordDAO();

        if (page.equalsIgnoreCase("AgeGroup")) {
            try {
                //If exsist
                x = recordDAO.checkExistRecordUploadChecker(200000000, year);
                if (x == true) {
                    x = recordDAO.checkExistYearApproved(200000000,year);
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
        } else if (page.equalsIgnoreCase("MaritalStatus")) {
            try {
                //If exsist
                x = recordDAO.checkExistRecordUploadChecker(300000000, year);
                if (x == true) {
                    x = recordDAO.checkExistYearApproved(300000000, year);
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
        } else if (page.equalsIgnoreCase("HHPop.5yrs.")) {
            try {
                //If exsist
                x = recordDAO.checkExistRecordUploadChecker(400000000, year);
                if (x == true) {
                    x = recordDAO.checkExistYearApproved(400000000, year);
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
        } //EDUC
        else if (page.equalsIgnoreCase("ELEMEnrollment")) {

            String classification = request.getParameter("classification");

            if (classification.equalsIgnoreCase("ePrivate")) {
                try {
                    x = recordDAO.checkExistRecordUploadChecker(140000000, year);

                    if (x == true) {
                        x = recordDAO.checkExistYearApproved(140000000, year);
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
                    x = recordDAO.checkExistRecordUploadChecker(160000000, year);
                    if (x) {
                        x = recordDAO.checkExistYearApproved(160000000, year);
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

        } //directory
        else if (page.equalsIgnoreCase("schoolDirectory")) {

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

        } //HEALTH
        else if (page.equalsIgnoreCase("nutritionalStatus")) {
            try {
                //If exsist
                x = recordDAO.checkExistRecordUploadChecker(800000000, year);
                if (x == true) {
                    x = recordDAO.checkExistYearApproved(800000000, year);
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
        } //hospital
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
        }
        String json = new Gson().toJson(decision);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(json);

    }

}
