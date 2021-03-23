package com.jms.model;

import java.util.Objects;

/**
 * Stock Class.
 * @author Jerry Mouse Software.
 */
public class Stock {

    // --------------------- PROPERTIES ---------------------
    
    
    private StockId stockId;
    private int qtyStock;
            
    /**
     * Hibernate join property with <nom_de_la_classe_1> Class  and <nom_de_la_classe_2> Class.
     */
    private Store store;
    
    /**
     * Hibernate join property with <nom_de_la_classe_1> Class  and <nom_de_la_classe_2> Class.
     */
    private Product product;
    
    // -------------------- CONSTRUCTORS --------------------
    
    public Stock() {
    }

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
