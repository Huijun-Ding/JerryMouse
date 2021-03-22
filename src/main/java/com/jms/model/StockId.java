package com.jms.model;

import java.util.Objects;

/**
 *
 * @author carol
 */
public class StockId {
    //proprieties
    private int id;// id of store
    private String ean; //identity of produit
    //constructor

    public StockId() {
    }

    public StockId(int id, String ean) {
        this.id = id;
        this.ean = ean;
    }
    //getter/setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }
    //Override methods

    @Override
    public String toString() {
        return "StockId{" + "id=" + id + ", ean=" + ean + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
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
        final StockId other = (StockId) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.ean, other.ean)) {
            return false;
        }
        return true;
    }
    
}
