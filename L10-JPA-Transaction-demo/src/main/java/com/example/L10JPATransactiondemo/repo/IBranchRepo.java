package com.example.L10JPATransactiondemo.repo;


import com.example.L10JPATransactiondemo.entry.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBranchRepo extends JpaRepository<Branch,Long> {
}
