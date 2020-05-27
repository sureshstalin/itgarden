package com.itgarden.restweb.service;

import com.itgarden.restweb.handler.DataHandler;
import com.itgarden.restweb.model.Employee;
import jdk.nashorn.internal.ir.EmptyNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    DataHandler dataHandler;

    @Autowired
    TestService(DataHandler dataHandler) {
        System.out.println("#### This is constructor injection example ###");
        this.dataHandler = dataHandler;
    }

    public void getCustomerData(String name) {
        List<Employee> employees = dataHandler.displayEmployee(name);
        System.out.println(employees);
    }

}
