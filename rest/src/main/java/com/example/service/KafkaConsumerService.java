package com.example.service;

import com.example.CalculationResponse;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

@Service
public class KafkaConsumerService {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "responses", groupId = "rest-group")
    public void listen(ConsumerRecord<String, CalculationResponse> record) {
        try {
            CalculationResponse response = record.value();
            MDC.put("requestId", response.getRequestId());
            
            log.info("Received response for request: {}", response.getRequestId());
            log.info("Operation {} between {} and {} results in: {}", 
                response.getOperationType(), 
                response.getA(), 
                response.getB(),
                response.getResult());
            
            if (response.getError() != null) {
                log.error("Calculation error: {}", response.getError());
            }
        } catch (Exception e) {
            log.error("Error processing message: {}", e.getMessage(), e);
        } finally {
            MDC.remove("requestId");
        }
    }
}
