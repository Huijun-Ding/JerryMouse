package com.jms.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Basket Class.
 * @author Jerry Mouse Software.
 */
@Entity
public class Basket implements Serializable{
    // --------------------- PROPERTIES ---------------------
    @EmbeddedId
    private BasketId basketId;
    
    @Column (name = "qtePanier")
    private int qtyBasket;

    //-----------Relation with Product--------------
    @ManyToOne 
    @JoinColumn(name = "EANP", insertable = false, updatable = false)
    private Product product;
    
    //-----------Relation with Client--------------
    @ManyToOne 
    @JoinColumn(name = "CodeCL", insertable = false, updatable = false)
    private Client client;
    
    // -------------------- CONSTRUCTORS --------------------
    public Basket() {
    }

    public Basket(BasketId basketId, int qtyBasket) {
        this.basketId = basketId;
        this.qtyBasket = qtyBasket;
    }
    
    // ----------------- GETTERS & SETTERS ------------------
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
    
    // ----------------------- METHODS ------------------------
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
