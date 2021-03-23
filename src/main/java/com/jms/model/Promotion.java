package com.jms.model;

/**
 *
 * @author Shanshan ZHAO
 */
public class Promotion {
    // Properties.
    private int id;
    private float percentage;
    private int rank;

    // Constructors.
    public Promotion(float pourcentage, int rank) {
        this.percentage = pourcentage;
        this.rank = rank;
    }

    public Promotion() {
    }

    // Override methods.
    @Override
    public String toString() {
        return "Promotion{" + "code=" + id + ", pourcentage=" + percentage + ", nieme=" + rank + '}';
    }

    // Getters and setters.
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
