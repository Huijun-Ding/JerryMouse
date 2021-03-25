/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The class OrderLineId represents a combinated id for a order line (Product's
 * id and Order's Id).
 *
 * @author Jerry Mouse Software
 */
@Embeddable
public class OrderLineId implements Serializable {

    // --------------------- PROPERTIES ---------------------
    /**
     * EAN (European Article Numbering): Bar code that uniquely identifies a
     * product.
     * <b>Rule(s) : > 0</b>
     * <b>Format : 12 or 13 digits</b>
     * <b>Maximum size : 20</b>
     */
    @Column(name = "EANP")
    private String ean;

    /**
     * Unique code identifying a order.
     */
    @Column(name = "CodeCD")
    private int orderId;

    // -------------------- CONSTRUCTORS --------------------
    /**
     * A constructor of the OrderLineID Class.
     *
     * @param ean EAN (European Article Numbering): Bar code that uniquely
     * identifies a product.
     * @param orderId Unique code identifying a order.
     */
    public OrderLineId() {
    }

    public OrderLineId(String ean, int orderId) {
        this.ean = ean;
        this.orderId = orderId;
    }

    // ----------------- GETTERS & SETTERS ------------------
    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    // ----------------------- METHODS ----------------------
    @Override
    public String toString() {
        return "OrderLineId{" + "ean=" + ean + ", orderId=" + orderId + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.ean);
        hash = 83 * hash + this.orderId;
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
        final OrderLineId other = (OrderLineId) obj;
        if (this.orderId != other.orderId) {
            return false;
        }
        if (!Objects.equals(this.ean, other.ean)) {
            return false;
        }
        return true;
    }

}
