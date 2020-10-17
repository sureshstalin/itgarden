package com.itgarden.entity;

import lombok.Data;

@Data
public class Address extends BaseObject {

    private String address1;

    private String address2;

    private String city;

    private String state;

    private String country;

    private String landmark;

    private String mobile;

    private User user;
}
