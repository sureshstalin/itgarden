package com.itgarden.service;

import com.itgarden.entity.Product;
import com.itgarden.repository.ProductDAO;
import com.itgarden.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductDAO productDAO;


    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public Product getProductById(Long productId) {
        Product product = productDAO.getProductById(productId);
        double price = product.getPrice();
        double tax = (price * 10) / 100;
        double total = price + tax;
        product.setTotalPrice(total);
        product.setTax(tax);
        return product;
    }



}
