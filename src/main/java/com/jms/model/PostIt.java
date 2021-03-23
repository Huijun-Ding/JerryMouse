package com.jms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * PostIt Class
 * @author JerryMouseSoftware
 */
@Entity(name = "PostIt")
public class PostIt {

    //------------- Properties ------------------
    /**
     * Designation: Unique code identifying a post-it
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodePI")
    private int code;
    
    /**
     * Designation: Label of a post-it
     */
    @Column(name = "LibellePI")
    private String wording;
    
    /**
     * Designation: ..A REMPLIR!!!!
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CodePI")
    private ShoppingList shoppingList;
    
    /**
     * Designation: A REMPLIR!!!!
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CodePI")
    private Product product;

    //------------- Constructor ------------------
    public PostIt() {
    }

    public PostIt(String wording) {
        this.wording = wording;
    }
    //------------ Getter & Setters --------------

    /**
     * Getter for the property code.
     * @return the code property .
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
     * Getter for the property wording.
     * @return the wording property. 
     */
    public String getWording() {
        return wording;
    }

    /**
     * Setter for the property wording.
     * @param wording The new value to set to the property.
     */
    public void setWording(String wording) {
        this.wording = wording;
    }

    //------------ Methods --------------
    /**
     * Method which converts the current object into a String object.
     */
    @Override
    public String toString() {
        return "PostIt{" + "code=" + code + ", wording=" + wording + '}';
    }

    /**
     * Method which returns the hash code of the current object id.
     * @return The hash code of the current object id.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.code;
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
        final PostIt other = (PostIt) obj;
        if (this.code != other.code) {
            return false;
        }
        return true;
    }

}
