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
 * StockDAO Class.
 * @author Jerry Mouse Software.
 */
public class StockDAO {
    /**
     * Check the stock of a product in a store.
     * @param idStore id of store.
     * @param ean id of product.
     * @param qte quantity of a product in the basket.
     * @return boolean.
     * @throws SQLException 
     */
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
    
    /**
     * Update the stock of a product in a store.
     * @param idStore id of store.
     * @param ean id of a product.
     * @param qte new quantity of stock of a product.
     * @return int.
     * @throws SQLException 
     */
    public static int updateStockProd(int idStore, String ean, int qte) throws SQLException{
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE Stocker "
                    + "SET QteStock = QteStock - :qte "
                    + "WHERE CodeP = :ean "
                    + "AND CodeM = :idStore");

            query.setParameter("qte", qte);
            query.setParameter("ean", ean);
            query.setParameter("idStore", idStore);
            
            int nb = query.executeUpdate();
            t.commit(); // Commit et flush automatique de la session.
            return nb;
        }
    }
    
    public static void main(String[] args) {
        try {
            System.out.println(StockDAO.checkStockProd(40, "P1", 2));
            System.out.println(StockDAO.updateStockProd(40, "P1", 2));
        } catch (SQLException ex) {
            Logger.getLogger(StockDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
