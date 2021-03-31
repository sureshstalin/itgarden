package com.itgarden.service;

import com.itgarden.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {


    ArrayList<Customer> customers = new ArrayList<>();

    public Customer save(Customer customer) {
//        System.out.println("CustomerService::save::starts");
        double fee = customer.getFee();
        double tax = (fee / 100) * 10;
        double total = fee + tax;
        customer.setTotalValue(total);
        customers.add(customer);
//        System.out.println("CustomerService::save::ends");
        return customer;
    }

    public List<Customer> getAll() {
//        System.out.println("CustomerService::getAll::starts");
        customers.stream().forEach(customer -> System.out.println(customer.getName()));
//        System.out.println("CustomerService::getAll::ends");
        return customers;

    }
}
