package com.itgarden.controller;

import com.itgarden.entity.Product;
import com.itgarden.repository.ProductRepository;
import com.itgarden.service.ProductManagerService;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductManagerService productManagerService;


    @Test
    public void productSaveTest() throws Exception {

        String requestBody = "{\"name\":\"Apple\"," +
                "\"description\":\"Apple description\", " +
                "\"price\":300}";

        Product product = new Product();
        product.setId(10l);
        product.setName("Apple");
        product.setDescription("This is Apple Test cases");
        product.setPrice(300d);

        when(productManagerService.save(any(Product.class))).thenReturn(product);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/products")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        System.out.println("Response as String " + response);
        JSONAssert.assertEquals("{name:Apple}",response,false); // Pass
    }

    @Test
    public void getProductById() throws Exception {
        Product product = new Product();
        product.setId(10l);
        product.setName("Mango");
        product.setDescription("This is Apple Test cases");
        product.setPrice(300d);

        when(productManagerService.getProductById(anyLong())).thenReturn(product);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/products/{productId}",10l)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        System.out.println("GET::Response as String " + response);
        JSONAssert.assertEquals("{name:Mango}",response,false); // Pass
    }

    @Test
    public void getAllProducts() throws Exception {
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

        productList.add(product1); productList.add(product2);
        when(productManagerService.getAllProduct()).thenReturn(productList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/products")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        System.out.println("Response as String " + response);;
        JSONAssert.assertEquals("[{name:Apple},{name:Orange}]",response,false); // Pass
    }

    @Test
    public void updateUserById() throws  Exception{
        String requestBody = "{\"name\":\"Shirt\"," +
                "\"description\":\"Shirt description\", " +
                "\"price\":300}";

        Product product = new Product();
        product.setId(10l);
        product.setName("Shirt");
        product.setDescription("This is Apple Test cases");
        product.setPrice(300d);

        when(productManagerService.save(any(Product.class))).thenReturn(product);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/products/{productId}",100l)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        System.out.println("Response as String " + response);
        JSONAssert.assertEquals("{name:Shirt}",response,false); // Pass
    }

    @Test
    public void deleteUserById() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
    }


}
