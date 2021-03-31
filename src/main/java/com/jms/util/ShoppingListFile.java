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
public class ShoppingListFile {

    /**
     * Read and turn each line of file into a PostIt
     *
     * @param fileList
     */
    public static Set<PostIt> importFromFile( File fileList) {
        Set<PostIt> postIts = new HashSet<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileList));
            String ligne;
            int nbLigne = 1;
            while ((ligne = br.readLine()) != null) {
                System.out.println(ligne);
                postIts.add(new PostIt(nbLigne, ligne));
                nbLigne++;  
            }
            
            
            System.out.println(nbLigne);
//            for (PostIt postIt : postIts) {
//                System.out.println(postIt);
//            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return postIts;
    }
    
    public static ShoppingList addPostIt(String titleList, Set<PostIt> postIts, File fileList ){
        
        postIts = importFromFile(fileList);
        ShoppingList fileImported =  new ShoppingList(titleList,postIts);
        return fileImported;
    }

    public static void main(String[] args) {
        File list = new File("listeShopping.txt");
        Set<PostIt> postIts =  importFromFile(list);
        
        ShoppingList s = addPostIt("Liste apéro",postIts,list);
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
