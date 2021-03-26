/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;

import com.jms.model.Department;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * DepartmentDAO Class.
 * @author Jerry Mouse Software.
 */
public class DepartmentDAO {

    // ----------------------- METHODS ----------------------

    public static void initialize() {

    }

    /**
     * Get all departments from database.
     * @return All departments from database.
     */
    public static List<Department> getAll() {
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            return session.createQuery(""
                + "FROM Rayon r "
                + "ORDER BY r.name").list();
        }
    }

    public static Department get(int id) {
        Department d;
        
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            Query query = session.createQuery(""
                + "FROM Rayon r "
                + "WHERE r.id = :id "
                + "ORDER BY r.name ");
            
            query.setParameter("id", id);
            
            d = (Department) query.list().get(0);
        }
        
        return d;
    }
}