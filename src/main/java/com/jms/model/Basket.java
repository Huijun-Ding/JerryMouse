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
 *
 * @author Jerry Mouse Software.
 */
@Entity(name = "Panier")
public class Basket implements Serializable {
    // --------------------- PROPERTIES ---------------------

    /**
     * Key of basket.
     */
    @EmbeddedId
    private BasketId basketId;
    /**
     * Quantity of a product in a given customer's cart.
     * <b>Rule(s) : QtePanier</b>
     * <b>Rule(s) : > 0</b>
     */
    @Column(name = "qtePanier")
    private int qtyBasket;

    //-----------Relation with Product--------------
    /**
     * Hibernate join property with Basket Class and Product Class.
     */
    @ManyToOne
    @JoinColumn(name = "EANP", insertable = false, updatable = false)
    private Product product;

    //-----------Relation with Client--------------
    /**
     * Hibernate join property with Basket Class and Client Class.
     */
    @ManyToOne
    @JoinColumn(name = "CodeCL", insertable = false, updatable = false)
    private Client client;

    // -------------------- CONSTRUCTORS --------------------
    public Basket() {
    }

    /**
     * A constructor of the Basket Class.
     *
     * @param basketId id of basket.
     * @param qtyBasket Quantity of a product in a given customer's cart.
     */
    public Basket(BasketId basketId, int qtyBasket) {
        this.basketId = basketId;
        this.qtyBasket = qtyBasket;
    }

    // ----------------- GETTERS & SETTERS ------------------
    /**
     * Getter for the basketId property.
     *
     * @return The basketId property.
     */
    public BasketId getBasketId() {
        return basketId;
    }

    /**
     * Setter for the basketId property.
     *
     * @param basketId The new value to set to the property.
     */
    public void setBasketId(BasketId basketId) {
        this.basketId = basketId;
    }

    /**
     * Getter for the qtyBasket property.
     *
     * @return The qtyBasket property.
     */
    public int getQtyBasket() {
        return qtyBasket;
    }

    /**
     * Setter for the qtyBasket property.
     *
     * @param qtyBasket The new value to set to the property.
     */
    public void setQtyBasket(int qtyBasket) {
        this.qtyBasket = qtyBasket;
    }

    /**
     * Getter for the product property.
     *
     * @return The product property.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Setter for the product property.
     *
     * @param product The new value to set to the property.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Getter for the client property.
     *
     * @return The client property.
     */
    public Client getClient() {
        return client;
    }

    /**
     * Setter for the client property.
     *
     * @param client The new value to set to the property.
     */
    public void setClient(Client client) {
        this.client = client;
    }

    // ----------------------- METHODS ------------------------
    @Override
    /**
     * Method which converts the current object into a String object.
     */

    public String toString() {
        return "Basket{" + "basketId=" + basketId + ", qtyBasket=" + qtyBasket + '}';
    }

    /**
     * Method which returns the hash code of the current object id.
     *
     * @return The hash code of the current object id.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.basketId);
        return hash;
    }

    /**
     * Method which compares the current object with another one.
     *
     * @param obj The object to compare with.
     * @return <b>True</b> if both objects are equals, <b>False</b> else.
     */
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
