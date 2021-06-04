package com.itgarden.controller;

import com.itgarden.entity.Customer;
import com.itgarden.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customers") // http://localhost:9092/api/customer
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.save(customer);
        return new ResponseEntity<Customer>(newCustomer, HttpStatus.CREATED);
    }


    @GetMapping("/customers")
        // http://localhost:9092/api/customers
    ResponseEntity<List<Customer>> getAllCustomers() throws Exception {
        List<Customer> customers = customerService.getCustomers();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    @GetMapping("/customers/{customerId}")
        // api/customer/20
    ResponseEntity<Customer> getCustomerById(@PathVariable Integer customerId) {

        Customer newCustomer = customerService.getCustomerById(customerId).orElse(null);
        return new ResponseEntity<Customer>(newCustomer, HttpStatus.OK);
    }

}
