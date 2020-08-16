package com.itgarden.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long userId;

    @NotNull(message = "User Email can't be empty")
    @Column(name = "EMAIL",nullable = false)
    private String email;

    @NotNull(message = "First Name can't be empty")
    @Column(name = "FIRST_NAME",nullable = false)
    private String firstName;

    @NotNull(message = "Last Name can't be empty")
    @Column(name = "LAST_NAME",nullable = false)
    private String lastName;

    @Column(name = "CREATED_DATE",nullable = false)
    private Timestamp createdDate;

    @Column(name = "MODIFIED_DATE",nullable = true)
    private String modifiedDate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
