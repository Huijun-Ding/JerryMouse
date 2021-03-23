package com.jms.model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Shanshan ZHAO
 */
public class OrderLine {
    // Properties.
    private int id;
    private Date date;
    
    // Constructors.
    public OrderLine() {
    }

    public OrderLine(Date date) {
        this.date = date;
    }
    
    // Getters and setters.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    // Override methods
    @Override
    public String toString() {
        return "CommandLine{" + "id=" + id + ", date=" + date + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.date);
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
        final OrderLine other = (OrderLine) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }
}
