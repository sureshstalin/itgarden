package com.itgarden.controller;

import com.itgarden.model.Product;
import com.itgarden.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/products")
public class StockManagementController {


    @Autowired
    private ProductService productService;


    @PostMapping("/add") // http://localhost:8080/api/products/add
    public void add(@RequestBody Product product) {
        productService.saveProduct(product);
    }

    @GetMapping() // http://localhost:8080/api/products
    public List<Product> getAllProducts() {
        System.out.println("getAllProducts::starts");
        return productService.getProducts();

    }

    @GetMapping("/{productId}") // http://localhost:8080/api/products/2
    public Product getProduct(@PathVariable Long productId) {
        System.out.println("getProduct::starts");
        Product product = productService.getProduct(productId);
        return product;
    }

    @PutMapping("/{productId}")  // http://localhost:8080/api/products/3
    public Product updateProduct(@RequestBody Product newProduct, @PathVariable Long productId) {
        System.out.println("updateProduct::starts");
        Product product = productService.saveProduct(newProduct);
        return product;
    }

    @DeleteMapping() // http://localhost:8080/api/products
    public String deleteAllProducts() {
        System.out.println("deleteAllProducts::starts");
        productService.deleteProducts();
        return "All Products are deleted";
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        System.out.println("deleteProduct::starts");
        productService.deleteProduct(productId);
    }
}
