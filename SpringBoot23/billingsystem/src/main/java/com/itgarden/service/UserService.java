package com.itgarden.service;

import com.itgarden.entity.Address;
import com.itgarden.entity.User;
import com.itgarden.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        User newUser = userRepository.save(user);
        return newUser;
    }
}
