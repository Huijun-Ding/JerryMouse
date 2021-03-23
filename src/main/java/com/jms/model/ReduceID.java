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
    private String eanp;
    private int id;

    // -------------------- CONSTRUCTORS --------------------
    public ReduceID(String eanp, int id) {
        this.eanp = eanp;
        this.id = id;
    }

    public ReduceID() {
    }

    // ----------------- GETTERS & SETTERS ------------------
    public String getEanp() {
        return eanp;
    }

    public void setEanp(String eanp) {
        this.eanp = eanp;
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
        return "ReduceID{" + "ean=" + eanp + ", id=" + id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.eanp);
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
        if (!Objects.equals(this.eanp, other.eanp)) {
            return false;
        }
        return true;
    }
}
