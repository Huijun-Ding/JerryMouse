/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;

/**
 * The class Order represents a order that ordered by a client which includ multiple order lines.
 * @author Jerry Mouse Software
 */
@Entity(name = "Commande")
public class Order {

    //-----------Properties----------------
    
    /**
     * The unique identity of a order.
     */
    @Id
    @Column(name = "CodeCD")
    private int orderId;
    
    /**
     * The date of order.
     */
    @Column(name = "DateCD")
    private String orderName;
    
    /**
    *  Hibernate join property with Product Class.
    */
    @OneToMany (mappedBy = "orders", cascade = CascadeType.ALL)
    @MapKeyJoinColumn (name = "EANP")
    private Map<Product, OrderLine> products = new HashMap<>(0);

    //-----------Constructors--------------
    public Order() {
    }

    public Order(int orderId, String orderName) {
        this.orderId = orderId;
        this.orderName = orderName;
    }

    //-----------Getters and setters----------------
    public int getOrderId() {
        return orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    //------------Methods--------------
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.orderId;
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
        final Order other = (Order) obj;
        if (this.orderId != other.orderId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", orderName=" + orderName + '}';
    }

}
