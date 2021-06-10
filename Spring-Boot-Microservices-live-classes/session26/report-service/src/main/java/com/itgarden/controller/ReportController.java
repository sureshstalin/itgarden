package com.itgarden.controller;

import com.itgarden.client.AccountServiceClient;
import com.itgarden.client.ProductServiceClient;
import com.itgarden.model.CustomerReport;
import com.itgarden.model.ProductReport;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private AccountServiceClient accountServiceClient;

    @Autowired
    private ProductServiceClient productServiceClient;


    // http://localhost:8081/report-service/api/reports/customers

    @GetMapping("/customers") // http://localhost:9094/api/reports/customers
    @Retry(name = "allCustomer", fallbackMethod = "showError")
    public ResponseEntity<List<CustomerReport>> getAllCustomerReport() throws Exception {
        logger.info("#### Report Service - This calling Account Service ##### ");
        List<CustomerReport> customerReportList = accountServiceClient.getAllCustomerReport();
        // we have to generate report properly. we need to write some logic
        return new ResponseEntity<List<CustomerReport>>(customerReportList, HttpStatus.OK);
    }

    @GetMapping("/customers/{customerId}") // http://localhost:9094/api/reports/customers
    @CircuitBreaker(name = "default", fallbackMethod = "showError")
    public ResponseEntity<CustomerReport> getCustomerById(@PathVariable("customerId") int customerId) {
        logger.info("************ This is API call method ************");
        CustomerReport customerReport = accountServiceClient.getCustomerReportById(customerId);
        // we have to generate report properly. we need to write some logic
        return new ResponseEntity<CustomerReport>(customerReport, HttpStatus.OK);
    }

    // http://localhost:8081/repor-service/api/reports/products
    @GetMapping("/products") // http://localhost:9094/api/reports/customers
    public ResponseEntity<List<ProductReport>> getAllProductReport() {
        logger.info("#### Report Service - Fetching all Products from Product Service ####");
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

    @GetMapping("/timelimiter") //http://localhost:9094/api/reports/timelimiter
    @TimeLimiter(name = "default")
    public CompletableFuture<Void> timeLimiter() {
        return CompletableFuture.runAsync(runnable);
    }

    @GetMapping("/ratelimiter")
    @RateLimiter(name = "default")
    public ResponseEntity<String> rateLimiter() {
        System.out.println("############ This is ratelimiter demo ############");
        return new ResponseEntity<String>("This is Ratelimiter Demo method", HttpStatus.OK);
    }

    @GetMapping("bulk")
    @Bulkhead(name = "mycalls",fallbackMethod = "showError")
    public ResponseEntity<String> bulkHeadDemo() throws  Exception{
        Thread.sleep(100);
        System.out.println("##### Response from Bulkhead #### ");
        return new ResponseEntity<String>("##### Response from Bulkhead ####", HttpStatus.OK);
    }

    public ResponseEntity<String> showError(Exception ex) {
        logger.info("###### This is default Response ####");
        return new ResponseEntity<String>("This is default response", HttpStatus.OK);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                System.out.println("Hello World");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

}

