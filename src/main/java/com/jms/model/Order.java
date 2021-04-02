/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The class Order represents a order that ordered by a client which includ
 * multiple order lines.
 *
 * @author Jerry Mouse Software
 */
@Entity(name = "Commande")
public class Order {

    //-----------Properties----------------
    /**
     * The unique identity of a order.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodeCD")
    private int orderId;

    /**
     * The date of order.
     */
    @Column(name = "DateCD")
    @Temporal (TemporalType.DATE)
    private Date orderDate;

    /**
     * The date when the order is to be picked up by the client.
     */
    @Column(name = "DateRetrait")
    @Temporal (TemporalType.DATE)
    private Date pickupDate;

    /**
     * Hibernate join property with Product Class.
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @MapKeyJoinColumn(name = "EANP")
    private Map<Product, OrderLine> products = new HashMap<>(0);

    /**
     * Hibernate join property with Client Class.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CodeCL")
    private Client client;

    /**
     * Hibernate join property with Store Class.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CodeM")
    private Store store;

    /**
     * Hibernate join property with CcreneauHoraire Class.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "HeureDebutCR")
    private TimeSlot timeslot;

    //-----------Constructors--------------
    public Order() {
    }

    public Order(int orderId, Date orderDate) {
        this.orderId = orderId;
        this.orderDate = orderDate;
    }

    public Order(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Order(Date orderDate, Store store, TimeSlot timeslot) {
        this.orderDate = orderDate;
        this.store = store;
        this.timeslot = timeslot;
    }

    public Order(int orderId, Client client, Store store, TimeSlot timeslot) {
       this.orderId=orderId;
        this.client = client;
        this.store = store;
        this.timeslot = timeslot;
    }
    

    //-----------Getters and setters----------------
    public int getOrderId() {
        return orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Map<Product, OrderLine> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, OrderLine> products) {
        this.products = products;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public TimeSlot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(TimeSlot timeslot) {
        this.timeslot = timeslot;
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
        return "Order{" + "orderId=" + orderId + ", orderDate=" + orderDate 
                + ", pickupDate=" + pickupDate + ", products=" + products 
                + ", client=" + client + ", store=" + store 
                + ", timeslot=" + timeslot + '}';
    }

}
