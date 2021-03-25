package com.jms.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 * The class OrderLine represents a order line which a product with certain
 * quantity.
 *
 * @author Jerry Mouse Software
 */
@Entity(name = "LigneCommande")
public class OrderLine {
    // --------------------- PROPERTIES ---------------------

    /**
     * Id of a order line.
     */
    @EmbeddedId
    private OrderLineId id;

    /**
     * Start date when a promotion was applied to a product.
     * <b>Format : jj/mm/aaaa</b>
     */
    @Column(name = "qteCommande")
    private int quantity;

    /**
     * Hibernate join property with Commande Class.
     */
    @ManyToOne
    @JoinColumn(name = "CodeCD", insertable = false, updatable = false)
    private Order order;

    /**
     * Hibernate join property with Product Class.
     */
    @ManyToOne
    @JoinColumn(name = "EANP", insertable = false, updatable = false)
    private Product product;

    // -------------------- CONSTRUCTORS --------------------
    public OrderLine() {
    }

    public OrderLine(OrderLineId id, int quantity, Order order, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.order = order;
        this.product = product;
    }

    // ----------------- GETTERS & SETTERS ------------------
    public OrderLineId getId() {
        return id;
    }

    public void setId(OrderLineId id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    // ----------------------- METHODS ------------------------
    @Override
    public String toString() {
        return "OrderLine{" + "id=" + id + ", quantity=" + quantity + ", order=" + order + ", product=" + product + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + this.quantity;
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
        final OrderLine other = (OrderLine) obj;
        if (this.quantity != other.quantity) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
