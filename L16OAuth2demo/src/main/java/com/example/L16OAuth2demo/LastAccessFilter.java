package com.example.L16OAuth2demo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class LastAccessFilter extends HttpFilter {

    private static Logger LOGGER = LoggerFactory.getLogger(LastAccessFilter.class);

    @Autowired
    private IMyUserRepo myUserRepo;


    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            OAuth2User user = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (user != null) {
                String email = user.getAttribute("email");
                MDC.put("user",email);
                String name = user.getAttribute("name");
                MyUser myUser = myUserRepo.findByEmail(email);
                if (myUser == null) {
                    myUser = new MyUser();
                    myUser.setName(name);
                    myUser.setEmail(email);
                }
                myUser.setLastAccess(new Date());
                myUserRepo.save(myUser);
            }
        }
        catch (Exception e){
            LOGGER.error("Exception while setting last access");
        }
        chain.doFilter(request,response);
        MDC.clear();
    }
}
