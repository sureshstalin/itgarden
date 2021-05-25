package com.itgarden.client;


import com.itgarden.model.CustomerReport;
import com.itgarden.model.ProductReport;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service") // http://localhost:9093
public interface ProductServiceClient {

    @GetMapping("api/products") //http://localhost:9093/api/products/
    public List<ProductReport> getAllProductReport();

    @GetMapping("api/products/{productId}") //http://localhost:9093/api/products/{productId}
    public ProductReport getProductReportById(@PathVariable ("productId") int productId);
}
