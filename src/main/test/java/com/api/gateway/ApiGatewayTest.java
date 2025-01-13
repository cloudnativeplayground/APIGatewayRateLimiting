package com.api.gateway;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiGatewayTest {

    @LocalServerPort
    private int port;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ApiController()).build(); // You can use ApiController if needed
    }

    @Test
    public void testHelloApiRoute() throws Exception {
        mockMvc.perform(get("http://localhost:" + port + "/api/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello from API Gateway"));
    }

    @Test
    public void testRateLimitedApiRoute() throws Exception {
        mockMvc.perform(get("http://localhost:" + port + "/api/rate-limited"))
                .andExpect(status().isTooManyRequests());  // Assuming rate limiting triggers HTTP 429
    }
}

