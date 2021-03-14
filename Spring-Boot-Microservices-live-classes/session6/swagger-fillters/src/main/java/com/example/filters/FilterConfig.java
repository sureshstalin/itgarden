package com.example.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {


    @Bean
    public FilterRegistrationBean<HandlePrintFilter> handlePrintFilter(){

        FilterRegistrationBean<HandlePrintFilter> registrationBean
                = new FilterRegistrationBean<>();
        registrationBean.setFilter(new HandlePrintFilter());
        registrationBean.addUrlPatterns("/api/customers/print/*");
        return registrationBean;
    }
}
