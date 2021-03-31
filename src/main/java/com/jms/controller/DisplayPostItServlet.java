package com.jms.controller;

import com.jms.dao.ShoppingListDAO;
import com.jms.model.PostIt;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisplayPostItServlet extends HttpServlet {

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

            String id = request.getParameter("id");

            try {
                // get all my shopping lists by getMyShoppingLists()
                List<PostIt> lst = ShoppingListDAO.getPostIts(Integer.parseInt(id));

                response.setContentType("application/xml;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                out.println("<?xml version=\"1.0\"?>");
                out.println("<postIts>");
                
                String productName = "";
                String productBrand = "";
                
                for (PostIt p : lst) {
                    
                    if (p.getProduct()==null) {
                        productName = "Libellé de produit inconnu";
                        productBrand = "Marque inconnu";
                    }
                    else{
                        productName = p.getProduct().getName();
                        productBrand = p.getProduct().getBrand();
                    }

                    out.println("<postIt>");
                    out.println("<name><![CDATA[" + p.getWording() + "]]></name>");
                    out.println("<code><![CDATA[" + p.getCode() + "]]></code>");
                    out.println("<pname><![CDATA[" + productName + "]]></pname>");
                    out.println("<pbrand><![CDATA[" + productBrand + "]]></pbrand>");
                    out.println("</postIt>");
                }
                out.println("</postIts>");

            } catch (SQLException ex) {
                out.println("<postIts>Erreur - " + ex.getMessage() + "</postIts>");
            }
        }
    }

    private int toInt(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
