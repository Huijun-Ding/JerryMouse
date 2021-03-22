package com.jms.model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author carol
 */
public class PickupTime {
    //proprieties
    private Date hourStart;
    private Date hourFin;
    //constructor

    public PickupTime() {
    }

    public PickupTime(Date hourStart, Date hourFin) {
        this.hourStart = hourStart;
        this.hourFin = hourFin;
    }
    //getter/setter

    public Date getHourStart() {
        return hourStart;
    }

    public void setHourStart(Date hourStart) {
        this.hourStart = hourStart;
    }

    public Date getHourFin() {
        return hourFin;
    }

    public void setHourFin(Date hourFin) {
        this.hourFin = hourFin;
    }
//

    @Override
    public String toString() {
        return "PickupTime{" + "hourStart=" + hourStart + ", hourFin=" + hourFin + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.hourStart);
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
        final PickupTime other = (PickupTime) obj;
        if (!Objects.equals(this.hourStart, other.hourStart)) {
            return false;
        }
        return true;
    }
    
}
