/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;

import com.jms.model.Client;
import com.jms.model.Product;
import java.text.ParseException;
import java.util.Set;
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
    
    public static void createClient(){
        //Open a session
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            //Open a transaction
            Transaction t = session.beginTransaction();
            
            Client cl1 = new Client("Rakot", "Mia", "rm@gmail.com", "rm123", 10);
            
            session.save(cl1);
            
            t.commit();
        }
    }
    
    public static void getProductsBySearch(String keyword) {
        //Open a session
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            //Open a transaction
            session.beginTransaction();

            Product p = session.get(Product.class, keyword);
            
        }
    }
    public static void main(String[] args) throws ParseException {
        /*----- Test -----*/

        //TestHibernateDAO.xxxxxxx();
        TestHibernateDAO.createClient();
        //TestHibernateDAO.getProductsBySearch("fr");

        /*----- Exit -----*/
        System.exit(0);
    }
    
}
