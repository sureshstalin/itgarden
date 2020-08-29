package com.itgarden.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "USER")
public class User extends BaseObject {

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Address> addressList;

    @ManyToMany(targetEntity = Role.class,cascade =
            {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name="user_roles",
            joinColumns=
            @JoinColumn( name="user_id", referencedColumnName="REC_ID"),
            inverseJoinColumns=@JoinColumn(name="role_id", referencedColumnName="REC_ID"))
    private List<Role> roles;
}
