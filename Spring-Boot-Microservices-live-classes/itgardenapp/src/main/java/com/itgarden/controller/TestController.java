package com.itgarden.controller;

import com.itgarden.entity.Customer;
import com.itgarden.service.CustomerService;
import com.itgarden.service.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    Product product;

    @GetMapping  // http://localhost:8080
    public String print(@RequestParam(value = "color",required = false) String color) {
        System.out.println("This is print method");
        if(color != null) {
            return "You are passing color as " + color.toUpperCase();
        }else{
            return "You are not passing color";
        }
    }

    @GetMapping("display") // http://localhost:8080/display
    public String display() {
        System.out.println("This is display method");
        return "This is display method call";
    }

    @PostMapping("send")
    public Customer send(@RequestBody Customer customer) {
        return customerService.getCustomer(customer);
    }
    @PostMapping("send-path/{customerName}")
    public String send(@PathVariable String customerName) {
        return "You have passed customer name is " + customerName;
    }

    @PutMapping("puttest")
    public String puttTest() {

        return "This is Put Mapping";
    }

    @DeleteMapping("deletest")
    public String deleteTest() {

        return "This is Delete Mapping";
    }

    @GetMapping("product")
    public String getProduct() {
        return product.getName();
    }
}
