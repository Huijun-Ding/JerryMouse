/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;

import com.jms.model.Store;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * StoreDAO Class.
 * @author Jerry Mouse Software.
 */
public class StoreDAO {

    // ----------------------- METHODS ----------------------

    public static void initialize() {
        StoreDAO.create("Drive Intermarché - Toulouse Saint Cyprien","3-5, Rue Villeneuve","Toulouse","31000");
        StoreDAO.create("Auchan Drive - Toulouse Charles-de-Fitte","16, Allée Charles de Fitte","Toulouse","31000");
        StoreDAO.create("Monoprix - Toulouse","39, Rue Alsace Lorraine","Toulouse","31000");
        StoreDAO.create("Carrefour Drive - Toulouse Lombez","41, Avenue de Lombez","Toulouse","31000");
        StoreDAO.create("Casino Drive - Toulouse Saint Georges","51, Boulevard Lazare Carnot","Toulouse","31000");
        StoreDAO.create("Drive Intermarché - Toulouse Strasbourg","4, Boulevard de Strasbourg","Toulouse","31000");
        StoreDAO.create("Casino Drive - Toulouse Honore Serres","37 Bis, Avenue Honoré Serres","Toulouse","31000");
        StoreDAO.create("Casino Drive - Toulouse La Cepière","389, Route de Saint Simon","Toulouse","31000");
        StoreDAO.create("Carrefour Drive - Toulouse Jean Rieux","57, Avenue Jean Rieux","Toulouse","31000");
        StoreDAO.create("Casino Drive - Toulouse Bonnefoy","25, Rue du Faubourg Bonnefoy","Toulouse","31000");
        StoreDAO.create("Casino Drive - Toulouse Pont des Demoiselles","2-4, Boulevard Griffoul Durval et 1, Avenue St Exupéry","Toulouse","31000");
        StoreDAO.create("Casino Drive - Toulouse Minimes","178, Avenue des Minimes","Toulouse","31000");
        StoreDAO.create("Carrefour Drive - Toulouse Trentin Minimes","83, Boulevard Sylvio Trentin","Toulouse","31000");
        StoreDAO.create("Drive Intermarché - Toulouse Lapujade","111, Chemin de Lapujade","Toulouse","31000");
        StoreDAO.create("Carrefour Drive - Toulouse Purpan","Centre Commercial Purpan","Toulouse","31000");
        StoreDAO.create("E. Leclerc Drive - Montaudran","170 avenue de Lespine","Toulouse","31000");
        StoreDAO.create("Carrefour Drive - Toulouse L'Hers","6, Rue Claudius Rougenet","Toulouse","31000");
        StoreDAO.create("Casino Drive - Toulouse Basso Cambo","Place Edouard Bouillière","Toulouse","31000");
        StoreDAO.create("Drive Intermarché - Balma","31 avenue des Arènes","Balma","31130");
        StoreDAO.create("Drive Intermarché - Toulouse Lardenne","30, Chemin Ferro-Lèbres","Toulouse","31000");
        StoreDAO.create("Carrefour Drive - Toulouse Seysses","138bis, Route de Seysses","Toulouse","31000");
        StoreDAO.create("Auchan Drive - Toulouse","Chemin de Gabardie","Toulouse","31075");
        StoreDAO.create("Chronodrive - Toulouse Basso Cambo","16 Avenue du Docteur Grynfogel","Toulouse","31000");
        StoreDAO.create("Chronodrive - Toulouse Lalande","46 Avenue Jean Zay","Toulouse","31000");
        StoreDAO.create("E. Leclerc Drive - Balma","90, Route de Castres","Balma","31130");
        StoreDAO.create("Carrefour Drive - Colomiers Ramassier","24, Espanade des Ramassiers","Colomiers","31770");
        StoreDAO.create("Drive Intermarché - L'Union","42, Route de Bessières","L'Union","31240");
        StoreDAO.create("Auchan Drive - Launaguet","Rue Benjamin Franklin","Launaguet","31140");
        StoreDAO.create("E. Leclerc Drive - Blagnac","2 Allée Emile Zola","Blagnac","31700");
        StoreDAO.create("Casino Drive - Toulouse L'Union","Lotissement Saint Caprais - RN 88","Toulouse","31000");
        StoreDAO.create("Carrefour Drive - Portet sur Garonne","Boulevard de l'Europe","Portet-sur-Garonne","31120");
        StoreDAO.create("Drive Intermarché - Saint Jean","6 avenue de Dancelle Centre Commercial Val Dancelle","Saint-Jean","31240");
        StoreDAO.create("Casino Drive - Toulouse Blagnac","15, Avenue d'Andromède","Blagnac","31700");
        StoreDAO.create("Drive Intermarché - Ramonville Saint Agne","1 rue Louis Braille","Ramonville-Saint-Agne","31520");
        StoreDAO.create("Chronodrive - Ramonville","2 Avenue des Crêtes","Ramonville-Saint-Agne","31520");
        StoreDAO.create("Carrefour Drive - Labège","Centre Commerciale Labège II","Labège","31670");
        StoreDAO.create("Courses U Drive - Colomiers","Boulevard de Gascogne - Centre Commercial Plein Centre","Colomiers","31770");
        StoreDAO.create("Chronodrive - Labège","16, Place du Lauragais","Labège","31670");
        StoreDAO.create("Carrefour Drive - Colomiers - Ormeau","4, Chemin de L'Ormeau","Colomiers","31770");
        StoreDAO.create("Auchan Drive - Fonbeauzard Aucamville","102, Route de Fronton","Fonbeauzard","31140");
        StoreDAO.create("Courses U Drive - Fonbeauzard","121, Route de Bessières","Fonbeauzard","31140");
        StoreDAO.create("Carrefour Drive - Beauzelle","144, Avenue Garossos","Beauzelle","31700");
        StoreDAO.create("Carrefour Drive - Tournefeuille","Route de Tarbes","Tournefeuille","31170");
    }
    
    public static void create(String name, String street, String city,
            String postalCode) {
        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();

            session.save(new Store(name, street, city, postalCode));

            t.commit();
        }
    }

    /**
     * Get all stores by postal code.
     * @param postalCode The postal code of the reseaching stores.
     * @return List of stores having 'postalCode' as postal code.
     */
    public static List<Store> getAllStores(String postalCode) {
        List<Store> stores;

        try (Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            Query query = session.createQuery(
                "FROM Magasin m "
                + "WHERE m.postalCode LIKE :postalCode"
            );

            query.setParameter("postalCode", postalCode + "%");

            stores = query.list();
        }

        return stores;
    }

    public static void main(String[] args) {
        for(Store s : StoreDAO.getAllStores("311")) {
            System.out.println(s);
        }
    }
}
