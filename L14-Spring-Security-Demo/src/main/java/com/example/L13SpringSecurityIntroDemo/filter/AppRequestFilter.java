package com.example.L13SpringSecurityIntroDemo.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(101)
public class AppRequestFilter extends HttpFilter {
    private static Logger LOGGER = LoggerFactory.getLogger(AppRequestFilter.class);
    @Override
    public void doFilter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        LOGGER.info("Processing in AppRequestFilter before Controller");
        filterChain.doFilter(httpServletRequest,httpServletResponse);
        LOGGER.info("Processing in AppRequestFilter before Sending response to Client");
    }
}
