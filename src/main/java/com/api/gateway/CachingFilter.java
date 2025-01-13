package com.api.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class CachingFilter implements WebFilter {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String cacheKey = "someCacheKey"; // You can create a dynamic key based on the request
        String cachedResponse = redisTemplate.opsForValue().get(cacheKey);

        if (cachedResponse != null) {
            // Return cached response
            exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(cachedResponse.getBytes())));
            return Mono.empty();
        }

        return chain.filter(exchange).doOnTerminate(() -> {
            // Cache response after request processing
            redisTemplate.opsForValue().set(cacheKey, "responseData", 60); // Cache for 60 seconds
        });
    }
}

