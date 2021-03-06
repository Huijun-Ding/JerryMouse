/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;

import com.jms.model.TimeSlot;
import static java.lang.String.format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author RAKOTOARISOA
 */
public class TimeSlotDAO {

    /*----- TimseSlot Format -----*/
    private static final SimpleDateFormat DF = new SimpleDateFormat("hh:mm");

    /**
     * Create a timeslot
     * @param startTime the start of the timeslot
     * @param endTime the end of the timeslot
     * @throws ParseException
     */
    public static void create(String startTime, String endTime)  {
        //Open a session
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            //Open a transaction
            Transaction t = session.beginTransaction();

            TimeSlot t1 = new TimeSlot(startTime, endTime);

            session.save(t1);

            t.commit();
        }
    }
    
    /**
     * Get the timeslot by start time
     * @param startTime the start of the timeslot
     * @return 
     */
    public static TimeSlot get(String startTime) {
        //Open a session
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            //Open a transaction
            Transaction t = session.beginTransaction();
            return session.get(TimeSlot.class, startTime);
        }
    }
    
    /**
     *Create all time slots 
     */
    public static void initialize() {
        

            create("07:30", "08:00");
            create("08:00", "08:30");
            create("08:30", "09:00");
            create("09:00", "09:30");
            create("09:30", "10:00");
            create("10:00", "10:30");
            create("10:30", "11:00");
            create("11:00", "11:30");
            create("11:30", "12:00");
            create("12:00", "12:30");
            create("12:30", "13:00");
            create("13:00", "13:30");
            create("13:30", "14:00");
            create("14:00", "14:30");
            create("14:30", "15:00");
            create("15:00", "15:30");
            create("15:30", "16:00");
            create("16:00", "16:30");
            create("16:30", "17:00");
            create("17:00", "17:30");
            create("17:30", "18:00");
            create("18:00", "18:30");
            create("18:30", "19:00");
            create("19:00", "19:30");
            create("19:30", "20:00");
            create("20:00", "20:30");
            create("20:30", "21:00");

            
        
    }
    
    public static void main(String[] args) {
        //TimeSlotDAO.initialize();
    }
}
