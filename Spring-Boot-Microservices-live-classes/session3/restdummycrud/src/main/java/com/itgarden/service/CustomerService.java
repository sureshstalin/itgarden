package com.itgarden.service;

import com.itgarden.entity.Customer;
import com.itgarden.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer save(Customer customer) {

        Customer newCustomer = customerRepository.save(customer);
        return newCustomer;

    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(int customerId) {
        return customerRepository.findById(customerId);
    }

    public void deleteById(int customerId) {
        customerRepository.deleteById(customerId);
    }
}
