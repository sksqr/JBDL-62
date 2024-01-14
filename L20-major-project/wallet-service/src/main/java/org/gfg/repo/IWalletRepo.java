package org.gfg.repo;

import org.gfg.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWalletRepo extends JpaRepository<Wallet,Long> {
    Wallet findByUserId(Long userId);
}
