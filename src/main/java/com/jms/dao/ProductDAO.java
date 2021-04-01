/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;

import com.jms.model.Client;
import com.jms.model.Order;
import com.jms.model.Product;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpSession;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * ProductDAO Class.
 *
 * @author Jerry Mouse Software.
 */
public class ProductDAO {

    /**
     * Connection.
     * <b>Rule(s) : connection to datebase</b>
     */
    private static Connection cx = null;
//    private static final String URL = "jdbc:mysql://localhost:3306/jm";
//    private static final String LOGIN = "root";
//    private static final String PASSWORD = "";

    private static final String URL = "jdbc:mysql://sql4.freemysqlhosting.net:3306/sql4402068";
    private static final String LOGIN = "sql4402068";
    private static final String PASSWORD = "Act8WQ5TtL";

    //------------Methods--------------
    /**
     * connexion. create connection to database
     */
    private static void connexion() throws ClassNotFoundException, SQLException {
        // load pivot for db
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("pilot ok");
        } catch (ClassNotFoundException ex) {
            throw new ClassNotFoundException("Exception connexion() - Pilote MySql introuvable - " + ex.getMessage());
        }

        try {
            ProductDAO.cx = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("connection ok");
        } catch (SQLException ex) {
            throw new SQLException("Exception connexion() - Problème de connexion à la base de données - " + ex.getMessage());
        }
    }

    /**
     * completeSearchBarByProductName. Complete the search bar by a product
     * name.
     *
     * @param product key word enter in search bar.
     *
     * @return ArrayList<String>.
     */
    public static ArrayList<String> completeSearchBarByProductName(String product) throws ClassNotFoundException, SQLException {
        // create connection to db
        if (ProductDAO.cx == null) {
            ProductDAO.connexion();
        }

        ArrayList<String> products = new ArrayList<>();

        // sql request
        String sql = "SELECT LibelleP FROM Produit WHERE LibelleP LIKE ?";
        //String sql = "SELECT LibelleP FROM Produit WHERE LibelleP LIKE ?";

        try (PreparedStatement st = ProductDAO.cx.prepareStatement(sql)) {
            //execute request
            st.setString(1, "%" + product + "%");
            try (ResultSet rs = st.executeQuery()) {
                // read request result
                while (rs.next()) {
                    products.add(rs.getString(1));
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("Exception completeSearchBarByProductName() : Problème SQL - " + ex.getMessage());
        }

        return products;
    }

    /**
     * Complete the search bar by a product name.
     *
     * @param product key word enter in search bar.
     *
     * @return ArrayList<String>.
     */
    public static ArrayList<String> completeSearchBarByCategory(String product) throws ClassNotFoundException, SQLException {
        // create connection to db
        if (ProductDAO.cx == null) {
            ProductDAO.connexion();
        }

        ArrayList<String> categories = new ArrayList<>();

        // sql request
        String sql = "SELECT LibelleCP FROM CategorieProduit WHERE LibelleCP LIKE ?";

        try (PreparedStatement st = ProductDAO.cx.prepareStatement(sql)) {
            //execute request
            st.setString(1, "%" + product + "%");
            try (ResultSet rs = st.executeQuery()) {
                // read request result
                while (rs.next()) {
                    categories.add(rs.getString(1));
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("Exception completeSearchBarByCategory() : Problème SQL - " + ex.getMessage());
        }

        return categories;
    }

    /**
     * Searche a product with its EAN
     *
     * @param ean
     * @return Product
     */
    public static Product searchProduct(String ean) throws SQLException {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            System.out.println(ean);
            Query query = session.createQuery("from Produit where EANP = :code");

            query.setParameter("code", ean);
            Product product = (Product) query.list().get(0);
            t.commit();
            return product;
        }
    }

    /**
     * Complete the search bar by a product name.
     *
     * @param keyword key word enter in search bar.
     *
     * @return List<Product>.
     */
    public static List<Product> returnSrearchResult(String keyword) {

        //Open a session
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();

            Query query = session.createQuery("SELECT DISTINCT new com.jms.model.Product(p.ean, p.name, p.format, p.nutriscore, p.packaging, p.packagingQuantity, p.unitPrice, p.kgPrice, p.urlThumbnail) FROM Produit p WHERE p.category.name = :c OR p.name LIKE :p");

            query.setParameter("c", keyword);
            query.setParameter("p", "%" + keyword + "%");

            List<Product> lstProducts = query.list();

            for (int i = 0; i < lstProducts.size(); i++) {
                System.out.println(lstProducts.get(i).getName());
            }

            t.commit();

            return lstProducts;
        }
    }

    public static List<Product> getProductsByName(String lib) {

        //Open a session
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();

            Query query = session.createQuery("FROM Produit p WHERE p.name LIKE :p");
            query.setParameter("p", "%" + lib + "%");

            List<Product> lstProducts = query.list();

            for (int i = 0; i < lstProducts.size(); i++) {
                System.out.println("product"+lstProducts.get(i));
            }

            t.commit();

            return lstProducts;
        }
    }

    public static Product getProductByHistory(Client client, List<Product> lstp) {
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            System.out.println("test1");
            Transaction t = session.beginTransaction();
            session.update(client);
            
            // Update the points for the client
            List<Product> history = new ArrayList<>();
            for (Iterator it = client.getOrders().iterator(); it.hasNext();) {
                Order order = (Order) it.next();
                List<Product> lst = new ArrayList<>(order.getProducts().keySet());
                for (Product p : lst) {
                    if (history.contains(p) == false) {
                        history.add(p);
                    }
                }
            }
            System.out.println(history);

            history.retainAll(lstp);
            System.out.println("getProductByHistory"+history);

            t.commit();

            if (history.size() >= 1) {
                return history.get(0);
            } else {
                return null;
            }
        }
    }

    public static Product getProductByPref(Client c, List<Product> lstp) {
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            session.update(c);
            
            Hibernate.initialize(c.getFavoriteProducts());
            for (Product favoriteProduct : c.getFavoriteProducts()) {
                Hibernate.initialize(favoriteProduct.getLabels());
                Hibernate.initialize(favoriteProduct.getPromotions());
            }

            System.out.println("0");

            Set<Product> lstcp = c.getFavoriteProducts();
            System.out.println(lstcp.size());
            ArrayList<Product> lstcpp = new ArrayList<>();
            for (Iterator it = lstcp.iterator(); it.hasNext();) {
                System.out.println("1" + it.next());
                lstcpp.add((Product) it.next());
                System.out.println("2" + (Product) it.next());
            }

            lstp.retainAll(lstcpp);

            System.out.println("productttttttttt" + lstp);
            t.commit();
            if (lstp.size() >= 1) {
                return lstp.get(0);
            } else {
                return new Product();
            }
        }
    }

//    public static void main(String[] s) throws ClassNotFoundException, SQLException {
//        try {
//        System.out.println(ProductDAO.completeSearchBarByProductName("Chocolat"));
//        System.out.println(ProductDAO.completeSearchBarByCategory("Chocolat"));
//
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
    public static void main(String[] args) throws ParseException, ClassNotFoundException {
        /*----- Test -----*/

        //System.out.println(getProductByPref());
        //ProductDAO.returnSrearchResult("Cafés");
        System.out.println(" main "+getProductsByName("Chocolat au lait").get(0));

        /*----- Exit -----*/
        System.exit(0);

        try {
            ProductDAO.searchProduct("P1");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
