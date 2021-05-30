package com.itgarden.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/external")
public class BillingController {


    @GetMapping("/billing") // http://localhost:8083/api/external/billing
    public String getBilling() {
        return "This is billing Service";
    }
}
