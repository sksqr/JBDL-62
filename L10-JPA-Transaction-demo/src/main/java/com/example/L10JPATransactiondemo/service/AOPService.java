package com.example.L10JPATransactiondemo.service;

import com.example.L10JPATransactiondemo.aspect.LogExecutionTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AOPService {
    private static Logger LOGGER = LoggerFactory.getLogger(AOPService.class);

    @LogExecutionTime
    public String getData(){
        LOGGER.info("Returning data");
        return "AOP Service Data";
    }
}
