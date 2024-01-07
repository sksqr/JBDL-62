package com.example.L18kafkaemailconsumerdemo.config;

import com.example.L18kafkaemailconsumerdemo.EmailRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class KafkaConsumerConfig {


    private static Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerConfig.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private JavaMailSender javaMailSender;

    @KafkaListener(topics = "topic03", groupId = "app01")
    public void consumeFromTopic02(Object payload) throws JsonProcessingException {
        LOGGER.info("Getting payload from kafka : {}",payload);
        String payloadValue = (String) ((ConsumerRecord)payload).value();
        EmailRequest emailRequest = objectMapper.readValue(payloadValue,EmailRequest.class);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("sk.email.service@gmail.com");
        simpleMailMessage.setSubject(emailRequest.getSubject());
        simpleMailMessage.setTo(emailRequest.getToEmail());
        simpleMailMessage.setText(emailRequest.getBody());
        simpleMailMessage.setCc(emailRequest.getCc());
        javaMailSender.send(simpleMailMessage);
    }

}
