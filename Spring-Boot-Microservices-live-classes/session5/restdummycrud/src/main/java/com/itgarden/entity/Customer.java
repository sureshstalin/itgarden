package com.itgarden.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "CUSTOMER")
public class Customer {
    public Customer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "The Customer Name can't be empty")
    @Column(name = "name")
    private String name;
    @NotEmpty(message = "The Customer Address can't be empty")
    @Column(name = "address")
    private String address;

    @Column(name = "fee")
    @Min(value = 100)
    private double fee;
    @Column(name = "total")
    private double totalValue;

    public Customer(String name, String address, double fee, double totalValue) {
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
