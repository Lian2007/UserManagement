package com.example.usermanagement;

import java.io.Serializable;

public class Treatment implements Serializable {  // <-- Implements Serializable!
    private int id;
    private String name;
    private int price;
    private String description;
    private double time;
    private String pic;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Treatment(String name, int price, String description, double time, String pic) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.time = time;
        this.pic = pic;
    }

    public Treatment() {
    }

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
