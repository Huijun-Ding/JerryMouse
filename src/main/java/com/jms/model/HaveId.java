package com.jms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * HaveId Class.
 * @author Jerry Mouse Software.
 */
@Embeddable
public class HaveId implements Serializable {

    // --------------------- PROPERTIES ---------------------

    /**
     * Hibernate join property with TimeSlot Class.
     */
    @Column(name = "HeureDebutCR")
    private String startTime;

    /**
     * Hibernate join property with Store Class.
     */
    @Column(name = "CodeM")
    private int storeId;

    // -------------------- CONSTRUCTORS --------------------

    /**
     * A constructor of the HaveId Class.
     */
    public HaveId() {
    }

    /**
     * A constructor of the HaveId Class.
     * @param startTime Start time of a time slot.
     * @param storeId Unique code identifying a store.
     */
    public HaveId(Date startTime, int storeId) {
        this.startTime = startTime;
        this.storeId = storeId;
    }

    // ----------------- GETTERS & SETTERS ------------------
    
    /**
     * Getter for the startTime property.
     * @return The startTime property.
     */
    public Date getStartTime() {
        return startTime;
    }
    
    /**
     * Setter for the startTime property.
     * @param startTime The new value to set to the property.
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    /**
     * Getter for the storeId property.
     * @return The storeId property.
     */
    public int getStoreId() {
        return storeId;
    }
    
    /**
     * Setter for the storeId property.
     * @param storeId The new value to set to the property.
     */
    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
    
    // ----------------------- METHODS ----------------------

    /**
     * Method which converts the current object into a String object.
     */
    @Override
    public String toString() {
        return "HaveId{" + "startTime=" + startTime + ", storeId=" + storeId + '}';
    }

    /**
     * Method which returns the hash code of the current object id.
     * @return The hash code of the current object id.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.startTime);
        hash = 67 * hash + this.storeId;
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
        final HaveId other = (HaveId) obj;
        if (this.storeId != other.storeId) {
            return false;
        }
        if (!Objects.equals(this.startTime, other.startTime)) {
            return false;
        }
        return true;
    }
}
