package com.itgarden.controller;

import com.itgarden.entity.CustomerLikes;
import com.itgarden.entity.CustomerReview;
import com.itgarden.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    @PostMapping("/likes")
    public ResponseEntity<CustomerLikes> addCustomerLikes(@RequestBody CustomerLikes customerLikes) {
       CustomerLikes newCustomerLikes = customerService.saveLikes(customerLikes);
       return new ResponseEntity<CustomerLikes>(newCustomerLikes, HttpStatus.CREATED);
    }

    @PostMapping("/reviews")
    public ResponseEntity<CustomerReview> addCustomerReviews(@RequestBody CustomerReview customerReview) {
        CustomerReview newCustomerReview = customerService.saveReview(customerReview);
        return new ResponseEntity<CustomerReview>(newCustomerReview,HttpStatus.CREATED);
    }
}
