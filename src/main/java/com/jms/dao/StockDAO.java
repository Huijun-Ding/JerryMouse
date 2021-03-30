/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Jerry Mouse Software.
 */
public class StockDAO {
    public static boolean checkStockProd(int idStore, String ean, int qte) throws SQLException{
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Query query = session.createSQLQuery("Select QteStock from Stocker where CodeP = :ean and CodeM= :idStore");

            query.setParameter("ean", ean);
            query.setParameter("idStore", idStore);
            int qteStock = Integer.valueOf(query.list().get(0).toString());
            
            Boolean res = true;
            if (qte > qteStock) res = false;
            
            t.commit(); // Commit et flush automatique de la session.
            return res;
        }
    }
    
    public static void main(String[] args) {
        try {
            System.out.println(StockDAO.checkStockProd(1, "P1", 2));
        } catch (SQLException ex) {
            Logger.getLogger(StockDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
