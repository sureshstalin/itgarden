package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SystemCodesController {

    @Autowired
    SystemCodes systemCodes;

    @GetMapping("/code")
    public List<String> getCountries() {
        List<String> codes = Arrays.asList(systemCodes.getCustomerCode(),
                systemCodes.getProductCode(),systemCodes.getVendorCode());
        return codes;
    }
}
