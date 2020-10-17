package com.itgarden.entity;

import lombok.Data;

import java.util.List;

@Data
public class Role extends BaseObject {

    private String name;

    private String description;

    private List<User> users;
}
