package com.jms.model;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;

/**
 * TimeSlot Class.
 * @author Jerry Mouse Software.
 */
public class TimeSlot {

    // --------------------- PROPERTIES ---------------------

    /**
     * Start time of a time slot.
     * <b>Rule(s) : ≥ 07:30 ET ≤ 20:30</b>
     * <b>Format : hh:mm (format 24h)</b>
     */
    @Id
    @Column(name="HeureDebutCR")
    private Date startTime;
    
    /**
     * End time of a time slot.
     * <b>Rule(s) : HeureDebutCR + 30 min</b>
     * <b>Format : hh:mm (format 24h)</b>
     */
    @Column(name="HeureFinCR")
    private Date endTime;

    /**
     * Hibernate join property with Store Class and Have Class.
     */
    @OneToMany(mappedBy = "pickUpTimes", cascade = CascadeType.ALL)
    @MapKeyJoinColumn(name = "CodeM")
    private Map<Store, Have> stores;

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
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Method which returns the hash code of the current object id.
     * @return The hash code of the current object id.
     */
    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
}
