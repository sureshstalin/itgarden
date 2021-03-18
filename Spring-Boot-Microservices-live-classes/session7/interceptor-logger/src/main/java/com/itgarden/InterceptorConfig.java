package com.itgarden;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

//    @Autowired
//    private APILoggerInterceptor apiLoggerInterceptor;
//
    @Autowired
    private APIMonitorInterceptor apiMonitorInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(apiLoggerInterceptor);
        registry.addInterceptor(apiMonitorInterceptor);
    }
}
