package com.user.controller;

import com.user.entity.UserPassword;
import com.user.model.UserPasswordRequest;
import com.user.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/password")
public class UserPasswordController {

    @Autowired
    private PasswordService passwordService;

    @PostMapping("/add")
    public ResponseEntity<UserPassword>
    addPassword(@RequestBody UserPasswordRequest userPasswordRequest,@RequestHeader("Authorization") String authString) {
        UserPassword userPassword = passwordService.addPassword(userPasswordRequest);
        ResponseEntity<UserPassword> responseEntity = new ResponseEntity<UserPassword>(userPassword, HttpStatus.CREATED);
        return responseEntity;
    }

    @PutMapping("/update")
    public ResponseEntity<UserPassword>
    updatePassword(@RequestBody UserPasswordRequest userPasswordRequest,@RequestHeader("Authorization") String authString) {
        UserPassword userPassword = passwordService.updatePassword(userPasswordRequest);
        ResponseEntity<UserPassword> responseEntity = new ResponseEntity<UserPassword>(userPassword, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/{userId}/{passwordId}")
    public ResponseEntity<UserPassword>
    viewPasswordByIdAndUserId(@PathVariable Long passwordId,@PathVariable Long userId,@RequestHeader("Authorization") String authString) {
        UserPassword userPassword = passwordService.getPasswordByUserIdAndPasswordId(passwordId,userId);
        ResponseEntity<UserPassword> responseEntity = new ResponseEntity<UserPassword>(userPassword, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserPassword>>
    viewPasswordByIdAndUserId(@PathVariable Long userId,@RequestHeader("Authorization") String authString) {
        List<UserPassword> userPasswords = passwordService.getPasswordByUserId(userId);
        ResponseEntity<List<UserPassword>> responseEntity = new ResponseEntity<List<UserPassword>>(userPasswords, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/{userId}/{passwordId}")
    public ResponseEntity<Void>
    deletePassword(@PathVariable Long passwordId,@PathVariable Long userId,@RequestHeader("Authorization") String authString) {
        passwordService.deletePasswordByIdAndUserId(passwordId,userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
