/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;

import com.jms.model.Have;
import com.jms.model.HaveId;
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
public class HaveIdDAO {
    
    /*----- Date Format -----*/
    private static String format = "dd/MM/yyyy";
    private static final SimpleDateFormat DF = new SimpleDateFormat(format);
    
    
    public static void create(String startTime, int storeId) throws ParseException {
        //Open a session
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            //Open a transaction
            Transaction t = session.beginTransaction();
            
           
            HaveId d = new HaveId(DF.parse(startTime),storeId);

            session.save(d);

            t.commit();
        }
    }
    
    /**
     * 
     * @param storeId
     * @param datePickUp
     * @return
     * @throws ParseException 
     */
    public static List<TimeSlot> getTimeSlotsByStoreId(int storeId, Date datePickUp) throws ParseException {
        //Open a session
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            //Open a transaction
            Transaction t = session.beginTransaction();

            Query query = session.createQuery("select new TimeSlot(c.startTime, c.endTime) "
                    + "from CreneauHoraire c , Avoir h"
                    + "where h.timeSlot.startTime = c.startTime "
                    + "and h.store.id = :storeId "
                    + "and h.date = :datePickUp");

            query.setParameter("storeId", storeId);
            query.setParameter("datePickUp", datePickUp);
            
            return query.list();
        }
    }
    
    public static void main (String[] args) throws ParseException{
        
        HaveIdDAO.getTimeSlotsByStoreId(22, DF.parse("26/03/2021"));
    }
}
