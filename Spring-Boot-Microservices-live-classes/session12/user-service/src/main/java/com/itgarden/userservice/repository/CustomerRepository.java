package com.itgarden.userservice.repository;

import com.itgarden.userservice.entity.Customer;
import com.itgarden.userservice.entity.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findCustomerByCustomerType(String customerType);

    // select * from customer where customer_type in ('type1',type2');
    List<Customer> findCustomerByCustomerTypeIn(List<String> types);

    //        select * from customer where customer_type in ('type1',type2');
    @Query("select c from Customer c where c.customerType in :customerType")
    List<Customer> findCustomerByCustomerTypeQuery(@Param("customerType") List<String> customerTypes);

        @Query("select new com.itgarden.userservice.entity.CustomerInfo( c.id,c.customerType,c.customerProfile)" +
                " from Customer c where c.id=:customerId")
        CustomerInfo findCustomerProfile(@Param("customerId") int profileId);

//    @Query("select  c.id,c.customerProfile,c.customerType from Customer c where c.id=:customerId")
//    Object[] findCustomerProfile(@Param("customerId") int profileId);
//    // object[0]

        @Query(value = "SELECT * from customer WHERE ID=?1",nativeQuery = true)
        Object[] findCustomerProfileNative(@Param("id") int id);

}
