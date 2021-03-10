package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @PostMapping("customer")
    public Customer getCustomer(@RequestBody Customer customer) {
        double total = (customer.getFee() * 10)/ 100;
        customer.setTotalValue(total);
        return customer;
    }
}
