package com.jms.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 * ReduceID Class.
 * @author Jerry Mouse Software.
 */
@Embeddable
public class ReduceID implements Serializable{

    // --------------------- PROPERTIES ---------------------
    private String ean;
    private int id;

    // -------------------- CONSTRUCTORS --------------------
    public ReduceID(String ean, int id) {
        this.ean = ean;
        this.id = id;
    }

    public ReduceID() {
    }

    // ----------------- GETTERS & SETTERS ------------------
    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // ----------------------- METHODS ----------------------
    @Override
    public String toString() {
        return "ReduceID{" + "ean=" + ean + ", id=" + id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.ean);
        hash = 17 * hash + this.id;
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
        final ReduceID other = (ReduceID) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.ean, other.ean)) {
            return false;
        }
        return true;
    }
}
