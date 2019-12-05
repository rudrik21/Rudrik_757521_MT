package com.example.rudrik_757521_mt;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Car implements Serializable {
    private String name;
    private int rent;
    private int days;
    private String age;
    private boolean isGPS;
    private boolean isChildSeat;
    private boolean isUnlimited;
    private double amount;
    private double total;

    static ArrayList<CarDetail> availableCars = new ArrayList<CarDetail>(){{
        add(new CarDetail("Please choose a car", -1));
        add(new CarDetail("BMW", 78));
        add(new CarDetail("Audi", 60));
        add(new CarDetail("Cadillac", 80));
        add(new CarDetail("Volks Wagon", 65));
        add(new CarDetail("Mercedes", 150));
        add(new CarDetail("Peugeot", 200));
    }};



    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    //  GETTERS & SETTERS

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public boolean isGPS() {
        return isGPS;
    }

    public void setGPS(boolean GPS) {
        isGPS = GPS;
    }

    public boolean isChildSeat() {
        return isChildSeat;
    }

    public void setChildSeat(boolean childSeat) {
        isChildSeat = childSeat;
    }

    public boolean isUnlimited() {
        return isUnlimited;
    }

    public void setUnlimited(boolean unlimited) {
        isUnlimited = unlimited;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = Double.parseDouble(new DecimalFormat("#.##").format(total));
    }
}


