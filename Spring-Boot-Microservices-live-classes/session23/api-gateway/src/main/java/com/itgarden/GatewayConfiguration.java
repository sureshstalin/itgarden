package com.itgarden;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {


    @Bean
    RouteLocator gatewayRouter(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(predicateSpec -> predicateSpec.path("/api/external/shopping")
                        .uri("http://localhost:8082/api/shopping"))

                .route(predicateSpec -> predicateSpec.header("country")
                        .uri("http://localhost:8083/api/billing"))
                .build();
    }

}
