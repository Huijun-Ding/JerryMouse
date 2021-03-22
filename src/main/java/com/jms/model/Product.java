package com.jms.model;

import java.util.Objects;

/**
 *
 * @author Shanshan ZHAO
 */
public class Product {
    // Properties.
    private String ean;
    private String name;
    private String description;
    private String brand;
    private String format;
    private boolean bio;
    
    // Constructors.
    public Product(String name, String description, String brand, String format, boolean bio) {
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.format = format;
        this.bio = bio;
    }

    public Product() {
    }
    
    // Getters and setters
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
    
    // Override methods.
    @Override
    public String toString() {
        return "Product{" + "ean=" + ean + ", name=" + name + ", description=" + description + ", brand=" + brand + ", format=" + format + ", bio=" + bio + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.ean);
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.description);
        hash = 23 * hash + Objects.hashCode(this.brand);
        hash = 23 * hash + Objects.hashCode(this.format);
        hash = 23 * hash + (this.bio ? 1 : 0);
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
        if (this.bio != other.bio) {
            return false;
        }
        if (!Objects.equals(this.ean, other.ean)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.brand, other.brand)) {
            return false;
        }
        if (!Objects.equals(this.format, other.format)) {
            return false;
        }
        return true;
    }
    
}
