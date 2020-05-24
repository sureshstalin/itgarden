package com.itgarden.restweb.service;

import com.itgarden.restweb.model.Employee;
import com.itgarden.restweb.handler.DataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    DataHandler dataHandler;

    public List<Employee> displayByName(String name) {

        return dataHandler.displayEmployee(name);
    }
}
