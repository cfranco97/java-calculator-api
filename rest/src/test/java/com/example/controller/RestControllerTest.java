package com.example.controller;

import com.example.CalculationRequest;
import com.example.service.KafkaProducerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class RestControllerTest {

    @Mock
    private KafkaProducerService kafkaProducerService;

    @InjectMocks
    private RestController restController;

    @Test
    void testCalculate() {
        // Prepare test data
        CalculationRequest request = new CalculationRequest();
        request.setOperationType("sum");
        request.setA(new BigDecimal("5"));
        request.setB(new BigDecimal("3"));

        // Execute test
        ResponseEntity<String> response = restController.calculate(request);

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getHeaders().getFirst("X-Request-ID"), "X-Request-ID header should be present");
        
        String body = response.getBody();
        assertNotNull(body, "Response body should not be null");
        assertTrue(body.contains("Request sent successfully"), "Response body should contain success message");
        
        // Verify Kafka producer was called
        verify(kafkaProducerService).sendRequest(any(CalculationRequest.class));
    }
}
