package com.jms.model;

/**
 *
 * @author carol
 */
public class ShoppingList {
    //proprieties
   private int id;
   private String name;
   //constructor

    public ShoppingList() {
    }

    public ShoppingList(int id, String name) {
        this.id = id;
        this.name = name;
    }
   
    //getter/setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //Override methods

    @Override
    public String toString() {
        return "ShoppingList{" + "id=" + id + ", name=" + name + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.id;
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
        final ShoppingList other = (ShoppingList) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
