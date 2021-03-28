package com.itgarden.repository;

import com.itgarden.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserByEmailId(String emailId);
    // findUserByEmailId(String emailId);
}
