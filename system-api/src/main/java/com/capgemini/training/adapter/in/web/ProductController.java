package com.capgemini.training.adapter.in.web;


import com.capgemini.training.application.port.in.CreateProductUseCase;
import com.capgemini.training.application.port.in.GetProductUseCase;

import com.capgemini.training.domain.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private final CreateProductUseCase createUseCase;
    private final GetProductUseCase getUseCase;

    public ProductController(CreateProductUseCase createUseCase, GetProductUseCase getUseCase) {
        this.createUseCase = createUseCase;
        this.getUseCase = getUseCase;
    }

    @PostMapping("/product")
    public Product create(@RequestParam ("name") String name, @RequestParam ("price") double price) {
        return createUseCase.create(name, price);
    }

    @GetMapping("/product")
    public String getProduct() {
        return getUseCase.getProduct();
    }
}