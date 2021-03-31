package com.itgarden.controller;

import com.itgarden.entity.Customer;
import com.itgarden.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        customerService.save(customer);
        ResponseEntity<Customer> responseEntity = new ResponseEntity<>(customer, HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomer() {
        List<Customer> customers = null;
        customers = customerService.getAll();
        ResponseEntity<List<Customer>> responseEntity = new ResponseEntity<>(customers, HttpStatus.OK);
        return responseEntity;
    }
}
