package com.jms.model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author carol
 */
public class Have {

    private HaveId haveId;
    private int capacity;
    private Date date;

    //constructor

    public Have() {
    }

    public Have(HaveId haveId, int capacity, Date date) {
        this.haveId = haveId;
        this.capacity = capacity;
        this.date = date;
    }
    
    //getter/setter

    public HaveId getHaveId() {
        return haveId;
    }

    public void setHaveId(HaveId haveId) {
        this.haveId = haveId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    //Override methots

    @Override
    public String toString() {
        return "Have{" + "haveId=" + haveId + ", capacity=" + capacity + ", date=" + date + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.haveId);
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
        final Have other = (Have) obj;
        if (!Objects.equals(this.haveId, other.haveId)) {
            return false;
        }
        return true;
    }
    
}
