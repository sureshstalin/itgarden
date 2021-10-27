package com.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    // URL to access the Swagger
//    http://localhost:9091/swagger-ui/index.html

    public static final Contact CONTACT_DETAILS = new Contact(
            "User Management", "http://www.useroperation.com", "info@useropertion.eu");

    public static final ApiInfo API_INFO = new ApiInfo(
            "User Management API", "User Management System", "1.0",
            "urn:tos", CONTACT_DETAILS,
            "User Management System 1.0", "https://www.useroperation.com/licenses/LICENSE-2.0", Arrays.asList());

    private static final Set<String> PRODUCES_AND_CONSUMES =
            new HashSet<String>(Arrays.asList("application/json"));

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(API_INFO)
                .produces(PRODUCES_AND_CONSUMES)
                .consumes(PRODUCES_AND_CONSUMES);
    }
}
