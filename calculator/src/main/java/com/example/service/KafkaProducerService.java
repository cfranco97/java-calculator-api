package com.example.service;


import com.example.CalculationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;



@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


    public void sendResponse(CalculationResponse response) {
        System.out.println("Sending response: " + response);
        
        MessageBuilder<CalculationResponse> messageBuilder = MessageBuilder
            .withPayload(response)
            .setHeader(KafkaHeaders.TOPIC, "responses")
            .setHeader("requestID", response.getRequestId())
            .setHeader("Content-Type", "application/json");

        kafkaTemplate.send(messageBuilder.build());
    }
}
