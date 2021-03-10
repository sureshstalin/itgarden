package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.ListResourceBundle;

@RestController
@RequestMapping("/api")
public class SimpleController {

    @Autowired
    Simple simple;

    @GetMapping("/simple")
    public String simple() {
        return simple.getName();
    }
}
