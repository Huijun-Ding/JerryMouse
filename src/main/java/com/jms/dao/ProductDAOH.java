/*
 * Copyright 2021 mlk.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jms.dao;

import com.jms.model.Product;
import com.jms.model.ProductConditioning;
import com.jms.model.ProductNutriScore;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Jerry Mouse Software
 */
public class ProductDAOH {

    // ----------------------- METHODS ----------------------
    /**
     * Gets all the information of a product from its EAN.
     *
     * @param id the ean of the product.
     */
    public static void getProduct(String id) {
        /*----- Session opening -----*/
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();

            //System.out.println("--------- GET ONE PRODUCT FROM ID ");
            Product prod = session.get(Product.class, id);
            //System.out.println(prod);
        }
    }
    
    /**
     * Gets all the information of a product.
     *
     * @param prod the given product.
     */
    public static Product getAll(Product prod) {
        /*----- Session opening -----*/
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();

            //System.out.println("--------- GET ONE PRODUCT FROM ID ");
            prod = session.get(Product.class, prod.getEan());
            //System.out.println(prod);
            return prod;
        }
    }

    /**
     * Gets all the products in the catalog.
     * @return the list of the products.
     */
    public static List<Product> getAllProducts() {
        List<Product> list;
        /*----- Session opening -----*/
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();

            //System.out.println("--------- GET ALL PRODUCTS ");
            
            String sql = 
                    "SELECT new com.jms.model.Product(p.ean, p.name, p.format, "
                    + "p.nutriscore, p.packaging, p.packagingQuantity, "
                    + "p.unitPrice, p.kgPrice, p.urlThumbnail) "
                    + "FROM Produit p LEFT OUTER JOIN p.labels l ";
            list = session.createQuery(sql).list();
            return list;
        } 
    }
    
    /**
     * Gets all the products in the catalog with all info.
     * @return the list of the products.
     */
    public static List<Product> getAllProductsWithAllInfo() {
        List<Product> list;
        /*----- Session opening -----*/
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();

            //System.out.println("--------- GET ALL PRODUCTS ");
            
            String sql = 
                    "SELECT new com.jms.model.Product(p.ean, p.name, p.format, "
                    + "p.nutriscore, p.packaging, p.packagingQuantity, "
                    + "p.unitPrice, p.kgPrice, p.urlThumbnail) "
                    + "FROM Produit p LEFT OUTER JOIN p.labels l ";
            list = session.createQuery(sql).list();
            return list;
        } 
    }
    
    
    /**
     * Gets the products of a category from its id.
     * @param id the id of the category.
     * @return the list of products.
     */
    public static List<Product> getProductsOfCategory(int id) {
        List<Product> list;
        /*----- Session opening -----*/
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();

            //System.out.println("--------- GET PRODUCTS OF A CATEGORY ");
            
            String sql = 
                    "SELECT new com.jms.model.Product(p.ean, p.name, p.format, "
                    + "p.nutriscore, p.packaging, p.packagingQuantity, "
                    + "p.unitPrice, p.kgPrice, p.urlThumbnail) "
                    + "FROM Produit p "
                    + "WHERE p.category.id = :id";
            
            Query query = session.createQuery(sql);
            query.setParameter("id", id);
            list = query.list();
            
            return list;
        }
    }
    
    /**
     * Gets the products of a department from its id.
     * @param id the id of the department.
     * @return the list of products.
     */
    public static List<Product> getProductsOfDepartment(int id) {
        List<Product> list;
        /*----- Session opening -----*/
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();

            //System.out.println("--------- GET PRODUCTS OF A DEPARTMENT ");
            
            String sql = 
                    "SELECT new com.jms.model.Product(p.ean, p.name, p.format, "
                    + "p.nutriscore, p.packaging, p.packagingQuantity, "
                    + "p.unitPrice, p.kgPrice, p.urlThumbnail) "
                    + "FROM Produit p "
                    + "WHERE p.category.department.id = :id";
            Query query = session.createQuery(sql);
            query.setParameter("id", id);
            list = query.list();
            return list;
        }
    }
    
    /** 
     * Gets the products currently with promotions.
     * @return the list of the products.
     */
    public static List<Product> getProductsWithPromo() {
        
        /*----- Session opening -----*/
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();

            //System.out.println("--------- GET PRODUCTS CURRENTLY IN PROMOTION");
            
            String sql = 
                    "SELECT new com.jms.model.Product(p.ean, p.name, p.format, "
                    + "p.nutriscore, p.packaging, p.packagingQuantity, "
                    + "p.unitPrice, p.kgPrice, p.urlThumbnail, "
                    + "pr.id, pr.percentage, pr.rank) "
                    + "FROM Reduire r "
                    + "JOIN r.product p "
                    + "JOIN r.promotion pr "
                    + "LEFT OUTER JOIN p.labels "
                    + "WHERE r.promoEndDate >= current_date "
                    + "ORDER BY p.unitPrice";
            Query query = session.createQuery(sql);
            List<Product> list = query.list();
            //session.close();
            return list;
        }
    }
    
    public static List<Product> getLabelsOfProducts(List<Product> list) {
        /*----- Session opening -----*/
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();

            //System.out.println("--------- GET PRODUCTS CURRENTLY IN PROMOTION");
            
            String sql = 
                    "SELECT new com.jms.model.Product(p.ean, p.name, p.format, "
                    + "p.nutriscore, p.packaging, p.packagingQuantity, "
                    + "p.unitPrice, p.kgPrice, p.urlThumbnail, "
                    + "pr.id, pr.percentage, pr.rank) "
                    + "FROM Reduire r "
                    + "JOIN r.product p "
                    + "JOIN r.promotion pr "
                    + "LEFT OUTER JOIN p.labels "
                    + "WHERE r.promoEndDate >= current_date "
                    + "ORDER BY p.unitPrice";
            Query query = session.createQuery(sql);
            
            List<Product> listLabel = query.list();
            //session.close();
            return list;
        }
    }
    
}
