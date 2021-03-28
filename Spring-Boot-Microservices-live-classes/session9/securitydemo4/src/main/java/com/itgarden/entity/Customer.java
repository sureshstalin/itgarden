package com.itgarden.entity;

public class Customer {

    private String name;
    private String address;
    private double fee;
    private double totalValue;
    private String email;

    public Customer(String name, String address, double fee, double totalValue,String email) {
        this.name = name;
        this.address = address;
        this.fee = fee;
        this.totalValue = totalValue;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
