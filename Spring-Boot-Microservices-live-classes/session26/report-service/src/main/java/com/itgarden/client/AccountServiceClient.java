package com.itgarden.client;


import com.itgarden.model.CustomerReport;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "account-service") // http://localhost:9092/api/customers
public interface AccountServiceClient {

    @GetMapping("api/customers")
    public List<CustomerReport> getAllCustomerReport();

    @GetMapping("api/customers/{customerId}") //http://localhost:9092/api/customers/{customerId}
    public CustomerReport getCustomerReportById(@PathVariable ("customerId") int customerId);
}
