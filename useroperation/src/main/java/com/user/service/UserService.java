package com.user.service;

import com.user.entity.User;
import com.user.exception.DuplicateResourceFoundException;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) throws DuplicateResourceFoundException {
        Long id = user.getId();
        User existUser = userRepository.findUserByEmail(user.getEmail());
        if (id == null || id == 0) {
            if (existUser != null) {
                throw new DuplicateResourceFoundException("Email id already exist");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setDateCreated(LocalDateTime.now());
        } else {
            user.setLastModified(LocalDateTime.now());
        }
        return userRepository.save(user);
    }

    public User getUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return user;
    }

    public User getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return user;
    }

    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public void deleteUserById(long userId) {
        userRepository.deleteById(userId);
    }

}
