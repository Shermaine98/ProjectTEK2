/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.accounts;

import com.google.gson.Gson;
import dao.accounts.Accounts;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlets.servlet.BaseServlet;

/**
 *
 * @author Shermaine
 */
public class CheckUserNameAvailability extends BaseServlet {
/**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean x = false;
        String userName = request.getParameter("userName");
        String availability = "false";
        // DEMO
        Accounts AccountsDAO = new Accounts();

      x = AccountsDAO.userNameAvailability(userName);
        if (x == true) {
            availability = "true";
        } else {
            availability = "false";
        }
        
          String json = new Gson().toJson(availability);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(json);

    }
}
