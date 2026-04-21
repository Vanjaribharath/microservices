package com.capgemini.training.middleware.application.service;

import com.capgemini.training.middleware.adapter.out.feign.SystemApiClient;
import com.capgemini.training.middleware.adapter.out.feign.dto.*;
import com.capgemini.training.middleware.domain.CatalogOverview;
import com.capgemini.training.middleware.domain.ProductDetailsView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class CatalogOrchestrationService {

    private final SystemApiClient systemApi;

    public ProductDetailsView getProductDetail(UUID id) {

        CompletableFuture<ProductDTO> productF =
                CompletableFuture.supplyAsync(() ->
                        systemApi.getProductById(id));

        CompletableFuture<CategoryDTO> categoryF =
                productF.thenApplyAsync(p -> {
                    try {
                        return systemApi.getCategoryById(p.categoryId());
                    } catch (Exception e) {
                        return null; // graceful degradation
                    }
                });

        CompletableFuture<SupplierDTO> supplierF =
                productF.thenApplyAsync(p -> {
                    try {
                        return systemApi.getSupplierById(p.supplierId());
                    } catch (Exception e) {
                        return null; // graceful degradation
                    }
                });

        ProductDTO product = productF.join();
        CategoryDTO category = categoryF.join();
        SupplierDTO supplier = supplierF.join();

        BigDecimal tax = product.price().multiply(BigDecimal.valueOf(0.18));
        BigDecimal finalPrice = product.price().add(tax);

        return new ProductDetailsView(
                product,
                category,
                supplier,
                tax,
                finalPrice
        );
    }

    public CatalogOverview getOverview() {

        CompletableFuture<List<ProductDTO>> topProductsF =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        return systemApi.getProducts(0, 10);
                    } catch (Exception e) {
                        return List.of(); // graceful fallback
                    }
                });

        CompletableFuture<List<CategoryDTO>> categoriesF =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        return systemApi.getCategories();
                    } catch (Exception e) {
                        return List.of(); // graceful fallback
                    }
                });

        CompletableFuture.allOf(topProductsF, categoriesF).join();

        return new CatalogOverview(
                topProductsF.join(),
                categoriesF.join().size(),
                topProductsF.join().size()
        );
    }
}