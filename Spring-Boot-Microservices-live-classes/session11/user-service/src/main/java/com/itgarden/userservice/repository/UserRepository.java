package com.itgarden.userservice.repository;


import com.itgarden.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findUserByEmailIdLike(String likeValue);

    @Query("select u from User u where u.emailId like %:emailId%")
    List<User> findUserByEmailIdLikeQuery(@Param("emailId") String emailId);

}
