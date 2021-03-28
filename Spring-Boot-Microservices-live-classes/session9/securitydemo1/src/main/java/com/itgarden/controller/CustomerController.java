package com.itgarden.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @GetMapping("/print")
    public String print() {
        return "This is get mapping";
    }

    @GetMapping // http://localhost:8080
    public String defaultMethod() {
        return "This is default mapping";
    }
}
