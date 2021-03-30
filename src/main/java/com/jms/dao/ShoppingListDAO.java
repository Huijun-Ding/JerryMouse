package com.jms.dao;

import com.jms.model.Basket;
import com.jms.model.Client;
import com.jms.model.PostIt;
import com.jms.model.Product;
import com.jms.model.ShoppingList;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Set;
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

    public static List<ShoppingList> getMyShoppingLists(int idClient) throws SQLException {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Query query = session.createQuery("from ListeCourse where CodeCL = :id");

            query.setParameter("id", idClient);

            List<ShoppingList> lstShoppinglists = query.list();
            lstShoppinglists.forEach(System.out::println);

            t.commit(); // Commit et flush automatique de la session.
            return lstShoppinglists;
        }
    }

    public static void savePostIt(String namePostIt, ShoppingList shoppinglist) {

        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            // new  session
            Transaction t = session.beginTransaction();

            PostIt postit = new PostIt(namePostIt, shoppinglist);

            session.save(postit);

            t.commit();
        }

    }

    public static void savePostItWithProduct(String namePostIt, Product product, ShoppingList shoppinglist) {

        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            // new  session
            Transaction t = session.beginTransaction();

            PostIt postit = new PostIt(namePostIt, shoppinglist, product);

            session.save(postit);

            t.commit();
        }

    }

    public static List<PostIt> getPostIts(int idShoppinglist) throws SQLException {
        /*----- Ouverture de la session -----*/
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            
            Query query = session.createQuery("from PostIt where CodeLC = :id");

            query.setParameter("id", idShoppinglist);

            List<PostIt> lstPostIts = query.list();
            lstPostIts.forEach(System.out::println);

            t.commit(); // Commit et flush automatique de la session.
            return lstPostIts;
        }
    }

    public static void main(String[] args) throws ParseException, SQLException {
        // Test
        // ShoppingListDAO.saveShoppingList("cooktail");
        //ShoppingListDAO.getMyShoppingLists(2); 

        // Exit
        System.exit(0);
    }
}
