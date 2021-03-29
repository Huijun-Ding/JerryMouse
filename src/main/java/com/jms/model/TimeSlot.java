package com.jms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;

/**
 * TimeSlot Class.
 * @author Jerry Mouse Software.
 */
@Entity(name = "CreneauHoraire")
@SuppressWarnings("PersistenceUnitPresent")
public class TimeSlot implements Serializable {

    // --------------------- PROPERTIES ---------------------

    /**
     * Start time of a time slot.
     * <b>Rule(s) : ≥ 07:30 ET ≤ 20:30</b>
     * <b>Format : hh:mm (format 24h)</b>
     */
    @Id
    @Column(name = "HeureDebutCR")
    private Date startTime;

    /**
     * End time of a time slot.
     * <b>Rule(s) : HeureDebutCR + 30 min</b>
     * <b>Format : hh:mm (format 24h)</b>
     */
    @Column(name = "HeureFinCR")
    private Date endTime;

    /**
     * Hibernate join property with Store Class and Have Class.
     */
    @OneToMany(mappedBy = "timeSlot", cascade = CascadeType.ALL)
    @MapKeyJoinColumn(name = "CodeM")
    private Map<Store, Have> stores = new HashMap<>();

    /**
     * Hibernate join property with Order Class.
     */
    @OneToMany(mappedBy = "timeslot",fetch = FetchType.LAZY)
    private Set<Order> orders = new HashSet<>(0);

    // -------------------- CONSTRUCTORS --------------------

    /**
     * A constructor of the TimeSlot Class.
     */
    public TimeSlot() {
    }

    /**
     * A constructor of the TimeSlot Class.
     * @param startTime Start time of a time slot.
     * @param endTime End time of a time slot.
     */
    public TimeSlot(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // ----------------- GETTERS & SETTERS ------------------
    /**
     * Getter for the start time property.
     * @return The start time property.
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Setter for the start time property.
     * @param startTime The new value to set to the property.
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter for the end time property.
     * @return The end time property.
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Setter for the end time property.
     * @param endTime The new value to set to the property.
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * Getter for the stores property.
     * @return The stores property.
     */
    public Map<Store, Have> getStores() {
        return stores;
    }

    /**
     * Setter for the stores property.
     * @param stores The new value to set to the property.
     */
    public void setStores(Map<Store, Have> stores) {
        this.stores = stores;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
    
    

    // ----------------------- METHODS ----------------------

    /**
     * Method which converts the current object into a String object.
     */
    @Override
    public String toString() {
        return "TimeSlot{" + "startTime=" + startTime + ", endTime=" + endTime + '}';
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
        final TimeSlot other = (TimeSlot) obj;
        if (!Objects.equals(this.startTime, other.startTime)) {
            return false;
        }
        if (!Objects.equals(this.endTime, other.endTime)) {
            return false;
        }
        return true;
    }

    /**
     * Method which returns the hash code of the current object id.
     * @return The hash code of the current object id.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.startTime);
        hash = 23 * hash + Objects.hashCode(this.endTime);
        return hash;
    }
}
