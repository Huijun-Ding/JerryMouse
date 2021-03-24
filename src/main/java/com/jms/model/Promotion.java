package com.jms.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;

/**
 * Promotion Class.
 *
 * @author Jerry Mouse Software.
 */
@Entity
public class Promotion implements Serializable {

    // --------------------- PROPERTIES ---------------------
    /**
     * Unique code identifying a promotion.
     * <b>Rule(s) : > 0</b>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodePR")
    private int id;

    /**
     * Percentage of discount linked to a discount.
     * <b>Rule(s) : > 0 ET ≤ 1</b>
     */
    @Column(name = "PourcentagePR")
    private float percentage;

    /**
     * Rank of the product concerned by the percentage discount (2 for the 2nd
     * product for example).
     * <b>Rule(s) : ≥ 0</b>
     */
    @Column(name = "NiemePR")
    private int rank;

    // -----------Relation with Reduce-----------
    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL)
    @MapKeyJoinColumn(name = "CodePR")
    private Map<Product, Reduce> reduce = new HashMap<>(0);

    // -------------------- CONSTRUCTORS --------------------
    /**
     * A constructor of the Promotion Class.
     *
     * @param pourcentage Percentage of discount linked to a discount.
     * @param rank Rank of the product concerned by the percentage discount (2
     * for the 2nd product for example).
     */
    public Promotion(float pourcentage, int rank) {
        this.percentage = pourcentage;
        this.rank = rank;
    }

    public Promotion() {
    }

    //-----------Getters and setters--------------
    /**
     * Getter for the id property.
     *
     * @return The id property.
     */
    public int getCode() {
        return id;
    }

    /**
     * Setter for the id property.
     *
     * @param id The new value to set to the property.
     */
    public void setCode(int id) {
        this.id = id;
    }

    /**
     * Getter for the percentage property.
     *
     * @return The percentage property.
     */
    public float getPourcentage() {
        return percentage;
    }

    /**
     * Setter for the pourcentage property.
     *
     * @param pourcentage The new value to set to the property.
     */
    public void setPourcentage(float pourcentage) {
        this.percentage = pourcentage;
    }

    /**
     * Getter for the rank property.
     *
     * @return The rank property.
     */
    public int getNieme() {
        return rank;
    }

    /**
     * Setter for the rank property.
     *
     * @param rank The new value to set to the property.
     */
    public void setNieme(int rank) {
        this.rank = rank;
    }

    // ----------------------- METHODS ----------------------
    /**
     * Method which converts the current object into a String object.
     */
    @Override
    public String toString() {
        return "Promotion{" + "code=" + id + ", pourcentage=" + percentage
                + ", nieme=" + rank + '}';
    }

    /**
     * Method which returns the hash code of the current object id.
     *
     * @return The hash code of the current object id.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
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
        final Promotion other = (Promotion) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
