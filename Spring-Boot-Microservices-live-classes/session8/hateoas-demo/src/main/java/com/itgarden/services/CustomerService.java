package com.itgarden.services;

import com.itgarden.entity.CustomerLikes;
import com.itgarden.entity.CustomerReview;
import com.itgarden.repository.CustomerLikesRepository;
import com.itgarden.repository.CustomerReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerLikesRepository customerLikesRepository;

    @Autowired
    private CustomerReviewRepository customerReviewRepository;

    public CustomerLikes saveLikes(CustomerLikes customerLikes) {
        return customerLikesRepository.save(customerLikes);
    }

    public CustomerReview saveReview(CustomerReview customerReview) {
        return customerReviewRepository.save(customerReview);
    }

    public Long getLikesCount() {
      return   customerLikesRepository.count();
    }

    public List<CustomerReview> getAllReviews() {
        return customerReviewRepository.findAll();
    }
}
