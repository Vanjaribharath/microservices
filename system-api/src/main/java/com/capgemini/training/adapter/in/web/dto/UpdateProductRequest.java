package com.capgemini.training.adapter.in.web.dto;

import java.math.BigDecimal;

public record UpdateProductRequest(
        String name,
        BigDecimal price,
        Long categoryId,
        Long supplierId
) {}
