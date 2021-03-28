package com.itgarden.entity;

import com.fasterxml.jackson.annotation.JsonInclude;


/*
 * Created by Suresh Stalin on 06 / Nov / 2020.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticationRequestInfo {

    private String userName;
    private String password;
    private String refreshToken;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
