package org.gfg;

import org.gfg.common.RequestFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WalletApp {
    public static void main(String[] args) {
        SpringApplication.run(WalletApp.class,args);
    }

    @Bean
    public RequestFilter getRequestFilter(){
        return new RequestFilter();
    }
}
