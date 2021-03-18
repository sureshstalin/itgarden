package com.itgarden.entity;

public class Customer {

    private String name;
    private String address;
    private double fee;
    private String email;
    private double totalValue;

    public Customer(String name, String address, double fee, String email, double totalValue) {
        this.name = name;
        this.address = address;
        this.fee = fee;
        this.email = email;
        this.totalValue = totalValue;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
