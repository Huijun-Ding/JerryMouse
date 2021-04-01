/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;

import com.jms.model.Client;
import com.jms.model.Product;

/**
 *
 * @author mlk
 */
public class TestHibernatePreferenceDAO {

    public static void main(String[] args) {

        Client client = ClientDAO.load(2);

        System.out.println("---- CLIENT ----");
        //System.out.println(client);

        Client clientUp = PreferenceDAO.getFavoriteProducts(client);

        System.out.println("---- CLIENT UPDATED (OUT) ----");
        //System.out.println(clientUp.getFavoriteProducts());
        for (Product favoriteProduct : clientUp.getFavoriteProducts()) {
            System.out.println("" + favoriteProduct);
        }
    }
}
