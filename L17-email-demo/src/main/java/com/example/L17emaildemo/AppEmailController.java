package com.example.L17emaildemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class AppEmailController {

    private ExecutorService executorService = Executors.newFixedThreadPool(5);
    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest){

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("sk.email.service@gmail.com");
        simpleMailMessage.setSubject(emailRequest.getSubject());
        simpleMailMessage.setTo(emailRequest.getToEmail());
        simpleMailMessage.setText(emailRequest.getBody());
        simpleMailMessage.setCc(emailRequest.getCc());
        javaMailSender.send(simpleMailMessage);
        return ResponseEntity.ok("Email Sent");
    }

    @PostMapping("/sendEmailAsync")
    public ResponseEntity<String> sendEmailAsync(@RequestBody EmailRequest emailRequest){
        Runnable task = () -> {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("sk.email.service@gmail.com");
            simpleMailMessage.setSubject(emailRequest.getSubject());
            simpleMailMessage.setTo(emailRequest.getToEmail());
            simpleMailMessage.setText(emailRequest.getBody());
            simpleMailMessage.setCc(emailRequest.getCc());
            javaMailSender.send(simpleMailMessage);
        };
        executorService.submit(task);
        return new  ResponseEntity<String>("Email will be triggered", HttpStatus.ACCEPTED);
    }
}
