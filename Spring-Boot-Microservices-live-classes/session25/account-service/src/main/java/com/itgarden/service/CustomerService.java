package com.itgarden.service;

import com.itgarden.entity.Customer;
import com.itgarden.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer save(Customer customer) {

        double fee = customer.getFee();
        double tax = (fee * 10) / 100;
        double totalValue = fee + tax;
        customer.setTotalValue(totalValue);
        customer.setTax(tax);
        Customer newCustomer = customerRepository.save(customer);
        return newCustomer;
    }

    public Optional<Customer> getCustomerById(Integer customerId) {
        return customerRepository.findById(customerId);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}
