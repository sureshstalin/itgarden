package com.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;



@Getter
@Setter
@Table(name = "jwttoken")
@Entity
public class JwtToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "access_token_expiration")
    private LocalDateTime accessTokenExpiration;

    @Column(name = "refresh_token_expiration")
    private LocalDateTime refreshTokenExpiration;
}
