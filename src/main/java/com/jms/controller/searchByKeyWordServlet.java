/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.controller;

import com.jms.dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class searchByKeyWordServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            // XML page
            out.println("<?xml version=\"1.0\"?>");
            out.println("<list_products>");

            // get parameter
            String product = request.getParameter("product");
            System.out.println("product");
            if (!product.equals("")) {
                try {
                    // put result into a list
                    ArrayList<String> categories = ProductDAO.completeSearchBarByCategory(product);

                    for (String c : categories) {
                        out.println("<product><![CDATA[" + c + "]]></product>");
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    out.println("<product>Erreur - " + ex.getMessage() + "</product>");
                }

                try {
                    // put result into a list
                    ArrayList<String> products = ProductDAO.completeSearchBarByProductName(product);

                    for (String p : products) {
                        out.println("<product><![CDATA[" + p + "]]></product>");
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    out.println("<product>Erreur - " + ex.getMessage() + "</product>");
                }
            } else {
                out.println("<product><![CDATA[]]></product>");
            }
            out.println("</list_products>");
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
