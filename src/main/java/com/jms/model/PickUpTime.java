package com.jms.model;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;

/**
 * PickUpTime Class.
 * @author Jerry Mouse Software.
 */
public class PickUpTime {

    // --------------------- PROPERTIES ---------------------

    private Date startTime;
    private Date endTime;

    /**
     * Hibernate join property with Store Class and Have Class.
     */
    @OneToMany(mappedBy = "pickUpTimes", cascade = CascadeType.ALL)
    @MapKeyJoinColumn(name = "CodeM")
    private Map<Store, Have> stores;

    // -------------------- CONSTRUCTORS --------------------

    public PickUpTime() {
    }

    // ----------------- GETTERS & SETTERS ------------------
    
    
    
    // ----------------------- METHODS ----------------------
    
}
