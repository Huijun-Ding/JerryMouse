/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.controller;

import com.jms.dao.BasketDAO;
import com.jms.dao.StockDAO;
import com.jms.model.Client;
import com.jms.model.Store;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jerry Mouse Software.
 */
public class EditQuantityServlet extends HttpServlet {

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
        String ean = request.getParameter("ean");
        String method = request.getParameter("method");

        HttpSession session = request.getSession(false);
        Client client = (Client) session.getAttribute("client");
        Store store = (Store) session.getAttribute("store");

        try ( PrintWriter out = response.getWriter()) {
            response.setContentType("application/xml;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            out.println("<?xml version=\"1.0\"?>");
            out.println("<results>");

            try {
                int qtyProdBefore = BasketDAO.checkQtyProd(client.getCode(), ean);
                int qtyProdAfter = qtyProdBefore;
                if (store != null) {
                    if (method.equals("m")) {
                        if (qtyProdBefore > 0) {
                            BasketDAO.updateBasketMinus(client.getCode(), ean);
                            qtyProdAfter -= 1;
                            out.println("<msg>ok</msg>");
                            out.println("<res>" + qtyProdAfter + "</res>");
                        } else {
                            out.println("<msg>noMinus</msg>");
                            out.println("<res>" + qtyProdAfter + "</res>");
                        }
                    } else if (method.equals("p")) {
                        // check stock de store
                        Boolean stock = StockDAO.checkStockProd(store.getId(), ean, qtyProdBefore + 1);
                        if (stock == true) {
                            // mettre à jour panier si stock suffisant
                            BasketDAO.updateBasket(client.getCode(), ean);
                            qtyProdAfter += 1;
                            out.println("<msg>ok</msg>");
                            out.println("<res>" + qtyProdAfter + "</res>");
                        } else {
                            // stock doesn't enough
                            out.println("<msg>noPlus</msg>");
                            out.println("<res>" + qtyProdAfter + "</res>");
                        }
                    }
                } else {
                    out.println("<msg>store</msg>");
                    out.println("<res>" + qtyProdAfter + "</res>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(EditQuantityServlet.class.getName()).log(Level.SEVERE, null, ex);
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
