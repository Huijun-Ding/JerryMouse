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

    public static Date[] nextDays(int n) {
        Date[] d = new Date[n + 1];
        
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        for(int i = 0 ; i <= n ; i++) {
            d[i] = c.getTime();
            c.add(Calendar.DATE, 1);
        }

        return d;
    }

    public static void main(String[] args) {
        System.out.println(dateOfToday());
        System.out.println(monthOfToday());
        
        int i = 0;
        for(Date d : nextDays(20))
            System.out.println((i++) + " : " + ALL_DATE_IN_LETTERS.format(d));
    }
}
