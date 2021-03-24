/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.dao;
import com.jms.model.Client;
/**
 *
 * @author RAKOTOARISOA
 */
public class ClientDAO {
    
    /**
     * check if entered email and entered password are right
     * @param email
     * @param password 
     */
    public static boolean authenticate(String email, String password){
       
        Client client = new Client();

        if((email == client.getEmail())&&(password == client.getPassword())){
            return true;
        }else {
            return false;
        }
        
    }
}

