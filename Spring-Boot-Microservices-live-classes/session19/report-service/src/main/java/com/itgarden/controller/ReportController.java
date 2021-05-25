package com.itgarden.controller;

import com.itgarden.client.AccountServiceClient;
import com.itgarden.client.ProductServiceClient;
import com.itgarden.model.CustomerReport;
import com.itgarden.model.ProductReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private AccountServiceClient accountServiceClient;

    @Autowired
    private ProductServiceClient productServiceClient;

    @GetMapping("/customers") // http://localhost:9094/api/reports/customers
    public ResponseEntity<List<CustomerReport>> getAllCustomerReport() {
        List<CustomerReport> customerReportList = accountServiceClient.getAllCustomerReport();
        // we have to generate report properly. we need to write some logic
        return new ResponseEntity<List<CustomerReport>>(customerReportList, HttpStatus.OK);
    }

    @GetMapping("/customers/{customerId}") // http://localhost:9094/api/reports/customers
    public ResponseEntity<CustomerReport> getCustomerById(@PathVariable("customerId") int customerId) {
        CustomerReport customerReport = accountServiceClient.getCustomerReportById(customerId);
        // we have to generate report properly. we need to write some logic
        return new ResponseEntity<CustomerReport>(customerReport, HttpStatus.OK);
    }

    @GetMapping("/products") // http://localhost:9094/api/reports/customers
    public ResponseEntity<List<ProductReport>> getAllProductReport() {
        List<ProductReport> productReportList = productServiceClient.getAllProductReport();
        // we have to generate report properly. we need to write some logic
        return new ResponseEntity<List<ProductReport>>(productReportList, HttpStatus.OK);
    }

    @GetMapping("/products/{productId}") // http://localhost:9094/api/reports/customers
    public ResponseEntity<ProductReport> getProductById(@PathVariable("productId") int productId) {
        ProductReport productReport = productServiceClient.getProductReportById(productId);
        // we have to generate report properly. we need to write some logic
        return new ResponseEntity<ProductReport>(productReport, HttpStatus.OK);
    }
}
