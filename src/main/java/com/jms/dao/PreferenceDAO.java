/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;

import com.jms.model.Client;
import com.jms.model.Product;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * The PreferenceDAO class contains the DAO methods regarding the preferences
 * of a client.
 *
 * @author Jerry Mouse Software
 */
public class PreferenceDAO {

    /**
     * Sets a product as favorite for a given client.
     * @param product the product to add to favorites.
     * @param client the given client.
     */
    public static void addFavoriteProduct(Product product, Client client) {
        /*----- Session opening -----*/
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            // Make objects persistents
            session.saveOrUpdate(product);
            session.saveOrUpdate(client);
            
            // Add in DB
            client.getFavoriteProducts().add(product);
            product.getClients().add(client);
            
            t.commit();
            session.close();
        }
    }
    
    /**
     * Gets the favorite products of a client.
     * @param client
     * @return the list of favorite products of a client.
     */
    public static List<Product> getFavoriteProducts (Client client) {
        List<Product> list;
        /*----- Session opening -----*/
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            
            String sql = "SELECT p "
                    + "FROM Produit p "
                    + "WHERE p.clients.code = :c";
            
            // Create HQL query
            Query query = session.createQuery(sql);
            query.setParameter("c", client.getCode());
            list = query.list();
            
            t.commit();
            session.close();
            return list;
        }
    }
}
