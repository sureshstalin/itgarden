package com.itgarden.controller;

import com.itgarden.dto.UserDto;
import com.itgarden.entity.User;
import com.itgarden.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("api")
public class TestController {

    @PostMapping("/mapper") // http://localost:9090/api/mapper
    public ResponseEntity<Void> mapperTesting(@RequestBody UserDto userDto)  {
        User user = UserMapper.INSTANCE.userDTOtoUser(userDto);
        System.out.println("Entity Class......");
        System.out.println("Entity First Name " + user.getFirstName());
        System.out.println("Entity Last Name " + user.getLastName());
        UserDto userDto2 = UserMapper.INSTANCE.userToUserDTO(user);
        System.out.println("DTO Class......");
        System.out.println("DTO First Name " + userDto2.getFirstName());
        System.out.println("DTO Last Name " + userDto2.getLastName());
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}


