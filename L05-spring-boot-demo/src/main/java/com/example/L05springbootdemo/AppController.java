package com.example.L05springbootdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/hello")
    public String getResponse(){
        return "Hello from :"+Thread.currentThread();
    }


    @GetMapping("/")
    public String defaultResponse(){
        return "Default from :"+Thread.currentThread();
    }

    @GetMapping("/person")
    public Person getPerson(){
        return new Person("RAkesh",25);
    }

}
