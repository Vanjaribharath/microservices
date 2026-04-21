package com.capgemini.training.middleware.util;

import org.slf4j.MDC;

import java.time.Instant;

public record ApiResponse<T>(
        T data,
        ResponseMetadata metadata
) {
    public static <T> ApiResponse<T> of(T data) {
        return new ApiResponse<>(
                data,
                new ResponseMetadata(
                        MDC.get("correlationId"),
                        Instant.now()
                )
        );
    }
}

record ResponseMetadata(
        String correlationId,
        Instant timestamp
) {}