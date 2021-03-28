package com.itgarden.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @GetMapping("/print")
    public String print() {
        return "This is get mapping";
    }

    @GetMapping
    public String defaultMethod() {
        return "This is default mapping";
    }
}


