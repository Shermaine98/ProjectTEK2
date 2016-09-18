/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.servlet;

import DAO.Accounts;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Geraldine Atayan
 */
public class Register extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {

            User newUser = new User();
            Accounts dao = new Accounts();

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String gender = request.getParameter("gender");
            String birthdate = request.getParameter("birthdate");
            String division = request.getParameter("division");
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            int userID = dao.getLastUserID();

            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setGender(gender);
            newUser.setBirthdate(birthdate);
            newUser.setDivision(division);
            newUser.setEmail(email);
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setUserID(userID);

            if (division.equals("others")) {
                newUser.setDivision("Others");
                newUser.setPosition("External Researchers");
                String reason = request.getParameter("reason");
                newUser.setReason(reason);
                if (dao.registerOthers(newUser)) {
                    ServletContext context = getServletContext();
                    RequestDispatcher rd = context.getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
                } else {
                    out.print("Something went wrong");
                    ServletContext context = getServletContext();
                    RequestDispatcher rd = context.getRequestDispatcher("/register.jsp");
                    rd.forward(request, response);
                }
            } else if (division.equals("employee")) {
                newUser.setDivision("Employee");
                if (dao.registerMembers(newUser)) {
                    ServletContext context = getServletContext();
                    RequestDispatcher rd = context.getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
                } else {
                    out.print("Something went wrong");
                    ServletContext context = getServletContext();
                    RequestDispatcher rd = context.getRequestDispatcher("/register.jsp");
                    rd.forward(request, response);
                }
            }
        } catch (ParseException ex) {
            getLogger(Register.class.getName()).log(SEVERE, null, ex);
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
            getLogger(Register.class.getName()).log(SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
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
            getLogger(Register.class.getName()).log(SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
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
