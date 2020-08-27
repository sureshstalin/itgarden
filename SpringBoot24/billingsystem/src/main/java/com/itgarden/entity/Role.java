package com.itgarden.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "ROLE")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "recId")
public class Role extends BaseObject {

    @Column(name = "ROLE_NAME")
    private String name;

    @Column(name = "ROLE_DESCRIPTION")
    private String description;

    @ManyToMany(targetEntity = User.class, mappedBy = "roles", cascade = CascadeType.ALL)
    private List<User> users;
}
