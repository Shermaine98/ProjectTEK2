package servlets.demo.servlet;

import dao.demo.MaritalStatusDAO;
import dao.demo.ByAgeGroupSexDAO;
import model.demo.ByAgeGroupSex;
import model.demo.MaritalStatus;
import model.temp.demo.MaritalStatusTemp;
import model.temp.demo.ByAgeGroupTemp;
import servlets.servlet.BaseServlet;
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

/**
 *
 * @author Gian Carlo Roxas
 * @author Shermaine Sy
 * @author Geraldine Atayan
 * 
 */
public class EditErrorFormServlet extends BaseServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String redirect = request.getParameter("page");
        String formID = request.getParameter("formID");
        RequestDispatcher rd = null;
        if (redirect.equalsIgnoreCase("editErrorByAge")) {

            try {

                ByAgeGroupSexDAO byAgeGroupSex = new ByAgeGroupSexDAO();
                ArrayList<ByAgeGroupSex> byAgeGroupDB = new ArrayList<>();

                byAgeGroupDB = byAgeGroupSex.ViewByAgeGroupSexFormID(parseInt(formID));

                ArrayList<ByAgeGroupSex> arrNoError = new ArrayList<ByAgeGroupSex>();
                ArrayList<ByAgeGroupTemp> arrError = new ArrayList<ByAgeGroupTemp>();

                for (int i = 0; i < byAgeGroupDB.size(); i++) {
                    if (byAgeGroupDB.get(i).isValidation()==1) {
                        byAgeGroupDB.get(i).getFormatcount(byAgeGroupDB.get(i).getBothSex());
                        byAgeGroupDB.get(i).getFormatcount(byAgeGroupDB.get(i).getMaleCount());
                        byAgeGroupDB.get(i).getFormatcount(byAgeGroupDB.get(i).getFemaleCount());
                        arrNoError.add(byAgeGroupDB.get(i));
                    } else {
                        ByAgeGroupTemp byAgeGroupTemp = new ByAgeGroupTemp();
                        byAgeGroupTemp.setAgeGroup(byAgeGroupDB.get(i).getAgeGroup());
                        byAgeGroupTemp.setBarangay(byAgeGroupDB.get(i).getBarangay());
                        byAgeGroupTemp.setBothSex(String.valueOf(byAgeGroupDB.get(i).getBothSex()));
                        byAgeGroupTemp.setFemaleCount(String.valueOf(byAgeGroupDB.get(i).getFemaleCount()));
                        byAgeGroupTemp.setFormID(byAgeGroupDB.get(i).getFormID());
                        byAgeGroupTemp.setMaleCount(String.valueOf(byAgeGroupDB.get(i).getMaleCount()));
                        byAgeGroupTemp.setUploadedBy(String.valueOf(byAgeGroupDB.get(i).getUploadedBy()));
                        byAgeGroupTemp.setYear(byAgeGroupDB.get(i).getYear());
                        byAgeGroupTemp.setvalidation(byAgeGroupDB.get(i).isvalidated());
                        byAgeGroupTemp.setErrorReason(byAgeGroupSex.getReason(byAgeGroupDB.get(i).isvalidated()));
                        arrError.add(byAgeGroupTemp);
                    }
                }

                request.setAttribute("ErrorMessage", "Error");
                request.setAttribute("page", "edited");
                request.setAttribute("ArrNoError", arrNoError);
                request.setAttribute("ArrError", arrError);
                rd = request.getRequestDispatcher("/WEB-INF/JSPDemo/valiAgeBySex.jsp");
                rd.forward(request, response);

            } catch (ParseException ex) {
                Logger.getLogger(EditErrorFormServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (redirect.equalsIgnoreCase("editErrorByHighest")) {
            
            
             MaritalStatusDAO MaritalStatusDAO = new MaritalStatusDAO();
                ArrayList<MaritalStatus> MaritalStatus = new ArrayList<>();

            try {
                MaritalStatus = MaritalStatusDAO.ViewMaritalStatusFormID(parseInt(formID));
            } catch (ParseException ex) {
                Logger.getLogger(EditErrorFormServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

                ArrayList<MaritalStatus> arrNoError = new ArrayList<MaritalStatus>();
                ArrayList<MaritalStatusTemp> arrError = new ArrayList<MaritalStatusTemp>();

                for (int i = 0; i < MaritalStatus.size(); i++) {
                    if (MaritalStatus.get(i).getValidation()) {

                        MaritalStatus.get(i).getFormatcount(MaritalStatus.get(i).getCommonLawLiveIn());
                        MaritalStatus.get(i).getFormatcount(MaritalStatus.get(i).getDivorcedSeparated());
                        MaritalStatus.get(i).getFormatcount(MaritalStatus.get(i).getMarried());
                        MaritalStatus.get(i).getFormatcount(MaritalStatus.get(i).getSingle());
                        MaritalStatus.get(i).getFormatcount(MaritalStatus.get(i).getTotal());
                        MaritalStatus.get(i).getFormatcount(MaritalStatus.get(i).getUnknown());
                        MaritalStatus.get(i).getFormatcount(MaritalStatus.get(i).getWidowed());
                        arrNoError.add(MaritalStatus.get(i));
                    } else {
                      //  arrError.add(MaritalStatus.get(i));
                    }
                }
            
            
            request.setAttribute("ErrorMessage", "none");
            rd = request.getRequestDispatcher("/WEB-INF/JSPDemo/valiHighestAttaintment.jsp");
            rd.forward(request, response);

        } else if (redirect.equalsIgnoreCase("editErrorByMarital")) {
            request.setAttribute("ErrorMessage", "none");
            rd = request.getRequestDispatcher("/WEB-INF/JSPDemo/valiMaritalStatus.jsp");
            rd.forward(request, response);
        }
    }
}
