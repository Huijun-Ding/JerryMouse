package com.jms.controller;

import com.jms.dao.BasketDAO;
import com.jms.dao.ClientDAO;
import com.jms.dao.ProductDAO;
import com.jms.dao.PromotionDAO;
import com.jms.model.Basket;
import com.jms.model.Client;
import com.jms.model.Product;
import com.jms.model.ProductConditioning;
import com.jms.model.ProductNutriScore;
import com.jms.model.Promotion;
import com.jms.model.Store;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.NumberFormat;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jerry Mouse Software.
 */
public class LookBasketServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession(true);
        
        try ( PrintWriter out = response.getWriter()) {
            // get un document DOM - XML
            out.println("<?xml version=\"1.0\"?>");
            response.setContentType("application/xml;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            out.println("<pageBasket>");
            out.println("<basket>");

            // ----------- TEST : CONSTANT ---------------
            Product p = new Product("2", "pomme", "bonne pomme", "bon", "350",
                    true, ProductNutriScore.A, ProductConditioning.LOT);
            Client c = new Client("Shangshang", "Zhao", "ss@gmail.com", "ss", 5);
            
            
            // ------ INFO CLIENT ------------------
            // get id of client from host page
//            String idClient = request.getParameter("idClient");
            // get info of client
//            Client client = ClientDAO.searchClient(Integer.parseInt(idClient));
            Client client = (Client) session.getAttribute("client");
            
            // ------ INFO BASKET / PRODUCT CLIENT ---------
            // search basket for client
            List<Basket> lstBasket = BasketDAO.loadBasket(client.getCode());

            // search products in basket and add to a list
            String eanp = "";
            Map<Product, Integer> productQty = new HashMap<>();
            for (Basket basket : lstBasket) {
                eanp = basket.getBasketId().getEan();
                productQty.put(ProductDAO.searchProduct(eanp), basket.getQtyBasket());
            }

            // a list of initial price for all products
            ArrayList<Float> prices = new ArrayList<>();
            
            // a list of discount for all products
            ArrayList<Float> discount = new ArrayList<>();

            // loop for all products
            for (Product product : productQty.keySet()) {
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
                
                // quantity of a product in basket
                int quantityProd = productQty.get(product);
                
                // total price of a product in basket
                float totalDiscountProduct = BasketDAO.calculPriceTotalProduct(
                        quantityProd, discountProd);
                // add discount for product to list of discount
                discount.add(totalDiscountProduct);
                
                // total price of a product in basket
                float totalPriceProduct = BasketDAO.calculPriceTotalProduct(
                        quantityProd, priceAfterFloat);

                prices.add(totalPriceProduct);

                out.println("<product>");
                out.println("<ean>" + product.getEan() + "</ean>");
                out.println("<photo>" + product.getUrlThumbnail() + "</photo>");
                out.println("<id>" + product.getEan() + "</id>");
                out.println("<name>" + product.getName() + "</name>");
                out.println("<format>" + product.getFormat() + "</format>");
                out.println("<price>" + String.format("%.2f", product.getUnitPrice()) 
                        + "</price>");
                out.println("<priceAfter>" + priceAfterString + "</priceAfter>");
                out.println("<quantity>" + quantityProd + "</quantity>");
                out.println("<totalPrice>" + String.format("%.2f", totalPriceProduct)
                        + "</totalPrice>");
                out.println("<promotion>" + promo + "</promotion>");
                out.println("</product>");
            }
            out.println("</basket>");
            
            // calcul total price of basket
            float total = BasketDAO.calculPriceTotal(prices);
            
            // calculate total discount for all products
            float totalDiscount = BasketDAO.calculPriceTotal(discount);

            // calculer points got
            int pointsGot = BasketDAO.calculPointsGot(total);

            // get info client via hibernate
            out.println("<client>");
            out.println("<pointsGot>" + pointsGot + "</pointsGot>");
            out.println("<pointsCumulative>" + client.getFidelityPoints() 
                    + "</pointsCumulative>");
            out.println("<discount>" + String.format("%.2f", totalDiscount) 
                        + "</discount>");
            out.println("<total>" + String.format("%.2f", total) + "</total>");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LookBasketServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LookBasketServlet.class.getName()).log(Level.SEVERE, null, ex);
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
