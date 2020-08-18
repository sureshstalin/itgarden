package com.itgarden.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itgarden.entity.Address;
import com.itgarden.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ConverToJson {


    public static void main(String[] args) throws  Exception{

        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User();
        Address address = new Address();
        address.setAddress1("1st Street");
        address.setAddress2("2nd Street");
        List<Address> addressList = new ArrayList<>();
        addressList.add(address);
        user.setAddressList(addressList);
        String jsonString = objectMapper.writeValueAsString(user);
        System.out.println(jsonString);

    }
}
