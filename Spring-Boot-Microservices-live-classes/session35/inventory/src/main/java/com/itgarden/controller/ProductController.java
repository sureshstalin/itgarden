package com.itgarden.controller;

import com.itgarden.entity.Product;
import com.itgarden.service.ProductManagerService;
import com.itgarden.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductManagerService productManagerService;

//    @GetMapping("/{productId}")
//    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId) {
//        Product product = productService.getProductById(productId);
//        return new ResponseEntity<Product>(product, HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product newProduct = productManagerService.save(product);
        return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId) {
        Product product = productManagerService.getProductById(productId);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productManagerService.getAllProduct();
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProductById(
            @RequestBody Product product, @PathVariable("productId") Long productId) {
        product.setId(productId);
        Product updatedProduct = productManagerService.save(product);
        return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("productId") Long productId) {
        productManagerService.deleteProductById(productId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
