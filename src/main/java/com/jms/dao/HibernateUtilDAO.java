/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Mathi
 */
public class HibernateUtilDAO {

    private static final SessionFactory SESSION_FACTORY;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            System.out.println("Hibernate Configuration loaded");

            /**
             * Ajout des classes. Pour com.jms.Client le fichier ressource
             * hbm.xml attaché est com/jms/Client.hbm.xml.
             */
//			configuration.addClass(com.jms.model.Client.class);
           
            /**
             * Entité.
             */
//            configuration.addAnnotatedClass(com.jms.model.Client.class);
            configuration.addAnnotatedClass(com.jms.model.Client.class);
            configuration.addAnnotatedClass(com.jms.model.ShoppingList.class);
            configuration.addAnnotatedClass(com.jms.model.PostIt.class);


            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate serviceRegistry created");

            SESSION_FACTORY = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException ex) {
            /*----- Exception -----*/
            System.err.println("Initial SessionFactory creation failed.\n" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

}
