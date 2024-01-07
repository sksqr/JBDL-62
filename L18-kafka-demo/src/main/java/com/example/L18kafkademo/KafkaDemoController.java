package com.example.L18kafkademo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/kafka")
public class KafkaDemoController {

    private static Logger LOGGER = LoggerFactory.getLogger(KafkaDemoController.class);
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Object> objectKafkaTemplate;

    @GetMapping("/push")
    ResponseEntity<String> pushMsgToKafka(@RequestParam String msg, @RequestParam String key) throws ExecutionException, InterruptedException {
        Future<SendResult<String,String>>future = kafkaTemplate.send("topic02",key,msg);
        LOGGER.info("Pushed data to kafka {}",future.get());
        return ResponseEntity.ok("Data Pushed");
    }


    @PostMapping("/sendEmailAsync")
    public ResponseEntity<String> sendEmailAsync(@RequestBody EmailRequest emailRequest) throws ExecutionException, InterruptedException {
        Future<SendResult<String,Object>>future = objectKafkaTemplate.send("topic03",emailRequest.getToEmail(),emailRequest);
        LOGGER.info("Pushed data to kafka {}",future.get());
        return new  ResponseEntity<String>("Email will be triggered", HttpStatus.ACCEPTED);
    }
}
