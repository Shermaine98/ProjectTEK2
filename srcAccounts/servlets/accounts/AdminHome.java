/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.accounts;

import dao.accounts.Accounts;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.accounts.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import servlet.setdata.SetAnalysisDataServlet;

/**
 *
 * @author ssy
 */
public class AdminHome extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ArrayList<User> users; //arrayList <model>
            Accounts accountsChart = new Accounts(); //DAO
            
            users = accountsChart.getSignUpCounts();
            
            JSONArray jarrayAccounts = new JSONArray();
            JSONObject ObjectAll = new JSONObject();
            
            for(int i = 0; i < users.size(); i++){
                JSONObject objAccounts = new JSONObject();
                try {
                    objAccounts.put("name", users.get(i).getDateCreated());
                    objAccounts.put("y", users.get(i).getCount());
                    System.out.println(users.get(i).getDateCreated() + " " + users.get(i).getCount());
                    jarrayAccounts.put(objAccounts);
                } catch (JSONException ex) {
                    getLogger(SetAnalysisDataServlet.class.getName()).log(SEVERE, null, ex);
                }
            }
           
            
            ObjectAll.put("series", jarrayAccounts);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write("["+ObjectAll.toString()+"]");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
