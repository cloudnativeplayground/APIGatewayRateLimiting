package com.api.api;

import org.springdoc.core.annotations.EnableOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.GroupedOpenApi;

@Configuration
@EnableOpenApi
public class ApiDocsConfig {

    @Bean
    public GroupedOpenApi apiGroup() {
        return GroupedOpenApi.builder()
                .group("API Gateway")
                .packagesToScan("com.api") // Scan the 'com.api' package for API documentation
                .build();
    }
}

