/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.model;


import java.io.Serializable;
import java.util.ArrayList;
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
    //------------ PROPERTIES  ------------
    
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
    *  Hibernate join property with ShoppingList Class  and Client Class.
    */
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<ShoppingList> shoppingList = new HashSet(0);
    
    /**
    *  Hibernate join property with Store Class  and Client Class.
    */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "CodeM")
    private Store store;
    
    /**
    *  Hibernate join property with Product Class  and Client Class.
    */
    @OneToMany(mappedBy= "client", cascade = CascadeType.ALL)
    @MapKeyJoinColumn(name="EANP")
    private Map<Product, Basket> baskets = new HashMap(0);
    
    /**
    *  Hibernate join property with Order Class  and Client Class.
    */
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @MapKeyJoinColumn(name="EANP")
    private ArrayList<Order> orders = new ArrayList<>();
    
    // -------------------- CONSTRUCTORS --------------------
    /**
    * A constructor of the PostIt Class.
    */
    public Client() {
    }
    
    /**
    * A constructor of the PostIt Class.
    * @param lastName Last name of a customer.
    * @param firstName Fisrt name of a customer.
    * @param email Email of a customer.
    * @param password Password of a customer
    * @param fidelityPoints Fidelity Points of a customer
    */
    public Client(String lastName, String firstName, String email, String password, int fidelityPoints) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.fidelityPoints = fidelityPoints;
    }

    public Client(int code, String lastName, String firstName, String email, String password, int fidelityPoints) {
        this.code = code;
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
    
    /**
     * Getter for the property fidelityPoints.
     * @return the fidelityPoints property.
     */
    public Set<ShoppingList> getShoppingList() {
        return shoppingList;
    }
    
    /**
     * Setter for the property fidelityPoints.
     * @param fidelityPoints The new value to set to the property.
     */
    public void setShoppingList(Set<ShoppingList> shoppingList) {
        this.shoppingList = shoppingList;
    }
    
    /**
     * Getter for the object store.
     * @return the store object.
     */
    public Store getStore() {
        return store;
    }
    
    /**
     * Setter for the object store.
     * @param store The new value to set to the property.
     */
    public void setStore(Store store) {
        this.store = store;
    }
    
    /**
     * Getter for the Map baskets.
     * @return the baskets property.
     */
    public Map<Product, Basket> getBaskets() {
        return baskets;
    }
    
    /**
     * Setter for the Map baskets.
     * @param baskest The new value to set to the property.
     */
    public void setBaskets(Map<Product, Basket> baskets) {
        this.baskets = baskets;
    }
    
    /**
     * Getter for the Set orders.
     * @return the orders Set.
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }
    
    /**
     * Setter for the property orders.
     * @param orders The new value to set to the property.
     */
    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
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
