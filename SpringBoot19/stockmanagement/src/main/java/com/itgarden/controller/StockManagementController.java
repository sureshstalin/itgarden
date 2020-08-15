package com.itgarden.controller;

import com.itgarden.exception.InvalidInputException;
import com.itgarden.exception.ResourceNotFoundException;
import com.itgarden.model.Product;
import com.itgarden.service.ProductService;
import com.itgarden.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/products")
public class StockManagementController {


    @Autowired
    private ProductService productService;


    @PostMapping() // http://localhost:8080/api/products
    public ResponseEntity<Void> add(@Valid @RequestBody Product product) throws  Exception{
        if(!CommonUtils.isValidLetters(product.getProductName())) {
            throw new InvalidInputException("The Product should not have other than letters");
        }
        productService.saveProduct(product);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping() // http://localhost:8080/api/products
    public List<Product> getAllProducts() throws Exception {
        System.out.println("getAllProducts::starts");
        List<Product> products = productService.getProducts();
        return products;
    }

    @GetMapping("/{productId}") // http://localhost:8080/api/products/{productId}
    public Product getProduct(@PathVariable Long productId) throws Exception {
        System.out.println("getProduct::starts");
        Product product = productService.getProduct(productId);
        if(product == null) {
            throw new ResourceNotFoundException("Product is not available");
        }
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
