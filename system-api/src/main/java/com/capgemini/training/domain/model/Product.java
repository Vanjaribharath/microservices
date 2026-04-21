package com.capgemini.training.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Product(
        UUID id,
        String name,
        BigDecimal price,
        Long categoryId,
        Long supplierId,
        ProductStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public Product {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be >= 0");
        }
    }
}