/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;

import com.jms.model.Store;
import java.util.List;
import org.hibernate.Session;

/**
 * StoreDAO Class.
 * @author Jerry Mouse Software.
 */
public class StoreDAO {

    // ----------------------- METHODS ----------------------

    /**
     * Get all stores from database.
     * @return All stores from database.
     */
    public static List<Store> getAllStores() {
        List<Store> stores;

        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            //Open a transaction
            session.beginTransaction();

            stores = session.createQuery(""
                    + "SELECT new com.jms.model.Store(m.NomM, m.RueM, m.VilleM, "
                    + "m.CodePostalM) "
                    + "FROM Magasin m").list();
        }
        
        return stores;
    }
}
