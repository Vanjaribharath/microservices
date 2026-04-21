package com.capgemini.training.middleware.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Component
//public class CorrelationFilter implements Filter {
//
//    private static final String CORRELATION_ID_HEADER = "X-Correlation-Id";
//
//    @Override
//    public void doFilter(
//            ServletRequest request,
//            ServletResponse response,
//            FilterChain chain
//    ) throws IOException, ServletException {
//
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String correlationId = httpRequest.getHeader(CORRELATION_ID_HEADER);
//
//        if (correlationId != null && !correlationId.isBlank()) {
//            MDC.put("correlationId", correlationId);
//        }
//
//        try {
//            chain.doFilter(request, response);
//        } finally {
//            MDC.clear();
//        }
//    }
//}

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorrelationFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String correlationId =
                httpRequest.getHeader("X-Correlation-Id");

        if (correlationId != null) {
            MDC.put("correlationId", correlationId);
        }

        try {
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}