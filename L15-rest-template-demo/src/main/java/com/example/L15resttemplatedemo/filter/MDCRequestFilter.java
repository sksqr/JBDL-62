package com.example.L15resttemplatedemo.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MDCRequestFilter extends HttpFilter {
    private static Logger LOGGER = LoggerFactory.getLogger(MDCRequestFilter.class);
    @Override
    public void doFilter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        LOGGER.info("Set MDC in MDCRequestFilter");
        MDC.put("X-Request-Id",httpServletRequest.getHeader("X-Request-Id"));
        filterChain.doFilter(httpServletRequest,httpServletResponse);
        MDC.clear();
        LOGGER.info("Processing in MDCRequestFilter before Sending response to Client");
    }
}
