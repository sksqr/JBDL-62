package com.example.L15resttemplatedemo.controller;

import com.example.L15resttemplatedemo.dto.Blog;
import com.example.L15resttemplatedemo.dto.UserDto;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
@RequestMapping("/public")
public class PublicController {

    private static Logger LOGGER = LoggerFactory.getLogger(PublicController.class);

    @Value("${blog.service.baseUrl}")
    private String blogServiceUrl;

    @Value("${admin.user-service.baseUrl}")
    private String adminUserServiceBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/blogs")
    public ResponseEntity<JsonNode> getBlogs(){
        LOGGER.info("Processing blogs API");
        JsonNode jsonNode = restTemplate.getForObject(blogServiceUrl, JsonNode.class);
        return ResponseEntity.ok(jsonNode);
    }

    @GetMapping("/blog/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id){
        LOGGER.info("Processing blog by Id API");
        String url = blogServiceUrl+id;
        Blog blog = restTemplate.getForObject(url, Blog.class);
        blog.setServerDateTime(new Date());
        return ResponseEntity.ok(blog);
    }

    @PostMapping("/createUser")
    public ResponseEntity<Long> createUser(@RequestBody UserDto userDto){
        LOGGER.info("Processing createUser API");
        //Long id = restTemplate.postForEntity(adminUserServiceBaseUrl,userDto,Long.class,"").getBody();
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.set("authorization","Basic YW1pdDpoYWNr");
        headers.set("X-Request-Id", MDC.get("X-Request-Id"));
        HttpEntity<UserDto> httpEntity = new HttpEntity(userDto,headers);
        Long id = restTemplate.exchange(adminUserServiceBaseUrl, HttpMethod.POST,httpEntity,Long.class).getBody();
        return ResponseEntity.ok(id);
    }
}
