/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;

import com.jms.model.Basket;
import com.jms.model.Client;
import com.jms.model.Have;
import com.jms.model.Order;
import com.jms.model.OrderLine;
import com.jms.model.Product;
import com.jms.model.Store;
import com.jms.model.TimeSlot;
import java.text.ParseException;
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
     *
     * @param client the client making an order.
     * @param store the store where to pick the order.
     * @param haveTS the object containing the date and the timeslot to pick up
     * the order.
     */
    public static Order registerBasket(Client client, Store store, Have haveTS) throws ParseException {
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();

            // Make the client persistent
            session.update(client);

            // Create new order with current date
            Date date = new Date();
            //String dateS = DF.format(date);
            Order order = new Order(date);

            // Register the store of the order
            order.setStore(store);

            // Associate the client with the order
            order.setClient(client);

            // Register the date and the timeslot to pick up the order
            order.setTimeslot(haveTS.getTimeSlot());
            order.setPickupDate(haveTS.getDate());

            // Save the order in DB
            session.save(order);

            // Register the order lines from the list of baskets of the client
            for (Product product : client.getBaskets().keySet()) {
                OrderLine orderLine = new OrderLine(client.getBaskets().get(product).getQtyBasket(), order, product);
                order.getProducts().put(product, orderLine);
                session.save(orderLine);
            }

            // Associate the order with the client
            client.getOrders().add(order);

            // Update in DB
            session.update(order);
            session.update(client);

            // Commit all the changes
            t.commit();

            // Close session
            session.close();
            return order;
        }
    }
}
