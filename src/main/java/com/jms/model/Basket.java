package com.jms.model;

import java.util.Objects;

/**
 *
 * @author carol
 */
public class Basket {
    //proprieties
    private BasketId basketId;
    private int qtyBasket;
    //constructor

    public Basket() {
    }

    public Basket(BasketId basketId, int qtyBasket) {
        this.basketId = basketId;
        this.qtyBasket = qtyBasket;
    }
    //getter/setter

    public BasketId getBasketId() {
        return basketId;
    }

    public void setBasketId(BasketId basketId) {
        this.basketId = basketId;
    }

    public int getQtyBasket() {
        return qtyBasket;
    }

    public void setQtyBasket(int qtyBasket) {
        this.qtyBasket = qtyBasket;
    }
    //Override methods

    @Override
    public String toString() {
        return "Basket{" + "basketId=" + basketId + ", qtyBasket=" + qtyBasket + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.basketId);
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
        final Basket other = (Basket) obj;
        if (!Objects.equals(this.basketId, other.basketId)) {
            return false;
        }
        return true;
    }
    
}
