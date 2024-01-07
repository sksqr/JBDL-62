package com.example.L18kafkademo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConsumerConfig {

    private static Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerConfig.class);

    @KafkaListener(topics = "topic02", groupId = "app01")
    public void consumeFromTopic02(Object payload){
        LOGGER.info("Getting payload from kafka : {}",payload);
    }
}
