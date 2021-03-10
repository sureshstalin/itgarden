package com.itgarden.repository;

import com.itgarden.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
// CRUD = CREATE,READ,UPDATE AND DELETE
public interface CustomerRepository  extends JpaRepository<Customer,Integer> {


}
