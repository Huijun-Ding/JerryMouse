/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.controller;

import com.jms.dao.BasketDAO;
import com.jms.dao.PromotionDAO;
import com.jms.model.Client;
import com.jms.model.Have;
import com.jms.model.Order;
import com.jms.model.OrderLine;
import com.jms.model.Product;
import com.jms.model.Promotion;
import com.jms.model.Store;
import com.jms.util.EmailUtil;
import com.jms.util.QRcodeUtil;
import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
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
public class SendEmailServlet extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        //get order of client from session
        HttpSession session = request.getSession(true);
        Order order = (Order) session.getAttribute("order");
        //get client from session
        Client client = (Client) session.getAttribute("client");   
        String receiveMail = client.getEmail();

        //get the infos of store from sesion
        Store store = (Store) session.getAttribute("store");

        //get pick up time from session
        Have have = (Have) session.getAttribute("have");
        //get pick up dure for the client
        DateFormat DF = new SimpleDateFormat("dd/MM/yyyy");
        String dateStart = " " + DF.format(order.getPickupDate()) +" "+ order.getTimeslot().getStartTime();
        String dateEnd = " " +  order.getTimeslot().getEndTime();

        //get products and orderlines
        Map<Product, OrderLine> orderlines = order.getProducts();
        
        Iterator<Map.Entry<Product, OrderLine>> entries = orderlines.entrySet().iterator();
        String contentOrder = "<table style='width:75%' frame='above'><caption>Votre Facture"
                + "</caption><thead><tr><th> </th><th>Produit</th><th>Quantité</th><th>Montant</th></tr></thead>";
        OrderLine orderline;
        Product product;
        ArrayList<Float> prices = new ArrayList<>();
        while (entries.hasNext()) {
            
            Map.Entry<Product, OrderLine> entry = entries.next();
            
            orderline = entry.getValue();
            product = entry.getKey();
            // ------ INFO PROMOTION PRODUCT ---------
            Promotion promotion = PromotionDAO.searchPromotion(product.getEan());

            // ------- CALCUL: PRICE, POINTS... -----------
            float priceAfterFloat = product.getUnitPrice();
            String priceAfterString = "";
            String promo = "";
            float discountProd = 0f;

            // convert to percentage
            NumberFormat nt = NumberFormat.getPercentInstance();
            nt.setMinimumFractionDigits(2);
            
            if (promotion != null) {
                // calculate price unitary after promotion and percentage of promotion
                priceAfterFloat = BasketDAO.calculPriceUnitaryAfterPromo(
                        product.getUnitPrice(), promotion.getPercentage());
                priceAfterString = String.format("%.2f", priceAfterFloat);
                promo = String.valueOf(nt.format(promotion.getPercentage()));
                discountProd = BasketDAO.calculMontReductionProduit(
                        product.getUnitPrice(), promotion.getPercentage());
            } else {
                priceAfterString = " ";
                promo = " ";
            }
            // total price of a product in basket
            float totalPriceProduct = BasketDAO.calculPriceTotalProduct(
                    orderline.getQuantity(), priceAfterFloat);
            prices.add(totalPriceProduct);

            contentOrder += "<tr><td align='center'><img src='" + product.getUrlThumbnail() + "'  width='100' height='100'></td><td align='center'>" + product.getName() + "</td><td align='center'>"
                    + orderline.getQuantity() + "</td><td align='center'>" + String.format("%.2f", totalPriceProduct) + " &#8364;</td></tr>";
            
        }

        //generate a QR Code for the client 
        String qrcode = "data:image/PNG;base64," + QRcodeUtil.getQRCodeImage("" + order.getClient().getCode() + order.getOrderId());
        float total = (float)session.getAttribute("total");
        contentOrder = contentOrder + "</table>";
        contentOrder = contentOrder + "<table style='width:75%' frame='below'> <tr><td align='left' > <b>Prix Total : </b> </td><td align='right' > <b>" + String.format("%.2f", total) + " &#8364; </b></td></tr></table>";
        String content = "<h4>Bonjour " + client.getFirstName() + "</h4></br>"
                + "</br>Veuillez trouver ci-dessous un resumé des articles "
                + "que vous avez commandés. Cet email vaut comme facture pour votre achat.</br> </br>"
                + contentOrder + "</br> Adresse de Retrait : " + store.getName()
                + store.getStreet() + ", " + store.getPostalCode()
                + "<br>Date de Retrait : " + dateStart + " - " + dateEnd
                + "<br> Votre QR Code : " + "<img src='" + qrcode + "'  width='100' height='100'>";
        EmailUtil e = new EmailUtil(receiveMail, content, client.getFirstName() + client.getLastName());
        request.getRequestDispatcher("confirmationOrder").forward(request, response);
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
        } catch (Exception ex) {
            Logger.getLogger(SendEmailServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(SendEmailServlet.class.getName()).log(Level.SEVERE, null, ex);
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
