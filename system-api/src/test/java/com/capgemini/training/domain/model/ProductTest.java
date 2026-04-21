package com.capgemini.training.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductTest {

    @Test
    void negativePrice_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product(
                        UUID.randomUUID(),
                        "Test Product",
                        new BigDecimal("-10.00"),
                        1L,
                        1L,
                        ProductStatus.DRAFT,
                        LocalDateTime.now(),
                        LocalDateTime.now()
                )
        );
    }
}