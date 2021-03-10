package com.itgarden.controller;

import com.itgarden.entity.Customer;
import com.itgarden.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customers") // http://localhost:8080/api/customers
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello hi";
    }
//    http://localhost:8080/api/customers POST HTTPSTATUS.CREATED 201

    @PostMapping
    public Customer addCustomer(@Valid @RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @GetMapping // http://localhost:8080/api/customers GET
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @PutMapping("/{customerId}")  // http://localhost:8080/api/customers/10 PUT
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @GetMapping("/{customerId}") // http://localhost:8080/api/customers/10 GET
    public Customer getCustomerById(@PathVariable int customerId) throws Exception{
        return customerService.getCustomerById(customerId);
    }

    @DeleteMapping("/{customerId}") // http://localhost:8080/api/customers/10 DELETE
    public void deleteCustomer(@PathVariable int customerId) {
        customerService.deleteById(customerId);
    }
}
