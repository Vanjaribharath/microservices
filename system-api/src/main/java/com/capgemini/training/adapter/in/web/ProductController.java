package com.capgemini.training.adapter.in.web;

import com.capgemini.training.adapter.in.web.dto.ProductResponse;
import com.capgemini.training.application.service.ProductService;
import com.capgemini.training.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // ---------- Existing endpoints ----------
    // POST /api/v1/products
    // GET  /api/v1/products/{id}
    // GET  /api/v1/products (paged)
    // DELETE /api/v1/products/{id}

    // ---------- ✅ INTERNAL CLEAN LIST (FOR MIDDLEWARE) ----------
    @GetMapping("/internal/list")
    public List<ProductResponse> listInternal(
            @RequestParam int page,
            @RequestParam int size) {

        return productService
                .list(PageRequest.of(page, size))
                .getContent()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }
}