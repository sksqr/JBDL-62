package org.gfg.controller;

import jakarta.validation.Valid;
import org.gfg.config.KafkaConsumerConfig;
import org.gfg.dto.TransactionRequestDto;
import org.gfg.dto.TxnStatusDto;
import org.gfg.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/transaction-service")
public class TransactionController {

    private static Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/doTransaction")
    ResponseEntity<String> doTransaction(@RequestBody @Valid TransactionRequestDto transactionRequest) throws ExecutionException, InterruptedException {
        LOGGER.info("Processing txn  : {}",transactionRequest);
        String txnId = transactionService.doTransaction(transactionRequest);
        return new ResponseEntity(txnId,HttpStatus.ACCEPTED);
    }

    @GetMapping("/status/{txnId}")
    ResponseEntity<TxnStatusDto> getStatus(@PathVariable String txnId){
        LOGGER.info("Fetching yxn status for : {}",txnId);
        TxnStatusDto txnStatusDto = transactionService.getStatus(txnId);
        return ResponseEntity.ok(txnStatusDto);
    }


}
