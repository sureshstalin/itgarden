package com.itgarden.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDto implements Serializable {

    protected  Long id;
    protected String flowType;
}
