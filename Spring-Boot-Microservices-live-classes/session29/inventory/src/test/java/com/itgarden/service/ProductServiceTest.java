package com.itgarden.service;

import com.itgarden.entity.Product;
import com.itgarden.repository.ProductDAO;
import com.itgarden.repository.ProductDAOImpl;
import com.itgarden.service.mock.MockProductDAOImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductServiceTest {


    @Test
    public void getProductById() {

        ProductDAO productDAO = new MockProductDAOImpl();
        ProductService productService = new ProductService(productDAO);
        Product product = productService.getProductById(1l);
        Assertions.assertNotNull(product);
        Assertions.assertEquals(30,product.getTax());
        Assertions.assertEquals(330,product.getTotalPrice());


    }

}
