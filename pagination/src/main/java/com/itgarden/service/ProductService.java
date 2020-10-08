package com.itgarden.service;

import com.itgarden.entity.Product;
import com.itgarden.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product saveProduct(Product product) {
        Product newProduct = productRepository.save(product);
        return newProduct;
    }
    @Transactional
    public Page<Product> findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize,Sort.by("productId"));//, Sort.by("recId").ascending());
        Page<Product> productPage = productRepository.findAll(pageable);
        System.out.println("Total Elements  "+ productPage.getTotalElements());
        System.out.println("Total Pages  "+ productPage.getTotalPages());
        return productPage;
    }

    @Transactional
    public List<Product> findAllProducts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("recId").ascending());
        List<Product> products = productRepository.findAllProducts(pageable);
        System.out.println("Page Size " + pageable.getPageSize());
        return products;
    }
}
