/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.controller;

import com.jms.dao.BasketDAO;
import com.jms.dao.ProductDAO;
import com.jms.dao.ShoppingListDAO;
import com.jms.model.Client;
import com.jms.model.PostIt;
import com.jms.model.Product;
import com.jms.model.ShoppingList;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mathi
 */
public class transferProductServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession(true);
        Client client = (Client) session.getAttribute("client");
        System.out.println("servlet"+client.getCode());
        int idClient = client.getCode();
        int idList = Integer.parseInt((String) session.getAttribute("idList"));
        ShoppingList sl = ShoppingListDAO.getShoppingList(idList);
        PostIt post;
        try ( PrintWriter out = response.getWriter()) {
            // get un document DOM - XML
            out.println("<?xml version=\"1.0\"?>");
            response.setContentType("application/xml;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            out.println("<info>");
            for (Iterator it = sl.getPostIts().iterator(); it.hasNext();) {
                post = (PostIt) it.next();
                if (post.getProduct() == null) {
                    System.out.println("111111" + post.getWording());
                    ProductDAO.getProductsByName(post.getWording()).get(0);
                    System.out.println("22222222" + ProductDAO.getProductsByName(post.getWording()).get(0));
                    if (ProductDAO.getProductsByName(post.getWording()) == null) {
                        out.println("<message> Non cette genre de produit. </message>");
                    } else {
                        // System.out.println("testetstetsetstets" + ProductDAO.getProductsByName("Chocolat au lait").get(0));
                        System.out.println("3333333");
                        Product p = ProductDAO.getProductByHistory(client, ProductDAO.getProductsByName(post.getWording()));
                        System.out.println("4444444");
                        if (p == null) {
                            System.out.println("55555555");
                            p = ProductDAO.getProductsByName(post.getWording()).get(0);

                        }

                        if (BasketDAO.checkProductBakset(idClient, p.getEan())) {
                            BasketDAO.updateBasket(idClient, p.getEan());
                        } else {
                            BasketDAO.addProductToBasket(idClient, p.getEan());
                        }

                        //return a message
                        out.println("<message> Bien transféré dans le panier. </message>");
                    }
                } else {
                    // add product in basket
                    if (BasketDAO.checkProductBakset(idClient, post.getProduct().getEan())) {
                        BasketDAO.updateBasket(idClient, post.getProduct().getEan());
                    } else {
                        BasketDAO.addProductToBasket(idClient, post.getProduct().getEan());
                    }
                    out.println("<message> Bien transféré dans le panier. </message>");

                }

            }
            out.println("</info>");
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
        } catch (SQLException ex) {
            Logger.getLogger(transferProductServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(transferProductServlet.class.getName()).log(Level.SEVERE, null, ex);
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
