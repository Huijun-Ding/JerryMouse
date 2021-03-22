package com.jms.model;

/**
 *
 * @author carol
 */
public class PostIt {
    //proprieties
    private int id;
    private String wording;
    //constructor

    public PostIt() {
    }

    public PostIt(String wording) {
        this.wording = wording;
    }
    //getter/setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }
    //Override methods

    @Override
    public String toString() {
        return "PostIt{" + "id=" + id + ", wording=" + wording + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final PostIt other = (PostIt) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
