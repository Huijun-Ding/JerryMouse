package com.jms.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

/**
 * Department Class represents a department in a store.
 * @author Jerry Mouse Software
 */
@Entity (name = "Rayon")
public class Department implements Serializable {
    
    // --------------------- PROPERTIES ---------------------
    /** 
     * Unique code identifying a department.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "CodeR")
    private int id;
    
    /**
     * Label of a department.
     */
    @Column (name = "LibelleR")
    private String name;
    
    /**
     * List of categories of products in a department.
     */
    @OneToMany (mappedBy = "department", fetch = FetchType.LAZY)
    private Set<ProductCategory> categories = new HashSet<>(0);
    
    // -------------------- CONSTRUCTORS --------------------
    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }
    
    // ----------------- GETTERS & SETTERS ------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProductCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<ProductCategory> categories) {
        this.categories = categories;
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
