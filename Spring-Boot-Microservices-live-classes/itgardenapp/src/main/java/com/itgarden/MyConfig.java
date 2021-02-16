package com.itgarden;

import com.itgarden.service.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    public MyConfig() {
        System.out.println("This is my Own Configuration file");
    }

    @Bean
    Product product() {
     return  new Product("My Product");
    }

}
