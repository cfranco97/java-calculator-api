package com.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    
    @Bean
    public NewTopic requestsTopic() {
        System.out.println("Creating requests topic...");
        return TopicBuilder.name("requests")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic responsesTopic() {
        System.out.println("Creating requests responses...");
        return TopicBuilder.name("responses")
                .partitions(1)
                .replicas(1)
                .build();
    }
}