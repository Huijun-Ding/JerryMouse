/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author mlk
 */

@Entity (name="Produit")
public class Product implements Serializable {
    
    // Properties.
    
    @Id
    @Column (name = "EANP")
    private String ean;
    
    @Column (name = "LibelleP")
    private String libelle;
    
    @Column (name = "DescriptionP")
    private String description;
    
    @Column (name = "MarqueP")
    private String brand;
    
    @Column (name = "FormatP")
    private String format;
    
    @Column (name = "BioP")
    private boolean bio;
    

    // Constructors.
    
    public Product() {
    }

    public Product(String ean, String libelle, String description, String brand, String format, boolean bio) {
        this.ean = ean;
        this.libelle = libelle;
        this.description = description;
        this.brand = brand;
        this.format = format;
        this.bio = bio;
    }
    
    // Getters & Setters.

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
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
        return "Product{" + "ean=" + ean + ", libelle=" + libelle + ", description=" + description + ", brand=" + brand + ", format=" + format + ", bio=" + bio + '}';
    }
    
    
    
}
