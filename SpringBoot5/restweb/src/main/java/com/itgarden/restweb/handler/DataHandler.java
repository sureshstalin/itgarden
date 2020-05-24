package com.itgarden.restweb.handler;

import com.itgarden.restweb.model.Customer;
import com.itgarden.restweb.model.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataHandler {
    List<Employee> employees = new ArrayList<>();
    List<Customer> customers = new ArrayList<>();

    public DataHandler() {

        populateCustomerData();
        populateEmployeeData();
    }

    public List<Employee> displayEmployee(String name) {
        List<Employee> result = employees.stream()
                .filter(e -> e.getEmployeeName().equalsIgnoreCase(name)).collect(Collectors.toList());
        return result;
    }

    public List<Customer> displayCustomer(String name) {
        List<Customer> result = customers.stream()
                .filter(c -> c.getCustomerName().equalsIgnoreCase(name)).collect(Collectors.toList());
        return result;
    }

    private void populateEmployeeData() {

        Employee employee1 = new Employee();
        employee1.setEmployeeId(1);
        employee1.setEmployeeName("Suresh");
        employee1.setDepartment("Accounts");
        employees.add(employee1);

        Employee employee2 = new Employee();
        employee2.setEmployeeId(2);
        employee2.setEmployeeName("Kesavan");
        employee2.setDepartment("Technology");
        employees.add(employee2);
    }

    private void populateCustomerData() {

        Customer customer1 = new Customer();
        customer1.setCustomerId("CUST001");
        customer1.setCustomerName("Raj Kumar");
        customer1.setCustomerType("Seller");
        customers.add(customer1);

        Customer customer2 = new Customer();
        customer2.setCustomerId("CUST002");
        customer2.setCustomerName("Kiran Ram");
        customer2.setCustomerType("Buyer");
        customers.add(customer2);
    }
}
