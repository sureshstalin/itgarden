package com.itgarden.controller;

import com.itgarden.client.AccountServiceClient;
import com.itgarden.model.CustomerReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private AccountServiceClient accountServiceClient;

    @GetMapping("/customers") // http://localhost:9094/api/reports/customers
    public ResponseEntity<List<CustomerReport>> getAllCustomerReport() {
        List<CustomerReport> customerReportList = accountServiceClient.getAllCustomerReport();

        // we have to generate report properly. we need to write some logic
        return new ResponseEntity<List<CustomerReport>>(customerReportList, HttpStatus.OK);
    }

}
