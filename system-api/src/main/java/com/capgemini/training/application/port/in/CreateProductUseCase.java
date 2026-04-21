package com.capgemini.training.application.port.in;

import com.capgemini.training.domain.model.Product;

import java.math.BigDecimal;
import java.util.UUID;

public interface CreateProductUseCase {

    Product create(String name,
                   BigDecimal price,
                   Long categoryId,
                   Long supplierId);

    Product update(UUID id,
                   String name,
                   BigDecimal price,
                   Long categoryId,
                   Long supplierId);

    void delete(UUID id);
}