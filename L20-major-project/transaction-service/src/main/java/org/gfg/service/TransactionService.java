package org.gfg.service;

import org.gfg.TransactionStatusEnum;
import org.gfg.common.TransactionInitPayload;
import org.gfg.dto.TransactionRequestDto;
import org.gfg.dto.TxnStatusDto;
import org.gfg.entity.Transaction;
import org.gfg.repo.ITransactionRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class TransactionService {

    private static String TXN_INIT_TOPIC = "TXN-INIT";

    private static Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);


    @Autowired
    private ITransactionRepo transactionRepo;

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;
    public String doTransaction(TransactionRequestDto transactionRequest) throws ExecutionException, InterruptedException {

        Transaction transaction = Transaction.builder()
                .fromUserId(transactionRequest.getFromUserId())
                .toUserId(transactionRequest.getToUserId())
                .amount(transactionRequest.getAmount())
                .remark(transactionRequest.getRemark())
                .txnId(UUID.randomUUID().toString())
                .status(TransactionStatusEnum.PENDING)
                .build();

        transactionRepo.save(transaction);

        TransactionInitPayload transactionInitPayload = TransactionInitPayload.builder()
                .id(transaction.getId())
                .fromUserId(transaction.getFromUserId())
                .toUserId(transaction.getToUserId())
                .amount(transaction.getAmount())
                .remark(transaction.getRemark())
                .requestId(MDC.get("requestId"))
                .build();
       Future<SendResult<String,Object>> future =  kafkaTemplate.send(TXN_INIT_TOPIC,String.valueOf(transactionRequest.getFromUserId()),transactionInitPayload);
       LOGGER.info("Pushed txnInit payload: {}",future.get());
       return transaction.getTxnId();
    }

    public TxnStatusDto getStatus(String txnId){
        Transaction transaction = transactionRepo.findByTxnId(txnId);
        TxnStatusDto txnStatusDto = TxnStatusDto.builder()
                .status(transaction.getStatus().toString())
                .reason(transaction.getReason())
                .build();
        return txnStatusDto;
    }

}
