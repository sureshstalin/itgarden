package com.itgarden.controller;

import com.itgarden.entity.Address;
import com.itgarden.entity.User;
import com.itgarden.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    public ResponseEntity<User> saveUsers(@RequestBody User user) {
        User newUser = userService.saveUser(user);
        ResponseEntity<User> responseEntity = new ResponseEntity<>(newUser, HttpStatus.CREATED);
        return responseEntity;
    }
}
