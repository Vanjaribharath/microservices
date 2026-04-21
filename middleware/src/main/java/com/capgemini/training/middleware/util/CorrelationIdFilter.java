package com.capgemini.training.middleware.util;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

//@Component
//public class CorrelationIdFilter implements Filter {
//
//    public static final String HEADER = "X-Correlation-ID";
//
//    @Override
//    public void doFilter(
//            ServletRequest request,
//            ServletResponse response,
//            FilterChain chain) throws IOException, ServletException {
//
//        HttpServletRequest req = (HttpServletRequest) request;
//        String cid = req.getHeader(HEADER);
//
//        if (cid == null) {
//            cid = UUID.randomUUID().toString();
//        }
//
//        MDC.put("correlationId", cid);
//        try {
//            chain.doFilter(request, response);
//        } finally {
//            MDC.clear();
//        }
//    }
//}

import jakarta.servlet.Filter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorrelationIdFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String correlationId = httpRequest.getHeader("X-Correlation-Id");

        if (correlationId != null && !correlationId.isBlank()) {
            MDC.put("correlationId", correlationId);
        }

        try {
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}