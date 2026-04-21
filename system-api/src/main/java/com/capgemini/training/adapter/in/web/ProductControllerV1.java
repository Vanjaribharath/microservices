package com.capgemini.training.adapter.in.web;

import com.capgemini.training.adapter.in.web.dto.CreateProductRequest;
import com.capgemini.training.adapter.in.web.dto.ProductRequest;
import com.capgemini.training.adapter.in.web.dto.ProductResponse;
import com.capgemini.training.application.service.ProductService;
import com.capgemini.training.domain.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;




@Tag(name = "Products", description = "Product CRUD APIs")
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductControllerV1 {

    private final ProductService service;

    @Operation(summary = "Create a product")
    @ApiResponse(responseCode = "201", description = "Product created")
    @PostMapping
    public ResponseEntity<ProductResponse> create(
            @Valid @RequestBody CreateProductRequest request) {

        Product p = service.create(
                request.name(), request.price(),
                request.categoryId(), request.supplierId());

        return ResponseEntity
                .created(URI.create("/api/v1/products/" + p.id()))
                .body(ProductResponse.from(p));
    }
}

//@RestController
//@RequestMapping("api/v1/products")
//@RequiredArgsConstructor
//public class ProductControllerV1 {
//
//    private final ProductService service;
//
//    @GetMapping
//    public List<Product> all() {
//        return service.getAllProducts();
//    }
//
//    @GetMapping("/{id}")
//    public Product one(@PathVariable UUID id) {
//        return service.getProduct(id);
//    }
//
//    @PostMapping
//    public Product create(@RequestBody ProductRequest request) {
//        return service.create(
//                request.name(),
//                request.price(),
//                request.categoryId(),
//                request.supplierId()
//        );
//    }
//}