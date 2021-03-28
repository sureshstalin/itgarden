package com.itgarden.controller;

import com.itgarden.entity.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {


    @PostMapping("/print")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        double fee = customer.getFee();
        double total = (fee*10)/100;
        customer.setTotalValue(total);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
}
