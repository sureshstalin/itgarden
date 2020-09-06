package com.itgarden.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping // http://localhost:8080
    public String index() {
        return "This is index page";
    }
}
