package com.capgemini.training.middleware.application.service;

import com.capgemini.training.middleware.adapter.out.feign.SystemApiClient;
import com.capgemini.training.middleware.adapter.out.feign.dto.ProductDTO;
import com.capgemini.training.middleware.domain.EnrichedProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MiddlewareService {

    private final SystemApiClient systemApiClient;

    public EnrichedProduct getEnrichedProduct(UUID id) {
        ProductDTO product = systemApiClient.getProductById(id);
        return new EnrichedProduct(
                product,
                product.price().multiply(new BigDecimal("0.18"))
        );
    }
}