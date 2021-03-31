package com.itgarden.userservice.controller;


import com.itgarden.userservice.entity.Customer;
import com.itgarden.userservice.entity.CustomerInfo;
import com.itgarden.userservice.entity.Employee;
import com.itgarden.userservice.entity.User;
import com.itgarden.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/customers")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        Customer newCustomer = userService.saveCustomer(customer);
        return new ResponseEntity<Customer>(newCustomer, HttpStatus.CREATED);
    }

    @PostMapping("/employees")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        Employee newEmployee = userService.saveEmployee(employee);
        return new ResponseEntity<Employee>(newEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/customers/{customerType}/type")
    public ResponseEntity<?> getAllCustomers(@PathVariable String customerType) {
        Customer customer = userService.findCustomerByType(customerType);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @GetMapping("/customers/type/in")
    public ResponseEntity<?> getAllCustomersTypesIn(@RequestParam("custType") List<String> customerTypes) {
        List<Customer> customers = userService.findCustomerByTypeIn(customerTypes);
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    @GetMapping("/customers/type/in/query")
    public ResponseEntity<?> getAllCustomersTypesInQuery(@RequestParam("custType") List<String> customerTypes) {
        List<Customer> customers = userService.findCustomerByCustomerTypeQuery(customerTypes);
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    @GetMapping("/like")
    public ResponseEntity<?> getAllUsersLike(@RequestParam("email") String email) {
        List<User> users = userService.findUserByEmailIdLike("%" + email + "%");
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping("/like/query")
    public ResponseEntity<?> getAllUsersLikeQuery(@RequestParam("email") String email) {
        List<User> users = userService.findUserByEmailIdLikeQuery(email);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping("/customers/profile/{customerId}")
    public ResponseEntity<?> getCustomerProfile(@PathVariable Integer customerId) {
        CustomerInfo customerInfo = userService.findCustomerProfile(customerId);
        return new ResponseEntity<CustomerInfo>(customerInfo, HttpStatus.OK);
    }

    @GetMapping("/customers/profile/native/{customerId}")
    public ResponseEntity<?> getCustomerProfileNative(@PathVariable Integer customerId) {
        Object[] customerInfo = userService.findCustomerProfileNative(customerId);
        return new ResponseEntity<Object[]>(customerInfo, HttpStatus.OK);
    }
}
