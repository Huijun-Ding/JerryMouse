package com.jms.controller;

import com.jms.dao.BasketDAO;
import com.jms.model.Client;
import com.jms.model.Product;
import java.io.IOException;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jerry Mouse Software.
 */
public class AddProductServlet extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        Client client = (Client)session.getAttribute("client");
        
//        ArrayList<Product> list = (ArrayList<Product>) request.getAttribute("productsList");
//        request.setAttribute("productsList", list);
        
        int idClient = client.getCode();
//        int idClient = Integer.parseInt(request.getParameter("idClient"));
//        int idClient = 1;
        String ean = request.getParameter("ean");
        
        try {
            // check nb products in basket
            int nb = BasketDAO.calculNbProduct(idClient);
//            session.setAttribute("nbProducts", nb);
            
            out.println("<?xml version=\"1.0\"?>");
            response.setContentType("application/xml;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            out.println("<nbProducts>" + nb + "</nbProducts>");
            
            // add product in basket
            if (BasketDAO.checkProductBakset(idClient, ean)){
                BasketDAO.updateBasket(idClient, ean);
            }else{
                BasketDAO.addProductToBasket(idClient, ean);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
        request.getRequestDispatcher("DisplayProducts?home").forward(request, response);
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
