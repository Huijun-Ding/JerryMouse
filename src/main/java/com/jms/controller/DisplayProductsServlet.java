/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.controller;

import com.jms.dao.PreferenceDAO;
import com.jms.dao.ProductDAOH;
import com.jms.model.Client;
import com.jms.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mlk
 */
public class DisplayProductsServlet extends HttpServlet {

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

        // Get the HTTP session
        HttpSession session = request.getSession(false);

        // Get the client in session
        Client client = (Client) session.getAttribute("client");

        // Get the favorite products of the client
        if (client != null) {
            System.out.println("Client = " + client.getCode() + " email = " + client.getEmail());
            Client clientUp = PreferenceDAO.getFavoriteProducts(client);
            System.out.println("Prenom = " + clientUp.getFirstName());
            // Update client in session
            session.setAttribute("client", clientUp);
        }
        
        // All products to display     
        if (request.getParameterMap().containsKey("all")) {
            List<Product> list = ProductDAOH.getAllProducts(); // for now display all products
            request.setAttribute("productsList", list);
            // Dispatch to ProductsList
            request.getRequestDispatcher("ProductsList?all").forward(request, response);
        }
        
        // Products to display on homePage    
        if (request.getParameterMap().containsKey("home")) {
            List<Product> list = ProductDAOH.getProductsWithPromo(); // for now display all products
            request.setAttribute("productsList", list);
            // Dispatch to ProductsList
            request.getRequestDispatcher("ProductsList?home").forward(request, response);
        }

        // Products of a category  
        if (request.getParameterMap().containsKey("cat")) {
            int codeCat = Integer.parseInt(request.getParameter("cat"));
            List<Product> list = ProductDAOH.getProductsOfCategory(codeCat); // for now display all products
            request.setAttribute("productsList", list);
            // Dispatch to ProductsList
            request.getRequestDispatcher("ProductsList").forward(request, response);
        }

        // Products of a department    
        if (request.getParameterMap().containsKey("dpt")) {
            int codeDpt = Integer.parseInt(request.getParameter("dpt"));
            List<Product> list = ProductDAOH.getProductsOfDepartment(codeDpt); // for now display all products
            request.setAttribute("productsList", list);
            // Dispatch to ProductsList
            request.getRequestDispatcher("ProductsList").forward(request, response);
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
