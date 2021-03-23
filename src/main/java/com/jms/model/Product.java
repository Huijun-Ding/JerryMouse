/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

/**
 * The class Product represents a product that exists in the catalog of a store.
 * @author mlk
 */

@Entity (name="Produit")
public class Product implements Serializable {
    
    // Properties.
    
    @Id
    @Column (name = "EANP")
    private String ean;
    
    @Column (name = "LibelleP")
    private String name;
    
    @Column (name = "DescriptionP")
    private String description;
    
    @Column (name = "MarqueP")
    private String brand;
    
    @Column (name = "FormatP")
    private String format;
    
    @Column (name = "BioP")
    private boolean bio;
    
    @Column (name = "NutriscoreP")
    private ProductNutriScore nutriscore;
    
    @Column (name = "ConditionnementP")
    private ProductConditioning packaging;
    
    // Relation Appartenir Categorie
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CodeCat")
    private ProductCategory category;
    
    // Relation Posseder Label
    @ManyToMany
    @JoinTable(name = "Posseder", 
            joinColumns = @JoinColumn(name = "EANP"), 
            inverseJoinColumns = @JoinColumn(name = "CodeLB"))
    private Set<Label> labels = new HashSet<>(0);

    // Constructors.
    
    public Product() {
    }

    public Product(String ean, String libelle, String description, String brand, 
            String format, boolean bio) {
        this.ean = ean;
        this.name = libelle;
        this.description = description;
        this.brand = brand;
        this.format = format;
        this.bio = bio;
    }

    public Product(String ean, String libelle, String description, 
            String brand, String format, boolean bio, 
            ProductNutriScore nutriscore, ProductConditioning packaging) {
        this.ean = ean;
        this.name = libelle;
        this.description = description;
        this.brand = brand;
        this.format = format;
        this.bio = bio;
        this.nutriscore = nutriscore;
        this.packaging = packaging;
    }
    
    // Getters & Setters.

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean isBio() {
        return bio;
    }

    public void setBio(boolean bio) {
        this.bio = bio;
    }

    public ProductNutriScore getNutriscore() {
        return nutriscore;
    }

    public void setNutriscore(ProductNutriScore nutriscore) {
        this.nutriscore = nutriscore;
    }

    public ProductConditioning getPackaging() {
        return packaging;
    }

    public void setPackaging(ProductConditioning packaging) {
        this.packaging = packaging;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public Set<Label> getLabels() {
        return labels;
    }

    public void setLabels(Set<Label> labels) {
        this.labels = labels;
    }
    
    // Methods.

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.ean);
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.ean, other.ean)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product{" + "ean=" + ean + ", name=" + name 
                + ", description=" + description + ", brand=" 
                + brand + ", format=" + format + ", bio=" + bio 
                + ", nutriscore=" + nutriscore 
                + ", packaging=" + packaging + ", category=" + category 
                + ", labels=" + labels + '}';
    }

    
}
