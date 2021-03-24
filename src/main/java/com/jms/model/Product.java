/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

/**
 * The class Product represents a product that exists in the catalog of a store.
 * @author Jerry Mouse Software
 */
@Entity(name = "Produit")
public class Product implements Serializable {
    
    // --------------------- PROPERTIES ---------------------
    
    @Id
    @Column(name = "EANP")
    private String ean;

    @Column(name = "LibelleP")
    private String name;

    @Column(name = "DescriptionP")
    private String description;

    @Column(name = "MarqueP")
    private String brand;

    @Column(name = "FormatP")
    private String format;

    @Column(name = "BioP")
    private boolean bio;

    @Column(name = "NutriscoreP")
    private ProductNutriScore nutriscore;

    @Column(name = "ConditionnementP")
    private ProductConditioning packaging;
    
    /**
     * If the conditioning of the product is in pack, 
     * the quantity of products in the pack.
     */
    @Column (name = "QteConditionnementP")
    private int packagingQuantity;
    
    /**
     * The unit price of a product.
     */
    @Column (name = "PrixUnit")
    private Double unitPrice;
    
    /**
     * The price per kilo of a product if exists.
     */
    @Column (name = "PrixKG")
    private Double kgPrice;
    
    /**
     * The url of the thumbnail of the product.
     */
    @Column (name = "URLP")
    private String urlThumbnail;
    
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
    
    // Relation Etre PostIt
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY)
    private Set<PostIt> postIts = new HashSet<>(0);
    
    // Relation Panier Client
    @OneToMany (mappedBy = "product", cascade = CascadeType.ALL)
    @MapKeyJoinColumn (name = "CodeCL")
    private Map<Client, Basket> baskets = new HashMap<>(0);
    
    // Relation Reduire Promotion
    @OneToMany (mappedBy = "product", cascade = CascadeType.ALL)
    @MapKeyJoinColumn (name = "CodePR")
    private Map<Promotion, Reduce> promotions = new HashMap<>(0);
    
    // Relation Ligne de commande Commande
    @OneToMany (mappedBy = "product", cascade = CascadeType.ALL)
    @MapKeyJoinColumn (name = "CodeCD")
    private Map<OrderLine, Order> orders = new HashMap<>(0);

    // -------------------- CONSTRUCTORS --------------------
    public Product() {
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

    public Product(String ean, String name, String format, 
            ProductNutriScore nutriscore, ProductConditioning packaging, 
            int packagingQuantity, Double unitPrice, Double kgPrice, 
            String urlThumbnail) {
        this.ean = ean;
        this.name = name;
        this.format = format;
        this.nutriscore = nutriscore;
        this.packaging = packaging;
        this.packagingQuantity = packagingQuantity;
        this.unitPrice = unitPrice;
        this.kgPrice = kgPrice;
        this.urlThumbnail = urlThumbnail;
    }
    
    

    // ----------------- GETTERS & SETTERS ------------------

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

    public int getPackagingQuantity() {
        return packagingQuantity;
    }

    public void setPackagingQuantity(int packagingQuantity) {
        this.packagingQuantity = packagingQuantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getKgPrice() {
        return kgPrice;
    }

    public void setKgPrice(Double kgPrice) {
        this.kgPrice = kgPrice;
    }

    public String getUrlThumbnail() {
        return urlThumbnail;
    }

    public void setUrlThumbnail(String urlThumbnail) {
        this.urlThumbnail = urlThumbnail;
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

    public Set<PostIt> getPostIts() {
        return postIts;
    }

    public void setPostIts(Set<PostIt> postIts) {
        this.postIts = postIts;
    }

    public Map<Client, Basket> getBaskets() {
        return baskets;
    }

    public void setBaskets(Map<Client, Basket> baskets) {
        this.baskets = baskets;
    }

    public Map<Promotion, Reduce> getPromotions() {
        return promotions;
    }

    public void setPromotions(Map<Promotion, Reduce> promotions) {
        this.promotions = promotions;
    }
    
    public Map<OrderLine, Order> getOrders() {
        return orders;
    }

    public void setOrders(Map<OrderLine, Order> orders) {
        this.orders = orders;
    }
    
    
    // ----------------------- METHODS ----------------------

    // ----------------------- METHODS ------------------------
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
