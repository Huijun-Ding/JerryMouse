package com.jms.controller;

import com.jms.dao.ProductDAO;
import com.jms.dao.ProductDAOH;
import com.jms.dao.ShoppingListDAO;
import com.jms.model.Client;
import com.jms.model.Product;
import com.jms.model.ShoppingList;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DisplayAllShoppingListsServlet extends HttpServlet {

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

        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();

            // Get the client id
            if (session.getAttribute("client") != null) {
                Client client = (Client) session.getAttribute("client");
                int idClient = client.getCode();

                try {
                    // get all my shopping lists by getMyShoppingLists()
                    List<ShoppingList> lst = ShoppingListDAO.getMyShoppingLists(idClient);
                    
                    response.setContentType("application/xml;charset=UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    out.println("<?xml version=\"1.0\"?>");
                    out.println("<shoppingLists>");
                    for (ShoppingList l : lst) {
                        out.println("<shoppingList>");
                        out.println("<code><![CDATA[" + l.getCode() + "]]></code>");
                        out.println("<name><![CDATA[" + l.getName() + "]]></name>");
                        out.println("</shoppingList>");
                    }
                    out.println("</shoppingLists>");

                } catch (SQLException ex) {
                    out.println("<shoppingLists>Erreur - " + ex.getMessage() + "</shoppingLists>");
                }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DisplayAllShoppingListsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DisplayAllShoppingListsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
