package com.itgarden.restweb.service;

import com.itgarden.restweb.model.Customer;
import com.itgarden.restweb.handler.DataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements BaseService{

    @Autowired
    private DataHandler dataHandler;

    public List<Customer> displayByName(String name) {
        return dataHandler.displayCustomer(name);
    }
}
