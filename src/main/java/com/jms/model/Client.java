/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.model;


import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;

/**
 * Client Class
 * @author JerryMouseSoftware
 */
@Entity(name="Client")
public class Client implements Serializable {
    //------------ Properties ------------
    
    /**
     * Designation: Unique code identifying a customer
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CodeCL")
    private int code;
    
    /**
     * Designation: Last name of a customer
     */
    @Column(name="NomCL")
    private String lastName;
    
    /**
     * Designation: First name of a customer
     */
    @Column(name="PrenomCL")
    private String firstName;
    
    /**
     * Designation: Email of a customer
     */
    @Column(name="AdresseMailCL")
    private String email;
    
    /**
     * Designation: Password of a customer
     */
    @Column(name="MotDePasseCL")
    private String password;
    
    /**
     * Designation: Fidelity Points of a customer
     */
    @Column(name="PointsFideliteCL")
    private int fidelityPoints;
    
    /**
     * Designation: A REMPLIR!!!!
     */
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<ShoppingList> shoppingList = new HashSet(0);
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "CodeM")
    private Store store;
    
    @OneToMany(mappedBy= "client", cascade = CascadeType.ALL)
    @MapKeyJoinColumn(name="EANP")
    private Map<Product, Basket> baskets = new HashMap(0);
    
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @MapKeyJoinColumn(name="EANP")
    private Set<Order> orders = new HashSet(0);
    
    // -------------------- CONSTRUCTORS --------------------

    public Client() {
    }

    public Client(String lastName, String firstName, String email, String password, int fidelityPoints) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.fidelityPoints = fidelityPoints;
    }
    

    // ----------------- GETTERS & SETTERS ------------------
    /**
     * Getter for the property code.
     * @return the code property.
     */
    public int getCode() {
        return code;
    }
    
    /**
     * Setter for the property code.
     * @param code The new value to set to the property.
     */
    public void setCode(int code) {
        this.code = code;
    }
    
    /**
     * Getter for the property lastName.
     * @return the lastName property.
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Setter for the property lastName.
     * @param lastName The new value to set to the property.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * Getter for the property firstName.
     * @return the firstName property.
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Setter for the property firstName.
     * @param firstName The new value to set to the property.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Getter for the property email.
     * @return the email property.
     */
    public String getEmail() {
        return email;
    }
    
    /**
    * Setter for the property email.
     * @param email The new value to set to the property.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Getter for the property password.
     * @return the password property.
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Setter for the property password.
     * @param password The new value to set to the property.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Getter for the property fidelityPoints.
     * @return the fidelityPoints property.
     */
    public int getFidelityPoints() {
        return fidelityPoints;
    }
    
    /**
     * Setter for the property fidelityPoints.
     * @param fidelityPoints The new value to set to the property.
     */
    public void setFidelityPoints(int fidelityPoints) {
        this.fidelityPoints = fidelityPoints;
    }
    

    // ----------------------- METHODS ----------------------
    /**
     * Method which converts the current object into a String object.
     */
    @Override
    public String toString() {
        return "Client{" + "code=" + code + ", lastName=" + lastName + ", firstName=" + firstName + ", email=" + email + ", password=" + password + ", fidelityPoints=" + fidelityPoints + '}';
    }
    
    /**
     * Method which returns the hash code of the current object id.
     * @return The hash code of the current object id.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + this.code;
        return hash;
    }
    
    /**
     * Method which compares the current object with another one.
     * @param obj The object to compare with.
     * @return <b>True</b> if both objects are equals, <b>False</b> else.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        if (this.code != other.code) {
            return false;
        }
        return true;
    } 
}
