/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;

import com.jms.model.ProductCategory;
import com.jms.model.Store;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * CategorieDAO Class.
 * @author Jerry Mouse Software.
 */
public class ProductCategoryDAO {

    // ----------------------- METHODS ----------------------

    public static void initialize() {
        
    }
    
    public static void create(String description) {
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();

            session.save(new ProductCategory(description));

            t.commit();
        }
    }
    
    public static List<ProductCategory> getProductCategoriesByDepartment(String department) {
        List<ProductCategory> categories;

        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            Query query = session.createQuery(
                  "SELECT new com.jms.model.ProductCategory(c.name) "
                + "FROM CategorieProduit c, Rayon r "
                + "c.department.id = r.id "
                + "WHERE m.name LIKE :department"
            );

            query.setParameter("department", department + "%");

            categories = query.list();
        }

        return categories;
    }
}
