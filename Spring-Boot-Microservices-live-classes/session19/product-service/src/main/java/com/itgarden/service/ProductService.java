package com.itgarden.service;

import com.itgarden.entity.Product;
import com.itgarden.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product) {
        int qty = product.getQuantity();
        double totalPrice = qty * product.getPrice();
        product.setTotalPrice(totalPrice);
        Product newProduct = productRepository.save(product);
        return newProduct;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int productId) {
        return productRepository.findById(productId).orElse(null);
    }
}
