package com.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.gateway.config.EnableGateway;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableGateway // Enables Spring Cloud Gateway functionality
@EnableEurekaClient // If you're using Eureka for service discovery
@ComponentScan(basePackages = "com.api") // Ensures all packages under 'com.api' are scanned
public class GatewayApplication {

    public static void main(String[] args) {
        // Launch the Spring Boot application
        SpringApplication.run(GatewayApplication.class, args);
    }
}

