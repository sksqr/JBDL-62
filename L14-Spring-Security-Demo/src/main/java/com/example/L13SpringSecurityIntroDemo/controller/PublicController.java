package com.example.L13SpringSecurityIntroDemo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping("/blogs")
    public String getBlogs(){
        return "Blogs APIs response from :"+Thread.currentThread().getName();
    }
}
