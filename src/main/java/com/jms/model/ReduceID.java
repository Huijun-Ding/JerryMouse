package com.jms.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 * ReduceID Class.
 *
 * @author Jerry Mouse Software.
 */
@Embeddable
public class ReduceID implements Serializable {

    // --------------------- PROPERTIES ---------------------
    /**
     * EAN (European Article Numbering): Bar code that uniquely identifies a
     * product.
     * <b>Rule(s) : > 0</b>
     * <b>Format : 12 or 13 digits</b>
     * <b>Maximum size : 20</b>
     */
    private String eanp;

    /**
     * Unique code identifying a promotion.
     * <b>Rule(s) : > 0</b>
     */
    private int id;

    // -------------------- CONSTRUCTORS --------------------
    /**
     * A constructor of the ReduceID Class.
     *
     * @param eanp EAN (European Article Numbering): Bar code that uniquely
     * identifies a product.
     * @param id Unique code identifying a promotion.
     */
    public ReduceID(String eanp, int id) {
        this.eanp = eanp;
        this.id = id;
    }

    public ReduceID() {
    }

    // ----------------- GETTERS & SETTERS ------------------
    /**
     * Getter for the eanp property.
     *
     * @return The eanp property.
     */
    public String getEanp() {
        return eanp;
    }

    /**
     * Setter for the eanp property.
     *
     * @param eanp The new value to set to the property.
     */
    public void setEanp(String eanp) {
        this.eanp = eanp;
    }

    /**
     * Getter for the id property.
     *
     * @return The id property.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for the id property.
     *
     * @param id The new value to set to the property.
     */
    public void setId(int id) {
        this.id = id;
    }

    // ----------------------- METHODS ----------------------
    /**
     * Method which converts the current object into a String object.
     */
    @Override
    public String toString() {
        return "ReduceID{" + "ean=" + eanp + ", id=" + id + '}';
    }

    /**
     * Method which returns the hash code of the current object id.
     *
     * @return The hash code of the current object id.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.eanp);
        hash = 79 * hash + this.id;
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
        final ReduceID other = (ReduceID) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.eanp, other.eanp)) {
            return false;
        }
        return true;
    }

}
