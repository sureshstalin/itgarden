package com.itgarden.service;

import com.itgarden.entity.Customer;
import com.itgarden.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        Customer newCustomer = customerRepository.save(customer);
        return newCustomer;
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(int customerId) throws Exception {
        Customer customer = customerRepository.findById(customerId).orElse(null);
//        if(customer == null) {
//            throw new ResourceNotFoundException("The Customer is not found for the id " + customerId);
//        }
        return customerRepository.findById(customerId).orElse(null);
    }

    public void deleteById(int customerId) {
        customerRepository.deleteById(customerId);
    }
}
