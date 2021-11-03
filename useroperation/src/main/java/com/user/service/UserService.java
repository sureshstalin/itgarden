package com.user.service;

import com.user.entity.User;
import com.user.entity.UserPassword;
import com.user.exception.DuplicateResourceFoundException;
import com.user.exception.InvalidInputException;
import com.user.exception.ResourceNotFoundException;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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

    @Transactional
    public User save(User user) throws DuplicateResourceFoundException {
            Long id = user.getId();
        User existUser = userRepository.findUserByEmail(user.getEmail());
        if (id == null || id == 0) {
            if (existUser != null) {
                throw new DuplicateResourceFoundException("Email id already exist");
            }
            List<UserPassword> userPasswords = user.getUserPasswords();
            for (UserPassword userPassword: userPasswords) {
                userPassword.setUser(user);
                userPassword.setPassword(passwordEncoder.encode(userPassword.getPassword()));
            }
        } else {
            user.setLastModified(LocalDateTime.now());
        }
        return userRepository.save(user);
    }

//    public User save(User user) throws DuplicateResourceFoundException {
//        Long id = user.getId();
//        User existUser = userRepository.findUserByEmail(user.getEmail());
//        if (id == null || id == 0) {
//            if (existUser != null) {
//                throw new DuplicateResourceFoundException("Email id already exist");
//            }
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//        } else {
//            user.setLastModified(LocalDateTime.now());
//        }
//        return userRepository.save(user);
//    }

    @Transactional
    public User getUser(Long userId) throws InvalidInputException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser == null) {
            throw new ResourceNotFoundException("Resource not found");
        } else {
            User user = optionalUser.get();
//            List<UserPassword> userPasswords = user.getUserPasswords();
//            userPasswords.forEach(u -> u.setPassword(null));
//            user.setUserPasswords(userPasswords);
            return user;
        }

    }

    @Transactional
    public User getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(String.format("User is not found for the email %s ", email));
            throw new InvalidInputException(errorMessages);
        }
//        List<UserPassword> userPasswords = user.getUserPasswords();
//        userPasswords.forEach(u -> u.setPassword(null));
//        user.setUserPasswords(userPasswords);
        return user;
    }

    @Transactional
    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
//        users.forEach((u) -> u.setPassword(null));
//        for (User u: users) {
//            List<UserPassword> userPasswords = u.getUserPasswords();
//            userPasswords.forEach(up -> up.setPassword(null));
//            u.setUserPasswords(userPasswords);
//        }
        return users;
    }

    @Transactional
    public void deleteUserById(long userId) {
        userRepository.deleteById(userId);
    }

}
