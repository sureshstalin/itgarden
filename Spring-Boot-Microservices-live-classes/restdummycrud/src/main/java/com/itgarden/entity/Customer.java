package com.itgarden.entity;

public class Customer {

    private int id;
    private String name;
    private String address;
    private double fee;
    private double totalValue;

    public Customer(int id, String name, String address, double fee, double totalValue) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.fee = fee;
        this.totalValue = totalValue;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }
}
