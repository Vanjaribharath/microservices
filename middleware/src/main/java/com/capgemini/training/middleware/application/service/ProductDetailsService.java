package com.capgemini.training.middleware.application.service;

import com.capgemini.training.middleware.adapter.out.feign.SystemApiClient;
import com.capgemini.training.middleware.adapter.out.feign.dto.*;
import com.capgemini.training.middleware.domain.ProductDetailsView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ProductDetailsService {

    private final SystemApiClient systemApiClient;

    public ProductDetailsView getProductDetails(UUID productId) {

        ProductDTO product = systemApiClient.getProductById(productId);

        CompletableFuture<CategoryDTO> categoryFuture =
                CompletableFuture.supplyAsync(() ->
                        systemApiClient.getCategoryById(product.categoryId()));

        CompletableFuture<SupplierDTO> supplierFuture =
                CompletableFuture.supplyAsync(() ->
                        systemApiClient.getSupplierById(product.supplierId()));

        CategoryDTO category = getSafely(categoryFuture);
        SupplierDTO supplier = getSafely(supplierFuture);

        BigDecimal tax = product.price().multiply(new BigDecimal("0.18"));
        BigDecimal finalPrice = product.price().add(tax);

        return new ProductDetailsView(
                product,
                category,
                supplier,
                tax,
                finalPrice
        );
    }

    private <T> T getSafely(CompletableFuture<T> future) {
        try {
            return future.join();
        } catch (Exception ex) {
            return null; // Graceful degradation
        }
    }
}