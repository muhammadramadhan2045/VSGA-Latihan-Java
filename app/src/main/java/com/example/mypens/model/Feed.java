package com.example.mypens.model;

import androidx.annotation.NonNull;

public class Feed {
    private int id;
    private String nrp;
    private String name;
    private String date;
    private String gender;
    private String address;


    public Feed(
            int id,
            String nrp,
            String name,
            String date,
            String gender,
            String address
    ) {
        this.id = id;
        this.nrp = nrp;
        this.name = name;
        this.date = date;
        this.gender = gender;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    //getters and setters nrp
    public String getNrp() {
        return nrp;
    }

    public void setNrp(String nrp) {
        this.nrp = nrp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getGender() {
        return gender;
    }

    //setters and getters
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NonNull
    @Override
    public String toString() {
        return "Feed{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                '}';

    }
}
    