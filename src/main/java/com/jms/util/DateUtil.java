/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Mathi
 */
public class DateUtil {

    public static SimpleDateFormat ALL_DATE_IN_LETTERS = new SimpleDateFormat("EEE dd MMM yyyy");
    
    public static String dateOfToday() {
        Date date = new Date();  
        return ALL_DATE_IN_LETTERS.format(date);
    }
    
    public static String monthOfToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");
        Date date = new Date();
        String time = sdf.format(date);
        return time.toUpperCase().substring(0,1) + time.substring(1);
    }
    
    public static Date[] fourNextDays() {
        Date[] d = new Date[4];
        
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        
        d[0] = c.getTime();
        c.add(Calendar.DATE, 1);
        d[1] = c.getTime();
        c.add(Calendar.DATE, 1);
        d[2] = c.getTime();
        c.add(Calendar.DATE, 1);
        d[3] = c.getTime();
        c.add(Calendar.DATE, 1);
        
        return d;
    }
    
    public static void main(String[] args) {
        System.out.println(dateOfToday());
        System.out.println(monthOfToday());
        
        for(Date d : fourNextDays())
            System.out.println(ALL_DATE_IN_LETTERS.format(d));
    }
}
