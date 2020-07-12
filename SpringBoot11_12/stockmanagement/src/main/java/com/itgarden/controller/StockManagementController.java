package com.itgarden.controller;

import com.itgarden.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/products")
public class StockManagementController {


    List<Product> prodcts = null;

    @GetMapping("/print")  //http://localhost:8080/api/products/print - endpoint
    public String display() {
        return "This is simple Web Service Example";
    }

    @PostMapping("/add") // http://localhost:8080/api/products/add
    public void addProduct(@RequestBody Product product,
                           @RequestHeader("Country-Name") String countryName,
                           @RequestHeader("Language") String language) {
        System.out.println("addProduct::starts");
        System.out.println("Country " + countryName);
        System.out.println("Language " + language);
        if (prodcts == null) {
            prodcts = new ArrayList<>();
        }
        prodcts.add(product);
    }

    @GetMapping() // http://localhost:8080/api/products
    public List<Product> getAllProducts() {
        System.out.println("getAllProducts::starts");
        return prodcts;

    }

    @GetMapping("/{productId}") // http://localhost:8080/api/products/2
    public Product getProduct(@PathVariable Long productId) {
        System.out.println("getProduct::starts");
        Product product = prodcts.stream()
                .filter(p -> p.getProductId().equals(productId)).findAny().get();
        return product;
    }

    @PutMapping("/{productId}")  // http://localhost:8080/api/products/3
    public Product updateProduct(@RequestBody Product newProduct, @PathVariable Long productId) {
        System.out.println("updateProduct::starts");
        Product product = prodcts.stream()
                .filter(p -> p.getProductId().equals(productId)).findAny().get();
        prodcts.remove(product);
        prodcts.add(newProduct);
        return newProduct;
    }

    @DeleteMapping() // http://localhost:8080/api/products
    public String deleteAllProducts() {
        System.out.println("deleteAllProducts::starts");
        prodcts.clear();
        return "All Products are deleted";
    }

    @DeleteMapping("/{productId}")
    public Product deleteProduct(@PathVariable Long productId) {
        System.out.println("deleteProduct::starts");
        Product product = prodcts.stream()
                .filter(p -> p.getProductId().equals(productId)).findAny().get();
        prodcts.remove(product);
        return product;
    }
}
