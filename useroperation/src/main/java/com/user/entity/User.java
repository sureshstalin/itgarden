package com.user.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {

    @ApiModelProperty(notes = "Auto generated User ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ApiModelProperty(notes = "Valid email id",required = true)
    @Column(name = "email")
    private String email;

    @ApiModelProperty(notes = "First Name",required = true)
    @Column(name = "first_name")
    private String firstName;

    @ApiModelProperty(notes = "Last Name",required = true)
    @Column(name = "last_name")
    private String lastName;

    @ApiModelProperty(notes = "Created Date")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @ApiModelProperty(notes = "Modified Date")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "last_modified")
    private LocalDateTime lastModified;

    @ApiModelProperty(notes = "Valid Mobile No",required = true)
    @Column(name = "mobile_no")
    private String mobileNo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserPassword> userPasswords;
}
