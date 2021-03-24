package com.jms.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 * The class OrderLine represents a order line which a product with certain
 * quantity.
 *
 * @author Jerry Mouse Software
 */
@Entity(name = "LigneCommande")
public class OrderLine {
    // --------------------- PROPERTIES ---------------------

    /**
     * Id of a order line.
     */
    @EmbeddedId
    private OrderLineId id;

    /**
     * Start date when a promotion was applied to a product.
     * <b>Format : jj/mm/aaaa</b>
     */
    @Column(name = "qteCommande")
    private int quantity;
    
    
    

}
