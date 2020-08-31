package com.itgarden.service;

import com.itgarden.entity.Address;
import com.itgarden.entity.Role;
import com.itgarden.entity.User;
import com.itgarden.model.UserInfo;
import com.itgarden.repository.AddressRepository;
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

    @Autowired
    private AddressRepository addressRepository;

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

    @Transactional
    public List<User> findUsers() {
        return userRepository.findByDeletedFalse();
    }

    @Transactional
    public void saveAddress(Long userid, Address address) {
        User user = userRepository.findById(userid).get();
        address.setUser(user);
        addressRepository.save(address);
    }

    @Transactional
    public List<Address> findStateList(List<String> stateList) {

        return addressRepository.findByStateIn(stateList);
    }

    @Transactional
    public List<Address> findByState(String state) {
        return addressRepository.findByState(state);
    }

    @Transactional
    public List<Address> findByStateListQuery(List<String> stateList) {
        return addressRepository.findByStateList(stateList);
    }

    @Transactional
    public List<Address> findByState2(String state) {
        return addressRepository.findByState2(state);
    }

    @Transactional
    public List<User> findByEmailLike(String email) {
        return userRepository.findByEmailLike("%" + email + "%");
    }

    @Transactional
    public UserInfo findUserInfoByUserId(long userId) {
        return userRepository.findUserInfoByUserId(userId);
    }

    @Transactional
    public List<User> findByEmailLikeAndDeletedFalse(String email) {
        return userRepository.findByEmailLikeAndDeletedFalse("%" + email + "%");
    }
}
