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
        String myEmailAccount = "jerrymouse.sw@gmail.com";
    /**
     * myEmailPassword : password for the sender email
     */
    String myEmailPassword = "#jms06IPM";
    /**
     * myEmailSMTPHost : address of server SMTP 
     */
    String myEmailSMTPHost = "smtp.gmail.com";
    /**
     * receiveMailAccount : address of recipient  
     */
     String receiveMailAccount = "18810952622@163.com";
     String contenue="test class";

        Email e= new Email(myEmailAccount,myEmailPassword,myEmailSMTPHost,receiveMailAccount,contenue);
    }
}
