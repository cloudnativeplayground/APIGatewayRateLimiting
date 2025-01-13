package com.api.gateway;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class RateLimitingFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // Logic to check and limit requests (Rate Limiting logic)
        // This can be done by checking the IP address or using Redis to store request counts
        // For now, simply passing the request through.
        System.out.println("Rate Limiting filter applied.");
        return chain.filter(exchange);
    }
}

