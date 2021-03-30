package com.jms.dao;

import com.jms.model.Client;
import com.jms.model.ShoppingList;
import java.text.ParseException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ShoppingListDAO {

    public static void saveShoppingList(String name, Client client) {

        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            // new  session
            Transaction t = session.beginTransaction();

            ShoppingList shoppinglist = new ShoppingList(name, client);

            session.save(shoppinglist);

            t.commit();
        }
        
    }

    public static void main(String[] args) throws ParseException {
        // Test
       // ShoppingListDAO.saveShoppingList("cooktail");

        // Exit
        System.exit(0);
    }
}
