package com.itgarden.controller;

import com.itgarden.entity.Address;
import com.itgarden.entity.User;
import com.itgarden.model.UserInfo;
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
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/deletedFalse")
    public ResponseEntity<List<User>> getAllUsersDeletedFalse() {
        List<User> users = userService.findUsers();
        ResponseEntity<List<User>> responseEntity = new ResponseEntity<>(users, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/{userId}/address")
    public void addAddress(@PathVariable Long userId, @RequestBody Address address) {
        userService.saveAddress(userId, address);
    }

    @GetMapping("/address/{state}")
    public ResponseEntity<List<Address>> findByState(@PathVariable String state) {
        List<Address> addressList = userService.findByState(state);
        ResponseEntity<List<Address>> responseEntity = new ResponseEntity<>(addressList, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/address/stateList")
    public ResponseEntity<List<Address>> findByStateList(@RequestParam("stateList") List<String> stateList) {
        List<Address> addressList = userService.findStateList(stateList);
        ResponseEntity<List<Address>> responseEntity = new ResponseEntity<>(addressList, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/address/query/{state}")
    public ResponseEntity<List<Address>> findByStateQuery(@PathVariable String state) {
        List<Address> addressList = userService.findByState2(state);
        ResponseEntity<List<Address>> responseEntity = new ResponseEntity<>(addressList, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/address/query/stateList")
    public ResponseEntity<List<Address>> findByStateListQuery(@RequestParam("stateList") List<String> stateList) {
        List<Address> addressList = userService.findByStateListQuery(stateList);
        ResponseEntity<List<Address>> responseEntity = new ResponseEntity<>(addressList, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/like")
    public ResponseEntity<List<User>> findByEmailLike(@RequestParam("email") String email) {
        List<User> users = userService.findByEmailLike(email);
        ResponseEntity<List<User>> responseEntity = new ResponseEntity<>(users, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/like/deleteFalse")
    public ResponseEntity<List<User>>  findByEmailLikeAndDeletedFalse(@RequestParam("email") String email) {
        List<User> users = userService.findByEmailLikeAndDeletedFalse(email);
        ResponseEntity<List<User>> responseEntity = new ResponseEntity<>(users, HttpStatus.OK);
        return responseEntity;
    }
    @GetMapping("/userInfo/{userId}")
    public ResponseEntity<UserInfo> findUserInfo(@PathVariable("userId") Long userId) {
        UserInfo userInfo = userService.findUserInfoByUserId(userId);
        ResponseEntity<UserInfo> responseEntity = new ResponseEntity<>(userInfo, HttpStatus.OK);
        return responseEntity;
    }
}
