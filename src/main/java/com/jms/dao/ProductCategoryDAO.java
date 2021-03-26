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
    
    public static List<ProductCategory> getProductCategoriesByDepartmentId(int departmentId) {
        List<ProductCategory> categories;

        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            Query query = session.createQuery(
                  "FROM CategorieProduit c "
                + "WHERE c.department.id = :departmentId "
                + "ORDER BY c.name"
            );

            query.setParameter("departmentId", departmentId);

            categories = query.list();
        }

        return categories;
    }
    
    public static void main(String[] args) {
        for(ProductCategory c : getProductCategoriesByDepartmentId(1)) {
            System.out.println(c);
        }
    }
}
