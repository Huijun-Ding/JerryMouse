package com.jms.model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author carol
 */
public class HaveId {
    //proprieties
    private Date hourStart;
    private int id;   //id of store
    //constructor

    public HaveId(Date hourStart, int id) {
        this.hourStart = hourStart;
        this.id = id;
    }

    public HaveId() {
    }
    
    // getter/setter

    public Date getHourStart() {
        return hourStart;
    }

    public void setHourStart(Date hourStart) {
        this.hourStart = hourStart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    // Override methods

    @Override
    public String toString() {
        return "HaveId{" + "hourStart=" + hourStart + ", id=" + id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.hourStart);
        hash = 67 * hash + this.id;
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
        final HaveId other = (HaveId) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.hourStart, other.hourStart)) {
            return false;
        }
        return true;
    }
    
}
