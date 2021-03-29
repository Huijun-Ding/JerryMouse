/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.controller;

import com.jms.dao.ValiderDAO;
import com.jms.model.Client;
import com.jms.model.Have;
import com.jms.model.Order;
import com.jms.model.Store;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mlk
 */
public class ValidateServlet extends HttpServlet {

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
        
        HttpSession session = request.getSession(true);

        // Get the information of order
        Client client = (Client) session.getAttribute("client");
        Store store = (Store) session.getAttribute("store");
        Have have = (Have) session.getAttribute("have");

        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/xml;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            out.println("<?xml version=\"1.0\"?>");
            out.println("<results>");
            
            if(client != null && store != null && have != null){
            try {
                // vérifier le stock
                // TODO
                
                Order order = ValiderDAO.registerBasket(client, store, have);
                session.setAttribute("order", order);
                out.println("<res><![CDATA[ok]]></res>");
            }catch (ParseException ex) {
                out.println("<res><![CDATA[error]]></res>");
                    out.println("<message><![CDATA[Erreur !]]></message>");
            }       
            }else if(client == null){
                out.println("<res><![CDATA[connection]]></res>");
            }else {
                if(store == null && have == null){
                out.println("<res><![CDATA[store]]></res>");
                out.println("<message><![CDATA[Veuillez choisir votre magasin de retrait !]]></message>");
                }else if(store != null && have == null){
                    out.println("<res><![CDATA[time]]></res>");
                    out.println("<message><![CDATA[Veuillez choisir un cr&eacute;neau de retrait !]]></message>");
                }
            }
            out.println("</results>");
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
