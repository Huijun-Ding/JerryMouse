/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jms.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The class OrderLineId represents a combinated id for a order line (Product's
 * id and Order's Id).
 *
 * @author Jerry Mouse Software
 */
@Embeddable
public class OrderLineId {

    // --------------------- PROPERTIES ---------------------
    @Column(name = "EANP")
    private String ean;

    @Column(name = "CodeCD")
    private int orderId;
}
