package com.jms.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

/**
 * BasketId Class.
 * @author Jerry Mouse Software.
 */
@Embeddable
public class BasketId implements Serializable{
    // --------------------- PROPERTIES ---------------------
    private int id; //id of custume
    private String ean;
    
    // -------------------- CONSTRUCTORS --------------------
    public BasketId() {
    }

    public BasketId(int id, String ean) {
        this.id = id;
        this.ean = ean;
    }
    
    // ----------------- GETTERS & SETTERS ------------------
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
    
    // ----------------------- METHODS ------------------------
    @Override
    public String toString() {
        return "BasketId{" + "id=" + id + ", ean=" + ean + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.ean);
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
        final BasketId other = (BasketId) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.ean, other.ean)) {
            return false;
        }
        return true;
    }
}
