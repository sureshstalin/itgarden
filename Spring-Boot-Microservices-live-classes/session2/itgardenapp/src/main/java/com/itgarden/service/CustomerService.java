package com.itgarden.service;

import com.itgarden.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    public Customer getCustomer(Customer customer) {
        int customerId = customer.getCustomerId();
        String name = customer.getCustomerName();
        String address = customer.getCustomerAddress();
        double fee = customer.getFee();
        double tax = (fee * 10)/100;
        double totalValue = fee + tax;
        Customer customerResponse = new Customer(customerId,name,address,fee,totalValue);
        return customerResponse;
    }
}
