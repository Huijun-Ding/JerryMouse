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
 *
 * @author Jerry Mouse Software.
 */
@Entity
public class Reduce implements Serializable {

    // --------------------- PROPERTIES ---------------------
    /**
     * Id of Reduce.
     */
    @EmbeddedId
    private ReduceID reduceid;

    /**
     * Start date when a promotion was applied to a product.
     * <b>Format : jj/mm/aaaa</b>
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "DateDebutPromoP")
    private Date promoStartDate;

    /**
     * End date when a promotion was applied to a product.
     * <b>Format : jj/mm/aaaa</b>
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "DateFinPromoP")
    private Date promoEndDate;

    // -------------------- RELATION WITH PROMOTION --------------------
    /**
     * Promotion.
     */
    @ManyToOne
    @JoinColumn(name = "CodePR", insertable = false, updatable = false)
    private Promotion promotion;

    // -------------------- RELATION WITH PRODUCT --------------------
    /**
     * Product.
     */
    @ManyToOne
    @JoinColumn(name = "EANP", insertable = false, updatable = false)
    private Product product;

    // -------------------- CONSTRUCTORS --------------------
    /**
     * A constructor of the Reduce Class.
     *
     * @param startDatePromo Start date when a promotion was applied to a
     * product.
     * @param endDatePromo End date when a promotion was applied to a product.
     */
    public Reduce(Date startDatePromo, Date endDatePromo) {
        this.promoStartDate = startDatePromo;
        this.promoEndDate = endDatePromo;
    }

    public Reduce() {
    }

    // ----------------- GETTERS & SETTERS ------------------
    /**
     * Getter for the promoStartDate property.
     *
     * @return The promoStartDate property.
     */
    public Date getPromoStartDate() {
        return promoStartDate;
    }

    /**
     * Setter for the promoStartDate property.
     *
     * @param promoStartDate The new value to set to the property.
     */
    public void setPromoStartDate(Date promoStartDate) {
        this.promoStartDate = promoStartDate;
    }

    /**
     * Getter for the promoEndDate property.
     *
     * @return The promoEndDate property.
     */
    public Date getPromoEndDate() {
        return promoEndDate;
    }

    /**
     * Setter for the promoEndDate property.
     *
     * @param promoEndDate The new value to set to the property.
     */
    public void setPromoEndDate(Date promoEndDate) {
        this.promoEndDate = promoEndDate;
    }

    /**
     * Getter for the reduceid property.
     *
     * @return The reduceid property.
     */
    public ReduceID getReduceid() {
        return reduceid;
    }

    /**
     * Setter for the reduceid property.
     *
     * @param reduceid The new value to set to the property.
     */
    public void setReduceid(ReduceID reduceid) {
        this.reduceid = reduceid;
    }

    /**
     * Getter for the promotion property.
     *
     * @return The promotion property.
     */
    public Promotion getPromotion() {
        return promotion;
    }

    /**
     * Setter for the promotion property.
     *
     * @param promotion The new value to set to the property.
     */
    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    /**
     * Getter for the product property.
     *
     * @return The product property.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Setter for the product property.
     *
     * @param product The new value to set to the property.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    // ----------------------- METHODS ------------------------
    /**
     * Method which converts the current object into a String object.
     */
    @Override
    public String toString() {
        return "Reduce{" + "promoStartDate=" + promoStartDate + ", promoEndDate=" + promoEndDate + '}';
    }

    /**
     * Method which returns the hash code of the current object id.
     *
     * @return The hash code of the current object id.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.reduceid);
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
        final Reduce other = (Reduce) obj;
        if (!Objects.equals(this.reduceid, other.reduceid)) {
            return false;
        }
        return true;
    }

}
