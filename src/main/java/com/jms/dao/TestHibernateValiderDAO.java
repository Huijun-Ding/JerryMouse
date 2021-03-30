/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;

import com.jms.model.Client;
import com.jms.model.Have;
import com.jms.model.Store;
import com.jms.model.TimeSlot;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 *
 * @author mlk
 */
public class TestHibernateValiderDAO {
    public static void main(String[] args) throws ParseException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        
        
        //-------- TEST TO UPDATE ONE PRODUCT
        System.out.println("----- TEST TO REGISTER AN ORDER");
        Client client = ClientDAO.searchClient(2);
        Store store = StoreDAO.get(22);
        String startTime = "09:00";
        String endTime = "09:30";
        TimeSlot timeslot = new TimeSlot(startTime, endTime);
        Have haveTS = new Have();
        haveTS.setTimeSlot(timeslot);
        Date date = new Date();
        haveTS.setDate(df.parse("01-04-2021"));
        ValiderDAO.registerBasket(client, store, haveTS);
        
        System.out.println("Client : " + client);
        
    }    
}
