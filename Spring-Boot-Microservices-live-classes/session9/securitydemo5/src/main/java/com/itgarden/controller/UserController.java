package com.itgarden.controller;

import com.itgarden.entity.UserInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {


    @GetMapping("/print")
    public String print() {
        return "This is print method";
    }

}
