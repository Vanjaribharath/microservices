package com.capgemini.training.middleware.adapter.in.web;

import com.capgemini.training.middleware.application.service.CatalogOrchestrationService;
import com.capgemini.training.middleware.application.service.ProductDetailsService;
import com.capgemini.training.middleware.domain.ProductDetailsView;
import com.capgemini.training.middleware.domain.response.ProductDetailResponse;
import com.capgemini.training.middleware.mapper.ProductMapper;
import com.capgemini.training.middleware.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/middleware/products")
@RequiredArgsConstructor
public class ProductDetailsController {

    private final CatalogOrchestrationService service;
    private final ProductMapper mapper;

    @GetMapping("/{id}/details")
    public ApiResponse<ProductDetailResponse> getDetails(@PathVariable UUID id) {

        var view = service.getProductDetail(id);

        ProductDetailResponse response =
                new ProductDetailResponse(
                        mapper.toEnriched(view.product()),
                        view.category() != null ? view.category().name() : null,
                        view.supplier() != null ? view.supplier().name() : null,
                        view.taxAmount(),
                        view.finalPrice()
                );

        return ApiResponse.of(response);
    }
}