/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;

import com.jms.model.Product;
import com.jms.model.ProductNutriScore;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author mlk
 */
public class TestHibernateProductDAO {
    public static void main(String[] args) throws ParseException {
        
        //-------- TEST TO CREATE ONE PRODUCT
        System.out.println("----- TEST TO CREATE ONE PRODUCT");
        //ProductDAOH.createProduct();
        
        //-------- TEST TO GET ONE PRODUCT
        System.out.println("----- TEST TO GET ONE PRODUCT");
        ProductDAOH.getProduct("P12");
        
        //-------- TEST TO UPDATE ONE PRODUCT
        System.out.println("----- TEST TO UPDATE ONE PRODUCT");
//        ProductDAOH.updateNutriscoreProduct("P1", ProductNutriScore.A);
//        ProductDAOH.updateNutriscoreProduct("P10", ProductNutriScore.D);
//        ProductDAOH.updateNutriscoreProduct("P2", ProductNutriScore.E);
//        ProductDAOH.updateNutriscoreProduct("P4", ProductNutriScore.E);
//        ProductDAOH.updateNutriscoreProduct("P5", ProductNutriScore.E);
//        ProductDAOH.updateNutriscoreProduct("P7", ProductNutriScore.B);
//        ProductDAOH.updateNutriscoreProduct("P8", ProductNutriScore.E);
//        ProductDAOH.updateNutriscoreProduct("P9", ProductNutriScore.E);
        //ProductDAOH.getProduct("P12");
        
        
        //-------- TEST TO GET ALL PRODUCTS 
        System.out.println("----- TEST TO GET ALL PRODUCTS");
//        List<Product> list = ProductDAOH.getAllProducts();
//        for (Product product : list) {
//            System.out.println(product);
//        }
        
        //-------- TEST TO GET PRODUCTS WITH PROMOTIONS
        System.out.println("----- TEST TO GET PRODUCTS WITH PROMOTIONS");
        List<Product> listPromo = ProductDAOH.getProductsWithPromo();
        for (Product product : listPromo) {
            System.out.println(product);
        }
    }
}
