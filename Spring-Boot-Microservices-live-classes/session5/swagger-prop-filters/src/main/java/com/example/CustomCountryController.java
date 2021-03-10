package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomCountryController {

    @Autowired
    Custom custom;

    @GetMapping("/countries")
    public List<String> getCountries() {
        List<String> countries = Arrays.asList(custom.getUsa(), custom.getIndia(), custom.getUk());
        return countries;
    }
}
