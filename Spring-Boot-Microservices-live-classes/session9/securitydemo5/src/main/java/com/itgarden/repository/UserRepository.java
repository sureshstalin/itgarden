package com.itgarden.repository;

import com.itgarden.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo, Integer> {
    UserInfo findByEmail(String email);
}
