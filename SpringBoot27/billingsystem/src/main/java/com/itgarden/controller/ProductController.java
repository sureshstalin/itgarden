package com.itgarden.controller;

import com.itgarden.entity.Product;
import com.itgarden.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Long> saveProduct(@RequestBody Product product) {
        Long productId = productService.saveProduct(product);
        return new ResponseEntity<>(productId, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(@RequestParam("pageNumber") int pageNumber,
                                                     @RequestParam("pageSize") int pageSize) {
        Page<Product> pageableProduct = productService.findAll(pageNumber,pageSize);
        Iterator<Product> iterator = pageableProduct.stream().iterator();
        List<Product> products = new ArrayList<Product>();
        iterator.forEachRemaining(products::add);
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping("/query")
    public ResponseEntity<List<Product>> findAllProductsQuery(@RequestParam("pageNumber") int pageNumber,
                                                     @RequestParam("pageSize") int pageSize) {
        List<Product> products = productService.findAllProducts(pageNumber,pageSize);
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }
}
