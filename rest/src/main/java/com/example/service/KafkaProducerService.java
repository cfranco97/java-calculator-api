package com.example.service;

import com.example.CalculationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;



@Service
public class KafkaProducerService {
    
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendRequest(CalculationRequest request) {
        try {
            System.out.println("[->]sendRequest()");
            System.out.println("Sending request to Kafka topic: requests");
            System.out.println("RequestID: " + request.getRequestId() + "\n");
            
            // build message headers
            MessageBuilder<CalculationRequest> messageBuilder = MessageBuilder
                .withPayload(request)
                .setHeader(KafkaHeaders.TOPIC, "requests")
                .setHeader("requestID", request.getRequestId())
                .setHeader("Content-Type", "application/json");

            // Send message with headers
            kafkaTemplate.send(messageBuilder.build());
            
        } catch (Exception e) {
            System.err.println("Error in sendRequest: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
