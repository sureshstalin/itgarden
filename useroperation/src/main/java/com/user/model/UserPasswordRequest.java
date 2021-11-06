package com.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPasswordRequest {

    private Long passwordId;
    private Long userId;
    private String password;
    private String appName;

}
