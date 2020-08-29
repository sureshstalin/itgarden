package com.itgarden.repository;

import com.itgarden.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Long> {

    // select * from address where state in ('ddd','ddd');
    List<Address> findByStateIn(List<String> stateList);
//  select * from address where state = 'ddd';
    List<Address> findByState(String state);

    @Query("select address from Address address where address.state in :stateList")
    List<Address> findByStateList(@Param("stateList") List<String> stateList);

    @Query("select address from Address address where address.state = :state")
    List<Address> findByState2(@Param("state") String state);


}
