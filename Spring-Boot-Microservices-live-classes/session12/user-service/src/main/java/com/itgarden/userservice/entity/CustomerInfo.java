package com.itgarden.userservice.entity;

import javax.persistence.Column;

public class CustomerInfo {

    private int id;

    private String customerType;

    private String customerProfile;

    public CustomerInfo(int id, String customerType, String customerProfile) {
        this.id = id;
        this.customerType = customerType;
        this.customerProfile = customerProfile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerProfile() {
        return customerProfile;
    }

    public void setCustomerProfile(String customerProfile) {
        this.customerProfile = customerProfile;
    }
}
