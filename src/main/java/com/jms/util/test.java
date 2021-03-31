/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.util;

/**
 *
 * @author Jerry Mouse Software.
 */
public class test {
    public static void main(String[] args) throws Exception {
    /**
     * receiveMailAccount : address of recipient  
     */
     String receiveMailAccount = "18810952622@163.com";
     String contenue="test class";

        EmailUtil e= new EmailUtil(receiveMailAccount,contenue,"Chloé");
    }
}
