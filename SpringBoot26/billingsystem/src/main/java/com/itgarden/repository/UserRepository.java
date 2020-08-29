package com.itgarden.repository;

import com.itgarden.entity.User;
import com.itgarden.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

        List<User> findByDeletedFalse();

        List<User> findByEmailLike(String email);

        List<User> findByEmailLikeAndDeletedFalse(String email);

        @Query("select new com.itgarden.model.UserInfo(u.email,a.address1,a.city,a.state,a.mobile,a.country) " +
                "from User u,Address a where u.recId=:userId and a.user.recId=u.recId")
        UserInfo findUserInfoByUserId(@Param("userId") long userId);
}
