package com.itgarden.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @GetMapping
    public String print() {
        return "This is get mapping";
    }

    @GetMapping("/user") // USER AND SUPERADMIN
    public String user() {
        return "This is User Page";
    }

    @GetMapping("/admin") // ADMIN AND SUPERADMIN
    public String admin() {
        return "This is Admin Page";
    }

    @GetMapping("/superadmin") // SUPERADMIN
    public String superAdmin() {
        return "This is Super Admin Page";
    }
}
