    package com.jms.model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Have Class.
 * @author Jerry Mouse Software.
 */
@Entity(name = "Avoir")
@SuppressWarnings("PersistenceUnitPresent")
public class Have implements Serializable {

    // --------------------- PROPERTIES ---------------------

    /**
     * Composite key of Have Class associating the identifiers
     * of Store Class and TimeSlot Class.
     */
    @EmbeddedId
    private HaveId haveId;

    /**
     * Maximum capacity of customers for a pickup in a store during a given time
     * slot.
     * <b>Rule(s) : > 0</b>
     */
    @Column(name = "CapaciteCR")
    private int capacity;

    
    /**
     * Hibernate join property with Store Class.
     */
    @ManyToOne
    @JoinColumn(name = "CodeM", insertable = false, updatable = false)
    private Store store;
    
    /**
     * Hibernate join property with TimeSlot Class.
     */
    @ManyToOne
    @JoinColumn(name = "HeureDebutCR", insertable = false, updatable = false)
    private TimeSlot timeSlot;

    // -------------------- CONSTRUCTORS --------------------

    /**
     * A constructor of the Have Class.
     */
    public Have() {
    }

    /**
     * A constructor of the Have Class.
     * @param haveId Composite key of Have Class associating the identifiers
     * of Store Class and TimeSlot Class.
     * @param capacity Maximum capacity of customers for a pickup in a store
     * during a given time slot.
     * @param date Date of a time slot.
     */
    public Have(HaveId haveId, int capacity) {
        this.haveId = haveId;
        this.capacity = capacity;
    }
    
    public Have(HaveId haveId, int capacity, TimeSlot timeSlot) {
        this.haveId = haveId;
        this.capacity = capacity;
        this.timeSlot = timeSlot;
    }

    // ----------------- GETTERS & SETTERS ------------------

    /**
     * Getter for the haveId property.
     * @return The haveId property.
     */
    public HaveId getHaveId() {
        return haveId;
    }
    
    /**
     * Setter for the haveId property.
     * @param haveId The new value to set to the property.
     */
    public void setHaveId(HaveId haveId) {
        this.haveId = haveId;
    }

    /**
     * Getter for the capacity property.
     * @return The capacity property.
     */
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * Setter for the capacity property.
     * @param capacity The new value to set to the property.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Getter for the store property.
     * @return The store property.
     */
    public Store getStore() {
        return store;
    }

    /**
     * Setter for the store property.
     * @param store The new value to set to the property.
     */
    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * Getter for the timeSlotproperty.
     * @return The timeSlot property.
     */
    public TimeSlot getTimeSlot() {
        return timeSlot;
    }
    
    /**
     * Setter for the timeSlot property.
     * @param timeSlot The new value to set to the property.
     */
    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    // ----------------------- METHODS ----------------------

    /**
     * Method which converts the current object into a String object.
     */
    @Override    
    public String toString() {
        return "Have{" + "haveId=" + haveId + ", capacity=" + capacity + ", store=" + store + ", timeSlot=" + timeSlot + '}';
    }

    /**
     * Method which returns the hash code of the current object id.
     * @return The hash code of the current object id.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.haveId);
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
        final Have other = (Have) obj;
        if (!Objects.equals(this.haveId, other.haveId)) {
            return false;
        }
        return true;
    }
}
