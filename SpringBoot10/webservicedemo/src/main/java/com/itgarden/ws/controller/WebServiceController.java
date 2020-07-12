package com.itgarden.ws.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employee")
public class WebServiceController {

    @PostMapping("")
    public String createEmployee() {

    }

    @GetMapping("/employees/{id}")
    public String viewEmployee() {

    }

    @PutMapping("/employees/{id}")
    public String updateObject() {

    }

    @DeleteMapping("/employees/{id}")
    public String deleteObject() {

    }

    @PatchMapping("/employees/{id}")
    public String patchObject() {

    }
}
