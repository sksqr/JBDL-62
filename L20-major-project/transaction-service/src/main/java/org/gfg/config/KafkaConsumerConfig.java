package org.gfg.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.gfg.TransactionStatusEnum;
import org.gfg.common.TxnCompPayload;
import org.gfg.common.UserCreatedPayload;
import org.gfg.entity.Transaction;
import org.gfg.repo.ITransactionRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Configuration
public class KafkaConsumerConfig {



    private static Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerConfig.class);

    @Autowired
    private ITransactionRepo transactionRepo;


    private static ObjectMapper objectMapper = new ObjectMapper();
    @KafkaListener(topics = "TXN-COMP", groupId = "txnapp")
    public void consumeFromUserCreatedTopic(ConsumerRecord payload) throws JsonProcessingException {
        TxnCompPayload txnCompPayload = objectMapper.readValue(payload.value().toString(),TxnCompPayload.class);
        MDC.put("requestId",txnCompPayload.getRequestId());
        LOGGER.info("Reading Payload from Kafka: {}",txnCompPayload);
        Transaction transaction = transactionRepo.findById(txnCompPayload.getId()).get();
        if(txnCompPayload.getSuccess()){
            transaction.setStatus(TransactionStatusEnum.SUCCESS);
        }
        else {
            transaction.setStatus(TransactionStatusEnum.FAILED);
            transaction.setReason(txnCompPayload.getReason());
        }
        transactionRepo.save(transaction);
        MDC.clear();
    }

}
