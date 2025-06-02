package com.example.controller;

import com.example.CalculationRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.service.KafkaProducerService;
import org.slf4j.MDC;
import java.util.UUID;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/calculate")
    public ResponseEntity<String> calculate(@RequestBody CalculationRequest request) {
        String requestId = UUID.randomUUID().toString();
        MDC.put("requestId", requestId);

        try {
            //log request details
            System.out.println("[->]api/calculate()");
            System.out.println("Received calculation request: " + request.getOperationType());
            request.setRequestId(requestId);
            kafkaProducerService.sendRequest(request);

            return ResponseEntity.ok()
                    .header("X-Request-ID", requestId)
                    .body("Request sent successfully with ID: " + requestId);
        } finally {
            MDC.remove("requestId");
        }
    }

    @PostConstruct
    public void init() {
        System.out.println("✅ RestController initialized");
    }

}
