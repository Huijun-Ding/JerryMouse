package com.jms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author carol
 */
@Embeddable
public class HaveId implements Serializable {
    
    // --------------------- PROPERTIES ---------------------
    @Column(name = "HeureDebutCR")
    private Date hourStart;
    
    @Column(name = "CodeM")
    private int storeId;
    
    // -------------------- CONSTRUCTORS --------------------

    public HaveId() {
    }
    
    public HaveId(Date hourStart, int storeId) {
        this.hourStart = hourStart;
        this.storeId = storeId;
    }

    // ----------------- GETTERS & SETTERS ------------------
    
    public Date getHourStart() {
        return hourStart;
    }

    public void setHourStart(Date hourStart) {
        this.hourStart = hourStart;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
    
    // ----------------------- METHODS ----------------------

    @Override
    public String toString() {
        return "HaveId{" + "hourStart=" + hourStart + ", storeId=" + storeId + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.hourStart);
        hash = 67 * hash + this.storeId;
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
        final HaveId other = (HaveId) obj;
        if (this.storeId != other.storeId) {
            return false;
        }
        if (!Objects.equals(this.hourStart, other.hourStart)) {
            return false;
        }
        return true;
    }
    
}
