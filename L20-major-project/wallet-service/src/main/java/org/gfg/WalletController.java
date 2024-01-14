package org.gfg;


import org.gfg.entity.Wallet;
import org.gfg.repo.IWalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/wallet-service")
public class WalletController {

    private static RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private IWalletRepo walletRepo;

    @GetMapping("/add-money-status/{pgTxnId}")
    public ResponseEntity<String> addMoneyStatus(@PathVariable String pgTxnId){
        PGPaymentStatusDTO pgPaymentStatusDTO = restTemplate.getForObject("http://localhost:9090/pg-service/payment-status/"+pgTxnId, PGPaymentStatusDTO.class);
        if( pgPaymentStatusDTO.getStatus().equalsIgnoreCase("SUCCESS")){
            Wallet wallet = walletRepo.findByUserId(pgPaymentStatusDTO.getUserId());
            wallet.setBalance(wallet.getBalance() + pgPaymentStatusDTO.getAmount());
            walletRepo.save(wallet);
            return ResponseEntity.ok("Wallet Updated");
        }

        return ResponseEntity.ok("PG Txn Failed");

    }

    //web-hook
    @PostMapping("/add-money-webhook")
    public ResponseEntity<String> addMoneyWebhook(@RequestBody PGPaymentStatusDTO pgPaymentStatusDTO){
        if( pgPaymentStatusDTO.getStatus().equalsIgnoreCase("SUCCESS")){
            Wallet wallet = walletRepo.findByUserId(pgPaymentStatusDTO.getUserId());
            wallet.setBalance(wallet.getBalance() + pgPaymentStatusDTO.getAmount());
            walletRepo.save(wallet);
        }
        return ResponseEntity.ok("Done");
    }
}
