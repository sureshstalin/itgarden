package com.user.repository;


import com.user.entity.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtTokenRepository extends JpaRepository<JwtToken, String> {

    JwtToken findByUserName(String userName);
}
