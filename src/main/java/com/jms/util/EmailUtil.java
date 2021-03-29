/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.util;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author Jerry Mouse Software.
 */
public class EmailUtil {

    /**
     * myEmailAccount : account of sender email
     */
    private String myEmailAccount = "jerrymouse.sw@gmail.com";
    /**
     * myEmailPassword : password for the sender email
     */
    private String myEmailPassword = "#jms06IPM";
    /**
     * myEmailSMTPHost : address of server SMTP
     */
    private String myEmailSMTPHost = "smtp.gmail.com";
    /**
     * receiveMailAccount : address of recipient
     */
    private String receiveMailAccount;
    /**
     * receiveMailName : name of recipient
     */
    private String receiveMailName;
    /**
     * contentMail : content of mail
     */
    private String contentMail;

    /**
     * A constructor of the Email Class.
     *
     * @param receiveMailAccount address of recipient.
     * @param contentMail content of mail.
     */
    public EmailUtil(String receiveMailAccount, String contentMail,String receiveMailName) throws Exception {
        // configuration of the parameters used to connect to the mail server
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", myEmailSMTPHost);
        // request authentication
        props.setProperty("mail.smtp.auth", "true");

        //Enable SSL secure connection
        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);

        //session used to interact with the mail server
        Session session = Session.getInstance(props);
        //Set in debug mode, view detailed send log
        session.setDebug(true);

        //create a email
        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount, contentMail,receiveMailName);

        //Get session-based mail forwarding subject
        Transport transport = session.getTransport();

        //Connection to the mail server
        transport.connect(myEmailAccount, myEmailPassword);

        // Send email 
        transport.sendMessage(message, message.getAllRecipients());
        // close the server
        transport.close();
    }

    /**
     * Create an email
     *
     * @param session Sessions that interact with the server
     * @param sendMail Sender mailbox
     * @param receiveMail Recipient mailbox
     * @param content
     * @return MimeMessage
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail, String content,String receiveMailName) throws Exception {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendMail, "JerryMouseSoftware", "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, receiveMailName, "UTF-8"));
        message.setSubject("Facture", "UTF-8");
        message.setContent(content, "text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }

    /**
     * Getter for the myEmailAccount property.
     *
     * @return The myEmailAccount property.
     */
    public String getMyEmailAccount() {
        return myEmailAccount;
    }

    /**
     * Setter for the myEmailAccount property.
     *
     * @param myEmailAccount The new value to set to the property.
     */
    public void setMyEmailAccount(String myEmailAccount) {
        this.myEmailAccount = myEmailAccount;
    }

    /**
     * Getter for the myEmailPassword property.
     *
     * @return The myEmailPassword property.
     */
    public String getMyEmailPassword() {
        return myEmailPassword;
    }

    /**
     * Setter for the myEmailPassword property.
     *
     * @param <nom_de_la_propriété> The new value to set to the property.
     */
    public void setMyEmailPassword(String myEmailPassword) {
        this.myEmailPassword = myEmailPassword;
    }

    /**
     * Getter for the myEmailSMTPHost property.
     *
     * @return The myEmailSMTPHost property.
     */
    public String getMyEmailSMTPHost() {
        return myEmailSMTPHost;
    }

    /**
     * Setter for the myEmailSMTPHost property.
     *
     * @param myEmailSMTPHost The new value to set to the property.
     */
    public void setMyEmailSMTPHost(String myEmailSMTPHost) {
        this.myEmailSMTPHost = myEmailSMTPHost;
    }

    /**
     * Getter for the receiveMailAccount property.
     *
     * @return The receiveMailAccount property.
     */
    public String getReceiveMailAccount() {
        return receiveMailAccount;
    }

    /**
     * Setter for the receiveMailAccount property.
     *
     * @param <nom_de_la_propriété> The new value to set to the property.
     */
    public void setReceiveMailAccount(String receiveMailAccount) {
        this.receiveMailAccount = receiveMailAccount;
    }

    /**
     * Getter for the contentMail property.
     *
     * @return The contentMail property.
     */
    public String getContentMail() {
        return contentMail;
    }

    /**
     * Setter for the contentMail property.
     *
     * @param <nom_de_la_propriété> The new value to set to the property.
     */
    public void setContentMail(String contentMail) {
        this.contentMail = contentMail;
    }

}
