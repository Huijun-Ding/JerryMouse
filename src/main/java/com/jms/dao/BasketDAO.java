package com.jms.dao;

import com.jms.model.Basket;
import com.jms.model.Client;
import com.jms.model.Product;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Jerry Mouse Software.
 */
public class BasketDAO {    
    public static List<Basket> loadBasket(int CodeCL) throws SQLException{
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Query query = session.createQuery("from Panier where CodeCL = :code");

            query.setParameter("code", CodeCL);

            List<Basket> lstBasket = query.list();
           // lstBasket.forEach(System.out::println);
            
            t.commit(); // Commit et flush automatique de la session.
            return lstBasket;
        }
    }
    
    // calculer le prix avec promotion : produit sans promo?
    public static float calculPriceUnitaryAfterPromo(float price, float percentage) {
        return price * (1 - percentage);
//        BigDecimal bdPrice = new BigDecimal(String.valueOf(price));
//        BigDecimal bdPercentage = new BigDecimal(String.valueOf(percentage));
//        BigDecimal res = new BigDecimal(String.valueOf("1")).subtract(bdPercentage);
//       String s=new java.text.DecimalFormat("0.00").format(bdPrice.multiply(res).doubleValue());
//        System.out.println(Double.valueOf(s));
//        double d=Double.valueOf(s);
//        return bdPrice.multiply(res).doubleValue();
    }
    
    // calculer le prix total avec promotion pour chaque produit 
    public static float calculPriceTotalProduct(int quantity, float price) {
        return price * quantity; 
    }
    
    // calculer le prix total final
    public static float calculPriceTotal(ArrayList<Float> prices) {
        float sum = 0f;
        for(int i = 0; i<prices.size(); i++){
            sum += prices.get(i);
        }
        return sum; 
    }
    
    // calculer points got
    public static int calculPointsGot(float priceTotal) {
        return (int)priceTotal/10; 
    }

    public static void main(String[] args) {
        // test for method loadBasket
        try {
            System.out.println(BasketDAO.loadBasket(1).get(0)); 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        // test for method calculPriceUnitaryAfterPromo
        System.out.println(BasketDAO.calculPriceUnitaryAfterPromo(2, 0.5F));
        
        // test for method calculPriceTotal
        Float[] d = new Float[]{1f, 2f};
        ArrayList<Float> prices = new ArrayList<>(Arrays.asList(d));
        System.out.println(BasketDAO.calculPriceTotal(prices));
        
        // test for method calculPointsGot
        System.out.println(BasketDAO.calculPointsGot(2f));
        
        // test double
        System.out.println(new BigDecimal(String.valueOf("1.2")).multiply(new BigDecimal(String.valueOf("2.14"))));
    }
}
