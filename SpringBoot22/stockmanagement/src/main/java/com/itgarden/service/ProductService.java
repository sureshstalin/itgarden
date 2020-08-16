package com.itgarden.service;

import com.itgarden.model.Product;
import com.itgarden.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product getProduct(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        Product product = productOptional.orElse(null);

        return product;
    }

    @Transactional
    public List<Product> getProducts() throws Exception {
        List<Product> products = productRepository.findAll();
        if (products == null || products.size() == 0) {
            throw new Exception("Product is not found");
        }
        return products;
    }

    @Transactional
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Transactional
    public void deleteProducts() {
        productRepository.deleteAll();
    }
}
