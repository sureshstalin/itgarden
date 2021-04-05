package com.itgarden.userservice.service;

import com.itgarden.userservice.common.Constants;
import com.itgarden.userservice.entity.*;
import com.itgarden.userservice.repository.CustomerRepository;
import com.itgarden.userservice.repository.EmployeeRepository;
import com.itgarden.userservice.repository.RoleRepository;
import com.itgarden.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public Customer saveCustomer(Customer customer) {
        User user = customer.getUser();
        List<Address> addresses = user.getAddressList();
        addresses.get(0).setUser(user);

        Role role = roleRepository.findById(1).get();
        List<Role> roles = Arrays.asList(role);
        user.setRoles(roles);
        customer.getUser().setUserType(Constants.USER_TYPE_CUSTOMER);
        return customerRepository.save(customer);
    }

    public Employee saveEmployee(Employee employee) {
        User user = employee.getUser();
//        employee.getUser().getAddressList().get(0).setUser(user);
//        Role role = roleRepository.findById(2).get();
//        List<Role> roles = Arrays.asList(role);
//        user.setRoles(roles);
        user.setUserType(Constants.USER_TYPE_EMPLOYEE);
        return employeeRepository.save(employee);
    }

    public Customer findCustomerByType(String customerType) {
        return customerRepository.findCustomerByCustomerType(customerType);
    }

    public List<Customer> findCustomerByTypeIn(List<String> customerType) {
        return customerRepository.findCustomerByCustomerTypeIn(customerType);
    }

    public List<Customer> findCustomerByCustomerTypeQuery(List<String> customerTypes) {
        return customerRepository.findCustomerByCustomerTypeQuery(customerTypes);
    }

    public List<User> findUserByEmailIdLike(String email) {
        return  userRepository.findUserByEmailIdLike(email);
    }

    public List<User> findUserByEmailIdLikeQuery(String email) {
        return  userRepository.findUserByEmailIdLikeQuery(email);
    }

    public CustomerInfo findCustomerProfile(int customerId) {
        return  customerRepository.findCustomerProfile(customerId);
    }

    public Object[] findCustomerProfileNative(int customerId) {
        return  customerRepository.findCustomerProfileNative(customerId);
    }
}
