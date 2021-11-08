package com.itgarden.service;

import com.itgarden.entity.Product;
import com.itgarden.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
public class ProductManagerServiceTest {

    @InjectMocks
    private ProductManagerService productManagerService;

    @Mock
    ProductRepository productRepository;

    @Test
    public void saveProduct() {
        Product product = new Product();
        product.setId(10l);
        product.setName("Apple");
        product.setDescription("This is Apple Test cases");
        product.setPrice(300d);
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
        Product newProduct = productManagerService.save(product);
        System.out.println(newProduct.getTax());
        System.out.println(newProduct.getTotalPrice());
        Assertions.assertNotNull(newProduct);
        Assertions.assertEquals(30, product.getTax());
        Assertions.assertEquals(330, product.getTotalPrice());
    }

    @Test
    public void getProductById() {
        Product product = new Product();
        product.setId(10l);
        product.setName("Apple");
        product.setDescription("This is Apple Test cases");
        product.setPrice(300d);
        Optional<Product> productOptional = Optional.of(product);
        when(productRepository.findById(Mockito.anyLong())).thenReturn(productOptional);
        Product newProduct = productManagerService.getProductById(19l);
        Assertions.assertNotNull(newProduct);
        Assertions.assertEquals("Apple", product.getName());

    }

    @Test
    public void getAllProducts() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(10l);
        product1.setName("Apple");
        product1.setDescription("This is Apple Test cases");
        product1.setPrice(300d);

        Product product2 = new Product();
        product2.setId(20l);
        product2.setName("Orange");
        product2.setDescription("This is Orange Test cases");
        product2.setPrice(400d);
        productList.add(product1);
        productList.add(product2);

        when(productRepository.findAll()).thenReturn(productList);
        List<Product> newProductList = productManagerService.getAllProduct();
        assertThat(newProductList).isNotNull().isNotEmpty().hasSize(2);
        assertThat(newProductList.get(0).getName()).isEqualTo("Apple");
        assertThat(newProductList).allMatch(product -> product.getPrice() > 200);
    }

    @Test
    public void deleteById() {
        productManagerService.deleteProductById(1l);
        verify(productRepository,times(1)).deleteById(anyLong());
    }
}
