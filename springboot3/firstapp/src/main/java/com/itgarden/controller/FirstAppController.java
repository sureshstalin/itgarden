package com.itgarden.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstAppController {


    @GetMapping("/print") // http://localhost:8081/print
    public String printData() {
        return "This is my first Restful Webservice";
    }
}
