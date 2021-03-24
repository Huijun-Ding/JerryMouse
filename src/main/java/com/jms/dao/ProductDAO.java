/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    private static final String URL = "jdbc:mysql://localhost:3307/db_21509053";
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
     * completeSearchBarByProductName.
     *
     * @param product key word enter in search bar.
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
     * completeSearchBarByCategory.
     *
     * @param product key word enter in search bar.
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

    public static void main(String[] s) {
        try {
            System.out.println(ProductDAO.completeSearchBarByProductName("fr"));
            System.out.println(ProductDAO.completeSearchBarByCategory("fr"));

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
