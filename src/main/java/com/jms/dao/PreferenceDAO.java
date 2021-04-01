/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;

import com.jms.model.Client;
import com.jms.model.Product;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * The PreferenceDAO class contains the DAO methods regarding the preferences of
 * a client.
 *
 * @author Jerry Mouse Software
 */
public class PreferenceDAO {

    /**
     * Sets a product as favorite for a given client.
     *
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
     *
     * @param client the given client.
     * @return the client updated with the Set of favorite products.
     */
    public static Client getFavoriteProducts(Client client) {
        //List<Product> list;
        /*----- Session opening -----*/
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();

            // Make the object in parameter persistent
            //client = (Client) session.merge(client);
            Client clientUp = (Client) session.get(Client.class, client.getCode());

            // Get favorite products lazily
            Hibernate.initialize(clientUp.getFavoriteProducts());
            for (Product favoriteProduct : clientUp.getFavoriteProducts()) {
                Hibernate.initialize(favoriteProduct.getLabels());
                Hibernate.initialize(favoriteProduct.getPromotions());
            }

//            String sql = "SELECT c "
//                    + "FROM Client c "
//                    + "LEFT JOIN FETCH c.favoriteProducts "
//                    + "WHERE c.code = :c";
//            
//            // Create HQL query
//            Query query = session.createQuery(sql);
//            query.setParameter("c", client.getCode());
            //list = query.list();
            //t.commit();
            session.close();
//            client = (Client) query.getSingleResult();
            //client = (Client) session.merge(client);
            //t.commit();
            return clientUp;
        }
    }
}
