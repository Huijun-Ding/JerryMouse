package com.jms.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

/**
 * BasketId Class.
 *
 * @author Jerry Mouse Software.
 */
@Embeddable
public class BasketId implements Serializable {

    // --------------------- PROPERTIES ---------------------
    /**
     * Unique code identifying a customer.
     * <b>Rule(s) : > 0</b>
     */
    @Column(name = "CodeCL")
    private int id;
    /**
     * EAN (European Article Numbering): Bar code that uniquely identifies a
     * product.
     * <b>Format : 12 or 13 digits</b>
     * <b>Maximum size : 20</b>
     */
    @Column(name = "EANP")
    private String ean;

    // -------------------- CONSTRUCTORS --------------------
    public BasketId() {
    }

    /**
     * A constructor of the BasketId Class.
     *
     * @param id Unique code identifying a customer.
     * @param ean EAN (European Article Numbering): Bar code that uniquely
     * identifies a product.
     */
    public BasketId(int id, String ean) {
        this.id = id;
        this.ean = ean;
    }

    // ----------------- GETTERS & SETTERS ------------------
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

    /**
     * Getter for the ean property.
     *
     * @return The ean property.
     */
    public String getEan() {
        return ean;
    }

    /**
     * Setter for the ean property.
     *
     * @param ean The new value to set to the property.
     */
    public void setEan(String ean) {
        this.ean = ean;
    }

    // ----------------------- METHODS ------------------------
    /**
     * Method which converts the current object into a String object.
     */
    @Override
    public String toString() {
        return "BasketId{" + "id=" + id + ", ean=" + ean + '}';
    }

    /**
     * Method which returns the hash code of the current object id.
     * @return The hash code of the current object id.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.ean);
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
        final BasketId other = (BasketId) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.ean, other.ean)) {
            return false;
        }
        return true;
    }
}
