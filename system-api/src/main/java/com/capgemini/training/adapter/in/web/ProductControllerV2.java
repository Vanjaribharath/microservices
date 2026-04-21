package com.capgemini.training.adapter.in.web;

import com.capgemini.training.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/v2/products")
@RequiredArgsConstructor
public class ProductControllerV2 {

    private final ProductService service;

    @GetMapping("/{id}")
    public ProductResponseV2 one(@PathVariable UUID id) {
        var p = service.getById(id);

        return new ProductResponseV2(
                p.id(),
                p.name(),
                p.price(),
                p.price().multiply(new BigDecimal("0.18"))
        );
    }
}

record ProductResponseV2(UUID id, String name, BigDecimal price, BigDecimal gst) {}