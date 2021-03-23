package com.jms.model;

import java.util.Objects;

/**
 *
 * @author Shanshan ZHAO
 */
public class Shelf {
    // Properties.
    private int id;
    private String name;
    
    // Constructors.
    public Shelf() {
    }

    public Shelf(String description) {
        this.name = description;
    }
    
    // Getters and setters.
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
        return "Family{" + "id=" + id + ", description=" + name + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
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
        final Shelf other = (Shelf) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}