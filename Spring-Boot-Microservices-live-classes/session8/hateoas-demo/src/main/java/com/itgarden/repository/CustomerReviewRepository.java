package com.itgarden.repository;

import com.itgarden.entity.CustomerReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerReviewRepository extends JpaRepository<CustomerReview,Long> {
}
