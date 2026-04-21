package com.capgemini.training.adapter.in.web.dto;

import com.capgemini.training.domain.model.Product;
import com.capgemini.training.domain.model.ProductStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        BigDecimal price,
        Long categoryId,
        Long supplierId,
        ProductStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.id(),
                product.name(),
                product.price(),
                product.categoryId(),
                product.supplierId(),
                product.status(),
                product.createdAt(),
                product.updatedAt()
        );
    }
}