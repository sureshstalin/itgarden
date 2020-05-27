package com.itgarden.restweb.service;

import com.itgarden.restweb.model.Customer;
import com.itgarden.restweb.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitAllService {

    CustomerService customerService;

    EmployeeService employeeService;

    TestService testService;

    @Autowired
    public InitAllService(CustomerService customerService, EmployeeService employeeService
    ,TestService testService) {
        System.out.println("Starts initializing all Services");
        this.customerService=customerService;
        this.employeeService=employeeService;
        this.testService=testService;
        System.out.println("Completed initializing all Services");
    }
}
