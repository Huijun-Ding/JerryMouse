/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;

import com.jms.model.Basket;
import com.jms.model.Promotion;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Jerry Mouse Software.
 */
public class PromotionDAO {

    public static Promotion searchPromotion(String ean) throws SQLException {
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Promotion promotion = null;
            Query query = session.createSQLQuery("Select Promotion.* "
                    + "from Reduire, Promotion "
                    + "where Reduire.CodePR = Promotion.CodePR "
                    + "and CURRENT_DATE() >= Reduire.DateDebutPromoP "
                    + "and CURRENT_DATE() <= Reduire.DateFinPromoP "
                    + "and Reduire.EANP = :ean").addEntity(Promotion.class);

            query.setParameter("ean", ean);
            if (query.list().size() > 0) {
                promotion = (Promotion) query.list().get(0);
            }
            t.commit();
            return promotion;
        }
    }

    //    public static float getPercentage(String ean){
//        /*----- Ouverture de la session -----*/
//        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
//            Transaction t = session.beginTransaction();
//            Query query = session.createSQLQuery("Select Promotion.PercentagePR"
//                    + "from Reduce, Promotion "
//                    + "where Reduce.CodePR = Promotion.CodePR"
////                    + "and unix_timestamp(now()) between unix_timestamp(Reduce.DateDebutPromoP) and unix_timestamp(Reduce.DateFinPromoP)"
//                    + "and CURRENT_DATE() >= Reduire.DateDebutPromoP "
//                    + "and CURRENT_DATE() <= Reduire.DateFinPromoP"
//
//                    + "and Reduce.EANP = :ean");
//
//            query.setParameter("ean", ean);
//
//            float percentage = (float)query.list().get(0);
//            
//            t.commit(); // Commit et flush automatique de la session.
//            return percentage;
//        }
//    }
    public static void main(String[] args) {
        try {
            System.out.println(PromotionDAO.searchPromotion("P3"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
