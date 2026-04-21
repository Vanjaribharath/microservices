//package com.capgemini.training.middleware.adapter.in.web;
//
//import feign.FeignException;
//import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
//import io.github.resilience4j.bulkhead.BulkheadFullException;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.MDC;
//import org.springframework.http.ProblemDetail;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//@Slf4j
//public class MiddlewareExceptionHandler {
//
//    @ExceptionHandler(FeignException.class)
//    ProblemDetail handleFeignException(FeignException ex) {
//
//        int status = switch (ex.status()) {
//            case 400 -> 400;
//            case 404 -> 404;
//            case 429 -> 429;
//            default  -> 502; // Bad Gateway
//        };
//
//        ProblemDetail pd = ProblemDetail.forStatus(status);
//        pd.setTitle("Downstream Service Error");
//        pd.setDetail("Error calling downstream system-api");
//        pd.setProperty("correlationId", MDC.get("correlationId"));
//
//        log.warn("Downstream error [{}]: {}", ex.status(), ex.getMessage());
//        return pd;
//    }
//
//    @ExceptionHandler(CallNotPermittedException.class)
//    ProblemDetail handleCircuitOpen(CallNotPermittedException ex) {
//
//        ProblemDetail pd = ProblemDetail.forStatus(503);
//        pd.setTitle("Service Temporarily Unavailable");
//        pd.setDetail("Circuit breaker is OPEN for downstream system-api");
//        pd.setProperty("correlationId", MDC.get("correlationId"));
//
//        return pd;
//    }
//
//    @ExceptionHandler(BulkheadFullException.class)
//    ProblemDetail handleBulkhead(BulkheadFullException ex) {
//
//        ProblemDetail pd = ProblemDetail.forStatus(503);
//        pd.setTitle("Service Overloaded");
//        pd.setDetail("Bulkhead limit reached");
//        pd.setProperty("correlationId", MDC.get("correlationId"));
//
//        return pd;
//    }
//}

package com.capgemini.training.middleware.adapter.in.web;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MiddlewareExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignException(FeignException ex) {
        log.error("Downstream system-api error", ex);
        return ResponseEntity
                .status(ex.status())
                .body("Error calling downstream system-api");
    }
}