package com.itgarden.restweb.controller;

import com.itgarden.restweb.model.Customer;
import com.itgarden.restweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/display") // http://localhost:8080/api/customer/display?name=kesavan
    public List<Customer> displayCustomer(@RequestParam("name") String name) {
        List<Customer> result = customerService.displayByName(name);
        return result;
    }
}
