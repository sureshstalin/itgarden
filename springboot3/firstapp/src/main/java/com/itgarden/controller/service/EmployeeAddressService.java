package com.itgarden.controller.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Primary
public class EmployeeAddressService implements AddressService {

    @Override
    public String getAddress() {
        return "Thi is from Employee Address Service";
    }
}
