package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultPropController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${country.name}")
    private String countryName;

    @GetMapping("appname")
    public String getApplicationName() {
        return applicationName + ", " + countryName;
    }
}
