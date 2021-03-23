package com.jms.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author carol
 */
@Embeddable
public class StockId {

    // --------------------- PROPERTIES ---------------------

    @Column(name = "CodeM")
    private int storeId;
    
    @Column(name = "CodeP")
    private String productEAN;
    
    // -------------------- CONSTRUCTORS --------------------

    public StockId() {
    }

    public StockId(int storeId, String productEAN) {
        this.storeId = storeId;
        this.productEAN = productEAN;
    }
    
    // ----------------- GETTERS & SETTERS ------------------
    
    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getProductEAN() {
        return productEAN;
    }

    public void setProductEAN(String productEAN) {
        this.productEAN = productEAN;
    }
    
    // ----------------------- METHODS ----------------------

    @Override
    public String toString() {
        return "StockId{" + "storeId=" + storeId + ", productEAN=" + productEAN + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.storeId;
        hash = 97 * hash + Objects.hashCode(this.productEAN);
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
        if (this.storeId != other.storeId) {
            return false;
        }
        if (!Objects.equals(this.productEAN, other.productEAN)) {
            return false;
        }
        return true;
    }
}
