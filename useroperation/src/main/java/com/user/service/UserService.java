package com.user.service;

import com.user.entity.User;
import com.user.exception.DuplicateResourceFoundException;
import com.user.exception.InvalidInputException;
import com.user.exception.ResourceNotFoundException;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service

public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user) throws DuplicateResourceFoundException {
        Long id = user.getId();
        User existUser = userRepository.findUserByEmail(user.getEmail());
        if (id == null || id == 0) {
            if (existUser != null) {
                throw new DuplicateResourceFoundException("Email id already exist");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            boolean result = passwordEncoder.matches("123", user.getPassword());
//            System.out.println("The password is  " + result);
            user.setDateCreated(LocalDateTime.now());
        } else {
            user.setLastModified(LocalDateTime.now());
        }
        return userRepository.save(user);
    }

    public User getUser(Long userId) throws InvalidInputException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser == null) {
            throw new ResourceNotFoundException("Resource not found");
        }else {
            User user  = optionalUser.get();
            user.setPassword(null);
            return user;
        }

    }

    public User getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        user.setPassword(null);
        return user;
    }

    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        users.forEach((u) -> u.setPassword(null));
        return users;
    }

    public void deleteUserById(long userId) {
        userRepository.deleteById(userId);
    }

}
