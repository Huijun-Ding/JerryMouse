/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;

/**
 * Client Class.
 * @author Jerry Mouse Software.
 */
@Entity
public class Client implements Serializable{
    // --------------------- PROPERTIES ---------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "CodeCL")
    private int code;
    
    @Column (name = "NomCL")
    private String lastName;
    
    @Column (name = "PrenomCL")
    private String firstName;
    
    @Column (name = "AdresseMailCL")
    private String email;
    
    @Column (name = "MotDePasseCL")
    private String password;
    
    @Column (name = "PointsFidelitesCL")
    private int fidelityPoints;
    
    //-----------Relation with Basket--------------
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL) 
    @MapKeyJoinColumn(name = "CodeCL")
    private Map<Client,Basket> basket = new HashMap<>(0);
    
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
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFidelityPoints() {
        return fidelityPoints;
    }

    public void setFidelityPoints(int fidelityPoints) {
        this.fidelityPoints = fidelityPoints;
    }
    
    // ----------------------- METHODS ----------------------
    @Override
    public String toString() {
        return "Client{" + "code=" + code + ", lastName=" + lastName + ", firstName=" + firstName + ", email=" + email + ", password=" + password + ", fidelityPoints=" + fidelityPoints + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + this.code;
        return hash;
    }

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
