/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.util;

import com.jms.model.PostIt;
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
     * @param titleList
     * @param fileList
     */
    public static void readFile(String titleList, File fileList) {
        Set<PostIt> postIts = new HashSet<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileList));
            String ligne;
            int nbLigne = 0;
            //Ne pas lire le titre
            int numLigne = 1;
            while ((ligne = br.readLine()) != null) {
                System.out.println(ligne);
                nbLigne++;
                while (numLigne < nbLigne) {
                    postIts.add(new PostIt(ligne));
                    numLigne++;
                }
            }
            System.out.println(nbLigne);
            for (PostIt postIt : postIts) {
                System.out.println(postIt);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //readFile("Liste Apéro", new File("listeShopping.txt"));
        Set<PostIt> sets = new HashSet<>(0);
        PostIt p = new PostIt("Fraise");
        PostIt p1 = new PostIt("Pomme");
        
        System.out.println(sets.add(p));
        //return false car code=0 existe déja
        System.out.println(sets.add(p1));
        sets.add(p1);



    }
}
