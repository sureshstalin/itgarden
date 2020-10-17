package com.itgarden.entity;

import lombok.Data;

import java.util.List;

@Data
public class User extends BaseObject {

    private String emailId;

    private String firstName;

    private String middleName;

    private String lastName;

    private String mobileNo;

    private List<Address> addressList;

    private List<Role> roles;
}
