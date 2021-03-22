/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.model;

/**
 *
 * @author RAKOTOARISOA
 */
public class Client {
    //------Properties ------
    private int code;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private int fidelityPoints;
    
    //-----Constructor ------------
    public Client() {
    }

    public Client(String lastName, String firstName, String email, String password, int fidelityPoints) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.fidelityPoints = fidelityPoints;
    }
    
    //---------Getter&Setters------------

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
    
    //-------Overriden Methods ------------------------

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
