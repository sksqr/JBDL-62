package org.gfg.repo;

import org.gfg.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionRepo extends JpaRepository<Transaction,Long> {
    Transaction findByTxnId(String txnId);
}
