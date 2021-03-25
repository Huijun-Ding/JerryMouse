/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;
import com.jms.model.Basket;
import com.jms.model.Client;
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
     * @param email
     * @param password 
     */
    public static boolean authenticate(String email, String password){
       
        Client client = new Client();

        if((email == client.getEmail())&&(password == client.getPassword())){
            return true;
        }else {
            return false;
        }
        
    }
    
    /**
     * Search a client with client's id
     * @param idClient
     * @return Client
     */
    public static Client searchClient(int idClient){
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Query query = session.createQuery("from Client where CodeCL = :code");

            query.setParameter("code", idClient);
            Client client = (Client)query.list().get(0);
            t.commit();
            return client;
        }
    }
    
    public static void main(String[] args) {
        
        System.out.println(ClientDAO.searchClient(1));
        
    }
}

