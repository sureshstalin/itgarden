package com.itgarden.controller;

import com.itgarden.entity.GrandAuthorityRoleInfo;
import com.itgarden.entity.User;
import com.itgarden.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User userResponse = userService.save(user);
        return new ResponseEntity<User>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/profile")
    public String getUserData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        return  "Currently logged in user " + userName + userDetails.getAuthorities();
    }


}
