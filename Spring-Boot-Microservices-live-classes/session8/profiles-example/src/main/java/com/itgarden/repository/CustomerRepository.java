package com.itgarden.repository;

import com.itgarden.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

// CRUD = CREATE,READ,UPDATE AND DELETE
public interface CustomerRepository  extends JpaRepository<Customer,Integer> {


}
