package com.jms.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

/**
 * ProductCategory class represents a category to which a product belongs.
 * @author Jerry Mouse Software
 */
@Entity (name = "CategorieProduit")
public class ProductCategory implements Serializable {
    
    // --------------------- PROPERTIES ---------------------
    /**
     * Unique code identifying a product category
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "CodeCP")
    private int id;
    
    /**
     * Label of a product category
     */
    @Column (name = "LibelleCP")
    private String name;
    
    /**
     * Collection of products of this category.
     */
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>(0);
    
    /**
     * Department of a product category.
     */
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "CodeR")
    private Department department;
    
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
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
        return true;
    }
}
