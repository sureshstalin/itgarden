package com.example.controller;

import com.example.entity.Customer;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @ApiOperation(value = "Adding a new  Customer ", response = Customer.class, notes="Adding new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK"),
            @ApiResponse(responseCode = "401", description = "not authorized!"),
            @ApiResponse(responseCode = "403", description = "forbidden!!!"),
            @ApiResponse(responseCode = "404", description = "not found!!!") })
    @PostMapping
    public ResponseEntity<Customer> getCustomer(
            @RequestBody Customer customer,   HttpServletRequest request) {
        double fee = customer.getFee();
        double total = (fee * 10) / 100;
        customer.setTotalValue(total);
        String countryName  = (String)request.getAttribute("country");
        System.out.println("country " + countryName);
        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
    }

    @GetMapping("/print")
    public ResponseEntity<String> print() {
        return new ResponseEntity<String>("This is test Response entity",HttpStatus.OK);
    }
    @GetMapping("/print/test")
    public ResponseEntity<String> printTEst() {
        return new ResponseEntity<String>("This is print test",HttpStatus.OK);
    }
}
