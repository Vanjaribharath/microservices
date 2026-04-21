package com.capgemini.training.middleware.adapter.in.web;

import com.capgemini.training.middleware.application.service.ProductOrchestrationService;
import com.capgemini.training.middleware.domain.EnrichedProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/middleware/products")
@RequiredArgsConstructor
public class MiddlewareProductController {

    private final ProductOrchestrationService service;

    @GetMapping("/{id}")
    public EnrichedProduct getProduct(@PathVariable UUID id) {
        return service.getEnrichedProduct(id);
    }
}