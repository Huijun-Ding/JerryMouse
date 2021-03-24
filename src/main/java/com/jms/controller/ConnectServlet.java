/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.controller;

import com.jms.dao.ClientDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class ConnectServlet
 *
 * @author Jerry Mouse Software.
 */
public class ConnectServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            //Get parameter method
            String method = request.getParameter("method");
            
            //Get parameter login and password
            String login = request.getParameter("login");
            String pw = request.getParameter("password");
            
            //Call method authenticate to check login and password
            boolean check = ClientDAO.authenticate(login, pw);
            
            
            switch (method) {
                /*
                if method = connection, check the login and password and get the connection
                    if check is true
                */
                case "connection":
                    if (check == true) {
                        //chain to index page
                        request.getRequestDispatcher("index").forward(request, response);
                    } else {
                        //chain to page login and display a message error
                        request.getRequestDispatcher("login").forward(request, response);
                        request.setAttribute("msg_error", "Le login ou le mot de passe est incorrect!");
                    }
                    
                    break;
                /*
                if method = return, chain to page index.jsp
                */
                case "return":
                    //chain to page index page
                    request.getRequestDispatcher("index").forward(request, response);
                    
                    break;
            }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
