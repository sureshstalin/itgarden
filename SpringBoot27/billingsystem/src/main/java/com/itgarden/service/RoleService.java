package com.itgarden.service;

import com.itgarden.entity.Role;
import com.itgarden.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public Role saveRole(Role role) {
        Role newRole = roleRepository.save(role);
        return newRole;
    }
}
