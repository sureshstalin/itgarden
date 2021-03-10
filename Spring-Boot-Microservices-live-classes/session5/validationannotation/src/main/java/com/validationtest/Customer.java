package com.validationtest;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

public class Customer {

    @NotEmpty(message = "The Customer Name can't be empty")
    @Size(min = 5,max = 25,message = "Min size is 5 and max size is 25")
    private String name;

    @NotBlank(message = "The Customer name can't be blank")
    @NotEmpty(message = "The Customer Address can't be empty")
    private String address;

    @Min(value = 100)
    @Max(value = 5000)
    private double fee;

    private double totalValue;

    @Email
    private String email;

    @Range(message = "The Age range must be within 25 to 45",max = 45,min = 25)
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
