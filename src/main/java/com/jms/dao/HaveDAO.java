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
 * @author JerryMouseSoftware
 */
public class HaveDAO {

    /*----- Date Format -----*/
    private static final String format = "yyyy-MM-dd";
    private static final SimpleDateFormat DF = new SimpleDateFormat(format);

    /**
     * Create and save a new Have object
     * @param startTime
     * @param storeId
     * @param date
     * @param capacity
     * @throws ParseException 
     */
    public static void create(String startTime, int storeId, String date, int capacity) throws ParseException {
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
    
    /**
     * Initialize the insertion into table Avoir
     * @throws ParseException 
     */
    public static void initialize() throws ParseException {
        int[] storeIds = new int[]{40, 28};

        for (int storeId : storeIds) {
            for (Date d : DateUtil.nextDays(8)) {
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

                    if (hour > 12 && hour <= 16) {
                        minCapacite = 0;
                        maxCapacite = 15;
                    } else if (hour > 16) {
                        minCapacite = 0;
                        maxCapacite = 20;
                    }

                    range = maxCapacite - minCapacite + 1;

                    create(hoursMinutes, storeId, DateUtil.yearMonthDayFormat(d), capacity);
                }
            }
        }

    }

    /**
     *Get the timeslot by idStore and the date
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
    /**
     * Get an object Have with the storeId, a date and a start time
     * @param storeId
     * @param datePickUp
     * @param startTime
     * @return 
     */
    public static Have getHave(int storeId, Date datePickUp, String startTime) {
        //Open a session
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
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
            if (!query.list().isEmpty()) {
                have = (Have) query.list().get(0);
            }
            t.commit();
            return have;

        }
    }
    /**
     * Decrease the capacity of a timeslot
     * @param have 
     */
    public static void decreaseTimeSlotAfterValidation(Have have) {
        //Open a session
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            //Open a transaction
            Transaction t = session.beginTransaction();

            int capacity = have.getCapacity() - 1;
            have.setCapacity(capacity);
            session.update(have);
                
            t.commit();
            session.close();
        }
    }

    public static void main(String[] args) throws ParseException {
        HaveDAO.initialize();
    }
}
