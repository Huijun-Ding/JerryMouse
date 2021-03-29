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
    private static String format = "hh:mm";
    private static final SimpleDateFormat DF = new SimpleDateFormat(format);
    
    /**
     *  
     * @param startTime
     * @param storeId
     * @throws ParseException 
     */
    public static void create(String startTime, int storeId) throws ParseException {
        //Open a session
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            //Open a transaction
            Transaction t = session.beginTransaction();
            
           
            HaveId d = new HaveId(DF.format(startTime),storeId);

            t.commit();
        }
    }
    

    
    public static void main (String[] args) throws ParseException{
        
    }
}
