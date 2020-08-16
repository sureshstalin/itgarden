package com.itgarden.service;

import com.itgarden.model.User;
import com.itgarden.repository.UserRepository;
import com.itgarden.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User save(User user) {
        user.setCreatedDate(CommonUtils.getCurrentDateTime());
        return userRepository.save(user);
    }

    @Transactional
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
