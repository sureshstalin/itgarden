package com.validationtest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CustomerController {

    @PostMapping("/validate")
    public ResponseEntity<Customer> validate(@Valid @RequestBody Customer customer) {
        double totalValue = (customer.getFee()*10)/ 100;
        customer.setTotalValue(customer.getFee() + totalValue);
        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
    }
}
