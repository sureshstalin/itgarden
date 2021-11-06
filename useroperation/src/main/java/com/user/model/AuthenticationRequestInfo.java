package com.user.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class    AuthenticationRequestInfo {

    @NotEmpty(message = "User Name can't be Empty")
    private String userName;

    @NotEmpty(message = "Password Name can't be Empty")
    private String password;


}
