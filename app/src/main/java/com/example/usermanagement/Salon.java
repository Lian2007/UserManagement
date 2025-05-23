package com.example.usermanagement;

import java.io.Serializable;
import java.util.ArrayList;

public class Salon implements Serializable {
    private String name;
    private String phone;
    private String type; // "men" or "women"
    private ArrayList<Treatment> treatments;

    public Salon(String name, String phone, String type, ArrayList<Treatment> treatments) {
        this.name = name;
        this.phone = phone;
        this.type = type;
        this.treatments = treatments;
    }

    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getType() { return type; }
    public ArrayList<Treatment> getTreatments() { return treatments; }
}
