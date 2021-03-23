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
 * @author Jerry Mouse Software.
 */
@Entity
public class Promotion implements Serializable{
    // --------------------- PROPERTIES ---------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodePR")
    private int id;

    @Column(name = "PourcentagePR")
    private float percentage;

    @Column(name = "NiemePR")
    private int rank;

    // -----------Relation with Reduce-----------
    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL)
    @MapKeyJoinColumn(name = "CodePR")
    private Map<Product, Reduce> reduce = new HashMap<>(0);

    // -------------------- CONSTRUCTORS --------------------
    public Promotion(float pourcentage, int rank) {
        this.percentage = pourcentage;
        this.rank = rank;
    }

    public Promotion() {
    }

    // ----------------------- METHODS ----------------------
    @Override
    public String toString() {
        return "Promotion{" + "code=" + id + ", pourcentage=" + percentage + ", nieme=" + rank + '}';
    }

    //-----------Getters and setters--------------
    public int getCode() {
        return id;
    }

    public void setCode(int id) {
        this.id = id;
    }

    public float getPourcentage() {
        return percentage;
    }

    public void setPourcentage(float pourcentage) {
        this.percentage = pourcentage;
    }

    public int getNieme() {
        return rank;
    }

    public void setNieme(int rank) {
        this.rank = rank;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Float.floatToIntBits(this.percentage);
        hash = 37 * hash + this.rank;
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
        final Promotion other = (Promotion) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Float.floatToIntBits(this.percentage) != Float.floatToIntBits(other.percentage)) {
            return false;
        }
        if (this.rank != other.rank) {
            return false;
        }
        return true;
    }
}
