package com.jms.model;

import java.util.Objects;

/**
 *
 * @author carol
 */
public class Stock {
    //proprieties
    private StockId stockId;
    private int qtyStock;
    //constructor

    public Stock() {
    }

    public Stock(StockId stockId, int qtyStock) {
        this.stockId = stockId;
        this.qtyStock = qtyStock;
    }
    //getter/setter

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
    //

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
