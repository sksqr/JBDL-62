package com.example.L12UnitTestAndIntegrationTestdemo.controller;


import com.example.L12UnitTestAndIntegrationTestdemo.entry.Branch;
import com.example.L12UnitTestAndIntegrationTestdemo.repo.IBranchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    private IBranchRepo branchRepo;

    @GetMapping("/{id}")
    public ResponseEntity<Branch> getEmp(@PathVariable Long id){
        Branch branch = branchRepo.findById(id).orElse(null);
        return ResponseEntity.ok().body(branch);
    }

}
