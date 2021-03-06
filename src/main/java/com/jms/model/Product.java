/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.model;

import java.io.Serializable;
import static java.sql.JDBCType.NULL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
  
/**
 * The class Product represents a product that exists in the catalog of a store.
 *
 * @author Jerry Mouse Software
 */
@Entity(name = "Produit")
public class Product implements Serializable {

    // --------------------- PROPERTIES ---------------------
    /**
     * EAN (European Article Numbering): Bar code that uniquely identifies a
     * product.
     * <b>Format : 12 or 13 digits</b>
     * <b>Maximum size : CC(20)</b>
     */
    @Id
    @Column(name = "EANP")
    private String ean;

    /**
     * Short name of a product.
     * <b>Maximum size : CC(50)</b>
     */
    @Column(name = "LibelleP")
    private String name;

    /**
     * Detailed description of a product.
     * <b>Maximum size : CC(255)</b>
     */
    @Column(name = "DescriptionP")
    private String description;

    /**
     * Brand name of a product.
     * <b>Maximum size : CC(100)</b>
     */
    @Column(name = "MarqueP")
    private String brand;

    /**
     * Format of a product (value and unit of measure).
     * <b>Format : (#)*[g/l]</b>
     * <b>Maximum size : CC(15)</b>
     */
    @Column(name = "FormatP")
    private String format;

    /**
     * Indicator of whether a product is organic or not.
     * <b>Format : boolean</b>
     */
    @Column(name = "BioP")
    private boolean bio;

    /**
     * Potential Nutriscore of a product.
     * <b>Rule : {null, "A", "B", "C", "D", "E"}</b>
     * <b>Format : Enumeration</b>
     */
    @Column(name = "NutriscoreP")
    @Enumerated(EnumType.STRING)
    private ProductNutriScore nutriscore;

    /**
     * Packaging of a product.
     * <b>Rule : {"UNITAIRE", "LOT"}</b>
     * <b>Format : Enumeration</b>
     */
    @Column(name = "ConditionnementP")
    @Enumerated(EnumType.STRING)
    private ProductConditioning packaging;

    /**
     * If the conditioning of the product is in pack, the quantity of products
     * in the pack.
     * <b>Format : Integer</b>
     */
    @Column(name = "QteConditionnementP")
    private int packagingQuantity;

    /**
     * The unit price of a product.
     * <b>Rule : > 0</b>
     */
    @Column(name = "PrixUnitaireP")
    private float unitPrice;

    /**
     * The price per kilo of a product if exists.
     * <b>Rule : > 0</b>
     */
    @Column(name = "PrixKgP")
    private float kgPrice;
 @Column(name = "EnergieP")
    private String energy;

    @Column(name = "MatieresGrassesP")
    private String fats;

    @Column(name = "GrasSaturesP")
    private String saturatedFatAcids;

    @Column(name = "GlucidesP")
    private String carbohydrates;

    @Column(name = "SucreP")
    private String sugar;

    @Column(name = "Prot??inesP")
    private String protein;

    @Column(name = "SelP")
    private String salt;
    
    @Column(name = "CompositionP")
    private String composition;




    /**
     * The url of the thumbnail of the product.
     */
    @Column(name = "URLP")
    private String urlThumbnail;

    // Relation Appartenir Categorie
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CodeCategorieP")
    private ProductCategory category;

    // Relation Posseder Label
    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable(name = "Posseder",
            joinColumns = @JoinColumn(name = "EANP"),
            inverseJoinColumns = @JoinColumn(name = "CodeLB"))
    private Set<Label> labels = new HashSet<>(0);

    // Relation Etre PostIt
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<PostIt> postIts = new HashSet<>(0);

    // Relation Panier Client
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY)
    @MapKeyJoinColumn(name = "CodeCL")
    private Map<Client, Basket> baskets = new HashMap<>(0);

    // Relation Reduire Promotion
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY)
    @MapKeyJoinColumn(name = "CodePR")
    private Map<Promotion, Reduce> promotions = new HashMap<>(0);

    // Relation Ligne de commande Commande
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY)
    @MapKeyJoinColumn(name = "CodeCD")
    private Map<Order, OrderLine> orders = new HashMap<>(0);

    /**
     * Hibernate join property with Product Class and Client Class for the
     * relationship "Preferer".
     */
    @ManyToMany(mappedBy = "favoriteProducts", fetch = FetchType.LAZY)
    private Set<Client> clients = new HashSet<>(0);

    // -------------------- CONSTRUCTORS --------------------
    public Product() {
    }

    public Product(String ean, String libelle, float unitPrice, String urlThumbnail) {
         this.ean = ean;
         this.name = libelle;
         this.unitPrice = unitPrice;
         this.urlThumbnail=urlThumbnail;
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

    public Product(String ean, String libelle, String description,
            String brand, String format, boolean bio,
            ProductNutriScore nutriscore, ProductConditioning packaging,
            ProductCategory category) {
            this.ean = ean;
            this.name = libelle;
            this.description = description;
            this.brand = brand;
            this.format = format;
            this.bio = bio;
            this.nutriscore = nutriscore;
            this.packaging = packaging;
            this.category = category;
    }

    public Product(String ean, String name, String format, String brand, String description,
            ProductNutriScore nutriscore, ProductConditioning packaging,
            int packagingQuantity, float unitPrice, float kgPrice,
            String urlThumbnail) {
            this.ean = ean;
            this.name = name;
            this.format = format;
            this.nutriscore = nutriscore;
            this.packaging = packaging;
            if (packaging == null) {
                this.packaging = null;
            }
            this.packagingQuantity = packagingQuantity;
            this.unitPrice = unitPrice;
            this.kgPrice = kgPrice;
            this.urlThumbnail = urlThumbnail;
    }
    
    public Product(String ean, String name, String format, String brand, String description,
            ProductNutriScore nutriscore, ProductConditioning packaging,
            int packagingQuantity, float unitPrice, float kgPrice,
            String urlThumbnail, int idPromotion, float percentage, int rank) {
            this.ean = ean;
            this.name = name;
            this.format = format;
            this.nutriscore = nutriscore;
            this.packaging = packaging;
            this.packagingQuantity = packagingQuantity;
            this.unitPrice = unitPrice;
            this.kgPrice = kgPrice;
            this.urlThumbnail = urlThumbnail;
            this.promotions.put(new Promotion(percentage, rank), new Reduce());
    }

    public Product(String ean, String name, String format, String brand, String description,
            ProductNutriScore nutriscore, ProductConditioning packaging,
            int packagingQuantity, float unitPrice, float kgPrice,
            String urlThumbnail, String energy, String fats, String saturatedFatAcids, 
            String carbohydrates, String sugar, String protein, String salt, String composition,
            int idPromotion, float percentage, int rank) {
        this.ean = ean;
        this.name = name;
        this.format = format;
        this.brand = brand;
        this.description = description;
        this.nutriscore = nutriscore;
        this.packaging = packaging;
        this.packagingQuantity = packagingQuantity;
        this.unitPrice = unitPrice;
        this.kgPrice = kgPrice;
        this.urlThumbnail = urlThumbnail;
        this.energy = energy;
        this.fats = fats;
        this.saturatedFatAcids = saturatedFatAcids;
        this.carbohydrates = carbohydrates;
        this.sugar = sugar;
        this.protein = protein;
        this.salt = salt;
        this.composition = composition;
        this.promotions.put(new Promotion(percentage, rank), new Reduce());
    }

    public Product(String ean, String name, String format,
            ProductNutriScore nutriscore, ProductConditioning packaging,
            int packagingQuantity, float unitPrice, float kgPrice,
            String urlThumbnail, int idPromotion, float percentage, int rank,
            Set<Label> labels) {
            this.ean = ean;
            this.name = name;
            this.format = format;
            this.nutriscore = nutriscore;
            this.packaging = packaging;
            this.packagingQuantity = packagingQuantity;
            this.unitPrice = unitPrice;
            this.kgPrice = kgPrice;
            this.urlThumbnail = urlThumbnail;
            this.promotions.put(new Promotion(percentage, rank), new Reduce());
            this.labels = labels;
    }
     public Product(String ean, String name, String format, String brand, String description,
            ProductNutriScore nutriscore, ProductConditioning packaging,
            int packagingQuantity, float unitPrice, float kgPrice,
            String urlThumbnail, String energy, String fats, String saturatedFatAcids, 
            String carbohydrates, String sugar, String protein, String salt, String composition) {
            this.ean = ean;
            this.name = name;
            this.brand = brand;
            this.description = description;
            this.format = format;
            this.nutriscore = nutriscore;
            this.packaging = packaging;
            if (packaging == null) {
                this.packaging = null;
            }
            this.packagingQuantity = packagingQuantity;
            this.unitPrice = unitPrice;
            this.kgPrice = kgPrice;
            this.urlThumbnail = urlThumbnail;
            this.energy = energy;
            this.fats = fats;
            this.saturatedFatAcids = saturatedFatAcids;
            this.carbohydrates = carbohydrates;
            this.sugar = sugar;
            this.protein = protein;
            this.salt = salt;
            this.composition = composition;
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

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getKgPrice() {
        return kgPrice;
    }

    public void setKgPrice(float kgPrice) {
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

    public Map<Order, OrderLine> getOrders() {
        return orders;
    }

    public void setOrders(Map<Order, OrderLine> orders) {
        this.orders = orders;
    }


    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public String getFats() {
        return fats;
    }

    public void setFats(String fats) {
        this.fats = fats;
    }

    public String getSaturatedFatAcids() {
        return saturatedFatAcids;
    }

    public void setSaturatedFatAcids(String saturatedFatAcids) {
        this.saturatedFatAcids = saturatedFatAcids;
    }

    public String getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(String carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }
    
    

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
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
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Product{" + "ean=" + ean + ", name=" + name
                + ", description=" + description + ", brand="
                + brand + ", format=" + format + ", bio=" + bio
                + ", nutriscore=" + nutriscore
                + ", packaging=" + packaging + ", category=" + category
                + ", labels=" + labels
                + ", promotions=" + promotions + '}';
    }

}
