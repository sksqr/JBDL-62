package com.example.L10JPATransactiondemo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class SimpleAspect {

    private static Logger LOGGER = LoggerFactory.getLogger(SimpleAspect.class);

    @Before("execution(* com.example.L10JPATransactiondemo.service.AOPService.getData(..))")
    public void beforeMethod(){
        LOGGER.info("Executing beforeMethod Advice");
    }


    @After("execution(* com.example.L10JPATransactiondemo.service.AOPService.getData(..))")
    public void afterMethod(){
        LOGGER.info("Executing afterMethod Advice");
    }

    @Around("@annotation(com.example.L10JPATransactiondemo.aspect.LogExecutionTime)")
    public Object executionLogTime(ProceedingJoinPoint point) throws Throwable {
        // before method
        long start = System.currentTimeMillis();
        //actual method
        Object result = point.proceed();
        //after method
        long end = System.currentTimeMillis();
        long totalTimeTaken = end - start;
        LOGGER.info("ExecutionTime {} ms",totalTimeTaken);
        return result;
    }
}
