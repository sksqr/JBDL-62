package com.example.L06springbootmvcdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

    @Value("${indigo.url}")
    private String url;

    public void flightServiceMethod(){
        System.out.println("URL:"+url);
    }

}
