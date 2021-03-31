/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.util;

import com.jms.model.Client;
import com.jms.model.PostIt;
import com.jms.model.ShoppingList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author RAKOTOARISOA
 */
public class FileUtil {

    /**
     * Read and turn each line of file into a PostIt
     *
     * @param fileList
     */
    public static Set<PostIt> importPostItsFromFile(File fileList) {
        Set<PostIt> postIts = new HashSet<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileList));
            String ligne;
            while ((ligne = br.readLine()) != null) {
                postIts.add(new PostIt(ligne));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return postIts;
    }
    
    public static ShoppingList importShoppingListFromFile(String titleList, File file) {
        Set<PostIt> postIts = importPostItsFromFile(file);
        ShoppingList fileImported = new ShoppingList(titleList, postIts);
        
        for (PostIt p : postIts) {
            p.setShoppingList(fileImported);
        }
        
        return fileImported;
    }
    
    public static void main(String[] args) {
        File file = new File("listeShopping.txt");        
        ShoppingList s = importShoppingListFromFile("Liste apéro", file);
        System.out.println(s);
        System.out.println(s.getPostIts());

//        Set<PostIt> sets = new HashSet<>(0);
//        PostIt p = new PostIt("Fraise");
//        PostIt p1 = new PostIt("Pomme");
//        
//        System.out.println(sets.add(p));
//        //return false car code=0 existe déja
//        System.out.println(sets.add(p1));
//        sets.add(p1);
    }
}
