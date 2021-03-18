package com.itgarden.controller;

import com.itgarden.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    ArrayList<Customer> customers = new ArrayList<>();
    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        logger.error("CustomerController::addCustomer::start.......");
        double fee = customer.getFee();
        double tax = (fee / 100) * 10;
        double total = fee + tax;
        customer.setTotalValue(total);
        customers.add(customer);
        ResponseEntity<Customer> responseEntity = new ResponseEntity<>(customer, HttpStatus.CREATED);
        logger.info("CustomerController::addCustomer::ends");
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomer() {
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }



}
