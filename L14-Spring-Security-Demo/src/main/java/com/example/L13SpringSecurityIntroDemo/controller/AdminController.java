package com.example.L13SpringSecurityIntroDemo.controller;

import com.example.L13SpringSecurityIntroDemo.HelloController;
import com.example.L13SpringSecurityIntroDemo.entity.AppUser;
import com.example.L13SpringSecurityIntroDemo.service.AppUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private static Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @PostMapping("/user")
    ResponseEntity<Long> createUser(@RequestBody AppUser appUser){
        Long id = appUserDetailsService.createUser(appUser);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/hello")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public String getHello(@AuthenticationPrincipal UserDetails userDetails){
        LOGGER.info("User info :{}", ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        LOGGER.info("User info with AuthenticationPrincipal :{}", userDetails.getUsername());
        return "Hello from AdminController "+Thread.currentThread().getName();
    }

    @PostMapping("/hello")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public String postHello(@AuthenticationPrincipal UserDetails userDetails){
        LOGGER.info("User info :{}", ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        LOGGER.info("User info with AuthenticationPrincipal :{}", userDetails.getUsername());
        return "Hello from AdminController "+Thread.currentThread().getName();
    }


    @GetMapping("/changePassword")
    public ResponseEntity<String> changePassword(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String newPassword){
        appUserDetailsService.changePassword(userDetails,newPassword);
        return ResponseEntity.ok("Password changed");

    }

}
//JSESSIONID=C4FE1BE205A420A3AA9EA209607DFA26
//JSESSIONID=C4FE1BE205A420A3AA9EA209607DFA26
//JSESSIONID=C4FE1BE205A420A3AA9EA209607DFA26