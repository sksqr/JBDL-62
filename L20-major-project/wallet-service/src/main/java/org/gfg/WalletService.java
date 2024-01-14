package org.gfg;

import jakarta.transaction.Transactional;
import org.gfg.common.TransactionInitPayload;
import org.gfg.common.TxnCompPayload;
import org.gfg.common.WalletUpdatedPayload;
import org.gfg.entity.Wallet;
import org.gfg.repo.IWalletRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class WalletService {

    private static String TXN_COMP_TOPIC = "TXN-COMP";

    private static String WALLET_UPDATED = "WALLET-UPDATED";

    private static Logger LOGGER = LoggerFactory.getLogger(WalletService.class);


    @Autowired
    private IWalletRepo walletRepo;

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @Transactional
    public void doWalletTxn(TransactionInitPayload transactionInitPayload) throws ExecutionException, InterruptedException {
        Wallet fromWallet = walletRepo.findByUserId(transactionInitPayload.getFromUserId());
        TxnCompPayload txnCompPayload = TxnCompPayload.builder()
                .id(transactionInitPayload.getId())
                .requestId(transactionInitPayload.getRequestId())
                .build();
        if(fromWallet.getBalance() < transactionInitPayload.getAmount()){
            txnCompPayload.setSuccess(false);
            txnCompPayload.setReason("Low Balance");
        }
        else{
            Wallet toWallet = walletRepo.findByUserId(transactionInitPayload.getToUserId());
            fromWallet.setBalance(fromWallet.getBalance()- transactionInitPayload.getAmount());
            toWallet.setBalance(toWallet.getBalance()+ transactionInitPayload.getAmount());
            txnCompPayload.setSuccess(true);

            // push fromWallet details in Wallet-Updated topic
            // userId, balance

            WalletUpdatedPayload walletUpdatedPayload1 = WalletUpdatedPayload.builder()
                    .userEmail(fromWallet.getEmail())
                    .balance(fromWallet.getBalance())
                    .requestId(MDC.get("requestId"))
                    .build();
            Future<SendResult<String,Object>> future1 =  kafkaTemplate.send(WALLET_UPDATED,String.valueOf(fromWallet.getUserId()),walletUpdatedPayload1);
            LOGGER.info("Pushed txnInit payload: {}",future1.get());

            WalletUpdatedPayload walletUpdatedPayload2 = WalletUpdatedPayload.builder()
                    .userEmail(toWallet.getEmail())
                    .balance(toWallet.getBalance())
                    .requestId(MDC.get("requestId"))
                    .build();
            Future<SendResult<String,Object>> future2 =  kafkaTemplate.send(WALLET_UPDATED,String.valueOf(toWallet.getUserId()),walletUpdatedPayload2);
            LOGGER.info("Pushed txnInit payload: {}",future2.get());
        }
        Future<SendResult<String,Object>> future =  kafkaTemplate.send(TXN_COMP_TOPIC,String.valueOf(transactionInitPayload.getFromUserId()),txnCompPayload);
        LOGGER.info("Pushed txnInit payload: {}",future.get());
    }
}
