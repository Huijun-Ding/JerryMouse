/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.controller;

import com.jms.dao.StoreDAO;
import com.jms.model.Store;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author axelt
 */
public class StoreServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            String postalCode = request.getParameter("postalCode");
            String setStoreId = request.getParameter("setStoreId");

            response.setContentType("application/xml;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            out.println("<?xml version=\"1.0\"?>");
            out.println("<stores>");

            if (postalCode != null && !postalCode.equals("")) {
                for (Store s : StoreDAO.getAll(postalCode)) {
                    out.println("   <store>");
                    out.println("       <id><![CDATA[" + s.getId() + "]]></id>");
                    out.println("       <name><![CDATA[" + s.getName() + "]]></name>");
                    out.println("       <street><![CDATA[" + s.getStreet() + "]]></street>");
                    out.println("       <city><![CDATA[" + s.getCity() + "]]></city>");
                    out.println("       <postalCode><![CDATA[" + s.getPostalCode() + "]]></postalCode>");
                    out.println("   </store>");
                }
            } else if(setStoreId != null && !setStoreId.equals("")) {
                Store s = StoreDAO.get(Integer.parseInt(setStoreId));
                
                out.println("   <store>");
                out.println("       <id><![CDATA[" + s.getId() + "]]></id>");
                out.println("       <name><![CDATA[" + s.getName() + "]]></name>");
                out.println("       <street><![CDATA[" + s.getStreet() + "]]></street>");
                out.println("       <city><![CDATA[" + s.getCity() + "]]></city>");
                out.println("       <postalCode><![CDATA[" + s.getPostalCode() + "]]></postalCode>");
                out.println("   </store>");                
                
                request.getSession().setAttribute("store", s);
            }

            out.println("</stores>");
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
