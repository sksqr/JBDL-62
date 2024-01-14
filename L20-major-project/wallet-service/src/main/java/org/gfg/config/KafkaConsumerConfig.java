package org.gfg.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.gfg.WalletService;
import org.gfg.common.TransactionInitPayload;
import org.gfg.common.UserCreatedPayload;
import org.gfg.entity.Wallet;
import org.gfg.repo.IWalletRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.ExecutionException;

@Configuration
public class KafkaConsumerConfig {

    private static Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerConfig.class);

    @Autowired
    private IWalletRepo walletRepo;

    @Autowired
    private WalletService walletService;

    private static ObjectMapper objectMapper = new ObjectMapper();
    @KafkaListener(topics = "USER-CREATED", groupId = "walletapp")
    public void consumeFromUserCreatedTopic(ConsumerRecord payload) throws JsonProcessingException {
        UserCreatedPayload userCreatedPayload = objectMapper.readValue(payload.value().toString(),UserCreatedPayload.class);
        MDC.put("requestId",userCreatedPayload.getRequestId());
        LOGGER.info("Reading Payload from Kafka: {}",userCreatedPayload);
        Wallet wallet = Wallet.builder()
                .userId(userCreatedPayload.getUserId())
                .email(userCreatedPayload.getUserEmail())
                .balance(100.00)
                .build();
        walletRepo.save(wallet);
        MDC.clear();
    }


    @KafkaListener(topics = "TXN-INIT", groupId = "walletapp")
    public void consumeFromTxnInit(ConsumerRecord payload) throws JsonProcessingException, ExecutionException, InterruptedException {
        TransactionInitPayload transactionInitPayload = objectMapper.readValue(payload.value().toString(),TransactionInitPayload.class);
        MDC.put("requestId",transactionInitPayload.getRequestId());
        LOGGER.info("Reading Payload from Kafka: {}",transactionInitPayload);
        walletService.doWalletTxn(transactionInitPayload);
        MDC.clear();
    }


}
