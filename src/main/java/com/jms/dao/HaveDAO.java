/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;

import com.jms.model.Have;
import com.jms.model.TimeSlot;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author RAKOTOARISOA
 */
public class HaveDAO {
    /*----- Date Format -----*/
    private static final String format = "yyyy-MM-dd";
    private static final SimpleDateFormat DF = new SimpleDateFormat(format);
    
    /**
     * 
     */
    public static void create(String timeSlot) throws ParseException {
        //Open a session
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            //Open a transaction
            Transaction t = session.beginTransaction();
            
            Have d = new Have(DF.parse(timeSlot));

            session.save(d);

            t.commit();
        }
    }
   
    
     public static void initialize() throws ParseException {
        //Open a session
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            //Open a transaction
            Transaction t = session.beginTransaction();
            
            t.commit();
        }
     }
    /**
     * 
     * @param storeId
     * @param datePickUp
     * @return
     */
    public static List<Have> getTimeSlotsByStoreId(int storeId, Date datePickUp){
        //Open a session
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            //Open a transaction
            Transaction t = session.beginTransaction();
            Query query = session.createQuery("select new Have(h.date,h.capacity, h.store, h.timeSlot) "
                    + "from CreneauHoraire c , Avoir h"
                    + "where h.timeSlot.startTime = c.startTime "
                    + "and h.store.id = :storeId "
                    + "and h.date = :datePickUp");

            query.setParameter("storeId", storeId);
            query.setParameter("datePickUp", datePickUp);
            
            return query.list();
        }
    } 
     
}
