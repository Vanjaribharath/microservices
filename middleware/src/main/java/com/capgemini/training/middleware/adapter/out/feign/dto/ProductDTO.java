package com.capgemini.training.middleware.adapter.out.feign.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDTO(
        UUID id,
        String name,
        BigDecimal price,
        Long categoryId,
        Long supplierId,
        String status
) {}