/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;

import com.jms.model.Client;
import java.text.ParseException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Mathi
 */
public class TestHibernateDAO {
     
    /**
     * Programme de test.
     */
    
    /**
     * Create a new client in the database
     */
    public static void createClient(){
        //Open a session
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            //Open a transaction
            Transaction t = session.beginTransaction();
            
            Client cl1 = new Client("Rakot", "Mia", "rm@gmail.com","rm123", 10);
            
            session.save(cl1);
            
            t.commit();
        }
    }
    
    public static void loadClient(int id) {
        // Open a session
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Client c = session.get(Client.class, id );
            
            System.out.println("----------Client------------");
            System.out.println(c.getLastName()+ " " + c.getFirstName()+" "+c.getEmail()+" "+c.getFidelityPoints());
        }
    }

    public static void main(String[] args) throws ParseException {
        /*----- Test -----*/

        //TestHibernateDAO.xxxxxxx();
        TestHibernateDAO.createClient();
        //TestHibernateDAO.loadClient(1);
        

        /*----- Exit -----*/
        System.exit(0);
    }
    
}
