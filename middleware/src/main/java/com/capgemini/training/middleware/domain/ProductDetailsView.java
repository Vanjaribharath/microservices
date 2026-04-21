package com.capgemini.training.middleware.domain;

import com.capgemini.training.middleware.adapter.out.feign.dto.*;

import java.math.BigDecimal;

public record ProductDetailsView(
        ProductDTO product,
        CategoryDTO category,
        SupplierDTO supplier,
        BigDecimal taxAmount,
        BigDecimal finalPrice
) {}