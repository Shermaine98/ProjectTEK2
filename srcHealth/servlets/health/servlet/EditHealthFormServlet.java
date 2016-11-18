/*
 *  ProjectTEK - DLSU CCS 2016
 */
package servlets.health.servlet;

import dao.health.NutritionalStatusDAO;
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
import model.health.NutritionalStatus;
import model.temp.health.NutritionalStatusBMITemp;
import model.temp.health.NutritionalStatusTemp;
import servlets.demo.servlet.EditErrorFormServlet;
import servlets.servlet.BaseServlet;

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class EditHealthFormServlet extends BaseServlet {

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
        if (redirect.equalsIgnoreCase("editErrorByNutritional")) {

            try {

                NutritionalStatusDAO nutritionalStatusDAO = new NutritionalStatusDAO();
                ArrayList<NutritionalStatus> arrNutritionalStatus = new ArrayList<>();

                arrNutritionalStatus = nutritionalStatusDAO.ViewNutritionalStatus(parseInt(formID));

                ArrayList<NutritionalStatus> arrNoError = new ArrayList<>();
                ArrayList<NutritionalStatusTemp> arrError = new ArrayList<>();

                for (int i = 0; i < arrNutritionalStatus.size(); i++) {
                     boolean x = false;
                
                     for (int y = 0; y < arrNutritionalStatus.get(i).getNutritionalStatusBMI().size(); y++) {
                    if (arrNutritionalStatus.get(i).getNutritionalStatusBMI().get(y).getValidation()!= 1) {
                        x = true;
                    }
                }
                
                    if (!x && arrNutritionalStatus.get(i).isValidation()== 1 ) {
                        arrNoError.add(arrNutritionalStatus.get(i));
                    } else if(x || arrNutritionalStatus.get(i).isValidation()!= 1) {

                     NutritionalStatusTemp   NutritionalStatusTemp = new NutritionalStatusTemp();
                    NutritionalStatusTemp.setFormID(arrNutritionalStatus.get(i).getFormID());
                    NutritionalStatusTemp.setCensusYear(arrNutritionalStatus.get(i).getCensusYear());
                    NutritionalStatusTemp.setDistrict(arrNutritionalStatus.get(i).getDistrict());
                    NutritionalStatusTemp.setGradeLevel(arrNutritionalStatus.get(i).getGradeLevel());
                    NutritionalStatusTemp.setTotalMale(String.valueOf(arrNutritionalStatus.get(i).getTotalMale()));
                    NutritionalStatusTemp.setTotalFemale(String.valueOf(arrNutritionalStatus.get(i).getTotalFemale()));
                    NutritionalStatusTemp.setTotalCount(String.valueOf(arrNutritionalStatus.get(i).getTotalCount()));
                    NutritionalStatusTemp.setValidation(arrNutritionalStatus.get(i).isValidation());
                     NutritionalStatusTemp.setReason(arrNutritionalStatus.get(i).getReason());
                    NutritionalStatusTemp.setPupilsWeighedFemale(String.valueOf(arrNutritionalStatus.get(i).getPupilsWeighedFemale()));
                    NutritionalStatusTemp.setPupilsWeighedMale(String.valueOf(arrNutritionalStatus.get(i).getPupilsWeighedMale()));
                    NutritionalStatusTemp.setPupilsWeighedTotal(String.valueOf(arrNutritionalStatus.get(i).getPupilsWeighedTotal()));

                   ArrayList<NutritionalStatusBMITemp> arrNutritionalStatusBMI = new ArrayList<>();
                    for (int y = 0; y < arrNutritionalStatus.get(i).getNutritionalStatusBMI().size(); y++) {
                     NutritionalStatusBMITemp NutritionalStatusBMITemp = new NutritionalStatusBMITemp();
                        NutritionalStatusBMITemp.setBMI(arrNutritionalStatus.get(i).getNutritionalStatusBMI().get(y).getBMI());
                        NutritionalStatusBMITemp.setMaleCount(String.valueOf(arrNutritionalStatus.get(i).getNutritionalStatusBMI().get(y).getMaleCount()));
                        NutritionalStatusBMITemp.setFemaleCount(String.valueOf(arrNutritionalStatus.get(i).getNutritionalStatusBMI().get(y).getFemaleCount()));
                        NutritionalStatusBMITemp.setTotalCount(String.valueOf(arrNutritionalStatus.get(i).getNutritionalStatusBMI().get(y).getTotalCount()));
                        NutritionalStatusBMITemp.setValidation(arrNutritionalStatus.get(i).getNutritionalStatusBMI().get(y).getValidation());
                        NutritionalStatusBMITemp.setReason(arrNutritionalStatus.get(i).getNutritionalStatusBMI().get(y).getReason());
                        arrNutritionalStatusBMI.add(NutritionalStatusBMITemp);

                    }
                    NutritionalStatusTemp.setNutritionalStatusTemp(arrNutritionalStatusBMI);
                        arrError.add(NutritionalStatusTemp);

                    }
                }

                request.setAttribute("ErrorMessage", "Error");
                request.setAttribute("page", "edited");
                request.setAttribute("ArrNoError", arrNoError);
                request.setAttribute("ArrError", arrError);
                rd = request.getRequestDispatcher("/WEB-INF/JSPHealth/valiNutritionalStatus.jsp");
                rd.forward(request, response);

            } catch (ParseException ex) {
                Logger.getLogger(EditErrorFormServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } 
    }
}
