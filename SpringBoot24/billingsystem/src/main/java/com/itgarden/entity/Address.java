package com.itgarden.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ADDRESS")
public class Address extends BaseObject {

    @Column(name = "ADDRESS1", nullable = false)
    private String address1;

    @Column(name = "ADDRESS2", nullable = true)
    private String address2;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "STATE", nullable = false)
    private String state;

    @Column(name = "COUNTRY", nullable = false)
    private String country;

    @Column(name = "LANDMARK", nullable = true)
    private String landmark;

    @Column(name = "MOBILE", nullable = false)
    private String mobile;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    @JsonIgnore
    private User user;
}
