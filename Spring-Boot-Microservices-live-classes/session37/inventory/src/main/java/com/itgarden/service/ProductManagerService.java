package com.itgarden.service;

import com.itgarden.entity.Product;
import com.itgarden.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductManagerService {

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product) {
        double price = product.getPrice();
        double tax = (price * 10) / 100;
        double total = price + tax;
        product.setTotalPrice(total);
        product.setTax(tax);
        Product newProduct = productRepository.save(product);
        return newProduct;
    }

    public Product getProductById(Long productId) {
        Product product = productRepository.findById(productId).get();
        return product;
    }

    public List<Product> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return  products;
    }

    public  void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }

}
