package org.gfg.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.gfg.common.UserCreatedPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class KafkaConsumerConfig {

    private static Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerConfig.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private JavaMailSender javaMailSender;

    @KafkaListener(topics = "USER-CREATED", groupId = "email")
    public void consumerUserCreatedTopicForEmail(ConsumerRecord payload) throws JsonProcessingException {
        UserCreatedPayload userCreatedPayload = objectMapper.readValue(payload.value().toString(),UserCreatedPayload.class);
        MDC.put("requestId",userCreatedPayload.getRequestId());
        LOGGER.info("Reading Payload from Kafka: {}",userCreatedPayload);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("jbdl.ewallet@gmail.com");
        simpleMailMessage.setSubject("Welcome "+userCreatedPayload.getUserName()+"!");
        simpleMailMessage.setTo(userCreatedPayload.getUserEmail());
        simpleMailMessage.setText("Hi "+userCreatedPayload.getUserName()+". Welcome in JBDL-62 wallet world");
        simpleMailMessage.setCc("admin.jbdl62@yopmail.com");
        javaMailSender.send(simpleMailMessage);
        MDC.clear();
    }
}
