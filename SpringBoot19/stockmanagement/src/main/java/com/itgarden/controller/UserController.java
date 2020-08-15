package com.itgarden.controller;

import com.itgarden.exception.InvalidInputException;
import com.itgarden.exception.ResourceNotFoundException;
import com.itgarden.model.User;
import com.itgarden.service.UserService;
import com.itgarden.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        if (!CommonUtils.isValidLetters(user.getLastName())) {
            throw new InvalidInputException("Invalid Last Name");
        }

        User newUser = userService.save(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) throws Exception {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User is not found");
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping //api/users
    public ResponseEntity<List<User>> getAllUsers() throws Exception {
        List<User> users = userService.getAllUsers();
        try {
            if (users == null || users.size() == 0) {
                throw new ResourceNotFoundException("Users is not found");
            }
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<User>>(users, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("users-hateoas/{userId}")
    public ResponseEntity<EntityModel<User>> getUsersHateaos(@PathVariable Long userId) throws Exception {
        User user = userService.getUserById(userId);
        EntityModel<User> resource = EntityModel.of(user);
        try {
            if (user == null) {
                throw new ResourceNotFoundException("Users is not found");
            }
            WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
            resource.add(linkTo.withRel("view-all-users"));
            return new ResponseEntity<EntityModel<User>>(resource, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<EntityModel<User>>(resource, HttpStatus.NOT_FOUND);
        }
    }
}
