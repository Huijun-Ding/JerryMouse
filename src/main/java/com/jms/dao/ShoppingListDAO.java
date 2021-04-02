package com.jms.dao;

import com.jms.model.Basket;
import com.jms.model.Client;
import com.jms.model.PostIt;
import com.jms.model.Product;
import com.jms.model.ShoppingList;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ShoppingListDAO {

    public static void saveShoppingList(String name, Client client) {

        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            // new  session
            Transaction t = session.beginTransaction();

            ShoppingList shoppinglist = new ShoppingList(name, client);

            session.save(shoppinglist);

            t.commit();
        }

    }

    public static List<ShoppingList> getMyShoppingLists(int idClient) throws SQLException {

        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
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

        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            // new  session
            Transaction t = session.beginTransaction();

            PostIt postit = new PostIt(namePostIt, shoppinglist);

            session.save(postit);

            t.commit();
        }

    }

    public static void savePostItWithProduct(Product product, ShoppingList shoppinglist) {

        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            // new  session
            Transaction t = session.beginTransaction();

            PostIt postit = new PostIt(shoppinglist, product);

            session.save(postit);

            t.commit();
        }

    }

    public static ShoppingList getShoppingList(int id) {
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            Query query = session.createQuery("from ListeCourse where CodeLC = :id");

            query.setParameter("id", id);

            ShoppingList sl = (ShoppingList) query.list().get(0);

            return sl;
        }
    }

    public static List<PostIt> getPostIts(int idShoppinglist) throws SQLException {

        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();

            Query query = session.createQuery("from PostIt where CodeLC = :id");

            query.setParameter("id", idShoppinglist);

            List<PostIt> lstPostIts = query.list();
            //lstPostIts.forEach(System.out::println);

            t.commit(); // Commit et flush automatique de la session.
            return lstPostIts;
        }
    }

    /**
     * Creates in database a shopping list for a given client, with a collection
     * of post-its.
     *
     * @param title the name of the shopping list.
     * @param postIts the set of post-its to add to the shopping list.
     * @param client the client creating the shoppinf list.
     */
    public static void create(String title, Set<PostIt> postIts, Client client) {

        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            // Create a new shopping list with parameters
            ShoppingList s = new ShoppingList(title, postIts, client);
            // Make the shopping list persistent in DB
            session.save(s);
            // Save each post-it in DB with the given shopping list
            for (PostIt p : s.getPostIts()) {
                p.setShoppingList(s);
                session.save(p);
            }
            session.update(s);

            t.commit();
            session.close();
        }
    }

    public static void main(String[] args) throws ParseException, SQLException {
        // Test
        // ShoppingListDAO.saveShoppingList("cooktail");
        // ShoppingListDAO.getMyShoppingLists(2); 
        //List<PostIt> lstPostIts = ShoppingListDAO.getPostIts(5);
//        for (PostIt lstPostIt : lstPostIts) {
//            if(lstPostIt.getProduct() != null)
//                System.out.println("Product name : " + lstPostIt.getProduct().getName());
//        }
        //ShoppingListDAO.getShoppingList(5);
        Client client = ClientDAO.load(2);
        Set<PostIt> postIts = new HashSet<>(0);

        postIts.add(new PostIt("vin 1"));
        postIts.add(new PostIt("beurre"));
        postIts.add(new PostIt("farine"));
        ShoppingListDAO.create("Test Create", postIts, client);

        // Exit
        System.exit(0);
    }
}
