/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.controller;

import com.jms.dao.BasketDAO;
import com.jms.dao.ClientDAO;
import com.jms.dao.HaveDAO;
import com.jms.dao.StockDAO;
import com.jms.dao.StoreDAO;
import com.jms.dao.ValiderDAO;
import com.jms.model.Basket;
import com.jms.model.Client;
import com.jms.model.Have;
import com.jms.model.Order;
import com.jms.model.Store;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        HttpSession session = request.getSession(true);
        // Get the information of order
        String idClient = request.getParameter("idClient");
        int idStore = Integer.parseInt(request.getParameter("idStore"));
        String startTime = request.getParameter("startTime");
        String date = request.getParameter("date");
        
        Client client = ClientDAO.searchClient(Integer.parseInt(idClient));
        Store store = (idStore == 0) ? null : StoreDAO.get(idStore);
        Have have = HaveDAO.getHave(idStore, date, startTime);
        
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/xml;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            out.println("<?xml version=\"1.0\"?>");
            out.println("<results>");
            
            if(client != null && store != null && have != null){  
                ArrayList<Boolean> lstRes = new ArrayList<>();
                // retrouver le panier et des produits de client
                try {
                    List<Basket> lstBasket = BasketDAO.loadBasket(Integer.parseInt(idClient));
                    for(Basket basket : lstBasket){
                        // vérifier le stock pour un produit
                        out.println("<product>");
                        out.println("<ean><![CDATA[" + basket.getBasketId().getEan() + "]]></ean>");
                        Boolean res = StockDAO.checkStockProd(idStore, 
                                basket.getBasketId().getEan(), basket.getQtyBasket());
                        lstRes.add(res);

                        if(res) out.println("<qte><![CDATA[ok]]></qte>");
                        else out.println("<qte><![CDATA[Rupture de stock !]]></qte>");
                        out.println("</product>");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ValidateServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                // vérifier si tous les prods sont en stock
                System.out.println(lstRes);
                Boolean resFinal = true;
                if(lstRes.contains(false)) resFinal = false;

                // si tous les prods sont en stock
                if(resFinal == true){
                    // enregistrer la cmd si client a choisi un mag et un creneau
                    try {
                        Order order = ValiderDAO.registerBasket(client, store, have);
                        session.setAttribute("order", order);
                        out.println("<res><![CDATA[ok]]></res>");
                    }catch (ParseException ex) {
                        out.println("<res><![CDATA[error]]></res>");
                        out.println("<message><![CDATA[Erreur !]]></message>");
                    }       
                }else{
                    out.println("<res><![CDATA[stock]]></res>");
                    out.println("<message><![CDATA[Rupture de stock !]]></message>");
                }
            }else if(client == null){
                // client ne se connecte pas
                out.println("<res><![CDATA[connection]]></res>");
            }else {
                if(store == null && have == null){
                    // client n'a pas choisi un mag
                    out.println("<res><![CDATA[store]]></res>");
                    out.println("<message><![CDATA[Veuillez choisir votre magasin de retrait !]]></message>");
                }else if(store != null && have == null){
                    // client a choisi un mag mas pas un créneau
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
