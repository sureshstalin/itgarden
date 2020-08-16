package com.itgarden;

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

    public static final Contact CONTACT_DETAILS = new Contact(
            "Suresh Stalin", "http://www.pinepad.com", "sales@pinepad.com");

    public static final ApiInfo API_INFO = new ApiInfo(
            "PinePad API", "PinePad API Description", "1.0",
            "urn:tos", CONTACT_DETAILS,
            "PinePad 2.0", "http://www.pinepad.com/licenses/LICENSE-2.0", Arrays.asList());

    private static final Set<String> PRODUCES_AND_CONSUMES =
            new HashSet<String>(Arrays.asList("application/json",
                    "application/xml"));
    @Bean
    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(API_INFO)
                .produces(PRODUCES_AND_CONSUMES)
                .consumes(PRODUCES_AND_CONSUMES);
    }
}
