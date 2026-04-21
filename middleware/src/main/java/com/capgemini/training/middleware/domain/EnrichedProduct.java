package com.capgemini.training.middleware.domain;

import com.capgemini.training.middleware.adapter.out.feign.dto.ProductDTO;

import java.math.BigDecimal;

public record EnrichedProduct(
        ProductDTO product,
        BigDecimal taxAmount
) {}