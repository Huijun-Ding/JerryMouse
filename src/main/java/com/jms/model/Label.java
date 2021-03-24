package com.jms.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * Label Class.
 *
 * @author Jerry Mouse Software.
 */
@Entity(name = "Label")
public class Label {

    //------------ Properties ------------
    
    /**
     * Designation: Unique code identifying a label
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodeLB")
    private int id;
    
    /**
     * Designation: Name of a label
     */
    @Column(name = "LibelleLB")
    private String name;
    
    /**
     * Designation: for the relationship "Posseder"
     */
    @ManyToMany(mappedBy = "services") 
    private Set<Product> products = new HashSet(0); 
    
    // Constructors.
    public Label() {
    }

    public Label(String description) {
        this.name = description;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return name;
    }

    public void setDescription(String description) {
        this.name = description;
    }

    // Override methods.
    @Override
    public String toString() {
        return "Label{" + "id=" + id + ", description=" + name + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
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
        final Label other = (Label) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
