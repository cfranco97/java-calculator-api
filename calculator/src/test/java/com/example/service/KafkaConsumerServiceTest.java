package com.example.service;

import com.example.CalculationRequest;
import com.example.CalculationResponse;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.math.BigDecimal;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class KafkaConsumerServiceTest {

    @Mock
    private CalculatorService calculatorService;

    @Mock
    private KafkaProducerService producerService;

    @InjectMocks
    private KafkaConsumerService consumerService;

    @Test
    void testListen() {
        // Prepare test data
        CalculationRequest request = new CalculationRequest();
        request.setRequestId("test-id");
        request.setOperationType("sum");
        request.setA(new BigDecimal("5"));
        request.setB(new BigDecimal("3"));

        ConsumerRecord<String, CalculationRequest> record = 
            new ConsumerRecord<>("requests", 0, 0, "key", request);

        when(calculatorService.sum(any(), any()))
            .thenReturn(new BigDecimal("8"));

        // Execute test
        consumerService.listen(record);

        // Verify response was sent
        verify(producerService).sendResponse(any(CalculationResponse.class));
    }
}
