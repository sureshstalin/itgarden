package com.itgarden.service.mock;

import com.itgarden.entity.Product;
import com.itgarden.repository.ProductDAO;

public class MockProductDAOImpl implements ProductDAO {

    @Override
    public Product getProductById(Long ProductId) {
        Product product = new Product();
        product.setId(1l);
        product.setName("Coffee");
        product.setDescription("This is best coffee in the world");
        product.setPrice(300d);
        return product;
    }
}
