/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;

import com.jms.model.Have;
import com.jms.model.HaveId;
import com.jms.util.DateUtil;
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
    public static void create(String startTime, int storeId,String date, int capacity) throws ParseException {
        //Open a session
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            //Open a transaction
            Transaction t = session.beginTransaction();

            HaveId haveId = new HaveId(startTime, storeId, DF.parse(date));
            Have d = new Have(haveId, capacity);

            session.save(d);

            t.commit();
        }
    }

    public static void initialize() throws ParseException {
        int[] storeIds = new int[]{15, 19, 38, 40};
        
        for(int storeId : storeIds) {
            for (Date d : DateUtil.nextDays(4)) {
                int hour = 7;
                String minutes = "30";
                int minCapacite = 0;
                int maxCapacite = 10;
                int range = maxCapacite - minCapacite + 1;

                String hoursMinutes = "";
                while (!hoursMinutes.equals("20:30")) {
                    int capacity = (int) (Math.random() * range) + minCapacite;

                    hoursMinutes = (hour < 10) ? "0" + hour + ":" + minutes : "" + hour + ":" + minutes;

                    if (minutes.equals("30")) {
                        hour++;
                    }
                    minutes = (minutes.equals("00")) ? "30" : "00";

                    System.out.println(
                            hoursMinutes + " "
                            + storeId + " "
                            + capacity + " "
                            + DateUtil.yearMonthDayFormat(d)
                    );
                    
                    if(hour > 12 && hour <= 16) {
                        minCapacite = 0;
                        maxCapacite = 15;
                    } else if(hour > 16) {
                        minCapacite = 0;
                        maxCapacite = 20;
                    }

                   range = maxCapacite - minCapacite + 1;
                   
                   create(hoursMinutes, storeId,DateUtil.yearMonthDayFormat(d), capacity);
                }
            }
        }

    }

    /**
     *
     * @param storeId
     * @param date
     * @return
     */
    public static List<Have> getTimeSlotsByStoreId(int storeId, Date date) {
        //Open a session
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            //Open a transaction
            Transaction t = session.beginTransaction();

            Query query = session.createQuery(""
                    + "select new com.jms.model.Have(h.haveId, h.capacity, "
                    + "h.timeSlot) "
                    + "from CreneauHoraire c, Avoir h "
                    + "where h.timeSlot.startTime = c.startTime "
                    + "and h.store.id = :storeId "
                    + "and h.haveId.date = :date");

            query.setParameter("storeId", storeId);
            query.setParameter("date", date);

            return query.list();
        }
    }
    
    public static Have getHave(int storeId, Date datePickUp, String startTime){
        //Open a session
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            //Open a transaction
            Transaction t = session.beginTransaction();
            Query query = session.createQuery("from Avoir "
                    + "where HeureDebutCR = :startTime "
                    + "and CodeM = :storeId "
                    + "and DateCR = :datePickUp");
            
            query.setParameter("startTime", startTime);
            query.setParameter("storeId", storeId);
            query.setParameter("datePickUp", datePickUp);
            
            Have have = null;
            if(!query.list().isEmpty()){
                have = (Have)query.list().get(0);
            }
            t.commit();
            return have;
            
        }
    }

    public static void main(String[] args) throws ParseException {
        // HaveDAO.initialize();
        
    }
}
