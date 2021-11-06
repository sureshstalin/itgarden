package com.user.service;


import com.user.entity.User;
import com.user.entity.UserPassword;
import com.user.model.UserPasswordRequest;
import com.user.repository.UserPasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PasswordService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPasswordRepository userPasswordRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserPassword addPassword(UserPasswordRequest passwordRequest) {
        User user = userService.getUser(passwordRequest.getUserId());
        UserPassword userPassword = new UserPassword();
        userPassword.setPassword(passwordEncoder.encode(passwordRequest.getPassword()));
        userPassword.setAppName(passwordRequest.getAppName());
        user.getUserPasswords().add(userPassword);
        userPassword.setUser(user);
        UserPassword newUserPassword = userPasswordRepository.save(userPassword);
        return newUserPassword;
    }

    public UserPassword updatePassword(UserPasswordRequest userPasswordRequest) {

        Long passwordId = userPasswordRequest.getPasswordId();
        Long userId = userPasswordRequest.getUserId();
        UserPassword userPassword = userPasswordRepository.findByIdAndUserId(passwordId,userId);
        userPassword.setPassword(passwordEncoder.encode(userPasswordRequest.getPassword()));
        UserPassword newUserPassword = userPasswordRepository.save(userPassword);
        return newUserPassword;
    }

    public List<UserPassword> getPasswordByUserId(Long userId) {
        List<UserPassword> userPasswords = userPasswordRepository.findByUserId(userId);
        return userPasswords;
    }

    public UserPassword getPasswordByUserIdAndPasswordId(Long passwordId, Long userId) {
        UserPassword userPassword = userPasswordRepository.findByIdAndUserId(passwordId,userId);
        return userPassword;
    }

    @Transactional
    public void deletePasswordByIdAndUserId(Long passwordId,Long userId) {
        try {
            userPasswordRepository.deleteUserPasswordByIdAndUser(passwordId,userId);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
