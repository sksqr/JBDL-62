package com.example.L13SpringSecurityIntroDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
//        return NoOpPasswordEncoder.getInstance();
    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("rahul@123"));
        System.out.println(passwordEncoder.encode("rahul@123"));
    }


//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user1Details = User.builder()
//                .username("rahul")
//                .password("$2a$10$B1CpBcL0QLg3DjQQcsccW.UPyCyWzpkIOCozUkrBTh/sZk88IURnS")
//                .build();
//        UserDetails user2Details = User.builder()
//                .username("ravi")
//                .password("$2a$10$UYxHogwV4IVFS4FOKsvgseB87buzVcJOD0g/QrMGhAemvDNvApc1i")
//                .build();
//        return new InMemoryUserDetailsManager(user1Details,user2Details);
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf((httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable()))
                .authorizeHttpRequests((auth)->{
                    auth.requestMatchers("/admin/**").hasAuthority("ADMIN")
                    .requestMatchers("/public/**").permitAll()
                            .anyRequest().authenticated();
                })
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
