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
     *
     * @throws ParseException
     */
    public static void create(String startTime, String endTime) throws ParseException {
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
     *
     */
    public static void initialize() throws ParseException {
        //Open a session
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            //Open a transaction
            Transaction t = session.beginTransaction();

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

            t.commit();
        }
    }
    
    
    
    public static void main(String[] args) throws ParseException {
        /*int minutes = 0;
        for (int i = 7; i < 21; i++) {
            String startMinutes = (minutes % 2 == 0 ? "00" : "30");
            String endMinutes = (minutes % 2 != 0 ? "00" : "30");
            
            String startHours = "";
            
            String endHours;
            if(startMinutes.equals("00") && endMinutes.equals("30")) {
                startHours = (i < 10) ? "0" + i : "" + i;
                endHours = (i + 1 < 10) ? "0" + (i + 1) : "" + (i + 1);
            } else {
                startHours = (i < 10) ? "0" + i : "" + i;
                endHours = (i < 10) ? "0" + i : "" + i;
            }
                
            minutes++;
            System.out.println("\"" + startHours + ":" + startMinutes + "\", \"" + endHours + ":" + endMinutes + '"');
        }*/
        
        //TimeSlotDAO.initialize();
        
        
        
    }
}
