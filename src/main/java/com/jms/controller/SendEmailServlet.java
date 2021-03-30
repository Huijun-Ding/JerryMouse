/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.controller;

import com.jms.dao.HibernateUtilDAO;
import com.jms.model.Client;
import com.jms.model.Order;
import com.jms.model.OrderLine;
import com.jms.model.OrderLineId;
import com.jms.model.Product;
import com.jms.model.ProductConditioning;
import com.jms.model.ProductNutriScore;
import com.jms.model.Store;
import com.jms.model.TimeSlot;
import com.jms.util.EmailUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        //HttpSession session = request.getSession(true);
        // Client client = (Client) session.getAttribute("client");
        Client client = new Client(2, "REN", "Chloé", "18810952622@163.com", "123", 10);
        String receiveMail = client.getEmail();
        // get order
        try ( Session sessionHiber = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = sessionHiber.beginTransaction();
            Order order = sessionHiber.load(Order.class, client.getCode());
            t.commit();
        }

        Date d1 = new Date(2021, 4, 2, 7, 30);
        Date d2 = new Date(2021, 4, 2, 8, 0);
        Store s1 = new Store("Auhchan", "rue du 12", "toulouse", "31000");
        TimeSlot t1 = new TimeSlot(d1, d2);
        Product p1 = new Product("p1", "eau", 1.4f,"https://www.carrefour.fr/media/1500x1500/Photosite/PGC/BOISSONS/3057640257858_PHOTOSITE_20201022_061508_0.jpg?placeholder=1");
        Product p2 = new Product("p2", "chocolat", 1.4f,"https://www.carrefour.fr/media/1500x1500/Photosite/PGC/EPICERIE/8000500003787_PHOTOSITE_20210317_165358_0.jpg?placeholder=1");
        Order o1 = new Order("order1", client, s1, t1);
        OrderLine ol1 = new OrderLine(2, o1, p1);
        OrderLine ol2 = new OrderLine(1, o1, p2);
        ArrayList<OrderLine> orderlines = new ArrayList<OrderLine>();
        orderlines.add(ol1);
        orderlines.add(ol2);
        DateFormat format1 = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
        int total = 12;
        String contentOrder = "<table style='width:60%' frame='above'><caption>Votre Facture"
                + "</caption><thead><tr><th>Image</th><th>Produit</th><th>Quantité</th><th>Montant</th></tr></thead>";
        for (OrderLine ol : orderlines) {

            contentOrder += "<tr><td align='center'><img src='"+ ol.getProduct().getUrlThumbnail() + "'  width='100' height='100'></td><td align='center'>" + ol.getProduct().getName() + "</td><td align='center'>"
                    + ol.getQuantity() + "</td><td align='center'>" + ol.getProduct().getUnitPrice() * ol.getQuantity() + " &#8364;</td></tr>";
        }
        contentOrder = contentOrder + "</table>";
        contentOrder = contentOrder + "<table style='width:60%' frame='below'> <tr><td align='left' > <b>Prix Total : </b> </td><td align='right' > <b>" + total + " &#8364; </b></td></tr></table>";
        String content = "<h4>Bonjour " + client.getFirstName() + "</h4></br>"
                + "</br>Veuillez trouver ci-dessous un resumé des articles "
                + "que vous avez commandés. Cet email vaut comme facture pour votre achat.</br> "
                + contentOrder + "</br> Adresse de Retrait : " + o1.getStore().getName()
                + o1.getStore().getStreet() + ", " + o1.getStore().getPostalCode()
                + "<br>Date de Retrait : "
                + format1.format(o1.getTimeslot().getStartTime()) + " - " + format1.format(o1.getTimeslot().getEndTime());
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
