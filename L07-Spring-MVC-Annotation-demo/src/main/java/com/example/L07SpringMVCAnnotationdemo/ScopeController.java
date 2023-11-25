package com.example.L07SpringMVCAnnotationdemo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class ScopeController {

    private static Set<Object> singletonBeans = new HashSet<>();
    private static Set<Object> prototypeBeans = new HashSet<>();
    private static Set<Object> requestBeans = new HashSet<>();

    private static Logger LOGGER = LoggerFactory.getLogger(ScopeController.class);

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/scopes")
    public String getScopeDetails(){

        singletonBeans.add(applicationContext.getBean("productService", ProductService.class));
        singletonBeans.add(applicationContext.getBean("productService", ProductService.class));

        prototypeBeans.add(applicationContext.getBean("prototypeService", PrototypeService.class));
        prototypeBeans.add(applicationContext.getBean("prototypeService", PrototypeService.class));

        requestBeans.add(applicationContext.getBean("requestScopeService", RequestScopeService.class));
        requestBeans.add(applicationContext.getBean("requestScopeService", RequestScopeService.class));

        LOGGER.info("singletonBeans :{}", singletonBeans.size());
        LOGGER.info("prototypeBeans :{}", prototypeBeans.size());
        LOGGER.info("requestBeans :{}", requestBeans.size());

        return "Done";
    }
}
