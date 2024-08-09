package com.example.blooddonation;

public class Donor {
    private String name;
    private String bloodGroup;
    private String city;

    public Donor(String name, String bloodGroup, String city) {
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getCity() {
        return city;
    }
}
