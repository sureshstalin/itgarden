package com.itgarden.controller;

import com.itgarden.entity.Product;
import com.itgarden.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") // http://localhsot:9093/api
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/products") // http://localhsot:9093/api/products
    public ResponseEntity<Product> save(@RequestBody Product product) {
        Product products = productService.save(product);
       return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @GetMapping("/products") // http://localhsot:9093/api/products
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getProducts();
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping("/products/{productId}") // http://localhsot:9093/api/products/<id>
    public ResponseEntity<Product> getProductById(@PathVariable int productId) {
        Product product = productService.getProductById(productId);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
}
