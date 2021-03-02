package com.itgarden.repository;

import com.itgarden.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerRepository {

    List<Customer> customers = new ArrayList<>();
    int key = 0;
    public Customer save(Customer customer) {
        double fee = customer.getFee();
        if(customer.getId() == 0) {
            customer.setId(++key);
            double tax = (fee * 10)/100;
            double totalValue = fee + tax;
            customer.setTotalValue(totalValue);
            customers.add(customer);
        }else{
            Customer oldValueCustomer =
                    customers.stream().filter(c -> c.getId() == customer.getId()).findAny().get();
            double tax = (fee * 10)/100;
            double totalValue = fee + tax;
            customer.setTotalValue(totalValue);
            customers.remove(oldValueCustomer);
            customers.add(customer);
        }
        return customer;
    }

    public List<Customer> findAll() {
        return customers;
    }
    public Customer findById(int customerId) {
     Customer customer =
             customers.stream().filter(c -> c.getId() == customerId).findAny().get();

//        Customer customer = null;
//        for (Customer c: customers) {
//            if(c.getId() == customerId) {
//                customer = c;
//                break;
//            }
//        }
     return customer;
    }

    public void deleteById(int customerId) {
        Customer customer =
                customers.stream().filter(c -> c.getId() == customerId).findAny().get();
        customers.remove(customer);
    }
}
