/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;

import com.jms.model.Basket;
import com.jms.model.Client;
import com.jms.model.Order;
import com.jms.model.OrderLine;
import com.jms.model.Product;
import com.jms.model.Store;
import com.jms.model.TimeSlot;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mlk
 */
public class ValiderDAO {
    /**
     * 
     */
    private static SimpleDateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
    
    /**
     * Register a basket in DB of a client with the store and timeslot.
     */
    public static void registerBasket(Client client, Store store, TimeSlot timeslot) {
        try (Session session = HibernateUtilDAO.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            
            // Create new order with current date
            Date date = new Date();
            DF.format(date);
            Order order = new Order(date);
            
            // Register the store of the order
            order.setStore(store);
            
            // Register the timeslot of the order
            order.setTimeslot(timeslot);
            
            // Register the order lines from the list of baskets of the client
            for (Product product : client.getBaskets().keySet()) {
                OrderLine orderLine = new OrderLine(client.getBaskets().get(product).getQtyBasket(), order, product);
                order.getProducts().put(product, orderLine);
            }
            
            // Associate the order with the client
            client.getOrders().add(order);
            
            // Update in DB
            session.save(order);
            session.update(client);
            
            // Close session
            session.close();
            
        }
    }
    
}
