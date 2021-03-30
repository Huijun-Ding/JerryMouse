package com.jms.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;

/**
 * Store Class.
 * @author Jerry Mouse Software.
 */
@Entity(name = "Magasin")
public class Store implements Serializable {

    // --------------------- PROPERTIES ---------------------

    /**
     * Unique code identifying a store.
     * <b>Rule(s) : > 0</b>.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CodeM")
    private int id;

    /**
     * Name of a store.
     * <b>Maximum size : 50</b>
     */
    @Column(name="NomM")
    private String name;

    /**
     * Number and type of street where a store is located.
     * <b>Rule(s) : > 0</b>.
     * <b>Format : street_number, street_type street_name</b>
     * <b>Maximum size : 100</b>
     */
    @Column(name="RueM")
    private String street;

    /**
     * City where a store is located.
     * <b>Maximum size : 100</b>
     */
    @Column(name="VilleM")
    private String city;

    /**
     * Postal code of the place where a store is located.
     * <b>Maximum size : 5</b>
     */
    @Column(name="CodePostalM")
    private String postalCode;

    /**
     * Hibernate join property with Product Class and Stock Class.
     */
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @MapKeyJoinColumn(name = "CodeP")
    private Map<Product, Stock> products = new HashMap<>(0);

    /**
     * Hibernate join property with Have Class and TimeSlot Class.
     */
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @MapKeyJoinColumn(name = "CodeCR")
    private Map<TimeSlot, Have> timeSlots = new HashMap<>(0);
    
    /**
     * Hibernate join property with Client Class.
     */
    @OneToMany(mappedBy = "store")
    private Set<Client> clients = new HashSet<>(0);
    
    /**
     * Hibernate join property with Order Class.
     */
    @OneToMany(mappedBy = "store")
    private Set<Order> orders = new HashSet<>(0);

    // -------------------- CONSTRUCTORS --------------------

    /**
     * A constructor of the Store Class.
     */
    public Store() { }

    /**
     * A constructor of the Store Class.
     * @param name Name of a store.
     * @param street Number and type of street where a store is located.
     * @param city City where a store is located.
     * @param postalCode Postal code of the place where a store is located.
     */
    public Store(String name, String street, String city, String postalCode) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
    }

    // ----------------- GETTERS & SETTERS ------------------
    
    /**
     * Getter for the id property.
     * @return The id property.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for the id property.
     * @param id The new value to set to the property.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for the name property.
     * @return The name property.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name property.
     * @param name The new value to set to the property.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the street property.
     * @return The street property.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Setter for the street property.
     * @param street The new value to set to the property.
     */
    public void setStreet(String street) {
        this.street = street;
    }
    
    /**
     * Getter for the city property.
     * @return The city property.
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter for the city property.
     * @param city The new value to set to the property.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Getter for the postal code property.
     * @return The postal code property.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Setter for the postal code property.
     * @param postalCode The new value to set to the property.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Getter for the postal code property.
     * @return The postal code property.
     */
    public Map<Product, Stock> getProducts() {
        return products;
    }

    /**
     * Setter for the products property.
     * @param products The new value to set to the property.
     */
    public void setProducts(Map<Product, Stock> products) {
        this.products = products;
    }

    public Map<TimeSlot, Have> getTimeSlots() {
        return timeSlots;
    }

    /**
     * Setter for the pick up times property.
     * @param pickUpTimes The new value to set to the property.
     */
    public void setTimeSlots(Map<TimeSlot, Have> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public Set<Client> getClients() {
        return clients;
    }

    /**
     * Setter for the clients property.
     * @param clients The new value to set to the property.
     */
    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    /**
     * Setter for the orders property.
     * @param orders The new value to set to the property.
     */
    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
    
    // ----------------------- METHODS ----------------------

    /**
     * Method which converts the current object into a String object.
     */
    @Override
    public String toString() {
        return "Store{" + "id=" + id + ", name=" + name + ", street=" + street + ", city=" + city + ", postalCode=" + postalCode + '}';
    }

    /**
     * Method which returns the hash code of the current object id.
     * @return The hash code of the current object id.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        return hash;
    }

    /**
     * Method which compares the current object with another one.
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
        final Store other = (Store) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}