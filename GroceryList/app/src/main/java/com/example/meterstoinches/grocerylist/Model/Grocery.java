package com.example.meterstoinches.grocerylist.Model;

public class Grocery {
    private String name;
    private String quantity;
    private String dataItemAdded;
    private int id;
    public Grocery(){

    }

    public Grocery(String name, String quantity, String dataItemAdded, int id) {
        this.name = name;
        this.quantity = quantity;
        this.dataItemAdded = dataItemAdded;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDataItemAdded() {
        return dataItemAdded;
    }

    public void setDataItemAdded(String dataItemAdded) {
        this.dataItemAdded = dataItemAdded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
