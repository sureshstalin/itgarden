package com.itgarden.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto extends BaseDto {

    private String emailId;

    private String firstName;

    private String middleName;

    private String lastName;

    private String mobileNo;

    private String flowType;

    private List<AddressDto> addressList;

    private List<RoleDto> roles;


}
