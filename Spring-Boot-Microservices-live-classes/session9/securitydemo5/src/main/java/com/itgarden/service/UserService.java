package com.itgarden.service;

import com.itgarden.entity.UserInfo;
import com.itgarden.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserInfo save(UserInfo userInfo) {
        return userRepository.save(userInfo);
    }

    public UserInfo getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
