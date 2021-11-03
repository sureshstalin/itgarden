package com.user.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "password")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPassword implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "password")
    private String password;

    @Column(name = "app_name")
    private String appName;

    @ApiModelProperty(notes = "Created Date")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @ApiModelProperty(notes = "Modified Date")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "last_modified")
    private LocalDateTime lastModified;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
