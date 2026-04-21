package com.capgemini.training.middleware.domain.response;

import java.math.BigDecimal;

public record ProductDetailResponse(
        EnrichedProductResponse product,
        String categoryName,
        String supplierName,
        BigDecimal taxAmount,
        BigDecimal finalPrice
) {}