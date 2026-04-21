package com.capgemini.training.adapter.in.web.dto;

import java.math.BigDecimal;

public record ProductRequest(
        String name,
        BigDecimal price,
        Long categoryId,
        Long supplierId
) {}