package com.itgarden.controller;

import com.itgarden.entity.User;
import com.itgarden.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUsers(@RequestBody User user) {
        User newUser = userService.saveUser(user);
        ResponseEntity<User> responseEntity = new ResponseEntity<>(newUser, HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUser();
        ResponseEntity<List<User>> responseEntity = new ResponseEntity<>(users, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new  ResponseEntity<>(HttpStatus.OK);
    }
}
