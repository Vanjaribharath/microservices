package com.capgemini.training.adapter.in.web;

import com.capgemini.training.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/v3/products")
@RequiredArgsConstructor
public class ProductControllerV3 {

    private final ProductService service;

    @GetMapping("/{id}")
    public ProductResponseV3 one(@PathVariable UUID id) {

        var p = service.getById(id);

        return new ProductResponseV3(
                p.id(),
                p.name(),
                p.price(),
                p.status().name(),
                p.createdAt(),
                Instant.now()
        );
    }
}

record ProductResponseV3(
        UUID id,
        String name,
        java.math.BigDecimal price,
        String status,
        java.time.LocalDateTime createdAt,
        Instant responseTime
) {}