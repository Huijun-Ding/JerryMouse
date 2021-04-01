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
            Query query = session.createQuery("from Panier where CodeCL = :code "
                    + "and qtePanier > 0");

            query.setParameter("code", CodeCL);

            List<Basket> lstBasket = query.list();
            lstBasket.forEach(System.out::println);
            
            t.commit(); // Commit et flush automatique de la session.
            return lstBasket;
        }
    }
    
    public static int calculNbProduct(int CodeCL) throws SQLException{
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Query query = session.createQuery("from Panier where CodeCL = :code");

            query.setParameter("code", CodeCL);

            List<Basket> lstBasket = query.list();
//            lstBasket.forEach(System.out::println);
            int nb = 0;
                if(!lstBasket.isEmpty()){
                    nb = lstBasket.stream().map(basket -> basket.getQtyBasket()).reduce(nb, Integer::sum);
            }
            t.commit(); // Commit et flush automatique de la session.
            return nb;
        }
    }
    
    // calculer le prix avec promotion : produit sans promo?
    public static float calculPriceUnitaryAfterPromo(float price, float percentage) {
        return price * (1 - percentage);
    }
    
    // calculer le montant de reduction d'un produit
    public static float calculMontReductionProduit(float price, float percentage) {
        return price * percentage;
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
    
    // calculate points got
    public static int calculPointsGot(float priceTotal) {
        return (int)priceTotal/10; 
    }
    
    // check if a product is in basket of a client
    public static boolean checkProductBakset(int idClient, String ean) throws SQLException{
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            
            boolean exist = false;
            Query query = session.createSQLQuery("select count(*) from Panier "
                    + "where EANP = :ean "
                    + "and codeCL = :codeCL");
            
            query.setParameter("codeCL", idClient);
            query.setParameter("ean", ean);
            int nbLine = Integer.valueOf(query.list().get(0).toString());    
           
            if (nbLine > 0){
                exist = true;
            }
            t.commit(); // Commit et flush automatique de la session.
            return exist;
        }
    }
    
    // add product to basket
    public static void addProductToBasket(int idClient, String ean) throws SQLException{
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Query query = session.createSQLQuery("insert into Panier (EANP, CodeCL, qtePanier) "
                    + "VALUES(:ean, :codeCL, 1)");
            
            query.setParameter("codeCL", idClient);
            query.setParameter("ean", ean);
            
            System.out.println(query.executeUpdate());
            
            t.commit(); // Commit et flush automatique de la session.
        }
    }
    
    // add a product
    public static int updateBasket(int idClient, String ean) throws SQLException{
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            
            // get the quantity of product
            Query query1 = session.createSQLQuery("SELECT qtePanier FROM Panier "
                    + "WHERE EANP= :ean and CodeCL= :codeCL");
            query1.setParameter("codeCL", idClient);
            query1.setParameter("ean", ean);
            int qtePanier = Integer.valueOf(query1.list().get(0).toString());
            
            // update the quantity of product
            Query query = session.createSQLQuery("UPDATE Panier SET qtePanier= :nb "
                    + "WHERE EANP= :ean and CodeCL= :codeCL");
            query.setParameter("codeCL", idClient);
            query.setParameter("ean", ean);
            query.setParameter("nb", qtePanier + 1);
            
            int nb = query.executeUpdate();
            t.commit(); // Commit et flush automatique de la session.
            return nb;
        }
    }
    
    // qtyprod - 1
    public static int updateBasketMinus(int idClient, String ean) throws SQLException{
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            
            // get the quantity of product
            Query query1 = session.createSQLQuery("SELECT qtePanier FROM Panier "
                    + "WHERE EANP= :ean and CodeCL= :codeCL");
            query1.setParameter("codeCL", idClient);
            query1.setParameter("ean", ean);
            int qtePanier = Integer.valueOf(query1.list().get(0).toString());
            
            // update the quantity of product
            Query query = session.createSQLQuery("UPDATE Panier SET qtePanier= :nb "
                    + "WHERE EANP= :ean and CodeCL= :codeCL");
            query.setParameter("codeCL", idClient);
            query.setParameter("ean", ean);
            query.setParameter("nb", qtePanier - 1);
            
            int nb = query.executeUpdate();
            t.commit(); // Commit et flush automatique de la session.
            return nb;
        }
    }
    
    // update a basket
    public static int deleteBasket(int idClient) throws SQLException{
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            
            // get the quantity of product
            Query query = session.createSQLQuery("DELETE FROM `Panier` WHERE CodeCL = :codeCL");
            query.setParameter("codeCL", idClient);

            int nb = query.executeUpdate();
            
            t.commit(); // Commit et flush automatique de la session.
            return nb;
        }
    }
    
    // check quantity of prod dans le panier
    public static int checkQtyProd(int CodeCL, String ean) throws SQLException{
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Query query = session.createQuery("from Panier "
                    + "where CodeCL = :code "
                    + "and EANP = :ean");

            query.setParameter("code", CodeCL);
            query.setParameter("ean", ean);
            List<Basket> lstBasket = query.list();
            int nb = lstBasket.get(0).getQtyBasket(); 
            
            t.commit(); // Commit et flush automatique de la session.
            return nb;
        }
    }    
    

    public static void main(String[] args) {
        // test for method loadBasket
//        try {
//            System.out.println(BasketDAO.loadBasket(1).get(0)); 
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//        // test for method calculPriceUnitaryAfterPromo
//        System.out.println(BasketDAO.calculPriceUnitaryAfterPromo(2, 0.5F));
//        
//        // test for method calculPriceTotal
//        Float[] d = new Float[]{1f, 2f};
//        ArrayList<Float> prices = new ArrayList<>(Arrays.asList(d));
//        System.out.println(BasketDAO.calculPriceTotal(prices));
//        
//        // test for method calculPointsGot
//        System.out.println(BasketDAO.calculPointsGot(2f));
        
        // test for method addProductToBasket    
        try {
//            BasketDAO.addProductToBasket(1, "P3");
//            BasketDAO.loadBasket(1); 
//            System.out.println(BasketDAO.checkProductBakset(1, "P3"));
//            System.out.println(BasketDAO.checkProductBakset(1, "P4"));
//            System.out.println(BasketDAO.updateBasket(1, "P3"));
//            System.out.println(calculNbProduct(2));
//            System.out.println(BasketDAO.deleteBasket(2));
            System.out.println(BasketDAO.checkQtyProd(2, "P10"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }
}
