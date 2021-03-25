package com.jms.controller;

import com.jms.dao.BasketDAO;
import com.jms.dao.PromotionDAO;
import com.jms.model.Basket;
import com.jms.model.BasketId;
import com.jms.model.Client;
import com.jms.model.Product;
import com.jms.model.ProductConditioning;
import com.jms.model.ProductNutriScore;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

//        HttpSession session = request.getSession(true);
        String idClient = request.getParameter("idClient");
//        int idClient = 1;

//        List<Basket> lstBasket = BasketDAO.loadBasket(idClient);
        // chercher ensuite chaque produit par une méthode ????
        Product p = new Product("2", "pomme", "bonne pomme", "bon", "350", true, ProductNutriScore.A, ProductConditioning.LOT);
        Client c = new Client("Shangshang", "Zhao", "ss@gmail.com", "ss", 5);
        
//        float percentage = PromotionDAO.getPercentage(p.getEan());
//        double priceAfter = BasketDAO.calculPriceUnitaryAfterPromo(p.getUnitPrice(), percentage);
//        // faut obtenir la quantité de produit dans panier
//        double totalPrice = BasketDAO.calculPriceTotalProduct(1, priceAfter);
//        ArrayList<Double> prices = new ArrayList<>();
//        prices.add(p.getUnitPrice());
//        double total = BasketDAO.calculPriceTotal(prices);
//        
//        // calculer points got
//        double pointsGot = BasketDAO.calculPointsGot(total);
        
        
        
        // get un document DOM - XML
        response.setContentType("application/xml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try ( PrintWriter out = response.getWriter()) {
            // loop for all products
//            int qte = 0;
//            for (Basket basket : lstBasket) {
//                if (basket.getProduct().getEan().equals(p.getEan())) {
//                    qte = basket.getQtyBasket();
//                }
//            }
            out.println("<?xml version=\"1.0\"?>");
            out.println("<pageBasket>");
            out.println("<basket>");

            // loop for all prodcut for(Product p: listProd)
            out.println("<product>");
            out.println("<photo>photo</photo>");
            out.println("<id>" + p.getEan() + "</id>");
            out.println("<name>" + p.getName() + "</name>");
            out.println("<price>2,00</price>");
            out.println("<priceAfter>1,00</priceAfter>");
            out.println("<quantity>2</quantity>");
            out.println("<totalPrice>2,00</totalPrice>");
            out.println("<promotion>50%</promotion>");
            out.println("</product>");
            out.println("</basket>");

            // get info client via hibernate
            out.println("<client>");
            out.println("<pointsGot>0,2</pointsGot>");
            out.println("<pointsCumulative>" + c.getFidelityPoints() + "</pointsCumulative>");
            out.println("<total>2,00</total>");
            out.println("</client>");
            out.println("</pageBasket>");
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
