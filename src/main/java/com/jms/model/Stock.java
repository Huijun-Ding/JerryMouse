package com.jms.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Stock Class.
 * @author Jerry Mouse Software.
 */
@Entity(name = "Stocker")
@SuppressWarnings("PersistenceUnitPresent")
public class Stock implements Serializable {

    // --------------------- PROPERTIES ---------------------
    
    @EmbeddedId
    private StockId stockId;
    
    /**
     * Quantity of stock of a given product in a store.
     * <b>Rule(s) : ≥ 0</b>
     */
    @Column(name = "QteStock")
    private int qtyStock;
            
    /**
     * Hibernate join property with Store Class.
     */
    @ManyToOne
    @JoinColumn(name = "CodeM", insertable = false, updatable = false)
    private Store store;
    
    /**
     * Hibernate join property with Product Class.
     */
    @ManyToOne
    @JoinColumn(name = "CodeP", insertable = false, updatable = false)
    private Product product;
    
    // -------------------- CONSTRUCTORS --------------------
    /**
     * A constructor of the Stock Class.
     */
    public Stock() {
    }
    /**
     * A constructor of the Stock Class.
     * @param stockId
     * @param qtyStock 
     */
    public Stock(StockId stockId, int qtyStock) {
        this.stockId = stockId;
        this.qtyStock = qtyStock;
    }

    // ----------------- GETTERS & SETTERS ------------------

    public StockId getStockId() {
        return stockId;
    }

    public void setStockId(StockId stockId) {
        this.stockId = stockId;
    }

    public int getQtyStock() {
        return qtyStock;
    }

    public void setQtyStock(int qtyStock) {
        this.qtyStock = qtyStock;
    }

    // ----------------------- METHODS ----------------------

    @Override
    public String toString() {
        return "Stock{" + "stockId=" + stockId + ", qtyStock=" + qtyStock + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.stockId);
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
        final Stock other = (Stock) obj;
        if (!Objects.equals(this.stockId, other.stockId)) {
            return false;
        }
        return true;
    }
}
