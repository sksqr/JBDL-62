package com.example.L10JPATransactiondemo.controller;


import com.example.L10JPATransactiondemo.service.AOPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aop")
public class AOPController {

    @Autowired
    private AOPService aopService;

    @GetMapping("/data")
    public ResponseEntity<String> getData(){
        String data = aopService.getData();
        return ResponseEntity.ok(data);
    }
}
