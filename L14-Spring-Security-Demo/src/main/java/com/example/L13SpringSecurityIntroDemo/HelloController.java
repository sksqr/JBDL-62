package com.example.L13SpringSecurityIntroDemo;

import com.example.L13SpringSecurityIntroDemo.filter.AppRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private static Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @GetMapping()
    public String getHello(@AuthenticationPrincipal UserDetails userDetails){
        LOGGER.info("User info :{}", ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        LOGGER.info("User info with AuthenticationPrincipal :{}", userDetails.getUsername());
        return "Hello from server "+Thread.currentThread().getName();
    }
}
//C155BC2C673D7FA38BF162D206F5378D
//C155BC2C673D7FA38BF162D206F5378D
//C155BC2C673D7FA38BF162D206F5378D
//8830E5E74CF292547EEF4521EB706D93
//8830E5E74CF292547EEF4521EB706D93