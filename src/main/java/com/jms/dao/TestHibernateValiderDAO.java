/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;

import com.jms.model.Client;
import com.jms.model.Store;
import com.jms.model.TimeSlot;
import java.text.Format;
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;

/**
 *
 * @author mlk
 */
public class TestHibernateValiderDAO {
    public static void main(String[] args) throws ParseException {
        
        //-------- TEST TO UPDATE ONE PRODUCT
        System.out.println("----- TEST TO REGISTER AN ORDER");
        Client client = ClientDAO.searchClient(2);
        Store store = StoreDAO.get(22);
        Date startTime = Date.from(Instant.MIN);
        Date endTime = Date.from(Instant.MAX);
        TimeSlot timeslot = new TimeSlot(startTime, endTime);
        ValiderDAO.registerBasket(client, store, timeslot);
        
        System.out.println("Client : " + client);
        
    }    
}
