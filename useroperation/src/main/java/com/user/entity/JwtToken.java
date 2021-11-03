package com.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;



@Getter
@Setter
@Table(name = "jwttoken")
@Entity
public class JwtToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "access_token")
    private String accessToken;

    @JsonIgnore
    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "access_token_expiration")
    private LocalDateTime accessTokenExpiration;

    @JsonIgnore
    @Column(name = "refresh_token_expiration")
    private LocalDateTime refreshTokenExpiration;


}
