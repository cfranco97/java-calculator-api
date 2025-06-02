package com.example.service;

import com.example.CalculationResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.math.BigDecimal;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class KafkaProducerServiceTest {

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private KafkaProducerService producerService;

    @Test
    void testSendResponse() {
        CalculationResponse response = new CalculationResponse();
        response.setRequestId("test-id");
        response.setOperationType("sum");
        response.setA(new BigDecimal("5"));
        response.setB(new BigDecimal("3"));
        response.setResult(new BigDecimal("8"));

        producerService.sendResponse(response);

        verify(kafkaTemplate).send(any(Message.class));
    }
}
