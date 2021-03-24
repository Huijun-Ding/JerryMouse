package com.jms.controller;

import com.jms.dao.BasketDAO;
import com.jms.model.Basket;
import com.jms.model.BasketId;
import com.jms.model.Product;
import com.jms.model.ProductConditioning;
import com.jms.model.ProductNutriScore;
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
 * @author Jerry Mouse Software.
 */
public class checkBasketServlet extends HttpServlet {

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
//        BasketId bid = new BasketId(1, "2");
//        Basket b1 = new Basket(bid, 3);

        HttpSession session = request.getSession(true);
        int idClient = (int) session.getAttribute("idClient");

        List<Basket> lstBasket = BasketDAO.loadBasket(idClient);

        // chercher ensuite chaque produit par une méthode ????
        Product p = new Product("2", "pomme", "bonne pomme", "bon", "350", true, ProductNutriScore.A, ProductConditioning.LOT, 2.5D);
        
        // get un document DOM - XML
        response.setContentType("application/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        try ( PrintWriter out = response.getWriter()) {
            out.println("<?xml version=\"1.0\"?>");

            // loop for all products
            
            out.println("<produit>");
            out.println("<nuplet id=" + p.getEan() + ">");
            out.println("<photo>" + p.getName() + "</photo>");
            out.println("<nom>" + p.getName() + "</nom>");
            out.println("<prix>" + p.getUnitPrice()+ "</pays>");
            out.println("</nuplet>");
            out.println("</produit>");
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
