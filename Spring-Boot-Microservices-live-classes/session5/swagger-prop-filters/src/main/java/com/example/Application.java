package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	public FilterRegistrationBean<Filter3> filter3(){
//		FilterRegistrationBean<Filter3> registrationBean
//				= new FilterRegistrationBean<>();
//		registrationBean.setFilter(new Filter3());
//		registrationBean.addUrlPatterns("/employee/*");
//		return registrationBean;
//	}

}
