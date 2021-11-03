package com.user.controller;

import com.user.entity.User;
import com.user.exception.DuplicateResourceFoundException;
import com.user.exception.InvalidInputException;
import com.user.model.ErrorMessage;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Rest API Standard purpose we are passing user id for update
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable("userId") Long userId,@RequestHeader("Authorization") String authString)
            throws DuplicateResourceFoundException {
        User newUser = userService.save(user);
        ResponseEntity<User> responseEntity =
                new ResponseEntity<User>(newUser, HttpStatus.OK);
        return responseEntity;
    }


    @GetMapping
    public ResponseEntity<?> getAllUser(@RequestHeader("Authorization") String authString) {
        List<User> userList = userService.getAllUser();
        ResponseEntity<List<User>> responseEntity =
                new ResponseEntity<List<User>>(userList, HttpStatus.OK);
        return responseEntity;
    }


    @GetMapping("/id/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") Long userId,@RequestHeader("Authorization") String authString) {
        User user = userService.getUser(userId);
        ResponseEntity<User> responseEntity =
                new ResponseEntity<User>(user, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/email/{emailId}")
    public ResponseEntity<?> getUserByEmailId(@PathVariable("emailId") String email,@RequestHeader("Authorization") String authString) {
        User user = userService.getUserByEmail(email);

        ResponseEntity<User> responseEntity =
                new ResponseEntity<User>(user, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable("userId") Long userId,@RequestHeader("Authorization") String authString) {
        userService.deleteUserById(userId);
        ResponseEntity<?> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        return responseEntity;
    }

}


