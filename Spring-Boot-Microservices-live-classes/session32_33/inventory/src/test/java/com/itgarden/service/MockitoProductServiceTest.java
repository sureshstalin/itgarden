package com.itgarden.service;

import com.itgarden.entity.Product;
import com.itgarden.repository.ProductDAO;
import com.itgarden.repository.ProductDAOImpl;
import com.itgarden.service.mock.MockProductDAOImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoProductServiceTest {


    @Test
    public void getProductById() {
        // Test data definition
        Product product = new Product();
        product.setPrice(100d);
        product.setDescription("Test Description");
        product.setName("Shirt");

        ProductDAO productDAO = mock(ProductDAOImpl.class);
        when(productDAO.getProductById(Mockito.anyLong())).thenReturn(product);
        ProductService productService = new ProductService(productDAO);
        Product mockProduct = productService.getProductById(1l);
        Assertions.assertNotNull(mockProduct);
        Assertions.assertEquals(10,mockProduct.getTax());
        Assertions.assertEquals(110,mockProduct.getTotalPrice());


    }

}
