package com.example.L12UnitTestAndIntegrationTestdemo.repo;


import com.example.L12UnitTestAndIntegrationTestdemo.entry.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBranchRepo extends JpaRepository<Branch,Long> {
}
