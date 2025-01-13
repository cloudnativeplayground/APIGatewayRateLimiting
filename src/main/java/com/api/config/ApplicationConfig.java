
package com.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.WebFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.context.annotation.ComponentScan;

import com.api.gateway.RateLimitingFilter;
import com.api.gateway.AuthenticationFilter;
import com.api.gateway.CachingFilter;

@Configuration
@EnableWebFlux
@ComponentScan(basePackages = "com.api.gateway")
public class ApplicationConfig {

    private final RateLimitingFilter rateLimitingFilter;
    private final AuthenticationFilter authenticationFilter;
    private final CachingFilter cachingFilter;

    public ApplicationConfig(RateLimitingFilter rateLimitingFilter,
                             AuthenticationFilter authenticationFilter,
                             CachingFilter cachingFilter) {
        this.rateLimitingFilter = rateLimitingFilter;
        this.authenticationFilter = authenticationFilter;
        this.cachingFilter = cachingFilter;
    }

    /**
     * Register Global Filters for Gateway
     * These filters will be applied to every request passing through the API Gateway.
     */
    @Bean
    public GlobalFilter globalRateLimitingFilter() {
        return (exchange, chain) -> rateLimitingFilter.filter(exchange, chain);
    }

    @Bean
    public GlobalFilter globalAuthenticationFilter() {
        return (exchange, chain) -> authenticationFilter.filter(exchange, chain);
    }

    @Bean
    public GlobalFilter globalCachingFilter() {
        return (exchange, chain) -> cachingFilter.filter(exchange, chain);
    }

    /**
     * Additional configuration, if necessary, such as custom route configuration,
     * service mesh, or additional filters could go here.
     */
}
