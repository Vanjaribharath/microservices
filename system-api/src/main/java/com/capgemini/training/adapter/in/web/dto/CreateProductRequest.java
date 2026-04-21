package com.capgemini.training.adapter.in.web.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateProductRequest(
        @NotBlank
        @Size(max = 200)
        String name,

        @NotNull
        @DecimalMin("0.01")
        BigDecimal price,

        @NotNull
        Long categoryId,

        Long supplierId
) {}
