package com.jms.model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Shanshan ZHAO
 */
public class Reduce {
    // Properties.
    private Date promoStartDate;
    private Date promoEndDate;
    
    //Constructors.
    public Reduce() {
    }

    public Reduce(Date startDatePromo, Date endDatePromo) {
        this.promoStartDate = startDatePromo;
        this.promoEndDate = endDatePromo;
    }
    
    // Getters and setters.
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
    
    // Override methods.
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
