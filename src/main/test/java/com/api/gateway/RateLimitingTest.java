package com.api.gateway;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class RateLimitingTest {

    @Autowired
    private ApiGatewayConfig apiGatewayConfig;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ApiController()).build(); // Add real controller or mock if needed
    }

    @Test
    public void testRateLimiting() throws Exception {
        // Simulate several requests to the rate-limited API
        for (int i = 0; i < 10; i++) {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/rate-limited"))
                    .andExpect(status().isOk())
                    .andReturn();
        }

        // Send a request after the limit is exceeded, expecting HTTP 429 (Too Many Requests)
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/rate-limited"))
                .andExpect(status().isTooManyRequests())
                .andReturn();

        // You can also check the response body or headers for more validation
        assertEquals("Rate limit exceeded", result.getResponse().getContentAsString());
    }
}

