package com.itgarden.service;

import com.itgarden.entity.Address;
import com.itgarden.entity.Role;
import com.itgarden.entity.User;
import com.itgarden.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User saveUser(User user) {
        // Adding user object in address to set user id in address table.
        List<Address> addresses = user.getAddressList();
        addresses.forEach(addr -> {
            addr.setUser(user);
        });
        User newUser = userRepository.save(user);
        return newUser;
    }

    @Transactional
    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Transactional
    public void deleteUser(long userId) {
       userRepository.deleteById(userId);
    }

}
