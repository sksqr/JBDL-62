package org.gfg.config;

import org.gfg.common.RequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean
    public RequestFilter getRequestFilter(){
        return new RequestFilter();
    }
}
