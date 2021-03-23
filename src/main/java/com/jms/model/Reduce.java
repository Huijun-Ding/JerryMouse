package com.jms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 * Reduce Class.
 * @author Jerry Mouse Software.
 */
@Entity
public class Reduce implements Serializable{
    // --------------------- PROPERTIES ---------------------
    @EmbeddedId
    private ReduceID reduceid;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column (name = "DateDebutPromoP")
    private Date promoStartDate;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column (name = "DateFinPromoP")
    private Date promoEndDate;
    
    //-----------Relation with Promotion--------------
    @ManyToOne 
    @JoinColumn(name = "CodePR", insertable = false, updatable = false)
    private Promotion promotion;
    
    //-----------Relation with Product--------------
    @ManyToOne 
    @JoinColumn(name = "EANP", insertable = false, updatable = false)
    private Product product;
    
    // -------------------- CONSTRUCTORS --------------------
    public Reduce() {
    }

    public Reduce(Date startDatePromo, Date endDatePromo) {
        this.promoStartDate = startDatePromo;
        this.promoEndDate = endDatePromo;
    }
    
    // ----------------- GETTERS & SETTERS ------------------
    public Date getPromoStartDate() {
        return promoStartDate;
    }

    public void setPromoStartDate(Date promoStartDate) {
        this.promoStartDate = promoStartDate;
    }

    public Date getPromoEndDate() {
        return promoEndDate;
    }

    public void setPromoEndDate(Date promoEndDate) {
        this.promoEndDate = promoEndDate;
    }
    
    // ----------------------- METHODS ------------------------
    @Override
    public String toString() {
        return "Reduce{" + "promoStartDate=" + promoStartDate + ", promoEndDate=" + promoEndDate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.promoStartDate);
        hash = 83 * hash + Objects.hashCode(this.promoEndDate);
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
        final Reduce other = (Reduce) obj;
        if (!Objects.equals(this.promoStartDate, other.promoStartDate)) {
            return false;
        }
        if (!Objects.equals(this.promoEndDate, other.promoEndDate)) {
            return false;
        }
        return true;
    }
    
}
