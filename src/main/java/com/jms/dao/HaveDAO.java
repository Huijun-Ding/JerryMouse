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
    public static void create(String startTime, int storeId, int capacity, String date) throws ParseException {
        //Open a session
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            //Open a transaction
            Transaction t = session.beginTransaction();

            HaveId haveId = new HaveId(startTime, storeId);
            Have d = new Have(haveId, capacity, DF.parse(date));

            session.save(d);

            t.commit();
        }
    }

    public static void initialize() throws ParseException {

        int minCapacite = 5;
        int maxCapacite = 20;
        int range = maxCapacite - minCapacite + 1;

        for (int storeId = 1; storeId <= 43; storeId++) {
            for (Date d : DateUtil.nextDays(4)) {
                int hour = 7;
                String minutes = "30";

                while (hour < 21) {
                    int capacity = (int) (Math.random() * range) + minCapacite;

                    String hoursMinutes = (hour < 10) ? "0" + hour + ":" + minutes : "" + hour + ":" + minutes;

                    if (minutes.equals("30")) {
                        hour++;
                    }
                    minutes = (minutes.equals("00")) ? "30" : "00";

                    create(hoursMinutes, storeId, capacity, DateUtil.yearMonthDayFormat(d));
                }
            }
        }

    }

    /**
     *
     * @param storeId
     * @param datePickUp
     * @return
     */
    public static List<Have> getTimeSlotsByStoreId(int storeId, Date datePickUp) {
        //Open a session
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
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

    public static void main(String[] args) throws ParseException {
        initialize();
        
    }
}
