package com.itgarden;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GlobalFilter {

    private Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        logger.info("### This is API Gateway Filter ##### ");
        String requestPath = exchange.getRequest().getPath().value();
        if (requestPath.equals("/api/external/shopping")) {
            System.out.println(String.format("Calling from external system %s ", requestPath));
        }
        if (exchange.getRequest().getHeaders().containsKey("country")) {
            System.out.println(String.format("Calling from external System %s ", requestPath));
        }
        if (requestPath.equals("/api/customers")) {
            System.out.println(String.format("Calling from internal System %s ", requestPath));
        }
        return chain.filter(exchange);
    }
}
