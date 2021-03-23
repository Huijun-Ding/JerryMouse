package com.jms.model;

import java.util.Objects;

/**
 * Department Class represents a shelf in a store.
 * @author Jerry Mouse Software
 */
public class Department {
    
    // --------------------- PROPERTIES ---------------------
    /** 
     * Unique code identifying a department.
     */
    private int id;
    
    /**
     * 
     */
    private String name;
    
    // -------------------- CONSTRUCTORS --------------------
    public Department() {
    }

    public Department(String description) {
        this.name = description;
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
        return "Department{" + "id=" + id + ", name=" + name + '}';
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
        final Department other = (Department) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
