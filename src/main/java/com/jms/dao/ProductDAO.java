/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;

import com.jms.model.Basket;
import com.jms.model.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
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
    private static final String URL = "jdbc:mysql://localhost:3307/db_21509053_2";
    private static final String LOGIN = "21509053";
    private static final String PASSWORD = "Q02MI0";

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

//    /**
//     * return a objet product by id.
//     *
//     * @param id identity of a product.
//     *
//     * @return Product.
//     */
//    public static Product getProductById(String id) {
//        //Open a session
//        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
//            //Open a transaction
//            session.beginTransaction();
//            // get object Product
//            Product p = session.get(Product.class, id);
//            
//            System.out.println("Product :" + p.getDescription() + " " + p.getBrand());
//            
//            return p;
//        }
//    }
//    /**
//     * Complete the search bar by a product name.
//     *
//     * @param keyword key word enter in search bar.
//     *
//     * @return ArrayList<Product>.
//     */
//    public static ArrayList<Product> returnSrearchResult(String keyword) throws ClassNotFoundException, SQLException {
//        // create connection to db
//        if (ProductDAO.cx == null) {
//            ProductDAO.connexion();
//        }
//
//        // create a now list de Product(object) empty
//        ArrayList<Product> p = new ArrayList<>();
//
//        if (!keyword.equals("")) {
//
//            // sql request
//            String sql = "SELECT DISTINCT p.EANP FROM CategorieProduit cp, Produit p WHERE p.CodeCategorieP = cp.CodeCP and LibelleCP = ? or p.LibelleP LIKE ?";
//
//            try (PreparedStatement st = ProductDAO.cx.prepareStatement(sql)) {
//                //execute request
//                st.setString(1, keyword);
//                st.setString(2, "%" + keyword + "%");
//
//                try (ResultSet rs = st.executeQuery()) {
//                    // if the result is not empty, add all products
//                    if (!rs.equals("")) {
//                        while (rs.next()) {
//                            p.add(getProductById(rs.getString(1)));
//                            System.out.println(getProductById(rs.getString(1)).getName());
//                        }
//                    } else {
//                        System.out.println("Il n'y a pas d'article correspondant.");
//                    }
//                }
//            } catch (SQLException ex) {
//                throw new SQLException("Exception returnSrearchResult() : Problème SQLc - " + ex.getMessage());
//            }
//        }
//        // return the list of products
//        return p;
//    }
    public static List<Product> returnSrearchResult(String keyword) {

        //Open a session
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();

            Query query = session.createQuery("SELECT new com.jms.model.Product(p.ean, p.name, p.format, p.nutriscore, p.packaging, p.packagingQuantity, p.unitPrice, p.kgPrice, p.urlThumbnail) FROM Produit p WHERE p.category.name = :c OR p.name LIKE :p");

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

//    public static void main(String[] s) throws ClassNotFoundException, SQLException {
//        try {
//        System.out.println(ProductDAO.completeSearchBarByProductName("Chocolat"));
//        System.out.println(ProductDAO.completeSearchBarByCategory("Chocolat"));
//
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
    public static void main(String[] args) throws ParseException {
        /*----- Test -----*/

        ProductDAO.returnSrearchResult("chocolat");

        /*----- Exit -----*/
        System.exit(0);
    }
}
