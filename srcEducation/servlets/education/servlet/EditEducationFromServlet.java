/*
 *  ProjectTEK - DLSU CCS 2016
 */
package servlets.education.servlet;

import dao.education.EnrollmentDAO;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.education.Enrollment;
import model.temp.education.EnrollmentDetTemp;
import model.temp.education.EnrollmentTemp;
import servlets.demo.servlet.EditErrorFormServlet;
import servlets.servlet.BaseServlet;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 *
 */
public class EditEducationFromServlet extends BaseServlet {

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String redirect = request.getParameter("page");
        String formID = request.getParameter("formID");
        RequestDispatcher rd = null;
        if (redirect.equalsIgnoreCase("editErrorByPrivate")) {

            try {

                EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
                ArrayList<Enrollment> Arrenrollment = new ArrayList<>();

                Arrenrollment = enrollmentDAO.ViewEnrollmentFormID(parseInt(formID), "private");

                ArrayList<Enrollment> arrNoError = new ArrayList<Enrollment>();
                ArrayList<EnrollmentTemp> arrError = new ArrayList<EnrollmentTemp>();

                for (int i = 0; i < Arrenrollment.size(); i++) {
                     boolean x = false;
                
                     for (int y = 0; y < Arrenrollment.get(i).getEnrollmentDetArrayList().size(); y++) {
                    if (Arrenrollment.get(i).getEnrollmentDetArrayList().get(y).isValidation() != 1) {
                        x = true;
                    }
                }
                
                    if (!x && Arrenrollment.get(i).getValidation() == 1 ) {
                        arrNoError.add(Arrenrollment.get(i));
                    } else if(x || Arrenrollment.get(i).getValidation() != 1) {

                        EnrollmentTemp EnrollmentTemp = new EnrollmentTemp();

                        // set stuff
                        EnrollmentTemp.setFormID(Arrenrollment.get(i).getFormID());
                        EnrollmentTemp.setCensusYear(Arrenrollment.get(i).getCensusYear());
                        //     enrollment.setSchoolID(Integer.parseInt(arrError.get(i).getSchoolID()));
                        EnrollmentTemp.setSchoolName(Arrenrollment.get(i).getSchoolName());
                        EnrollmentTemp.setDistrict(Arrenrollment.get(i).getDistrict());
                        EnrollmentTemp.setSchoolType(Arrenrollment.get(i).getSchoolType());

                        //END SET OTHER STUFF
                        //
                        EnrollmentTemp.setTotalFemale(String.valueOf(Arrenrollment.get(i).getTotalFemale()));
                        EnrollmentTemp.setTotalMale(String.valueOf(Arrenrollment.get(i).getTotalMale()));
                        EnrollmentTemp.setGrandTotal(String.valueOf(Arrenrollment.get(i).getGrandTotal()));
                        EnrollmentTemp.setGenderDisparityIndex(String.valueOf(Arrenrollment.get(i).getGenderDisparityIndex()));
                        EnrollmentTemp.setValidation(Arrenrollment.get(i).getValidation());
                        EnrollmentTemp.setReason(Arrenrollment.get(i).getReason());
                        ArrayList<EnrollmentDetTemp> arrEnrEnrollmentTem = new ArrayList<EnrollmentDetTemp>();

                        for (int y = 0; y < Arrenrollment.get(i).getEnrollmentDetArrayList().size(); y++) {
                            EnrollmentDetTemp EnrollmentDetTemp = new EnrollmentDetTemp();
                            EnrollmentDetTemp.setMaleCount(String.valueOf(Arrenrollment.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                            EnrollmentDetTemp.setFemaleCount(String.valueOf(Arrenrollment.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                            EnrollmentDetTemp.setTotalCount(String.valueOf(Arrenrollment.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                            EnrollmentDetTemp.setGradeLevel(Arrenrollment.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                            EnrollmentDetTemp.setValidation(Arrenrollment.get(i).getEnrollmentDetArrayList().get(y).isValidation());
                            EnrollmentDetTemp.setReason(Arrenrollment.get(i).getEnrollmentDetArrayList().get(y).getReason());
                            arrEnrEnrollmentTem.add(EnrollmentDetTemp);
                        }
                        EnrollmentTemp.setEnrollmentDetArrayList(arrEnrEnrollmentTem);
                        arrError.add(EnrollmentTemp);

                    }
                }

                request.setAttribute("ErrorMessage", "Error");
                request.setAttribute("page", "edited");
                request.setAttribute("classification", "Private");
                request.setAttribute("ArrNoError", arrNoError);
                request.setAttribute("ArrError", arrError);
                rd = request.getRequestDispatcher("/WEB-INF/JSPEducation/valiEnrollment.jsp");
                rd.forward(request, response);

            } catch (ParseException ex) {
                Logger.getLogger(EditErrorFormServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (redirect.equalsIgnoreCase("editErrorByPublic")) {

            try {

                EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
                ArrayList<Enrollment> Arrenrollment = new ArrayList<>();

                Arrenrollment = enrollmentDAO.ViewEnrollmentFormID(parseInt(formID), "public");

                ArrayList<Enrollment> arrNoError = new ArrayList<Enrollment>();
                ArrayList<EnrollmentTemp> arrError = new ArrayList<EnrollmentTemp>();

                for (int i = 0; i < Arrenrollment.size(); i++) {
                     boolean x = false;
                
                     for (int y = 0; y < Arrenrollment.get(i).getEnrollmentDetArrayList().size(); y++) {
                    if (Arrenrollment.get(i).getEnrollmentDetArrayList().get(y).isValidation() != 1) {
                        x = true;
                    }
                }
                
                    if (!x && Arrenrollment.get(i).getValidation() == 1 ) {
                        arrNoError.add(Arrenrollment.get(i));
                    } else if(x || Arrenrollment.get(i).getValidation() != 1) {

                        EnrollmentTemp EnrollmentTemp = new EnrollmentTemp();

                        // set stuff
                        EnrollmentTemp.setFormID(Arrenrollment.get(i).getFormID());
                        EnrollmentTemp.setCensusYear(Arrenrollment.get(i).getCensusYear());
                        //     enrollment.setSchoolID(Integer.parseInt(arrError.get(i).getSchoolID()));
                        EnrollmentTemp.setSchoolName(Arrenrollment.get(i).getSchoolName());
                        EnrollmentTemp.setDistrict(Arrenrollment.get(i).getDistrict());
                        EnrollmentTemp.setSchoolType(Arrenrollment.get(i).getSchoolType());

                        //END SET OTHER STUFF
                        //
                        EnrollmentTemp.setTotalFemale(String.valueOf(Arrenrollment.get(i).getTotalFemale()));
                        EnrollmentTemp.setTotalMale(String.valueOf(Arrenrollment.get(i).getTotalMale()));
                        EnrollmentTemp.setGrandTotal(String.valueOf(Arrenrollment.get(i).getGrandTotal()));
                        EnrollmentTemp.setGenderDisparityIndex(String.valueOf(Arrenrollment.get(i).getGenderDisparityIndex()));
                        EnrollmentTemp.setValidation(Arrenrollment.get(i).getValidation());
                        EnrollmentTemp.setReason(Arrenrollment.get(i).getReason());
                        ArrayList<EnrollmentDetTemp> arrEnrEnrollmentTem = new ArrayList<EnrollmentDetTemp>();

                        for (int y = 0; y < Arrenrollment.get(i).getEnrollmentDetArrayList().size(); y++) {
                            EnrollmentDetTemp EnrollmentDetTemp = new EnrollmentDetTemp();
                            EnrollmentDetTemp.setMaleCount(String.valueOf(Arrenrollment.get(i).getEnrollmentDetArrayList().get(y).getMaleCount()));
                            EnrollmentDetTemp.setFemaleCount(String.valueOf(Arrenrollment.get(i).getEnrollmentDetArrayList().get(y).getFemaleCount()));
                            EnrollmentDetTemp.setTotalCount(String.valueOf(Arrenrollment.get(i).getEnrollmentDetArrayList().get(y).getTotalCount()));
                            EnrollmentDetTemp.setGradeLevel(Arrenrollment.get(i).getEnrollmentDetArrayList().get(y).getGradeLevel());

                            EnrollmentDetTemp.setValidation(Arrenrollment.get(i).getEnrollmentDetArrayList().get(y).isValidation());
                            EnrollmentDetTemp.setReason(Arrenrollment.get(i).getEnrollmentDetArrayList().get(y).getReason());
                            arrEnrEnrollmentTem.add(EnrollmentDetTemp);
                        }
                        EnrollmentTemp.setEnrollmentDetArrayList(arrEnrEnrollmentTem);
                        arrError.add(EnrollmentTemp);

                    }
                }

                request.setAttribute("ErrorMessage", "Error");
                request.setAttribute("page", "edited");
                request.setAttribute("classification", "Private");
                request.setAttribute("ArrNoError", arrNoError);
                request.setAttribute("ArrError", arrError);
                rd = request.getRequestDispatcher("/WEB-INF/JSPEducation/valiEnrollment.jsp");
                rd.forward(request, response);

            } catch (ParseException ex) {
                Logger.getLogger(EditErrorFormServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
