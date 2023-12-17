package com.example.L13SpringSecurityIntroDemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping()
    public String getHello(){
        return "Hello from server "+Thread.currentThread().getName();
    }
}
//C155BC2C673D7FA38BF162D206F5378D
//C155BC2C673D7FA38BF162D206F5378D
//C155BC2C673D7FA38BF162D206F5378D

//8830E5E74CF292547EEF4521EB706D93
//8830E5E74CF292547EEF4521EB706D93