package com.jms.model;

import java.util.Objects;

/**
 *
 * @author Shanshan ZHAO
 */
public class ProductCategory {
    
    // --------------------- PROPERTIES ---------------------
    private int id;
    private String name;
    
    // -------------------- CONSTRUCTORS --------------------
    public ProductCategory(String description) {
        this.name = description;
    }

    public ProductCategory() {
    }
    
    // ----------------- GETTERS & SETTERS ------------------
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

    // ----------------------- METHODS ----------------------    
    @Override
    public String toString() {
        return "ProductCategory{" + "id=" + id + ", description=" + name + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.name);
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
        final ProductCategory other = (ProductCategory) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
}
