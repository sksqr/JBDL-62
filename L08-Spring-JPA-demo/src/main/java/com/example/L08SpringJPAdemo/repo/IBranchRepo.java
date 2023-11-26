package com.example.L08SpringJPAdemo.repo;

import com.example.L08SpringJPAdemo.entry.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBranchRepo extends JpaRepository<Branch,Long> {
}
