package com.jms.dao;

import com.jms.model.Basket;
import com.jms.model.Client;
import com.jms.model.Product;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Jerry Mouse Software.
 */
public class BasketDAO {

     /*----- Connexion -----*/
    private static Connection cx = null;

    /*----- Données de connexion -----*/
    private static final String URL = "jdbc:mysql://localhost:3307/db_21509053";
    private static final String LOGIN = "21509053";
    private static final String PASSWORD = "Q02MI0";


    /*----------*/
 /* Méthodes */
 /*----------*/
    /**
     * Crée la connexion avec la base de données.
     */
    private static void connexion() throws ClassNotFoundException, SQLException {
        /*----- Chargement du pilote pour la BD -----*/
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("pilot ok");
        } catch (ClassNotFoundException ex) {
            throw new ClassNotFoundException("Exception connexion() - Pilote MySql introuvable - " + ex.getMessage());
        }

        /*----- Ouverture de la connexion -----*/
        try {
            BasketDAO.cx = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("connection ok");
        } catch (SQLException ex) {
            throw new SQLException("Exception connexion() - Problème de connexion à la base de données - " + ex.getMessage());
        }
    }
    
    public static <T>List<T> ResultSetToBean(ResultSet resultSet, Class beanClass) throws Exception {
        // 获取Bean对象内的所有属性
        Field[] fields = beanClass.getDeclaredFields();
        List<T> beanList = new ArrayList<>();
        if (resultSet != null) {
            while (resultSet.next()) {
                // 每当有一行数据就创建一个Bean对象
                T object = (T) beanClass.newInstance();
                for (Field field : fields) {
                    String fieldName = field.getName();
                    // 利用字符串拼接，将属性名的首字母变为大写，获取对应的set方法。
                    Method setField = beanClass.getMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), field.getType());
                    // 利用MySQL数据库不区分大小写的性质获取对应字段的值。
                    setField.invoke(object,resultSet.getObject(fieldName.toUpperCase()));
                }
                beanList.add(object);
            }
        }
        return beanList;
    }


    public static ResultSet completeSearchBarByProductName() throws ClassNotFoundException, SQLException, Exception {
        /*----- Création de la connexion à la base de données -----*/
        if (BasketDAO.cx == null) {
            BasketDAO.connexion();
        }

        /*----- Interrogation de la base -----*/

        /*----- Requête SQL -----*/
        String sql = "SELECT * FROM Produit";
        //String sql = "SELECT LibelleP FROM Produit WHERE LibelleP LIKE ?";

        /*----- Ouverture de l'espace de requête -----*/
        try ( PreparedStatement st = BasketDAO.cx.prepareStatement(sql)) {
            /*----- Exécution de la requête -----*/
            
            ResultSet rs = st.executeQuery();
                /*----- Lecture du contenu du ResultSet -----*/
//            List<Product> prod = ResultSetToBean(rs, Product.class);
//            prod.forEach(System.out::println);
            return rs;     
            
        } catch (SQLException ex) {
            throw new SQLException("Exception completeSearchBarByProductName() : Problème SQL - " + ex.getMessage());
        }
        
    }
   
    
    // CONSULTATION PANIER
    public static List<Basket> loadBasket(int CodeCL) {
        /*----- Ouverture de la session -----*/
        try ( Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Query query = session.createQuery("from Panier where CodeCL = :code");

            query.setParameter("code", CodeCL);

            List<Basket> lstBasket = query.list();
           // lstBasket.forEach(System.out::println);
            
            t.commit(); // Commit et flush automatique de la session.
            return lstBasket;
        }
    }
    
    public static void main(String[] args) throws SQLException, Exception {
        if (BasketDAO.cx == null) {
            BasketDAO.connexion();
        }

        /*----- Interrogation de la base -----*/

        /*----- Requête SQL -----*/
        String sql = "SELECT * FROM Produit";
        //String sql = "SELECT LibelleP FROM Produit WHERE LibelleP LIKE ?";

        /*----- Ouverture de l'espace de requête -----*/
        try ( PreparedStatement st = BasketDAO.cx.prepareStatement(sql)) {
            /*----- Exécution de la requête -----*/
            
            ResultSet rs = st.executeQuery();
                /*----- Lecture du contenu du ResultSet -----*/
//            List<Product> prod = ResultSetToBean(rs, Product.class);
//            prod.forEach(System.out::println);
    
            ResultSetToBean(rs, Product.class);
        } catch (SQLException ex) {
            throw new SQLException("Exception completeSearchBarByProductName() : Problème SQL - " + ex.getMessage());
        }
              
    }
}
