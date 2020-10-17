package com.itgarden.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDto extends BaseDto {

    private String address1;

    private String address2;

    private String city;

    private String state;

    private String country;

    private String landmark;

    private String mobile;
}
