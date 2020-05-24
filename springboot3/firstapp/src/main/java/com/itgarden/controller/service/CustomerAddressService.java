package com.itgarden.controller.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public class CustomerAddressService implements AddressService {

    @Override
    public String getAddress() {
        return "Test Address for Customer";
    }
}
