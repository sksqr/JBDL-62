package com.example.L18springbootactuatordemo;

import org.springframework.stereotype.Service;

@Service
public class AppService {

    public String msg(){
        return "Hello from "+Thread.currentThread().getName();
    }
}
