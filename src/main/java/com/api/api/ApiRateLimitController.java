package com.api.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiRateLimitController {

    @GetMapping("/api/rate-limited")
    public String rateLimitedEndpoint() {
        return "This endpoint is rate-limited.";
    }
}

