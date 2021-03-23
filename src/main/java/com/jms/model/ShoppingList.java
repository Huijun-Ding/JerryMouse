package com.jms.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * ShoppingList Class
 * @author JerryMouseSoftware
 */

@Entity(name="ListeCourse")
public class ShoppingList {

    //--------------- Properties -----------------
    
    /**
     * Designation: Unique code identifying a Shopping list
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CodeLC")
    private int id;
    
    /**
     * Designation: Name of a shopping list
     */
    @Column(name="NomLC")
    private String name;
    
    /**
     * Designation: A REMPLIR!!!!
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="CodeClient")
    private Client client;
    
    /**
     * Designation: A REMPLIR!!!!
     */
    @OneToMany(mappedBy ="shoppingList", fetch = FetchType.LAZY)
    private Set<PostIt> postIts = new HashSet(0);
    
    //---------------- Constructor -----------------
    public ShoppingList() {
    }

    public ShoppingList(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //---------------- Getters & Setters -----------------------
    /**
     * Getter for the property id.
     * @return the id property. 
     */
    public int getId() {
        return id;
    }
    /**
     * Setter for the property id.
     * @param id The new value to set to the property.
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Getter for the property name.
     * @return the name property. 
     */
    public String getName() {
        return name;
    }
    /**
     * Setter for the property name.
     * @param name The new value to set to the property.
     */
    public void setName(String name) {
        this.name = name;
    }
    //--------------------- Methods -------------------
    /**
     * Method which converts the current object into a String object.
     */
    @Override
    public String toString() {
        return "ShoppingList{" + "id=" + id + ", name=" + name + '}';
    }
    
    /**
     * Method which returns the hash code of the current object id.
     * @return The hash code of the current object id.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.id;
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
        final ShoppingList other = (ShoppingList) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
