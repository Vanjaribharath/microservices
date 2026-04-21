package com.capgemini.training.middleware.application.service;

import com.capgemini.training.middleware.adapter.out.feign.SystemApiClient;
import com.capgemini.training.middleware.adapter.out.feign.dto.ProductDTO;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResilientProductService {

    private final SystemApiClient systemApi;

    @CircuitBreaker(name = "systemApi", fallbackMethod = "fallbackProducts")
    @Retry(name = "systemApi")
    @Bulkhead(name = "systemApi")
    @TimeLimiter(name = "systemApi")
    public CompletableFuture<List<ProductDTO>> getProducts() {

        return CompletableFuture.supplyAsync(() ->
                systemApi.getProducts(0, 20)); // ✅ FIXED
    }

    private CompletableFuture<List<ProductDTO>> fallbackProducts(Throwable t) {
        log.warn("Circuit open. Returning fallback: {}", t.getMessage());
        return CompletableFuture.completedFuture(List.of());
    }
}