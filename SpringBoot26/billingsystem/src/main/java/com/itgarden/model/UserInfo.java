package com.itgarden.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class UserInfo implements Serializable {

    private String email;
    private String address1;
    private String city;
    private String state;
    private String mobile;
    private String country;

    public UserInfo() {

    }
    public UserInfo(String email,String address1,String city,String state,String mobile,String country) {
        this.email=email;
        this.address1=address1;
        this.city=city;
        this.state=state;
        this.mobile=mobile;
        this.country=country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
