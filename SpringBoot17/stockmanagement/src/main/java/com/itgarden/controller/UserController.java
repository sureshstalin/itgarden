package com.itgarden.controller;

import com.itgarden.exception.InvalidInputException;
import com.itgarden.model.User;
import com.itgarden.service.UserService;
import com.itgarden.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) throws Exception {
        if (!CommonUtils.isValidEmailAddress(user.getEmail())) {
            throw new InvalidInputException("Invalid Email Id");
        }
        if (!CommonUtils.isValidLetters(user.getFirstName())) {
            throw new InvalidInputException("Invalid First Name");
        }
        if (!CommonUtils.isValidLetters(user.getFirstName())) {
            throw new InvalidInputException("Invalid Last Name");
        }
        User newUser = userService.save(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
