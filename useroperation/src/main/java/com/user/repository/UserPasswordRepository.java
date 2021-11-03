package com.user.repository;

import com.user.entity.UserPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserPasswordRepository extends JpaRepository<UserPassword, Long> {

    UserPassword findByIdAndUserId(Long id, Long userId);

    List<UserPassword> findByUserId(Long userId);

    @Modifying
    @Query(value = "DELETE FROM UserPassword up WHERE up.id = :id and up.user.id = :userId")
    void deleteUserPasswordByIdAndUser(Long id, Long userId);
}
