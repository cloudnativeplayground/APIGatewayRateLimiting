package com.api.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.config.GlobalCorsProperties;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class APIGatewayConfig {

    @Value("${service.backend.url}")
    private String backendServiceUrl; // URL of the backend service

    /**
     * Configuring routes for the Gateway
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Example route: Forward requests to /api/hello to backend service
                .route(r -> r.path("/api/hello")
                        .filters(f -> f.addRequestHeader("X-Request-Foo", "Bar")
                                .filter(rateLimitingFilter())) // Example: apply rate limiting filter here
                        .uri(backendServiceUrl)) // Route to backend service URL (e.g., service mesh or external service)
                // Another example route: Forward requests to /api/rate-limited to a rate-limited service
                .route(r -> r.path("/api/rate-limited")
                        .filters(f -> f.addRequestHeader("X-RateLimit", "True")
                                .filter(rateLimitingFilter())) // Example: apply rate-limiting filter
                        .uri("http://rate-limited-service-url")) // URL of rate-limited service
                .build();
    }

    /**
     * Create a custom rate-limiting filter
     */
    private GatewayFilter rateLimitingFilter() {
        return new GatewayFilter() {
            /**
             * @param exchange
             * @param chain
             * @return
             */
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                return null;
            }

            @Override
            public org.springframework.web.server.ServerWebExchange filter(org.springframework.web.server.ServerWebExchange exchange,
                                                                           org.springframework.web.server.WebFilterChain chain) {
                // Custom rate-limiting logic goes here
                System.out.println("Rate Limiting Filter Applied");
                return chain.filter(exchange);
            }
        };
    }

    /**
     * CORS configuration for API Gateway
     */
    @Bean
    public GlobalCorsProperties globalCorsProperties() {
        GlobalCorsProperties corsProperties = new GlobalCorsProperties();
        corsProperties.addAllowedOrigin("http://localhost:3000"); // Example: Allow frontend from localhost:3000
        corsProperties.addAllowedMethod(HttpMethod.GET);
        corsProperties.addAllowedMethod(HttpMethod.POST);
        corsProperties.addAllowedHeader("*");
        return corsProperties;
    }
}

