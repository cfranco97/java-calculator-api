package com.example.service;

import com.example.CalculationRequest;
import com.example.CalculationResponse;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import java.math.BigDecimal;

@Service
public class KafkaConsumerService {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerService.class);

    @Autowired
    private CalculatorService calculatorService;
    
    @Autowired
    private KafkaProducerService producerService;

    @KafkaListener(topics = "requests", groupId = "calculator-group")
    public void listen(ConsumerRecord<String, CalculationRequest> record) {
        try {
            CalculationRequest request = record.value();
            MDC.put("requestId", request.getRequestId());
            
            log.info("Received request: {} for operands {} and {}", 
                request.getOperationType(), request.getA(), request.getB());
            
            CalculationResponse response = new CalculationResponse();
            response.setRequestId(request.getRequestId());
            response.setOperationType(request.getOperationType());
            response.setA(request.getA());
            response.setB(request.getB());
            
            try {
                BigDecimal result = switch (request.getOperationType().toLowerCase()) {
                    case "sum" -> calculatorService.sum(request.getA(), request.getB());
                    case "subtract" -> calculatorService.subtract(request.getA(), request.getB());
                    case "multiply" -> calculatorService.multiply(request.getA(), request.getB());
                    case "divide" -> calculatorService.divide(request.getA(), request.getB());
                    default -> throw new IllegalArgumentException("Unknown operation type: " + request.getOperationType());
                };
                response.setResult(result);
                log.info("Calculation result: {}", result);
            } catch (Exception e) {
                log.error("Calculation error: {}", e.getMessage());
                response.setError(e.getMessage());
            }

            producerService.sendResponse(response);
        } catch (Exception e) {
            log.error("Message processing error: {}", e.getMessage(), e);
        } finally {
            MDC.remove("requestId");
        }
    }


}
