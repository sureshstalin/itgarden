package com.itgarden.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WelcomeController {

    @GetMapping("/user")
    public String user() {
        return "This is User Page";
    }

    @GetMapping("/admin")
    public String admin() {
        return "This is Admin Page";
    }

    @GetMapping("/superadmin")
    public String superAdmin() {
        return "This is Super Admin Page";
    }

}


