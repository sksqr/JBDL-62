package com.example.L13SpringDataRedisdemo.repo;



import com.example.L13SpringDataRedisdemo.entry.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBranchRepo extends JpaRepository<Branch,Long> {
}
