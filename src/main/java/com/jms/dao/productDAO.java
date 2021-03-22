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
 *
 * @author Mathi
 */
public class productDAO {
    
    /*---------*/
    /* Données */
    /*---------*/

    /*----- Connexion -----*/
    private static Connection cx = null;

    /*----- Données de connexion -----*/
    private static final String URL = "jdbc:mysql://localhost:3307/db_21509053_2";
    private static final String LOGIN = "21509053";
    private static final String PASSWORD = "Q02MI0";


    /*----------*/
    /* Méthodes */
    /*----------*/

    /**
     * Crée la connexion avec la base de données.
     */
    private static void connexion() throws ClassNotFoundException, SQLException {
        /*----- Chargement du pilote pour la BD -----*/
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new ClassNotFoundException("Exception connexion() - Pilote MySql introuvable - " + ex.getMessage());
        }

        /*----- Ouverture de la connexion -----*/
        try {
            productDAO.cx = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException ex) {
            throw new SQLException("Exception connexion() - Problème de connexion à la base de données - " + ex.getMessage());
        }
    }
    
        public static ArrayList<String> searchProduct(String product) throws ClassNotFoundException, SQLException {
        /*----- Création de la connexion à la base de données -----*/
        if (productDAO.cx == null) {
            productDAO.connexion();
        }

        /*----- Interrogation de la base -----*/
        ArrayList<String> products = new ArrayList<>();

        /*----- Requête SQL -----*/
        String sql = "SELECT p.idP FROM Produit p, Categorie c WHERE p.numC = c.numC and p.NomP LIKE ? or c.NomC LIKE ?";

        /*----- Ouverture de l'espace de requête -----*/
        try (PreparedStatement st = productDAO.cx.prepareStatement(sql)) {
            /*----- Exécution de la requête -----*/
            st.setString(1, "%" + product + "%");
            st.setString(2, "%" + product + "%");
            try (ResultSet rs = st.executeQuery()) {
                /*----- Lecture du contenu du ResultSet -----*/
                while (rs.next()) {
                    products.add(rs.getString(1));
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("Exception lireMots() : Problème SQL - " + ex.getMessage());
        }

        return products;
    }
    
        public static void main(String[] s) {
        try {
            System.out.println(productDAO.searchProduct("café"));

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
