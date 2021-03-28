package com.itgarden.repository;

import com.itgarden.entity.CustomerLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerLikesRepository extends JpaRepository<CustomerLikes,Long> {
}
