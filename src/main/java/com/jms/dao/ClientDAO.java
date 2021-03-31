/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;

import com.jms.model.Basket;
import com.jms.model.Client;
import java.text.ParseException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author RAKOTOARISOA
 */
public class ClientDAO {

    /**
     * check if entered email and entered password are right
     *
     * @param email
     * @param password
     * @return true if email and password are valid else false
     */
    public static boolean authenticate(String email, String password) {
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            /*----------------Ouverture d'une transaction---------*/
            Transaction t = session.beginTransaction();
            Query query = session.createQuery("from Client c "
                    + "where c.email = :email "
                    + "and c.password = :password");

            query.setParameter("email", email);
            query.setParameter("password", password);

            if (query.list().size() > 0) {
                return true;
            }
            return false;

        }
    }

    /**
     * Get the object client by the email and the password
     *
     * @param email
     * @param password
     * @return
     */
    public static Client getByEmailPassword(String email, String password) {
        Client client;
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            /*----------------Ouverture d'une transaction---------*/
            Transaction t = session.beginTransaction();
            Query query = session.createQuery("from Client c "
                    + "where c.email = :email "
                    + "and c.password = :password");

            query.setParameter("email", email);
            query.setParameter("password", password);
            List<Client> lstClients = query.list();
            client = lstClients.get(0);
        }
        return client;
    }

    /**
     * Create a new client in the database
     */
    public static void create() {
        //Open a session
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            //Open a transaction
            Transaction t = session.beginTransaction();

            Client cl1 = new Client("RAKOTO", "Chloé", "rc@gmail.com", "rm123", 10);

            session.save(cl1);

            t.commit();
        }
    }

    /**
     * Load client by id
     *
     * @param id
     */
    public static void load(int id) {
        // Open a session
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Client c = session.get(Client.class, id);

            System.out.println("----------Client------------");
            System.out.println(c.getLastName() + " " + c.getFirstName() + " " + c.getEmail() + " " + c.getFidelityPoints());
        }
    }

    /**
     * Search a client with client's id
     *
     * @param idClient
     * @return Client
     */
    public static Client searchClient(int idClient) {
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Query query = session.createQuery("from Client where CodeCL = :code");

            query.setParameter("code", idClient);
            Client client = (Client) query.list().get(0);
            t.commit();
            return client;
        }
    }

    /**
     * update the points of the client
     *
     * @param client
     * @param point
     * @throws ParseException
     */
    public static void updatePoint(Client client, int point) throws ParseException {
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            // Make the client persistent
            session.update(client);
            // Update the points for the client
            client.setFidelityPoints(point);
            // Save the client in DB
            session.save(client);
            // Update in DB
            session.update(client);
            // Commit all the changes
            t.commit();
            // Close session
            session.close();
        }
    }

    public static void main(String[] args) throws ParseException {
        // test for method searchClient
        Client client=ClientDAO.searchClient(2);
        ClientDAO.updatePoint(client,15);
        System.out.println(ClientDAO.searchClient(2).getFidelityPoints());

        //create();
        load(2);
        boolean res = authenticate("rc@gmail.com", "rm123");
        System.out.println(res);
    }
}
