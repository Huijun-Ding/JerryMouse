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
import java.text.NumberFormat;
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
        //Client client = new Client(2, "REN", "Chloé", "18810952622@163.com", "123", 10);    
        String receiveMail = client.getEmail();

        //get the infos of store from sesion
        Store store = (Store) session.getAttribute("store");
        //Store s1 = new Store("Auhchan", "rue du 12", "toulouse", "31000");

        //get pick up time from session
        Have have = (Have) session.getAttribute("have");
        //get pick up dure for the client
        String dateStart = " " + order.getPickupDate() + order.getTimeslot().getStartTime();
        String dateEnd = " " + order.getPickupDate() + order.getTimeslot().getEndTime();

        //TimeSlot t1 = new TimeSlot("21-04-02 07:30:00", "21-04-02 08:00:00");
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
                
                if (promotion != null){
                    // calculate price unitary after promotion and percentage of promotion
                    priceAfterFloat = BasketDAO.calculPriceUnitaryAfterPromo(
                            product.getUnitPrice(), promotion.getPercentage());
                    priceAfterString = String.format("%.2f", priceAfterFloat);
                    promo = String.valueOf(nt.format(promotion.getPercentage()));
                    discountProd = BasketDAO.calculMontReductionProduit(
                            product.getUnitPrice(), promotion.getPercentage());
                }else {
                    priceAfterString = " ";
                    promo = " ";
                }               
                // total price of a product in basket
                float totalPriceProduct = BasketDAO.calculPriceTotalProduct(
                        orderline.getQuantity(), priceAfterFloat);
                prices.add(totalPriceProduct);
            
            // System.out.println("product = " + entry.getKey() + ", orderline = " + entry.getValue());
            contentOrder += "<tr><td align='center'><img src='" + product.getUrlThumbnail() + "'  width='100' height='100'></td><td align='center'>" + product.getName() + "</td><td align='center'>"
                    + orderline.getQuantity() + "</td><td align='center'>" + totalPriceProduct + " &#8364;</td></tr>";

        }

        //Product p1 = new Product("p1", "eau", 1.4f, "https://www.carrefour.fr/media/1500x1500/Photosite/PGC/BOISSONS/3057640257858_PHOTOSITE_20201022_061508_0.jpg?placeholder=1");
        //Product p2 = new Product("p2", "chocolat", 1.4f, "https://www.carrefour.fr/media/1500x1500/Photosite/PGC/EPICERIE/8000500003787_PHOTOSITE_20210317_165358_0.jpg?placeholder=1");
        //Order o1 = new Order(123, client, s1, t1);
        //OrderLine ol1 = new OrderLine(2, o1, p1);
        //OrderLine ol2 = new OrderLine(1, o1, p2);
        //ArrayList<OrderLine> orderlines = new ArrayList<OrderLine>();
        //orderlines.add(ol1);
        //orderlines.add(ol2);
        //generate a QR Code for the client 
        String qrcode = "data:image/PNG;base64," + QRcodeUtil.getQRCodeImage("" + order.getClient().getCode() + order.getOrderId());
        //DateFormat format1 = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
        float total = BasketDAO.calculPriceTotal(prices);
        //String contentOrder = "<table style='width:75%' frame='above'><caption>Votre Facture"
        //      + "</caption><thead><tr><th> </th><th>Produit</th><th>Quantité</th><th>Montant</th></tr></thead>";
//        for (OrderLine ol : orderlines) {
//
//            contentOrder += "<tr><td align='center'><img src='" + ol.getProduct().getUrlThumbnail() + "'  width='100' height='100'></td><td align='center'>" + ol.getProduct().getName() + "</td><td align='center'>"
//                    + ol.getQuantity() + "</td><td align='center'>" + ol.getProduct().getUnitPrice() * ol.getQuantity() + " &#8364;</td></tr>";
//        }
        contentOrder = contentOrder + "</table>";
        contentOrder = contentOrder + "<table style='width:75%' frame='below'> <tr><td align='left' > <b>Prix Total : </b> </td><td align='right' > <b>" + total + " &#8364; </b></td></tr></table>";
        String content = "<h4>Bonjour " + client.getFirstName() + "</h4></br>"
                + "</br>Veuillez trouver ci-dessous un resumé des articles "
                + "que vous avez commandés. Cet email vaut comme facture pour votre achat.</br> </br>"
                + contentOrder + "</br> Adresse de Retrait : " + store.getName()
                + store.getStreet() + ", " + store.getPostalCode()
                + "<br>Date de Retrait : de " + dateStart + " à " + dateEnd
                + "<br> Votre QR Code : " + "<img src='" + qrcode + "'  width='100' height='100'>";
        EmailUtil e = new EmailUtil(receiveMail, content, client.getFirstName() + client.getLastName());

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
