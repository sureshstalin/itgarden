package com.itgarden.repository;

import com.itgarden.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;

@Repository
public class ProductDAOImpl implements ProductDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public  Product  getProductById(Long productId) {
        Product product = entityManager.find(Product.class,productId);
        return product;
    }
}
