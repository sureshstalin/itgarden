package com.user.controller;

import com.user.entity.User;
import com.user.exception.DuplicateResourceFoundException;
import com.user.service.UserService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> addUser(
            @RequestBody User user) throws DuplicateResourceFoundException {

        User newUser = userService.save(user);
        ResponseEntity<User> responseEntity =
                new ResponseEntity<User>(newUser, HttpStatus.CREATED);
        return responseEntity;
    }
}
