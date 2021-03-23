package com.jms.model;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;

/**
 * TimeSlot Class.
 * @author Jerry Mouse Software.
 */
public class TimeSlot {

    // --------------------- PROPERTIES ---------------------

    /**
     * <Désignation_du_dictionnaire_des_données>.
     * <b>Rule(s) : <règle_ici></b>
     * <b>Format : <format_si_existe></b>
     * <b>Maximum size : <taille_si_existe></b>
     */
    private Date startTime;
    
    /**
     * <Désignation_du_dictionnaire_des_données>.
     * <b>Rule(s) : <règle_ici></b>
     * <b>Format : <format_si_existe></b>
     * <b>Maximum size : <taille_si_existe></b>
     */
    private Date endTime;

    /**
     * Hibernate join property with Store Class and Have Class.
     */
    @OneToMany(mappedBy = "pickUpTimes", cascade = CascadeType.ALL)
    @MapKeyJoinColumn(name = "CodeM")
    private Map<Store, Have> stores;

    // -------------------- CONSTRUCTORS --------------------

    public TimeSlot() {
    }

    // ----------------- GETTERS & SETTERS ------------------
    
    
    
    // ----------------------- METHODS ----------------------
    
}
